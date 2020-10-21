package engine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import engine.Entities.Answer;

@Deprecated
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
