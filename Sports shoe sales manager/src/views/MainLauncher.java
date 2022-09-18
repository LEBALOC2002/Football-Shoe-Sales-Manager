package views;

import model.Role;

import java.util.InputMismatchException;
import java.util.Scanner;


public class MainLauncher {
    private static final AdminViewLaucher adminViewLaucher = new AdminViewLaucher();
    private static final UserViewLauncher userViewLauncher = new UserViewLauncher();
    private static final ProductViewLauncher productViewLauncher = new ProductViewLauncher();

    public static void launch(Role role) {
        UserView userView = new UserView();
        userView.userLogin(Role.USER);
        userLogin();
        AdminView adminView = new AdminView();
        AdminView.adminMain(Role.ADMIN);
        adminLogin();
    }


    public static void userLogin() {
        do {
            MenuUser();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Chọn chức năng ");
                System.out.print("⏹⏹▶ ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        OrderViewLaucher.launch();
                        break;
                    case 2:
                        exitUser();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng nhập lại!");
                        MenuUser();

                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai,vui lòng nhập lại!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static void exitUser() {
        System.exit(2);
    }


    protected static void MenuUser() {
        System.out.println("♔♔♔♔♔♔♔♔♔♔  MAIN MENU  ♔♔♔♔♔♔♔♔♔♔♔");
        System.out.println("♔                                            ♔");
        System.out.println("♔                1. Mua sản phẩm             ♔");
        System.out.println("♔                2. Thoát                    ♔");
        System.out.println("♔                                            ♔");
        System.out.println("♔                                            ♔");
        System.out.println("♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔");

    }

    public static void adminLogin() {
        do {
            mainAdmin();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Chọn chức năng ");
                System.out.print("⏹⏹▶ ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        UserViewLauncher.launch();
                        break;
                    case 2:
                        ProductViewLauncher.launch();
                        break;
                    case 3:
                        OrderViewLaucher.launch();
                        break;
                    case 4:
                        exit();
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng nhập lại!");
                        mainAdmin();

                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai,vui lòng nhập lại!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static void exit() {
        System.exit(4);
    }

    static void mainAdmin() {
        System.out.println("♔♔♔♔♔♔♔♔♔♔♔  MAIN MENU  ♔♔♔♔♔♔♔♔♔♔♔♔");
        System.out.println("♔                                                ♔");
        System.out.println("♔                1. Quản lí người dùng           ♔");
        System.out.println("♔                2. Quản lý sản phẩm             ♔");
        System.out.println("♔                3. Quản lý đơn  hàng            ♔");
        System.out.println("♔                4. Thoát chương trình           ♔");
        System.out.println("♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔");
    }


    public static void orderMenu() {
        System.out.println("《》《》《》《》《》《》  USERS MANAGER  《》《》《》《》《》《》");
        System.out.println("《》                                                   《》");
        System.out.println("《》                1. Thêm người dùng                 《》");
        System.out.println("《》                2. Sửa thông tin người dùng        《》");
        System.out.println("《》                3. Hiện danh sách người dùng       《》");
        System.out.println("《》                4. Quay lại                        《》");
        System.out.println("《》                                                   《》");
        System.out.println("《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》");
    }
}