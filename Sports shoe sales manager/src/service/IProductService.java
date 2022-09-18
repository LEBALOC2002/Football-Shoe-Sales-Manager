package service;

import model.Product;

import java.util.List;

public interface IProductService {


    List<Product> getProduct();

    List<Product> findAll();

    Product findById(long id);

    void searchByTitle(String title);
    Product searchById(long id);

    Product existsById(long id);

    void add(Product newProduct);

    void update(Product newProduct);

    void remove(long newProduct);


    List<Product> sortQuantityASC();

    List<Product> sortQuantityDESC();

    void updateQuantity(long id, int quantity);

    boolean existById(long id);
}