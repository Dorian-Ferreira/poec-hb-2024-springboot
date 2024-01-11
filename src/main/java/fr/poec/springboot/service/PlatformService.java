package fr.poec.springboot.service;

import fr.poec.springboot.DTO.PlatformDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.PlatformCustomApiResponse;
import fr.poec.springboot.entity.Platform;
import fr.poec.springboot.repository.PlatformRepository;
import fr.poec.springboot.utils.Slug;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlatformService {

    private PlatformRepository platformRepository;

    public CustomApiResponse findAll() {
        PlatformCustomApiResponse apiResponse = new PlatformCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Platform.class.getSimpleName());
        errorApiResponse.setEntity(Platform.class.getSimpleName());

        List<Platform> platforms = platformRepository.findAll();
        if(platforms.isEmpty()) {
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no platforms.");

            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(platforms);

        return apiResponse;
    }

    public Page<Platform> findAll(Pageable pageable) {
        return platformRepository.findAll(pageable);
    }

    public CustomApiResponse persist(PlatformDTO platformDTO, Long id) {
        PlatformCustomApiResponse apiResponse = new PlatformCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Platform.class.getSimpleName());
        errorApiResponse.setEntity(Platform.class.getSimpleName());

        Platform p = platformRepository.saveAndFlush(objectFromDto(platformDTO, id));

        if(p.getId() == null) {
            errorApiResponse.setCode(HttpStatus.BAD_REQUEST.value());
            errorApiResponse.setMessage("Error after creating");
            return errorApiResponse;
        }
        List<Platform> platforms = platformRepository.findAll();
        if(platforms.isEmpty()) {
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no platforms.");

            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(platforms);

        return apiResponse;
    }

    private Platform objectFromDto(PlatformDTO platformDTO, Long id) {
        Platform platform = new Platform();

        platform.setId(id);
        platform.setName(platformDTO.getName());

        return platform;
    }

    public PlatformDTO getDTOById(Long id) {
        Platform platform = getObjectById(id);
        PlatformDTO dto = new PlatformDTO();
        dto.setName(platform.getName());
        return dto;
    }

    private Platform getObjectById(Long id) {
        Optional<Platform> p = platformRepository.findById(id);
        return p.orElse(null);
    }
}
