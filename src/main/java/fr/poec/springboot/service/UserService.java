package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.UserListCustomApiResponse;
import fr.poec.springboot.custom_response.UserShowCustomApiResponse;
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

    public CustomApiResponse findAll() {
        UserListCustomApiResponse apiResponse = new UserListCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        errorApiResponse.setEntity(User.class.getSimpleName());

        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no users.");

            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(new ArrayList<>(users));

        return apiResponse;
    }

    public CustomApiResponse show(Long id) {
        UserShowCustomApiResponse apiResponse = new UserShowCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        errorApiResponse.setEntity(User.class.getSimpleName());

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(Collections.singletonList(user.get()));

            return apiResponse;
        }

        errorApiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorApiResponse.setMessage("This user doesn't exist.");

        return errorApiResponse;
    }
}
