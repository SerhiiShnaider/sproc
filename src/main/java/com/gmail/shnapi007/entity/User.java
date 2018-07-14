package com.gmail.shnapi007.entity;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true)
  private Long id;

  @NotBlank
  @Column(unique = true)
  private String username;

  @NotBlank
  private String password;

  private Role role = Role.ROLE_USER;

  private boolean accountNonExpired = true;

  private boolean accountNonLocked = true;

  private boolean credentialsNonExpired = true;

  private boolean enabled;

  public User() {

  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.role.name()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("username", username)
        .add("password", password)
        .add("role", role)
        .add("accountNonExpired", accountNonExpired)
        .add("accountNonLocked", accountNonLocked)
        .add("credentialsNonExpired", credentialsNonExpired)
        .add("enabled", enabled)
        .toString();
  }
}
