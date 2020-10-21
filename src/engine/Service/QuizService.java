package engine.Service;

import engine.Entities.*;
import engine.Repository.AnswerRepository;
import engine.Repository.OptionsRepository;
import engine.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private OptionsRepository optionsRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public ResponseEntity<QuizBody> addQuiz(QuizBody quizBody) {
        //Quiz quiz = quizBody.getQuizObject();
        Quiz quiz =  quizRepository.save(quizBody.getQuizObject());
        quiz.getOptions().forEach(optionsRepository::save);
        quiz.getAnswer().forEach(answerRepository::save);

        return ResponseEntity.ok(quiz.getQuizBodyObject());
    }

    public ResponseEntity<QuizCheck> solveQuiz(long id, QuizAnswer answer) { //throws URISyntaxException {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        } else {
            if ((answer.getAnswer().containsAll(quiz.getAnswer().stream().map(Answer::getAnswer).collect(Collectors.toList()))
                    && answer.getAnswer().size() == quiz.getAnswer().size())) {
                //return ResponseEntity.ok(new QuizCheck(true, "Congratulations, you're right!"));
                return ResponseEntity.ok(QuizCheck.CORRECT);
            }
            //return ResponseEntity.ok(new QuizCheck(false, "Wrong answer! Please, try again."));
            return ResponseEntity.ok(QuizCheck.WRONG);
        }
    }

    public ResponseEntity<QuizBody> getQuiz(long id) { //throws URISyntaxException {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        if (quiz == null) { //!!!!!!!!!!!!!!!! To Check!!!!!!
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(quiz.getQuizBodyObject());
        }
    }

    public ResponseEntity<List<QuizBody>> getAllQuizzes(){
        ArrayList<QuizBody> list = new ArrayList();
        quizRepository.findAll().forEach(e -> list.add(e.getQuizBodyObject()));
        return ResponseEntity.ok(list);
    }
}
