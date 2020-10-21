package engine.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QuizCheck {

    CORRECT( true, "Congratulations, you're right!"),
    WRONG(false, "Wrong answer! Please, try again.");

    private boolean success;
    private String feedback;


    QuizCheck(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    /*public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }*/

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
