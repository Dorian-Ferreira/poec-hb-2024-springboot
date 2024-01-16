package fr.poec.springboot.instant_faking.controller;

import fr.poec.springboot.instant_faking.DTO.ReviewDTO;
import fr.poec.springboot.instant_faking.entity.Game;
import fr.poec.springboot.instant_faking.entity.User;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
import fr.poec.springboot.instant_faking.service.GameService;
import fr.poec.springboot.instant_faking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppGame")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    private final UserService userService;

    @GetMapping(path = UrlRoute.URL_GAME + "/{slug}", name = "show")
    public ModelAndView show(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal
    ) {
        Game game = gameService.findBySlug(slug);

        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            ReviewDTO dto = new ReviewDTO();
            dto.setGameId(game.getId());
            dto.setUserId(user.getId());
            mav.addObject("reviewDto", dto);
        }
        mav.setViewName("game/show");
        mav.addObject("game", game);
        return mav;
    }

    @GetMapping(path =  UrlRoute.URL_GAME_SEARCH + "/{searched}", name = "search")
    public ModelAndView search(@PathVariable String searched, ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("gamesReleased", gameService.findAllBySearchedValue(searched));
        return mav;
    }

}
