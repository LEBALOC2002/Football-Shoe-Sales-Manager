package service;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User adminLogin(String admin, String password);
    User userLogin (String user , String password);
    List<User> getUsers();

    void add(User newUser);
    void update(User newUser);
    boolean existById(int id);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByUserName(String userName);
    User findById(int id);
}
