package co.istad.itpmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    //derived query
    List<User> findByNameContainingIgnoreCase(String name);

    @Query("{name:  {$regex: ?0, $options:  'i'}}")
    List<User> filterNameByTitle(String name);

    @Query(value = "{name: {$eq: ?0}}",fields = "{name: 1}")
    List<User> findByName(String name);
}
