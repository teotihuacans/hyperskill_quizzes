package engine.Repository;

import engine.Entities.QuizEnt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizEntRepository extends PagingAndSortingRepository<QuizEnt, Long> {
    @Query(
            value = "SELECT * FROM QUIZ",
            countQuery = "SELECT count(*) FROM QUIZ",
            nativeQuery = true)
    Page<QuizEnt> findAllQuizzesWithPagination(Pageable pageable);
}
