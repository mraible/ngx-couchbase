package tech.jhipster.sample.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.jhipster.sample.domain.EntityWithServiceClassPaginationAndDTO;
import tech.jhipster.sample.repository.EntityWithServiceClassPaginationAndDTORepository;
import tech.jhipster.sample.service.dto.EntityWithServiceClassPaginationAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithServiceClassPaginationAndDTOMapper;

/**
 * Service Implementation for managing {@link EntityWithServiceClassPaginationAndDTO}.
 */
@Service
public class EntityWithServiceClassPaginationAndDTOService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceClassPaginationAndDTOService.class);

    private final EntityWithServiceClassPaginationAndDTORepository entityWithServiceClassPaginationAndDTORepository;

    private final EntityWithServiceClassPaginationAndDTOMapper entityWithServiceClassPaginationAndDTOMapper;

    public EntityWithServiceClassPaginationAndDTOService(
        EntityWithServiceClassPaginationAndDTORepository entityWithServiceClassPaginationAndDTORepository,
        EntityWithServiceClassPaginationAndDTOMapper entityWithServiceClassPaginationAndDTOMapper
    ) {
        this.entityWithServiceClassPaginationAndDTORepository = entityWithServiceClassPaginationAndDTORepository;
        this.entityWithServiceClassPaginationAndDTOMapper = entityWithServiceClassPaginationAndDTOMapper;
    }

    /**
     * Save a entityWithServiceClassPaginationAndDTO.
     *
     * @param entityWithServiceClassPaginationAndDTODTO the entity to save.
     * @return the persisted entity.
     */
    public EntityWithServiceClassPaginationAndDTODTO save(
        EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO
    ) {
        log.debug("Request to save EntityWithServiceClassPaginationAndDTO : {}", entityWithServiceClassPaginationAndDTODTO);
        EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTOMapper.toEntity(
            entityWithServiceClassPaginationAndDTODTO
        );
        entityWithServiceClassPaginationAndDTO =
            entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);
        return entityWithServiceClassPaginationAndDTOMapper.toDto(entityWithServiceClassPaginationAndDTO);
    }

    /**
     * Partially update a entityWithServiceClassPaginationAndDTO.
     *
     * @param entityWithServiceClassPaginationAndDTODTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityWithServiceClassPaginationAndDTODTO> partialUpdate(
        EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO
    ) {
        log.debug("Request to partially update EntityWithServiceClassPaginationAndDTO : {}", entityWithServiceClassPaginationAndDTODTO);

        return entityWithServiceClassPaginationAndDTORepository
            .findById(entityWithServiceClassPaginationAndDTODTO.getId())
            .map(
                existingEntityWithServiceClassPaginationAndDTO -> {
                    if (entityWithServiceClassPaginationAndDTODTO.getLena() != null) {
                        existingEntityWithServiceClassPaginationAndDTO.setLena(entityWithServiceClassPaginationAndDTODTO.getLena());
                    }

                    return existingEntityWithServiceClassPaginationAndDTO;
                }
            )
            .map(entityWithServiceClassPaginationAndDTORepository::save)
            .map(entityWithServiceClassPaginationAndDTOMapper::toDto);
    }

    /**
     * Get all the entityWithServiceClassPaginationAndDTOS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<EntityWithServiceClassPaginationAndDTODTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceClassPaginationAndDTOS");
        return entityWithServiceClassPaginationAndDTORepository.findAll(pageable).map(entityWithServiceClassPaginationAndDTOMapper::toDto);
    }

    /**
     * Get one entityWithServiceClassPaginationAndDTO by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<EntityWithServiceClassPaginationAndDTODTO> findOne(String id) {
        log.debug("Request to get EntityWithServiceClassPaginationAndDTO : {}", id);
        return entityWithServiceClassPaginationAndDTORepository.findById(id).map(entityWithServiceClassPaginationAndDTOMapper::toDto);
    }

    /**
     * Delete the entityWithServiceClassPaginationAndDTO by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EntityWithServiceClassPaginationAndDTO : {}", id);
        entityWithServiceClassPaginationAndDTORepository.deleteById(id);
    }
}
