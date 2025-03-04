package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productsData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(UUID.randomUUID().toString()); // Set ID jika kosong
        }
        if (product.getProductQuantity() < 0) {
            product.setProductQuantity(0); // Set quantity ke 0 jika negatif
        }
        productsData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productsData.iterator();
    }

    public Product findById(String productId) {
        return productsData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product update(Product updatedProduct) {
        for (int i = 0; i < productsData.size(); i++) {
            if (productsData.get(i).getProductId().equals(updatedProduct.getProductId())) {
                if (updatedProduct.getProductQuantity() < 0) {
                    updatedProduct.setProductQuantity(0); // Set quantity ke 0 jika negatif
                }
                productsData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    public void delete(String productId) {
        productsData.removeIf(product -> product.getProductId().equals(productId));
    }
}
