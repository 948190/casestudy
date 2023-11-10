package com.sonata.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sonata.dto.BasketProductDTO;
import com.sonata.dto.BasketRequestDTO;
import com.sonata.dto.ProductDescription;
import com.sonata.dto.ProductList;
import com.sonata.dto.UserDTO;
import com.sonata.model.Basket;
import com.sonata.model.Brand;
import com.sonata.model.Inventory;

import com.sonata.model.Product;
import com.sonata.model.User;
import com.sonata.repo.ProductRepository;
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
    
    //13 list of the  product details that belongs to the user
    @GetMapping("user/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
        UserDTO userDTO = productService.getUserDetails(userId);

        

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
  //14 To delete the user by id
    @DeleteMapping("/deleteusers/{userId}")
    public void deleteUserById(@PathVariable long userId) {
        productService.deleteUserById(userId);
    }
    
    //15 To add a new User
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return productService.addUser(user);
    }
    
    //16 To get All the users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return productService.getAllUsers();
    }


    
    //17 To update the user details
	@PutMapping("/users/{userId}")
	public User updateUser(@PathVariable long userId, @RequestBody User updatedUser) {
		
	
    User updatedUser1 = productService.updateUser(userId, updatedUser);

    if (updatedUser1 != null) {
        return updatedUser1;
    }
	return updatedUser1;
    }


	//18 adding items to the basket
	
	@PostMapping("/add")
	public Basket addItemToBasket(@RequestBody BasketRequestDTO basketRequest) {
	    return productService.addItemToBasket(basketRequest);
	}
	//19 to remove item from the basket
	@DeleteMapping("/removeitem/{basketId}")
    public void removeItemFromBasket(@PathVariable Long basketId) {
        productService.removeItemFromBasket(basketId);
    }
	
	//20 to modify the quantity in the basket
	@PutMapping("modifyquantity/{basketId}")
    public void modifyItemQuantity(
            @PathVariable Long basketId,
            @RequestParam int newQuantity
    ) {
        productService.modifyItemQuantity(basketId, newQuantity);
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
//    @GetMapping("/User/{userId}")
//    public ResponseEntity<List<BasketProductDTO>> getBasketProductsByUserId(@PathVariable Long userId) {
//        List<BasketProductDTO> basketProducts = productService.getBasketProductsByUserId(userId);
//        return new ResponseEntity<>(basketProducts, HttpStatus.OK);
//    }
//    
    
}



    
    
    

    
    
    
    

    


