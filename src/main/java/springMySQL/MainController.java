package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAddService userAddService;
    @Autowired
    UserDAO userDAO;

    /*
            @GetMapping(path = "/submit")
            public @ResponseBody
            String addNewUser(@RequestParam String name,
                              @RequestParam String email) {

                UserAddingDTO n = new UserAddingDTO();
                n.setName(name);
                n.setEmail(email);
                userAddService.addUser(n);
                return "Saved";
            }
    */
//    @GetMapping(path = "/submit")
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("form", new UserAddingDTO());
        return "index";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String add(UserAddingDTO dto, Model model) {
        model.addAttribute("form", dto);
        userAddService.addUser(dto);
        return "success";
    }


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
