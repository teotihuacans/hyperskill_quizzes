package engine.Entities;

import java.util.ArrayList;
import java.util.List;

public class QuizAnswer {
    private List<Integer> answer = new ArrayList<Integer>();

    public QuizAnswer() {}

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }
}
