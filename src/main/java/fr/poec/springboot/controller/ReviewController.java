package fr.poec.springboot.controller;

import fr.poec.springboot.DTO.PlatformDTO;
import fr.poec.springboot.DTO.ReviewDTO;
import fr.poec.springboot.entity.Game;
import fr.poec.springboot.entity.Review;
import fr.poec.springboot.service.GameService;
import fr.poec.springboot.service.PlatformService;
import fr.poec.springboot.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/review", name = "AppReview")
public class ReviewController {

    private final ReviewService reviewService;
    private final GameService gameService;


    @GetMapping(value = "/add/{slug}", name = "add")
    public ModelAndView edit(
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            @PathVariable String slug
    ) {
        try {
            gameService.findBySlug(slug).get();
        } catch (Exception e) {
            mav.setViewName("redirect:/"); // FORCEMENT UN PATH (une URL de route !)
            return mav;
        }
        return getFormByDTO(
                mav,
                reviewService.getDTOBySlug(slug, 1L),
                httpServletRequest.getRequestURI(),
                slug
        );
    }

    @PostMapping(value = "/add/{slug}", name = "addHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("review") ReviewDTO reviewDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable String slug
    ) {
        return formHandle(result, mav, reviewDTO, slug);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            ReviewDTO reviewDTO,
            String uri,
            String slug
    ) {
        mav.setViewName("review/form");
        mav.addObject("review", reviewDTO);
        mav.addObject("action", uri);
        mav.addObject("game",gameService.findBySlug(slug).get());
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            ReviewDTO dto,
            String gameSlug
    ) {
        if (result.hasErrors()) {
            mav.setViewName("review/form");
            mav.addObject("game",gameService.findBySlug(gameSlug).get());
            return mav;
        }
        Review r = reviewService.save(dto, gameSlug, 1L);
        mav.setViewName("redirect:/game/"+r.getGame().getSlug()); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }
}
