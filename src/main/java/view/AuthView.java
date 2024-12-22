//package auth;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Auth {
//    private final AuthService authService = AuthService.getInstance();
//    private final Scanner scanner = new Scanner(System.in);
//
//    private void login() throws IOException {
//        System.out.println("Выполните вход: ");
//        System.out.print("Введите логин: ");
//        String username = scanner.nextLine();
//        System.out.print("Введите пароль: ");
//        String password = scanner.nextLine();
//        if (authService.login(username, password)) {
//            System.out.println("Вход выполнен успешно!");
//        } else {
//            System.out.println("Неверный логин или пароль!");
//        }
//    }
//
//    private void register() throws IOException {
//        System.out.print("Введите логин: ");
//        String username = scanner.nextLine();
//        System.out.print("Введите пароль: ");
//        String password = scanner.nextLine();
//        authService.register(username, password);
//    }
//
//    public void view() throws IOException {
//        System.out.println("Вы хотите войти или зарегистрироваться?");
//        System.out.println("1. Войти");
//        System.out.println("2. Зарегистрироваться");
//        String choice = scanner.nextLine();
//        switch (choice) {
//            case "1":
//                this.login();
//                break;
//            case "2":
//                this.register();
//                break;
//            default:
//                System.out.println("Неверный ввод!");
//                this.view();
//        }
//    }
//}

package view;

import java.util.Scanner;

public class AuthView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("Добро пожаловать! Введите данные для входа в аккаунт:\n");
    }

    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public void showSuccess(String message) {
        System.out.println("Успех: " + message);
    }

    public void showError(String message) {
        System.out.println("Ошибка: " + message);
    }
}

