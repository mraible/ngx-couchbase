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
import tech.jhipster.sample.domain.EntityWithServiceImplAndDTO;
import tech.jhipster.sample.repository.EntityWithServiceImplAndDTORepository;
import tech.jhipster.sample.service.dto.EntityWithServiceImplAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithServiceImplAndDTOMapper;

/**
 * Integration tests for the {@link EntityWithServiceImplAndDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityWithServiceImplAndDTOResourceIT {

    private static final String DEFAULT_LOUIS = "AAAAAAAAAA";
    private static final String UPDATED_LOUIS = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceImplAndDTORepository entityWithServiceImplAndDTORepository;

    @Autowired
    private EntityWithServiceImplAndDTOMapper entityWithServiceImplAndDTOMapper;

    @Autowired
    private MockMvc restEntityWithServiceImplAndDTOMockMvc;

    private EntityWithServiceImplAndDTO entityWithServiceImplAndDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceImplAndDTO createEntity() {
        EntityWithServiceImplAndDTO entityWithServiceImplAndDTO = new EntityWithServiceImplAndDTO().louis(DEFAULT_LOUIS);
        return entityWithServiceImplAndDTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceImplAndDTO createUpdatedEntity() {
        EntityWithServiceImplAndDTO entityWithServiceImplAndDTO = new EntityWithServiceImplAndDTO().louis(UPDATED_LOUIS);
        return entityWithServiceImplAndDTO;
    }

    @BeforeEach
    public void initTest() {
        entityWithServiceImplAndDTORepository.deleteAll();
        entityWithServiceImplAndDTO = createEntity();
    }

    @Test
    void createEntityWithServiceImplAndDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceImplAndDTORepository.findAll().size();
        // Create the EntityWithServiceImplAndDTO
        EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO = entityWithServiceImplAndDTOMapper.toDto(
            entityWithServiceImplAndDTO
        );
        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                post("/api/entity-with-service-impl-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplAndDTODTO))
            )
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceImplAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndDTO> entityWithServiceImplAndDTOList = entityWithServiceImplAndDTORepository.findAll();
        assertThat(entityWithServiceImplAndDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceImplAndDTO testEntityWithServiceImplAndDTO = entityWithServiceImplAndDTOList.get(
            entityWithServiceImplAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndDTO.getLouis()).isEqualTo(DEFAULT_LOUIS);
    }

    @Test
    void createEntityWithServiceImplAndDTOWithExistingId() throws Exception {
        // Create the EntityWithServiceImplAndDTO with an existing ID
        entityWithServiceImplAndDTO.setId("existing_id");
        EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO = entityWithServiceImplAndDTOMapper.toDto(
            entityWithServiceImplAndDTO
        );

        int databaseSizeBeforeCreate = entityWithServiceImplAndDTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                post("/api/entity-with-service-impl-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceImplAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndDTO> entityWithServiceImplAndDTOList = entityWithServiceImplAndDTORepository.findAll();
        assertThat(entityWithServiceImplAndDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEntityWithServiceImplAndDTOS() throws Exception {
        // Initialize the database
        entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);

        // Get all the entityWithServiceImplAndDTOList
        restEntityWithServiceImplAndDTOMockMvc
            .perform(get("/api/entity-with-service-impl-and-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceImplAndDTO.getId())))
            .andExpect(jsonPath("$.[*].louis").value(hasItem(DEFAULT_LOUIS)));
    }

    @Test
    void getEntityWithServiceImplAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);

        // Get the entityWithServiceImplAndDTO
        restEntityWithServiceImplAndDTOMockMvc
            .perform(get("/api/entity-with-service-impl-and-dtos/{id}", entityWithServiceImplAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceImplAndDTO.getId()))
            .andExpect(jsonPath("$.louis").value(DEFAULT_LOUIS));
    }

    @Test
    void getNonExistingEntityWithServiceImplAndDTO() throws Exception {
        // Get the entityWithServiceImplAndDTO
        restEntityWithServiceImplAndDTOMockMvc
            .perform(get("/api/entity-with-service-impl-and-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateEntityWithServiceImplAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceImplAndDTORepository.findAll().size();

        // Update the entityWithServiceImplAndDTO
        EntityWithServiceImplAndDTO updatedEntityWithServiceImplAndDTO = entityWithServiceImplAndDTORepository
            .findById(entityWithServiceImplAndDTO.getId())
            .get();
        updatedEntityWithServiceImplAndDTO.louis(UPDATED_LOUIS);
        EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO = entityWithServiceImplAndDTOMapper.toDto(
            updatedEntityWithServiceImplAndDTO
        );

        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                put("/api/entity-with-service-impl-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplAndDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndDTO> entityWithServiceImplAndDTOList = entityWithServiceImplAndDTORepository.findAll();
        assertThat(entityWithServiceImplAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplAndDTO testEntityWithServiceImplAndDTO = entityWithServiceImplAndDTOList.get(
            entityWithServiceImplAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndDTO.getLouis()).isEqualTo(UPDATED_LOUIS);
    }

    @Test
    void updateNonExistingEntityWithServiceImplAndDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceImplAndDTORepository.findAll().size();

        // Create the EntityWithServiceImplAndDTO
        EntityWithServiceImplAndDTODTO entityWithServiceImplAndDTODTO = entityWithServiceImplAndDTOMapper.toDto(
            entityWithServiceImplAndDTO
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                put("/api/entity-with-service-impl-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceImplAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndDTO> entityWithServiceImplAndDTOList = entityWithServiceImplAndDTORepository.findAll();
        assertThat(entityWithServiceImplAndDTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEntityWithServiceImplAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceImplAndDTORepository.findAll().size();

        // Update the entityWithServiceImplAndDTO using partial update
        EntityWithServiceImplAndDTO partialUpdatedEntityWithServiceImplAndDTO = new EntityWithServiceImplAndDTO();
        partialUpdatedEntityWithServiceImplAndDTO.setId(entityWithServiceImplAndDTO.getId());

        partialUpdatedEntityWithServiceImplAndDTO.louis(UPDATED_LOUIS);

        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-impl-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndDTO> entityWithServiceImplAndDTOList = entityWithServiceImplAndDTORepository.findAll();
        assertThat(entityWithServiceImplAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplAndDTO testEntityWithServiceImplAndDTO = entityWithServiceImplAndDTOList.get(
            entityWithServiceImplAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndDTO.getLouis()).isEqualTo(UPDATED_LOUIS);
    }

    @Test
    void fullUpdateEntityWithServiceImplAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceImplAndDTORepository.findAll().size();

        // Update the entityWithServiceImplAndDTO using partial update
        EntityWithServiceImplAndDTO partialUpdatedEntityWithServiceImplAndDTO = new EntityWithServiceImplAndDTO();
        partialUpdatedEntityWithServiceImplAndDTO.setId(entityWithServiceImplAndDTO.getId());

        partialUpdatedEntityWithServiceImplAndDTO.louis(UPDATED_LOUIS);

        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-impl-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndDTO> entityWithServiceImplAndDTOList = entityWithServiceImplAndDTORepository.findAll();
        assertThat(entityWithServiceImplAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplAndDTO testEntityWithServiceImplAndDTO = entityWithServiceImplAndDTOList.get(
            entityWithServiceImplAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndDTO.getLouis()).isEqualTo(UPDATED_LOUIS);
    }

    @Test
    void partialUpdateEntityWithServiceImplAndDTOShouldThrown() throws Exception {
        // Update the entityWithServiceImplAndDTO without id should throw
        EntityWithServiceImplAndDTO partialUpdatedEntityWithServiceImplAndDTO = new EntityWithServiceImplAndDTO();

        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-impl-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplAndDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntityWithServiceImplAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceImplAndDTORepository.save(entityWithServiceImplAndDTO);

        int databaseSizeBeforeDelete = entityWithServiceImplAndDTORepository.findAll().size();

        // Delete the entityWithServiceImplAndDTO
        restEntityWithServiceImplAndDTOMockMvc
            .perform(
                delete("/api/entity-with-service-impl-and-dtos/{id}", entityWithServiceImplAndDTO.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndDTO> entityWithServiceImplAndDTOList = entityWithServiceImplAndDTORepository.findAll();
        assertThat(entityWithServiceImplAndDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
