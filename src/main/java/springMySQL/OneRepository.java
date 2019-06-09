package springMySQL;

import org.springframework.data.repository.CrudRepository;
import springMySQL.entities.BaseEntity;
import springMySQL.entities.User;

public interface OneRepository<T extends BaseEntity, Integer> extends CrudRepository<T , Integer> {

    Iterable<User> findByLogin(String name);
    Iterable<User> findByEmail(String email);

}
