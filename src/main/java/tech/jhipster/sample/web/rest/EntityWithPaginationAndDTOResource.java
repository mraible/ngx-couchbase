package tech.jhipster.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.sample.domain.EntityWithPaginationAndDTO;
import tech.jhipster.sample.repository.EntityWithPaginationAndDTORepository;
import tech.jhipster.sample.service.dto.EntityWithPaginationAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithPaginationAndDTOMapper;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityWithPaginationAndDTO}.
 */
@RestController
@RequestMapping("/api")
public class EntityWithPaginationAndDTOResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithPaginationAndDTOResource.class);

    private static final String ENTITY_NAME = "entityWithPaginationAndDTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityWithPaginationAndDTORepository entityWithPaginationAndDTORepository;

    private final EntityWithPaginationAndDTOMapper entityWithPaginationAndDTOMapper;

    public EntityWithPaginationAndDTOResource(
        EntityWithPaginationAndDTORepository entityWithPaginationAndDTORepository,
        EntityWithPaginationAndDTOMapper entityWithPaginationAndDTOMapper
    ) {
        this.entityWithPaginationAndDTORepository = entityWithPaginationAndDTORepository;
        this.entityWithPaginationAndDTOMapper = entityWithPaginationAndDTOMapper;
    }

    /**
     * {@code POST  /entity-with-pagination-and-dtos} : Create a new entityWithPaginationAndDTO.
     *
     * @param entityWithPaginationAndDTODTO the entityWithPaginationAndDTODTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityWithPaginationAndDTODTO, or with status {@code 400 (Bad Request)} if the entityWithPaginationAndDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-with-pagination-and-dtos")
    public ResponseEntity<EntityWithPaginationAndDTODTO> createEntityWithPaginationAndDTO(
        @RequestBody EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO
    ) throws URISyntaxException {
        log.debug("REST request to save EntityWithPaginationAndDTO : {}", entityWithPaginationAndDTODTO);
        if (entityWithPaginationAndDTODTO.getId() != null) {
            throw new BadRequestAlertException("A new entityWithPaginationAndDTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = entityWithPaginationAndDTOMapper.toEntity(entityWithPaginationAndDTODTO);
        entityWithPaginationAndDTO = entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);
        EntityWithPaginationAndDTODTO result = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);
        return ResponseEntity
            .created(new URI("/api/entity-with-pagination-and-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-with-pagination-and-dtos} : Updates an existing entityWithPaginationAndDTO.
     *
     * @param entityWithPaginationAndDTODTO the entityWithPaginationAndDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityWithPaginationAndDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityWithPaginationAndDTODTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityWithPaginationAndDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-with-pagination-and-dtos")
    public ResponseEntity<EntityWithPaginationAndDTODTO> updateEntityWithPaginationAndDTO(
        @RequestBody EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO
    ) throws URISyntaxException {
        log.debug("REST request to update EntityWithPaginationAndDTO : {}", entityWithPaginationAndDTODTO);
        if (entityWithPaginationAndDTODTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = entityWithPaginationAndDTOMapper.toEntity(entityWithPaginationAndDTODTO);
        entityWithPaginationAndDTO = entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);
        EntityWithPaginationAndDTODTO result = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityWithPaginationAndDTODTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /entity-with-pagination-and-dtos} : Updates given fields of an existing entityWithPaginationAndDTO.
     *
     * @param entityWithPaginationAndDTODTO the entityWithPaginationAndDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityWithPaginationAndDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityWithPaginationAndDTODTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityWithPaginationAndDTODTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityWithPaginationAndDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/entity-with-pagination-and-dtos", consumes = "application/merge-patch+json")
    public ResponseEntity<EntityWithPaginationAndDTODTO> partialUpdateEntityWithPaginationAndDTO(
        @RequestBody EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO
    ) throws URISyntaxException {
        log.debug("REST request to update EntityWithPaginationAndDTO partially : {}", entityWithPaginationAndDTODTO);
        if (entityWithPaginationAndDTODTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<EntityWithPaginationAndDTODTO> result = entityWithPaginationAndDTORepository
            .findById(entityWithPaginationAndDTODTO.getId())
            .map(
                existingEntityWithPaginationAndDTO -> {
                    if (entityWithPaginationAndDTODTO.getLea() != null) {
                        existingEntityWithPaginationAndDTO.setLea(entityWithPaginationAndDTODTO.getLea());
                    }

                    return existingEntityWithPaginationAndDTO;
                }
            )
            .map(entityWithPaginationAndDTORepository::save)
            .map(entityWithPaginationAndDTOMapper::toDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityWithPaginationAndDTODTO.getId())
        );
    }

    /**
     * {@code GET  /entity-with-pagination-and-dtos} : get all the entityWithPaginationAndDTOS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityWithPaginationAndDTOS in body.
     */
    @GetMapping("/entity-with-pagination-and-dtos")
    public ResponseEntity<List<EntityWithPaginationAndDTODTO>> getAllEntityWithPaginationAndDTOS(Pageable pageable) {
        log.debug("REST request to get a page of EntityWithPaginationAndDTOS");
        Page<EntityWithPaginationAndDTODTO> page = entityWithPaginationAndDTORepository
            .findAll(pageable)
            .map(entityWithPaginationAndDTOMapper::toDto);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /entity-with-pagination-and-dtos/:id} : get the "id" entityWithPaginationAndDTO.
     *
     * @param id the id of the entityWithPaginationAndDTODTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityWithPaginationAndDTODTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-with-pagination-and-dtos/{id}")
    public ResponseEntity<EntityWithPaginationAndDTODTO> getEntityWithPaginationAndDTO(@PathVariable String id) {
        log.debug("REST request to get EntityWithPaginationAndDTO : {}", id);
        Optional<EntityWithPaginationAndDTODTO> entityWithPaginationAndDTODTO = entityWithPaginationAndDTORepository
            .findById(id)
            .map(entityWithPaginationAndDTOMapper::toDto);
        return ResponseUtil.wrapOrNotFound(entityWithPaginationAndDTODTO);
    }

    /**
     * {@code DELETE  /entity-with-pagination-and-dtos/:id} : delete the "id" entityWithPaginationAndDTO.
     *
     * @param id the id of the entityWithPaginationAndDTODTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-with-pagination-and-dtos/{id}")
    public ResponseEntity<Void> deleteEntityWithPaginationAndDTO(@PathVariable String id) {
        log.debug("REST request to delete EntityWithPaginationAndDTO : {}", id);
        entityWithPaginationAndDTORepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
