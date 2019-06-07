package springMySQL.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMySQL.*;
import springMySQL.entities.User;

@Service
public class UserAddService {

    @Autowired
    UserDAO userDAO;
    UserRepository userRepository;

    public void addUser(UserAddingDTO dto) {
     /*   if (userExists(dto)) {
            throw new UserExistsException("user " + dto.getEmail() + " already exists!");
        } else {*/
            User user = new User();
            rewriteDataToUser(dto, user);
            userDAO.addToDb(dto, user);

    }

    private void rewriteDataToUser(UserAddingDTO dto, User user) {
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
    }

    private boolean userExists(UserAddingDTO dto) {
        return userRepository.findByEmail(dto.getEmail()) != null;
    }

}

