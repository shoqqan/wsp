package controller;

import service.AuthService;
import view.AuthView;

public class AuthController {
    private final AuthService authService;
    private final AuthView authView;

    public AuthController(AuthService authService, AuthView authView) {
        this.authService = authService;
        this.authView = authView;
    }

    public void handleMenu() {
        authView.showMenu();
        String choice = authView.getInput("Выберите действие");
        switch (choice) {
            case "1":
                handleRegister();
                break;
            case "2":
                handleLogin();
                break;
            default:
                authView.showError("Неверный выбор!");
        }
    }

    private void handleRegister() {
        String username = authView.getInput("Введите имя пользователя");
        String password = authView.getInput("Введите пароль");
        try {
            authService.register(username, password);
            authView.showSuccess("Регистрация выполнена успешно.");
        } catch (Exception e) {
            authView.showError(e.getMessage());
        }
    }

    private void handleLogin() {
        String username = authView.getInput("Введите имя пользователя");
        String password = authView.getInput("Введите пароль");
        try {
            authService.login(username, password);
            authView.showSuccess("Вход выполнен успешно.");
        } catch (Exception e) {
            authView.showError(e.getMessage());
        }
    }
}
