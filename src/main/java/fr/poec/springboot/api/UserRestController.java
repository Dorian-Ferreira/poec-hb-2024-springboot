package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.DTO.UserPutDTO;
import fr.poec.springboot.custom_response.*;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.UserService;
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
@RequestMapping("/api/user")
public class UserRestController {

    private UserService userService;

    @GetMapping
    @JsonView(JsonViews.UserListView.class)
    @Operation(summary = "Get a list of User", description = "Returns all User of the application")
    public CustomApiResponse list() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.UserShowView.class)
    @Operation(summary = "Get a User by id", description = "Returns a User as per the id")
    public CustomApiResponse show(@PathVariable Long id) {
        return userService.show(id);
    }

    @PutMapping(path = "/{id}")
    @JsonView(JsonViews.UserShowView.class)
    @Operation(summary = "Modify a User", description = "Returns the modified User")
    public CustomApiResponse update(@Valid @RequestBody UserPutDTO userDTO, @PathVariable Long id) {
        return userService.persist(userDTO, id);
    }
}