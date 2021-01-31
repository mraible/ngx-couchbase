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
import tech.jhipster.sample.domain.EntityWithPaginationAndDTO;
import tech.jhipster.sample.repository.EntityWithPaginationAndDTORepository;
import tech.jhipster.sample.service.dto.EntityWithPaginationAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithPaginationAndDTOMapper;

/**
 * Integration tests for the {@link EntityWithPaginationAndDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityWithPaginationAndDTOResourceIT {

    private static final String DEFAULT_LEA = "AAAAAAAAAA";
    private static final String UPDATED_LEA = "BBBBBBBBBB";

    @Autowired
    private EntityWithPaginationAndDTORepository entityWithPaginationAndDTORepository;

    @Autowired
    private EntityWithPaginationAndDTOMapper entityWithPaginationAndDTOMapper;

    @Autowired
    private MockMvc restEntityWithPaginationAndDTOMockMvc;

    private EntityWithPaginationAndDTO entityWithPaginationAndDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithPaginationAndDTO createEntity() {
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = new EntityWithPaginationAndDTO().lea(DEFAULT_LEA);
        return entityWithPaginationAndDTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithPaginationAndDTO createUpdatedEntity() {
        EntityWithPaginationAndDTO entityWithPaginationAndDTO = new EntityWithPaginationAndDTO().lea(UPDATED_LEA);
        return entityWithPaginationAndDTO;
    }

    @BeforeEach
    public void initTest() {
        entityWithPaginationAndDTORepository.deleteAll();
        entityWithPaginationAndDTO = createEntity();
    }

    @Test
    void createEntityWithPaginationAndDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithPaginationAndDTORepository.findAll().size();
        // Create the EntityWithPaginationAndDTO
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);
        restEntityWithPaginationAndDTOMockMvc
            .perform(
                post("/api/entity-with-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO))
            )
            .andExpect(status().isCreated());

        // Validate the EntityWithPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithPaginationAndDTO testEntityWithPaginationAndDTO = entityWithPaginationAndDTOList.get(
            entityWithPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithPaginationAndDTO.getLea()).isEqualTo(DEFAULT_LEA);
    }

    @Test
    void createEntityWithPaginationAndDTOWithExistingId() throws Exception {
        // Create the EntityWithPaginationAndDTO with an existing ID
        entityWithPaginationAndDTO.setId("existing_id");
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);

        int databaseSizeBeforeCreate = entityWithPaginationAndDTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithPaginationAndDTOMockMvc
            .perform(
                post("/api/entity-with-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEntityWithPaginationAndDTOS() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);

        // Get all the entityWithPaginationAndDTOList
        restEntityWithPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-pagination-and-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithPaginationAndDTO.getId())))
            .andExpect(jsonPath("$.[*].lea").value(hasItem(DEFAULT_LEA)));
    }

    @Test
    void getEntityWithPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);

        // Get the entityWithPaginationAndDTO
        restEntityWithPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-pagination-and-dtos/{id}", entityWithPaginationAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithPaginationAndDTO.getId()))
            .andExpect(jsonPath("$.lea").value(DEFAULT_LEA));
    }

    @Test
    void getNonExistingEntityWithPaginationAndDTO() throws Exception {
        // Get the entityWithPaginationAndDTO
        restEntityWithPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-pagination-and-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateEntityWithPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithPaginationAndDTORepository.findAll().size();

        // Update the entityWithPaginationAndDTO
        EntityWithPaginationAndDTO updatedEntityWithPaginationAndDTO = entityWithPaginationAndDTORepository
            .findById(entityWithPaginationAndDTO.getId())
            .get();
        updatedEntityWithPaginationAndDTO.lea(UPDATED_LEA);
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(
            updatedEntityWithPaginationAndDTO
        );

        restEntityWithPaginationAndDTOMockMvc
            .perform(
                put("/api/entity-with-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithPaginationAndDTO testEntityWithPaginationAndDTO = entityWithPaginationAndDTOList.get(
            entityWithPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithPaginationAndDTO.getLea()).isEqualTo(UPDATED_LEA);
    }

    @Test
    void updateNonExistingEntityWithPaginationAndDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithPaginationAndDTORepository.findAll().size();

        // Create the EntityWithPaginationAndDTO
        EntityWithPaginationAndDTODTO entityWithPaginationAndDTODTO = entityWithPaginationAndDTOMapper.toDto(entityWithPaginationAndDTO);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityWithPaginationAndDTOMockMvc
            .perform(
                put("/api/entity-with-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithPaginationAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEntityWithPaginationAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithPaginationAndDTORepository.findAll().size();

        // Update the entityWithPaginationAndDTO using partial update
        EntityWithPaginationAndDTO partialUpdatedEntityWithPaginationAndDTO = new EntityWithPaginationAndDTO();
        partialUpdatedEntityWithPaginationAndDTO.setId(entityWithPaginationAndDTO.getId());

        restEntityWithPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithPaginationAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithPaginationAndDTO testEntityWithPaginationAndDTO = entityWithPaginationAndDTOList.get(
            entityWithPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithPaginationAndDTO.getLea()).isEqualTo(DEFAULT_LEA);
    }

    @Test
    void fullUpdateEntityWithPaginationAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithPaginationAndDTORepository.findAll().size();

        // Update the entityWithPaginationAndDTO using partial update
        EntityWithPaginationAndDTO partialUpdatedEntityWithPaginationAndDTO = new EntityWithPaginationAndDTO();
        partialUpdatedEntityWithPaginationAndDTO.setId(entityWithPaginationAndDTO.getId());

        partialUpdatedEntityWithPaginationAndDTO.lea(UPDATED_LEA);

        restEntityWithPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithPaginationAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithPaginationAndDTO testEntityWithPaginationAndDTO = entityWithPaginationAndDTOList.get(
            entityWithPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithPaginationAndDTO.getLea()).isEqualTo(UPDATED_LEA);
    }

    @Test
    void partialUpdateEntityWithPaginationAndDTOShouldThrown() throws Exception {
        // Update the entityWithPaginationAndDTO without id should throw
        EntityWithPaginationAndDTO partialUpdatedEntityWithPaginationAndDTO = new EntityWithPaginationAndDTO();

        restEntityWithPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithPaginationAndDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntityWithPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithPaginationAndDTORepository.save(entityWithPaginationAndDTO);

        int databaseSizeBeforeDelete = entityWithPaginationAndDTORepository.findAll().size();

        // Delete the entityWithPaginationAndDTO
        restEntityWithPaginationAndDTOMockMvc
            .perform(
                delete("/api/entity-with-pagination-and-dtos/{id}", entityWithPaginationAndDTO.getId()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithPaginationAndDTO> entityWithPaginationAndDTOList = entityWithPaginationAndDTORepository.findAll();
        assertThat(entityWithPaginationAndDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
