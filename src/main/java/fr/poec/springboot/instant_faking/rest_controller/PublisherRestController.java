package fr.poec.springboot.instant_faking.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.DTO.PublisherDTO;
import fr.poec.springboot.instant_faking.entity.Publisher;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
import fr.poec.springboot.instant_faking.service.PublisherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/publisher")
public class PublisherRestController {

    private PublisherService publisherService;

    @GetMapping
    @JsonView(JsonViews.PublisherShowView.class)
    public List<Publisher> list() {
        return publisherService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.PublisherAllShowView.class)
    public Publisher show(@PathVariable Long id) {
        return publisherService.show(id);
    }

    @PostMapping
    public Publisher create(@Valid @RequestBody PublisherDTO publisherDTO) {
        return publisherService.persist(publisherDTO, null);
    }

    @PutMapping("/{id}")
    public Publisher update(@Valid @RequestBody PublisherDTO publisherDTO, @PathVariable Long id) {
        return publisherService.persist(publisherDTO, id);
    }

}