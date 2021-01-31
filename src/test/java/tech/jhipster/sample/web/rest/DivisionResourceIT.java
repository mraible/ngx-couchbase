package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.sample.IntegrationTest;
import tech.jhipster.sample.domain.Division;
import tech.jhipster.sample.domain.enumeration.DivisionType;
import tech.jhipster.sample.repository.DivisionRepository;

/**
 * Integration tests for the {@link DivisionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DivisionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMBER_OF_PEOPLE = 1L;
    private static final Long UPDATED_NUMBER_OF_PEOPLE = 2L;

    private static final DivisionType DEFAULT_DIVISION_TYPE = DivisionType.SCHOOL;
    private static final DivisionType UPDATED_DIVISION_TYPE = DivisionType.CLASS;

    private static final String DEFAULT_COLOR_BACKGROUND = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_BACKGROUND = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_TEXT = "BBBBBBBBBB";

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private MockMvc restDivisionMockMvc;

    private Division division;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Division createEntity() {
        Division division = new Division()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .numberOfPeople(DEFAULT_NUMBER_OF_PEOPLE)
            .divisionType(DEFAULT_DIVISION_TYPE)
            .colorBackground(DEFAULT_COLOR_BACKGROUND)
            .colorText(DEFAULT_COLOR_TEXT);
        return division;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Division createUpdatedEntity() {
        Division division = new Division()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .numberOfPeople(UPDATED_NUMBER_OF_PEOPLE)
            .divisionType(UPDATED_DIVISION_TYPE)
            .colorBackground(UPDATED_COLOR_BACKGROUND)
            .colorText(UPDATED_COLOR_TEXT);
        return division;
    }

    @BeforeEach
    public void initTest() {
        divisionRepository.deleteAll();
        division = createEntity();
    }

    @Test
    void createDivision() throws Exception {
        int databaseSizeBeforeCreate = divisionRepository.findAll().size();
        // Create the Division
        restDivisionMockMvc
            .perform(post("/api/divisions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(division)))
            .andExpect(status().isCreated());

        // Validate the Division in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeCreate + 1);
        Division testDivision = divisionList.get(divisionList.size() - 1);
        assertThat(testDivision.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDivision.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testDivision.getNumberOfPeople()).isEqualTo(DEFAULT_NUMBER_OF_PEOPLE);
        assertThat(testDivision.getDivisionType()).isEqualTo(DEFAULT_DIVISION_TYPE);
        assertThat(testDivision.getColorBackground()).isEqualTo(DEFAULT_COLOR_BACKGROUND);
        assertThat(testDivision.getColorText()).isEqualTo(DEFAULT_COLOR_TEXT);
    }

    @Test
    void createDivisionWithExistingId() throws Exception {
        // Create the Division with an existing ID
        division.setId("existing_id");

        int databaseSizeBeforeCreate = divisionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDivisionMockMvc
            .perform(post("/api/divisions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(division)))
            .andExpect(status().isBadRequest());

        // Validate the Division in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionRepository.findAll().size();
        // set the field null
        division.setName(null);

        // Create the Division, which fails.

        restDivisionMockMvc
            .perform(post("/api/divisions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(division)))
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDivisionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = divisionRepository.findAll().size();
        // set the field null
        division.setDivisionType(null);

        // Create the Division, which fails.

        restDivisionMockMvc
            .perform(post("/api/divisions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(division)))
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDivisions() throws Exception {
        // Initialize the database
        divisionRepository.save(division);

        // Get all the divisionList
        restDivisionMockMvc
            .perform(get("/api/divisions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(division.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].numberOfPeople").value(hasItem(DEFAULT_NUMBER_OF_PEOPLE.intValue())))
            .andExpect(jsonPath("$.[*].divisionType").value(hasItem(DEFAULT_DIVISION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].colorBackground").value(hasItem(DEFAULT_COLOR_BACKGROUND)))
            .andExpect(jsonPath("$.[*].colorText").value(hasItem(DEFAULT_COLOR_TEXT)));
    }

    @Test
    void getDivision() throws Exception {
        // Initialize the database
        divisionRepository.save(division);

        // Get the division
        restDivisionMockMvc
            .perform(get("/api/divisions/{id}", division.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(division.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME))
            .andExpect(jsonPath("$.numberOfPeople").value(DEFAULT_NUMBER_OF_PEOPLE.intValue()))
            .andExpect(jsonPath("$.divisionType").value(DEFAULT_DIVISION_TYPE.toString()))
            .andExpect(jsonPath("$.colorBackground").value(DEFAULT_COLOR_BACKGROUND))
            .andExpect(jsonPath("$.colorText").value(DEFAULT_COLOR_TEXT));
    }

    @Test
    void getNonExistingDivision() throws Exception {
        // Get the division
        restDivisionMockMvc.perform(get("/api/divisions/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void updateDivision() throws Exception {
        // Initialize the database
        divisionRepository.save(division);

        int databaseSizeBeforeUpdate = divisionRepository.findAll().size();

        // Update the division
        Division updatedDivision = divisionRepository.findById(division.getId()).get();
        updatedDivision
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .numberOfPeople(UPDATED_NUMBER_OF_PEOPLE)
            .divisionType(UPDATED_DIVISION_TYPE)
            .colorBackground(UPDATED_COLOR_BACKGROUND)
            .colorText(UPDATED_COLOR_TEXT);

        restDivisionMockMvc
            .perform(
                put("/api/divisions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(updatedDivision))
            )
            .andExpect(status().isOk());

        // Validate the Division in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeUpdate);
        Division testDivision = divisionList.get(divisionList.size() - 1);
        assertThat(testDivision.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDivision.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testDivision.getNumberOfPeople()).isEqualTo(UPDATED_NUMBER_OF_PEOPLE);
        assertThat(testDivision.getDivisionType()).isEqualTo(UPDATED_DIVISION_TYPE);
        assertThat(testDivision.getColorBackground()).isEqualTo(UPDATED_COLOR_BACKGROUND);
        assertThat(testDivision.getColorText()).isEqualTo(UPDATED_COLOR_TEXT);
    }

    @Test
    void updateNonExistingDivision() throws Exception {
        int databaseSizeBeforeUpdate = divisionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDivisionMockMvc
            .perform(put("/api/divisions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(division)))
            .andExpect(status().isBadRequest());

        // Validate the Division in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDivisionWithPatch() throws Exception {
        // Initialize the database
        divisionRepository.save(division);

        int databaseSizeBeforeUpdate = divisionRepository.findAll().size();

        // Update the division using partial update
        Division partialUpdatedDivision = new Division();
        partialUpdatedDivision.setId(division.getId());

        partialUpdatedDivision.colorBackground(UPDATED_COLOR_BACKGROUND);

        restDivisionMockMvc
            .perform(
                patch("/api/divisions")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDivision))
            )
            .andExpect(status().isOk());

        // Validate the Division in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeUpdate);
        Division testDivision = divisionList.get(divisionList.size() - 1);
        assertThat(testDivision.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDivision.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testDivision.getNumberOfPeople()).isEqualTo(DEFAULT_NUMBER_OF_PEOPLE);
        assertThat(testDivision.getDivisionType()).isEqualTo(DEFAULT_DIVISION_TYPE);
        assertThat(testDivision.getColorBackground()).isEqualTo(UPDATED_COLOR_BACKGROUND);
        assertThat(testDivision.getColorText()).isEqualTo(DEFAULT_COLOR_TEXT);
    }

    @Test
    void fullUpdateDivisionWithPatch() throws Exception {
        // Initialize the database
        divisionRepository.save(division);

        int databaseSizeBeforeUpdate = divisionRepository.findAll().size();

        // Update the division using partial update
        Division partialUpdatedDivision = new Division();
        partialUpdatedDivision.setId(division.getId());

        partialUpdatedDivision
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .numberOfPeople(UPDATED_NUMBER_OF_PEOPLE)
            .divisionType(UPDATED_DIVISION_TYPE)
            .colorBackground(UPDATED_COLOR_BACKGROUND)
            .colorText(UPDATED_COLOR_TEXT);

        restDivisionMockMvc
            .perform(
                patch("/api/divisions")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDivision))
            )
            .andExpect(status().isOk());

        // Validate the Division in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeUpdate);
        Division testDivision = divisionList.get(divisionList.size() - 1);
        assertThat(testDivision.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDivision.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testDivision.getNumberOfPeople()).isEqualTo(UPDATED_NUMBER_OF_PEOPLE);
        assertThat(testDivision.getDivisionType()).isEqualTo(UPDATED_DIVISION_TYPE);
        assertThat(testDivision.getColorBackground()).isEqualTo(UPDATED_COLOR_BACKGROUND);
        assertThat(testDivision.getColorText()).isEqualTo(UPDATED_COLOR_TEXT);
    }

    @Test
    void partialUpdateDivisionShouldThrown() throws Exception {
        // Update the division without id should throw
        Division partialUpdatedDivision = new Division();

        restDivisionMockMvc
            .perform(
                patch("/api/divisions")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDivision))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteDivision() throws Exception {
        // Initialize the database
        divisionRepository.save(division);

        int databaseSizeBeforeDelete = divisionRepository.findAll().size();

        // Delete the division
        restDivisionMockMvc
            .perform(delete("/api/divisions/{id}", division.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Division> divisionList = divisionRepository.findAll();
        assertThat(divisionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
