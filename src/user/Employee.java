package user;

import database.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Employee extends User {

    public Employee(String username, String password) {
        super(username, password);
        this.setId("EMP-" + System.currentTimeMillis());
    }

    /**
     * Метод для найма нового пользователя (например, Teacher или Employee).
     *
     * @param newUser Пользователь, которого нужно нанять.
     * @throws IOException Если не удалось сохранить данные в базу.
     */
    public void hireUser(User newUser) throws IOException {
        Database db = Database.getInstance();

        if (!db.getHashMap().containsKey("users")) {
            db.getHashMap().put("users", new ArrayList<User>());
        }

        List<User> users = (List<User>) db.getHashMap().get("users");

        users.add(newUser);
        db.getHashMap().put("users", users);

        db.save();

        System.out.println("Новый пользователь успешно добавлен: " + newUser.getUsername());
    }
}
