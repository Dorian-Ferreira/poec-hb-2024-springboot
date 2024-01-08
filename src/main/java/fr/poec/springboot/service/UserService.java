package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.custom_response.UserListApiResponse;
import fr.poec.springboot.custom_response.UserShowApiResponse;
import fr.poec.springboot.entity.User;
import fr.poec.springboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public ApiResponse findAll() {
        UserListApiResponse apiResponse = new UserListApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            apiResponse.setCode(HttpStatus.NO_CONTENT.value());
            apiResponse.addObject("There is no users.");
        } else {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(new ArrayList<>(users));
        }

        return apiResponse;
    }

    public ApiResponse show(Long id) {
        UserShowApiResponse apiResponse = new UserShowApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(Collections.singletonList(user.get()));
        } else {
            apiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            apiResponse.addObject("This user doesn't exist.");
        }

        return apiResponse;
    }
}
