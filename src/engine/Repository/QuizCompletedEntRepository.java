package engine.Repository;

import engine.Entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletedEntRepository extends PagingAndSortingRepository<QuizCompletedEnt, Long> {
    @Query(
            value = "SELECT * FROM QUIZ_COMPLETED_ENT WHERE USERID = :userid AND RESULT = :result",
            countQuery = "SELECT count(*) FROM QUIZ_COMPLETED_ENT",
            nativeQuery = true)
    Page<QuizCompletedEnt> findAllQuizzesCompletedWithPagination(@Param("userid") Long userId,
                                                                 @Param("result") Boolean result,
                                                                 Pageable pageable);
}
