package fr.poec.springboot.instant_faking.controller;

import fr.poec.springboot.instant_faking.entity.User;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
import fr.poec.springboot.instant_faking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppUser")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = UrlRoute.URL_USER, name = "show")
    public ModelAndView show(
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null) {
            mav.setViewName("redirect:/");
            return mav;
        }
        User user = userService.findByEmail(principal.getName());

        mav.addObject("loggedUser", principal.getName().equals(user.getUsername()));

        mav.setViewName("user/show");
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_USER + "/{name}", name = "show")
    public ModelAndView show(
            @PathVariable String name,
            ModelAndView mav,
            Principal principal
    ) {
        User user = userService.findByName(name);
        mav.addObject("loggedUser", principal.getName().equals(user.getUsername()));
        mav.setViewName("user/show");
        mav.addObject("user", user);
        return mav;
    }
}
