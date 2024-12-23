import auth.AuthController;
import auth.AuthService;
import auth.AuthView;
import course.CourseController;
import course.CourseRepository;
import course.CourseService;
import course.CourseView;
import database.Database;
import mainMenu.MainMenuController;
import mainMenu.MainMenuView;
import news.NewsController;
import news.NewsRepository;
import news.NewsService;
import news.NewsView;
import teacher.TeacherRepository;
import transcript.TranscriptController;
import transcript.TranscriptService;
import transcript.TranscriptView;
import user.UserRepository;
import user.admin.AdminController;
import user.admin.AdminService;
import user.admin.AdminView;
import user.student.StudentRepository;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try (Connection connection = Database.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            StudentRepository studentRepository = new StudentRepository(connection);
            TeacherRepository teacherRepo = new TeacherRepository(connection);

            AuthService authService = new AuthService(userRepository);
            AuthView authView = new AuthView();
            AuthController authController = new AuthController(authService, authView);

            NewsRepository newsRepository = new NewsRepository(connection);
            NewsService newsService = new NewsService(newsRepository);
            NewsView newsView = new NewsView();
            NewsController newsController = new NewsController(newsService, newsView);

            TranscriptService transcriptService = new TranscriptService(studentRepository);
            TranscriptView transcriptView = new TranscriptView(transcriptService);
            TranscriptController transcriptController = new TranscriptController(transcriptService, transcriptView);

            CourseRepository courseRepository = new CourseRepository(connection);
            CourseService courseService = new CourseService(courseRepository, teacherRepo);
            CourseView courseView = new CourseView();
            CourseController courseController = new CourseController(courseView, courseService);

            AdminService adminService = new AdminService(userRepository);
            AdminController adminController = new AdminController(adminService);
            AdminView adminView = new AdminView(adminController);
            if (authController.handleMenu()) {
                String studentId = authController.getAuthenticatedUserId(); // тут получаем айдишку юзера, чтобы в будущем дёргать из бдшки данные по айдишке, у нас ведь джвтшки нет
                MainMenuView mainMenuView = new MainMenuView(userRepository, studentId);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView, newsController, transcriptController, courseController, adminView, studentId);
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
