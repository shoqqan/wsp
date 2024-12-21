package service;

import model.users.User;
import repository.UserRepository;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String username, String password) throws Exception {
        if (userRepository.findByUsername(username) != null) {
            throw new Exception("Пользователь с таким именем уже существует.");
        }
        userRepository.save(new User(username, password));
    }

    public User login(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("Неверный логин или пароль.");
        }
        return user;
    }
}
