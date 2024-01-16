package fr.poec.springboot.instant_faking.controller.admin;

import fr.poec.springboot.instant_faking.DTO.CategoryDTO;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
import fr.poec.springboot.instant_faking.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping(name = "AppCategory")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = UrlRoute.URL_ADMIN_CATEGORY, name = "index")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/category/index");
        mav.addObject("categories", categoryService.findAll());
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_CATEGORY_NEW, name = "new")
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new CategoryDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_CATEGORY_EDIT + "/{id}", name = "edit")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                categoryService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_CATEGORY_NEW, name = "newHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("category") CategoryDTO categoryDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, categoryDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_CATEGORY_EDIT + "/{id}", name = "editHandler")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("category") CategoryDTO categoryDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id
    ) {
        return formHandle(result, mav, categoryDTO, id);
    }

    private ModelAndView getFormByDTO(
            ModelAndView mav,
            CategoryDTO dto,
            String uri,
            boolean isEdit
    ) {
        mav.setViewName("admin/category/form");
        mav.addObject("category", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(
            BindingResult result,
            ModelAndView mav,
            CategoryDTO dto,
            Long id
    ) {
        if (result.hasErrors()) {
            mav.setViewName("admin/category/form");
            return mav;
        }
        categoryService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_CATEGORY); // FORCEMENT UN PATH (une URL de route !)
        return mav;
    }
}
