package engine.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class QuizCompletedEnt {

    @JsonIgnore
    @Id
    @Column(name = "quizcomplID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("id")
    @Column(name = "quizID")
    private Long quizID;

    @JsonProperty("completedAt")
    @Column(name = "completedAt", updatable = false) //columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    //@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss") //Calendar
    private Date completedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @JsonIgnore
    @Column(name = "result")
    private Boolean result;

    public QuizCompletedEnt() {}

    public void setQuizID(Long quizID) {
        this.quizID = quizID;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public Long getQuizID() {
        return quizID;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public User getUser() {
        return user;
    }

    public Boolean getResult() {
        return result;
    }
}
