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
import tech.jhipster.sample.domain.EntityWithServiceClassPaginationAndDTO;
import tech.jhipster.sample.repository.EntityWithServiceClassPaginationAndDTORepository;
import tech.jhipster.sample.service.dto.EntityWithServiceClassPaginationAndDTODTO;
import tech.jhipster.sample.service.mapper.EntityWithServiceClassPaginationAndDTOMapper;

/**
 * Integration tests for the {@link EntityWithServiceClassPaginationAndDTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityWithServiceClassPaginationAndDTOResourceIT {

    private static final String DEFAULT_LENA = "AAAAAAAAAA";
    private static final String UPDATED_LENA = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceClassPaginationAndDTORepository entityWithServiceClassPaginationAndDTORepository;

    @Autowired
    private EntityWithServiceClassPaginationAndDTOMapper entityWithServiceClassPaginationAndDTOMapper;

    @Autowired
    private MockMvc restEntityWithServiceClassPaginationAndDTOMockMvc;

    private EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceClassPaginationAndDTO createEntity() {
        EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO = new EntityWithServiceClassPaginationAndDTO()
        .lena(DEFAULT_LENA);
        return entityWithServiceClassPaginationAndDTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceClassPaginationAndDTO createUpdatedEntity() {
        EntityWithServiceClassPaginationAndDTO entityWithServiceClassPaginationAndDTO = new EntityWithServiceClassPaginationAndDTO()
        .lena(UPDATED_LENA);
        return entityWithServiceClassPaginationAndDTO;
    }

    @BeforeEach
    public void initTest() {
        entityWithServiceClassPaginationAndDTORepository.deleteAll();
        entityWithServiceClassPaginationAndDTO = createEntity();
    }

    @Test
    void createEntityWithServiceClassPaginationAndDTO() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceClassPaginationAndDTORepository.findAll().size();
        // Create the EntityWithServiceClassPaginationAndDTO
        EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO = entityWithServiceClassPaginationAndDTOMapper.toDto(
            entityWithServiceClassPaginationAndDTO
        );
        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                post("/api/entity-with-service-class-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassPaginationAndDTODTO))
            )
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceClassPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassPaginationAndDTO> entityWithServiceClassPaginationAndDTOList = entityWithServiceClassPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceClassPaginationAndDTOList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceClassPaginationAndDTO testEntityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTOList.get(
            entityWithServiceClassPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceClassPaginationAndDTO.getLena()).isEqualTo(DEFAULT_LENA);
    }

    @Test
    void createEntityWithServiceClassPaginationAndDTOWithExistingId() throws Exception {
        // Create the EntityWithServiceClassPaginationAndDTO with an existing ID
        entityWithServiceClassPaginationAndDTO.setId("existing_id");
        EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO = entityWithServiceClassPaginationAndDTOMapper.toDto(
            entityWithServiceClassPaginationAndDTO
        );

        int databaseSizeBeforeCreate = entityWithServiceClassPaginationAndDTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                post("/api/entity-with-service-class-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassPaginationAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceClassPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassPaginationAndDTO> entityWithServiceClassPaginationAndDTOList = entityWithServiceClassPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceClassPaginationAndDTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEntityWithServiceClassPaginationAndDTOS() throws Exception {
        // Initialize the database
        entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);

        // Get all the entityWithServiceClassPaginationAndDTOList
        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-service-class-pagination-and-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceClassPaginationAndDTO.getId())))
            .andExpect(jsonPath("$.[*].lena").value(hasItem(DEFAULT_LENA)));
    }

    @Test
    void getEntityWithServiceClassPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);

        // Get the entityWithServiceClassPaginationAndDTO
        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-service-class-pagination-and-dtos/{id}", entityWithServiceClassPaginationAndDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceClassPaginationAndDTO.getId()))
            .andExpect(jsonPath("$.lena").value(DEFAULT_LENA));
    }

    @Test
    void getNonExistingEntityWithServiceClassPaginationAndDTO() throws Exception {
        // Get the entityWithServiceClassPaginationAndDTO
        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(get("/api/entity-with-service-class-pagination-and-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateEntityWithServiceClassPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceClassPaginationAndDTORepository.findAll().size();

        // Update the entityWithServiceClassPaginationAndDTO
        EntityWithServiceClassPaginationAndDTO updatedEntityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTORepository
            .findById(entityWithServiceClassPaginationAndDTO.getId())
            .get();
        updatedEntityWithServiceClassPaginationAndDTO.lena(UPDATED_LENA);
        EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO = entityWithServiceClassPaginationAndDTOMapper.toDto(
            updatedEntityWithServiceClassPaginationAndDTO
        );

        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                put("/api/entity-with-service-class-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassPaginationAndDTODTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassPaginationAndDTO> entityWithServiceClassPaginationAndDTOList = entityWithServiceClassPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceClassPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassPaginationAndDTO testEntityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTOList.get(
            entityWithServiceClassPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceClassPaginationAndDTO.getLena()).isEqualTo(UPDATED_LENA);
    }

    @Test
    void updateNonExistingEntityWithServiceClassPaginationAndDTO() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceClassPaginationAndDTORepository.findAll().size();

        // Create the EntityWithServiceClassPaginationAndDTO
        EntityWithServiceClassPaginationAndDTODTO entityWithServiceClassPaginationAndDTODTO = entityWithServiceClassPaginationAndDTOMapper.toDto(
            entityWithServiceClassPaginationAndDTO
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                put("/api/entity-with-service-class-pagination-and-dtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassPaginationAndDTODTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceClassPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassPaginationAndDTO> entityWithServiceClassPaginationAndDTOList = entityWithServiceClassPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceClassPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEntityWithServiceClassPaginationAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceClassPaginationAndDTORepository.findAll().size();

        // Update the entityWithServiceClassPaginationAndDTO using partial update
        EntityWithServiceClassPaginationAndDTO partialUpdatedEntityWithServiceClassPaginationAndDTO = new EntityWithServiceClassPaginationAndDTO();
        partialUpdatedEntityWithServiceClassPaginationAndDTO.setId(entityWithServiceClassPaginationAndDTO.getId());

        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-class-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceClassPaginationAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassPaginationAndDTO> entityWithServiceClassPaginationAndDTOList = entityWithServiceClassPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceClassPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassPaginationAndDTO testEntityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTOList.get(
            entityWithServiceClassPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceClassPaginationAndDTO.getLena()).isEqualTo(DEFAULT_LENA);
    }

    @Test
    void fullUpdateEntityWithServiceClassPaginationAndDTOWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);

        int databaseSizeBeforeUpdate = entityWithServiceClassPaginationAndDTORepository.findAll().size();

        // Update the entityWithServiceClassPaginationAndDTO using partial update
        EntityWithServiceClassPaginationAndDTO partialUpdatedEntityWithServiceClassPaginationAndDTO = new EntityWithServiceClassPaginationAndDTO();
        partialUpdatedEntityWithServiceClassPaginationAndDTO.setId(entityWithServiceClassPaginationAndDTO.getId());

        partialUpdatedEntityWithServiceClassPaginationAndDTO.lena(UPDATED_LENA);

        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-class-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceClassPaginationAndDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassPaginationAndDTO in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassPaginationAndDTO> entityWithServiceClassPaginationAndDTOList = entityWithServiceClassPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceClassPaginationAndDTOList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassPaginationAndDTO testEntityWithServiceClassPaginationAndDTO = entityWithServiceClassPaginationAndDTOList.get(
            entityWithServiceClassPaginationAndDTOList.size() - 1
        );
        assertThat(testEntityWithServiceClassPaginationAndDTO.getLena()).isEqualTo(UPDATED_LENA);
    }

    @Test
    void partialUpdateEntityWithServiceClassPaginationAndDTOShouldThrown() throws Exception {
        // Update the entityWithServiceClassPaginationAndDTO without id should throw
        EntityWithServiceClassPaginationAndDTO partialUpdatedEntityWithServiceClassPaginationAndDTO = new EntityWithServiceClassPaginationAndDTO();

        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                patch("/api/entity-with-service-class-pagination-and-dtos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceClassPaginationAndDTO))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntityWithServiceClassPaginationAndDTO() throws Exception {
        // Initialize the database
        entityWithServiceClassPaginationAndDTORepository.save(entityWithServiceClassPaginationAndDTO);

        int databaseSizeBeforeDelete = entityWithServiceClassPaginationAndDTORepository.findAll().size();

        // Delete the entityWithServiceClassPaginationAndDTO
        restEntityWithServiceClassPaginationAndDTOMockMvc
            .perform(
                delete("/api/entity-with-service-class-pagination-and-dtos/{id}", entityWithServiceClassPaginationAndDTO.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassPaginationAndDTO> entityWithServiceClassPaginationAndDTOList = entityWithServiceClassPaginationAndDTORepository.findAll();
        assertThat(entityWithServiceClassPaginationAndDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
