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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.sample.domain.FieldTestPaginationEntity;
import tech.jhipster.sample.repository.FieldTestPaginationEntityRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.FieldTestPaginationEntity}.
 */
@RestController
@RequestMapping("/api")
public class FieldTestPaginationEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestPaginationEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestPaginationEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldTestPaginationEntityRepository fieldTestPaginationEntityRepository;

    public FieldTestPaginationEntityResource(FieldTestPaginationEntityRepository fieldTestPaginationEntityRepository) {
        this.fieldTestPaginationEntityRepository = fieldTestPaginationEntityRepository;
    }

    /**
     * {@code POST  /field-test-pagination-entities} : Create a new fieldTestPaginationEntity.
     *
     * @param fieldTestPaginationEntity the fieldTestPaginationEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldTestPaginationEntity, or with status {@code 400 (Bad Request)} if the fieldTestPaginationEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-test-pagination-entities")
    public ResponseEntity<FieldTestPaginationEntity> createFieldTestPaginationEntity(
        @Valid @RequestBody FieldTestPaginationEntity fieldTestPaginationEntity
    ) throws URISyntaxException {
        log.debug("REST request to save FieldTestPaginationEntity : {}", fieldTestPaginationEntity);
        if (fieldTestPaginationEntity.getId() != null) {
            throw new BadRequestAlertException("A new fieldTestPaginationEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldTestPaginationEntity result = fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);
        return ResponseEntity
            .created(new URI("/api/field-test-pagination-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /field-test-pagination-entities} : Updates an existing fieldTestPaginationEntity.
     *
     * @param fieldTestPaginationEntity the fieldTestPaginationEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestPaginationEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestPaginationEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestPaginationEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-test-pagination-entities")
    public ResponseEntity<FieldTestPaginationEntity> updateFieldTestPaginationEntity(
        @Valid @RequestBody FieldTestPaginationEntity fieldTestPaginationEntity
    ) throws URISyntaxException {
        log.debug("REST request to update FieldTestPaginationEntity : {}", fieldTestPaginationEntity);
        if (fieldTestPaginationEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldTestPaginationEntity result = fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestPaginationEntity.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /field-test-pagination-entities} : Updates given fields of an existing fieldTestPaginationEntity.
     *
     * @param fieldTestPaginationEntity the fieldTestPaginationEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestPaginationEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestPaginationEntity is not valid,
     * or with status {@code 404 (Not Found)} if the fieldTestPaginationEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestPaginationEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/field-test-pagination-entities", consumes = "application/merge-patch+json")
    public ResponseEntity<FieldTestPaginationEntity> partialUpdateFieldTestPaginationEntity(
        @NotNull @RequestBody FieldTestPaginationEntity fieldTestPaginationEntity
    ) throws URISyntaxException {
        log.debug("REST request to update FieldTestPaginationEntity partially : {}", fieldTestPaginationEntity);
        if (fieldTestPaginationEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<FieldTestPaginationEntity> result = fieldTestPaginationEntityRepository
            .findById(fieldTestPaginationEntity.getId())
            .map(
                existingFieldTestPaginationEntity -> {
                    if (fieldTestPaginationEntity.getStringAlice() != null) {
                        existingFieldTestPaginationEntity.setStringAlice(fieldTestPaginationEntity.getStringAlice());
                    }

                    if (fieldTestPaginationEntity.getStringRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setStringRequiredAlice(fieldTestPaginationEntity.getStringRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getStringMinlengthAlice() != null) {
                        existingFieldTestPaginationEntity.setStringMinlengthAlice(fieldTestPaginationEntity.getStringMinlengthAlice());
                    }

                    if (fieldTestPaginationEntity.getStringMaxlengthAlice() != null) {
                        existingFieldTestPaginationEntity.setStringMaxlengthAlice(fieldTestPaginationEntity.getStringMaxlengthAlice());
                    }

                    if (fieldTestPaginationEntity.getStringPatternAlice() != null) {
                        existingFieldTestPaginationEntity.setStringPatternAlice(fieldTestPaginationEntity.getStringPatternAlice());
                    }

                    if (fieldTestPaginationEntity.getIntegerAlice() != null) {
                        existingFieldTestPaginationEntity.setIntegerAlice(fieldTestPaginationEntity.getIntegerAlice());
                    }

                    if (fieldTestPaginationEntity.getIntegerRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setIntegerRequiredAlice(fieldTestPaginationEntity.getIntegerRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getIntegerMinAlice() != null) {
                        existingFieldTestPaginationEntity.setIntegerMinAlice(fieldTestPaginationEntity.getIntegerMinAlice());
                    }

                    if (fieldTestPaginationEntity.getIntegerMaxAlice() != null) {
                        existingFieldTestPaginationEntity.setIntegerMaxAlice(fieldTestPaginationEntity.getIntegerMaxAlice());
                    }

                    if (fieldTestPaginationEntity.getLongAlice() != null) {
                        existingFieldTestPaginationEntity.setLongAlice(fieldTestPaginationEntity.getLongAlice());
                    }

                    if (fieldTestPaginationEntity.getLongRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setLongRequiredAlice(fieldTestPaginationEntity.getLongRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getLongMinAlice() != null) {
                        existingFieldTestPaginationEntity.setLongMinAlice(fieldTestPaginationEntity.getLongMinAlice());
                    }

                    if (fieldTestPaginationEntity.getLongMaxAlice() != null) {
                        existingFieldTestPaginationEntity.setLongMaxAlice(fieldTestPaginationEntity.getLongMaxAlice());
                    }

                    if (fieldTestPaginationEntity.getFloatAlice() != null) {
                        existingFieldTestPaginationEntity.setFloatAlice(fieldTestPaginationEntity.getFloatAlice());
                    }

                    if (fieldTestPaginationEntity.getFloatRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setFloatRequiredAlice(fieldTestPaginationEntity.getFloatRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getFloatMinAlice() != null) {
                        existingFieldTestPaginationEntity.setFloatMinAlice(fieldTestPaginationEntity.getFloatMinAlice());
                    }

                    if (fieldTestPaginationEntity.getFloatMaxAlice() != null) {
                        existingFieldTestPaginationEntity.setFloatMaxAlice(fieldTestPaginationEntity.getFloatMaxAlice());
                    }

                    if (fieldTestPaginationEntity.getDoubleRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setDoubleRequiredAlice(fieldTestPaginationEntity.getDoubleRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getDoubleMinAlice() != null) {
                        existingFieldTestPaginationEntity.setDoubleMinAlice(fieldTestPaginationEntity.getDoubleMinAlice());
                    }

                    if (fieldTestPaginationEntity.getDoubleMaxAlice() != null) {
                        existingFieldTestPaginationEntity.setDoubleMaxAlice(fieldTestPaginationEntity.getDoubleMaxAlice());
                    }

                    if (fieldTestPaginationEntity.getBigDecimalRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setBigDecimalRequiredAlice(
                            fieldTestPaginationEntity.getBigDecimalRequiredAlice()
                        );
                    }

                    if (fieldTestPaginationEntity.getBigDecimalMinAlice() != null) {
                        existingFieldTestPaginationEntity.setBigDecimalMinAlice(fieldTestPaginationEntity.getBigDecimalMinAlice());
                    }

                    if (fieldTestPaginationEntity.getBigDecimalMaxAlice() != null) {
                        existingFieldTestPaginationEntity.setBigDecimalMaxAlice(fieldTestPaginationEntity.getBigDecimalMaxAlice());
                    }

                    if (fieldTestPaginationEntity.getLocalDateAlice() != null) {
                        existingFieldTestPaginationEntity.setLocalDateAlice(fieldTestPaginationEntity.getLocalDateAlice());
                    }

                    if (fieldTestPaginationEntity.getLocalDateRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setLocalDateRequiredAlice(fieldTestPaginationEntity.getLocalDateRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getInstantAlice() != null) {
                        existingFieldTestPaginationEntity.setInstantAlice(fieldTestPaginationEntity.getInstantAlice());
                    }

                    if (fieldTestPaginationEntity.getInstanteRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setInstanteRequiredAlice(fieldTestPaginationEntity.getInstanteRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getZonedDateTimeAlice() != null) {
                        existingFieldTestPaginationEntity.setZonedDateTimeAlice(fieldTestPaginationEntity.getZonedDateTimeAlice());
                    }

                    if (fieldTestPaginationEntity.getZonedDateTimeRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setZonedDateTimeRequiredAlice(
                            fieldTestPaginationEntity.getZonedDateTimeRequiredAlice()
                        );
                    }

                    if (fieldTestPaginationEntity.getDurationAlice() != null) {
                        existingFieldTestPaginationEntity.setDurationAlice(fieldTestPaginationEntity.getDurationAlice());
                    }

                    if (fieldTestPaginationEntity.getDurationRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setDurationRequiredAlice(fieldTestPaginationEntity.getDurationRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getBooleanAlice() != null) {
                        existingFieldTestPaginationEntity.setBooleanAlice(fieldTestPaginationEntity.getBooleanAlice());
                    }

                    if (fieldTestPaginationEntity.getBooleanRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setBooleanRequiredAlice(fieldTestPaginationEntity.getBooleanRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getEnumAlice() != null) {
                        existingFieldTestPaginationEntity.setEnumAlice(fieldTestPaginationEntity.getEnumAlice());
                    }

                    if (fieldTestPaginationEntity.getEnumRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setEnumRequiredAlice(fieldTestPaginationEntity.getEnumRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getUuidAlice() != null) {
                        existingFieldTestPaginationEntity.setUuidAlice(fieldTestPaginationEntity.getUuidAlice());
                    }

                    if (fieldTestPaginationEntity.getUuidRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setUuidRequiredAlice(fieldTestPaginationEntity.getUuidRequiredAlice());
                    }

                    if (fieldTestPaginationEntity.getByteImageAlice() != null) {
                        existingFieldTestPaginationEntity.setByteImageAlice(fieldTestPaginationEntity.getByteImageAlice());
                    }
                    if (fieldTestPaginationEntity.getByteImageAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteImageAliceContentType(
                            fieldTestPaginationEntity.getByteImageAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteImageRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setByteImageRequiredAlice(fieldTestPaginationEntity.getByteImageRequiredAlice());
                    }
                    if (fieldTestPaginationEntity.getByteImageRequiredAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteImageRequiredAliceContentType(
                            fieldTestPaginationEntity.getByteImageRequiredAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteImageMinbytesAlice() != null) {
                        existingFieldTestPaginationEntity.setByteImageMinbytesAlice(fieldTestPaginationEntity.getByteImageMinbytesAlice());
                    }
                    if (fieldTestPaginationEntity.getByteImageMinbytesAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteImageMinbytesAliceContentType(
                            fieldTestPaginationEntity.getByteImageMinbytesAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteImageMaxbytesAlice() != null) {
                        existingFieldTestPaginationEntity.setByteImageMaxbytesAlice(fieldTestPaginationEntity.getByteImageMaxbytesAlice());
                    }
                    if (fieldTestPaginationEntity.getByteImageMaxbytesAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteImageMaxbytesAliceContentType(
                            fieldTestPaginationEntity.getByteImageMaxbytesAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteAnyAlice() != null) {
                        existingFieldTestPaginationEntity.setByteAnyAlice(fieldTestPaginationEntity.getByteAnyAlice());
                    }
                    if (fieldTestPaginationEntity.getByteAnyAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteAnyAliceContentType(
                            fieldTestPaginationEntity.getByteAnyAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteAnyRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setByteAnyRequiredAlice(fieldTestPaginationEntity.getByteAnyRequiredAlice());
                    }
                    if (fieldTestPaginationEntity.getByteAnyRequiredAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteAnyRequiredAliceContentType(
                            fieldTestPaginationEntity.getByteAnyRequiredAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteAnyMinbytesAlice() != null) {
                        existingFieldTestPaginationEntity.setByteAnyMinbytesAlice(fieldTestPaginationEntity.getByteAnyMinbytesAlice());
                    }
                    if (fieldTestPaginationEntity.getByteAnyMinbytesAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteAnyMinbytesAliceContentType(
                            fieldTestPaginationEntity.getByteAnyMinbytesAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteAnyMaxbytesAlice() != null) {
                        existingFieldTestPaginationEntity.setByteAnyMaxbytesAlice(fieldTestPaginationEntity.getByteAnyMaxbytesAlice());
                    }
                    if (fieldTestPaginationEntity.getByteAnyMaxbytesAliceContentType() != null) {
                        existingFieldTestPaginationEntity.setByteAnyMaxbytesAliceContentType(
                            fieldTestPaginationEntity.getByteAnyMaxbytesAliceContentType()
                        );
                    }

                    if (fieldTestPaginationEntity.getByteTextAlice() != null) {
                        existingFieldTestPaginationEntity.setByteTextAlice(fieldTestPaginationEntity.getByteTextAlice());
                    }

                    if (fieldTestPaginationEntity.getByteTextRequiredAlice() != null) {
                        existingFieldTestPaginationEntity.setByteTextRequiredAlice(fieldTestPaginationEntity.getByteTextRequiredAlice());
                    }

                    return existingFieldTestPaginationEntity;
                }
            )
            .map(fieldTestPaginationEntityRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestPaginationEntity.getId())
        );
    }

    /**
     * {@code GET  /field-test-pagination-entities} : get all the fieldTestPaginationEntities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldTestPaginationEntities in body.
     */
    @GetMapping("/field-test-pagination-entities")
    public ResponseEntity<List<FieldTestPaginationEntity>> getAllFieldTestPaginationEntities(Pageable pageable) {
        log.debug("REST request to get a page of FieldTestPaginationEntities");
        Page<FieldTestPaginationEntity> page = fieldTestPaginationEntityRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /field-test-pagination-entities/:id} : get the "id" fieldTestPaginationEntity.
     *
     * @param id the id of the fieldTestPaginationEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldTestPaginationEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-test-pagination-entities/{id}")
    public ResponseEntity<FieldTestPaginationEntity> getFieldTestPaginationEntity(@PathVariable String id) {
        log.debug("REST request to get FieldTestPaginationEntity : {}", id);
        Optional<FieldTestPaginationEntity> fieldTestPaginationEntity = fieldTestPaginationEntityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fieldTestPaginationEntity);
    }

    /**
     * {@code DELETE  /field-test-pagination-entities/:id} : delete the "id" fieldTestPaginationEntity.
     *
     * @param id the id of the fieldTestPaginationEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-test-pagination-entities/{id}")
    public ResponseEntity<Void> deleteFieldTestPaginationEntity(@PathVariable String id) {
        log.debug("REST request to delete FieldTestPaginationEntity : {}", id);
        fieldTestPaginationEntityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
