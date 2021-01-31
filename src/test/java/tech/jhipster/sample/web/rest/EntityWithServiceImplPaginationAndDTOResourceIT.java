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
import tech.jhipster.sample.domain.EntityWithServiceImplPaginationAndDTO;
import tech.jhipster.sample.repository.EntityWithServiceImplPaginationAndDTORepository;
import tech.jhipster.sample.service.dto.EntityWithServiceImplPaginationAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithServiceImplPaginationAndDTOMapper;

/**
 * Integration tests for the {@link EntityWithServiceImplPaginationAndDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityWithServiceImplPaginationAndDTOResourceIT {

    private static final String DEFAULT_THEO = "AAAAAAAAAA";
    private static final String UPDATED_THEO = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceImplPaginationAndDTORepository entityWithServiceImplPaginationAndDTORepository;

    @Autowired
    private EntityWithServiceImplPaginationAndDTOMapper entityWithServiceImplPaginationAndDTOMapper;

    @Autowired
    private MockMvc restEntityWithServiceImplPaginationAndDTOMockMvc;

    private EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceImplPaginationAndDTO createEntity() {
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = new EntityWithServiceImplPaginationAndDTO()
        .theo(DEFAULT_THEO);
        return entityWithServiceImplPaginationAndDTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceImplPaginationAndDTO createUpdatedEntity() {
        EntityWithServiceImplPaginationAndDTO entityWithServiceImplPaginationAndDTO = new EntityWithServiceImplPaginationAndDTO()
        .theo(UPDATED_THEO);
        return entityWithServiceImplPaginationAndDTO;
    }

    @BeforeEach
    public void initTest() {
        entityWithServiceImplPaginationAndDTORepository.deleteAll();
        entityWithServiceImplPaginationAndDTO = createEntity();
    }

    @Test
    void createEntityWithServiceImplPaginationAndDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceImplPaginationAndDTORepository.findAll().size();
        // Create the EntityWithServiceImplPaginationAndDTO
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(
            entityWithServiceImplPaginationAndDTO
        );
        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                post("/api/entity-with-service-impl-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO))
            )
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceImplPaginationAndDTO testEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOList.get(
            entityWithServiceImplPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplPaginationAndDTO.getTheo()).isEqualTo(DEFAULT_THEO);
    }

    @Test
    void createEntityWithServiceImplPaginationAndDTOWithExistingId() throws Exception {
        // Create the EntityWithServiceImplPaginationAndDTO with an existing ID
        entityWithServiceImplPaginationAndDTO.setId("existing_id");
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(
            entityWithServiceImplPaginationAndDTO
        );

        int databaseSizeBeforeCreate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                post("/api/entity-with-service-impl-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEntityWithServiceImplPaginationAndDTOS() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);

        // Get all the entityWithServiceImplPaginationAndDTOList
        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-service-impl-pagination-and-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceImplPaginationAndDTO.getId())))
            .andExpect(jsonPath("$.[*].theo").value(hasItem(DEFAULT_THEO)));
    }

    @Test
    void getEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);

        // Get the entityWithServiceImplPaginationAndDTO
        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-service-impl-pagination-and-dtos/{id}", entityWithServiceImplPaginationAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceImplPaginationAndDTO.getId()))
            .andExpect(jsonPath("$.theo").value(DEFAULT_THEO));
    }

    @Test
    void getNonExistingEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Get the entityWithServiceImplPaginationAndDTO
        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-service-impl-pagination-and-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Update the entityWithServiceImplPaginationAndDTO
        EntityWithServiceImplPaginationAndDTO updatedEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTORepository
            .findById(entityWithServiceImplPaginationAndDTO.getId())
            .get();
        updatedEntityWithServiceImplPaginationAndDTO.theo(UPDATED_THEO);
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(
            updatedEntityWithServiceImplPaginationAndDTO
        );

        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                put("/api/entity-with-service-impl-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplPaginationAndDTO testEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOList.get(
            entityWithServiceImplPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplPaginationAndDTO.getTheo()).isEqualTo(UPDATED_THEO);
    }

    @Test
    void updateNonExistingEntityWithServiceImplPaginationAndDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Create the EntityWithServiceImplPaginationAndDTO
        EntityWithServiceImplPaginationAndDTODTO entityWithServiceImplPaginationAndDTODTO = entityWithServiceImplPaginationAndDTOMapper.toDto(
            entityWithServiceImplPaginationAndDTO
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                put("/api/entity-with-service-impl-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplPaginationAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEntityWithServiceImplPaginationAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Update the entityWithServiceImplPaginationAndDTO using partial update
        EntityWithServiceImplPaginationAndDTO partialUpdatedEntityWithServiceImplPaginationAndDTO = new EntityWithServiceImplPaginationAndDTO();
        partialUpdatedEntityWithServiceImplPaginationAndDTO.setId(entityWithServiceImplPaginationAndDTO.getId());

        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-impl-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplPaginationAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplPaginationAndDTO testEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOList.get(
            entityWithServiceImplPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplPaginationAndDTO.getTheo()).isEqualTo(DEFAULT_THEO);
    }

    @Test
    void fullUpdateEntityWithServiceImplPaginationAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Update the entityWithServiceImplPaginationAndDTO using partial update
        EntityWithServiceImplPaginationAndDTO partialUpdatedEntityWithServiceImplPaginationAndDTO = new EntityWithServiceImplPaginationAndDTO();
        partialUpdatedEntityWithServiceImplPaginationAndDTO.setId(entityWithServiceImplPaginationAndDTO.getId());

        partialUpdatedEntityWithServiceImplPaginationAndDTO.theo(UPDATED_THEO);

        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-impl-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplPaginationAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplPaginationAndDTO testEntityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTOList.get(
            entityWithServiceImplPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplPaginationAndDTO.getTheo()).isEqualTo(UPDATED_THEO);
    }

    @Test
    void partialUpdateEntityWithServiceImplPaginationAndDTOShouldThrown() throws Exception {
        // Update the entityWithServiceImplPaginationAndDTO without id should throw
        EntityWithServiceImplPaginationAndDTO partialUpdatedEntityWithServiceImplPaginationAndDTO = new EntityWithServiceImplPaginationAndDTO();

        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-impl-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplPaginationAndDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntityWithServiceImplPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplPaginationAndDTORepository.save(entityWithServiceImplPaginationAndDTO);

        int databaseSizeBeforeDelete = entityWithServiceImplPaginationAndDTORepository.findAll().size();

        // Delete the entityWithServiceImplPaginationAndDTO
        restEntityWithServiceImplPaginationAndDTOMockMvc
            .perform(
                delete("/api/entity-with-service-impl-pagination-and-dtos/{id}", entityWithServiceImplPaginationAndDTO.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplPaginationAndDTO> entityWithServiceImplPaginationAndDTOList = entityWithServiceImplPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceImplPaginationAndDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
