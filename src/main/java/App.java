import auth.*;
import database.*;
import news.*;
import student.StudentRepository;
import transcript.TranscriptController;
import transcript.TranscriptService;
import transcript.TranscriptView;
import user.*;


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
                int studentId = authController.getAuthenticatedUserId(); // тут получаем айдишку юзера, чтобы в будущем дёргать из бдшки данные по айдишке, у нас ведь джвтшки нет
//                newsController.showNews();
                transcriptController.showTranscript(studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
