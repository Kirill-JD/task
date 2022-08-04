package com.example.task.domain.user;

import com.example.task.domain.animal.Animal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
public class User implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = -5449326074498337967L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_id_generator")
    @SequenceGenerator(name = "usr_id_generator", sequenceName = "sq_usr_id", allocationSize = 1)
    private Long id;

    private String username;
    private String password;
    private boolean active;

    @OneToMany
    private Set<Animal> animal;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}