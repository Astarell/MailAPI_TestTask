package mailapi.testmailapi.services;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @param <T> View for entity
 * @param <S> Entity
 */
@NoRepositoryBean
public interface BasicServiceOptions<T, S> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void add(S entity);
    void update(S patchEntity);
    void deleteById(Long id);
    void delete(S entity);
}
