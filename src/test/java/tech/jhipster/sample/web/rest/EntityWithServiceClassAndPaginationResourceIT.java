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
import tech.jhipster.sample.domain.EntityWithServiceClassAndPagination;
import tech.jhipster.sample.repository.EntityWithServiceClassAndPaginationRepository;

/**
 * Integration tests for the {@link EntityWithServiceClassAndPaginationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityWithServiceClassAndPaginationResourceIT {

    private static final String DEFAULT_ENZO = "AAAAAAAAAA";
    private static final String UPDATED_ENZO = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceClassAndPaginationRepository entityWithServiceClassAndPaginationRepository;

    @Autowired
    private MockMvc restEntityWithServiceClassAndPaginationMockMvc;

    private EntityWithServiceClassAndPagination entityWithServiceClassAndPagination;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceClassAndPagination createEntity() {
        EntityWithServiceClassAndPagination entityWithServiceClassAndPagination = new EntityWithServiceClassAndPagination()
        .enzo(DEFAULT_ENZO);
        return entityWithServiceClassAndPagination;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceClassAndPagination createUpdatedEntity() {
        EntityWithServiceClassAndPagination entityWithServiceClassAndPagination = new EntityWithServiceClassAndPagination()
        .enzo(UPDATED_ENZO);
        return entityWithServiceClassAndPagination;
    }

    @BeforeEach
    public void initTest() {
        entityWithServiceClassAndPaginationRepository.deleteAll();
        entityWithServiceClassAndPagination = createEntity();
    }

    @Test
    void createEntityWithServiceClassAndPagination() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceClassAndPaginationRepository.findAll().size();
        // Create the EntityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                post("/api/entity-with-service-class-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndPagination))
            )
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceClassAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceClassAndPagination testEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationList.get(
            entityWithServiceClassAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceClassAndPagination.getEnzo()).isEqualTo(DEFAULT_ENZO);
    }

    @Test
    void createEntityWithServiceClassAndPaginationWithExistingId() throws Exception {
        // Create the EntityWithServiceClassAndPagination with an existing ID
        entityWithServiceClassAndPagination.setId("existing_id");

        int databaseSizeBeforeCreate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                post("/api/entity-with-service-class-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndPagination))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceClassAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEntityWithServiceClassAndPaginations() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);

        // Get all the entityWithServiceClassAndPaginationList
        restEntityWithServiceClassAndPaginationMockMvc
            .perform(get("/api/entity-with-service-class-and-paginations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceClassAndPagination.getId())))
            .andExpect(jsonPath("$.[*].enzo").value(hasItem(DEFAULT_ENZO)));
    }

    @Test
    void getEntityWithServiceClassAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);

        // Get the entityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc
            .perform(get("/api/entity-with-service-class-and-paginations/{id}", entityWithServiceClassAndPagination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceClassAndPagination.getId()))
            .andExpect(jsonPath("$.enzo").value(DEFAULT_ENZO));
    }

    @Test
    void getNonExistingEntityWithServiceClassAndPagination() throws Exception {
        // Get the entityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc
            .perform(get("/api/entity-with-service-class-and-paginations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateEntityWithServiceClassAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);

        int databaseSizeBeforeUpdate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Update the entityWithServiceClassAndPagination
        EntityWithServiceClassAndPagination updatedEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationRepository
            .findById(entityWithServiceClassAndPagination.getId())
            .get();
        updatedEntityWithServiceClassAndPagination.enzo(UPDATED_ENZO);

        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                put("/api/entity-with-service-class-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEntityWithServiceClassAndPagination))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassAndPagination testEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationList.get(
            entityWithServiceClassAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceClassAndPagination.getEnzo()).isEqualTo(UPDATED_ENZO);
    }

    @Test
    void updateNonExistingEntityWithServiceClassAndPagination() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                put("/api/entity-with-service-class-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceClassAndPagination))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceClassAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEntityWithServiceClassAndPaginationWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);

        int databaseSizeBeforeUpdate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Update the entityWithServiceClassAndPagination using partial update
        EntityWithServiceClassAndPagination partialUpdatedEntityWithServiceClassAndPagination = new EntityWithServiceClassAndPagination();
        partialUpdatedEntityWithServiceClassAndPagination.setId(entityWithServiceClassAndPagination.getId());

        partialUpdatedEntityWithServiceClassAndPagination.enzo(UPDATED_ENZO);

        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                patch("/api/entity-with-service-class-and-paginations")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceClassAndPagination))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassAndPagination testEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationList.get(
            entityWithServiceClassAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceClassAndPagination.getEnzo()).isEqualTo(UPDATED_ENZO);
    }

    @Test
    void fullUpdateEntityWithServiceClassAndPaginationWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);

        int databaseSizeBeforeUpdate = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Update the entityWithServiceClassAndPagination using partial update
        EntityWithServiceClassAndPagination partialUpdatedEntityWithServiceClassAndPagination = new EntityWithServiceClassAndPagination();
        partialUpdatedEntityWithServiceClassAndPagination.setId(entityWithServiceClassAndPagination.getId());

        partialUpdatedEntityWithServiceClassAndPagination.enzo(UPDATED_ENZO);

        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                patch("/api/entity-with-service-class-and-paginations")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceClassAndPagination))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceClassAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceClassAndPagination testEntityWithServiceClassAndPagination = entityWithServiceClassAndPaginationList.get(
            entityWithServiceClassAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceClassAndPagination.getEnzo()).isEqualTo(UPDATED_ENZO);
    }

    @Test
    void partialUpdateEntityWithServiceClassAndPaginationShouldThrown() throws Exception {
        // Update the entityWithServiceClassAndPagination without id should throw
        EntityWithServiceClassAndPagination partialUpdatedEntityWithServiceClassAndPagination = new EntityWithServiceClassAndPagination();

        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                patch("/api/entity-with-service-class-and-paginations")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceClassAndPagination))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntityWithServiceClassAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceClassAndPaginationRepository.save(entityWithServiceClassAndPagination);

        int databaseSizeBeforeDelete = entityWithServiceClassAndPaginationRepository.findAll().size();

        // Delete the entityWithServiceClassAndPagination
        restEntityWithServiceClassAndPaginationMockMvc
            .perform(
                delete("/api/entity-with-service-class-and-paginations/{id}", entityWithServiceClassAndPagination.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceClassAndPagination> entityWithServiceClassAndPaginationList = entityWithServiceClassAndPaginationRepository.findAll();
        assertThat(entityWithServiceClassAndPaginationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
