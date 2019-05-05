package repository.impl;

import entity.User;
import repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Repository<User> {

    private Map<Integer, User> users;

    public UserRepository() {
        users = new HashMap<>();
        usersInit();
    }

    @Override
    public User add(User object) {
        return users.put(object.getId(), object);
    }

    @Override
    public boolean contains(int key) {
        return users.containsKey(key);
    }

    @Override
    public User get(int key) {
        return users.get(key);
    }

    @Override
    public boolean remove(int key) {
        users.remove(key);
        return false;
    }

    @Override
    public Map<Integer, User> getAll() {
        return users;
    }

    private void usersInit() {
        for (int i = 0; i < 10; i++) {
            users.put(i, new User.UserBuilder().setLogin("login" + i)
                                .setFirstName("name" + i)
                                .setLastName("lastName" + i)
                                .setPassword("password" + i)
                                .setEmail("email"+ i + "@mail.com")
                                .build());
        }
    }
}
