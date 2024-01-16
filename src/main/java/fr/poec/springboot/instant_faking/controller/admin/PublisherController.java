package fr.poec.springboot.instant_faking.controller.admin;

import fr.poec.springboot.instant_faking.DTO.PlatformDTO;
import fr.poec.springboot.instant_faking.DTO.PublisherDTO;
import fr.poec.springboot.instant_faking.DTO.PublisherWebDTO;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
import fr.poec.springboot.instant_faking.service.CountryService;
import fr.poec.springboot.instant_faking.service.PlatformService;
import fr.poec.springboot.instant_faking.service.PublisherService;
import fr.poec.springboot.instant_faking.validator.group.ValidationGroup;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppPublisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final CountryService countryService;

    @GetMapping(path = UrlRoute.URL_ADMIN_PUBLISHER, name = "index")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/publisher/index");
        mav.addObject("publishers", publisherService.findAll());
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new PublisherWebDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_EDIT + "/{id}", name = "edit")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                publisherService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_NEW, name = "newHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("publisher") PublisherWebDTO platformDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, platformDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_EDIT + "/{id}", name = "editHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("publisher") PublisherWebDTO platformDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id
    ) {
        return formHandle(result, mav, platformDTO, id);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            PublisherWebDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("admin/publisher/form");
        mav.addObject("publisher", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        mav.addObject("countries", countryService.findAll());
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            PublisherWebDTO dto,
            Long id
    ) {
        if (result.hasErrors()) {
            mav.setViewName("admin/publisher/form");
            return mav;
        }
        publisherService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_PUBLISHER); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }
}
