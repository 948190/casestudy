package com.sonata.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sonata.dto.BasketProductDTO;
import com.sonata.dto.ProductDescription;
import com.sonata.dto.ProductList;
import com.sonata.model.Author;
import com.sonata.model.Basket;
import com.sonata.model.Brand;
import com.sonata.model.Inventory;
import com.sonata.model.Person;
import com.sonata.model.Product;
import com.sonata.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    
    
    
    
    //1.Fetch a data of particular products based on its Id, which has max price
    
//    @GetMapping("/maxprice")
//    public ResponseEntity<Product> getProductWithMaxPriceByCategory(@RequestParam String categoryName) {
//        Product maxPriceProduct = productService.getProductWithMaxPriceByCategory(categoryName);
//
//        
//            return new ResponseEntity<>(maxPriceProduct, HttpStatus.OK);
//        
//        }

    //2.No. Of products per brands
    @GetMapping("/products-per-brand")
    public ResponseEntity<Map<String, Long>> getProductsPerBrand() {
        Map<String, Long> productCountPerBrand = productService.getProductsPerBrand();
        return new ResponseEntity<>(productCountPerBrand, HttpStatus.OK);
    }
    
    //3.List of products Group by price
//    @GetMapping("/products-grouped-by-price")
//    public ResponseEntity<Map<Double, List<Product>>>
//    getProductsGroupedByPrice() {
//        Map<Double, List<Product>> productsGroupedByPrice = productService.getProductsGroupedByPrice();
//        return new ResponseEntity<>(productsGroupedByPrice, HttpStatus.OK);
    
    //4.
    @GetMapping("/products-grouped-by-color")
    public ResponseEntity<Map<String, List<Product>>>
    getProductsGroupedByColor() {
        Map<String, List<Product>> productsGroupedByColor = productService.getProductsGroupedByColor();
        return new ResponseEntity<>(productsGroupedByColor, HttpStatus.OK);
    }
    
    @GetMapping("/products-by-color")
    public ResponseEntity<List<Product>> getProductsByColor(@RequestParam String color) {
        List<Product> productsByColor = productService.getProductsByColor(color);
        return new ResponseEntity<>(productsByColor, HttpStatus.OK);
    }
    //5.List of products group by size
    @GetMapping("/products-grouped-by-size")
    public ResponseEntity<Map<String, List<Product>>>
    getProductsGroupedBySize() {
        Map<String, List<Product>> productsGroupedBySize = productService.getProductsGroupedBySize();
        return new ResponseEntity<>(productsGroupedBySize, HttpStatus.OK);
    }
    
    //6.List of products group by brands
    @GetMapping("/products-grouped-by-brand")
    public ResponseEntity<Map<String, List<Product>>> getProductsGroupedByBrand() {
        Map<String, List<Product>> productsGroupedByBrand = productService.getProductsGroupedByBrand();
        return new ResponseEntity<>(productsGroupedByBrand, HttpStatus.OK);
    }
    
    
    //7
    @GetMapping("/isvalid")
    public List<Product> searchProducts(
            @RequestParam(name = "name") String name
    ) {
        return productService.findProductsByIsValid(name);
    }
    
    
    
    
    //8
    @GetMapping("/search")
    public List<Map<String, Object>> searchProductsByName(@RequestParam String name) {
        return productService.findProductDetailsByName(name);
    }
    //8.1 all
    @GetMapping("/all")
    public List<Map<String, Object>> searchProductsByName1(@RequestParam String name) {
        return productService.findProductDetailsByName(name);
    }
    //9
    @GetMapping("/list")
    public List<ProductList> getProductListData() {
        return productService.getProductListData();
    }
    
    //10
    @GetMapping("/details")
    public List<ProductDescription> getProductDescriptions() {
        return productService.getProductDescriptions();
    }
    
    //11
//    @PostMapping("/creates")
//    public ResponseEntity<Basket> createBasket(@RequestBody Map<String, Object> requestData) {
//        Long productId = Long.valueOf(requestData.get("productId").toString());
//        double price = (double) requestData.get("price");
//        int quantity = (int) requestData.get("quantity");
//
//        Basket basket = productService.createBasket(productId, price, quantity);
//        return new ResponseEntity<>(basket, HttpStatus.CREATED);
//    }
    
    //12
    @GetMapping("/User/{userId}")
    public ResponseEntity<List<BasketProductDTO>> getBasketProductsByUserId(@PathVariable Long userId) {
        List<BasketProductDTO> basketProducts = productService.getBasketProductsByUserId(userId);
        return new ResponseEntity<>(basketProducts, HttpStatus.OK);
    }
    
    
    
//    @PostMapping("/products")
//    public Product addProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }

//    @PostMapping("/create")
//    public ResponseEntity<String> createProduct(@RequestBody Product productDTO) {
//        try {
//            Product createdProduct = productService.createProduct(productDTO);
//            return new ResponseEntity<>("Product created successfully with ID: " + createdProduct.getProductId(), HttpStatus.CREATED);
//        } catch (Exception e) {
//            
//            return new ResponseEntity<>("Failed to create the product: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
    
    
    @GetMapping("/authors/{authorId}")
    public ResponseEntity<Author> getAuthorWithBooks(@PathVariable Long authorId) {
        Author author = productService.getAuthorWithBooks(authorId);

        
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
    @GetMapping("/people/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long personId) {
        Person person = productService.getPersonById(personId);

        
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    

    

}
