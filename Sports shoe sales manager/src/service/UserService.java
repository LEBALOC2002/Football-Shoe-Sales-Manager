package service;

import model.Role;
import model.User;
import utils.CSVUtils;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public final static String path = "C:\\Module2\\Sports shoe sales manager\\src\\data\\users.csv";
    private static UserService instance;

    private UserService() {
    }


    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User adminLogin(String admin, String password) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getUsername().equals(admin) && user.getPassword().equals(password) && user.getRole().equals(Role.ADMIN)) {
                return user;
            }
        }
        return null;
    }
    public User userLogin(String user, String password) {
        List<User> users = getUsers();
        for (User userL : users) {
            if (userL.getUsername().equals(user) && userL.getPassword().equals(password) && userL.getRole().equals(Role.USER)) {
                return userL;
            }
        }
        return null;
    }




    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public void add(User newUser) {
        List<User> users = getUsers();
        users.add(newUser);
        CSVUtils.write(path, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId() == newUser.getId()) {
                String name = user.getFullName();
                if (name != null && !name.isEmpty())
                    user.setFullName(newUser.getFullName());
                String phone = newUser.getPhone();
                if (phone != null && !phone.isEmpty())
                    user.setPhone(newUser.getPhone());
                String address = newUser.getAddress();
                if (address != null && !address.isEmpty())
                    user.setAddress(newUser.getAddress());
                CSVUtils.write(path, users);
                break;
            }
        }
    }

    @Override
    public boolean existById(int id) {
        return findById(id) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean existsByUserName(String userName) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public User findById(int id) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }
}
