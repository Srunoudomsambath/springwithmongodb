package co.istad.itpmongodb.service;


import co.istad.itpmongodb.domain.User;
import co.istad.itpmongodb.dto.UserResponse;
import co.istad.itpmongodb.mapper.UserMapper;
import co.istad.itpmongodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll(int page, int size, String[] sort) {
        // handle sorting
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort sortOrder = Sort.by(direction, sort[0]);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort[0]));
        return userRepository.findAll(pageable)
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse findById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User with id " + id + " not found"));
    }

    @Override
    public UserResponse createUser(User user) {
        User saved = userRepository.save(user);
        return userMapper.toUserResponse(saved);
    }

    @Override
    public UserResponse updateUser(String id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User with id " + id + " not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());

        User updated = userRepository.save(existingUser);
        return userMapper.toUserResponse(updated);
    }

    @Override
    public void deleteUserById(String id) {
        boolean exists = userRepository.existsById(id);
        if (!exists)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User with id " + id + " not found");

        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> searchUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findByName(String name) {
        return userRepository.findByName(name)
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public List<UserResponse> filterUserByName(String name) {
        return userRepository.filterNameByTitle(name)
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public List<UserResponse> filterByAge(String city, Integer age) {
        return userRepository.filterByCity(city,age)
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
}
