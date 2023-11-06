package com.sonata.service;

import java.util.Date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.dto.BasketProductDTO;
import com.sonata.dto.ProductDescription;
import com.sonata.dto.ProductList;
import com.sonata.model.Author;
import com.sonata.model.Basket;
import com.sonata.model.Brand;
import com.sonata.model.Category;
import com.sonata.model.Inventory;
import com.sonata.model.Person;
import com.sonata.model.Product;
import com.sonata.repo.AuthorRepository;
import com.sonata.repo.BasketRepository;
import com.sonata.repo.BrandRepository;
import com.sonata.repo.CategoryRepository;
import com.sonata.repo.InventoryRepository;
import com.sonata.repo.PersonRepository;
import com.sonata.repo.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private BasketRepository basketRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    
    
    
//    //1
//    public Product getProductWithMaxPriceByCategory(String categoryName) {
//        Category category = categoryRepository.findByName(categoryName);
//
//        if (category == null) {
//            return null;
//        }
//
//        List<Product> productsInCategory = productRepository.findByCategory(category);
//
//        if (productsInCategory.isEmpty()) {
//            return null;
//        }
//
//        return productsInCategory.stream()
//                .max(Comparator.comparing(Product::getPrice))
//                .orElse(null);
//    }
    
    //2
    public Map<String, Long> getProductsPerBrand() {
        
        List<Product> products = productRepository.findAll();
        return products.stream()
                .collect(Collectors.groupingBy(product -> product.getBrand().getName(),
                        Collectors.counting()));
    }
    
    //3
//    public Map<Double, List<Product>> getProductsGroupedByPrice() {
//        List<Product> products = productRepository.findAll();
//        return products.stream().collect(Collectors.groupingBy(Product::getPrice));
//    }
//    
    //4
    public Map<String, List<Product>> getProductsGroupedByColor() {
        List<Product> products = productRepository.findAll();
        return products.stream().collect(Collectors.groupingBy(Product::getColor));
    }
    
    public List<Product> getProductsByColor(String color) {
        
        return productRepository.findByColor(color);
    }
    
    //5
    
    public Map<String, List<Product>> getProductsGroupedBySize() {
        List<Product> products = productRepository.findAll();
        return products.stream().collect(Collectors.groupingBy(Product::getSize));
    }
    
    //6
    public Map<String, List<Product>> getProductsGroupedByBrand() {
        List<Product> products = productRepository.findAll();

        Map<String, List<Product>> productsGroupedByBrand = new HashMap<>();

        for (Product product : products) {
            String brandName = product.getBrand().getName(); 
            List<Product> productList = productsGroupedByBrand.getOrDefault(brandName, new ArrayList<>());

            productList.add(product);
            productsGroupedByBrand.put(brandName, productList);
        }

        return productsGroupedByBrand;
    }
    
    
    //7
    public List<Product> findProductsByIsValid(String productName) {
        Date currentDate = new Date();

        return productRepository.findByNameAndDiscontinuedAfter(productName, currentDate);
    }

    
    
    
    
    //8.1 all
    public List<Map<String, Object>> findProductDetailsByName1(String productName) {
        List<Map<String, Object>> productDetailsList = new ArrayList<>();

        
        List<Product> products = productRepository.findByName(productName);

        for (Product product : products) {
            List<Inventory> batches = product.getInventory();
            for (Inventory batch : batches) {
                Map<String, Object> productDetails = new HashMap<>();
                productDetails.put("productId", product.getProductId());
                productDetails.put("productName", product.getName());
                productDetails.put("batchId", batch.getBatchId());
                productDetails.put("quantity", batch.getQuantity());
                productDetails.put("price", batch.getPrice());
                productDetailsList.add(productDetails);
        }
        }
    

    return productDetailsList;
    }
    
    //8
    
    
    public List<Map<String, Object>> findProductDetailsByName(String productName) {
        List<Map<String, Object>> productDetailsList = new ArrayList<>();

        List<Product> products = productRepository.findByName(productName);

        for (Product product : products) {
            List<Inventory> batches = product.getInventory();

            Inventory maxPriceBatch = findMaxPriceBatch(batches);

            Map<String, Object> productDetails = new HashMap<>();
            productDetails.put("productId", product.getProductId());
            productDetails.put("productName", product.getName());
            productDetails.put("batchId", maxPriceBatch.getBatchId());
            productDetails.put("quantity", maxPriceBatch.getQuantity());
            productDetails.put("price", maxPriceBatch.getPrice());
            productDetailsList.add(productDetails);
        }

        return productDetailsList;
    }

    private Inventory findMaxPriceBatch(List<Inventory> batches) {
        Inventory maxPriceBatch = null;
        double maxPrice = Double.MIN_VALUE;

        for (Inventory batch : batches) {
            if (batch.getPrice() > maxPrice) {
                maxPrice = batch.getPrice();
                maxPriceBatch = batch;
            }
        }

        return maxPriceBatch;
    }



