package fr.poec.springboot.utils;

public class Slug {

    public static String slugify(String toSlug) {
        String slug = toSlug.toLowerCase();
        slug = slug.replaceAll("^([a-z]|[à-ü]|[0-9])", " ");
        slug = slug.replaceAll("\\s+", " ");
        slug = slug.replaceAll(" ", "-");
        return slug;
    }
}
