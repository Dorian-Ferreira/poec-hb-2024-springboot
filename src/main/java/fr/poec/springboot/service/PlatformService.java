package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.custom_response.PlatformApiResponse;
import fr.poec.springboot.entity.Category;
import fr.poec.springboot.entity.Platform;
import fr.poec.springboot.repository.PlatformRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlatformService {

    private PlatformRepository platformRepository;

    public ApiResponse findAll() {
        PlatformApiResponse apiResponse = new PlatformApiResponse();

        apiResponse.setEntity(Platform.class.getSimpleName());
        List<Platform> platforms = platformRepository.findAll();
        if(platforms.isEmpty()){
            apiResponse.setCode(HttpStatus.NO_CONTENT.value());
            apiResponse.addObject("There is no platforms.");
        } else {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(new ArrayList<>(platforms));
        }

        return apiResponse;
    }

}
