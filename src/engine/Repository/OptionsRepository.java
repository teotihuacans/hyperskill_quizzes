package engine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import engine.Entities.Options;

@Deprecated
@Repository
public interface OptionsRepository extends JpaRepository<Options, Long> {

}
