package tech.jhipster.sample.service;

import java.util.List;
import java.util.Optional;
import tech.jhipster.sample.domain.FieldTestServiceImplEntity;

/**
 * Service Interface for managing {@link FieldTestServiceImplEntity}.
 */
public interface FieldTestServiceImplEntityService {
    /**
     * Save a fieldTestServiceImplEntity.
     *
     * @param fieldTestServiceImplEntity the entity to save.
     * @return the persisted entity.
     */
    FieldTestServiceImplEntity save(FieldTestServiceImplEntity fieldTestServiceImplEntity);

    /**
     * Partially updates a fieldTestServiceImplEntity.
     *
     * @param fieldTestServiceImplEntity the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FieldTestServiceImplEntity> partialUpdate(FieldTestServiceImplEntity fieldTestServiceImplEntity);

    /**
     * Get all the fieldTestServiceImplEntities.
     *
     * @return the list of entities.
     */
    List<FieldTestServiceImplEntity> findAll();

    /**
     * Get the "id" fieldTestServiceImplEntity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FieldTestServiceImplEntity> findOne(String id);

    /**
     * Delete the "id" fieldTestServiceImplEntity.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
