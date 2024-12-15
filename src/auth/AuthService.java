package auth;

import database.Database;
import user.Employee;
import user.Student;
import enums.*;
import user.Teacher;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthService {
    private final Database db = Database.getInstance();
    private static AuthService instance;

    private AuthService() {
        // private constructor to prevent instantiation
    }

    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public void register(String username, String password) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип пользователя:");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Employee");
        String choice = scanner.nextLine();

        User newUser;

        switch (choice) {
            case "1":
                System.out.println("Введите тип студента (B/M/D): ");
                String studentTypeInput = scanner.nextLine().toUpperCase();
                StudentType studentType = StudentType.fromCode(studentTypeInput);
                System.out.println("Введите год поступления: ");
                int admissionYear = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите год обучения: ");
                int studyYear = Integer.parseInt(scanner.nextLine());

                newUser = new Student(username, password, studentType, studyYear, admissionYear);
                break;

            case "2":
                System.out.println("Введите ранг учителя (T/L/S/P): ");
                String rankInput = scanner.nextLine().toUpperCase();
                TeacherRank rank = TeacherRank.fromCode(rankInput);

                System.out.println("Введите год начала работы: ");
                int startYear = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите предмет: ");
                String subject = scanner.nextLine();

                newUser = new Teacher(username, password, rank, subject, startYear);
                break;

            case "3":
                newUser = new Employee(username, password);
                break;

            default:
                System.out.println("Неверный выбор! Регистрация отменена.");
                return;
        }

        List<User> users = (List<User>) db.getHashMap().get("users");
        if (users == null) {
            users = new ArrayList<>();
        }

        users.add(newUser);
        db.getHashMap().put("users", users);
        db.save();

        System.out.println("Пользователь успешно зарегистрирован: " + newUser.getUsername());
    }
}
