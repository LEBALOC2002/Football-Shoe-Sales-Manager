package views;

import model.Product;
import service.IProductService;
import service.ProductService;
import utils.AppUtils;
import utils.ValidateUtils;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private static final Scanner scanner = new Scanner(System.in);
    private IProductService productService;


    public ProductView() {
        productService = ProductService.getInstance();
    }

    public void showProductSort(InputOption inputOption, List<Product> products) {

        System.out.println("||《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《《》《》《》《》《》||");
        System.out.printf("%-15s | %-18s %-60s %-15s %-10s\n", "ID", "Size", "Tên sản phẩm", "Giá", "Số lượng");
        for (Product product : products) {
            System.out.printf("%-15s | %-18s %-60s %-15s %-10s\n", product.getId(), product.getSize(), product.getTitle(), product.getPrice(), product.getQuantity());
        }
        System.out.println("||《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》||");
        if (inputOption == InputOption.SHOW) AppUtils.isRetry(InputOption.SHOW);
    }

    public static void showProduct(InputOption option) {
        List<Product> productList = ProductService.getInstance().findAll();
        System.out.println("||《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》 DANH SÁCH SẢN PHẨM 《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》||");
        System.out.printf("%-15s | %-18s %-60s %-15s %-10s\n", "ID", "Size", "Tên sản phẩm", "Giá", "Số lượng");
        for (Product product : productList) {
            System.out.printf("%-15s | %-18s %-60s %-15s %-10s\n", product.getId(), product.
                    getSize(), product.getTitle(), AppUtils.doubleToVND(product.getPrice()), product.getQuantity());
        }
        System.out.println("||《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《《》《》《》《》《》||");
    }

    public void addProduct() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                System.out.println("Nhập size:");
                System.out.print("⏹⏹▶ ");
                int size = Integer.parseInt(scanner.nextLine());
                while (size < 10 || size > 45) {
                    System.out.println("Size không có! vui lòng chọn size khác!");
                    System.out.print("⏹⏹▶ ");
                    size = Integer.parseInt(scanner.nextLine()) ;
                }
                System.out.println("Nhập tên sản phẩm");
                System.out.print("⏹⏹▶ ");
                String title = scanner.nextLine();
                while ( title == null || title.isEmpty()) {
                    System.out.println("Bạn nhập không đúng dạng! xin vui lòng nhập lại");
                    System.out.print("⏹⏹▶ ");
                    title = scanner.nextLine();
                }
                System.out.println("Nhập giá:");
                System.out.print("⏹⏹▶ ");
                double price = Double.parseDouble(scanner.nextLine());
                while (price < 0 || price > 10000000) {
                    System.out.println("Vui lòng nhập đúng giá sản phẩm");
                    System.out.print("⏹⏹▶ ");
                    price = scanner.nextDouble();

                }
                System.out.println("Nhập số lượng:");
                System.out.print("⏹⏹▶ ");
                int quantity = Integer.parseInt(scanner.nextLine());
                while (quantity < 0 || quantity > 10000) {
                    System.out.println("Số lượng tối đa là 10000 đôi");
                    System.out.print("⏹⏹▶ ");
                    quantity = scanner.nextInt();
                }
                Instant createAt = Instant.now();
                Product product = new Product(id, size, title, price, quantity, createAt, null);
                productService.add(product);
                System.out.println("Bạn đã thêm thành công!");

            } catch (Exception e) {
                System.out.println("Nhập sai! Vui lòng nhập lại!");
            }
        } while (AppUtils.isRetry(InputOption.ADD));
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng bạn muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity < 0 || quantity > 10000)
                System.out.println("Số lượng phải lớn hơn 0 hoặc bé hơn 10!");
        } while (quantity < 0 || quantity > 10000);
        return quantity;
    }

    public int inputSize() {
        do {
            System.out.println("nhap size san pham: ");
            int size = Integer.parseInt(scanner.nextLine());
            if (size < 10 || size > 45) {
                System.out.println("Nhap khong dung! Vui long nhap lai.");
                continue;
            }
            return size;
        }
        while (true);
    }


    private int inputId(InputOption inputOption) {
        int id;
        switch (inputOption) {
            case ADD:
                System.out.println("Nhập ID: ");
                break;
            case UPDATE:
                System.out.println("Nhập ID bạn muốn sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập ID bạn muốn xóa: ");
                break;
        }
        boolean isRetry = false;
        System.out.print("⏹⏹▶ ");
        do {
            id = AppUtils.retryParseInt();
            boolean exist = productService.existById(id);
            switch (inputOption) {
                case ADD:
                    if (exist) {
                        System.out.println("ID này đã tồn tại.Vui lòng nhập ID khác!");

                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thất ID! Vui lòng nhập lại");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    public double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price < 1000)
                System.out.println("giá phải lớn hơn 0!");
        } while (price < 1000);
        return price;
    }


    public void sortByPriceByASC() {
        showProductSort(InputOption.SHOW, productService.sortQuantityASC());
    }

    public void sortByPriceByDESC() {
        showProductSort(InputOption.SHOW, productService.sortQuantityDESC());
    }

    public void updateProduct() {
        boolean isRetry;
        do {
            showProduct(InputOption.UPDATE);
            long id = inputId(InputOption.UPDATE);
            System.out.println("|《》《》《》《》《》《》《》《》《》《》《》《》|SỬA SẢN PHẨM|《》《》《》《》《》《》《》《》《》《》《》《》《 |");
            System.out.println("|⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌  |");
            System.out.println("|《》                       1. Sửa size sản phẩm                                                 《》|");
            System.out.println("|《》                       2. Sửa tên sản phẩm                                                  《》|");
            System.out.println("|《》                       3. Sửa giá sản phẩm                                                  《》|");
            System.out.println("|《》                       4. Sửa số lượng sản phẩm                                             《》|");
            System.out.println("|《》                       5. Quay lại Menu                                                     《》|");
            System.out.println("|《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》 |");
            System.out.println(" ⏹ Chọn chức năng mà bạn muốn:                     ");
            int option = AppUtils.retryChoose(1, 5);
            Product newProduct = productService.existsById(id);
            switch (option) {

                case 1:
                    int size = inputSize();
                    newProduct.setSize(size);
                    productService.update(newProduct);
                    System.out.println("Size sản phẩm đã cập nhật thành công");
                    showProduct(InputOption.SHOW);
                    break;
                case 2:
                    String title = inputFullName(InputOption.UPDATE);
                    newProduct.setTitle(title);
                    productService.update(newProduct);
                    System.out.println("Tên sản phẩm đã cập nhật thành công");
                    showProduct(InputOption.SHOW);
                    break;
                case 3:
                    double price = inputPrice(InputOption.UPDATE);
                    newProduct.setPrice(price);
                    productService.update(newProduct);
                    System.out.println("Bạn đã sửa giá sản phẩm thành công");
                    showProduct(InputOption.SHOW);
                    break;
                case 4:
                    int quantity = inputQuantity(InputOption.UPDATE);
                    newProduct.setQuantity(quantity);
                    productService.update(newProduct);
                    System.out.println("Số lượng sản phẩm đã cập nhật thành công");
                    showProduct(InputOption.SHOW);
                    break;
                case 5 :
                    ProductViewLauncher.launch();
                    break;
            }
            isRetry = option != 5 && AppUtils.isRetry(InputOption.UPDATE);
        }
        while (isRetry);
    }

    public void searchByTitle() {
        try {
            showProduct(InputOption.SHOW);
            System.out.println("Nhap id cua san pham: ");
            long id = inputId(InputOption.SHOW);
            productService.searchById(id);
        } catch (Exception e) {
            System.out.println("Nhap sai! Vui long nhap lai");
        }
    }


    public void removeProduct() {
        showProduct(InputOption.SHOW);
        System.out.println("Nhap id ban muon xoa: ");
        long idProduct = scanner.nextLong();
        while (!productService.existById(idProduct)) {
            System.out.println("Bạn nhập id không đúng! vui lòng nhập lại");
            idProduct = scanner.nextLong();
        }
        productService.remove(idProduct);
        System.out.println("Bạn xóa thành công ");
        showProduct(InputOption.SHOW);
    }

    private String inputFullName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập họ và tên (vd: Loc SaDo) ");
                break;
            case UPDATE:
                System.out.println("Nhập tên mà bạn muốn sửa đổi");
                break;
        }

        System.out.print("⏹⏹▶ ");
        String fullName = scanner.nextLine();
        while (!ValidateUtils.NameValid(fullName) || fullName == null || fullName.isEmpty()) {
            System.out.println("Tên " + fullName + " không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
            System.out.println("Nhập tên (vd: Le Ba Loc) ");
            System.out.print("⏹⏹▶ ");
            fullName = scanner.nextLine();
        }
        return fullName;
    }
}
