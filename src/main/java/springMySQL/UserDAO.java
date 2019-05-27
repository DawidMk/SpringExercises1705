package springMySQL;

import org.springframework.stereotype.Service;

@Service
public class UserDAO {
    private void rewriteDataToUser(UserAddingDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }

    public void addToDb(UserAddingDTO dto){
        //todo implement
    }

    public User getFromDbByName(String name){
        //todo implement
        return null;
    }

    public User getFromDbByEmail(String email){
        //todo implement
        return null;
    }
}
