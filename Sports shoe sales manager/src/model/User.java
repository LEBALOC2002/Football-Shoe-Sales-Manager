package model;

import java.time.Instant;

public class User {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String Phone ;
    private String email;
    private String address;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;

    public User() {
    }

    public User(long id, String username, String password, String fullName, String Phone, String email, String address, Role role , Instant createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.Phone = Phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }



    public static User parseUser(String record) {
        User user = new User();
        String[] field = record.split(",");
        user.id = Long.parseLong(field[0]);
        user.username = field[1];
        user.password = field[2];
        user.fullName = field[3];
        user.Phone = (field[4]);
        user.email = field[5];
        user.address = field[6];
        user.role = Role.parseRole(field[7]);
        user.createdAt = Instant.parse(field[8]);
        String temp = field[9];
        if(temp != null && !temp.equals("null"))
            user.updatedAt = Instant.parse(temp);
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
     return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s",
             id,
             username,
             password,
             fullName,
             Phone,
             email,
             address,
             role,
             createdAt,
             updatedAt);
    }

}
