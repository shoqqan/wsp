//import auth.Auth;
//
//import java.io.IOException;
//
//public class App {
//    public static void main(String[] args) {
//        System.out.println("Добро пожаловать в WSP!");
//
//        Auth auth = new Auth();
//        try {
//            auth.view();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


import database.Database;
import controller.AuthController;
import repository.UserRepository;
import service.AuthService;
import view.AuthView;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try (Connection connection = Database.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            AuthService authService = new AuthService(userRepository);
            AuthView authView = new AuthView();
            AuthController authController = new AuthController(authService, authView);

            authController.handleMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
