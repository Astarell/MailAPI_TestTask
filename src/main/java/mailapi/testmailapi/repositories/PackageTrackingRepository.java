package mailapi.testmailapi.repositories;

import mailapi.testmailapi.models.PackageTracking;
import mailapi.testmailapi.projections.PackageTrackingView;
import mailapi.testmailapi.projections.PostageView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface PackageTrackingRepository extends CrudRepository<PackageTracking, Long> {

    @Query("from PackageTracking")
    Iterable<PackageTrackingView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
}
