package fr.poec.springboot.api;

import fr.poec.springboot.DTO.PlatformDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.PlatformCustomApiResponse;
import fr.poec.springboot.service.PlatformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/platform")
public class PlatformRestController {

    private PlatformService platformService;

    @GetMapping
    @Operation(summary = "Get a list of Platform", description = "Returns all Platform of the application")
    public CustomApiResponse list() {
        return this.platformService.findAll();
    }

    @PostMapping
    @Operation(summary = "Add a Platform", description = "Returns all Platform of the application after addition")
    public CustomApiResponse create(@Valid @RequestBody PlatformDTO platformDTO) {
        return platformService.persist(platformDTO, null);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update a Platform", description = "Returns all Platform of the application after modification")
    public CustomApiResponse update(@Valid @RequestBody PlatformDTO platformDTO,@PathVariable Long id) {
        return platformService.persist(platformDTO, id);
    }
}