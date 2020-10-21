package engine.Service;

import engine.Entities.*;
import engine.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceNew {
    @Autowired
    private QuizEntRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizCompletedEntRepository quizCompletedRepository;

    public ResponseEntity registerUser(User user) { ///!!!! here and in controller exceptions!!!! Not validating user
        if (user.getEmail() != null && !user.getEmail().isEmpty() &&
                !user.getPassword().isEmpty() &&
                userRepository.findUserByEmail(user.getEmail()) == null) {
            user.encryptPassword();
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public User userLoggedIn() {
        return userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public ResponseEntity<QuizEnt> addQuiz(QuizEnt quizBody) {
        quizBody.setUser(this.userLoggedIn());
        quizRepository.save(quizBody);

        return ResponseEntity.ok(quizBody);
    }

    public ResponseEntity<QuizCheck> solveQuiz(long id, QuizAnswer answer) { //throws URISyntaxException {
        QuizEnt quiz = quizRepository.findById(id).orElse(null);
        QuizCompletedEnt quizCompleted = new QuizCompletedEnt();
        quizCompleted.setUser(this.userLoggedIn());
        quizCompleted.setQuizID(id);

        if (quiz == null) {
            return ResponseEntity.notFound().build();
        } else {
            if ((answer.getAnswer().containsAll(quiz.getAnswer())
                    && answer.getAnswer().size() == quiz.getAnswer().size())) {
                quizCompleted.setResult(true);
                quizCompletedRepository.save(quizCompleted);
                return ResponseEntity.ok(QuizCheck.CORRECT);
            }
            quizCompleted.setResult(false);
            quizCompletedRepository.save(quizCompleted);
            return ResponseEntity.ok(QuizCheck.WRONG);
        }
    }

    public ResponseEntity<QuizEnt> getQuiz(long id) { //throws URISyntaxException {
        QuizEnt quiz = quizRepository.findById(id).orElse(null);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(quiz);
        }
    }

    public ResponseEntity<Page<QuizEnt>> getAllQuizzes(Integer page, Integer pageSize) {

        Pageable paging = PageRequest.of(page, pageSize);
        Page<QuizEnt> pagedResult = quizRepository.findAll(paging);
        //Page<QuizEnt> pagedResult = quizRepository.findAllQuizzesWithPagination(paging);

        if(pagedResult.hasContent()) {
            return ResponseEntity.ok(pagedResult);
        } else {
            return ResponseEntity.ok(Page.empty());
        }
    }

    public ResponseEntity<Page<QuizCompletedEnt>> getAllQuizzesCompleted(Integer page, Integer pageSize) {

        Pageable paging = PageRequest.of(page, pageSize, Sort.by("COMPLETED_AT").descending());
        //Page<QuizCompletedEnt> pagedResult = quizCompletedRepository.findAll(paging);
        Page<QuizCompletedEnt> pagedResult = quizCompletedRepository
                .findAllQuizzesCompletedWithPagination(userLoggedIn().getId(), true, paging);

        if(pagedResult.hasContent()) {
            return ResponseEntity.ok(pagedResult);
        } else {
            return ResponseEntity.ok(Page.empty());
        }
    }

    public ResponseEntity deleteQuiz(Long id) {
        QuizEnt quiz = quizRepository.findById(id).orElse(null);
        if (quiz == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println();
        if (!quiz.getUser().equals(this.userLoggedIn())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        quizRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
