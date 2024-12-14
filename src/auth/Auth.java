package auth;

import java.util.Scanner;

public class Auth {
    private final AuthService authService = AuthService.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    private void login() {
        System.out.println("Выполните вход: ");
        System.out.println("Введите логин: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        if (authService.login(username, password)) {
            System.out.println("Вход выполнен успешно!");
        } else {
            System.out.println("Неверный логин или пароль!");
            this.login();
        }
    }

    private void register() {
        System.out.println("Введите логин: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        authService.register(username, password);
        System.out.println("Пользователь зарегистрирован!");
        this.login();
    }

    public void view() {
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