package views;

import model.Order;
import model.OrderItem;
import model.Product;
import service.*;
import utils.AppUtils;
import utils.CSVUtils;
import utils.ValidateUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class OrderView {
    public final static String pathOrderItem = "C:\\Module2\\Sports shoe sales manager\\src\\data\\oderItem.csv";
    public final static String pathOrder = "C:\\Module2\\Sports shoe sales manager\\src\\data\\orders.csv" ;
    private final IProductService productService;
    private final IOrderService orderService;
    private IItemOrderService itemOrderService;

    private final Scanner scanner = new Scanner(System.in);
    private String size;

    public OrderView() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        itemOrderService = OrderItemService.getInstance();

    }

    public OrderItem addOrderItems(long orderId) {
        OrderItemService.getInstance();
        ProductView productView = new ProductView();
        ProductView.showProduct(InputOption.ADD);
        long id = System.currentTimeMillis() / 1000;
        long productId = inputProductId(InputOption.ADD);
        Product product = productService.findById(productId);

        int oldQuantity = product.getQuantity();
        int quantity;
        do {
            System.out.println("Nhập số lượng sản phẩm bạn muốn mua ");
            System.out.print("⏹⏹▶ ");
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0 || quantity > oldQuantity) {
                System.out.println("Số lượng phải lớn hơn 0 và không quá số lượng đang có!!!");
            }
        } while (quantity <= 0 || quantity > oldQuantity);
        int size;
        do {
            System.out.println("Nhập size bạn muốn mua (Shop chỉ có size 10 đến 45)");
            System.out.print("⏹⏹▶ ");
            size = AppUtils.retryParseInt();
//            if (size <= 10 || size >= 45) {
//                System.out.println("Size trong shop chỉ có 10 đến 45");
//            }

                if(product.getSize() != size){
                    System.out.println("Size không có!!");
                }
        } while (product.getSize() != size);
//        List<Product> productList = ProductService.getInstance().findAll();
//        for ( Product product1 : productList) {
//           int tamp =  product1.getSize();
//           if(tamp == size)
//        }
        Double price = product.getPrice();
        String productName = product.getTitle();
        long total = (long) (quantity * price);
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        List<OrderItem> orderItemList = OrderItemService.getInstance().getOrderItems();
        productService.update(product);
        OrderItem orderItem = new OrderItem(id, price, size, quantity, orderId, productId, productName, total);

        CSVUtils.write(pathOrderItem,orderItemList);
        itemOrderService.add(orderItem);
