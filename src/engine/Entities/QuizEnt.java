package engine.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.Service.QuizServiceNew;
import engine.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
public class QuizEnt {

    @Id
    @Column(name = "quizID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "text is mandatory")
    private String text;

    @NotEmpty(message = "options are mandatory")
    @Size(min = 2)
    @ElementCollection
    private List<String> options = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private List<Integer> answer = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public QuizEnt() {
    }

    /*public QuizEnt(Long id, String title, String text, List<Options> options, List<Answer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        options.forEach(e -> this.options.add(e.getOptions()));
        answer.forEach(e -> this.answer.add(e.getAnswer()));
    }*/

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    //@JsonProperty(value = "answer")
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    //@JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }

    public User getUser() {
        return user;
    }
}
