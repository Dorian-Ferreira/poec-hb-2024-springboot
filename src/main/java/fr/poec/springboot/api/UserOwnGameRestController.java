package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.DTO.UserOwnGameDTO;
import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.UserShowCustomApiResponse;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.repository.UserOwnGameRepository;
import fr.poec.springboot.service.UserOwnGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/userOwnGame")
public class UserOwnGameRestController {

    private UserOwnGameService userOwnGameService;

    @PostMapping
    @JsonView(JsonViews.UserShowView.class)
    @Operation(summary = "Add a UserOwnGame", description = "Returns the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = UserShowCustomApiResponse.class))}),
    })
    public CustomApiResponse create(@Valid @RequestBody UserOwnGameDTO userDTO) {
        return userOwnGameService.persist(userDTO);
    }

}