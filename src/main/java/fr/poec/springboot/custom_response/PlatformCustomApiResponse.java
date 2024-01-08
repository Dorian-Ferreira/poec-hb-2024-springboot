package fr.poec.springboot.custom_response;

import fr.poec.springboot.entity.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlatformCustomApiResponse extends CustomApiResponse {

    private List<Platform> objects = new ArrayList<>();

    public void addObject(Platform object) {
        objects.add(object);
    }
}