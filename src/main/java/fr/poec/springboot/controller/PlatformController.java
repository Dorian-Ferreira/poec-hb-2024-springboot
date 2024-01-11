package fr.poec.springboot.controller;

import fr.poec.springboot.DTO.PlatformDTO;
import fr.poec.springboot.service.PlatformService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/platform")
public class PlatformController {

    private final PlatformService platformService;

    @GetMapping("")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("platform/index");
        mav.addObject("platforms", platformService.findAll(PageRequest.of(0, 5)).getContent());
        return mav;
    }

    @GetMapping("/new")
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

    @GetMapping("/edit/{id}")
    public ModelAndView edit(
            ModelAndView mav,
            HttpServletRequest httpServletRequest,
            @PathVariable Long id
    ) {
        return getFormByDTO(
                mav,
                platformService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping("/new")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("platform") PlatformDTO platformDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, platformDTO, null);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("platform") PlatformDTO platformDTO,
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
        mav.setViewName("redirect:/platform"); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }
}
