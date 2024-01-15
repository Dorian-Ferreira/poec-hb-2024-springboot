package fr.poec.springboot.controller;

import fr.poec.springboot.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/", name = "AppHome")
@AllArgsConstructor
public class HomeController {

    private GameService gameService;

    @GetMapping(name = "index")
    public ModelAndView home(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("gamesReleased", gameService.findTop9ByOrderByPublishedAtDesc());
        return mav;
    }

}
