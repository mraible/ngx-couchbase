package tech.jhipster.sample.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.jhipster.sample.domain.EntityWithServiceImplAndPagination;
import tech.jhipster.sample.repository.EntityWithServiceImplAndPaginationRepository;
import tech.jhipster.sample.service.EntityWithServiceImplAndPaginationService;

/**
 * Service Implementation for managing {@link EntityWithServiceImplAndPagination}.
 */
@Service
public class EntityWithServiceImplAndPaginationServiceImpl implements EntityWithServiceImplAndPaginationService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplAndPaginationServiceImpl.class);

    private final EntityWithServiceImplAndPaginationRepository entityWithServiceImplAndPaginationRepository;

    public EntityWithServiceImplAndPaginationServiceImpl(
        EntityWithServiceImplAndPaginationRepository entityWithServiceImplAndPaginationRepository
    ) {
        this.entityWithServiceImplAndPaginationRepository = entityWithServiceImplAndPaginationRepository;
    }

    @Override
    public EntityWithServiceImplAndPagination save(EntityWithServiceImplAndPagination entityWithServiceImplAndPagination) {
        log.debug("Request to save EntityWithServiceImplAndPagination : {}", entityWithServiceImplAndPagination);
        return entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);
    }

    @Override
    public Optional<EntityWithServiceImplAndPagination> partialUpdate(
        EntityWithServiceImplAndPagination entityWithServiceImplAndPagination
    ) {
        log.debug("Request to partially update EntityWithServiceImplAndPagination : {}", entityWithServiceImplAndPagination);

        return entityWithServiceImplAndPaginationRepository
            .findById(entityWithServiceImplAndPagination.getId())
            .map(
                existingEntityWithServiceImplAndPagination -> {
                    if (entityWithServiceImplAndPagination.getHugo() != null) {
                        existingEntityWithServiceImplAndPagination.setHugo(entityWithServiceImplAndPagination.getHugo());
                    }

                    return existingEntityWithServiceImplAndPagination;
                }
            )
            .map(entityWithServiceImplAndPaginationRepository::save);
    }

    @Override
    public Page<EntityWithServiceImplAndPagination> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceImplAndPaginations");
        return entityWithServiceImplAndPaginationRepository.findAll(pageable);
    }

    @Override
    public Optional<EntityWithServiceImplAndPagination> findOne(String id) {
        log.debug("Request to get EntityWithServiceImplAndPagination : {}", id);
        return entityWithServiceImplAndPaginationRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete EntityWithServiceImplAndPagination : {}", id);
        entityWithServiceImplAndPaginationRepository.deleteById(id);
    }
}
