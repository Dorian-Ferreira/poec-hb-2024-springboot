package fr.poec.springboot.api;

import fr.poec.springboot.repository.UserOwnGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/userOwnGame")
public class UserOwnGameRestController {

    private UserOwnGameRepository userOwnGameRepository;

}