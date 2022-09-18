import model.Role;
import views.UserView;

public class MainUser {
    public static void main(String[] args) {
        UserView userView = new UserView() ;
        userView.userLogin(Role.ADMIN);
    }
}