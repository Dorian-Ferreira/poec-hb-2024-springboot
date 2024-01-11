package fr.poec.springboot.controller;

import fr.poec.springboot.DTO.ReviewDTO;
import fr.poec.springboot.entity.Game;
import fr.poec.springboot.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/{slug}")
    public ModelAndView show(ModelAndView mav, @PathVariable String slug) {
        Game game = gameService.findBySlug(slug);
        // Initialisation du ReviewDTO
        ReviewDTO dto = new ReviewDTO();
        dto.setGameId(game.getId());
        dto.setUserId(1L);
        mav.setViewName("game/show");
        mav.addObject("game", game);
        return mav;
    }

}
