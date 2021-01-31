package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.web.rest.TestUtil.sameInstant;
import static tech.jhipster.sample.web.rest.TestUtil.sameNumber;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import tech.jhipster.sample.IntegrationTest;
import tech.jhipster.sample.domain.FieldTestPaginationEntity;
import tech.jhipster.sample.domain.enumeration.EnumFieldClass;
import tech.jhipster.sample.domain.enumeration.EnumRequiredFieldClass;
import tech.jhipster.sample.repository.FieldTestPaginationEntityRepository;

/**
 * Integration tests for the {@link FieldTestPaginationEntityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FieldTestPaginationEntityResourceIT {

    private static final String DEFAULT_STRING_ALICE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_ALICE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_ALICE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_ALICE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_ALICE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_ALICE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_ALICE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_ALICE = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_ALICE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_ALICE = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_ALICE = 1;
    private static final Integer UPDATED_INTEGER_ALICE = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_ALICE = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_ALICE = 2;

    private static final Integer DEFAULT_INTEGER_MIN_ALICE = 0;
    private static final Integer UPDATED_INTEGER_MIN_ALICE = 1;

    private static final Integer DEFAULT_INTEGER_MAX_ALICE = 100;
    private static final Integer UPDATED_INTEGER_MAX_ALICE = 99;

    private static final Long DEFAULT_LONG_ALICE = 1L;
    private static final Long UPDATED_LONG_ALICE = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_ALICE = 1L;
    private static final Long UPDATED_LONG_REQUIRED_ALICE = 2L;

    private static final Long DEFAULT_LONG_MIN_ALICE = 0L;
    private static final Long UPDATED_LONG_MIN_ALICE = 1L;

    private static final Long DEFAULT_LONG_MAX_ALICE = 100L;
    private static final Long UPDATED_LONG_MAX_ALICE = 99L;

    private static final Float DEFAULT_FLOAT_ALICE = 1F;
    private static final Float UPDATED_FLOAT_ALICE = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_ALICE = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_ALICE = 2F;

    private static final Float DEFAULT_FLOAT_MIN_ALICE = 0F;
    private static final Float UPDATED_FLOAT_MIN_ALICE = 1F;

    private static final Float DEFAULT_FLOAT_MAX_ALICE = 100F;
    private static final Float UPDATED_FLOAT_MAX_ALICE = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_ALICE = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_ALICE = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_ALICE = 0D;
    private static final Double UPDATED_DOUBLE_MIN_ALICE = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_ALICE = 100D;
    private static final Double UPDATED_DOUBLE_MAX_ALICE = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_ALICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_ALICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_ALICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_ALICE = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_ALICE = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_ALICE = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_ALICE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_ALICE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_ALICE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_ALICE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_ALICE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_ALICE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_ALICE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_ALICE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_ALICE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_ALICE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_ALICE = ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(0L),
        ZoneOffset.UTC
    );
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Duration DEFAULT_DURATION_ALICE = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION_ALICE = Duration.ofHours(12);

    private static final Duration DEFAULT_DURATION_REQUIRED_ALICE = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION_REQUIRED_ALICE = Duration.ofHours(12);

    private static final Boolean DEFAULT_BOOLEAN_ALICE = false;
    private static final Boolean UPDATED_BOOLEAN_ALICE = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_ALICE = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_ALICE = true;

    private static final EnumFieldClass DEFAULT_ENUM_ALICE = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_ALICE = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_ALICE = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_ALICE = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final UUID DEFAULT_UUID_ALICE = UUID.randomUUID();
    private static final UUID UPDATED_UUID_ALICE = UUID.randomUUID();

    private static final UUID DEFAULT_UUID_REQUIRED_ALICE = UUID.randomUUID();
    private static final UUID UPDATED_UUID_REQUIRED_ALICE = UUID.randomUUID();

    private static final byte[] DEFAULT_BYTE_IMAGE_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_ALICE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_ALICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_ALICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_ALICE = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_ALICE = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_ALICE = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_ALICE = "BBBBBBBBBB";

    @Autowired
    private FieldTestPaginationEntityRepository fieldTestPaginationEntityRepository;

    @Autowired
    private MockMvc restFieldTestPaginationEntityMockMvc;

    private FieldTestPaginationEntity fieldTestPaginationEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldTestPaginationEntity createEntity() {
        FieldTestPaginationEntity fieldTestPaginationEntity = new FieldTestPaginationEntity()
            .stringAlice(DEFAULT_STRING_ALICE)
            .stringRequiredAlice(DEFAULT_STRING_REQUIRED_ALICE)
            .stringMinlengthAlice(DEFAULT_STRING_MINLENGTH_ALICE)
            .stringMaxlengthAlice(DEFAULT_STRING_MAXLENGTH_ALICE)
            .stringPatternAlice(DEFAULT_STRING_PATTERN_ALICE)
            .integerAlice(DEFAULT_INTEGER_ALICE)
            .integerRequiredAlice(DEFAULT_INTEGER_REQUIRED_ALICE)
            .integerMinAlice(DEFAULT_INTEGER_MIN_ALICE)
            .integerMaxAlice(DEFAULT_INTEGER_MAX_ALICE)
            .longAlice(DEFAULT_LONG_ALICE)
            .longRequiredAlice(DEFAULT_LONG_REQUIRED_ALICE)
            .longMinAlice(DEFAULT_LONG_MIN_ALICE)
            .longMaxAlice(DEFAULT_LONG_MAX_ALICE)
            .floatAlice(DEFAULT_FLOAT_ALICE)
            .floatRequiredAlice(DEFAULT_FLOAT_REQUIRED_ALICE)
            .floatMinAlice(DEFAULT_FLOAT_MIN_ALICE)
            .floatMaxAlice(DEFAULT_FLOAT_MAX_ALICE)
            .doubleRequiredAlice(DEFAULT_DOUBLE_REQUIRED_ALICE)
            .doubleMinAlice(DEFAULT_DOUBLE_MIN_ALICE)
            .doubleMaxAlice(DEFAULT_DOUBLE_MAX_ALICE)
            .bigDecimalRequiredAlice(DEFAULT_BIG_DECIMAL_REQUIRED_ALICE)
            .bigDecimalMinAlice(DEFAULT_BIG_DECIMAL_MIN_ALICE)
            .bigDecimalMaxAlice(DEFAULT_BIG_DECIMAL_MAX_ALICE)
            .localDateAlice(DEFAULT_LOCAL_DATE_ALICE)
            .localDateRequiredAlice(DEFAULT_LOCAL_DATE_REQUIRED_ALICE)
            .instantAlice(DEFAULT_INSTANT_ALICE)
            .instanteRequiredAlice(DEFAULT_INSTANTE_REQUIRED_ALICE)
            .zonedDateTimeAlice(DEFAULT_ZONED_DATE_TIME_ALICE)
            .zonedDateTimeRequiredAlice(DEFAULT_ZONED_DATE_TIME_REQUIRED_ALICE)
            .durationAlice(DEFAULT_DURATION_ALICE)
            .durationRequiredAlice(DEFAULT_DURATION_REQUIRED_ALICE)
            .booleanAlice(DEFAULT_BOOLEAN_ALICE)
            .booleanRequiredAlice(DEFAULT_BOOLEAN_REQUIRED_ALICE)
            .enumAlice(DEFAULT_ENUM_ALICE)
            .enumRequiredAlice(DEFAULT_ENUM_REQUIRED_ALICE)
            .uuidAlice(DEFAULT_UUID_ALICE)
            .uuidRequiredAlice(DEFAULT_UUID_REQUIRED_ALICE)
            .byteImageAlice(DEFAULT_BYTE_IMAGE_ALICE)
            .byteImageAliceContentType(DEFAULT_BYTE_IMAGE_ALICE_CONTENT_TYPE)
            .byteImageRequiredAlice(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE)
            .byteImageRequiredAliceContentType(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE)
            .byteImageMinbytesAlice(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE)
            .byteImageMinbytesAliceContentType(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE)
            .byteImageMaxbytesAlice(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE)
            .byteImageMaxbytesAliceContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteAnyAlice(DEFAULT_BYTE_ANY_ALICE)
            .byteAnyAliceContentType(DEFAULT_BYTE_ANY_ALICE_CONTENT_TYPE)
            .byteAnyRequiredAlice(DEFAULT_BYTE_ANY_REQUIRED_ALICE)
            .byteAnyRequiredAliceContentType(DEFAULT_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE)
            .byteAnyMinbytesAlice(DEFAULT_BYTE_ANY_MINBYTES_ALICE)
            .byteAnyMinbytesAliceContentType(DEFAULT_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE)
            .byteAnyMaxbytesAlice(DEFAULT_BYTE_ANY_MAXBYTES_ALICE)
            .byteAnyMaxbytesAliceContentType(DEFAULT_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteTextAlice(DEFAULT_BYTE_TEXT_ALICE)
            .byteTextRequiredAlice(DEFAULT_BYTE_TEXT_REQUIRED_ALICE);
        return fieldTestPaginationEntity;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldTestPaginationEntity createUpdatedEntity() {
        FieldTestPaginationEntity fieldTestPaginationEntity = new FieldTestPaginationEntity()
            .stringAlice(UPDATED_STRING_ALICE)
            .stringRequiredAlice(UPDATED_STRING_REQUIRED_ALICE)
            .stringMinlengthAlice(UPDATED_STRING_MINLENGTH_ALICE)
            .stringMaxlengthAlice(UPDATED_STRING_MAXLENGTH_ALICE)
            .stringPatternAlice(UPDATED_STRING_PATTERN_ALICE)
            .integerAlice(UPDATED_INTEGER_ALICE)
            .integerRequiredAlice(UPDATED_INTEGER_REQUIRED_ALICE)
            .integerMinAlice(UPDATED_INTEGER_MIN_ALICE)
            .integerMaxAlice(UPDATED_INTEGER_MAX_ALICE)
            .longAlice(UPDATED_LONG_ALICE)
            .longRequiredAlice(UPDATED_LONG_REQUIRED_ALICE)
            .longMinAlice(UPDATED_LONG_MIN_ALICE)
            .longMaxAlice(UPDATED_LONG_MAX_ALICE)
            .floatAlice(UPDATED_FLOAT_ALICE)
            .floatRequiredAlice(UPDATED_FLOAT_REQUIRED_ALICE)
            .floatMinAlice(UPDATED_FLOAT_MIN_ALICE)
            .floatMaxAlice(UPDATED_FLOAT_MAX_ALICE)
            .doubleRequiredAlice(UPDATED_DOUBLE_REQUIRED_ALICE)
            .doubleMinAlice(UPDATED_DOUBLE_MIN_ALICE)
            .doubleMaxAlice(UPDATED_DOUBLE_MAX_ALICE)
            .bigDecimalRequiredAlice(UPDATED_BIG_DECIMAL_REQUIRED_ALICE)
            .bigDecimalMinAlice(UPDATED_BIG_DECIMAL_MIN_ALICE)
            .bigDecimalMaxAlice(UPDATED_BIG_DECIMAL_MAX_ALICE)
            .localDateAlice(UPDATED_LOCAL_DATE_ALICE)
            .localDateRequiredAlice(UPDATED_LOCAL_DATE_REQUIRED_ALICE)
            .instantAlice(UPDATED_INSTANT_ALICE)
            .instanteRequiredAlice(UPDATED_INSTANTE_REQUIRED_ALICE)
            .zonedDateTimeAlice(UPDATED_ZONED_DATE_TIME_ALICE)
            .zonedDateTimeRequiredAlice(UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE)
            .durationAlice(UPDATED_DURATION_ALICE)
            .durationRequiredAlice(UPDATED_DURATION_REQUIRED_ALICE)
            .booleanAlice(UPDATED_BOOLEAN_ALICE)
            .booleanRequiredAlice(UPDATED_BOOLEAN_REQUIRED_ALICE)
            .enumAlice(UPDATED_ENUM_ALICE)
            .enumRequiredAlice(UPDATED_ENUM_REQUIRED_ALICE)
            .uuidAlice(UPDATED_UUID_ALICE)
            .uuidRequiredAlice(UPDATED_UUID_REQUIRED_ALICE)
            .byteImageAlice(UPDATED_BYTE_IMAGE_ALICE)
            .byteImageAliceContentType(UPDATED_BYTE_IMAGE_ALICE_CONTENT_TYPE)
            .byteImageRequiredAlice(UPDATED_BYTE_IMAGE_REQUIRED_ALICE)
            .byteImageRequiredAliceContentType(UPDATED_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE)
            .byteImageMinbytesAlice(UPDATED_BYTE_IMAGE_MINBYTES_ALICE)
            .byteImageMinbytesAliceContentType(UPDATED_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE)
            .byteImageMaxbytesAlice(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE)
            .byteImageMaxbytesAliceContentType(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteAnyAlice(UPDATED_BYTE_ANY_ALICE)
            .byteAnyAliceContentType(UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE)
            .byteAnyRequiredAlice(UPDATED_BYTE_ANY_REQUIRED_ALICE)
            .byteAnyRequiredAliceContentType(UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE)
            .byteAnyMinbytesAlice(UPDATED_BYTE_ANY_MINBYTES_ALICE)
            .byteAnyMinbytesAliceContentType(UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE)
            .byteAnyMaxbytesAlice(UPDATED_BYTE_ANY_MAXBYTES_ALICE)
            .byteAnyMaxbytesAliceContentType(UPDATED_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteTextAlice(UPDATED_BYTE_TEXT_ALICE)
            .byteTextRequiredAlice(UPDATED_BYTE_TEXT_REQUIRED_ALICE);
        return fieldTestPaginationEntity;
    }

    @BeforeEach
    public void initTest() {
        fieldTestPaginationEntityRepository.deleteAll();
        fieldTestPaginationEntity = createEntity();
    }

    @Test
    void createFieldTestPaginationEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestPaginationEntityRepository.findAll().size();
        // Create the FieldTestPaginationEntity
        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isCreated());

        // Validate the FieldTestPaginationEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestPaginationEntity testFieldTestPaginationEntity = fieldTestPaginationEntityList.get(
            fieldTestPaginationEntityList.size() - 1
        );
        assertThat(testFieldTestPaginationEntity.getStringAlice()).isEqualTo(DEFAULT_STRING_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringRequiredAlice()).isEqualTo(DEFAULT_STRING_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMinlengthAlice()).isEqualTo(DEFAULT_STRING_MINLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMaxlengthAlice()).isEqualTo(DEFAULT_STRING_MAXLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringPatternAlice()).isEqualTo(DEFAULT_STRING_PATTERN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerAlice()).isEqualTo(DEFAULT_INTEGER_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerRequiredAlice()).isEqualTo(DEFAULT_INTEGER_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMinAlice()).isEqualTo(DEFAULT_INTEGER_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMaxAlice()).isEqualTo(DEFAULT_INTEGER_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongAlice()).isEqualTo(DEFAULT_LONG_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongRequiredAlice()).isEqualTo(DEFAULT_LONG_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMinAlice()).isEqualTo(DEFAULT_LONG_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMaxAlice()).isEqualTo(DEFAULT_LONG_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatAlice()).isEqualTo(DEFAULT_FLOAT_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatRequiredAlice()).isEqualTo(DEFAULT_FLOAT_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMinAlice()).isEqualTo(DEFAULT_FLOAT_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMaxAlice()).isEqualTo(DEFAULT_FLOAT_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleRequiredAlice()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMinAlice()).isEqualTo(DEFAULT_DOUBLE_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMaxAlice()).isEqualTo(DEFAULT_DOUBLE_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalRequiredAlice()).isEqualByComparingTo(DEFAULT_BIG_DECIMAL_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMinAlice()).isEqualByComparingTo(DEFAULT_BIG_DECIMAL_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMaxAlice()).isEqualByComparingTo(DEFAULT_BIG_DECIMAL_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateAlice()).isEqualTo(DEFAULT_LOCAL_DATE_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateRequiredAlice()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstantAlice()).isEqualTo(DEFAULT_INSTANT_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstanteRequiredAlice()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeAlice()).isEqualTo(DEFAULT_ZONED_DATE_TIME_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeRequiredAlice()).isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationAlice()).isEqualTo(DEFAULT_DURATION_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationRequiredAlice()).isEqualTo(DEFAULT_DURATION_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanAlice()).isEqualTo(DEFAULT_BOOLEAN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanRequiredAlice()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumAlice()).isEqualTo(DEFAULT_ENUM_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumRequiredAlice()).isEqualTo(DEFAULT_ENUM_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidAlice()).isEqualTo(DEFAULT_UUID_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidRequiredAlice()).isEqualTo(DEFAULT_UUID_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAliceContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAliceContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAliceContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAliceContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAlice()).isEqualTo(DEFAULT_BYTE_ANY_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAliceContentType()).isEqualTo(DEFAULT_BYTE_ANY_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAlice()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAliceContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAlice()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAliceContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAlice()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAliceContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteTextAlice()).isEqualTo(DEFAULT_BYTE_TEXT_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteTextRequiredAlice()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_ALICE);
    }

    @Test
    void createFieldTestPaginationEntityWithExistingId() throws Exception {
        // Create the FieldTestPaginationEntity with an existing ID
        fieldTestPaginationEntity.setId("existing_id");

        int databaseSizeBeforeCreate = fieldTestPaginationEntityRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldTestPaginationEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkStringRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setStringRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIntegerRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setIntegerRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLongRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setLongRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFloatRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setFloatRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDoubleRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setDoubleRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBigDecimalRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setBigDecimalRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLocalDateRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setLocalDateRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInstanteRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setInstanteRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkZonedDateTimeRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setZonedDateTimeRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDurationRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setDurationRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBooleanRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setBooleanRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEnumRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setEnumRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUuidRequiredAliceIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestPaginationEntityRepository.findAll().size();
        // set the field null
        fieldTestPaginationEntity.setUuidRequiredAlice(null);

        // Create the FieldTestPaginationEntity, which fails.

        restFieldTestPaginationEntityMockMvc
            .perform(
                post("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFieldTestPaginationEntities() throws Exception {
        // Initialize the database
        fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);

        // Get all the fieldTestPaginationEntityList
        restFieldTestPaginationEntityMockMvc
            .perform(get("/api/field-test-pagination-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestPaginationEntity.getId())))
            .andExpect(jsonPath("$.[*].stringAlice").value(hasItem(DEFAULT_STRING_ALICE)))
            .andExpect(jsonPath("$.[*].stringRequiredAlice").value(hasItem(DEFAULT_STRING_REQUIRED_ALICE)))
            .andExpect(jsonPath("$.[*].stringMinlengthAlice").value(hasItem(DEFAULT_STRING_MINLENGTH_ALICE)))
            .andExpect(jsonPath("$.[*].stringMaxlengthAlice").value(hasItem(DEFAULT_STRING_MAXLENGTH_ALICE)))
            .andExpect(jsonPath("$.[*].stringPatternAlice").value(hasItem(DEFAULT_STRING_PATTERN_ALICE)))
            .andExpect(jsonPath("$.[*].integerAlice").value(hasItem(DEFAULT_INTEGER_ALICE)))
            .andExpect(jsonPath("$.[*].integerRequiredAlice").value(hasItem(DEFAULT_INTEGER_REQUIRED_ALICE)))
            .andExpect(jsonPath("$.[*].integerMinAlice").value(hasItem(DEFAULT_INTEGER_MIN_ALICE)))
            .andExpect(jsonPath("$.[*].integerMaxAlice").value(hasItem(DEFAULT_INTEGER_MAX_ALICE)))
            .andExpect(jsonPath("$.[*].longAlice").value(hasItem(DEFAULT_LONG_ALICE.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredAlice").value(hasItem(DEFAULT_LONG_REQUIRED_ALICE.intValue())))
            .andExpect(jsonPath("$.[*].longMinAlice").value(hasItem(DEFAULT_LONG_MIN_ALICE.intValue())))
            .andExpect(jsonPath("$.[*].longMaxAlice").value(hasItem(DEFAULT_LONG_MAX_ALICE.intValue())))
            .andExpect(jsonPath("$.[*].floatAlice").value(hasItem(DEFAULT_FLOAT_ALICE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredAlice").value(hasItem(DEFAULT_FLOAT_REQUIRED_ALICE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinAlice").value(hasItem(DEFAULT_FLOAT_MIN_ALICE.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxAlice").value(hasItem(DEFAULT_FLOAT_MAX_ALICE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredAlice").value(hasItem(DEFAULT_DOUBLE_REQUIRED_ALICE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinAlice").value(hasItem(DEFAULT_DOUBLE_MIN_ALICE.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxAlice").value(hasItem(DEFAULT_DOUBLE_MAX_ALICE.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredAlice").value(hasItem(sameNumber(DEFAULT_BIG_DECIMAL_REQUIRED_ALICE))))
            .andExpect(jsonPath("$.[*].bigDecimalMinAlice").value(hasItem(sameNumber(DEFAULT_BIG_DECIMAL_MIN_ALICE))))
            .andExpect(jsonPath("$.[*].bigDecimalMaxAlice").value(hasItem(sameNumber(DEFAULT_BIG_DECIMAL_MAX_ALICE))))
            .andExpect(jsonPath("$.[*].localDateAlice").value(hasItem(DEFAULT_LOCAL_DATE_ALICE.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredAlice").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_ALICE.toString())))
            .andExpect(jsonPath("$.[*].instantAlice").value(hasItem(DEFAULT_INSTANT_ALICE.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredAlice").value(hasItem(DEFAULT_INSTANTE_REQUIRED_ALICE.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeAlice").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_ALICE))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredAlice").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_ALICE))))
            .andExpect(jsonPath("$.[*].durationAlice").value(hasItem(DEFAULT_DURATION_ALICE.toString())))
            .andExpect(jsonPath("$.[*].durationRequiredAlice").value(hasItem(DEFAULT_DURATION_REQUIRED_ALICE.toString())))
            .andExpect(jsonPath("$.[*].booleanAlice").value(hasItem(DEFAULT_BOOLEAN_ALICE.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredAlice").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_ALICE.booleanValue())))
            .andExpect(jsonPath("$.[*].enumAlice").value(hasItem(DEFAULT_ENUM_ALICE.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredAlice").value(hasItem(DEFAULT_ENUM_REQUIRED_ALICE.toString())))
            .andExpect(jsonPath("$.[*].uuidAlice").value(hasItem(DEFAULT_UUID_ALICE.toString())))
            .andExpect(jsonPath("$.[*].uuidRequiredAlice").value(hasItem(DEFAULT_UUID_REQUIRED_ALICE.toString())))
            .andExpect(jsonPath("$.[*].byteImageAliceContentType").value(hasItem(DEFAULT_BYTE_IMAGE_ALICE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_ALICE))))
            .andExpect(jsonPath("$.[*].byteImageRequiredAliceContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE)))
            .andExpect(
                jsonPath("$.[*].byteImageRequiredAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE)))
            )
            .andExpect(jsonPath("$.[*].byteImageMinbytesAliceContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE)))
            .andExpect(
                jsonPath("$.[*].byteImageMinbytesAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE)))
            )
            .andExpect(jsonPath("$.[*].byteImageMaxbytesAliceContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE)))
            .andExpect(
                jsonPath("$.[*].byteImageMaxbytesAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE)))
            )
            .andExpect(jsonPath("$.[*].byteAnyAliceContentType").value(hasItem(DEFAULT_BYTE_ANY_ALICE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_ALICE))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredAliceContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_ALICE))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesAliceContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_ALICE))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesAliceContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesAlice").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_ALICE))))
            .andExpect(jsonPath("$.[*].byteTextAlice").value(hasItem(DEFAULT_BYTE_TEXT_ALICE.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredAlice").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_ALICE.toString())));
    }

    @Test
    void getFieldTestPaginationEntity() throws Exception {
        // Initialize the database
        fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);

        // Get the fieldTestPaginationEntity
        restFieldTestPaginationEntityMockMvc
            .perform(get("/api/field-test-pagination-entities/{id}", fieldTestPaginationEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestPaginationEntity.getId()))
            .andExpect(jsonPath("$.stringAlice").value(DEFAULT_STRING_ALICE))
            .andExpect(jsonPath("$.stringRequiredAlice").value(DEFAULT_STRING_REQUIRED_ALICE))
            .andExpect(jsonPath("$.stringMinlengthAlice").value(DEFAULT_STRING_MINLENGTH_ALICE))
            .andExpect(jsonPath("$.stringMaxlengthAlice").value(DEFAULT_STRING_MAXLENGTH_ALICE))
            .andExpect(jsonPath("$.stringPatternAlice").value(DEFAULT_STRING_PATTERN_ALICE))
            .andExpect(jsonPath("$.integerAlice").value(DEFAULT_INTEGER_ALICE))
            .andExpect(jsonPath("$.integerRequiredAlice").value(DEFAULT_INTEGER_REQUIRED_ALICE))
            .andExpect(jsonPath("$.integerMinAlice").value(DEFAULT_INTEGER_MIN_ALICE))
            .andExpect(jsonPath("$.integerMaxAlice").value(DEFAULT_INTEGER_MAX_ALICE))
            .andExpect(jsonPath("$.longAlice").value(DEFAULT_LONG_ALICE.intValue()))
            .andExpect(jsonPath("$.longRequiredAlice").value(DEFAULT_LONG_REQUIRED_ALICE.intValue()))
            .andExpect(jsonPath("$.longMinAlice").value(DEFAULT_LONG_MIN_ALICE.intValue()))
            .andExpect(jsonPath("$.longMaxAlice").value(DEFAULT_LONG_MAX_ALICE.intValue()))
            .andExpect(jsonPath("$.floatAlice").value(DEFAULT_FLOAT_ALICE.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredAlice").value(DEFAULT_FLOAT_REQUIRED_ALICE.doubleValue()))
            .andExpect(jsonPath("$.floatMinAlice").value(DEFAULT_FLOAT_MIN_ALICE.doubleValue()))
            .andExpect(jsonPath("$.floatMaxAlice").value(DEFAULT_FLOAT_MAX_ALICE.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredAlice").value(DEFAULT_DOUBLE_REQUIRED_ALICE.doubleValue()))
            .andExpect(jsonPath("$.doubleMinAlice").value(DEFAULT_DOUBLE_MIN_ALICE.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxAlice").value(DEFAULT_DOUBLE_MAX_ALICE.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredAlice").value(sameNumber(DEFAULT_BIG_DECIMAL_REQUIRED_ALICE)))
            .andExpect(jsonPath("$.bigDecimalMinAlice").value(sameNumber(DEFAULT_BIG_DECIMAL_MIN_ALICE)))
            .andExpect(jsonPath("$.bigDecimalMaxAlice").value(sameNumber(DEFAULT_BIG_DECIMAL_MAX_ALICE)))
            .andExpect(jsonPath("$.localDateAlice").value(DEFAULT_LOCAL_DATE_ALICE.toString()))
            .andExpect(jsonPath("$.localDateRequiredAlice").value(DEFAULT_LOCAL_DATE_REQUIRED_ALICE.toString()))
            .andExpect(jsonPath("$.instantAlice").value(DEFAULT_INSTANT_ALICE.toString()))
            .andExpect(jsonPath("$.instanteRequiredAlice").value(DEFAULT_INSTANTE_REQUIRED_ALICE.toString()))
            .andExpect(jsonPath("$.zonedDateTimeAlice").value(sameInstant(DEFAULT_ZONED_DATE_TIME_ALICE)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredAlice").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_ALICE)))
            .andExpect(jsonPath("$.durationAlice").value(DEFAULT_DURATION_ALICE.toString()))
            .andExpect(jsonPath("$.durationRequiredAlice").value(DEFAULT_DURATION_REQUIRED_ALICE.toString()))
            .andExpect(jsonPath("$.booleanAlice").value(DEFAULT_BOOLEAN_ALICE.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredAlice").value(DEFAULT_BOOLEAN_REQUIRED_ALICE.booleanValue()))
            .andExpect(jsonPath("$.enumAlice").value(DEFAULT_ENUM_ALICE.toString()))
            .andExpect(jsonPath("$.enumRequiredAlice").value(DEFAULT_ENUM_REQUIRED_ALICE.toString()))
            .andExpect(jsonPath("$.uuidAlice").value(DEFAULT_UUID_ALICE.toString()))
            .andExpect(jsonPath("$.uuidRequiredAlice").value(DEFAULT_UUID_REQUIRED_ALICE.toString()))
            .andExpect(jsonPath("$.byteImageAliceContentType").value(DEFAULT_BYTE_IMAGE_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_ALICE)))
            .andExpect(jsonPath("$.byteImageRequiredAliceContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE)))
            .andExpect(jsonPath("$.byteImageMinbytesAliceContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE)))
            .andExpect(jsonPath("$.byteImageMaxbytesAliceContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE)))
            .andExpect(jsonPath("$.byteAnyAliceContentType").value(DEFAULT_BYTE_ANY_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_ALICE)))
            .andExpect(jsonPath("$.byteAnyRequiredAliceContentType").value(DEFAULT_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_ALICE)))
            .andExpect(jsonPath("$.byteAnyMinbytesAliceContentType").value(DEFAULT_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_ALICE)))
            .andExpect(jsonPath("$.byteAnyMaxbytesAliceContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesAlice").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_ALICE)))
            .andExpect(jsonPath("$.byteTextAlice").value(DEFAULT_BYTE_TEXT_ALICE.toString()))
            .andExpect(jsonPath("$.byteTextRequiredAlice").value(DEFAULT_BYTE_TEXT_REQUIRED_ALICE.toString()));
    }

    @Test
    void getNonExistingFieldTestPaginationEntity() throws Exception {
        // Get the fieldTestPaginationEntity
        restFieldTestPaginationEntityMockMvc
            .perform(get("/api/field-test-pagination-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateFieldTestPaginationEntity() throws Exception {
        // Initialize the database
        fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);

        int databaseSizeBeforeUpdate = fieldTestPaginationEntityRepository.findAll().size();

        // Update the fieldTestPaginationEntity
        FieldTestPaginationEntity updatedFieldTestPaginationEntity = fieldTestPaginationEntityRepository
            .findById(fieldTestPaginationEntity.getId())
            .get();
        updatedFieldTestPaginationEntity
            .stringAlice(UPDATED_STRING_ALICE)
            .stringRequiredAlice(UPDATED_STRING_REQUIRED_ALICE)
            .stringMinlengthAlice(UPDATED_STRING_MINLENGTH_ALICE)
            .stringMaxlengthAlice(UPDATED_STRING_MAXLENGTH_ALICE)
            .stringPatternAlice(UPDATED_STRING_PATTERN_ALICE)
            .integerAlice(UPDATED_INTEGER_ALICE)
            .integerRequiredAlice(UPDATED_INTEGER_REQUIRED_ALICE)
            .integerMinAlice(UPDATED_INTEGER_MIN_ALICE)
            .integerMaxAlice(UPDATED_INTEGER_MAX_ALICE)
            .longAlice(UPDATED_LONG_ALICE)
            .longRequiredAlice(UPDATED_LONG_REQUIRED_ALICE)
            .longMinAlice(UPDATED_LONG_MIN_ALICE)
            .longMaxAlice(UPDATED_LONG_MAX_ALICE)
            .floatAlice(UPDATED_FLOAT_ALICE)
            .floatRequiredAlice(UPDATED_FLOAT_REQUIRED_ALICE)
            .floatMinAlice(UPDATED_FLOAT_MIN_ALICE)
            .floatMaxAlice(UPDATED_FLOAT_MAX_ALICE)
            .doubleRequiredAlice(UPDATED_DOUBLE_REQUIRED_ALICE)
            .doubleMinAlice(UPDATED_DOUBLE_MIN_ALICE)
            .doubleMaxAlice(UPDATED_DOUBLE_MAX_ALICE)
            .bigDecimalRequiredAlice(UPDATED_BIG_DECIMAL_REQUIRED_ALICE)
            .bigDecimalMinAlice(UPDATED_BIG_DECIMAL_MIN_ALICE)
            .bigDecimalMaxAlice(UPDATED_BIG_DECIMAL_MAX_ALICE)
            .localDateAlice(UPDATED_LOCAL_DATE_ALICE)
            .localDateRequiredAlice(UPDATED_LOCAL_DATE_REQUIRED_ALICE)
            .instantAlice(UPDATED_INSTANT_ALICE)
            .instanteRequiredAlice(UPDATED_INSTANTE_REQUIRED_ALICE)
            .zonedDateTimeAlice(UPDATED_ZONED_DATE_TIME_ALICE)
            .zonedDateTimeRequiredAlice(UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE)
            .durationAlice(UPDATED_DURATION_ALICE)
            .durationRequiredAlice(UPDATED_DURATION_REQUIRED_ALICE)
            .booleanAlice(UPDATED_BOOLEAN_ALICE)
            .booleanRequiredAlice(UPDATED_BOOLEAN_REQUIRED_ALICE)
            .enumAlice(UPDATED_ENUM_ALICE)
            .enumRequiredAlice(UPDATED_ENUM_REQUIRED_ALICE)
            .uuidAlice(UPDATED_UUID_ALICE)
            .uuidRequiredAlice(UPDATED_UUID_REQUIRED_ALICE)
            .byteImageAlice(UPDATED_BYTE_IMAGE_ALICE)
            .byteImageAliceContentType(UPDATED_BYTE_IMAGE_ALICE_CONTENT_TYPE)
            .byteImageRequiredAlice(UPDATED_BYTE_IMAGE_REQUIRED_ALICE)
            .byteImageRequiredAliceContentType(UPDATED_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE)
            .byteImageMinbytesAlice(UPDATED_BYTE_IMAGE_MINBYTES_ALICE)
            .byteImageMinbytesAliceContentType(UPDATED_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE)
            .byteImageMaxbytesAlice(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE)
            .byteImageMaxbytesAliceContentType(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteAnyAlice(UPDATED_BYTE_ANY_ALICE)
            .byteAnyAliceContentType(UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE)
            .byteAnyRequiredAlice(UPDATED_BYTE_ANY_REQUIRED_ALICE)
            .byteAnyRequiredAliceContentType(UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE)
            .byteAnyMinbytesAlice(UPDATED_BYTE_ANY_MINBYTES_ALICE)
            .byteAnyMinbytesAliceContentType(UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE)
            .byteAnyMaxbytesAlice(UPDATED_BYTE_ANY_MAXBYTES_ALICE)
            .byteAnyMaxbytesAliceContentType(UPDATED_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteTextAlice(UPDATED_BYTE_TEXT_ALICE)
            .byteTextRequiredAlice(UPDATED_BYTE_TEXT_REQUIRED_ALICE);

        restFieldTestPaginationEntityMockMvc
            .perform(
                put("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFieldTestPaginationEntity))
            )
            .andExpect(status().isOk());

        // Validate the FieldTestPaginationEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestPaginationEntity testFieldTestPaginationEntity = fieldTestPaginationEntityList.get(
            fieldTestPaginationEntityList.size() - 1
        );
        assertThat(testFieldTestPaginationEntity.getStringAlice()).isEqualTo(UPDATED_STRING_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringRequiredAlice()).isEqualTo(UPDATED_STRING_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMinlengthAlice()).isEqualTo(UPDATED_STRING_MINLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMaxlengthAlice()).isEqualTo(UPDATED_STRING_MAXLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringPatternAlice()).isEqualTo(UPDATED_STRING_PATTERN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerAlice()).isEqualTo(UPDATED_INTEGER_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerRequiredAlice()).isEqualTo(UPDATED_INTEGER_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMinAlice()).isEqualTo(UPDATED_INTEGER_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMaxAlice()).isEqualTo(UPDATED_INTEGER_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongAlice()).isEqualTo(UPDATED_LONG_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongRequiredAlice()).isEqualTo(UPDATED_LONG_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMinAlice()).isEqualTo(UPDATED_LONG_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMaxAlice()).isEqualTo(UPDATED_LONG_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatAlice()).isEqualTo(UPDATED_FLOAT_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatRequiredAlice()).isEqualTo(UPDATED_FLOAT_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMinAlice()).isEqualTo(UPDATED_FLOAT_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMaxAlice()).isEqualTo(UPDATED_FLOAT_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleRequiredAlice()).isEqualTo(UPDATED_DOUBLE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMinAlice()).isEqualTo(UPDATED_DOUBLE_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMaxAlice()).isEqualTo(UPDATED_DOUBLE_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalRequiredAlice()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMinAlice()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMaxAlice()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateAlice()).isEqualTo(UPDATED_LOCAL_DATE_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateRequiredAlice()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstantAlice()).isEqualTo(UPDATED_INSTANT_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstanteRequiredAlice()).isEqualTo(UPDATED_INSTANTE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeAlice()).isEqualTo(UPDATED_ZONED_DATE_TIME_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeRequiredAlice()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationAlice()).isEqualTo(UPDATED_DURATION_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationRequiredAlice()).isEqualTo(UPDATED_DURATION_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanAlice()).isEqualTo(UPDATED_BOOLEAN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanRequiredAlice()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumAlice()).isEqualTo(UPDATED_ENUM_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumRequiredAlice()).isEqualTo(UPDATED_ENUM_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidAlice()).isEqualTo(UPDATED_UUID_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidRequiredAlice()).isEqualTo(UPDATED_UUID_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAlice()).isEqualTo(UPDATED_BYTE_IMAGE_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAliceContentType()).isEqualTo(UPDATED_BYTE_IMAGE_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAlice()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAliceContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAlice()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAlice()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAlice()).isEqualTo(UPDATED_BYTE_ANY_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAliceContentType()).isEqualTo(UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAlice()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAlice()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAlice()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteTextAlice()).isEqualTo(UPDATED_BYTE_TEXT_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteTextRequiredAlice()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_ALICE);
    }

    @Test
    void updateNonExistingFieldTestPaginationEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestPaginationEntityRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldTestPaginationEntityMockMvc
            .perform(
                put("/api/field-test-pagination-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldTestPaginationEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFieldTestPaginationEntityWithPatch() throws Exception {
        // Initialize the database
        fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);

        int databaseSizeBeforeUpdate = fieldTestPaginationEntityRepository.findAll().size();

        // Update the fieldTestPaginationEntity using partial update
        FieldTestPaginationEntity partialUpdatedFieldTestPaginationEntity = new FieldTestPaginationEntity();
        partialUpdatedFieldTestPaginationEntity.setId(fieldTestPaginationEntity.getId());

        partialUpdatedFieldTestPaginationEntity
            .stringMinlengthAlice(UPDATED_STRING_MINLENGTH_ALICE)
            .stringPatternAlice(UPDATED_STRING_PATTERN_ALICE)
            .integerRequiredAlice(UPDATED_INTEGER_REQUIRED_ALICE)
            .integerMaxAlice(UPDATED_INTEGER_MAX_ALICE)
            .longAlice(UPDATED_LONG_ALICE)
            .floatAlice(UPDATED_FLOAT_ALICE)
            .floatRequiredAlice(UPDATED_FLOAT_REQUIRED_ALICE)
            .floatMinAlice(UPDATED_FLOAT_MIN_ALICE)
            .bigDecimalRequiredAlice(UPDATED_BIG_DECIMAL_REQUIRED_ALICE)
            .bigDecimalMinAlice(UPDATED_BIG_DECIMAL_MIN_ALICE)
            .localDateAlice(UPDATED_LOCAL_DATE_ALICE)
            .localDateRequiredAlice(UPDATED_LOCAL_DATE_REQUIRED_ALICE)
            .instantAlice(UPDATED_INSTANT_ALICE)
            .instanteRequiredAlice(UPDATED_INSTANTE_REQUIRED_ALICE)
            .zonedDateTimeRequiredAlice(UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE)
            .booleanAlice(UPDATED_BOOLEAN_ALICE)
            .booleanRequiredAlice(UPDATED_BOOLEAN_REQUIRED_ALICE)
            .enumAlice(UPDATED_ENUM_ALICE)
            .enumRequiredAlice(UPDATED_ENUM_REQUIRED_ALICE)
            .uuidAlice(UPDATED_UUID_ALICE)
            .uuidRequiredAlice(UPDATED_UUID_REQUIRED_ALICE)
            .byteAnyAlice(UPDATED_BYTE_ANY_ALICE)
            .byteAnyAliceContentType(UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE)
            .byteAnyRequiredAlice(UPDATED_BYTE_ANY_REQUIRED_ALICE)
            .byteAnyRequiredAliceContentType(UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE)
            .byteAnyMinbytesAlice(UPDATED_BYTE_ANY_MINBYTES_ALICE)
            .byteAnyMinbytesAliceContentType(UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE)
            .byteTextAlice(UPDATED_BYTE_TEXT_ALICE);

        restFieldTestPaginationEntityMockMvc
            .perform(
                patch("/api/field-test-pagination-entities")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldTestPaginationEntity))
            )
            .andExpect(status().isOk());

        // Validate the FieldTestPaginationEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestPaginationEntity testFieldTestPaginationEntity = fieldTestPaginationEntityList.get(
            fieldTestPaginationEntityList.size() - 1
        );
        assertThat(testFieldTestPaginationEntity.getStringAlice()).isEqualTo(DEFAULT_STRING_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringRequiredAlice()).isEqualTo(DEFAULT_STRING_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMinlengthAlice()).isEqualTo(UPDATED_STRING_MINLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMaxlengthAlice()).isEqualTo(DEFAULT_STRING_MAXLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringPatternAlice()).isEqualTo(UPDATED_STRING_PATTERN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerAlice()).isEqualTo(DEFAULT_INTEGER_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerRequiredAlice()).isEqualTo(UPDATED_INTEGER_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMinAlice()).isEqualTo(DEFAULT_INTEGER_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMaxAlice()).isEqualTo(UPDATED_INTEGER_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongAlice()).isEqualTo(UPDATED_LONG_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongRequiredAlice()).isEqualTo(DEFAULT_LONG_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMinAlice()).isEqualTo(DEFAULT_LONG_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMaxAlice()).isEqualTo(DEFAULT_LONG_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatAlice()).isEqualTo(UPDATED_FLOAT_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatRequiredAlice()).isEqualTo(UPDATED_FLOAT_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMinAlice()).isEqualTo(UPDATED_FLOAT_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMaxAlice()).isEqualTo(DEFAULT_FLOAT_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleRequiredAlice()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMinAlice()).isEqualTo(DEFAULT_DOUBLE_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMaxAlice()).isEqualTo(DEFAULT_DOUBLE_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalRequiredAlice()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMinAlice()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMaxAlice()).isEqualByComparingTo(DEFAULT_BIG_DECIMAL_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateAlice()).isEqualTo(UPDATED_LOCAL_DATE_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateRequiredAlice()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstantAlice()).isEqualTo(UPDATED_INSTANT_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstanteRequiredAlice()).isEqualTo(UPDATED_INSTANTE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeAlice()).isEqualTo(DEFAULT_ZONED_DATE_TIME_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeRequiredAlice()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationAlice()).isEqualTo(DEFAULT_DURATION_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationRequiredAlice()).isEqualTo(DEFAULT_DURATION_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanAlice()).isEqualTo(UPDATED_BOOLEAN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanRequiredAlice()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumAlice()).isEqualTo(UPDATED_ENUM_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumRequiredAlice()).isEqualTo(UPDATED_ENUM_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidAlice()).isEqualTo(UPDATED_UUID_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidRequiredAlice()).isEqualTo(UPDATED_UUID_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAliceContentType()).isEqualTo(DEFAULT_BYTE_IMAGE_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAliceContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAliceContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAlice()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAliceContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAlice()).isEqualTo(UPDATED_BYTE_ANY_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAliceContentType()).isEqualTo(UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAlice()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAlice()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAlice()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAliceContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteTextAlice()).isEqualTo(UPDATED_BYTE_TEXT_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteTextRequiredAlice()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_ALICE);
    }

    @Test
    void fullUpdateFieldTestPaginationEntityWithPatch() throws Exception {
        // Initialize the database
        fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);

        int databaseSizeBeforeUpdate = fieldTestPaginationEntityRepository.findAll().size();

        // Update the fieldTestPaginationEntity using partial update
        FieldTestPaginationEntity partialUpdatedFieldTestPaginationEntity = new FieldTestPaginationEntity();
        partialUpdatedFieldTestPaginationEntity.setId(fieldTestPaginationEntity.getId());

        partialUpdatedFieldTestPaginationEntity
            .stringAlice(UPDATED_STRING_ALICE)
            .stringRequiredAlice(UPDATED_STRING_REQUIRED_ALICE)
            .stringMinlengthAlice(UPDATED_STRING_MINLENGTH_ALICE)
            .stringMaxlengthAlice(UPDATED_STRING_MAXLENGTH_ALICE)
            .stringPatternAlice(UPDATED_STRING_PATTERN_ALICE)
            .integerAlice(UPDATED_INTEGER_ALICE)
            .integerRequiredAlice(UPDATED_INTEGER_REQUIRED_ALICE)
            .integerMinAlice(UPDATED_INTEGER_MIN_ALICE)
            .integerMaxAlice(UPDATED_INTEGER_MAX_ALICE)
            .longAlice(UPDATED_LONG_ALICE)
            .longRequiredAlice(UPDATED_LONG_REQUIRED_ALICE)
            .longMinAlice(UPDATED_LONG_MIN_ALICE)
            .longMaxAlice(UPDATED_LONG_MAX_ALICE)
            .floatAlice(UPDATED_FLOAT_ALICE)
            .floatRequiredAlice(UPDATED_FLOAT_REQUIRED_ALICE)
            .floatMinAlice(UPDATED_FLOAT_MIN_ALICE)
            .floatMaxAlice(UPDATED_FLOAT_MAX_ALICE)
            .doubleRequiredAlice(UPDATED_DOUBLE_REQUIRED_ALICE)
            .doubleMinAlice(UPDATED_DOUBLE_MIN_ALICE)
            .doubleMaxAlice(UPDATED_DOUBLE_MAX_ALICE)
            .bigDecimalRequiredAlice(UPDATED_BIG_DECIMAL_REQUIRED_ALICE)
            .bigDecimalMinAlice(UPDATED_BIG_DECIMAL_MIN_ALICE)
            .bigDecimalMaxAlice(UPDATED_BIG_DECIMAL_MAX_ALICE)
            .localDateAlice(UPDATED_LOCAL_DATE_ALICE)
            .localDateRequiredAlice(UPDATED_LOCAL_DATE_REQUIRED_ALICE)
            .instantAlice(UPDATED_INSTANT_ALICE)
            .instanteRequiredAlice(UPDATED_INSTANTE_REQUIRED_ALICE)
            .zonedDateTimeAlice(UPDATED_ZONED_DATE_TIME_ALICE)
            .zonedDateTimeRequiredAlice(UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE)
            .durationAlice(UPDATED_DURATION_ALICE)
            .durationRequiredAlice(UPDATED_DURATION_REQUIRED_ALICE)
            .booleanAlice(UPDATED_BOOLEAN_ALICE)
            .booleanRequiredAlice(UPDATED_BOOLEAN_REQUIRED_ALICE)
            .enumAlice(UPDATED_ENUM_ALICE)
            .enumRequiredAlice(UPDATED_ENUM_REQUIRED_ALICE)
            .uuidAlice(UPDATED_UUID_ALICE)
            .uuidRequiredAlice(UPDATED_UUID_REQUIRED_ALICE)
            .byteImageAlice(UPDATED_BYTE_IMAGE_ALICE)
            .byteImageAliceContentType(UPDATED_BYTE_IMAGE_ALICE_CONTENT_TYPE)
            .byteImageRequiredAlice(UPDATED_BYTE_IMAGE_REQUIRED_ALICE)
            .byteImageRequiredAliceContentType(UPDATED_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE)
            .byteImageMinbytesAlice(UPDATED_BYTE_IMAGE_MINBYTES_ALICE)
            .byteImageMinbytesAliceContentType(UPDATED_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE)
            .byteImageMaxbytesAlice(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE)
            .byteImageMaxbytesAliceContentType(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteAnyAlice(UPDATED_BYTE_ANY_ALICE)
            .byteAnyAliceContentType(UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE)
            .byteAnyRequiredAlice(UPDATED_BYTE_ANY_REQUIRED_ALICE)
            .byteAnyRequiredAliceContentType(UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE)
            .byteAnyMinbytesAlice(UPDATED_BYTE_ANY_MINBYTES_ALICE)
            .byteAnyMinbytesAliceContentType(UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE)
            .byteAnyMaxbytesAlice(UPDATED_BYTE_ANY_MAXBYTES_ALICE)
            .byteAnyMaxbytesAliceContentType(UPDATED_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE)
            .byteTextAlice(UPDATED_BYTE_TEXT_ALICE)
            .byteTextRequiredAlice(UPDATED_BYTE_TEXT_REQUIRED_ALICE);

        restFieldTestPaginationEntityMockMvc
            .perform(
                patch("/api/field-test-pagination-entities")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldTestPaginationEntity))
            )
            .andExpect(status().isOk());

        // Validate the FieldTestPaginationEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestPaginationEntity testFieldTestPaginationEntity = fieldTestPaginationEntityList.get(
            fieldTestPaginationEntityList.size() - 1
        );
        assertThat(testFieldTestPaginationEntity.getStringAlice()).isEqualTo(UPDATED_STRING_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringRequiredAlice()).isEqualTo(UPDATED_STRING_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMinlengthAlice()).isEqualTo(UPDATED_STRING_MINLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringMaxlengthAlice()).isEqualTo(UPDATED_STRING_MAXLENGTH_ALICE);
        assertThat(testFieldTestPaginationEntity.getStringPatternAlice()).isEqualTo(UPDATED_STRING_PATTERN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerAlice()).isEqualTo(UPDATED_INTEGER_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerRequiredAlice()).isEqualTo(UPDATED_INTEGER_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMinAlice()).isEqualTo(UPDATED_INTEGER_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getIntegerMaxAlice()).isEqualTo(UPDATED_INTEGER_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongAlice()).isEqualTo(UPDATED_LONG_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongRequiredAlice()).isEqualTo(UPDATED_LONG_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMinAlice()).isEqualTo(UPDATED_LONG_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getLongMaxAlice()).isEqualTo(UPDATED_LONG_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatAlice()).isEqualTo(UPDATED_FLOAT_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatRequiredAlice()).isEqualTo(UPDATED_FLOAT_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMinAlice()).isEqualTo(UPDATED_FLOAT_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getFloatMaxAlice()).isEqualTo(UPDATED_FLOAT_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleRequiredAlice()).isEqualTo(UPDATED_DOUBLE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMinAlice()).isEqualTo(UPDATED_DOUBLE_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getDoubleMaxAlice()).isEqualTo(UPDATED_DOUBLE_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalRequiredAlice()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMinAlice()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_MIN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBigDecimalMaxAlice()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_MAX_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateAlice()).isEqualTo(UPDATED_LOCAL_DATE_ALICE);
        assertThat(testFieldTestPaginationEntity.getLocalDateRequiredAlice()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstantAlice()).isEqualTo(UPDATED_INSTANT_ALICE);
        assertThat(testFieldTestPaginationEntity.getInstanteRequiredAlice()).isEqualTo(UPDATED_INSTANTE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeAlice()).isEqualTo(UPDATED_ZONED_DATE_TIME_ALICE);
        assertThat(testFieldTestPaginationEntity.getZonedDateTimeRequiredAlice()).isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationAlice()).isEqualTo(UPDATED_DURATION_ALICE);
        assertThat(testFieldTestPaginationEntity.getDurationRequiredAlice()).isEqualTo(UPDATED_DURATION_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanAlice()).isEqualTo(UPDATED_BOOLEAN_ALICE);
        assertThat(testFieldTestPaginationEntity.getBooleanRequiredAlice()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumAlice()).isEqualTo(UPDATED_ENUM_ALICE);
        assertThat(testFieldTestPaginationEntity.getEnumRequiredAlice()).isEqualTo(UPDATED_ENUM_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidAlice()).isEqualTo(UPDATED_UUID_ALICE);
        assertThat(testFieldTestPaginationEntity.getUuidRequiredAlice()).isEqualTo(UPDATED_UUID_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAlice()).isEqualTo(UPDATED_BYTE_IMAGE_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageAliceContentType()).isEqualTo(UPDATED_BYTE_IMAGE_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAlice()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageRequiredAliceContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAlice()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMinbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAlice()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteImageMaxbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAlice()).isEqualTo(UPDATED_BYTE_ANY_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyAliceContentType()).isEqualTo(UPDATED_BYTE_ANY_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAlice()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyRequiredAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_REQUIRED_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAlice()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMinbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MINBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAlice()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteAnyMaxbytesAliceContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_ALICE_CONTENT_TYPE);
        assertThat(testFieldTestPaginationEntity.getByteTextAlice()).isEqualTo(UPDATED_BYTE_TEXT_ALICE);
        assertThat(testFieldTestPaginationEntity.getByteTextRequiredAlice()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_ALICE);
    }

    @Test
    void partialUpdateFieldTestPaginationEntityShouldThrown() throws Exception {
        // Update the fieldTestPaginationEntity without id should throw
        FieldTestPaginationEntity partialUpdatedFieldTestPaginationEntity = new FieldTestPaginationEntity();

        restFieldTestPaginationEntityMockMvc
            .perform(
                patch("/api/field-test-pagination-entities")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldTestPaginationEntity))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteFieldTestPaginationEntity() throws Exception {
        // Initialize the database
        fieldTestPaginationEntityRepository.save(fieldTestPaginationEntity);

        int databaseSizeBeforeDelete = fieldTestPaginationEntityRepository.findAll().size();

        // Delete the fieldTestPaginationEntity
        restFieldTestPaginationEntityMockMvc
            .perform(
                delete("/api/field-test-pagination-entities/{id}", fieldTestPaginationEntity.getId()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestPaginationEntity> fieldTestPaginationEntityList = fieldTestPaginationEntityRepository.findAll();
        assertThat(fieldTestPaginationEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
