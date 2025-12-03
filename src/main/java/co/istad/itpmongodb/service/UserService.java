package co.istad.itpmongodb.service;

import co.istad.itpmongodb.domain.User;
import co.istad.itpmongodb.dto.FilterDto;
import co.istad.itpmongodb.dto.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {


    Page<UserResponse> filterUsers(FilterDto filter,int page,int size);
    List<UserResponse> findAll(int page, int size, String[] sort);
    UserResponse findById(String id);
    UserResponse createUser(User user);
    UserResponse updateUser(String id, User user);
    void deleteUserById(String id);
    List<UserResponse> searchUserByName(String name);
    List<UserResponse> findByName(String name);
    List<UserResponse> filterUserByName(String name);
    List<UserResponse> filterByAge(String city, Integer age);
}
