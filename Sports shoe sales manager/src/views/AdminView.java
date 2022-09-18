package views;

import model.Role;
import model.User;
import service.IUserService;
import service.UserService;
import utils.AppUtils;
import utils.ValidateUtils;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

import static views.AdminViewLaucher.menuAdmin;

public class AdminView {
    private static IUserService userService;
    private final Scanner scanner = new Scanner(System.in);
    private static final ProductViewLauncher productViewLauncher = new ProductViewLauncher();

    public AdminView() {
        userService = UserService.getInstance();
    }

    public static void adminMain(Role role) {
        boolean isRetry = Boolean.parseBoolean(null);
        System.out.println("♔♔♔♔♔♔♔♔♔♔♔  ADMIN MANAGER  ♔♔♔♔♔♔♔♔♔♔");
        System.out.println("♔                                                ♔");
        System.out.println("♔                                                ♔");
        System.out.println("♔                 1. ĐĂNG NHẬP ADMIN             ♔");
        System.out.println("♔                 2. ĐĂNG XUẤT                   ♔");
        System.out.println("♔                                                ♔");
        System.out.println("♔                                                ♔");
        System.out.println("♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔");
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Chọn chức năng!");
            System.out.print("⭆ ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    do {
                        System.out.println("Username");
                        String username = AppUtils.retryString("Username");
                        System.out.println("Password");
                        String password = AppUtils.retryString("Password");
                        if (null == userService.adminLogin(username, password)) {
                            System.out.println("Tài khoản không hợp lệ!");
                            adminMain(role);
                            isRetry = true;
                        } else {
                            System.out.println(" Bạn đã đăng nhập thành công!");
                            isRetry = false;
                            menuAdmin();
                        }
                    } while (isRetry);
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println(" Nhập sai! Vui lòng nhập lại!");
                    adminMain(role);
            }
        } while (isRetry);
    }
    public void addUser() {
        do {
            try {
//                    Integer id = inputId(InputOption.ADD);
                long id = System.currentTimeMillis() / 1000;
                String username = inputUserName(InputOption.ADD);
                String password = "";
                do {
                   password=inputPassword();

                }
                while (!ValidateUtils.PasswordValid(password));



                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();
                Instant createAt = Instant.now();
                User user = new User(id, username, password, fullName, phone, email, address, Role.ADMIN, createAt);
                setRole(user);
                userService.add(user);
                System.out.println("Đã thêm thành công!");
                menuAdmin();
            } catch (Exception e) {
                System.out.println("Nhập sai. vui lòng nhập lại!");
            }
        } while (isRetry(InputOption.ADD));
    }

    public void setRole(User user) {

        System.out.println("♔♔♔♔♔♔♔♔♔♔♔♔  SET ROLE  ♔♔♔♔♔♔♔♔♔♔♔");
        System.out.println("♔                                              ♔");
        System.out.println("♔                     1. ADMIN                 ♔");
        System.out.println("♔                     2. USER                  ♔");
        System.out.println("♔                                              ♔");
        System.out.println("♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔");
        System.out.println("Chọn Role: ");
        System.out.print(" ⏹⏹▶ ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                user.setRole(Role.ADMIN);
                break;
            case 2:
                user.setRole(Role.USER);
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại");
                setRole(user);
        }
    }

    public void showUsers(InputOption inputOption) {

        System.out.println("||♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔DANH SÁCH NGƯỜI DÙNG♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔||");
        System.out.println("||⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌||");
        System.out.printf("|%-12s| | %-18s |  | %-20s | %-40s | %-20s |  | %-24s |\n", "ID", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf("|%-12s| | %-18s |  | %-20s | %-40s | %-20s |  | %-24s |\n", user.getId(), user.getFullName(), user.getPhone(), user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("|| ♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔||");
        if (inputOption == InputOption.SHOW) AppUtils.isRetry(InputOption.SHOW);
        menuAdmin();

    }

    public void updateUser() {
        showUsers(InputOption.SHOW);
        boolean isRetry = false;
        do {
            try {
                int id = inputId(InputOption.UPDATE);
                System.out.println("|♔♔♔♔♔♔♔♔♔♔♔  ADMIN MANAGER  ♔♔♔♔♔♔♔♔♔♔♔|");
                System.out.println("||⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌⚌||");
                System.out.println("♔                                                    ♔");
                System.out.println("♔                   1. Đổi tên                       ♔");
                System.out.println("♔                   2. Sửa số điện thoại             ♔");
                System.out.println("♔                   3. Sửa địa chỉ                   ♔");
                System.out.println("♔                   4. Quay lại                      ♔");
                System.out.println("♔                                                    ♔");
                System.out.println("♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔♔");

                int option = AppUtils.retryChoose(1, 4);
                User newUser = userService.findById(id);

                switch (option) {
                    case 1:
                        String name = inputFullName(InputOption.UPDATE);
                        newUser.setFullName(name);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi tên thành công!");
                        updateUser();
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newUser.setPhone(phone);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi số điện thoại thành công!");
                        updateUser();
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi thành công!");
                        updateUser();
                        break;
                }
                isRetry = option != 4 && isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (isRetry);
    }

    private boolean isRetry(InputOption inputOption) {
        do {
            try {
                switch (inputOption) {
                    case ADD:
                        System.out.println("Nhấn 'y' để thêm tiếp người dùng \t|\t 'q' để quay lại \t|\t 't' để thoát");
                        break;
                    case UPDATE:
                        System.out.println("Nhấn 'y' để sửa tiếp \t|\t 'q' để quay lại\t|\t 't' để thoát chương trình");
                        break;
                    case SHOW:
                        System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + inputOption);
                }

                System.out.print("⏹⏹▶ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "q":
                        return false;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
                ex.printStackTrace();
            }
        } while (true);
    }

    private Integer inputId(InputOption option) {
        Integer id = null;
        switch (option) {
            case ADD:
                System.out.println("Nhập ID");
                System.out.print("⏹⏹▶ ");
                while (userService.existById(id = Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("ID này đã tồn tại. Vui lòng nhập id khác!");
                    System.out.print("⏹⏹▶ ");
                }
                break;
            case UPDATE:
                System.out.println("Nhập ID bạn muốn sửa");
                System.out.print("⏹⏹▶ ");
                while (!userService.existById(id = Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Không tìm thấy id! Vui lòng nhập lại");
                    System.out.print("⏹⏹▶ ");
                }
                break;
        }
        return id;
    }

    private String inputUserName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("nhập User bạn muốn ");
                break;
            case UPDATE:
                System.out.println("Nhập User mà bạn muốn đổi");
                break;
        }
        System.out.print("⏹⏹▶ ");
        String userName = scanner.nextLine();
        while (ValidateUtils.UserValid(userName)) {
            System.out.println("User không  hợp lệ! Vui lòng nhập lại ");
            System.out.print("⏹⏹▶ ");
            userName = scanner.nextLine();
        }
        return userName;
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

    public String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số điện thoại (vd: 0796785558): ");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại mà bạn muốn đổi");
                break;
        }
        System.out.print("⏹⏹▶  ");
        String phone = scanner.nextLine();
        while (!ValidateUtils.PhoneValid(phone)) {
            System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
            System.out.println("Nhập số điện thoại (vd: 0796785558");
            System.out.print("⏹⏹▶ ");
            phone = scanner.nextLine();
        }
        while (userService.existsByPhone(phone)) {
            System.out.println("Số này đã tồn tại! Mời bạn nhập lại");
            System.out.print("⏹⏹▶ ");
            phone = scanner.nextLine();
        }
        return phone;
    }

    private String inputEmail() {
        System.out.println("Nhập email (vd: lochuedvfb@gmail.com)");
        System.out.print("⏹⏹▶ ");
        String email = scanner.nextLine();
        while (!ValidateUtils.EmailValid(email)) {
            System.out.println("Email " + email + " của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
            System.out.println("Nhập email (vd: lochuedvfb@gmail.com)");
            System.out.print("⏹⏹▶ ");
            email = scanner.nextLine();
        }
        while (userService.existsByEmail(email)) {
            System.out.println("Email " + email + " của bạn đã tồn tại! vui lòng kiểm tra và nhập lại");
            email = scanner.nextLine();
        }
        return email;
    }

    private String inputPassword() {
        System.out.println("Nhập mật khẩu( mật khẩu phải > 7 kí tự )");
        System.out.print("⏹⏹▶ ");
        String password = scanner.nextLine();
        while (ValidateUtils.PasswordValid(password)) {
            System.out.println("Mật khẩu yếu! Vui lòng nhập lại ");
            System.out.print("⏹⏹▶ ");
            password = scanner.nextLine();
        }
        return password;
    }

    private String inputAddress(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập địa chỉ (vd: Huế)");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ mà bạn muốn đổi");
                break;
        }
        System.out.print("⏹⏹▶ ");
        String address = scanner.nextLine();
        while (ValidateUtils.AddressValid(address)) {
            System.out.println("Bạn nhập địa chỉ sai ! Vui lòng nhập lại ");
            System.out.print("⏹⏹▶ ");
            address = scanner.nextLine();
        }
        return address;
    }
}
