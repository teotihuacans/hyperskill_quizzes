package engine.Entities;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.Entities.*;

//@Getter @Setter
//@NoArgsConstructor //!!! Not works!!
//@AllArgsConstructor
public class QuizBody {
    private Long id;

    @NotBlank(message="title is mandatory")
    private String title;

    @NotBlank(message="text is mandatory")
    private String text;

    @NotEmpty(message="options is mandatory")
    @Size(min = 2)
    private List<String> options = new ArrayList<>(); //String[] options;

    private List<Integer> answer = new ArrayList<>();

    /*
    * 1. Ignore for answer
    * 2. What if input null? */

    public QuizBody() {
    }

    public QuizBody(Long id, String title, String text, List<Options> options, List<Answer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        options.forEach(e -> this.options.add(e.getOptions()));
        answer.forEach(e -> this.answer.add(e.getAnswer()));
    }

    @JsonIgnore
    public Quiz getQuizObject() {
        return new Quiz(this.title, this.text, this.options, this.answer);
    }

    /*@JsonIgnore
    public QuizBody getQuizBodyObject(Quiz quiz) {
        return new QuizBody(quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer());
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

    @JsonProperty(value = "answer")
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
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

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer;
    }
}
