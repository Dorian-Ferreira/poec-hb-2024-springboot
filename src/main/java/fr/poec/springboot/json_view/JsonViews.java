package fr.poec.springboot.json_view;

public class JsonViews {
    public interface IgnoreView {}
    public interface CountryDetailsView {}
    public interface PublisherMinimalView {}
    public interface ReviewMinimalView {}
    public interface UserMinimalView {}
    public interface CustomApiResponseView {}

    public interface GameListView extends CustomApiResponseView {}
    public interface GameShowView extends UserMinimalView, PublisherMinimalView, GameListView, CountryDetailsView, ReviewMinimalView {}

    public interface ReviewListView extends UserMinimalView, GameListView, ReviewMinimalView {}

    public interface PublisherShowView extends PublisherMinimalView, GameListView, CountryDetailsView {}

    public interface UserListView extends CustomApiResponseView, UserMinimalView, CountryDetailsView {}
    public interface UserShowView extends UserListView, GameListView, CountryDetailsView, ReviewMinimalView {}
}
