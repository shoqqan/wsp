package mainMenu;

import news.NewsController;
import transcript.TranscriptController;

import java.sql.SQLException;

public class MainMenuController {
    private final MainMenuView mainMenuView;
    private final NewsController newsController;
    private final TranscriptController transcriptController;
    private final String userId;

    public MainMenuController(MainMenuView mainMenuView, NewsController newsController, TranscriptController transcriptController, String userId) {
        this.mainMenuView = mainMenuView;
        this.newsController = newsController;
        this.transcriptController = transcriptController;
        this.userId = userId;
    }

    public void showMainMenu() throws SQLException {
        int choice = mainMenuView.getUserChoice();
        switch (choice) {
            case (1):
                newsController.showNews();
                break;
            case (2):
                transcriptController.showTranscript(Integer.parseInt(userId));
                break;
            case (3):
                System.out.println("Регистрация на дисциплины");
                break;
            case (4):
                System.out.println("Выход из аккаунта");
                break;
            case (5):
                System.out.println("Админ-панель");
                break;
        }
    }
}
