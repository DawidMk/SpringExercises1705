package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMySQL.entities.BaseEntity;
import springMySQL.entities.User;

@Service
public class OneDAO {
    @Autowired
    OneRepository oneRepository;

    public void addToDb(AddDTO dto, BaseEntity o) {
        oneRepository.save(o);
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
