package tech.jhipster.sample.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import java.util.List;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import tech.jhipster.sample.domain.EntityWithServiceImplAndDTO;

/**
 * Spring Data Couchbase repository for the EntityWithServiceImplAndDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityWithServiceImplAndDTORepository extends CouchbaseRepository<EntityWithServiceImplAndDTO, String> {
    @Override
    // Add ScanConsistency to fix issue with Spring Data Couchbase
    // https://github.com/spring-projects/spring-data-couchbase/issues/897
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<EntityWithServiceImplAndDTO> findAll();

    @Override
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<EntityWithServiceImplAndDTO> findAll(Sort sort);

    @Override
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Page<EntityWithServiceImplAndDTO> findAll(Pageable pageable);
}
