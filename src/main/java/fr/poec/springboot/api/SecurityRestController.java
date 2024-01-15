package fr.poec.springboot.api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.DTO.UserLoginDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.JwtTokenResponse;
import fr.poec.springboot.entity.User;
import fr.poec.springboot.json_view.JsonViews;
import fr.poec.springboot.service.UserService;
import fr.poec.springboot.service.security.JwtAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SecurityRestController {

    private UserService userService;

    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping("/register")
    @JsonView(JsonViews.UserShowView.class)
    @Operation(summary = "Add a User", description = "Returns the added User")
    public CustomApiResponse create(@Valid @RequestBody UserPostDTO userDTO) {
        return userService.persist(userDTO, null);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> create(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return jwtAuthenticationService.authenticate(userLoginDTO);
    }

}
