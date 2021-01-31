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
import tech.jhipster.sample.domain.EntityWithServiceImplAndPagination;
import tech.jhipster.sample.repository.EntityWithServiceImplAndPaginationRepository;

/**
 * Integration tests for the {@link EntityWithServiceImplAndPaginationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityWithServiceImplAndPaginationResourceIT {

    private static final String DEFAULT_HUGO = "AAAAAAAAAA";
    private static final String UPDATED_HUGO = "BBBBBBBBBB";

    @Autowired
    private EntityWithServiceImplAndPaginationRepository entityWithServiceImplAndPaginationRepository;

    @Autowired
    private MockMvc restEntityWithServiceImplAndPaginationMockMvc;

    private EntityWithServiceImplAndPagination entityWithServiceImplAndPagination;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceImplAndPagination createEntity() {
        EntityWithServiceImplAndPagination entityWithServiceImplAndPagination = new EntityWithServiceImplAndPagination().hugo(DEFAULT_HUGO);
        return entityWithServiceImplAndPagination;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityWithServiceImplAndPagination createUpdatedEntity() {
        EntityWithServiceImplAndPagination entityWithServiceImplAndPagination = new EntityWithServiceImplAndPagination().hugo(UPDATED_HUGO);
        return entityWithServiceImplAndPagination;
    }

    @BeforeEach
    public void initTest() {
        entityWithServiceImplAndPaginationRepository.deleteAll();
        entityWithServiceImplAndPagination = createEntity();
    }

    @Test
    void createEntityWithServiceImplAndPagination() throws Exception {
        int databaseSizeBeforeCreate = entityWithServiceImplAndPaginationRepository.findAll().size();
        // Create the EntityWithServiceImplAndPagination
        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                post("/api/entity-with-service-impl-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplAndPagination))
            )
            .andExpect(status().isCreated());

        // Validate the EntityWithServiceImplAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndPagination> entityWithServiceImplAndPaginationList = entityWithServiceImplAndPaginationRepository.findAll();
        assertThat(entityWithServiceImplAndPaginationList).hasSize(databaseSizeBeforeCreate + 1);
        EntityWithServiceImplAndPagination testEntityWithServiceImplAndPagination = entityWithServiceImplAndPaginationList.get(
            entityWithServiceImplAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndPagination.getHugo()).isEqualTo(DEFAULT_HUGO);
    }

    @Test
    void createEntityWithServiceImplAndPaginationWithExistingId() throws Exception {
        // Create the EntityWithServiceImplAndPagination with an existing ID
        entityWithServiceImplAndPagination.setId("existing_id");

        int databaseSizeBeforeCreate = entityWithServiceImplAndPaginationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                post("/api/entity-with-service-impl-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplAndPagination))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceImplAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndPagination> entityWithServiceImplAndPaginationList = entityWithServiceImplAndPaginationRepository.findAll();
        assertThat(entityWithServiceImplAndPaginationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllEntityWithServiceImplAndPaginations() throws Exception {
        // Initialize the database
        entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);

        // Get all the entityWithServiceImplAndPaginationList
        restEntityWithServiceImplAndPaginationMockMvc
            .perform(get("/api/entity-with-service-impl-and-paginations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityWithServiceImplAndPagination.getId())))
            .andExpect(jsonPath("$.[*].hugo").value(hasItem(DEFAULT_HUGO)));
    }

    @Test
    void getEntityWithServiceImplAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);

        // Get the entityWithServiceImplAndPagination
        restEntityWithServiceImplAndPaginationMockMvc
            .perform(get("/api/entity-with-service-impl-and-paginations/{id}", entityWithServiceImplAndPagination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityWithServiceImplAndPagination.getId()))
            .andExpect(jsonPath("$.hugo").value(DEFAULT_HUGO));
    }

    @Test
    void getNonExistingEntityWithServiceImplAndPagination() throws Exception {
        // Get the entityWithServiceImplAndPagination
        restEntityWithServiceImplAndPaginationMockMvc
            .perform(get("/api/entity-with-service-impl-and-paginations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateEntityWithServiceImplAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);

        int databaseSizeBeforeUpdate = entityWithServiceImplAndPaginationRepository.findAll().size();

        // Update the entityWithServiceImplAndPagination
        EntityWithServiceImplAndPagination updatedEntityWithServiceImplAndPagination = entityWithServiceImplAndPaginationRepository
            .findById(entityWithServiceImplAndPagination.getId())
            .get();
        updatedEntityWithServiceImplAndPagination.hugo(UPDATED_HUGO);

        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                put("/api/entity-with-service-impl-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEntityWithServiceImplAndPagination))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndPagination> entityWithServiceImplAndPaginationList = entityWithServiceImplAndPaginationRepository.findAll();
        assertThat(entityWithServiceImplAndPaginationList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplAndPagination testEntityWithServiceImplAndPagination = entityWithServiceImplAndPaginationList.get(
            entityWithServiceImplAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndPagination.getHugo()).isEqualTo(UPDATED_HUGO);
    }

    @Test
    void updateNonExistingEntityWithServiceImplAndPagination() throws Exception {
        int databaseSizeBeforeUpdate = entityWithServiceImplAndPaginationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                put("/api/entity-with-service-impl-and-paginations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityWithServiceImplAndPagination))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityWithServiceImplAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndPagination> entityWithServiceImplAndPaginationList = entityWithServiceImplAndPaginationRepository.findAll();
        assertThat(entityWithServiceImplAndPaginationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEntityWithServiceImplAndPaginationWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);

        int databaseSizeBeforeUpdate = entityWithServiceImplAndPaginationRepository.findAll().size();

        // Update the entityWithServiceImplAndPagination using partial update
        EntityWithServiceImplAndPagination partialUpdatedEntityWithServiceImplAndPagination = new EntityWithServiceImplAndPagination();
        partialUpdatedEntityWithServiceImplAndPagination.setId(entityWithServiceImplAndPagination.getId());

        partialUpdatedEntityWithServiceImplAndPagination.hugo(UPDATED_HUGO);

        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                patch("/api/entity-with-service-impl-and-paginations")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplAndPagination))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndPagination> entityWithServiceImplAndPaginationList = entityWithServiceImplAndPaginationRepository.findAll();
        assertThat(entityWithServiceImplAndPaginationList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplAndPagination testEntityWithServiceImplAndPagination = entityWithServiceImplAndPaginationList.get(
            entityWithServiceImplAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndPagination.getHugo()).isEqualTo(UPDATED_HUGO);
    }

    @Test
    void fullUpdateEntityWithServiceImplAndPaginationWithPatch() throws Exception {
        // Initialize the database
        entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);

        int databaseSizeBeforeUpdate = entityWithServiceImplAndPaginationRepository.findAll().size();

        // Update the entityWithServiceImplAndPagination using partial update
        EntityWithServiceImplAndPagination partialUpdatedEntityWithServiceImplAndPagination = new EntityWithServiceImplAndPagination();
        partialUpdatedEntityWithServiceImplAndPagination.setId(entityWithServiceImplAndPagination.getId());

        partialUpdatedEntityWithServiceImplAndPagination.hugo(UPDATED_HUGO);

        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                patch("/api/entity-with-service-impl-and-paginations")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplAndPagination))
            )
            .andExpect(status().isOk());

        // Validate the EntityWithServiceImplAndPagination in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndPagination> entityWithServiceImplAndPaginationList = entityWithServiceImplAndPaginationRepository.findAll();
        assertThat(entityWithServiceImplAndPaginationList).hasSize(databaseSizeBeforeUpdate);
        EntityWithServiceImplAndPagination testEntityWithServiceImplAndPagination = entityWithServiceImplAndPaginationList.get(
            entityWithServiceImplAndPaginationList.size() - 1
        );
        assertThat(testEntityWithServiceImplAndPagination.getHugo()).isEqualTo(UPDATED_HUGO);
    }

    @Test
    void partialUpdateEntityWithServiceImplAndPaginationShouldThrown() throws Exception {
        // Update the entityWithServiceImplAndPagination without id should throw
        EntityWithServiceImplAndPagination partialUpdatedEntityWithServiceImplAndPagination = new EntityWithServiceImplAndPagination();

        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                patch("/api/entity-with-service-impl-and-paginations")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityWithServiceImplAndPagination))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteEntityWithServiceImplAndPagination() throws Exception {
        // Initialize the database
        entityWithServiceImplAndPaginationRepository.save(entityWithServiceImplAndPagination);

        int databaseSizeBeforeDelete = entityWithServiceImplAndPaginationRepository.findAll().size();

        // Delete the entityWithServiceImplAndPagination
        restEntityWithServiceImplAndPaginationMockMvc
            .perform(
                delete("/api/entity-with-service-impl-and-paginations/{id}", entityWithServiceImplAndPagination.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<EntityWithServiceImplAndPagination> entityWithServiceImplAndPaginationList = entityWithServiceImplAndPaginationRepository.findAll();
        assertThat(entityWithServiceImplAndPaginationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
