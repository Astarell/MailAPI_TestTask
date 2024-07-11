package mailapi.testmailapi.repositories;

import mailapi.testmailapi.models.Postage;
import mailapi.testmailapi.projections.PostageView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface PostageRepository extends CrudRepository<Postage, Long> {

    @Query("from Postage")
    Iterable<PostageView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
}
