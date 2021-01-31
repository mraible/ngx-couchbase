package tech.jhipster.sample.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.jhipster.sample.domain.EntityWithServiceClassAndPagination;
import tech.jhipster.sample.repository.EntityWithServiceClassAndPaginationRepository;

/**
 * Service Implementation for managing {@link EntityWithServiceClassAndPagination}.
 */
@Service
public class EntityWithServiceClassAndPaginationService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceClassAndPaginationService.class);

    private final EntityWithServiceClassAndPaginationRepository entityWithServiceClassAndPaginationRepository;

    public EntityWithServiceClassAndPaginationService(
        EntityWithServiceClassAndPaginationRepository entityWithServiceClassAndPaginationRepository
    ) {
        this.entityWithServiceClassAndPaginationRepository = entityWithServiceClassAndPaginationRepository;
    }

    /**
     * Save a entityWithServiceClassAndPagination.
     *
     * @param entityWithServiceClassAndPagination the entity to save.
     * @return the persisted entity.
     */
    public EntityWithServiceClassAndPagination save(EntityWithServiceClassAndPagination entityWithServiceClassAndPagination) {
        log.debug("Request to save EntityWithServiceClassAndPagination : {}", entityWithServiceClassAndPagination);
        return entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);
    }

    /**
     * Partially update a entityWithServiceClassAndPagination.
     *
     * @param entityWithServiceClassAndPagination the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityWithServiceClassAndPagination> partialUpdate(
        EntityWithServiceClassAndPagination entityWithServiceClassAndPagination
    ) {
        log.debug("Request to partially update EntityWithServiceClassAndPagination : {}", entityWithServiceClassAndPagination);

        return entityWithServiceClassAndPaginationRepository
            .findById(entityWithServiceClassAndPagination.getId())
            .map(
                existingEntityWithServiceClassAndPagination -> {
                    if (entityWithServiceClassAndPagination.getEnzo() != null) {
                        existingEntityWithServiceClassAndPagination.setEnzo(entityWithServiceClassAndPagination.getEnzo());
                    }

                    return existingEntityWithServiceClassAndPagination;
                }
            )
            .map(entityWithServiceClassAndPaginationRepository::save);
    }

    /**
     * Get all the entityWithServiceClassAndPaginations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<EntityWithServiceClassAndPagination> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceClassAndPaginations");
        return entityWithServiceClassAndPaginationRepository.findAll(pageable);
    }

    /**
     * Get one entityWithServiceClassAndPagination by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<EntityWithServiceClassAndPagination> findOne(String id) {
        log.debug("Request to get EntityWithServiceClassAndPagination : {}", id);
        return entityWithServiceClassAndPaginationRepository.findById(id);
    }

    /**
     * Delete the entityWithServiceClassAndPagination by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EntityWithServiceClassAndPagination : {}", id);
        entityWithServiceClassAndPaginationRepository.deleteById(id);
    }
}
