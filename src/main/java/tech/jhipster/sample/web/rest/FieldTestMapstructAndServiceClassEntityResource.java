package tech.jhipster.sample.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.sample.service.FieldTestMapstructAndServiceClassEntityService;
import tech.jhipster.sample.service.dto.FieldTestMapstructAndServiceClassEntityDTO;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.FieldTestMapstructAndServiceClassEntity}.
 */
@RestController
@RequestMapping("/api")
public class FieldTestMapstructAndServiceClassEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestMapstructAndServiceClassEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestMapstructAndServiceClassEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldTestMapstructAndServiceClassEntityService fieldTestMapstructAndServiceClassEntityService;

    public FieldTestMapstructAndServiceClassEntityResource(
        FieldTestMapstructAndServiceClassEntityService fieldTestMapstructAndServiceClassEntityService
    ) {
        this.fieldTestMapstructAndServiceClassEntityService = fieldTestMapstructAndServiceClassEntityService;
    }

    /**
     * {@code POST  /field-test-mapstruct-and-service-class-entities} : Create a new fieldTestMapstructAndServiceClassEntity.
     *
     * @param fieldTestMapstructAndServiceClassEntityDTO the fieldTestMapstructAndServiceClassEntityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldTestMapstructAndServiceClassEntityDTO, or with status {@code 400 (Bad Request)} if the fieldTestMapstructAndServiceClassEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-test-mapstruct-and-service-class-entities")
    public ResponseEntity<FieldTestMapstructAndServiceClassEntityDTO> createFieldTestMapstructAndServiceClassEntity(
        @Valid @RequestBody FieldTestMapstructAndServiceClassEntityDTO fieldTestMapstructAndServiceClassEntityDTO
    ) throws URISyntaxException {
        log.debug("REST request to save FieldTestMapstructAndServiceClassEntity : {}", fieldTestMapstructAndServiceClassEntityDTO);
        if (fieldTestMapstructAndServiceClassEntityDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new fieldTestMapstructAndServiceClassEntity cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        FieldTestMapstructAndServiceClassEntityDTO result = fieldTestMapstructAndServiceClassEntityService.save(
            fieldTestMapstructAndServiceClassEntityDTO
        );
        return ResponseEntity
            .created(new URI("/api/field-test-mapstruct-and-service-class-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /field-test-mapstruct-and-service-class-entities} : Updates an existing fieldTestMapstructAndServiceClassEntity.
     *
     * @param fieldTestMapstructAndServiceClassEntityDTO the fieldTestMapstructAndServiceClassEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestMapstructAndServiceClassEntityDTO,
     * or with status {@code 400 (Bad Request)} if the fieldTestMapstructAndServiceClassEntityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestMapstructAndServiceClassEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-test-mapstruct-and-service-class-entities")
    public ResponseEntity<FieldTestMapstructAndServiceClassEntityDTO> updateFieldTestMapstructAndServiceClassEntity(
        @Valid @RequestBody FieldTestMapstructAndServiceClassEntityDTO fieldTestMapstructAndServiceClassEntityDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FieldTestMapstructAndServiceClassEntity : {}", fieldTestMapstructAndServiceClassEntityDTO);
        if (fieldTestMapstructAndServiceClassEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldTestMapstructAndServiceClassEntityDTO result = fieldTestMapstructAndServiceClassEntityService.save(
            fieldTestMapstructAndServiceClassEntityDTO
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestMapstructAndServiceClassEntityDTO.getId())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /field-test-mapstruct-and-service-class-entities} : Updates given fields of an existing fieldTestMapstructAndServiceClassEntity.
     *
     * @param fieldTestMapstructAndServiceClassEntityDTO the fieldTestMapstructAndServiceClassEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestMapstructAndServiceClassEntityDTO,
     * or with status {@code 400 (Bad Request)} if the fieldTestMapstructAndServiceClassEntityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fieldTestMapstructAndServiceClassEntityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestMapstructAndServiceClassEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/field-test-mapstruct-and-service-class-entities", consumes = "application/merge-patch+json")
    public ResponseEntity<FieldTestMapstructAndServiceClassEntityDTO> partialUpdateFieldTestMapstructAndServiceClassEntity(
        @NotNull @RequestBody FieldTestMapstructAndServiceClassEntityDTO fieldTestMapstructAndServiceClassEntityDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to update FieldTestMapstructAndServiceClassEntity partially : {}",
            fieldTestMapstructAndServiceClassEntityDTO
        );
        if (fieldTestMapstructAndServiceClassEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<FieldTestMapstructAndServiceClassEntityDTO> result = fieldTestMapstructAndServiceClassEntityService.partialUpdate(
            fieldTestMapstructAndServiceClassEntityDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestMapstructAndServiceClassEntityDTO.getId())
        );
    }

    /**
     * {@code GET  /field-test-mapstruct-and-service-class-entities} : get all the fieldTestMapstructAndServiceClassEntities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldTestMapstructAndServiceClassEntities in body.
     */
    @GetMapping("/field-test-mapstruct-and-service-class-entities")
    public List<FieldTestMapstructAndServiceClassEntityDTO> getAllFieldTestMapstructAndServiceClassEntities() {
        log.debug("REST request to get all FieldTestMapstructAndServiceClassEntities");
        return fieldTestMapstructAndServiceClassEntityService.findAll();
    }

    /**
     * {@code GET  /field-test-mapstruct-and-service-class-entities/:id} : get the "id" fieldTestMapstructAndServiceClassEntity.
     *
     * @param id the id of the fieldTestMapstructAndServiceClassEntityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldTestMapstructAndServiceClassEntityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-test-mapstruct-and-service-class-entities/{id}")
    public ResponseEntity<FieldTestMapstructAndServiceClassEntityDTO> getFieldTestMapstructAndServiceClassEntity(@PathVariable String id) {
        log.debug("REST request to get FieldTestMapstructAndServiceClassEntity : {}", id);
        Optional<FieldTestMapstructAndServiceClassEntityDTO> fieldTestMapstructAndServiceClassEntityDTO = fieldTestMapstructAndServiceClassEntityService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(fieldTestMapstructAndServiceClassEntityDTO);
    }

    /**
     * {@code DELETE  /field-test-mapstruct-and-service-class-entities/:id} : delete the "id" fieldTestMapstructAndServiceClassEntity.
     *
     * @param id the id of the fieldTestMapstructAndServiceClassEntityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-test-mapstruct-and-service-class-entities/{id}")
    public ResponseEntity<Void> deleteFieldTestMapstructAndServiceClassEntity(@PathVariable String id) {
        log.debug("REST request to delete FieldTestMapstructAndServiceClassEntity : {}", id);
        fieldTestMapstructAndServiceClassEntityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
