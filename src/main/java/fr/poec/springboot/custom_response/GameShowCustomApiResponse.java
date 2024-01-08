package fr.poec.springboot.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.entity.Game;
import fr.poec.springboot.json_view.JsonViews;
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
public class GameShowCustomApiResponse extends CustomApiResponse {

    @JsonView(JsonViews.GameShowView.class)
    private List<Game> objects = new ArrayList<>();

    public void addObject(Game object) {
        objects.add(object);
    }
}