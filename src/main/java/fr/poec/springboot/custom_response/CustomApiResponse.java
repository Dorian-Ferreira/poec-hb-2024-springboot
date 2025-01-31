package fr.poec.springboot.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.json_view.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomApiResponse {

    @JsonView(JsonViews.CustomApiResponseView.class)
    private int code;

    @JsonView(JsonViews.CustomApiResponseView.class)
    private String entity;
}