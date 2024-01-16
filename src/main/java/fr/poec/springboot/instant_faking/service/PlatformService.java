package fr.poec.springboot.instant_faking.service;

import fr.poec.springboot.instant_faking.DTO.PlatformDTO;
import fr.poec.springboot.instant_faking.entity.Platform;
import fr.poec.springboot.instant_faking.exception.NotFoundInstantFakingException;
import fr.poec.springboot.instant_faking.repository.PlatformRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlatformService implements DAOServiceInterface<Platform> {

    private PlatformRepository platformRepository;

    @Override
    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    public Page<Platform> findAll(Pageable pageable) {
        return platformRepository.findAll(pageable);
    }

    @Override
    public Platform getObjectById(Long id) {
        Optional<Platform> optionalPlatform = platformRepository.findById(id);
        if (optionalPlatform.isEmpty()) {
            throw new NotFoundInstantFakingException("Platform", "id", id);
        }
        return optionalPlatform.get();
    }

    public Platform persist(PlatformDTO platformDTO, Long id) {
        if (id != null && platformRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Platform", "id", id
            );
        }

        Platform pf = new Platform();
        pf.setId(id);
        pf.setName(platformDTO.getName());
        return platformRepository.saveAndFlush(pf);
    }

    public Optional<Platform> findByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return platformRepository.findById(id);
        } catch (NumberFormatException e) {
            return platformRepository.findByName(field);
        }
    }

    public PlatformDTO getDTOById(Long id) {
        Platform platform = getObjectById(id);
        PlatformDTO dto = new PlatformDTO();
        dto.setName(platform.getName());
        return dto;
    }
}