//        orderItemList.add(orderItem);
   return orderItem;
    }
    private long inputProductId(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập ID sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập ID sản phẩm muốn chỉnh sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập ID sản phẩm muốn xóa: ");
                break;
        }
        long id;
        boolean isTrue = true;
        do {
            id = AppUtils.retryParseLong();
            boolean isFindId = productService.existById(id);
            if (isFindId) {
                isTrue = false;
            } else {
                System.out.println("Không tìm thấy! Vui lòng nhập lại");
            }
        } while (isTrue);
        return id;
    }

    public void showAllOrder() {
        List<Order> orders = orderService.getOrders();
        List<OrderItem> orderItems = OrderItemService.getInstance().getOrderItems();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println(" ⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫   LIST PRODUCT  ⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫ ");
            System.out.println("|                                                                                                                                                                                                         |");
            System.out.printf("%-30s %-20s %-20s %-15s %-60s %-15s %-15s %-21s %-7s\n|", "   Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên Sản Phẩm", "Size", "Số lượng", "   Giá", "   Tổng" + "                                       |");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getIdOder()) {
                        newOrderItem = orderItem;
                        Product product = productService.findById(newOrderItem.getProductId());
                        System.out.printf("%-30s %-20s %-20s %-15s %-60s %-15s %-15s %-21s %-7s\n|",
                                order.getIdOder(),
                                order.getFullName(),
                                order.getMobile(),
                                order.getAddress(),
                                newOrderItem.getProductName(),
                                newOrderItem.getSize(),
                                newOrderItem.getQuantity(),
                                newOrderItem.getPrice(),
                                AppUtils.doubleToVND(newOrderItem.getTotal()));
                    }
                }
            }
            System.out.println("                                                                                                                                                                                                          |");
            System.out.println(" ⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫ LOC SADO SPORT ⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫⚫");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print("⏹⏹▶  ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLaucher.launch();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public void addOrder() {
        try {
//            orderService.getOrders();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên (vd: Le Ba Loc) ");
            System.out.print("⏹⏹▶  ");
            String name = scanner.nextLine();
            while (!ValidateUtils.NameValid(name)) {
                System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên (vd: Le Ba Loc) ");
                System.out.print("⏹⏹▶ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số di động ");
            System.out.print("⏹⏹▶ ");
            String Phone = scanner.nextLine();
            while (!ValidateUtils.PhoneValid(Phone)) {
                System.out.println("Số điện thoại " + Phone + " không đúng " + " xin vui lòng hãy nhập lại" + " số 0 phải viết đầu ");
                System.out.println(" ví dụ : 0796785558");
                System.out.print("⏹⏹▶ ");
                Phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ ");
            System.out.print("⏹⏹▶ ");
            String address = scanner.nextLine();
            while (ValidateUtils.AddressValid(address)) {
                System.out.println("Địa chỉ " + address + " không được để trống! xin vui lòng nhập lại ");
                System.out.println("Ví dụ : Huế");
                System.out.println("Nhập địa chỉ");
                System.out.print("⏹⏹▶ ");
                address = scanner.nextLine();
            }
            Instant createAT = Instant.now();
            List<Order> orderList = OrderService.getInstance().getOrders();
            Order order = new Order(orderId, name, Phone, address, 0, createAT);
            orderList.add(order);
            CSVUtils.write(pathOrder , orderList);
            OrderItem orderItem = addOrderItems(orderId);
            setGrandTotal(orderId);
            System.out.println("Đơn của bạn đã thành công");
            System.out.println("Tạo đơn hàng thành công");
            do {
                System.out.println("『 』 『 』 『 』 『 』 『 』 『 』 『 』 『 』 『 』 『 』 ");
                System.out.println("『 』                                           『 』");
                System.out.println("『 』                                           『 』");
                System.out.println("『 』              Nhấp 1 để in hoá đơn         『 』");
                System.out.println("『 』              Nhấn 2 để trở lại            『 』");
                System.out.println("『 』              Nhấn 3 để thoát              『 』");
                System.out.println("『 』                                           『 』");
                System.out.println("『 』 『 』 『 』 『 』 『 』 『 』 『 』 『 』 『 』 『 』");
                System.out.print("➾ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "2":
                        OrderViewLaucher.launch();
                        break;
                    case "3":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setGrandTotal(long orderId) {
        List<OrderItem> orderItems = itemOrderService.getOrderTtemsByOrderId(orderId);
        double loccoho = 0;
        for (OrderItem orderItem : orderItems) {
            loccoho = loccoho + orderItem.getTotal();
        }
        Order order = orderService.getOrderById(orderId);
        order.setGrandTotal(loccoho);
    }


    public boolean checkQuality(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌HOÁ ĐƠN⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌");
            System.out.printf("|%-15s %-25s %-15s %-15s %-40s %-15s %-15s %-15s \n|",
                    "   Id",
                    "Tên khách hàng",
                    "   SĐT",
                    "Địa chỉ",
                    "Tên sản phẩm",
                    "Số lượng",
                    "Size",
                    "Giá",
                    "Total");
            System.out.printf("%-15s %-25s %-15s %-15s %-40s %-15s %-15s %-15s \n|", order.getIdOder(), order.getFullName(), order.getMobile(),
                    order.getAddress(), orderItem.getProductName(), orderItem.getQuantity(), orderItem.getSize(), AppUtils.doubleToVND(orderItem.getPrice()));
            System.out.printf("⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌                      Tổng tiền: %s ⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌\n", AppUtils.doubleToVND(orderItem.getTotal()));

            System.out.println("⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌ LOC SADO SPORT ⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để tiếp tục chương trình");
                System.out.println("Nhấn    ");
                System.out.print("➾ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLaucher.launch();
                        break;
                    case "t":
                        addOrder();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}