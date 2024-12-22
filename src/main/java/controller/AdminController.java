package controller;

import model.users.User;
import service.AdminService;
import java.util.List;
import view.AuthView;

public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void addUser(String username, String password) throws Exception {
        adminService.register(username, password);
        System.out.println("Пользователь добавлен");
    }

    public void deleteUser(String userId) throws Exception {
        adminService.deleteUser(userId);
        System.out.println("Пользователь удален");
    }

    public void updateUser(String userId, User updatedUser) throws Exception {
        adminService.updateUser(userId, updatedUser);
        System.out.println("Данные пользователя успешно обновлены");
    }

    // public void viewLogs() {
    //     List<String> logs = adminService.viewLogs();
    //     logs.forEach(System.out::println);
    // }

    public void handleRegister() {
        AuthView authView = new AuthView();
        String username = authView.getInput("Введите имя пользователя");
        String password = authView.getInput("Введите пароль");
        try {
            addUser(username, password);
            authView.showSuccess("Регистрация выполнена успешно.");
        } catch (Exception e) {
            authView.showError(e.getMessage());
        }
    }

    public void handleViewAllUsers() {
        List<User> users = adminService.getAllUsers();
        System.out.println("Список всех пользователей:");
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Имя: " + user.getUsername());
        }
    }

}
