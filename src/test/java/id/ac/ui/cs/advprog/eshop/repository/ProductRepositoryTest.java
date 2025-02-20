package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateProductWithGeneratedId() {
        Product product = new Product();
        product.setProductName("Shampoo XYZ");
        product.setProductQuantity(50);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId()); // Harus memiliki ID
        assertEquals("Shampoo XYZ", createdProduct.getProductName());
        assertEquals(50, createdProduct.getProductQuantity());
    }
    @Test
    void testCreateProductWithNullId() {
        Product product = new Product();
        product.setProductId(null); // ID null
        product.setProductName("Sabun ABC");
        product.setProductQuantity(10);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId()); // Harus membuat ID baru
        assertFalse(createdProduct.getProductId().isEmpty()); // Pastikan tidak kosong
        assertEquals("Sabun ABC", createdProduct.getProductName());
    }

    @Test
    void testCreateProductWithEmptyId() {
        Product product = new Product();
        product.setProductId(""); // Set ID kosong
        product.setProductName("Sabun ABC");
        product.setProductQuantity(30);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId()); // Harus membuat ID baru
        assertFalse(createdProduct.getProductId().isEmpty()); // Pastikan tidak kosong
        assertEquals("Sabun ABC", createdProduct.getProductName());
    }

    @Test
    void testCreateProductWithNegativeQuantity() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Sabun ABC");
        product.setProductQuantity(-10); // Set negatif

        Product createdProduct = productRepository.create(product);

        assertEquals(0, createdProduct.getProductQuantity()); // Harus jadi 0
    }

    @Test
    void testCreateProductWithZeroQuantity() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Sabun ABC");
        product.setProductQuantity(0); // Set 0

        Product createdProduct = productRepository.create(product);

        assertEquals(0, createdProduct.getProductQuantity()); // Tetap 0
    }

    @Test
    void testCreateProductWithPositiveQuantity() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Sabun ABC");
        product.setProductQuantity(10); // Set positif

        Product createdProduct = productRepository.create(product);

        assertEquals(10, createdProduct.getProductQuantity()); // Harus tetap 10
    }

    @Test
    void testUpdateProductWithNegativeQuantity() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Shampoo");
        product.setProductQuantity(10);
        productRepository.create(product);

        product.setProductQuantity(-5); // Set negatif
        Product updatedProduct = productRepository.update(product);

        assertNotNull(updatedProduct);
        assertEquals(0, updatedProduct.getProductQuantity()); // Harus jadi 0
    }

    @Test
    void testUpdateProductWithZeroQuantity() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Shampoo");
        product.setProductQuantity(10);
        productRepository.create(product);

        product.setProductQuantity(0); // Set 0
        Product updatedProduct = productRepository.update(product);

        assertNotNull(updatedProduct);
        assertEquals(0, updatedProduct.getProductQuantity()); // Tetap 0
    }

    @Test
    void testUpdateProductWithPositiveQuantity() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Shampoo");
        product.setProductQuantity(10);
        productRepository.create(product);

        product.setProductQuantity(20); // Set positif
        Product updatedProduct = productRepository.update(product);

        assertNotNull(updatedProduct);
        assertEquals(20, updatedProduct.getProductQuantity()); // Tetap 20
    }

    @Test
    void testUpdateProductNotFound() {
        Product product = new Product();
        product.setProductId("not-exist");
        product.setProductName("Produk Tidak Ada");
        product.setProductQuantity(50);

        Product updatedProduct = productRepository.update(product);
        assertNull(updatedProduct); // Harus null karena produk tidak ada
    }
    @Test
    void testUpdateSecondProduct() {
        // Arrange: Tambah dua produk ke repository
        Product product1 = new Product();
        product1.setProductId("111");
        product1.setProductName("Shampoo A");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("222"); // Produk kedua
        product2.setProductName("Shampoo B");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        // Act: Update produk kedua
        Product updatedProduct = new Product();
        updatedProduct.setProductId("222"); // ID yang sama dengan produk kedua
        updatedProduct.setProductName("Shampoo B - Updated");
        updatedProduct.setProductQuantity(30);

        Product result = productRepository.update(updatedProduct);

        // Assert: Produk kedua diperbarui
        assertNotNull(result);
        assertEquals("222", result.getProductId());
        assertEquals("Shampoo B - Updated", result.getProductName());
        assertEquals(30, result.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdSuccess() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Body Wash");
        productRepository.create(product);

        Product foundProduct = productRepository.findById("123");
        assertNotNull(foundProduct);
        assertEquals("123", foundProduct.getProductId());
    }

    @Test
    void testFindByIdFailure() {
        Product foundProduct = productRepository.findById("not-exist");
        assertNull(foundProduct);
    }

    @Test
    void testDeleteProductSuccess() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Shampoo Original");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.delete("123");

        Product deletedProduct = productRepository.findById("123");
        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProductFailure() {
        productRepository.delete("not-exist"); // Harus tetap aman
        assertNull(productRepository.findById("not-exist"));
    }
}
