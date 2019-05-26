package springMySQL;

public class UserAddService {

    public User createUser(UserAddingDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
