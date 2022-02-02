package kz.iitu.restapi.model;

import com.google.common.collect.Sets;
import java.util.Set;

import static kz.iitu.restapi.model.Permission.*;

public enum Role {
    USER(Sets.newHashSet(MOVIE_BUY, MOVIE_READ)),
    ADMIN(Sets.newHashSet(MOVIE_BUY, MOVIE_READ, MOVIE_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
