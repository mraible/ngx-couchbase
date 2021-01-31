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
import tech.jhipster.sample.service.EntityWithServiceImplPaginationAndDTOService;
import tech.jhipster.sample.service.dto.EntityWithServiceImplPaginationAndDTODTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.EntityWithServiceImplPaginationAndDTO}.
 */
@RestController
@RequestMapping("/api")
public class EntityWithServiceImplPaginationAndDTOResource {

    private final Logger log = LoggerFactory.getLogger(EntityWithServiceImplPaginationAndDTOResource.class);

    private static final String ENTITY_NAME = "entityWithServiceImplPaginationAndDTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityWithServiceImplPaginationAndDTOService entityWithServiceImplPaginationAndDTOService;

    public EntityWithServiceImplPaginationAndDTOResource(
        EntityWithServiceImplPaginationAndDTOService entityWithServiceImplPaginationAndDTOService
    ) {
        this.entityWithServiceImplPaginationAndDTOService = entityWithServiceImplPaginationAndDTOService;
    }

    /**
     * {@code POST  /entity-with-service-impl-pagination-and-dtos} : Create a new entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entityWithServiceImplPaginationAndDTODTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityWithServiceImplPaginationAndDTODTO, or with status {@code 400 (Bad Request)} if the entityWithServiceImplPaginationAndDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-with-service-impl-pagination-and-dtos")
    public ResponseEntity<EntityWithServiceImplPaginationAndDTODTO> createEntityWithServiceImplPaginationAndDTO(
        @RequestBody EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO
    ) throws URISyntaxException {
        log.debug("REST request to save EntityWithServiceImplPaginationAndDTO : {}", entityWithServiceImplPaginationAndDTODTO);
        if (entityWithServiceImplPaginationAndDTODTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new entityWithServiceImplPaginationAndDTO cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        EntityWithServiceImplPaginationAndDTODTO result = entityWithServiceImplPaginationAndDTOService.save(
            entityWithServiceImplPaginationAndDTODTO
        );
        return ResponseEntity
            .created(new URI("/api/entity-with-service-impl-pagination-and-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-with-service-impl-pagination-and-dtos} : Updates an existing entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entityWithServiceImplPaginationAndDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityWithServiceImplPaginationAndDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityWithServiceImplPaginationAndDTODTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityWithServiceImplPaginationAndDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-with-service-impl-pagination-and-dtos")
    public ResponseEntity<EntityWithServiceImplPaginationAndDTODTO> updateEntityWithServiceImplPaginationAndDTO(
        @RequestBody EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO
    ) throws URISyntaxException {
        log.debug("REST request to update EntityWithServiceImplPaginationAndDTO : {}", entityWithServiceImplPaginationAndDTODTO);
        if (entityWithServiceImplPaginationAndDTODTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityWithServiceImplPaginationAndDTODTO result = entityWithServiceImplPaginationAndDTOService.save(
            entityWithServiceImplPaginationAndDTODTO
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityWithServiceImplPaginationAndDTODTO.getId())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /entity-with-service-impl-pagination-and-dtos} : Updates given fields of an existing entityWithServiceImplPaginationAndDTO.
     *
     * @param entityWithServiceImplPaginationAndDTODTO the entityWithServiceImplPaginationAndDTODTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityWithServiceImplPaginationAndDTODTO,
     * or with status {@code 400 (Bad Request)} if the entityWithServiceImplPaginationAndDTODTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityWithServiceImplPaginationAndDTODTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityWithServiceImplPaginationAndDTODTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/entity-with-service-impl-pagination-and-dtos", consumes = "application/merge-patch+json")
    public ResponseEntity<EntityWithServiceImplPaginationAndDTODTO> partialUpdateEntityWithServiceImplPaginationAndDTO(
        @RequestBody EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO
    ) throws URISyntaxException {
        log.debug("REST request to update EntityWithServiceImplPaginationAndDTO partially : {}", entityWithServiceImplPaginationAndDTODTO);
        if (entityWithServiceImplPaginationAndDTODTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<EntityWithServiceImplPaginationAndDTODTO> result = entityWithServiceImplPaginationAndDTOService.partialUpdate(
            entityWithServiceImplPaginationAndDTODTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, entityWithServiceImplPaginationAndDTODTO.getId())
        );
    }

    /**
     * {@code GET  /entity-with-service-impl-pagination-and-dtos} : get all the entityWithServiceImplPaginationAndDTOS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityWithServiceImplPaginationAndDTOS in body.
     */
    @GetMapping("/entity-with-service-impl-pagination-and-dtos")
    public ResponseEntity<List<EntityWithServiceImplPaginationAndDTODTO>> getAllEntityWithServiceImplPaginationAndDTOS(Pageable pageable) {
        log.debug("REST request to get a page of EntityWithServiceImplPaginationAndDTOS");
        Page<EntityWithServiceImplPaginationAndDTODTO> page = entityWithServiceImplPaginationAndDTOService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /entity-with-service-impl-pagination-and-dtos/:id} : get the "id" entityWithServiceImplPaginationAndDTO.
     *
     * @param id the id of the entityWithServiceImplPaginationAndDTODTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityWithServiceImplPaginationAndDTODTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-with-service-impl-pagination-and-dtos/{id}")
    public ResponseEntity<EntityWithServiceImplPaginationAndDTODTO> getEntityWithServiceImplPaginationAndDTO(@PathVariable String id) {
        log.debug("REST request to get EntityWithServiceImplPaginationAndDTO : {}", id);
        Optional<EntityWithServiceImplPaginationAndDTODTO> entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(entityWithServiceImplPaginationAndDTODTO);
    }

    /**
     * {@code DELETE  /entity-with-service-impl-pagination-and-dtos/:id} : delete the "id" entityWithServiceImplPaginationAndDTO.
     *
     * @param id the id of the entityWithServiceImplPaginationAndDTODTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-with-service-impl-pagination-and-dtos/{id}")
    public ResponseEntity<Void> deleteEntityWithServiceImplPaginationAndDTO(@PathVariable String id) {
        log.debug("REST request to delete EntityWithServiceImplPaginationAndDTO : {}", id);
        entityWithServiceImplPaginationAndDTOService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
