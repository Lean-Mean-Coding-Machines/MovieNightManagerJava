package com.carterprojects.movienightmanager.repository.models.user;

import com.carterprojects.movienightmanager.repository.models.CommunityUser;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.NominationLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    UserRole userRole;

    String firstName;

    String lastName;

    String username;

    String password;

    String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Nomination> nominations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<NominationLike> nominationLikes;

    @OneToMany(mappedBy = "user")
    List<CommunityUser> communityUsers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
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
        return true;
    }
}
