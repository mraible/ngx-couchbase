package tech.jhipster.sample.repository;

import static tech.jhipster.sample.config.Constants.ID_DELIMITER;

import com.couchbase.client.java.query.QueryScanConsistency;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.User;

/**
 * Spring Data Couchbase repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {
    // @ScanConsistency is to fix index issues with Spring Data Couchbase
    // https://github.com/spring-projects/spring-data-couchbase/issues/897

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<User> findOneByActivationKey(String activationKey);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<User> findOneByResetKey(String resetKey);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<User> findOneByEmailIgnoreCase(String email);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    default Optional<User> findOneByLogin(String login) {
        return findById(User.PREFIX + ID_DELIMITER + login);
    }

    @Override
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<User> findAll();

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Page<User> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);
}
