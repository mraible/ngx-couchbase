package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import tech.jhipster.sample.IntegrationTest;
import tech.jhipster.sample.domain.Place;
import tech.jhipster.sample.repository.PlaceRepository;

/**
 * Integration tests for the {@link PlaceResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class PlaceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMBER_OF_SEATS = 1L;
    private static final Long UPDATED_NUMBER_OF_SEATS = 2L;

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR_BACKGROUND = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_BACKGROUND = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private PlaceRepository placeRepository;

    @Mock
    private PlaceRepository placeRepositoryMock;

    @Autowired
    private MockMvc restPlaceMockMvc;

    private Place place;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Place createEntity() {
        Place place = new Place()
            .name(DEFAULT_NAME)
            .numberOfSeats(DEFAULT_NUMBER_OF_SEATS)
            .shortName(DEFAULT_SHORT_NAME)
            .colorBackground(DEFAULT_COLOR_BACKGROUND)
            .colorText(DEFAULT_COLOR_TEXT)
            .description(DEFAULT_DESCRIPTION);
        return place;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Place createUpdatedEntity() {
        Place place = new Place()
            .name(UPDATED_NAME)
            .numberOfSeats(UPDATED_NUMBER_OF_SEATS)
            .shortName(UPDATED_SHORT_NAME)
            .colorBackground(UPDATED_COLOR_BACKGROUND)
            .colorText(UPDATED_COLOR_TEXT)
            .description(UPDATED_DESCRIPTION);
        return place;
    }

    @BeforeEach
    public void initTest() {
        placeRepository.deleteAll();
        place = createEntity();
    }

    @Test
    void createPlace() throws Exception {
        int databaseSizeBeforeCreate = placeRepository.findAll().size();
        // Create the Place
        restPlaceMockMvc
            .perform(post("/api/places").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(place)))
            .andExpect(status().isCreated());

        // Validate the Place in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeCreate + 1);
        Place testPlace = placeList.get(placeList.size() - 1);
        assertThat(testPlace.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlace.getNumberOfSeats()).isEqualTo(DEFAULT_NUMBER_OF_SEATS);
        assertThat(testPlace.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testPlace.getColorBackground()).isEqualTo(DEFAULT_COLOR_BACKGROUND);
        assertThat(testPlace.getColorText()).isEqualTo(DEFAULT_COLOR_TEXT);
        assertThat(testPlace.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    void createPlaceWithExistingId() throws Exception {
        // Create the Place with an existing ID
        place.setId("existing_id");

        int databaseSizeBeforeCreate = placeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlaceMockMvc
            .perform(post("/api/places").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(place)))
            .andExpect(status().isBadRequest());

        // Validate the Place in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = placeRepository.findAll().size();
        // set the field null
        place.setName(null);

        // Create the Place, which fails.

        restPlaceMockMvc
            .perform(post("/api/places").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(place)))
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPlaces() throws Exception {
        // Initialize the database
        placeRepository.save(place);

        // Get all the placeList
        restPlaceMockMvc
            .perform(get("/api/places?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(place.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].numberOfSeats").value(hasItem(DEFAULT_NUMBER_OF_SEATS.intValue())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].colorBackground").value(hasItem(DEFAULT_COLOR_BACKGROUND)))
            .andExpect(jsonPath("$.[*].colorText").value(hasItem(DEFAULT_COLOR_TEXT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPlacesWithEagerRelationshipsIsEnabled() throws Exception {
        when(placeRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPlaceMockMvc.perform(get("/api/places?eagerload=true")).andExpect(status().isOk());

        verify(placeRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPlacesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(placeRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPlaceMockMvc.perform(get("/api/places?eagerload=true")).andExpect(status().isOk());

        verify(placeRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getPlace() throws Exception {
        // Initialize the database
        placeRepository.save(place);

        // Get the place
        restPlaceMockMvc
            .perform(get("/api/places/{id}", place.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(place.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.numberOfSeats").value(DEFAULT_NUMBER_OF_SEATS.intValue()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME))
            .andExpect(jsonPath("$.colorBackground").value(DEFAULT_COLOR_BACKGROUND))
            .andExpect(jsonPath("$.colorText").value(DEFAULT_COLOR_TEXT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    void getNonExistingPlace() throws Exception {
        // Get the place
        restPlaceMockMvc.perform(get("/api/places/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void updatePlace() throws Exception {
        // Initialize the database
        placeRepository.save(place);

        int databaseSizeBeforeUpdate = placeRepository.findAll().size();

        // Update the place
        Place updatedPlace = placeRepository.findById(place.getId()).get();
        updatedPlace
            .name(UPDATED_NAME)
            .numberOfSeats(UPDATED_NUMBER_OF_SEATS)
            .shortName(UPDATED_SHORT_NAME)
            .colorBackground(UPDATED_COLOR_BACKGROUND)
            .colorText(UPDATED_COLOR_TEXT)
            .description(UPDATED_DESCRIPTION);

        restPlaceMockMvc
            .perform(put("/api/places").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(updatedPlace)))
            .andExpect(status().isOk());

        // Validate the Place in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeUpdate);
        Place testPlace = placeList.get(placeList.size() - 1);
        assertThat(testPlace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlace.getNumberOfSeats()).isEqualTo(UPDATED_NUMBER_OF_SEATS);
        assertThat(testPlace.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testPlace.getColorBackground()).isEqualTo(UPDATED_COLOR_BACKGROUND);
        assertThat(testPlace.getColorText()).isEqualTo(UPDATED_COLOR_TEXT);
        assertThat(testPlace.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    void updateNonExistingPlace() throws Exception {
        int databaseSizeBeforeUpdate = placeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlaceMockMvc
            .perform(put("/api/places").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(place)))
            .andExpect(status().isBadRequest());

        // Validate the Place in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePlaceWithPatch() throws Exception {
        // Initialize the database
        placeRepository.save(place);

        int databaseSizeBeforeUpdate = placeRepository.findAll().size();

        // Update the place using partial update
        Place partialUpdatedPlace = new Place();
        partialUpdatedPlace.setId(place.getId());

        partialUpdatedPlace.name(UPDATED_NAME);

        restPlaceMockMvc
            .perform(
                patch("/api/places")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlace))
            )
            .andExpect(status().isOk());

        // Validate the Place in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeUpdate);
        Place testPlace = placeList.get(placeList.size() - 1);
        assertThat(testPlace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlace.getNumberOfSeats()).isEqualTo(DEFAULT_NUMBER_OF_SEATS);
        assertThat(testPlace.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testPlace.getColorBackground()).isEqualTo(DEFAULT_COLOR_BACKGROUND);
        assertThat(testPlace.getColorText()).isEqualTo(DEFAULT_COLOR_TEXT);
        assertThat(testPlace.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    void fullUpdatePlaceWithPatch() throws Exception {
        // Initialize the database
        placeRepository.save(place);

        int databaseSizeBeforeUpdate = placeRepository.findAll().size();

        // Update the place using partial update
        Place partialUpdatedPlace = new Place();
        partialUpdatedPlace.setId(place.getId());

        partialUpdatedPlace
            .name(UPDATED_NAME)
            .numberOfSeats(UPDATED_NUMBER_OF_SEATS)
            .shortName(UPDATED_SHORT_NAME)
            .colorBackground(UPDATED_COLOR_BACKGROUND)
            .colorText(UPDATED_COLOR_TEXT)
            .description(UPDATED_DESCRIPTION);

        restPlaceMockMvc
            .perform(
                patch("/api/places")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlace))
            )
            .andExpect(status().isOk());

        // Validate the Place in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeUpdate);
        Place testPlace = placeList.get(placeList.size() - 1);
        assertThat(testPlace.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlace.getNumberOfSeats()).isEqualTo(UPDATED_NUMBER_OF_SEATS);
        assertThat(testPlace.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testPlace.getColorBackground()).isEqualTo(UPDATED_COLOR_BACKGROUND);
        assertThat(testPlace.getColorText()).isEqualTo(UPDATED_COLOR_TEXT);
        assertThat(testPlace.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    void partialUpdatePlaceShouldThrown() throws Exception {
        // Update the place without id should throw
        Place partialUpdatedPlace = new Place();

        restPlaceMockMvc
            .perform(
                patch("/api/places")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPlace))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deletePlace() throws Exception {
        // Initialize the database
        placeRepository.save(place);

        int databaseSizeBeforeDelete = placeRepository.findAll().size();

        // Delete the place
        restPlaceMockMvc
            .perform(delete("/api/places/{id}", place.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<Place> placeList = placeRepository.findAll();
        assertThat(placeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
