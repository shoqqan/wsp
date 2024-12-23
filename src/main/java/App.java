import auth.*;
import database.*;
import news.*;
import user.student.StudentRepository;
import teacher.TeacherRepository;
import transcript.TranscriptController;
import transcript.TranscriptService;
import transcript.TranscriptView;
import user.UserRepository;
import user.student.StudentRepository;
import mainMenu.MainMenuController;
import mainMenu.MainMenuView;
import java.sql.Connection;

import course.*;

public class App {
    public static void main(String[] args) {
        try (Connection connection = Database.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            NewsRepository newsRepository = new NewsRepository(connection);
            StudentRepository studentRepository = new StudentRepository(connection);
            CourseRepository courseRepository = new CourseRepository(connection);
            TeacherRepository teacherRepo = new TeacherRepository(connection);

            AuthService authService = new AuthService(userRepository);
            NewsService newsService = new NewsService(newsRepository);
            TranscriptService transcriptService = new TranscriptService(studentRepository);
            CourseService courseService = new CourseService(courseRepository, teacherRepo);

            AuthView authView = new AuthView();
            NewsView newsView = new NewsView();
            TranscriptView transcriptView = new TranscriptView(transcriptService);
            CourseView courseView = new CourseView();

            AuthController authController = new AuthController(authService, authView);
            NewsController newsController = new NewsController(newsService, newsView);
            TranscriptController transcriptController = new TranscriptController(transcriptService, transcriptView);
            CourseController courseController = new CourseController(courseView, courseService);


            if (authController.handleMenu()) {
                String studentId = authController.getAuthenticatedUserId(); // тут получаем айдишку юзера, чтобы в будущем дёргать из бдшки данные по айдишке, у нас ведь джвтшки нет
                MainMenuView mainMenuView = new MainMenuView(userRepository, studentId);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView, newsController, transcriptController, studentId);
                mainMenuController.showMainMenu();


//                courseController.displayCourses();
//
//                courseController.handleStudentRegistration(studentId);
//
//                transcriptController.showTranscript(studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
