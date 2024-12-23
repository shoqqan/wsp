package auth;
import user.User;


public class AuthController {
    private final AuthService authService;
    private final AuthView authView;
    private String authenticatedUserId;


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
            User user = authService.login(username, password);
            authenticatedUserId = user.getId();
            authView.showSuccess("Вход выполнен успешно.");
            return true;
        } catch (Exception e) {
            authView.showError(e.getMessage());
            return false;
        }
    }
    public String getAuthenticatedUserId() {
        return authenticatedUserId;
    }
}
