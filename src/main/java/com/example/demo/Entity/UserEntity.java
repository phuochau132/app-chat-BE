package com.example.demo.Entity;

import com.example.demo.Response.IErr;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class UserEntity implements UserDetails, IErr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=null;
    private String email=null;
    private String full_name=null;
    private String name=null;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT '/anonymous_avatar.png'")
    private String avatar;
    @JsonIgnore
    private String password=null;
    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles=new HashSet<RoleEntity>();
    @ManyToMany
    @JoinTable(name = "friendships",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<UserEntity> friends = new HashSet<>();
    public UserEntity(Long id, String name, String email, String full_name, String password, Set<RoleEntity> roles) {
        this.id = id;
        this.email = email;
        this.name=name;
        this.full_name = full_name;
        this.password = password;
        this.roles = roles;
    }



    public UserEntity(String email, String password) {
        this.email=email;
        this.password=password;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection=new ArrayList<>();
        roles.stream().forEach(item->collection.add(new SimpleGrantedAuthority(item.getName())));
        return List.of(new SimpleGrantedAuthority(collection.toString()));
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return this.name;
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
