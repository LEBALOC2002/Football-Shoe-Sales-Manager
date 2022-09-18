package views;

import java.util.Scanner;

public class OrderViewLaucher {
    private static final Scanner scanner = new Scanner(System.in);
    public static final OrderView orderView = new OrderView();

    public static void launch() {
        OrderView orderView = new OrderView();
        boolean isRetry = true;
        do {
           try{
               orderMenu();
               Scanner scanner = new Scanner(System.in);
               System.out.println("Chọn chức năng");
               System.out.print("➾ ");
               int choices = Integer.parseInt(scanner.nextLine());
               switch (choices) {
                   case 1:
                       orderView.addOrder();
                       break;
                   case 2:
                       orderView.showAllOrder();
                       break;
                   case 3:
                       AdminViewLaucher.menuAdmin();
                     return;
                   default:
                       System.out.println("Chọn sai! xin vui lòng nhập lại");
                       break;
               }
           }catch (Exception e){ e.printStackTrace();}

        } while (isRetry);


    }

    public static void orderMenu() {
        System.out.println("‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼ ORDER MENU ‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼");
        System.out.println("‼‼                                      ‼‼");
        System.out.println("‼‼         1. Tạo order                 ‼‼");
        System.out.println("‼‼         2. Xem danh sách order       ‼‼");
        System.out.println("‼‼         3. Trở lại                   ‼‼");
        System.out.println("‼‼                                      ‼‼");
        System.out.println("‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼ ");
    }
}
