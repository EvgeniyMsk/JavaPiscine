package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImpl implements ProductsRepository {
    private final Connection connection;

    public ProductsReposutoryJdbcImpl(Connection connection) throws ClassNotFoundException, SQLException {
//        Class.forName("org.hsqldb.jdbc.JDBCDriver");
//        this.connection = DriverManager.getConnection("jdbc:hsqldb:file:/Users/qsymond/Desktop/hsqldb-2.6.0/hsqldb/data/test;hsqldb.lock_file=false", "SA", "");;
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from PRODUCT;");
        List<Product> result = new LinkedList<>();
        while (resultSet.next())
            result.add(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
        statement.close();
        resultSet.close();
        return result;
    }

    @Override
    public Optional<Product> findById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from PRODUCT where IDENTIFIER=?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = null;
        if (resultSet.next()) {
            product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
        }
        preparedStatement.close();
        resultSet.close();
        return Optional.ofNullable(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("update PRODUCT set NAME='" + product.getName() + "', " +
                "PRICE=" + product.getPrice() +
                " where IDENTIFIER=" + product.getId() + ";");
        statement.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("insert into PRODUCT(name, price) values ('" + product.getName() + "', " + product.getPrice() + ");");
        statement.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("delete from PRODUCT where IDENTIFIER=" + id);
        statement.close();
    }
}
