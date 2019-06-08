package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMySQL.entities.BaseEntity;
import springMySQL.entities.User;

@Service
public class OneDAO {
    @Autowired
    UserRepository userRepository;

    public void addToDb(AddDTO dto, BaseEntity o) {
        userRepository.save(o);
    }

    public User getFromDbByName(String name) {
        //todo implement
        return null;
    }

    public User getFromDbByEmail(String email) {
        //todo implement
        return null;
    }
}
