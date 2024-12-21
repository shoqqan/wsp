package view;

import model.News;

import java.util.List;
import java.util.Scanner;

public class NewsView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayNews(List<News> newsList, int currentPage) {
        System.out.println("=== Новости (Страница " + currentPage + ") ===");

        for (News news : newsList) {
            System.out.println(news);
        }

        if (currentPage > 1) {
            System.out.println("\n1. Следующая страница");
            System.out.println("2. Предыдущая страница");
        } else {
            System.out.println("\n1. Следующая страница");
        }

        System.out.println("3. Выход");
    }

    public int getUserChoice() {
        System.out.print("Введите ваш выбор: ");
        return scanner.nextInt();
    }
}
