package tech.jhipster.sample.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static tech.jhipster.sample.config.Constants.ID_DELIMITER;

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
    @Query("#{#n1ql.selectEntity} WHERE LOWER(email) = LOWER($1) AND #{#n1ql.filter}")
    Optional<User> findOneByEmailIgnoreCase(String email);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    default Optional<User> findOneByLogin(String login) {
        return findById(User.PREFIX + ID_DELIMITER + login);
    }

    @Override
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<User> findAll();

    @Override
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Page<User> findAll(Pageable pageable);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Page<User> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND meta(#{#n1ql.bucket}).id is not null AND activated = true")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<User> findAllByIdNotNullAndActivatedIsTrue();

    @Query("SELECT COUNT(*) as count FROM #{#n1ql.bucket} WHERE #{#n1ql.filter} AND meta(#{#n1ql.bucket}).id is not null AND activated = true")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Long countAllByIdNotNullAndActivatedIsTrue();
}
