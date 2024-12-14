import database.Database;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello and welcome!");
        Database db = Database.getInstance();
        db.init();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                db.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}
