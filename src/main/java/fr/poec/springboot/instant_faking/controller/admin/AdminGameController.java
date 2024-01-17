package fr.poec.springboot.instant_faking.controller.admin;

import fr.poec.springboot.instant_faking.DTO.GameDTO;
import fr.poec.springboot.instant_faking.DTO.PublisherWebDTO;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
import fr.poec.springboot.instant_faking.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppAdminGame")
public class AdminGameController {

    private final GameService gameService;
    private final CountryService countryService;
    private final PlatformService platformService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME, name = "index")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/game/index");
        mav.addObject("games", gameService.findAll());
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new GameDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_EDIT + "/{id}", name = "edit")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                gameService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GAME_NEW, name = "newHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("game") GameDTO platformDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, platformDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GAME_EDIT + "/{id}", name = "editHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("game") GameDTO platformDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id
    ) {
        return formHandle(result, mav, platformDTO, id);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            GameDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("admin/game/form");
        mav.addObject("game", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        mav.addObject("countries", countryService.findAll());
        mav.addObject("platforms", platformService.findAll());
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("publishers", publisherService.findAll());
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            GameDTO dto,
            Long id
    ) {
        if (result.hasErrors()) {
            mav.setViewName("admin/game/form");
            mav.addObject("countries", countryService.findAll());
            mav.addObject("platforms", platformService.findAll());
            mav.addObject("categories", categoryService.findAll());
            mav.addObject("publishers", publisherService.findAll());
            return mav;
        }
        gameService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_GAME); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }
}
