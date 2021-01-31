package tech.jhipster.sample.service;

import java.util.List;
import java.util.Optional;
import tech.jhipster.sample.service.dto.EntityWithServiceImplAndDTODTO;

/**
 * Service Interface for managing {@link tech.jhipster.sample.domain.EntityWithServiceImplAndDTO}.
 */
public interface EntityWithServiceImplAndDTOService {
    /**
     * Save a entityWithServiceImplAndDTO.
     *
     * @param entityWithServiceImplAndDTODTO the entity to save.
     * @return the persisted entity.
     */
    EntityWithServiceImplAndDTODTO save(EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO);

    /**
     * Partially updates a entityWithServiceImplAndDTO.
     *
     * @param entityWithServiceImplAndDTODTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EntityWithServiceImplAndDTODTO> partialUpdate(EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO);

    /**
     * Get all the entityWithServiceImplAndDTOS.
     *
     * @return the list of entities.
     */
    List<EntityWithServiceImplAndDTODTO> findAll();

    /**
     * Get the "id" entityWithServiceImplAndDTO.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityWithServiceImplAndDTODTO> findOne(String id);

    /**
     * Delete the "id" entityWithServiceImplAndDTO.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
