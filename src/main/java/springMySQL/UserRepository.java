package springMySQL;

import org.springframework.data.repository.CrudRepository;
import springMySQL.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    Iterable<User> findByLogin(String name);
    Iterable<User> findByEmail(String email);

}
