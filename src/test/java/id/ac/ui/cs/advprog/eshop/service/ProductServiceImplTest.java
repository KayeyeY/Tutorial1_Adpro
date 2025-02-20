package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("12345");
        product.setProductName("Sample Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Sample Product", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Iterator<Product> iteratorMock = productList.iterator();
        when(productRepository.findAll()).thenReturn(iteratorMock);

        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals("Sample Product", result.get(0).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById("12345")).thenReturn(product);
        Product foundProduct = productService.findById("12345");

        assertNotNull(foundProduct);
        assertEquals("12345", foundProduct.getProductId());
        verify(productRepository, times(1)).findById("12345");
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.update(product)).thenReturn(product);
        Product updatedProduct = productService.update(product);

        assertNotNull(updatedProduct);
        assertEquals("Sample Product", updatedProduct.getProductName());
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testDeleteProduct() {
        productService.delete("12345");

        verify(productRepository, times(1)).delete("12345");
    }
}
