package fr.poec.springboot.instant_faking.controller.admin;

import fr.poec.springboot.instant_faking.DTO.PlatformDTO;
import fr.poec.springboot.instant_faking.mapping.UrlRoute;
import fr.poec.springboot.instant_faking.service.PlatformService;
import fr.poec.springboot.instant_faking.service.ReviewService;
import fr.poec.springboot.instant_faking.service.UserOwnGameService;
import fr.poec.springboot.instant_faking.service.UserService;
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
@RequestMapping(name = "AppAdmin")
public class AdminController {
    private UserService userService;
    private UserOwnGameService userOwnGameService;
    private ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_ADMIN, name = "index")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/index");
        mav.addObject("lastUsers", userService.getLastCreatedUsers());
        mav.addObject("lastSales", userOwnGameService.getLastSales());
        mav.addObject("lastReviews", reviewService.getLastReviews());
        mav.addObject("totalSales", userOwnGameService.getTotalSales());
        return mav;
    }

}
