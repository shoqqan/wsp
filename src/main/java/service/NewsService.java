package service;

import model.News;
import repository.NewsRepository;

import java.util.List;

public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getNews(int page, int[] regularCountContainer) {
        int offset = (page - 1) * 10;
        return newsRepository.getNews(offset, regularCountContainer);
    }
}
