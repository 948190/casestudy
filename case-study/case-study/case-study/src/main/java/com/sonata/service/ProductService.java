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

import com.sonata.model.Audit;
import com.sonata.model.Basket;
import com.sonata.model.Brand;
import com.sonata.model.Category;
import com.sonata.model.Inventory;

import com.sonata.model.Product;
import com.sonata.model.User;
import com.sonata.dto.*;

import com.sonata.repo.BasketRepository;
import com.sonata.repo.BrandRepository;
import com.sonata.repo.CategoryRepository;
import com.sonata.repo.InventoryRepository;

import com.sonata.repo.ProductRepository;
import com.sonata.repo.UserRepository;

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
    private UserRepository userRepository;
    
    
    
    
    
    
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
//    public List<BasketProductDTO> getBasketProductsByUserId(Long userId) {
//        List<Basket> baskets = basketRepository.findByUserId(userId);
//        List<BasketProductDTO> basketProductDTOs = new ArrayList<>();
//
//        for (Basket basket : baskets) {
//            Product product = basket.getProduct();
//            BasketProductDTO basketProductDTO = new BasketProductDTO(
//               
//                product.getName(),
//                product.getBrand().getName(),
//                product.getCategory().getName(),
//                basket.getQuantity(), 
//                basket.getPrice()
//            		);
//            basketProductDTOs.add(basketProductDTO);
//        }
//
//        return basketProductDTOs;
//    }
    
    //13 to list the products which belongs to the user
    public UserDTO getUserDetails(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null; 
        }
        
        

        List<Basket> baskets = basketRepository.findByUser(user);
        List<ProductDTO> products = new ArrayList<>();
        Double totalBasketPrice = 0.0; 
        Double totalMrpPrice=0.0;
        

        for (Basket basket : baskets) {
            Product product = basket.getProduct();
            ProductDTO productDTO = new ProductDTO(product);
            
            Audit audit = product.getAudit();
            productDTO.setCreatedTimestamp(audit.getCreatedTimestamp());
            productDTO.setUpdatedBy(audit.getUpdatedBy());
            productDTO.setUpdatedTimestamp(audit.getUpdatedTimestamp());
            

            
            Double mrpPrice = inventoryRepository.findMaxPriceByProductId(product.getProductId());
            productDTO.setMrpPrice(mrpPrice);
            productDTO.setListPrice(basket.getPrice());

            productDTO.setQuantity(basket.getQuantity());
            productDTO.setBasketId(basket.getBasketId());
            Long maxInventoryBatchId = inventoryRepository.findBatchIdForMaxPrice(product.getProductId(), mrpPrice);
            productDTO.setBatchId(maxInventoryBatchId);
            Double discount=mrpPrice-basket.getPrice();
            productDTO.setDiscount(discount);
           
            products.add(productDTO);
            totalBasketPrice += basket.getPrice();
            totalMrpPrice+=mrpPrice;
            
        }

        UserDTO userDTO = new UserDTO(user);
        userDTO.setProducts(products);
        userDTO.setTotalBasketPrice(totalBasketPrice);
        userDTO.setTotalMrpPrice(totalMrpPrice);
        Double totalDiscountPrice=totalMrpPrice-totalBasketPrice;
        userDTO.setTotalDiscountPrice(totalDiscountPrice);
        Long TotalProducts=(long)products.size();
        userDTO.setTotalProducts(TotalProducts);
        

        return userDTO;
    }
    
    //14 To delete the user by id
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }
    
    //15 to add a new user
    public User addUser(User user) {
       
        return userRepository.save(user);
    }
    
    //16 to fetch the all the user deatils
    public List<User> getAllUsers(){
    	return userRepository.findAllUsers();
    	
    }
    
    //17 TO modify the user deatils
    
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public User updateUser(long userId, User updatedUser) {
        User existingUser = getUserById(userId);

        if (existingUser != null) {
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNum(updatedUser.getPhoneNum());

            return userRepository.save(existingUser);
        
            
            
        }
		return existingUser;
    }
    
    
    //18 adding an item to the basket
    public Basket addItemToBasket(BasketRequestDTO basketRequest) {
        Long userId = basketRequest.getUserId();
        Long productId = basketRequest.getProductId();
        int quantity = basketRequest.getQuantity();
        double price = basketRequest.getPrice();

        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            
            return null;
        }

        Basket basketItem = new Basket();
        basketItem.setUser(user);
        basketItem.setProduct(product);
        basketItem.setQuantity(quantity);
        basketItem.setPrice(price);

        basketRepository.save(basketItem);

        return basketItem;
    }
    
    //19 to remove the item from the basket
    public void removeItemFromBasket(Long basketId) {
        
        Basket basket = basketRepository.findById(basketId).orElse(null);

        if (basket != null) {
            
            basketRepository.delete(basket);
        
    }
    }
    
    //20 to modify the quantity in the basket
    public void modifyItemQuantity(Long basketId, int newQuantity) {
        
        Basket basket = basketRepository.findById(basketId).orElse(null);

        if (basket != null) {
           
            basket.setQuantity(newQuantity);
            basketRepository.save(basket);
        
    }

    
    
    
                
   





   
    
    


    
    
    

    
    

    
   
    
    


    
    }
}





