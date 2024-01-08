package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ApiResponse list() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViews.UserShowView.class)
    @Operation(summary = "Get a User by id", description = "Returns a User as per the id")
    public ApiResponse show(@PathVariable Long id) {
        return userService.show(id);
    }

}