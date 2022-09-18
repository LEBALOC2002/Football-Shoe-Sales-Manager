package service;

import model.Product;
import utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ProductService implements IProductService {

    public final static String path = "C:\\Module2\\Sports shoe sales manager\\src\\data\\products.csv";

    private static ProductService instance;

    public ProductService() {
    }

    public static IProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }


    @Override
    public List<Product> getProduct() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            products.add(Product.parseProduct(record));
        }
        return products;
    }


    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            products.add(Product.parseProduct(record));
        }
        return products;
    }

    @Override
    public Product findById(long id) {
        List<Product> products = findAll();
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void searchByTitle(String title) {
        List<Product> products = findAll();
        for (Product p : products) {
            if (p.getTitle().equals(title)) {
                return;
            }
        }
    }


    @Override
    public Product searchById(long id) {
        return null;
    }

    @Override
    public Product existsById(long id) {
        List<Product> products = findAll();
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void add(Product newProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            String newName = newProduct.getTitle().replace(" ", "").toLowerCase();
            String nameProduct = product.getTitle().replace(" ", "").toLowerCase();
            if (newName.equals(nameProduct) && newProduct.getPrice() == (product.getPrice())) {
                product.setQuantity(product.getQuantity() + newProduct.getQuantity());
                CSVUtils.write(path, products);
                return;
            }
        }
        products.add(newProduct);
        CSVUtils.write(path, products);
    }

    public void update(Product newProduct) {
        List<Product> products = getProduct();
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                product.setTitle(newProduct.getTitle());
                product.setPrice(newProduct.getPrice());
                product.setSize(newProduct.getSize());
                product.setQuantity(newProduct.getQuantity());
                product.setUpdatedAt(Instant.now());
                CSVUtils.write(path, products);
                break;
            }
        }
    }

    @Override
    public void remove(long id) {
        List<Product> products = getProduct();
        products.removeIf(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {

                return product.getId() == id;
            }
        });
        CSVUtils.write(path, products);
    }

    @Override
    public List<Product> sortQuantityASC() {
        List<Product> products = new ArrayList<>(getProduct());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                double result = o2.getPrice() - o1.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });
        return products;

    }

    @Override
    public List<Product> sortQuantityDESC() {
        List<Product> products = new ArrayList<>(getProduct());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                double result = o1.getPrice() - o2.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });
        return products;
    }

    @Override
    public void updateQuantity(long id, int quantity) {
        List<Product> products = getProduct();
        for (Product product : products) {
            if (product.getId() == id)
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    CSVUtils.write(path, products);
                    break;
                }
        }
    }

    @Override
    public boolean existById(long id) {
        List<Product> p = getProduct();
        for (Product product : p) {
            if ( product.getId() == id)
                return true;
        }
        return false;
    }
}