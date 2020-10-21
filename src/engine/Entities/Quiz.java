package engine.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import engine.Entities.Answer;
import engine.Entities.Options;
import engine.Entities.QuizBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Deprecated
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz")
public class Quiz {

    @Id
    @Column(name="quizID")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //AUTO
    private Long id;

    @Column
    private String title;


    @Column
    private String text;

    @Column
    @OneToMany(mappedBy="quiz")
    private List<Options> options = new ArrayList<>(); //String[] options;
    /*private List<Options> options = mapper.reader()
            .forType(new TypeReference<List<Options>>() {})
            .readValue()
            ;*/

    @Column
    @OneToMany(mappedBy="quiz")
    private List<Answer> answer = new ArrayList<>();

    /*protected Quiz() {
    }*/

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        //this.options.addAll(options);
        options.forEach(e -> this.options.add(new Options(e, this)));
        //this.answer.addAll(answer);
        answer.forEach(e -> this.answer.add(new Answer(e, this)));
    }

    public QuizBody getQuizBodyObject() {
        return new QuizBody(this.id, this.getTitle(), this.getText(), this.getOptions(), this.getAnswer());
    }

    /*public void setId(Integer id) {
        this.id = id;
    }*/

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(List<Options> options) {
        this.options.addAll(options);
    }

    public void setAnswer(List<Answer> answer) {
        this.answer.addAll(answer);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<Options> getOptions() {
        return options;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public Long getId() {
        return id;
    }

    //equals() and hashCode() methods ???
}
