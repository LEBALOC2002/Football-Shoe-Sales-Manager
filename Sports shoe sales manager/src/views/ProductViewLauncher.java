package views;

import java.util.Scanner;

public class ProductViewLauncher {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductView productView = new ProductView();

    public static void launch() {

            int choice;
            do {
                System.out.println("《》《》《》《》《》《》《》《》|QUẢN LÍ SẢN PHẨM|《》《》《》《》《》《》《》《》");
                System.out.println("《》                             Main Menu                             《》");
                System.out.println("《》                                                                   《》");
                System.out.println("《》                         1. Hiển thị danh sách.                    《》");
                System.out.println("《》                         2. Thêm sản phẩm.                         《》");
                System.out.println("《》                         3. Sửa sản phẩm.                          《》");
                System.out.println("《》                         4. Xóa sản phẩm.                          《》");
                System.out.println("《》                         5. Sắp xếp giá tăng dần.                  《》");
                System.out.println("《》                         6. Sắp xếp giá giảm dần.                  《》");
                System.out.println("《》                         7. Quay lại.                              《》");
                System.out.println("《》                                                                   《》");
                System.out.println("《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》");
                System.out.println("Chọn chức năng mà bạn muốn chọn");
                System.out.print("⏹⏹▶ ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        productView.showProduct(InputOption.SHOW);
                        break;
                    case 2:
                        productView.addProduct();
                        break;
                    case 3:
                        productView.updateProduct();
                        break;
                    case 4:
                        productView.removeProduct();
                        break;
////                    case 5:
////                        productView.searchByTitle();
//                        break;
                    case 5:
                        productView.sortByPriceByASC();
                        break;
                    case 6:
                        productView.sortByPriceByDESC();
                        break;
                    case 7:
                        System.out.println("Xin vui lòng nhập lại ");
                        AdminViewLaucher.menuAdmin();
                        break;
                    default:
                        System.out.println("Bạn chọn không hợp lệ! Vui long nhap lai.");
                }
            } while (true);
        }
    }
