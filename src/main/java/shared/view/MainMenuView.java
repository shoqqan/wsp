package shared.view;

import java.util.Scanner;

public class MainMenuView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("=== Главное меню ===");
        System.out.println("1. Просмотреть новости");
        System.out.println("2. Просмотреть Транскрипт");
        System.out.println("3. Регистрация на дисциплины");
        System.out.println("4. Выход из аккаунта");
        System.out.println("=====================");
    }

    public int getUserChoice() {
        System.out.print("Введите ваш выбор: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите число.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
