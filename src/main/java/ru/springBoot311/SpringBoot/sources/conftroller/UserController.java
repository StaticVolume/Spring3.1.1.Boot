package ru.springBoot311.SpringBoot.sources.conftroller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.springBoot311.SpringBoot.sources.model.User;
import ru.springBoot311.SpringBoot.sources.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String printStartPage() {
        return "index";
    }

    @GetMapping("/allUsers")
    public String printAllUsers(Model model) {
        model.addAttribute("users_list", userService.getAllUsers());
        return "/allUsers";
    }

    @RequestMapping("/createUser")
    public String createUserForm(Model model){
        model.addAttribute("user",new User());
        return "/createUser";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "createUser";
        }
        userService.addUser(user);
        return "redirect:/allUsers";
    }

    @GetMapping("/allUsers/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "/editUser";
    }

    @PatchMapping("/allUsers/{id}")
    public String updateUser(@ModelAttribute("user") User user,@PathVariable("id") long id ) {
        userService.updateUser(user);
        return "redirect:/allUsers";
    }

    @DeleteMapping("/allUsers/delete/{id}")
    public String deleteUser( @PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/allUsers";
    }
}
