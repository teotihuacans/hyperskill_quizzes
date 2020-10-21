package engine.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="userID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message="email is mandatory")
    //@Email
    @Pattern(regexp = ".+@.+\\..+", message = "invalid email format")
    @Column(unique=true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message="password is mandatory")
    @Size(min=5)
    private String password;

    public User() {}

    /*public User(String email, String password) {
        this.email = email;
        //setPassword(password);
        this.password = new BCryptPasswordEncoder().encode(password);
    }*/

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        //this.password = new BCryptPasswordEncoder().encode(password);
        this.password = password;
    }

    public void encryptPassword() {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
