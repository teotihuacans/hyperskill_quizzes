package engine.Controller;

import engine.Entities.*;
import engine.Service.QuizServiceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    //private QuizService quizService;
    private QuizServiceNew quizService;

    /*public QuizController() {
    }*/

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity registerUser(@Valid @RequestBody User user) {
        return quizService.registerUser(user);
    }


    @PostMapping(value = "/quizzes", consumes = "application/json")
    public ResponseEntity<QuizEnt> addQuiz(@Valid @RequestBody QuizEnt quizBody) {
        return quizService.addQuiz(quizBody);
    }

    @PostMapping(value = "/quizzes/{id}/solve", consumes = "application/json")
    public ResponseEntity<QuizCheck> solveQuiz(@PathVariable long id, @RequestBody QuizAnswer answer) {
            return quizService.solveQuiz(id, answer);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<QuizEnt> getQuiz(@PathVariable long id) { //throws URISyntaxException {
        return quizService.getQuiz(id);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<Page<QuizEnt>> getAllQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        /*List<QuizEnt> list = quizService.getAllQuizzes(page, pageSize);
        return new ResponseEntity<List<QuizEnt>>(list, new HttpHeaders(), HttpStatus.OK);*/
        return quizService.getAllQuizzes(page, pageSize);
    }

    @GetMapping("/quizzes/completed")
    public ResponseEntity<Page<QuizCompletedEnt>> getAllQuizzesCompleted(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        return quizService.getAllQuizzesCompleted(page, pageSize);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable Long id) {
        return quizService.deleteQuiz(id);
    }


}