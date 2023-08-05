package com.example.demo.Entity;

import com.example.demo.Response.IEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class UserEntity implements UserDetails, IEmpty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=null;
    private String nickName=null;
    private String birthDay=null;
    private String name=null;
    private String fullName=null;
    private String expoPushToken=null;
    private String story=null;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT '/anonymous_avatar.png'")
    private String avatar;
    @JsonIgnore
    private String password=null;
    @ManyToMany(mappedBy = "likedUsers")
    @JsonIgnoreProperties("likedUsers")
    private Set<PostEntity> posts=new HashSet<>();
    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles=new HashSet<RoleEntity>();
    public UserEntity(Long id, String name, String birthDay, String story, String nickName, String password, Set<RoleEntity> roles) {
        this.id = id;
        this.nickName = nickName;
        this.story = story;
        this.name=name;
        this.birthDay = birthDay;
        this.password = password;
        this.roles = roles;
    }
    public UserEntity(String name, String password) {
        this.name=name;
        this.password=password;
    }

    @JsonIgnore
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
