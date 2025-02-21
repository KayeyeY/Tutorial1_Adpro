package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @Mock
    Model model;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage() {
        String result = productController.createProductPage(model);
        assertEquals("CreateProduct", result);
    }

    @Test
    void testCreateProductPost() {
        when(productService.create(any(Product.class))).thenReturn(product);
        String result = productController.createProduct(product, model);
        assertEquals("redirect:list", result);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.findAll()).thenReturn(productList);

        String result = productController.productListPage(model);
        assertEquals("ProductList", result);
        verify(model).addAttribute(eq("products"), anyList());
    }

    @Test
    void testEditProductPage() {
        when(productService.findById(product.getProductId())).thenReturn(product);
        String result = productController.editProductPage(product.getProductId(), model);
        assertEquals("EditProduct", result);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPost() {
        when(productService.update(any(Product.class))).thenReturn(product);
        String result = productController.editProduct(product);
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testDeleteProduct() {
        String result = productController.deleteProduct(product.getProductId());
        assertEquals("redirect:/product/list", result);
    }
}
