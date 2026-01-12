package com.example.module_s10_giohang.controller;


import com.example.module_s10_giohang.entity.Cart;
import com.example.module_s10_giohang.entity.Product;
import com.example.module_s10_giohang.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ShoppingController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@SessionAttribute("cart") Cart cart){
        ModelAndView modelAndView = new ModelAndView("views/cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }

    @GetMapping("/shopping-cart/detail/{productId}")
    public ModelAndView showProductDetail(@PathVariable Long productId,
                                          @SessionAttribute("cart") Cart cart) {
        Optional<Product> productOptional = productService.findById(productId);
        if (!productOptional.isPresent()) {
            return new ModelAndView("views/error_404");
        }
        
        // Find quantity in cart
        Integer quantity = 0;
        for (var entry : cart.getProducts().entrySet()) {
            if (entry.getKey().getId().equals(productId)) {
                quantity = entry.getValue();
                break;
            }
        }
        
        ModelAndView modelAndView = new ModelAndView("views/cart_detail");
        modelAndView.addObject("product", productOptional.get());
        modelAndView.addObject("quantity", quantity);
        return modelAndView;
    }

    @PostMapping("/shopping-cart/update/{productId}")
    public String updateQuantity(@PathVariable Long productId,
                                 @RequestParam("quantity") Integer quantity,
                                 @SessionAttribute("cart") Cart cart) {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            cart.updateProductQuantity(productOptional.get(), quantity);
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/increase/{productId}")
    public String increaseQuantity(@PathVariable Long productId,
                                   @SessionAttribute("cart") Cart cart) {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            cart.increaseProduct(productOptional.get());
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/decrease/{productId}")
    public String decreaseQuantity(@PathVariable Long productId,
                                   @SessionAttribute("cart") Cart cart) {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            cart.decreaseProduct(productOptional.get());
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/remove/{productId}")
    public String removeProduct(@PathVariable Long productId,
                               @SessionAttribute("cart") Cart cart) {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            cart.removeProduct(productOptional.get());
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/clear")
    public String clearCart(@SessionAttribute("cart") Cart cart) {
        cart.clearCart();
        return "redirect:/shopping-cart";
    }

    @PostMapping("/shopping-cart/checkout")
    public ModelAndView checkout(@SessionAttribute("cart") Cart cart) {
        Float totalAmount = cart.countTotalPayment();
        Integer totalItems = cart.countProductQuantity();
        
        // Clear cart after checkout
        cart.clearCart();
        
        ModelAndView modelAndView = new ModelAndView("views/checkout_success");
        modelAndView.addObject("totalAmount", totalAmount);
        modelAndView.addObject("totalItems", totalItems);
        return modelAndView;
    }
}