package kz.iitu.restapi.model;

//есть ли другой путь?
//
public enum Permission {
    MOVIE_READ("movie:read"),
    MOVIE_WRITE("movie:write"),
    MOVIE_BUY("movie:buy");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
