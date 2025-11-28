package co.istad.itpmongodb.service;

import co.istad.itpmongodb.domain.User;
import co.istad.itpmongodb.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();
    UserResponse findById(String id);
    User createUser(User user);
//    User updateUser(String id , User user);
    void deleteUserById(String id);
    List<User> searchUserByName(String name);
    List<User> findByName(String name);
    List<User> filterUserByName(String name);

}
