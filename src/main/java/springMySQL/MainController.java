package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMySQL.entities.User;
import springMySQL.services.UserAddService;
import springMySQL.services.UserValidationService;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAddService userAddService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserValidationService userValidationService;

    /*
            @GetMapping(path = "/submit")
            public @ResponseBody
            String addNewUser(@RequestParam String login,
                              @RequestParam String email) {

                UserAddingDTO n = new UserAddingDTO();
                n.setLogin(login);
                n.setEmail(email);
                userAddService.addUser(n);
                return "Saved";
            }
    */


    @GetMapping(path = "/submit")
//    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("form", new UserAddingDTO());
        return "index.html";
    }

    //    @PostMapping(path = "/submit")
    @RequestMapping(value = "/submit", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(UserAddingDTO dto, Model model) {
        Map<String, String> errorMap = userValidationService.validate(dto);
        model.addAttribute("form", dto);
        userAddService.addUser(dto);
/*//todo make this work
        if (errorMap.isEmpty()) {
            try {
                userAddService.addUser(dto);
            } catch (UserExistsException e) {
                model.addAttribute("userExistsExceptionMessage", e.getMessage());
                return e.getMessage();
            }
        } else {
            model.addAllAttributes(errorMap);
            return "redirect:/fail.html";
        }*/
        return "redirect:/success.html";
//        return "success";
    }


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/findByLogin")
    public @ResponseBody
    Iterable<User> getByName(@RequestParam String name) {
        return userRepository.findByLogin(name);
    }

    @GetMapping(path = "/findByEmail")
    public @ResponseBody
    Iterable<User> getByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }


}
