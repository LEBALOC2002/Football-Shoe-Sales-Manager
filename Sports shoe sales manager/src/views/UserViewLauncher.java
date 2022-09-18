package views;

import java.util.Scanner;

import static views.AdminViewLaucher.menuAdmin;

public class UserViewLauncher {

    public static void menuUser() {
        System.out.println("《》《》《》《》《》《》《》USERS MANAGER《》《》《》《》《》《》《》");
        System.out.println(" 》⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌《");
        System.out.println("《》                                                      《");
        System.out.println("《》                1. Order đơn hàng                     《");
        System.out.println("《》                2. Quay lại                           《");
        System.out.println("《》                                                      《");
        System.out.println("《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》《》");
        launch();
    }
    public static void launch() {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        int option = -1;
        do {
            try {
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print("⏹⏹▶ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 2 || option < 1)
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                } while (option > 2 || option < 1);

                switch (option) {
                    case 1:
                       OrderViewLaucher.launch();
                        break;
                    case 2:
                        UserViewLauncher.launch();
                        break;
                    default:
                        System.out.println("Chọn chức năng sai! Vui lòng chọn lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (option != 4);

    }
}