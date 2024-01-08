package fr.poec.springboot.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewListDTO {

    private Long id;
    private Date createdAt;
    private String content;
    private String title;
    private double rating;
    private int upVote;
    private int downVote;

    private UserDTO user;
    private GameDTO game;

}
