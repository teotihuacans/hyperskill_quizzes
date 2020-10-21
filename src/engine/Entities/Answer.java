package engine.Entities;

/*import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;*/

import engine.Entities.*;

import javax.persistence.*;

@Deprecated
@Entity
/*@Getter
@Setter
@NoArgsConstructor*/
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "answerID")
    private long id;

    @Column(name = "answer")
    private Integer answer;

    @ManyToOne
    @JoinColumn(name = "quizID")
    private Quiz quiz;

    protected Answer() {}

    public Answer(Integer answer, Quiz quiz) {
        this.answer = answer;
        this.quiz = quiz;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
