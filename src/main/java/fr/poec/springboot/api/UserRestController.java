package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.custom_response.*;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private UserService userService;

    @GetMapping
    @JsonView(JsonViews.UserListView.class)
    @Operation(summary = "Get a list of User", description = "Returns all User of the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = UserListCustomApiResponse.class))}),

            @ApiResponse(responseCode = "204", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse list() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.UserShowView.class)
    @Operation(summary = "Get a User by id", description = "Returns a User as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = UserShowCustomApiResponse.class))}),

            @ApiResponse(responseCode = "204", description = "KO", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorCustomApiResponse.class))})
    })
    public CustomApiResponse show(@PathVariable Long id) {
        return userService.show(id);
    }

}