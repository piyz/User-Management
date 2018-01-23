package by.matrosov.usermanagment.controller;

import by.matrosov.usermanagment.model.User;
import by.matrosov.usermanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SimpleController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
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


}
