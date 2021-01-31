package tech.jhipster.sample.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import java.util.List;
import java.util.Optional;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.Place;

/**
 * Spring Data Couchbase repository for the Place entity.
 */
@Repository
public interface PlaceRepository extends CouchbaseRepository<Place, String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Page<Place> findAllWithEagerRelationships(Pageable pageable);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<Place> findAllWithEagerRelationships();

    @Query("#{#n1ql.selectEntity} USE KEYS $1 WHERE #{#n1ql.filter}")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<Place> findOneWithEagerRelationships(String id);

    @Override
    // Add ScanConsistency to fix issue with Spring Data Couchbase
    // https://github.com/spring-projects/spring-data-couchbase/issues/897
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<Place> findAll();

    @Override
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<Place> findAll(Sort sort);

    @Override
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Page<Place> findAll(Pageable pageable);
}
