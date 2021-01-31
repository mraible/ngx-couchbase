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
import tech.jhipster.sample.domain.Division;
import tech.jhipster.sample.repository.DivisionRepository;
import tech.jhipster.sample.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link tech.jhipster.sample.domain.Division}.
 */
@RestController
@RequestMapping("/api")
public class DivisionResource {

    private final Logger log = LoggerFactory.getLogger(DivisionResource.class);

    private static final String ENTITY_NAME = "testRootDivision";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DivisionRepository divisionRepository;

    public DivisionResource(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    /**
     * {@code POST  /divisions} : Create a new division.
     *
     * @param division the division to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new division, or with status {@code 400 (Bad Request)} if the division has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/divisions")
    public ResponseEntity<Division> createDivision(@Valid @RequestBody Division division) throws URISyntaxException {
        log.debug("REST request to save Division : {}", division);
        if (division.getId() != null) {
            throw new BadRequestAlertException("A new division cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Division result = divisionRepository.save(division);
        return ResponseEntity
            .created(new URI("/api/divisions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /divisions} : Updates an existing division.
     *
     * @param division the division to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated division,
     * or with status {@code 400 (Bad Request)} if the division is not valid,
     * or with status {@code 500 (Internal Server Error)} if the division couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/divisions")
    public ResponseEntity<Division> updateDivision(@Valid @RequestBody Division division) throws URISyntaxException {
        log.debug("REST request to update Division : {}", division);
        if (division.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Division result = divisionRepository.save(division);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, division.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /divisions} : Updates given fields of an existing division.
     *
     * @param division the division to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated division,
     * or with status {@code 400 (Bad Request)} if the division is not valid,
     * or with status {@code 404 (Not Found)} if the division is not found,
     * or with status {@code 500 (Internal Server Error)} if the division couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/divisions", consumes = "application/merge-patch+json")
    public ResponseEntity<Division> partialUpdateDivision(@NotNull @RequestBody Division division) throws URISyntaxException {
        log.debug("REST request to update Division partially : {}", division);
        if (division.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Optional<Division> result = divisionRepository
            .findById(division.getId())
            .map(
                existingDivision -> {
                    if (division.getName() != null) {
                        existingDivision.setName(division.getName());
                    }

                    if (division.getShortName() != null) {
                        existingDivision.setShortName(division.getShortName());
                    }

                    if (division.getNumberOfPeople() != null) {
                        existingDivision.setNumberOfPeople(division.getNumberOfPeople());
                    }

                    if (division.getDivisionType() != null) {
                        existingDivision.setDivisionType(division.getDivisionType());
                    }

                    if (division.getColorBackground() != null) {
                        existingDivision.setColorBackground(division.getColorBackground());
                    }

                    if (division.getColorText() != null) {
                        existingDivision.setColorText(division.getColorText());
                    }

                    return existingDivision;
                }
            )
            .map(divisionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, division.getId())
        );
    }

    /**
     * {@code GET  /divisions} : get all the divisions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of divisions in body.
     */
    @GetMapping("/divisions")
    public List<Division> getAllDivisions() {
        log.debug("REST request to get all Divisions");
        return divisionRepository.findAll();
    }

    /**
     * {@code GET  /divisions/:id} : get the "id" division.
     *
     * @param id the id of the division to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the division, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> getDivision(@PathVariable String id) {
        log.debug("REST request to get Division : {}", id);
        Optional<Division> division = divisionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(division);
    }

    /**
     * {@code DELETE  /divisions/:id} : delete the "id" division.
     *
     * @param id the id of the division to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/divisions/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable String id) {
        log.debug("REST request to delete Division : {}", id);
        divisionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
