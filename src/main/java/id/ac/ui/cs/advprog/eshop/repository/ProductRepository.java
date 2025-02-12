package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productsData = new ArrayList<>();

    public Product create(Product product) {
        productsData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productsData.iterator();
    }
}
