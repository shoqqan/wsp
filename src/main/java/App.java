import auth.AuthController;
import auth.AuthService;
import auth.AuthView;
import database.Database;
import mainMenu.MainMenuController;
import mainMenu.MainMenuView;
import news.NewsController;
import news.NewsRepository;
import news.NewsService;
import news.NewsView;
import transcript.TranscriptController;
import transcript.TranscriptService;
import transcript.TranscriptView;
import user.UserRepository;
import user.student.StudentRepository;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try (Connection connection = Database.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            NewsRepository newsRepository = new NewsRepository(connection);
            StudentRepository studentRepository = new StudentRepository(connection);

            AuthService authService = new AuthService(userRepository);
            NewsService newsService = new NewsService(newsRepository);
            TranscriptService transcriptService = new TranscriptService(studentRepository);

            AuthView authView = new AuthView();
            NewsView newsView = new NewsView();
            TranscriptView transcriptView = new TranscriptView(transcriptService);

            AuthController authController = new AuthController(authService, authView);
            NewsController newsController = new NewsController(newsService, newsView);
            TranscriptController transcriptController = new TranscriptController(transcriptService, transcriptView);


            if (authController.handleMenu()) {
                String studentId = authController.getAuthenticatedUserId(); // тут получаем айдишку юзера, чтобы в будущем дёргать из бдшки данные по айдишке, у нас ведь джвтшки нет
                MainMenuView mainMenuView = new MainMenuView(userRepository, studentId);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView, newsController, transcriptController, studentId);
                mainMenuController.showMainMenu();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
