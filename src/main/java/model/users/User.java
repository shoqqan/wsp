package model.users;

import model.enums.UserRole;

import java.util.Scanner;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    protected UserRole role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role=UserRole.STUDENT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    private final Scanner scanner = new Scanner(System.in);
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
}
