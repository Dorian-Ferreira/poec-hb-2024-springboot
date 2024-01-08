package fr.poec.springboot.utils;

import org.springframework.stereotype.Component;

@Component
public class Slug {

    public String slugify(String toSlug) {
        String slug = toSlug.toLowerCase();
        slug = slug.replaceAll("^([a-z]|[à-ü]|[0-9])", "");
        slug = slug.replaceAll(" ", "-");
        return slug;
    }
}
