package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserAddingDTO dto){
        User user = new User();
        rewriteDataToUser(dto, user);
        userRepository.save(user);
    }

    private void rewriteDataToUser(UserAddingDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }
}
