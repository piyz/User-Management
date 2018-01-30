package by.matrosov.usermanagment.controller;

import by.matrosov.usermanagment.model.User;
import by.matrosov.usermanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView userHome(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> list = userService.getAllByActive(1);
        modelAndView.addObject("listUsers", list);
        modelAndView.setViewName("home");
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
        List<User> list = userService.getAllUsers();
        modelAndView.addObject("listUsers", list);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin");
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
            modelAndView.setViewName("admin");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getuserbyid", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(id);
        modelAndView.addObject("id", user);
        modelAndView.setViewName("id");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getuserbyfirstname", method = RequestMethod.GET)
    public ModelAndView getUserByFirstname(@RequestParam("name1") String name){
        ModelAndView modelAndView = new ModelAndView();
        List<User> user = userService.getByFirstname(name);
        modelAndView.addObject("name1", user);
        modelAndView.setViewName("admin/firstname");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getuserbylastname", method = RequestMethod.GET)
    public ModelAndView getUserByLastname(@RequestParam("name2") String name){
        ModelAndView modelAndView = new ModelAndView();
        List<User> user = userService.getByLastname(name);
        modelAndView.addObject("name2", user);
        modelAndView.setViewName("admin/lastname");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getbybirthday", method = RequestMethod.GET)
    public ModelAndView getUserByBirthday(@RequestParam("date") Date date){
        ModelAndView modelAndView = new ModelAndView();
        List<User> user = userService.getByBirthday(date);
        modelAndView.addObject("date", user);
        modelAndView.setViewName("admin/date");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/getbyemail", method = RequestMethod.GET)
    public ModelAndView getUserByEmail(@RequestParam("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getByEmail(email);
        modelAndView.addObject("email", user);
        modelAndView.setViewName("admin/email");
        return modelAndView;
    }

    //shit code but it work
    @RequestMapping(value = "/admin/home/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("id") long id, @Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExist = userService.getById(id);
        if (userExist != null){

            //need to check active or non-active user
            if (userExist.getActive() == 1){
                userExist.setFirstname(user.getFirstname());
                userExist.setLastname(user.getLastname());
                userExist.setUsername(user.getUsername());
                userExist.setEmail(user.getEmail());
                userExist.setBirthday(user.getBirthday());
                userExist.setZip(user.getZip());
                userExist.setCountry(user.getCountry());
                userExist.setCity(user.getCity());
                userExist.setDistrict(user.getDistrict());
                userExist.setStreet(user.getStreet());
                if (bindingResult.hasErrors()) {
                    modelAndView.setViewName("update");
                }else{
                    userService.updateUser(userExist);
                    modelAndView.addObject("successMessage", "User has been updated successfully");
                    modelAndView.addObject("id", userExist);
                    modelAndView.setViewName("update");
                }
            }else {
                modelAndView.addObject("errorMessage", "Sorry, you can not update non-active users");
                modelAndView.setViewName("update");
            }

        }else {
            if (!bindingResult.hasErrors()){
                modelAndView.addObject("errorMessage", "User with provide id is not exist");
                modelAndView.setViewName("update");
            }else {
                modelAndView.setViewName("update");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/update", method = RequestMethod.GET)
    public ModelAndView updateHome(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/addRole", method = RequestMethod.GET)
    public ModelAndView addRoleHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("role");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/addRole1", method = RequestMethod.POST)
    public ModelAndView addAdminRole(@RequestParam("id1") long id){
        ModelAndView modelAndView = new ModelAndView();
        User userExist = userService.getById(id);
        if (userExist != null){
            userService.addAdminRole(userExist);
            modelAndView.addObject("successMessage1", "Admin Role has been added successfully");
            modelAndView.setViewName("role");
        }else {
            modelAndView.addObject("errorMessage1", "User with provide id is not exist");
            modelAndView.setViewName("role");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/addRole2", method = RequestMethod.POST)
    public ModelAndView addUserRole(@RequestParam("id2") long id){
        ModelAndView modelAndView = new ModelAndView();

        /*
            there should be no situations
            when last ADMIN can set role USER for yourself
         */

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User admin = userService.getByUsername(authentication.getName());
        int count = 0;
        List<User> list = userService.getAllUsers();
        for (User a : list) {
            if (a.getGroups().toString().contains("ADMIN")) {
                count++;
            }
        }
        if (count == 1 && admin.getId() == id){
            modelAndView.addObject("adminError", "Sorry, you a last user with admin role in system");
            modelAndView.setViewName("role");
            return modelAndView;
        }

        User userExist = userService.getById(id);
        if (userExist != null){
            userService.addUserRole(userExist);
            modelAndView.addObject("successMessage2", "User Role has been added successfully");
            modelAndView.setViewName("role");
        }else {
            modelAndView.addObject("errorMessage2", "User with provide id is not exist");
            modelAndView.setViewName("role");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/activate", method = RequestMethod.POST)
    public ModelAndView activate(@RequestParam("id3") long id){
        ModelAndView modelAndView = new ModelAndView();
        User userExist = userService.getById(id);
        if (userExist != null){
            userService.activate(userExist);
            modelAndView.addObject("successMessage3", "User activate successfully");
            modelAndView.setViewName("role");
        }else {
            modelAndView.addObject("errorMessage3", "User with provide id is not exist");
            modelAndView.setViewName("role");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home/deactivate", method = RequestMethod.POST)
    public ModelAndView deactivate(@RequestParam("id4") long id){
        ModelAndView modelAndView = new ModelAndView();
        User userExist = userService.getById(id);
        if (userExist != null){
            userService.deActivate(userExist);
            modelAndView.addObject("successMessage4", "User deactivate successfully");
            modelAndView.setViewName("role");
        }else {
            modelAndView.addObject("errorMessage4", "User with provide id is not exist");
            modelAndView.setViewName("role");
        }
        return modelAndView;
    }
}
