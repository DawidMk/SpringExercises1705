package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMySQL.entities.User;

@Service
public class UserDAO {
    @Autowired
    UserRepository userRepository;

    private void rewriteDataToUser(UserAddingDTO dto, User user) {
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
    }

    public void addToDb(UserAddingDTO dto, User user){
        userRepository.save(user);    }

    public User getFromDbByName(String name){
        //todo implement
        return null;
    }

    public User getFromDbByEmail(String email){
        //todo implement
        return null;
    }
}
