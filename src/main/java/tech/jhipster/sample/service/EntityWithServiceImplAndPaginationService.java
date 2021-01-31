package tech.jhipster.sample.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.jhipster.sample.domain.EntityWithServiceImplAndPagination;

/**
 * Service Interface for managing {@link EntityWithServiceImplAndPagination}.
 */
public interface EntityWithServiceImplAndPaginationService {
    /**
     * Save a entityWithServiceImplAndPagination.
     *
     * @param entityWithServiceImplAndPagination the entity to save.
     * @return the persisted entity.
     */
    EntityWithServiceImplAndPagination save(EntityWithServiceImplAndPagination entityWithServiceImplAndPagination);

    /**
     * Partially updates a entityWithServiceImplAndPagination.
     *
     * @param entityWithServiceImplAndPagination the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EntityWithServiceImplAndPagination> partialUpdate(EntityWithServiceImplAndPagination entityWithServiceImplAndPagination);

    /**
     * Get all the entityWithServiceImplAndPaginations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EntityWithServiceImplAndPagination> findAll(Pageable pageable);

    /**
     * Get the "id" entityWithServiceImplAndPagination.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityWithServiceImplAndPagination> findOne(String id);

    /**
     * Delete the "id" entityWithServiceImplAndPagination.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
