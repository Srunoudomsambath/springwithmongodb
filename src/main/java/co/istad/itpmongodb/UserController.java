package co.istad.itpmongodb;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // allow your Next.js frontend
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    @GetMapping
    public List<User> findAll()  {
        List<User> users = userRepository.findAll();
//        for (User u : users) {
//            Thread.sleep(1000); // 1 second per user
//        }
        return users;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userServiceImpl.findById(id);
    }
    @GetMapping("/name-only")
    public List<User> findByName(@RequestParam String name){
        return userServiceImpl.findByName(name);
    }

    @GetMapping("/filter")
    public List<User> filterUserByName(@RequestParam String name){
        return userServiceImpl.filterUserByName(name);
    }

    @GetMapping("/search")
    public List<User> searchByName(@RequestParam String name){
        return userServiceImpl.searchUserByName(name);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userServiceImpl.createUser(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user){
        return userServiceImpl.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userServiceImpl.deleteUserById(id);
    }
}
