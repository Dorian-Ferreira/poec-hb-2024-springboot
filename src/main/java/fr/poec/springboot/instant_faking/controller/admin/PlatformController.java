package fr.poec.springboot.instant_faking.controller.admin;

import fr.poec.springboot.instant_faking.DTO.PlatformDTO;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
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
@RequestMapping(name = "AppPlatform")
public class PlatformController {

    private final PlatformService platformService;

    @GetMapping(path = UrlRoute.URL_ADMIN_PLATFORM, name = "index")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("platform/index");
        mav.addObject("platforms", platformService.findAll(PageRequest.of(0, 50)));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PLATFORM_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new PlatformDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PLATFORM_EDIT + "/{id}", name = "edit")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                platformService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PLATFORM_NEW, name = "newHandler")
    public ModelAndView formHandler(
        @Validated(ValidationGroup.OnPostItem.class) @ModelAttribute("platform") PlatformDTO platformDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, platformDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PLATFORM_EDIT + "/{id}", name = "editHandler")
    public ModelAndView formHandler(
        @Validated(ValidationGroup.OnPostItem.class) @ModelAttribute("platform") PlatformDTO platformDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id
    ) {
        return formHandle(result, mav, platformDTO, id);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            PlatformDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("platform/form");
        mav.addObject("platform", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            PlatformDTO dto,
            Long id
    ) {
        if (result.hasErrors()) {
            mav.setViewName("platform/form");
            return mav;
        }
        platformService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_PLATFORM); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }
}