//9
    public List<ProductList> getProductListData() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToProductList)
                .collect(Collectors.toList());
    }

    private ProductList mapToProductList(Product product) {
        // Retrieve the price from the Inventory table, based on the relationship with Product
        List<Inventory> inventoryData = inventoryRepository.findAllByProduct(product);
        double price = inventoryData.stream()
                .mapToDouble(Inventory::getPrice)
                .max()
                .orElse(0.0);

        return new ProductList(product.getName(), price);
}
    
    //10
    public List<ProductDescription> getProductDescriptions() {
        List<Product> products = productRepository.findAll(); // Assuming findAll retrieves all products from the database
        List<ProductDescription> productDescriptions = new ArrayList<>();

        for (Product product : products) {
            ProductDescription productDescription = new ProductDescription(product);
            productDescriptions.add(productDescription);
        }

        return productDescriptions;
    }
    
    //11
    
    
    
//    public Basket createBasket(Long productId, double price, int quantity) {
//        Basket basket = new Basket();
//        basket.setProductId(productId);
//        basket.setPrice(price);
//        basket.setQuantity(quantity);
//        return basketRepository.save(basket);
//    }
    
    //12
    public List<BasketProductDTO> getBasketProductsByUserId(Long userId) {
        List<Basket> baskets = basketRepository.findByUserId(userId);
        List<BasketProductDTO> basketProductDTOs = new ArrayList<>();

        for (Basket basket : baskets) {
            Product product = basket.getProduct();
            BasketProductDTO basketProductDTO = new BasketProductDTO(
               
                product.getName(),
                product.getBrand().getName(),
                product.getCategory().getName(),
                basket.getQuantity(), 
                basket.getPrice()
            		);
            basketProductDTOs.add(basketProductDTO);
        }

        return basketProductDTOs;
    }
    
    
    
    
                
   





   
    
    


    
    
    

    
    
//    public Product addProduct(Product product) {
//        //Create a new Product instance from the Product
//        Product newProduct = new Product();
//        newProduct.setName(product.getName());
//        newProduct.setPrice(product.getPrice());
//        newProduct.setColor(product.getColor());
//        newProduct.setSize(product.getSize());
//        
//
//        
//        newProduct.setDiscontinued(product.getDiscontinued());
//        newProduct.setVisible(product.isVisible());
//
//        // Try to find the Brand in the repository
//        Brand brand = brandRepository.findByName(product.getBrand().getName());
//
//        if (brand == null) {
//            // Create a new Brand if it doesn't exist
//            brand = new Brand();
//            brand.setName(product.getBrand().getName());
//            brand = brandRepository.save(brand);
//        }
//
//        
//        Category category = categoryRepository.findByName(product.getCategory().getName());
//
//        if (category == null) {
//            
//            category = new Category();
//            category.setName(product.getCategory().getName());
//            category = categoryRepository.save(category);
//        }
//
//        
//        newProduct.setBrand(brand);
//        newProduct.setCategory(category);
//
//        
//        return productRepository.save(newProduct);
//    }
//



    
    

//    public Product createProduct(Product product) {
//        
//        Brand brand = brandRepository.findByName(product.getBrand().getName());
//        
//        if (brand == null) {
//            
//        	brand = new Brand();
//            brand.setName(product.getBrand().getName());
//            brand = brandRepository.save(brand);
//        }
//
//        
//        Category category = categoryRepository.findByName(product.getCategory().getName());
//         if (category == null) {
//            
//            category = new Category();
//            category.setName(product.getCategory().getName());
//            category = categoryRepository.save(category);
//        }
//
//        
//        Product newProduct = new Product();
//        newProduct.setName(product.getName());
//        newProduct.setPrice(product.getPrice());
//        newProduct.setColor(product.getColor());
//        newProduct.setSize(product.getSize());
//        newProduct.setBrand(brand);
//        newProduct.setCategory(category);
//
//        
//        return productRepository.save(newProduct);
//    }
    
    public Author getAuthorWithBooks(Long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);

        if (author != null) {
            
            author.getBooks().size(); 
        }

        return author;
    }
    
    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(null);
    }
    
    


    
}



