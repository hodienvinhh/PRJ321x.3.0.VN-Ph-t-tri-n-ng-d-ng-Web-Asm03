package com.example.assignment03.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserLoginDTO extends User implements UserDetails {

    @Getter
    private int id;
    private String email;

    public UserLoginDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserLoginDTO(String username, String password, Collection<? extends GrantedAuthority> authorities, com.example.assignment03.entity.User user) {
        super(username, password, authorities);
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public boolean checkAdmin(Collection<? extends GrantedAuthority> authorities) {
        for (Object au : authorities.toArray()) {
            if (au.toString().equals("Manager"))
                return true;
        }
        return false;
    }


    public UserLoginDTO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
