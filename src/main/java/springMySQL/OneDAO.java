package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMySQL.entities.AppUser;
import springMySQL.entities.BaseEntity;

@Service
public class OneDAO {
    @Autowired
    OneRepository oneRepository;

    public void addToDb(AddDTO dto, BaseEntity o) {
        oneRepository.save(o);
    }

    public AppUser getFromDbByName(String name) {
        //todo implement
        return null;
    }

    public AppUser getFromDbByEmail(String email) {
        //todo implement
        return null;
    }
}
