package admin;

import user.User;
import user.UserRepository;

import java.util.List;

public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String username, String password) throws Exception {
        if (userRepository.findByUsername(username) != null) {
            throw new Exception("Пользователь с таким именем уже существует.");
        }
        userRepository.save(new User(username, password));
    }

    public void deleteUser(String userId) throws Exception {
        if (userRepository.findById(userId) != null) {
            throw new Exception("Пользователя с таким ID нету");
        }
        userRepository.deleteById(userId);
    }

    public void updateUser(String userId, User updatedUser) throws Exception {
        if (userRepository.findById(userId) != null) {
            throw new Exception("Пользователя с таким ID нету");
        }
        userRepository.update(updatedUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // public List<String> viewLogs() {
    // // TODO: Needs to be done
    // // TODO: Its not hard at all. I'm just lazy bum
    // }
}
