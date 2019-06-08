package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMySQL.entities.User;
import springMySQL.services.addService;
import springMySQL.services.UserValidationService;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    addService addService;
    @Autowired
    OneDAO oneDAO;
    @Autowired
    UserValidationService userValidationService;

    /*
            @GetMapping(path = "/submit")
            public @ResponseBody
            String addNewUser(@RequestParam String login,
                              @RequestParam String email) {

                AddDTO n = new AddDTO();
                n.setLogin(login);
                n.setEmail(email);
                addService.addHuman(n);
                return "Saved";
            }
    */


    @GetMapping(path = "/submit")
//    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("form", new AddDTO());
        return "index.html";
    }

    //    @PostMapping(path = "/submit")
    @RequestMapping(value = "/submit", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(AddDTO dto, Model model) {
//        Map<String, String> errorMap = userValidationService.validate(dto);
        model.addAttribute("form", dto);
        addService.addHuman(dto);
/*//todo make this work
        if (errorMap.isEmpty()) {
            try {
                addService.addHuman(dto);
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
    Iterable<Object> getAllUsers() {
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
