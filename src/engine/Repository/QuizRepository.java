package engine.Repository;

/*
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

    //List<Quiz> findByTitle(String title);
    //Quiz findById(long id);
}*/

import engine.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
