package mainMenu;

import course.CourseController;
import news.NewsController;
import transcript.TranscriptController;
import user.admin.AdminView;

import java.sql.SQLException;

public class MainMenuController {
    private final MainMenuView mainMenuView;
    private final NewsController newsController;
    private final TranscriptController transcriptController;
    private final CourseController courseController;
    private final AdminView adminView;
    private final String userId;

    public MainMenuController(MainMenuView mainMenuView, NewsController newsController, TranscriptController transcriptController, CourseController courseController, AdminView adminView, String userId) {
        this.mainMenuView = mainMenuView;
        this.newsController = newsController;
        this.transcriptController = transcriptController;
        this.courseController = courseController;
        this.adminView = adminView;
        this.userId = userId;
    }

    public void showMainMenu() throws SQLException {
        mainMenuView.displayMenu();
        int choice = mainMenuView.getUserChoice();
        switch (choice) {
            case (1):
                newsController.showNews();
                break;
            case (2):
                transcriptController.showTranscript(userId);
                break;
            case (3):
                courseController.handleStudentRegistration(userId);
                break;
            case (4):
                System.out.println("Выход из аккаунта");
                break;
            case (5):
                adminView.showMenu();
                break;
        }
    }
}
