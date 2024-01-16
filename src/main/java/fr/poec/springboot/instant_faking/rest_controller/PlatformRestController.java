package fr.poec.springboot.instant_faking.rest_controller;


import fr.poec.springboot.instant_faking.DTO.PlatformDTO;
import fr.poec.springboot.instant_faking.entity.Platform;
import fr.poec.springboot.instant_faking.service.PlatformService;
import fr.poec.springboot.instant_faking.validator.group.ValidationGroup;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/platform")
public class PlatformRestController {

    private PlatformService platformService;

    @GetMapping
    public List<Platform> list() {
        return platformService.findAll();
    }

    @GetMapping("/{field}")
    public Optional<Platform> show(@PathVariable String field) {
        return platformService.findByField(field);
    }

    @PostMapping
    @Validated(ValidationGroup.OnPostItem.class)
    public Platform persist(@Valid @RequestBody PlatformDTO dto) {
        return platformService.persist(dto, null);
    }

    @PutMapping("/{id}")
    @Validated(ValidationGroup.OnPutItem.class)
    public Platform persist(@Valid @RequestBody PlatformDTO dto, @PathVariable Long id) {
        return platformService.persist(dto, id);
    }

}