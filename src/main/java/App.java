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
//import controller.MainMenuController;
import controller.NewsController;
import repository.UserRepository;
import repository.NewsRepository;
import service.AuthService;
import service.NewsService;
import view.AuthView;
//import view.MainMenuView;
import view.NewsView;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try (Connection connection = Database.getConnection()) {
            // Репозитории
            UserRepository userRepository = new UserRepository(connection);
            NewsRepository newsRepository = new NewsRepository(connection);

            // Сервисы
            AuthService authService = new AuthService(userRepository);
            NewsService newsService = new NewsService(newsRepository);

            // Вьюшки
            AuthView authView = new AuthView();
            NewsView newsView = new NewsView();
//            MainMenuView mainMenuView = new MainMenuView();

            // Контроллеры
            AuthController authController = new AuthController(authService, authView);
            NewsController newsController = new NewsController(newsService, newsView);
//            MainMenuController mainMenuController = new MainMenuController(mainMenuView);

            // Основной поток программы
            if (authController.handleMenu()) {
                newsController.showNews();
//                mainMenuController.handleMainMenu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
