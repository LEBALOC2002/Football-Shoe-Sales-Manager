package views;


import java.util.Scanner;

public class AdminViewLaucher {
    public static void menuAdmin() {
        System.out.println("《》《》《》《》《》《》  USERS MANAGER  《》《》《》《》《》《》");
        System.out.println("《》                                                   《》");
        System.out.println("《》                1. Thêm người dùng                 《》");
        System.out.println("《》                2. Sửa thông tin người dùng        《》");
        System.out.println("《》                3. Hiện danh sách người dùng       《》");
        System.out.println("《》                4. Quản lí sản phẩm                《》");
        System.out.println("《》                5. Quản lí đơn hàng                《》");
        System.out.println("《》                                                   《》");
        System.out.println("《》                                                   《》");
        System.out.println("《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》");
        launch();

    }
    public static void launch() {
        Scanner scanner = new Scanner(System.in);
        AdminView adminView = new AdminView();
        int option = -1;
        do {
            try {
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print("⏹⏹▶ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 6 || option < 1)
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                } while (option > 6 || option < 1);

                switch (option) {
                    case 1:
                        adminView.addUser();
                        break;
                    case 2:
                        adminView.updateUser();
                        break;
                    case 3:
                        adminView.showUsers(InputOption.UPDATE);
                        break;
                    case 4:
                        ProductViewLauncher.launch();
                        break;
                    case 5:
                        OrderViewLaucher.launch();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (option != 6);

    }
}
