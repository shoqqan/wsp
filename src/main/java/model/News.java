package model;

import java.time.LocalDateTime;

public class News {
    private Long id;
    private String title;
    private String content;
    private boolean isPinned;
    private LocalDateTime createdAt;

    public News(Long id, String title, String content, boolean isPinned, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPinned = isPinned;
        this.createdAt = createdAt;
    }

    // суда геттеров и сеттеров пж

    @Override
    public String toString() {
        return String.format("[%s] %s: %s", createdAt, title, content);
    }

    public boolean isPinned() {
        return isPinned;
    }
}
