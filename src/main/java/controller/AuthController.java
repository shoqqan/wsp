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

    public boolean handleMenu() {
        authView.showMenu();
        return handleLogin();
    }

    private boolean handleLogin() {
        String username = authView.getInput("Введите имя пользователя");
        String password = authView.getInput("Введите пароль");
        try {
            authService.login(username, password);
            authView.showSuccess("Вход выполнен успешно");
            return true;
        } catch (Exception e) {
            authView.showError(e.getMessage());
            return false;
        }
    }
}
