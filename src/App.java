import auth.Auth;
import database.Database;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Добро пожаловать в WSP!");
        Database db = Database.getInstance();
        db.init();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                db.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        Auth auth = new Auth();
        auth.view();

    }
}
