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
import tech.jhipster.sample.domain.EntityWithDTO;
import tech.jhipster.sample.repository.EntityWithDTORepository;
import tech.jhipster.sample.service.dto.EntityWithDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithDTOMapper;

/**
 * Integration tests for the {@link EntityWithDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityWithDTOResourceIT {

    private static final String DEFAULT_EMMA = "AAAAAAAAAA";
    private static final String UPDATED_EMMA = "BBBBBBBBBB";

    @Autowired
    private EntityWithDTORepository entityWithDTORepository;

    @Autowired
    private EntityWithDTOMapper entityWithDTOMapper;

    @Autowired
    private MockMvc restEntityWithDTOMockMvc;

    private EntityWithDTO entityWithDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithDTO createEntity() {
        EntityWithDTO entityWithDTO = new EntityWithDTO().emma(DEFAULT_EMMA);
        return entityWithDTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithDTO createUpdatedEntity() {
        EntityWithDTO entityWithDTO = new EntityWithDTO().emma(UPDATED_EMMA);
        return entityWithDTO;
    }

    @BeforeEach
    public void initTest() {
        entityWithDTORepository.deleteAll();
        entityWithDTO = createEntity();
    }

    @Test
    void createEntityWithDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithDTORepository.findAll().size();
        // Create the EntityWithDTO
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(entityWithDTO);
        restEntityWithDTOMockMvc
            .perform(
                post("/api/entity-with-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO))
            )
            .andExpect(status().isCreated());

        // Validate the EntityWithDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithDTO testEntityWithDTO = entityWithDTOList.get(entityWithDTOList.size() - 1);
        assertThat(testEntityWithDTO.getEmma()).isEqualTo(DEFAULT_EMMA);
    }

    @Test
    void createEntityWithDTOWithExistingId() throws Exception {
        // Create the EntityWithDTO with an existing ID
        entityWithDTO.setId("existing_id");
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(entityWithDTO);

        int databaseSizeBeforeCreate = entityWithDTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithDTOMockMvc
            .perform(
                post("/api/entity-with-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEntityWithDTOS() throws Exception {
        // Initialize the database
        entityWithDTORepository.save(entityWithDTO);

        // Get all the entityWithDTOList
        restEntityWithDTOMockMvc
            .perform(get("/api/entity-with-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithDTO.getId())))
            .andExpect(jsonPath("$.[*].emma").value(hasItem(DEFAULT_EMMA)));
    }

    @Test
    void getEntityWithDTO() throws Exception {
        // Initialize the database
        entityWithDTORepository.save(entityWithDTO);

        // Get the entityWithDTO
        restEntityWithDTOMockMvc
            .perform(get("/api/entity-with-dtos/{id}", entityWithDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithDTO.getId()))
            .andExpect(jsonPath("$.emma").value(DEFAULT_EMMA));
    }

    @Test
    void getNonExistingEntityWithDTO() throws Exception {
        // Get the entityWithDTO
        restEntityWithDTOMockMvc.perform(get("/api/entity-with-dtos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void updateEntityWithDTO() throws Exception {
        // Initialize the database
        entityWithDTORepository.save(entityWithDTO);

        int databaseSizeBeforeUpdate = entityWithDTORepository.findAll().size();

        // Update the entityWithDTO
        EntityWithDTO updatedEntityWithDTO = entityWithDTORepository.findById(entityWithDTO.getId()).get();
        updatedEntityWithDTO.emma(UPDATED_EMMA);
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(updatedEntityWithDTO);

        restEntityWithDTOMockMvc
            .perform(
                put("/api/entity-with-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithDTO testEntityWithDTO = entityWithDTOList.get(entityWithDTOList.size() - 1);
        assertThat(testEntityWithDTO.getEmma()).isEqualTo(UPDATED_EMMA);
    }

    @Test
    void updateNonExistingEntityWithDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithDTORepository.findAll().size();

        // Create the EntityWithDTO
        EntityWithDTODTO entityWithDTODTO = entityWithDTOMapper.toDto(entityWithDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityWithDTOMockMvc
            .perform(
                put("/api/entity-with-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEntityWithDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithDTORepository.save(entityWithDTO);

        int databaseSizeBeforeUpdate = entityWithDTORepository.findAll().size();

        // Update the entityWithDTO using partial update
        EntityWithDTO partialUpdatedEntityWithDTO = new EntityWithDTO();
        partialUpdatedEntityWithDTO.setId(entityWithDTO.getId());

        restEntityWithDTOMockMvc
            .perform(
                patch("/api/entity-with-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithDTO testEntityWithDTO = entityWithDTOList.get(entityWithDTOList.size() - 1);
        assertThat(testEntityWithDTO.getEmma()).isEqualTo(DEFAULT_EMMA);
    }

    @Test
    void fullUpdateEntityWithDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithDTORepository.save(entityWithDTO);

        int databaseSizeBeforeUpdate = entityWithDTORepository.findAll().size();

        // Update the entityWithDTO using partial update
        EntityWithDTO partialUpdatedEntityWithDTO = new EntityWithDTO();
        partialUpdatedEntityWithDTO.setId(entityWithDTO.getId());

        partialUpdatedEntityWithDTO.emma(UPDATED_EMMA);

        restEntityWithDTOMockMvc
            .perform(
                patch("/api/entity-with-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithDTO testEntityWithDTO = entityWithDTOList.get(entityWithDTOList.size() - 1);
        assertThat(testEntityWithDTO.getEmma()).isEqualTo(UPDATED_EMMA);
    }

    @Test
    void partialUpdateEntityWithDTOShouldThrown() throws Exception {
        // Update the entityWithDTO without id should throw
        EntityWithDTO partialUpdatedEntityWithDTO = new EntityWithDTO();

        restEntityWithDTOMockMvc
            .perform(
                patch("/api/entity-with-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntityWithDTO() throws Exception {
        // Initialize the database
        entityWithDTORepository.save(entityWithDTO);

        int databaseSizeBeforeDelete = entityWithDTORepository.findAll().size();

        // Delete the entityWithDTO
        restEntityWithDTOMockMvc
            .perform(delete("/api/entity-with-dtos/{id}", entityWithDTO.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithDTO> entityWithDTOList = entityWithDTORepository.findAll();
        assertThat(entityWithDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
