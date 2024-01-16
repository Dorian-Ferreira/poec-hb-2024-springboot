package fr.poec.springboot.instant_faking.json_views;

public class JsonViews {

    public interface GameListView extends
            GameMinimalView {}

    public interface GameMinimalView {}

    public interface GameShowView extends
            GameMinimalView,
            PublisherShowView,
            ReviewShowView,
            UserListView,
            GamePlatformMinimalView,
            CategoryMinimalView,
            CountryDetailView { }

    public interface GamePlatformView extends
            GamePlatformMinimalView {}

    public interface GamePlatformMinimalView {}

    public interface CategoryView extends
            CategoryMinimalView {}

    public interface CategoryMinimalView {}

    public interface CountryDetailView {}

    public interface PublisherAllShowView extends
            PublisherShowView,
            GameListView {}

    public interface PublisherShowView extends
            CountryDetailView {}

    public interface UserShowView extends
            GameListView,
            UserMinimalView,
            CountryDetailView,
            ReviewShowView {}

    public interface UserMinimalView {}

    public interface UserListView extends
            UserMinimalView,
            CountryDetailView {}

    public interface ReviewShowView extends
            GameReviewShowView,
            UserListView {}

    public interface GameReviewShowView {}

    public interface ReviewListView extends
            GameListView,
            ReviewShowView,
            UserListView {}

}
