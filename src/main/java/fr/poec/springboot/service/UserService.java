package fr.poec.springboot.service;

import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.DTO.UserPutDTO;
import fr.poec.springboot.custom_response.*;
import fr.poec.springboot.entity.Country;
import fr.poec.springboot.entity.Platform;
import fr.poec.springboot.entity.User;
import fr.poec.springboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private CountryService countryService;

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
        apiResponse.setObjects(users);

        return apiResponse;
    }

    public CustomApiResponse show(Long id) {
        UserShowCustomApiResponse apiResponse = new UserShowCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        errorApiResponse.setEntity(User.class.getSimpleName());

        Optional<User> user = getById(id);

        if(user.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObject(user.get());

            return apiResponse;
        }

        errorApiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorApiResponse.setMessage("This user doesn't exist.");

        return errorApiResponse;
    }

    public CustomApiResponse create(UserPutDTO userDTO, Long id) {
        UserShowCustomApiResponse apiResponse = new UserShowCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        errorApiResponse.setEntity(User.class.getSimpleName());

        User user;

        if(id == null) {
            if(userDTO.getPassword() == null) {
                errorApiResponse.setCode(HttpStatus.BAD_REQUEST.value());
                errorApiResponse.setMessage("Please input a password");
                return errorApiResponse;
            }
            user = userRepository.saveAndFlush(objectFromUserPostDto((UserPostDTO) userDTO));
        } else {
            user = objectFromUserPutDto(userDTO, id);
            if(user == null) {
                errorApiResponse.setCode(HttpStatus.BAD_REQUEST.value());
                errorApiResponse.setMessage("User doesn't exist");
                return errorApiResponse;
            }
            user = userRepository.saveAndFlush(user);
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObject(user);

        return apiResponse;
    }

    private User objectFromUserPostDto(UserPostDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        user.setCreatedAt(new Date());
        user.setWallet(0);

        user.setNickname(userDTO.getNickname());
        user.setPassword(userDTO.getPassword());
        user.setProfileImage(userDTO.getProfileImage());
        if(userDTO.getCountryId() != null) {
            countryService.getById(userDTO.getCountryId()).ifPresent(user::setCountry);
        }
        user.setRoles("[]");

        return user;
    }

    private User objectFromUserPutDto(UserPutDTO userDTO, Long id) {
        Optional<User> oUser = getById(id);
        if(oUser.isEmpty()) {
            return null;
        }
        User user = oUser.get();

        if(userDTO.getNickname() != null) {
            user.setNickname(userDTO.getNickname());
        }
        if(userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if(userDTO.getProfileImage() != null) {
            user.setProfileImage(userDTO.getProfileImage());
        }
        if(userDTO.getCountryId() != null) {
            countryService.getById(userDTO.getCountryId()).ifPresent(user::setCountry);
        }

        return user;
    }


    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
