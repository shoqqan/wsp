package controller;

import model.News;
import service.NewsService;
import view.NewsView;

import java.util.List;

public class NewsController {
    private final NewsService newsService;
    private final NewsView newsView;

    public NewsController(NewsService newsService, NewsView newsView) {
        this.newsService = newsService;
        this.newsView = newsView;
    }

    public void showNews() {
        int currentPage = 1;

        while (true) {
            int[] regularCountContainer = {-1};
            List<News> newsList = newsService.getNews(currentPage, regularCountContainer);

            if (regularCountContainer[0] == 0) {
                System.out.println("Новостей больше нет.");
                break;
            }

            boolean hasRegularNews = newsList.stream().anyMatch(news -> !news.isPinned());
            if (!hasRegularNews) {
                System.out.println("Новостей больше нет.");
                break;
            }

            newsView.displayNews(newsList, currentPage);

            int choice = newsView.getUserChoice();

            if (choice == 1) {
                currentPage++;
            } else if (choice == 2 && currentPage > 1) {
                currentPage--;
            } else if (choice == 3) {
                break;
            }
        }
    }
}
