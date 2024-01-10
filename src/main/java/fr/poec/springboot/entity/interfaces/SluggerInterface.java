package fr.poec.springboot.entity.interfaces;


import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.json_view.JsonViews;

public interface SluggerInterface {

    void setSlug(String slug);

    String getField();

}
