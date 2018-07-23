package com.gmail.shnapi007.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token {

  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String token;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user")
  private User user;

  // @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime expiryDate;

  private boolean wasUsed;

  private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expiryDate = now.plusMinutes(expiryTimeInMinutes);
    return expiryDate;
  }

  public Token(String token, User user) {
    this.token = token;
    this.user = user;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

  public Token() {
  }

  public static int getEXPIRATION() {
    return EXPIRATION;
  }

  public Long getId() {
    return id;
  }

  public String getToken() {
    return token;
  }

  public User getUser() {
    return user;
  }

  public LocalDateTime getExpiryDate() {
    return expiryDate;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setExpiryDate(LocalDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }

  public boolean isWasUsed() {
    return wasUsed;
  }

  public void setWasUsed(boolean wasUsed) {
    this.wasUsed = wasUsed;
  }
}
