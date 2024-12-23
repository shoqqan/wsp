package user;

import java.util.Scanner;

public class User {
    private final Scanner scanner = new Scanner(System.in);
    protected Role role;
    private String id;
    private String username;
    private String password;
    private String email;
    private String iin;
    private String firstName;
    private String lastName;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // this.role = UserRole.STUDENT;
    }

    public User(String id, String username, String password, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String id, String username, String password, String iin, String firstName, String lastName, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.iin = iin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }


    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void changePassword() {
        System.out.println("Введите ваш старый пароль: ");
        String oldPassword = scanner.nextLine();
        if (oldPassword.equals(this.password)) {
            System.out.println("Введите новый пароль: ");
            String newPassword = scanner.nextLine();
            this.password = newPassword;
            System.out.println("Пароль изменен");
        } else {
            System.out.println("Неверный пароль!");
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}