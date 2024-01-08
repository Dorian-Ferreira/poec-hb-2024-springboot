package fr.poec.springboot.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.entity.Review;
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
public class ReviewCustomApiResponse extends CustomApiResponse {

    @JsonView(JsonViews.ReviewListView.class)
    private List<Review> objects = new ArrayList<>();

    public void addObject(Review object) {
        objects.add(object);
    }
}