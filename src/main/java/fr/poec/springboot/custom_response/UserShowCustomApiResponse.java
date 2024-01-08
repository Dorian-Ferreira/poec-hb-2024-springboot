package fr.poec.springboot.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.entity.User;
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
public class UserShowCustomApiResponse extends CustomApiResponse {

    @JsonView(JsonViews.UserShowView.class)
    private List<User> objects = new ArrayList<>();

    public void addObject(User object) {
        objects.add(object);
    }
}