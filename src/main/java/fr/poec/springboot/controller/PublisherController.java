package fr.poec.springboot.controller;

import fr.poec.springboot.DTO.ReviewDTO;
import fr.poec.springboot.entity.Game;
import fr.poec.springboot.entity.Publisher;
import fr.poec.springboot.service.GameService;
import fr.poec.springboot.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/publisher", name = "AppPublisher")
@AllArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping(value = "/{slug}", name = "show")
    public ModelAndView show(ModelAndView mav, @PathVariable String slug) {
        Publisher publisher;
        try {
            publisher = publisherService.findBySlug(slug).get();
        } catch (Exception e) {
            mav.setViewName("redirect:/"); // FORCEMENT UN PATH (une URL de route !)
            return mav;
        }
        mav.setViewName("publisher/show");
        mav.addObject("publisher", publisher);
        return mav;
    }

}
