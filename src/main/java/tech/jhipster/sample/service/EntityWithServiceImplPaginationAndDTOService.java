package tech.jhipster.sample.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.jhipster.sample.service.dto.EntityWithServiceImplPaginationAndDTODTO;

/**
 * Service Interface for managing {@link tech.jhipster.sample.domain.EntityWithServiceImplPaginationAndDTO}.
 */
public interface EntityWithServiceImplPaginationAndDTOService {
    /**
     * Save a entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entity to save.
     * @return the persisted entity.
     */
    EntityWithServiceImplPaginationAndDTODTO save(EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO);

    /**
     * Partially updates a entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EntityWithServiceImplPaginationAndDTODTO> partialUpdate(
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO
    );

    /**
     * Get all the entityWithServiceImplPaginationAndDTOS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EntityWithServiceImplPaginationAndDTODTO> findAll(Pageable pageable);

    /**
     * Get the "id" entityWithServiceImplPaginationAndDTO.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityWithServiceImplPaginationAndDTODTO> findOne(String id);

    /**
     * Delete the "id" entityWithServiceImplPaginationAndDTO.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
