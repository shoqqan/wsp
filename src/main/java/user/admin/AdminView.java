package user.admin;

import user.User;

import java.util.Scanner;

public class AdminView {
    private final AdminController adminController;
    private final Scanner scanner;

    public AdminView(AdminController adminController) {
        this.adminController = adminController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Зарегистрировать пользователя");
            System.out.println("2. Удалить пользователя");
            System.out.println("3. Обновить данные пользователя");
            System.out.println("4. Просмотреть всех пользователей");
            System.out.println("5. Выйти");
            System.out.print("Введите номер действия: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: {
                    adminController.handleRegister();
                    break;
                }
                case 2: {
                    System.out.print("Введите ID пользователя для удаления: ");
                    String userId = scanner.nextLine();
                    try {
                        adminController.deleteUser(userId);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    System.out.print("Введите ID пользователя для обновления: ");
                    String userId = scanner.nextLine();
                    System.out.print("Введите новое имя пользователя: ");
                    String username = scanner.nextLine();
                    System.out.print("Введите новый пароль: ");
                    String password = scanner.nextLine();
                    User updatedUser = new User(username, password);
                    try {
                        adminController.updateUser(userId, updatedUser);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 4: {
                    try {
                        adminController.handleViewAllUsers();
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 5: {
                    System.out.println("Выход из меню администратора.");
                    return;
                }
                default: {
                    System.out.println("Неверный выбор. Повторите попытку.");
                }
            }
        }
    }
}
