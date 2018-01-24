package by.matrosov.usermanagment.controller;

import by.matrosov.usermanagment.model.User;
import by.matrosov.usermanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
public class SimpleController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/access-denied", method = RequestMethod.GET)
    public ModelAndView accessDenied(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/access-denied");
        return modelAndView;
    }

    @RequestMapping(value="/users/home", method = RequestMethod.GET)
    public ModelAndView userHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users/home");
        return modelAndView;
    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView adminHome(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.getByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/home");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin/home");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/all", method = RequestMethod.GET)
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> list = userService.getAllUsers();
        modelAndView.addObject("all", list);
        modelAndView.setViewName("admin/all");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getuserbyid", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(id);
        modelAndView.addObject("id", user);
        modelAndView.setViewName("admin/ID");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getuserbyfirstname", method = RequestMethod.GET)
    public ModelAndView getUserByFirstname(@RequestParam("name1") String name){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getByFirstname(name);
        modelAndView.addObject("name1", user);
        modelAndView.setViewName("admin/firstname");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getuserbylastname", method = RequestMethod.GET)
    public ModelAndView getUserByLastname(@RequestParam("name2") String name){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getByLastname(name);
        modelAndView.addObject("name2", user);
        modelAndView.setViewName("admin/lastname");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getbybirthday", method = RequestMethod.GET)
    public ModelAndView getUserByBirthday(@RequestParam("date") Date date){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getByBirthday(date);
        modelAndView.addObject("date", user);
        modelAndView.setViewName("admin/date");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getbyemail", method = RequestMethod.GET)
    public ModelAndView getUserByBirthday(@RequestParam("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getByEmail(email);
        modelAndView.addObject("email", user);
        modelAndView.setViewName("admin/email");
        return modelAndView;
    }


}
