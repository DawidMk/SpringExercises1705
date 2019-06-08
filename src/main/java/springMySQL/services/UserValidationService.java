package springMySQL.services;

import org.springframework.stereotype.Service;
import springMySQL.AddDTO;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserValidationService {
    public Map<String, String> validate(AddDTO dto) {
        Map<String, String> errorMap = new HashMap<>();
        String name = dto.getLogin();
        String email = dto.getEmail();

        if(name == null || !name.matches("[a-z]{2,}")){
            errorMap.put("login Validation Result","wrong login - at least 3 letters");
        }
        if(email==null || !email.matches("^[\\w\\.]{2,}@([a-z]{2,}\\.){1,2}[a-z]{2,}")){
            errorMap.put("email Validation Result","wrong email format");
        }
        return errorMap;
    }
}
