package fr.poec.springboot.instant_faking.mapping;

public class UrlRoute {

    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTER = "/register";

    public final static String URL_GAME = "/game";
    public final static String URL_GAME_SEARCH = URL_GAME + "/search";

    public final static String URL_ADMIN = "/admin";
    public final static String URL_ADMIN_PLATFORM = URL_ADMIN + "/platform";
    public final static String URL_ADMIN_PLATFORM_NEW = URL_ADMIN_PLATFORM + "/new";
    public final static String URL_ADMIN_PLATFORM_EDIT = URL_ADMIN_PLATFORM + "/edit";
}
