package fr.poec.springboot.api;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.service.PlatformService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/platform")
public class PlatformRestController {

    private PlatformService platformService;

    @GetMapping
    @Operation(summary = "Get a list of Platform", description = "Returns all Platform of the application")
    public ApiResponse list() {
        return this.platformService.findAll();
    }

}