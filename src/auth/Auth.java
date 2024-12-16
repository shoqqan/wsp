package auth;

import java.io.IOException;
import java.util.Scanner;
import auth.*;

public class Auth {
    private final AuthService authService = AuthService.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    private void login() throws IOException {
        System.out.println("Выполните вход: ");
        System.out.println("Введите логин: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        if (authService.login(username, password)) {
            System.out.println("Вход выполнен успешно!");
        } else {
            System.out.println("Неверный логин или пароль!");
        }
    }

    private void register() throws IOException {
        System.out.println("Введите логин: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        authService.register(username, password);
    }

    public void view() throws IOException {
        System.out.println("Вы хотите войти или зарегистрироваться?");
        System.out.println("1. Войти");
        System.out.println("2. Зарегистрироваться");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                this.login();
                break;
            case "2":
                this.register();
                break;
            default:
                System.out.println("Неверный ввод!");
                this.view();
        }
    }
}
