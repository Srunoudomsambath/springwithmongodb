package co.istad.itpmongodb.service;


import co.istad.itpmongodb.domain.User;
import co.istad.itpmongodb.dto.UserResponse;
import co.istad.itpmongodb.mapper.UserMapper;
import co.istad.itpmongodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        // we use dto of user response so we need to map dto to the domain
        return users.stream()
                .map(userMapper :: toUserResponse)
                .toList();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        User existingUser = findById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);

    }


    @Override
    public List<User> searchUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> filterUserByName(String name) {
        return userRepository.filterNameByTitle(name);
    }


}
