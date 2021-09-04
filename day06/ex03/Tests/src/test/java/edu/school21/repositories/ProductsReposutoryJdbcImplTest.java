package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {
    private Connection connection;
    private ProductsRepository productsRepository;

    public ProductsReposutoryJdbcImplTest() throws SQLException, ClassNotFoundException {
    }

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS;
    {
        Product product0 = new Product(0, "Телефон", 1000);
        Product product1 = new Product(1, "Планшет", 1200);
        Product product2 = new Product(2, "Телевизор", 9000);
        Product product3 = new Product(3, "Наушники", 700);
        Product product4 = new Product(4, "Монитор", 5500);
        Product product5 = new Product(5, "Моноблок", 13000);
        EXPECTED_FIND_ALL_PRODUCTS = new LinkedList<Product>();
        EXPECTED_FIND_ALL_PRODUCTS.add(product0);
        EXPECTED_FIND_ALL_PRODUCTS.add(product1);
        EXPECTED_FIND_ALL_PRODUCTS.add(product2);
        EXPECTED_FIND_ALL_PRODUCTS.add(product3);
        EXPECTED_FIND_ALL_PRODUCTS.add(product4);
        EXPECTED_FIND_ALL_PRODUCTS.add(product5);
    }


    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "Планшет", 1200);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2, "Чайник", 777);
    final Product EXPECTED_SAVED_PRODUCT = new Product("Часы", 9999);

    @BeforeEach
    public void setConnection() throws SQLException, ClassNotFoundException {
        this.connection = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build().getConnection();
        this.productsRepository = new ProductsReposutoryJdbcImpl(connection);
    }

    @Test
    public void testFindById() throws SQLException {
        Assertions.assertEquals(productsRepository.findById(1).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    public void testFindAll() throws SQLException {
        Assertions.assertIterableEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    public void testUpdate() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    public void testSave() throws SQLException {
        EXPECTED_SAVED_PRODUCT.setId(6);
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(6).get());
    }

    @Test
    public void testDelete() throws SQLException {
        Assertions.assertTrue(productsRepository.findById(2).isPresent());
        productsRepository.delete(2);
        Assertions.assertFalse(productsRepository.findById(2).isPresent());
    }
}
