package fr.poec.springboot.controller;

import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/register", name = "AppRegister")
public class RegisterController {

    private UserService userService;

    @GetMapping(name = "register")
    public ModelAndView register(ModelAndView mav) {
        mav.setViewName("security/register");
        mav.addObject("userPostDTO", new UserPostDTO());
        return mav;
    }

    @PostMapping(name = "registerHandler")
    public ModelAndView registerHandler(
            ModelAndView mav,
            @Valid @ModelAttribute("userPostDTO") UserPostDTO userPostDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("security/register");
            return mav;
        }
        userService.persist(userPostDTO, null);
        mav.setViewName("redirect:/");
        return mav;
    }

}
