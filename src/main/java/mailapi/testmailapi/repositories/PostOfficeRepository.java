package mailapi.testmailapi.repositories;

import mailapi.testmailapi.models.PostOffice;
import mailapi.testmailapi.projections.PostOfficeView;
import mailapi.testmailapi.projections.PostageView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostOfficeRepository extends CrudRepository<PostOffice, Long> {
    @Query("from PostOffice")
    Iterable<PostOfficeView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
}
