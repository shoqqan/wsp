package news;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    private final Connection connection;

    public NewsRepository(Connection connection) {
        this.connection = connection;
    }

    public List<News> getNews(int offset, int[] regularCountContainer) {
        List<News> newsList = new ArrayList<>();
        String sql = """
                        WITH pinned_news AS (
                            SELECT id, title, content, is_pinned, created_at
                            FROM news
                            WHERE is_pinned = true
                            ORDER BY created_at DESC
                            LIMIT 5
                        ),
                        regular_news AS (
                            SELECT id, title, content, is_pinned, created_at
                            FROM news
                            WHERE is_pinned = false
                            ORDER BY created_at DESC
                            LIMIT 10 OFFSET ?
                        ),
                        regular_count AS (
                            SELECT COUNT(*) AS total_regular_count
                            FROM news
                            WHERE is_pinned = false
                        )
                        SELECT *,
                               (SELECT total_regular_count FROM regular_count) AS regular_count
                        FROM (
                            SELECT * FROM pinned_news
                            UNION ALL
                            SELECT * FROM regular_news
                        ) combined_news
                        ORDER BY is_pinned DESC, created_at DESC;
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, offset);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                if (regularCountContainer[0] == -1) {
                    regularCountContainer[0] = rs.getInt("regular_count");
                }
                newsList.add(new News(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getBoolean("is_pinned"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }

            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
