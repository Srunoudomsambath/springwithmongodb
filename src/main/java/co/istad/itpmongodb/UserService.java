package co.istad.itpmongodb;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(String id);
    User createUser(User user);
    User updateUser(String id , User user);
    void deleteUserById(String id);
    List<User> searchUserByName(String name);
    List<User> findByName(String name);
    List<User> filterUserByName(String name);

}
