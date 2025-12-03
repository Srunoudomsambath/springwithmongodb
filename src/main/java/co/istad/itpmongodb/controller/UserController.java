package co.istad.itpmongodb.controller;


import co.istad.itpmongodb.domain.User;
import co.istad.itpmongodb.dto.FilterDto;
import co.istad.itpmongodb.dto.UserResponse;
import co.istad.itpmongodb.repository.UserRepository;
import co.istad.itpmongodb.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // allow your Next.js frontend
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @PostMapping("/filter")
    public Page<UserResponse> filterUsers(
            @RequestBody FilterDto filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ){
        return userService.filterUsers(filter,page,size);

    }

    @GetMapping
    public List<UserResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    )  {
        return userService.findAll(page,size,sort);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/name-only")
    public List<UserResponse> findByName(@RequestParam String name){
        return userService.findByName(name);
    }

    @GetMapping("/filter")
    public List<UserResponse> filterUserByName(@RequestParam String name){
        return userService.filterUserByName(name);
    }

    @GetMapping("/search")
    public List<UserResponse> searchByName(@RequestParam String name){
        return userService.searchUserByName(name);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUserById(id);
    }
}
