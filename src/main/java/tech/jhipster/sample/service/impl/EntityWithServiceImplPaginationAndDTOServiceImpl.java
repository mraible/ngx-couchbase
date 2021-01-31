package tech.jhipster.sample.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.jhipster.sample.domain.EntityWithServiceImplPaginationAndDTO;
import tech.jhipster.sample.repository.EntityWithServiceImplPaginationAndDTORepository;
import tech.jhipster.sample.service.EntityWithServiceImplPaginationAndDTOService;
import tech.jhipster.sample.service.dto.EntityWithServiceImplPaginationAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithServiceImplPaginationAndDTOMapper;

/**
 * Service Implementation for managing {@link EntityWithServiceImplPaginationAndDTO}.
 */
@Service
public class EntityWithServiceImplPaginationAndDTOServiceImpl implements EntityWithServiceImplPaginationAndDTOService {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplPaginationAndDTOServiceImpl.class);

    private final EntityWithServiceImplPaginationAndDTORepository entityWithServiceImplPaginationAndDTORepository;

    private final EntityWithServiceImplPaginationAndDTOMapper entityWithServiceImplPaginationAndDTOMapper;

    public EntityWithServiceImplPaginationAndDTOServiceImpl(
        EntityWithServiceImplPaginationAndDTORepository entityWithServiceImplPaginationAndDTORepository,
        EntityWithServiceImplPaginationAndDTOMapper entityWithServiceImplPaginationAndDTOMapper
    ) {
        this.entityWithServiceImplPaginationAndDTORepository = entityWithServiceImplPaginationAndDTORepository;
        this.entityWithServiceImplPaginationAndDTOMapper = entityWithServiceImplPaginationAndDTOMapper;
    }

    @Override
    public EntityWithServiceImplPaginationAndDTODTO save(
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO
    ) {
        log.debug("Request to save EntityWithServiceImplPaginationAndDTO : {}", entityWithServiceImplPaginationAndDTODTO);
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOMapper.toEntity(
            entityWithServiceImplPaginationAndDTODTO
        );
        entityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);
        return entityWithServiceImplPaginationAndDTOMapper.toDto(entityWithServiceImplPaginationAndDTO);
    }

    @Override
    public Optional<EntityWithServiceImplPaginationAndDTODTO> partialUpdate(
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO
    ) {
        log.debug("Request to partially update EntityWithServiceImplPaginationAndDTO : {}", entityWithServiceImplPaginationAndDTODTO);

        return entityWithServiceImplPaginationAndDTORepository
            .findById(entityWithServiceImplPaginationAndDTODTO.getId())
            .map(
                existingEntityWithServiceImplPaginationAndDTO -> {
                    if (entityWithServiceImplPaginationAndDTODTO.getTheo() != null) {
                        existingEntityWithServiceImplPaginationAndDTO.setTheo(entityWithServiceImplPaginationAndDTODTO.getTheo());
                    }

                    return existingEntityWithServiceImplPaginationAndDTO;
                }
            )
            .map(entityWithServiceImplPaginationAndDTORepository::save)
            .map(entityWithServiceImplPaginationAndDTOMapper::toDto);
    }

    @Override
    public Page<EntityWithServiceImplPaginationAndDTODTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntityWithServiceImplPaginationAndDTOS");
        return entityWithServiceImplPaginationAndDTORepository.findAll(pageable).map(entityWithServiceImplPaginationAndDTOMapper::toDto);
    }

    @Override
    public Optional<EntityWithServiceImplPaginationAndDTODTO> findOne(String id) {
        log.debug("Request to get EntityWithServiceImplPaginationAndDTO : {}", id);
        return entityWithServiceImplPaginationAndDTORepository.findById(id).map(entityWithServiceImplPaginationAndDTOMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete EntityWithServiceImplPaginationAndDTO : {}", id);
        entityWithServiceImplPaginationAndDTORepository.deleteById(id);
    }
}
