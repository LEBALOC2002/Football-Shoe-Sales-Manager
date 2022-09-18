import model.Role;
import views.AdminView;
public class MainAdmin {
    public static void main(String[] args) {
        AdminView adminView = new AdminView() ;
        adminView.adminMain(Role.ADMIN);
    }
}