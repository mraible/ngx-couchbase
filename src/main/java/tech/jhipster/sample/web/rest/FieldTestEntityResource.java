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
import tech.jhipster.sample.domain.FieldTestEntity;
import tech.jhipster.sample.repository.FieldTestEntityRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.FieldTestEntity}.
 */
@RestController
@RequestMapping("/api")
public class FieldTestEntityResource {

    private final Logger log = LoggerFactory.getLogger(FieldTestEntityResource.class);

    private static final String ENTITY_NAME = "fieldTestEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FieldTestEntityRepository fieldTestEntityRepository;

    public FieldTestEntityResource(FieldTestEntityRepository fieldTestEntityRepository) {
        this.fieldTestEntityRepository = fieldTestEntityRepository;
    }

    /**
     * {@code POST  /field-test-entities} : Create a new fieldTestEntity.
     *
     * @param fieldTestEntity the fieldTestEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fieldTestEntity, or with status {@code 400 (Bad Request)} if the fieldTestEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/field-test-entities")
    public ResponseEntity<FieldTestEntity> createFieldTestEntity(@Valid @RequestBody FieldTestEntity fieldTestEntity)
        throws URISyntaxException {
        log.debug("REST request to save FieldTestEntity : {}", fieldTestEntity);
        if (fieldTestEntity.getId() != null) {
            throw new BadRequestAlertException("A new fieldTestEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FieldTestEntity result = fieldTestEntityRepository.save(fieldTestEntity);
        return ResponseEntity
            .created(new URI("/api/field-test-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /field-test-entities} : Updates an existing fieldTestEntity.
     *
     * @param fieldTestEntity the fieldTestEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/field-test-entities")
    public ResponseEntity<FieldTestEntity> updateFieldTestEntity(@Valid @RequestBody FieldTestEntity fieldTestEntity)
        throws URISyntaxException {
        log.debug("REST request to update FieldTestEntity : {}", fieldTestEntity);
        if (fieldTestEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FieldTestEntity result = fieldTestEntityRepository.save(fieldTestEntity);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestEntity.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /field-test-entities} : Updates given fields of an existing fieldTestEntity.
     *
     * @param fieldTestEntity the fieldTestEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fieldTestEntity,
     * or with status {@code 400 (Bad Request)} if the fieldTestEntity is not valid,
     * or with status {@code 404 (Not Found)} if the fieldTestEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the fieldTestEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/field-test-entities", consumes = "application/merge-patch+json")
    public ResponseEntity<FieldTestEntity> partialUpdateFieldTestEntity(@NotNull @RequestBody FieldTestEntity fieldTestEntity)
        throws URISyntaxException {
        log.debug("REST request to update FieldTestEntity partially : {}", fieldTestEntity);
        if (fieldTestEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<FieldTestEntity> result = fieldTestEntityRepository
            .findById(fieldTestEntity.getId())
            .map(
                existingFieldTestEntity -> {
                    if (fieldTestEntity.getStringTom() != null) {
                        existingFieldTestEntity.setStringTom(fieldTestEntity.getStringTom());
                    }

                    if (fieldTestEntity.getStringRequiredTom() != null) {
                        existingFieldTestEntity.setStringRequiredTom(fieldTestEntity.getStringRequiredTom());
                    }

                    if (fieldTestEntity.getStringMinlengthTom() != null) {
                        existingFieldTestEntity.setStringMinlengthTom(fieldTestEntity.getStringMinlengthTom());
                    }

                    if (fieldTestEntity.getStringMaxlengthTom() != null) {
                        existingFieldTestEntity.setStringMaxlengthTom(fieldTestEntity.getStringMaxlengthTom());
                    }

                    if (fieldTestEntity.getStringPatternTom() != null) {
                        existingFieldTestEntity.setStringPatternTom(fieldTestEntity.getStringPatternTom());
                    }

                    if (fieldTestEntity.getNumberPatternTom() != null) {
                        existingFieldTestEntity.setNumberPatternTom(fieldTestEntity.getNumberPatternTom());
                    }

                    if (fieldTestEntity.getNumberPatternRequiredTom() != null) {
                        existingFieldTestEntity.setNumberPatternRequiredTom(fieldTestEntity.getNumberPatternRequiredTom());
                    }

                    if (fieldTestEntity.getIntegerTom() != null) {
                        existingFieldTestEntity.setIntegerTom(fieldTestEntity.getIntegerTom());
                    }

                    if (fieldTestEntity.getIntegerRequiredTom() != null) {
                        existingFieldTestEntity.setIntegerRequiredTom(fieldTestEntity.getIntegerRequiredTom());
                    }

                    if (fieldTestEntity.getIntegerMinTom() != null) {
                        existingFieldTestEntity.setIntegerMinTom(fieldTestEntity.getIntegerMinTom());
                    }

                    if (fieldTestEntity.getIntegerMaxTom() != null) {
                        existingFieldTestEntity.setIntegerMaxTom(fieldTestEntity.getIntegerMaxTom());
                    }

                    if (fieldTestEntity.getLongTom() != null) {
                        existingFieldTestEntity.setLongTom(fieldTestEntity.getLongTom());
                    }

                    if (fieldTestEntity.getLongRequiredTom() != null) {
                        existingFieldTestEntity.setLongRequiredTom(fieldTestEntity.getLongRequiredTom());
                    }

                    if (fieldTestEntity.getLongMinTom() != null) {
                        existingFieldTestEntity.setLongMinTom(fieldTestEntity.getLongMinTom());
                    }

                    if (fieldTestEntity.getLongMaxTom() != null) {
                        existingFieldTestEntity.setLongMaxTom(fieldTestEntity.getLongMaxTom());
                    }

                    if (fieldTestEntity.getFloatTom() != null) {
                        existingFieldTestEntity.setFloatTom(fieldTestEntity.getFloatTom());
                    }

                    if (fieldTestEntity.getFloatRequiredTom() != null) {
                        existingFieldTestEntity.setFloatRequiredTom(fieldTestEntity.getFloatRequiredTom());
                    }

                    if (fieldTestEntity.getFloatMinTom() != null) {
                        existingFieldTestEntity.setFloatMinTom(fieldTestEntity.getFloatMinTom());
                    }

                    if (fieldTestEntity.getFloatMaxTom() != null) {
                        existingFieldTestEntity.setFloatMaxTom(fieldTestEntity.getFloatMaxTom());
                    }

                    if (fieldTestEntity.getDoubleRequiredTom() != null) {
                        existingFieldTestEntity.setDoubleRequiredTom(fieldTestEntity.getDoubleRequiredTom());
                    }

                    if (fieldTestEntity.getDoubleMinTom() != null) {
                        existingFieldTestEntity.setDoubleMinTom(fieldTestEntity.getDoubleMinTom());
                    }

                    if (fieldTestEntity.getDoubleMaxTom() != null) {
                        existingFieldTestEntity.setDoubleMaxTom(fieldTestEntity.getDoubleMaxTom());
                    }

                    if (fieldTestEntity.getBigDecimalRequiredTom() != null) {
                        existingFieldTestEntity.setBigDecimalRequiredTom(fieldTestEntity.getBigDecimalRequiredTom());
                    }

                    if (fieldTestEntity.getBigDecimalMinTom() != null) {
                        existingFieldTestEntity.setBigDecimalMinTom(fieldTestEntity.getBigDecimalMinTom());
                    }

                    if (fieldTestEntity.getBigDecimalMaxTom() != null) {
                        existingFieldTestEntity.setBigDecimalMaxTom(fieldTestEntity.getBigDecimalMaxTom());
                    }

                    if (fieldTestEntity.getLocalDateTom() != null) {
                        existingFieldTestEntity.setLocalDateTom(fieldTestEntity.getLocalDateTom());
                    }

                    if (fieldTestEntity.getLocalDateRequiredTom() != null) {
                        existingFieldTestEntity.setLocalDateRequiredTom(fieldTestEntity.getLocalDateRequiredTom());
                    }

                    if (fieldTestEntity.getInstantTom() != null) {
                        existingFieldTestEntity.setInstantTom(fieldTestEntity.getInstantTom());
                    }

                    if (fieldTestEntity.getInstantRequiredTom() != null) {
                        existingFieldTestEntity.setInstantRequiredTom(fieldTestEntity.getInstantRequiredTom());
                    }

                    if (fieldTestEntity.getZonedDateTimeTom() != null) {
                        existingFieldTestEntity.setZonedDateTimeTom(fieldTestEntity.getZonedDateTimeTom());
                    }

                    if (fieldTestEntity.getZonedDateTimeRequiredTom() != null) {
                        existingFieldTestEntity.setZonedDateTimeRequiredTom(fieldTestEntity.getZonedDateTimeRequiredTom());
                    }

                    if (fieldTestEntity.getDurationTom() != null) {
                        existingFieldTestEntity.setDurationTom(fieldTestEntity.getDurationTom());
                    }

                    if (fieldTestEntity.getDurationRequiredTom() != null) {
                        existingFieldTestEntity.setDurationRequiredTom(fieldTestEntity.getDurationRequiredTom());
                    }

                    if (fieldTestEntity.getBooleanTom() != null) {
                        existingFieldTestEntity.setBooleanTom(fieldTestEntity.getBooleanTom());
                    }

                    if (fieldTestEntity.getBooleanRequiredTom() != null) {
                        existingFieldTestEntity.setBooleanRequiredTom(fieldTestEntity.getBooleanRequiredTom());
                    }

                    if (fieldTestEntity.getEnumTom() != null) {
                        existingFieldTestEntity.setEnumTom(fieldTestEntity.getEnumTom());
                    }

                    if (fieldTestEntity.getEnumRequiredTom() != null) {
                        existingFieldTestEntity.setEnumRequiredTom(fieldTestEntity.getEnumRequiredTom());
                    }

                    if (fieldTestEntity.getUuidTom() != null) {
                        existingFieldTestEntity.setUuidTom(fieldTestEntity.getUuidTom());
                    }

                    if (fieldTestEntity.getUuidRequiredTom() != null) {
                        existingFieldTestEntity.setUuidRequiredTom(fieldTestEntity.getUuidRequiredTom());
                    }

                    if (fieldTestEntity.getByteImageTom() != null) {
                        existingFieldTestEntity.setByteImageTom(fieldTestEntity.getByteImageTom());
                    }
                    if (fieldTestEntity.getByteImageTomContentType() != null) {
                        existingFieldTestEntity.setByteImageTomContentType(fieldTestEntity.getByteImageTomContentType());
                    }

                    if (fieldTestEntity.getByteImageRequiredTom() != null) {
                        existingFieldTestEntity.setByteImageRequiredTom(fieldTestEntity.getByteImageRequiredTom());
                    }
                    if (fieldTestEntity.getByteImageRequiredTomContentType() != null) {
                        existingFieldTestEntity.setByteImageRequiredTomContentType(fieldTestEntity.getByteImageRequiredTomContentType());
                    }

                    if (fieldTestEntity.getByteImageMinbytesTom() != null) {
                        existingFieldTestEntity.setByteImageMinbytesTom(fieldTestEntity.getByteImageMinbytesTom());
                    }
                    if (fieldTestEntity.getByteImageMinbytesTomContentType() != null) {
                        existingFieldTestEntity.setByteImageMinbytesTomContentType(fieldTestEntity.getByteImageMinbytesTomContentType());
                    }

                    if (fieldTestEntity.getByteImageMaxbytesTom() != null) {
                        existingFieldTestEntity.setByteImageMaxbytesTom(fieldTestEntity.getByteImageMaxbytesTom());
                    }
                    if (fieldTestEntity.getByteImageMaxbytesTomContentType() != null) {
                        existingFieldTestEntity.setByteImageMaxbytesTomContentType(fieldTestEntity.getByteImageMaxbytesTomContentType());
                    }

                    if (fieldTestEntity.getByteAnyTom() != null) {
                        existingFieldTestEntity.setByteAnyTom(fieldTestEntity.getByteAnyTom());
                    }
                    if (fieldTestEntity.getByteAnyTomContentType() != null) {
                        existingFieldTestEntity.setByteAnyTomContentType(fieldTestEntity.getByteAnyTomContentType());
                    }

                    if (fieldTestEntity.getByteAnyRequiredTom() != null) {
                        existingFieldTestEntity.setByteAnyRequiredTom(fieldTestEntity.getByteAnyRequiredTom());
                    }
                    if (fieldTestEntity.getByteAnyRequiredTomContentType() != null) {
                        existingFieldTestEntity.setByteAnyRequiredTomContentType(fieldTestEntity.getByteAnyRequiredTomContentType());
                    }

                    if (fieldTestEntity.getByteAnyMinbytesTom() != null) {
                        existingFieldTestEntity.setByteAnyMinbytesTom(fieldTestEntity.getByteAnyMinbytesTom());
                    }
                    if (fieldTestEntity.getByteAnyMinbytesTomContentType() != null) {
                        existingFieldTestEntity.setByteAnyMinbytesTomContentType(fieldTestEntity.getByteAnyMinbytesTomContentType());
                    }

                    if (fieldTestEntity.getByteAnyMaxbytesTom() != null) {
                        existingFieldTestEntity.setByteAnyMaxbytesTom(fieldTestEntity.getByteAnyMaxbytesTom());
                    }
                    if (fieldTestEntity.getByteAnyMaxbytesTomContentType() != null) {
                        existingFieldTestEntity.setByteAnyMaxbytesTomContentType(fieldTestEntity.getByteAnyMaxbytesTomContentType());
                    }

                    if (fieldTestEntity.getByteTextTom() != null) {
                        existingFieldTestEntity.setByteTextTom(fieldTestEntity.getByteTextTom());
                    }

                    if (fieldTestEntity.getByteTextRequiredTom() != null) {
                        existingFieldTestEntity.setByteTextRequiredTom(fieldTestEntity.getByteTextRequiredTom());
                    }

                    return existingFieldTestEntity;
                }
            )
            .map(fieldTestEntityRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fieldTestEntity.getId())
        );
    }

    /**
     * {@code GET  /field-test-entities} : get all the fieldTestEntities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fieldTestEntities in body.
     */
    @GetMapping("/field-test-entities")
    public List<FieldTestEntity> getAllFieldTestEntities() {
        log.debug("REST request to get all FieldTestEntities");
        return fieldTestEntityRepository.findAll();
    }

    /**
     * {@code GET  /field-test-entities/:id} : get the "id" fieldTestEntity.
     *
     * @param id the id of the fieldTestEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fieldTestEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/field-test-entities/{id}")
    public ResponseEntity<FieldTestEntity> getFieldTestEntity(@PathVariable String id) {
        log.debug("REST request to get FieldTestEntity : {}", id);
        Optional<FieldTestEntity> fieldTestEntity = fieldTestEntityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fieldTestEntity);
    }

    /**
     * {@code DELETE  /field-test-entities/:id} : delete the "id" fieldTestEntity.
     *
     * @param id the id of the fieldTestEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/field-test-entities/{id}")
    public ResponseEntity<Void> deleteFieldTestEntity(@PathVariable String id) {
        log.debug("REST request to delete FieldTestEntity : {}", id);
        fieldTestEntityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
