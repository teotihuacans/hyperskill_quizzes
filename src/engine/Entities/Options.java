package engine.Entities;

import engine.Entities.*;

import javax.persistence.*;
/*import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;*/

@Deprecated
@Entity
//@Getter @Setter @NoArgsConstructor
@Table(name = "options")
public class Options {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "optionsID")
    //@JsonIgnore
    private long id;

    @Column(name = "options")
    private String options;

    @ManyToOne
    @JoinColumn(name = "quizID")
    private Quiz quiz;

    protected Options() {}

    public Options(String option, Quiz quiz) {
        this.options = option;
        this.quiz = quiz;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String option) {
        this.options = option;
    }
}
