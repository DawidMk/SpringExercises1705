package springMySQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMySQL.entities.AppUser;
import springMySQL.services.AddService;
import springMySQL.services.UserValidationService;

@Controller
public class MainController {
    @Autowired
    OneRepository oneRepository;
    @Autowired
    AddService addService;
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
                AddService.addHuman(n);
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
                AddService.addHuman(dto);
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
    Iterable<AppUser> getAll() {
        return oneRepository.findAllUsers();
    }

    @RequestMapping(path = "/findByLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Iterable<AppUser> getByLogin(@RequestParam(value = "login") String login) {
        return oneRepository.findByLogin(login);
    }

    @RequestMapping(path = "/findByEmail", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Iterable<AppUser> getByEmail(@RequestParam(value = "email") String email) {
        return oneRepository.findByEmail(email);
    }


}
