package mainMenu;

import user.Role;
import user.User;
import user.UserRepository;

import java.util.Optional;
import java.util.Scanner;

public class MainMenuView {
    private final Scanner scanner = new Scanner(System.in);
    private final UserRepository userRepository;
    private final String userId;

    public MainMenuView(UserRepository userRepository, String userId) {
        this.userRepository = userRepository;
        this.userId = userId;
    }

    public void displayMenu() {
        System.out.println("=== Главное меню ===");
        System.out.println("1. Просмотреть новости");
        System.out.println("2. Просмотреть Транскрипт");
        System.out.println("3. Регистрация на дисциплины");
        System.out.println("4. Выход из аккаунта");
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && user.get().getRole() == Role.ADMIN) {
            System.out.println("5. Админ-панель");
        }
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
