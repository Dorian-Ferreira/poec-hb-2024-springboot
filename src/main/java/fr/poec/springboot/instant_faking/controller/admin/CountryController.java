package fr.poec.springboot.instant_faking.controller.admin;

import fr.poec.springboot.instant_faking.DTO.CountryDTO;
import fr.poec.springboot.instant_faking.DTO.PlatformDTO;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
import fr.poec.springboot.instant_faking.service.CountryService;
import fr.poec.springboot.instant_faking.service.PlatformService;
import fr.poec.springboot.instant_faking.validator.group.ValidationGroup;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppCountry")
public class CountryController {

    private final CountryService countryService;

    @GetMapping(path = UrlRoute.URL_ADMIN_COUNTRY, name = "index")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/country/index");
        mav.addObject("countries", countryService.findAll());
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_COUNTRY_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new CountryDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_COUNTRY_EDIT + "/{id}", name = "edit")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                countryService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_COUNTRY_NEW, name = "newHandler")
    public ModelAndView formHandler(
        @Validated(ValidationGroup.OnPostItem.class) @ModelAttribute("country") CountryDTO countryDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, countryDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_COUNTRY_EDIT + "/{id}", name = "editHandler")
    public ModelAndView formHandler(
        @Validated(ValidationGroup.OnPostItem.class) @ModelAttribute("country") CountryDTO countryDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id
    ) {
        return formHandle(result, mav, countryDTO, id);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            CountryDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("admin/country/form");
        mav.addObject("country", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            CountryDTO dto,
            Long id
    ) {
        if (result.hasErrors()) {
            mav.setViewName("admin/country/form");
            return mav;
        }
        countryService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_COUNTRY); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }
}
