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
import tech.jhipster.sample.domain.FieldTestServiceClassAndJpaFilteringEntity;
import tech.jhipster.sample.domain.enumeration.EnumFieldClass;
import tech.jhipster.sample.domain.enumeration.EnumRequiredFieldClass;
import tech.jhipster.sample.repository.FieldTestServiceClassAndJpaFilteringEntityRepository;

/**
 * Integration tests for the {@link FieldTestServiceClassAndJpaFilteringEntityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FieldTestServiceClassAndJpaFilteringEntityResourceIT {

    private static final String DEFAULT_STRING_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_REQUIRED_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_REQUIRED_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MINLENGTH_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MINLENGTH_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_MAXLENGTH_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_MAXLENGTH_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_PATTERN_BOB = "AAAAAAAAAA";
    private static final String UPDATED_STRING_PATTERN_BOB = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGER_BOB = 1;
    private static final Integer UPDATED_INTEGER_BOB = 2;

    private static final Integer DEFAULT_INTEGER_REQUIRED_BOB = 1;
    private static final Integer UPDATED_INTEGER_REQUIRED_BOB = 2;

    private static final Integer DEFAULT_INTEGER_MIN_BOB = 0;
    private static final Integer UPDATED_INTEGER_MIN_BOB = 1;

    private static final Integer DEFAULT_INTEGER_MAX_BOB = 100;
    private static final Integer UPDATED_INTEGER_MAX_BOB = 99;

    private static final Long DEFAULT_LONG_BOB = 1L;
    private static final Long UPDATED_LONG_BOB = 2L;

    private static final Long DEFAULT_LONG_REQUIRED_BOB = 1L;
    private static final Long UPDATED_LONG_REQUIRED_BOB = 2L;

    private static final Long DEFAULT_LONG_MIN_BOB = 0L;
    private static final Long UPDATED_LONG_MIN_BOB = 1L;

    private static final Long DEFAULT_LONG_MAX_BOB = 100L;
    private static final Long UPDATED_LONG_MAX_BOB = 99L;

    private static final Float DEFAULT_FLOAT_BOB = 1F;
    private static final Float UPDATED_FLOAT_BOB = 2F;

    private static final Float DEFAULT_FLOAT_REQUIRED_BOB = 1F;
    private static final Float UPDATED_FLOAT_REQUIRED_BOB = 2F;

    private static final Float DEFAULT_FLOAT_MIN_BOB = 0F;
    private static final Float UPDATED_FLOAT_MIN_BOB = 1F;

    private static final Float DEFAULT_FLOAT_MAX_BOB = 100F;
    private static final Float UPDATED_FLOAT_MAX_BOB = 99F;

    private static final Double DEFAULT_DOUBLE_REQUIRED_BOB = 1D;
    private static final Double UPDATED_DOUBLE_REQUIRED_BOB = 2D;

    private static final Double DEFAULT_DOUBLE_MIN_BOB = 0D;
    private static final Double UPDATED_DOUBLE_MIN_BOB = 1D;

    private static final Double DEFAULT_DOUBLE_MAX_BOB = 100D;
    private static final Double UPDATED_DOUBLE_MAX_BOB = 99D;

    private static final BigDecimal DEFAULT_BIG_DECIMAL_REQUIRED_BOB = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIG_DECIMAL_REQUIRED_BOB = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MIN_BOB = new BigDecimal(0);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MIN_BOB = new BigDecimal(1);

    private static final BigDecimal DEFAULT_BIG_DECIMAL_MAX_BOB = new BigDecimal(100);
    private static final BigDecimal UPDATED_BIG_DECIMAL_MAX_BOB = new BigDecimal(99);

    private static final LocalDate DEFAULT_LOCAL_DATE_BOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_BOB = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOCAL_DATE_REQUIRED_BOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOCAL_DATE_REQUIRED_BOB = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_INSTANT_BOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT_BOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSTANTE_REQUIRED_BOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANTE_REQUIRED_BOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_BOB = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_BOB = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB = ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(0L),
        ZoneOffset.UTC
    );
    private static final ZonedDateTime UPDATED_ZONED_DATE_TIME_REQUIRED_BOB = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Duration DEFAULT_DURATION_BOB = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION_BOB = Duration.ofHours(12);

    private static final Duration DEFAULT_DURATION_REQUIRED_BOB = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION_REQUIRED_BOB = Duration.ofHours(12);

    private static final Boolean DEFAULT_BOOLEAN_BOB = false;
    private static final Boolean UPDATED_BOOLEAN_BOB = true;

    private static final Boolean DEFAULT_BOOLEAN_REQUIRED_BOB = false;
    private static final Boolean UPDATED_BOOLEAN_REQUIRED_BOB = true;

    private static final EnumFieldClass DEFAULT_ENUM_BOB = EnumFieldClass.ENUM_VALUE_1;
    private static final EnumFieldClass UPDATED_ENUM_BOB = EnumFieldClass.ENUM_VALUE_2;

    private static final EnumRequiredFieldClass DEFAULT_ENUM_REQUIRED_BOB = EnumRequiredFieldClass.ENUM_VALUE_1;
    private static final EnumRequiredFieldClass UPDATED_ENUM_REQUIRED_BOB = EnumRequiredFieldClass.ENUM_VALUE_2;

    private static final UUID DEFAULT_UUID_BOB = UUID.randomUUID();
    private static final UUID UPDATED_UUID_BOB = UUID.randomUUID();

    private static final UUID DEFAULT_UUID_REQUIRED_BOB = UUID.randomUUID();
    private static final UUID UPDATED_UUID_REQUIRED_BOB = UUID.randomUUID();

    private static final byte[] DEFAULT_BYTE_IMAGE_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_REQUIRED_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_REQUIRED_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MINBYTES_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MINBYTES_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_IMAGE_MAXBYTES_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_IMAGE_MAXBYTES_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_REQUIRED_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_REQUIRED_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MINBYTES_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MINBYTES_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BYTE_ANY_MAXBYTES_BOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BYTE_ANY_MAXBYTES_BOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BYTE_TEXT_BOB = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_BOB = "BBBBBBBBBB";

    private static final String DEFAULT_BYTE_TEXT_REQUIRED_BOB = "AAAAAAAAAA";
    private static final String UPDATED_BYTE_TEXT_REQUIRED_BOB = "BBBBBBBBBB";

    @Autowired
    private FieldTestServiceClassAndJpaFilteringEntityRepository fieldTestServiceClassAndJpaFilteringEntityRepository;

    @Autowired
    private MockMvc restFieldTestServiceClassAndJpaFilteringEntityMockMvc;

    private FieldTestServiceClassAndJpaFilteringEntity fieldTestServiceClassAndJpaFilteringEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldTestServiceClassAndJpaFilteringEntity createEntity() {
        FieldTestServiceClassAndJpaFilteringEntity fieldTestServiceClassAndJpaFilteringEntity = new FieldTestServiceClassAndJpaFilteringEntity()
            .stringBob(DEFAULT_STRING_BOB)
            .stringRequiredBob(DEFAULT_STRING_REQUIRED_BOB)
            .stringMinlengthBob(DEFAULT_STRING_MINLENGTH_BOB)
            .stringMaxlengthBob(DEFAULT_STRING_MAXLENGTH_BOB)
            .stringPatternBob(DEFAULT_STRING_PATTERN_BOB)
            .integerBob(DEFAULT_INTEGER_BOB)
            .integerRequiredBob(DEFAULT_INTEGER_REQUIRED_BOB)
            .integerMinBob(DEFAULT_INTEGER_MIN_BOB)
            .integerMaxBob(DEFAULT_INTEGER_MAX_BOB)
            .longBob(DEFAULT_LONG_BOB)
            .longRequiredBob(DEFAULT_LONG_REQUIRED_BOB)
            .longMinBob(DEFAULT_LONG_MIN_BOB)
            .longMaxBob(DEFAULT_LONG_MAX_BOB)
            .floatBob(DEFAULT_FLOAT_BOB)
            .floatRequiredBob(DEFAULT_FLOAT_REQUIRED_BOB)
            .floatMinBob(DEFAULT_FLOAT_MIN_BOB)
            .floatMaxBob(DEFAULT_FLOAT_MAX_BOB)
            .doubleRequiredBob(DEFAULT_DOUBLE_REQUIRED_BOB)
            .doubleMinBob(DEFAULT_DOUBLE_MIN_BOB)
            .doubleMaxBob(DEFAULT_DOUBLE_MAX_BOB)
            .bigDecimalRequiredBob(DEFAULT_BIG_DECIMAL_REQUIRED_BOB)
            .bigDecimalMinBob(DEFAULT_BIG_DECIMAL_MIN_BOB)
            .bigDecimalMaxBob(DEFAULT_BIG_DECIMAL_MAX_BOB)
            .localDateBob(DEFAULT_LOCAL_DATE_BOB)
            .localDateRequiredBob(DEFAULT_LOCAL_DATE_REQUIRED_BOB)
            .instantBob(DEFAULT_INSTANT_BOB)
            .instanteRequiredBob(DEFAULT_INSTANTE_REQUIRED_BOB)
            .zonedDateTimeBob(DEFAULT_ZONED_DATE_TIME_BOB)
            .zonedDateTimeRequiredBob(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB)
            .durationBob(DEFAULT_DURATION_BOB)
            .durationRequiredBob(DEFAULT_DURATION_REQUIRED_BOB)
            .booleanBob(DEFAULT_BOOLEAN_BOB)
            .booleanRequiredBob(DEFAULT_BOOLEAN_REQUIRED_BOB)
            .enumBob(DEFAULT_ENUM_BOB)
            .enumRequiredBob(DEFAULT_ENUM_REQUIRED_BOB)
            .uuidBob(DEFAULT_UUID_BOB)
            .uuidRequiredBob(DEFAULT_UUID_REQUIRED_BOB)
            .byteImageBob(DEFAULT_BYTE_IMAGE_BOB)
            .byteImageBobContentType(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE)
            .byteImageRequiredBob(DEFAULT_BYTE_IMAGE_REQUIRED_BOB)
            .byteImageRequiredBobContentType(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)
            .byteImageMinbytesBob(DEFAULT_BYTE_IMAGE_MINBYTES_BOB)
            .byteImageMinbytesBobContentType(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)
            .byteImageMaxbytesBob(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB)
            .byteImageMaxbytesBobContentType(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)
            .byteAnyBob(DEFAULT_BYTE_ANY_BOB)
            .byteAnyBobContentType(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE)
            .byteAnyRequiredBob(DEFAULT_BYTE_ANY_REQUIRED_BOB)
            .byteAnyRequiredBobContentType(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)
            .byteAnyMinbytesBob(DEFAULT_BYTE_ANY_MINBYTES_BOB)
            .byteAnyMinbytesBobContentType(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)
            .byteAnyMaxbytesBob(DEFAULT_BYTE_ANY_MAXBYTES_BOB)
            .byteAnyMaxbytesBobContentType(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)
            .byteTextBob(DEFAULT_BYTE_TEXT_BOB)
            .byteTextRequiredBob(DEFAULT_BYTE_TEXT_REQUIRED_BOB);
        return fieldTestServiceClassAndJpaFilteringEntity;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldTestServiceClassAndJpaFilteringEntity createUpdatedEntity() {
        FieldTestServiceClassAndJpaFilteringEntity fieldTestServiceClassAndJpaFilteringEntity = new FieldTestServiceClassAndJpaFilteringEntity()
            .stringBob(UPDATED_STRING_BOB)
            .stringRequiredBob(UPDATED_STRING_REQUIRED_BOB)
            .stringMinlengthBob(UPDATED_STRING_MINLENGTH_BOB)
            .stringMaxlengthBob(UPDATED_STRING_MAXLENGTH_BOB)
            .stringPatternBob(UPDATED_STRING_PATTERN_BOB)
            .integerBob(UPDATED_INTEGER_BOB)
            .integerRequiredBob(UPDATED_INTEGER_REQUIRED_BOB)
            .integerMinBob(UPDATED_INTEGER_MIN_BOB)
            .integerMaxBob(UPDATED_INTEGER_MAX_BOB)
            .longBob(UPDATED_LONG_BOB)
            .longRequiredBob(UPDATED_LONG_REQUIRED_BOB)
            .longMinBob(UPDATED_LONG_MIN_BOB)
            .longMaxBob(UPDATED_LONG_MAX_BOB)
            .floatBob(UPDATED_FLOAT_BOB)
            .floatRequiredBob(UPDATED_FLOAT_REQUIRED_BOB)
            .floatMinBob(UPDATED_FLOAT_MIN_BOB)
            .floatMaxBob(UPDATED_FLOAT_MAX_BOB)
            .doubleRequiredBob(UPDATED_DOUBLE_REQUIRED_BOB)
            .doubleMinBob(UPDATED_DOUBLE_MIN_BOB)
            .doubleMaxBob(UPDATED_DOUBLE_MAX_BOB)
            .bigDecimalRequiredBob(UPDATED_BIG_DECIMAL_REQUIRED_BOB)
            .bigDecimalMinBob(UPDATED_BIG_DECIMAL_MIN_BOB)
            .bigDecimalMaxBob(UPDATED_BIG_DECIMAL_MAX_BOB)
            .localDateBob(UPDATED_LOCAL_DATE_BOB)
            .localDateRequiredBob(UPDATED_LOCAL_DATE_REQUIRED_BOB)
            .instantBob(UPDATED_INSTANT_BOB)
            .instanteRequiredBob(UPDATED_INSTANTE_REQUIRED_BOB)
            .zonedDateTimeBob(UPDATED_ZONED_DATE_TIME_BOB)
            .zonedDateTimeRequiredBob(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB)
            .durationBob(UPDATED_DURATION_BOB)
            .durationRequiredBob(UPDATED_DURATION_REQUIRED_BOB)
            .booleanBob(UPDATED_BOOLEAN_BOB)
            .booleanRequiredBob(UPDATED_BOOLEAN_REQUIRED_BOB)
            .enumBob(UPDATED_ENUM_BOB)
            .enumRequiredBob(UPDATED_ENUM_REQUIRED_BOB)
            .uuidBob(UPDATED_UUID_BOB)
            .uuidRequiredBob(UPDATED_UUID_REQUIRED_BOB)
            .byteImageBob(UPDATED_BYTE_IMAGE_BOB)
            .byteImageBobContentType(UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE)
            .byteImageRequiredBob(UPDATED_BYTE_IMAGE_REQUIRED_BOB)
            .byteImageRequiredBobContentType(UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)
            .byteImageMinbytesBob(UPDATED_BYTE_IMAGE_MINBYTES_BOB)
            .byteImageMinbytesBobContentType(UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)
            .byteImageMaxbytesBob(UPDATED_BYTE_IMAGE_MAXBYTES_BOB)
            .byteImageMaxbytesBobContentType(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)
            .byteAnyBob(UPDATED_BYTE_ANY_BOB)
            .byteAnyBobContentType(UPDATED_BYTE_ANY_BOB_CONTENT_TYPE)
            .byteAnyRequiredBob(UPDATED_BYTE_ANY_REQUIRED_BOB)
            .byteAnyRequiredBobContentType(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)
            .byteAnyMinbytesBob(UPDATED_BYTE_ANY_MINBYTES_BOB)
            .byteAnyMinbytesBobContentType(UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)
            .byteAnyMaxbytesBob(UPDATED_BYTE_ANY_MAXBYTES_BOB)
            .byteAnyMaxbytesBobContentType(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)
            .byteTextBob(UPDATED_BYTE_TEXT_BOB)
            .byteTextRequiredBob(UPDATED_BYTE_TEXT_REQUIRED_BOB);
        return fieldTestServiceClassAndJpaFilteringEntity;
    }

    @BeforeEach
    public void initTest() {
        fieldTestServiceClassAndJpaFilteringEntityRepository.deleteAll();
        fieldTestServiceClassAndJpaFilteringEntity = createEntity();
    }

    @Test
    void createFieldTestServiceClassAndJpaFilteringEntity() throws Exception {
        int databaseSizeBeforeCreate = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // Create the FieldTestServiceClassAndJpaFilteringEntity
        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isCreated());

        // Validate the FieldTestServiceClassAndJpaFilteringEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeCreate + 1);
        FieldTestServiceClassAndJpaFilteringEntity testFieldTestServiceClassAndJpaFilteringEntity = fieldTestServiceClassAndJpaFilteringEntityList.get(
            fieldTestServiceClassAndJpaFilteringEntityList.size() - 1
        );
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringBob()).isEqualTo(DEFAULT_STRING_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringRequiredBob()).isEqualTo(DEFAULT_STRING_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMinlengthBob()).isEqualTo(DEFAULT_STRING_MINLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMaxlengthBob()).isEqualTo(DEFAULT_STRING_MAXLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringPatternBob()).isEqualTo(DEFAULT_STRING_PATTERN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerBob()).isEqualTo(DEFAULT_INTEGER_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerRequiredBob()).isEqualTo(DEFAULT_INTEGER_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMinBob()).isEqualTo(DEFAULT_INTEGER_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMaxBob()).isEqualTo(DEFAULT_INTEGER_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongBob()).isEqualTo(DEFAULT_LONG_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongRequiredBob()).isEqualTo(DEFAULT_LONG_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMinBob()).isEqualTo(DEFAULT_LONG_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMaxBob()).isEqualTo(DEFAULT_LONG_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatBob()).isEqualTo(DEFAULT_FLOAT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatRequiredBob()).isEqualTo(DEFAULT_FLOAT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMinBob()).isEqualTo(DEFAULT_FLOAT_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMaxBob()).isEqualTo(DEFAULT_FLOAT_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleRequiredBob()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMinBob()).isEqualTo(DEFAULT_DOUBLE_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMaxBob()).isEqualTo(DEFAULT_DOUBLE_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalRequiredBob())
            .isEqualByComparingTo(DEFAULT_BIG_DECIMAL_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMinBob()).isEqualByComparingTo(DEFAULT_BIG_DECIMAL_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMaxBob()).isEqualByComparingTo(DEFAULT_BIG_DECIMAL_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateBob()).isEqualTo(DEFAULT_LOCAL_DATE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateRequiredBob()).isEqualTo(DEFAULT_LOCAL_DATE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstantBob()).isEqualTo(DEFAULT_INSTANT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstanteRequiredBob()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeBob()).isEqualTo(DEFAULT_ZONED_DATE_TIME_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeRequiredBob())
            .isEqualTo(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationBob()).isEqualTo(DEFAULT_DURATION_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationRequiredBob()).isEqualTo(DEFAULT_DURATION_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanBob()).isEqualTo(DEFAULT_BOOLEAN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanRequiredBob()).isEqualTo(DEFAULT_BOOLEAN_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumBob()).isEqualTo(DEFAULT_ENUM_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumRequiredBob()).isEqualTo(DEFAULT_ENUM_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidBob()).isEqualTo(DEFAULT_UUID_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidRequiredBob()).isEqualTo(DEFAULT_UUID_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBob()).isEqualTo(DEFAULT_BYTE_IMAGE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBobContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBob()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBobContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBob()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBobContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBob()).isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBobContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBob()).isEqualTo(DEFAULT_BYTE_ANY_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBobContentType()).isEqualTo(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBob()).isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBobContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBob()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBobContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBob()).isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBobContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextBob()).isEqualTo(DEFAULT_BYTE_TEXT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextRequiredBob()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_BOB);
    }

    @Test
    void createFieldTestServiceClassAndJpaFilteringEntityWithExistingId() throws Exception {
        // Create the FieldTestServiceClassAndJpaFilteringEntity with an existing ID
        fieldTestServiceClassAndJpaFilteringEntity.setId("existing_id");

        int databaseSizeBeforeCreate = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldTestServiceClassAndJpaFilteringEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkStringRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setStringRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIntegerRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setIntegerRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLongRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setLongRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFloatRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setFloatRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDoubleRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setDoubleRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBigDecimalRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setBigDecimalRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLocalDateRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setLocalDateRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInstanteRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setInstanteRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkZonedDateTimeRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setZonedDateTimeRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDurationRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setDurationRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBooleanRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setBooleanRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEnumRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setEnumRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUuidRequiredBobIsRequired() throws Exception {
        int databaseSizeBeforeTest = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();
        // set the field null
        fieldTestServiceClassAndJpaFilteringEntity.setUuidRequiredBob(null);

        // Create the FieldTestServiceClassAndJpaFilteringEntity, which fails.

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                post("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFieldTestServiceClassAndJpaFilteringEntities() throws Exception {
        // Initialize the database
        fieldTestServiceClassAndJpaFilteringEntityRepository.save(fieldTestServiceClassAndJpaFilteringEntity);

        // Get all the fieldTestServiceClassAndJpaFilteringEntityList
        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(get("/api/field-test-service-class-and-jpa-filtering-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldTestServiceClassAndJpaFilteringEntity.getId())))
            .andExpect(jsonPath("$.[*].stringBob").value(hasItem(DEFAULT_STRING_BOB)))
            .andExpect(jsonPath("$.[*].stringRequiredBob").value(hasItem(DEFAULT_STRING_REQUIRED_BOB)))
            .andExpect(jsonPath("$.[*].stringMinlengthBob").value(hasItem(DEFAULT_STRING_MINLENGTH_BOB)))
            .andExpect(jsonPath("$.[*].stringMaxlengthBob").value(hasItem(DEFAULT_STRING_MAXLENGTH_BOB)))
            .andExpect(jsonPath("$.[*].stringPatternBob").value(hasItem(DEFAULT_STRING_PATTERN_BOB)))
            .andExpect(jsonPath("$.[*].integerBob").value(hasItem(DEFAULT_INTEGER_BOB)))
            .andExpect(jsonPath("$.[*].integerRequiredBob").value(hasItem(DEFAULT_INTEGER_REQUIRED_BOB)))
            .andExpect(jsonPath("$.[*].integerMinBob").value(hasItem(DEFAULT_INTEGER_MIN_BOB)))
            .andExpect(jsonPath("$.[*].integerMaxBob").value(hasItem(DEFAULT_INTEGER_MAX_BOB)))
            .andExpect(jsonPath("$.[*].longBob").value(hasItem(DEFAULT_LONG_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longRequiredBob").value(hasItem(DEFAULT_LONG_REQUIRED_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longMinBob").value(hasItem(DEFAULT_LONG_MIN_BOB.intValue())))
            .andExpect(jsonPath("$.[*].longMaxBob").value(hasItem(DEFAULT_LONG_MAX_BOB.intValue())))
            .andExpect(jsonPath("$.[*].floatBob").value(hasItem(DEFAULT_FLOAT_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatRequiredBob").value(hasItem(DEFAULT_FLOAT_REQUIRED_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMinBob").value(hasItem(DEFAULT_FLOAT_MIN_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].floatMaxBob").value(hasItem(DEFAULT_FLOAT_MAX_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleRequiredBob").value(hasItem(DEFAULT_DOUBLE_REQUIRED_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMinBob").value(hasItem(DEFAULT_DOUBLE_MIN_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].doubleMaxBob").value(hasItem(DEFAULT_DOUBLE_MAX_BOB.doubleValue())))
            .andExpect(jsonPath("$.[*].bigDecimalRequiredBob").value(hasItem(sameNumber(DEFAULT_BIG_DECIMAL_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].bigDecimalMinBob").value(hasItem(sameNumber(DEFAULT_BIG_DECIMAL_MIN_BOB))))
            .andExpect(jsonPath("$.[*].bigDecimalMaxBob").value(hasItem(sameNumber(DEFAULT_BIG_DECIMAL_MAX_BOB))))
            .andExpect(jsonPath("$.[*].localDateBob").value(hasItem(DEFAULT_LOCAL_DATE_BOB.toString())))
            .andExpect(jsonPath("$.[*].localDateRequiredBob").value(hasItem(DEFAULT_LOCAL_DATE_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].instantBob").value(hasItem(DEFAULT_INSTANT_BOB.toString())))
            .andExpect(jsonPath("$.[*].instanteRequiredBob").value(hasItem(DEFAULT_INSTANTE_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].zonedDateTimeBob").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_BOB))))
            .andExpect(jsonPath("$.[*].zonedDateTimeRequiredBob").value(hasItem(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].durationBob").value(hasItem(DEFAULT_DURATION_BOB.toString())))
            .andExpect(jsonPath("$.[*].durationRequiredBob").value(hasItem(DEFAULT_DURATION_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].booleanBob").value(hasItem(DEFAULT_BOOLEAN_BOB.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanRequiredBob").value(hasItem(DEFAULT_BOOLEAN_REQUIRED_BOB.booleanValue())))
            .andExpect(jsonPath("$.[*].enumBob").value(hasItem(DEFAULT_ENUM_BOB.toString())))
            .andExpect(jsonPath("$.[*].enumRequiredBob").value(hasItem(DEFAULT_ENUM_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].uuidBob").value(hasItem(DEFAULT_UUID_BOB.toString())))
            .andExpect(jsonPath("$.[*].uuidRequiredBob").value(hasItem(DEFAULT_UUID_REQUIRED_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteImageBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_BOB))))
            .andExpect(jsonPath("$.[*].byteImageRequiredBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageRequiredBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].byteImageMinbytesBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMinbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesBobContentType").value(hasItem(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteImageMaxbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyBobContentType").value(hasItem(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyRequiredBobContentType").value(hasItem(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyRequiredBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesBobContentType").value(hasItem(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMinbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesBobContentType").value(hasItem(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].byteAnyMaxbytesBob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_BOB))))
            .andExpect(jsonPath("$.[*].byteTextBob").value(hasItem(DEFAULT_BYTE_TEXT_BOB.toString())))
            .andExpect(jsonPath("$.[*].byteTextRequiredBob").value(hasItem(DEFAULT_BYTE_TEXT_REQUIRED_BOB.toString())));
    }

    @Test
    void getFieldTestServiceClassAndJpaFilteringEntity() throws Exception {
        // Initialize the database
        fieldTestServiceClassAndJpaFilteringEntityRepository.save(fieldTestServiceClassAndJpaFilteringEntity);

        // Get the fieldTestServiceClassAndJpaFilteringEntity
        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                get("/api/field-test-service-class-and-jpa-filtering-entities/{id}", fieldTestServiceClassAndJpaFilteringEntity.getId())
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fieldTestServiceClassAndJpaFilteringEntity.getId()))
            .andExpect(jsonPath("$.stringBob").value(DEFAULT_STRING_BOB))
            .andExpect(jsonPath("$.stringRequiredBob").value(DEFAULT_STRING_REQUIRED_BOB))
            .andExpect(jsonPath("$.stringMinlengthBob").value(DEFAULT_STRING_MINLENGTH_BOB))
            .andExpect(jsonPath("$.stringMaxlengthBob").value(DEFAULT_STRING_MAXLENGTH_BOB))
            .andExpect(jsonPath("$.stringPatternBob").value(DEFAULT_STRING_PATTERN_BOB))
            .andExpect(jsonPath("$.integerBob").value(DEFAULT_INTEGER_BOB))
            .andExpect(jsonPath("$.integerRequiredBob").value(DEFAULT_INTEGER_REQUIRED_BOB))
            .andExpect(jsonPath("$.integerMinBob").value(DEFAULT_INTEGER_MIN_BOB))
            .andExpect(jsonPath("$.integerMaxBob").value(DEFAULT_INTEGER_MAX_BOB))
            .andExpect(jsonPath("$.longBob").value(DEFAULT_LONG_BOB.intValue()))
            .andExpect(jsonPath("$.longRequiredBob").value(DEFAULT_LONG_REQUIRED_BOB.intValue()))
            .andExpect(jsonPath("$.longMinBob").value(DEFAULT_LONG_MIN_BOB.intValue()))
            .andExpect(jsonPath("$.longMaxBob").value(DEFAULT_LONG_MAX_BOB.intValue()))
            .andExpect(jsonPath("$.floatBob").value(DEFAULT_FLOAT_BOB.doubleValue()))
            .andExpect(jsonPath("$.floatRequiredBob").value(DEFAULT_FLOAT_REQUIRED_BOB.doubleValue()))
            .andExpect(jsonPath("$.floatMinBob").value(DEFAULT_FLOAT_MIN_BOB.doubleValue()))
            .andExpect(jsonPath("$.floatMaxBob").value(DEFAULT_FLOAT_MAX_BOB.doubleValue()))
            .andExpect(jsonPath("$.doubleRequiredBob").value(DEFAULT_DOUBLE_REQUIRED_BOB.doubleValue()))
            .andExpect(jsonPath("$.doubleMinBob").value(DEFAULT_DOUBLE_MIN_BOB.doubleValue()))
            .andExpect(jsonPath("$.doubleMaxBob").value(DEFAULT_DOUBLE_MAX_BOB.doubleValue()))
            .andExpect(jsonPath("$.bigDecimalRequiredBob").value(sameNumber(DEFAULT_BIG_DECIMAL_REQUIRED_BOB)))
            .andExpect(jsonPath("$.bigDecimalMinBob").value(sameNumber(DEFAULT_BIG_DECIMAL_MIN_BOB)))
            .andExpect(jsonPath("$.bigDecimalMaxBob").value(sameNumber(DEFAULT_BIG_DECIMAL_MAX_BOB)))
            .andExpect(jsonPath("$.localDateBob").value(DEFAULT_LOCAL_DATE_BOB.toString()))
            .andExpect(jsonPath("$.localDateRequiredBob").value(DEFAULT_LOCAL_DATE_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.instantBob").value(DEFAULT_INSTANT_BOB.toString()))
            .andExpect(jsonPath("$.instanteRequiredBob").value(DEFAULT_INSTANTE_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.zonedDateTimeBob").value(sameInstant(DEFAULT_ZONED_DATE_TIME_BOB)))
            .andExpect(jsonPath("$.zonedDateTimeRequiredBob").value(sameInstant(DEFAULT_ZONED_DATE_TIME_REQUIRED_BOB)))
            .andExpect(jsonPath("$.durationBob").value(DEFAULT_DURATION_BOB.toString()))
            .andExpect(jsonPath("$.durationRequiredBob").value(DEFAULT_DURATION_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.booleanBob").value(DEFAULT_BOOLEAN_BOB.booleanValue()))
            .andExpect(jsonPath("$.booleanRequiredBob").value(DEFAULT_BOOLEAN_REQUIRED_BOB.booleanValue()))
            .andExpect(jsonPath("$.enumBob").value(DEFAULT_ENUM_BOB.toString()))
            .andExpect(jsonPath("$.enumRequiredBob").value(DEFAULT_ENUM_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.uuidBob").value(DEFAULT_UUID_BOB.toString()))
            .andExpect(jsonPath("$.uuidRequiredBob").value(DEFAULT_UUID_REQUIRED_BOB.toString()))
            .andExpect(jsonPath("$.byteImageBobContentType").value(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_BOB)))
            .andExpect(jsonPath("$.byteImageRequiredBobContentType").value(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageRequiredBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_REQUIRED_BOB)))
            .andExpect(jsonPath("$.byteImageMinbytesBobContentType").value(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMinbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MINBYTES_BOB)))
            .andExpect(jsonPath("$.byteImageMaxbytesBobContentType").value(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteImageMaxbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_IMAGE_MAXBYTES_BOB)))
            .andExpect(jsonPath("$.byteAnyBobContentType").value(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_BOB)))
            .andExpect(jsonPath("$.byteAnyRequiredBobContentType").value(DEFAULT_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyRequiredBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_REQUIRED_BOB)))
            .andExpect(jsonPath("$.byteAnyMinbytesBobContentType").value(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMinbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MINBYTES_BOB)))
            .andExpect(jsonPath("$.byteAnyMaxbytesBobContentType").value(DEFAULT_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.byteAnyMaxbytesBob").value(Base64Utils.encodeToString(DEFAULT_BYTE_ANY_MAXBYTES_BOB)))
            .andExpect(jsonPath("$.byteTextBob").value(DEFAULT_BYTE_TEXT_BOB.toString()))
            .andExpect(jsonPath("$.byteTextRequiredBob").value(DEFAULT_BYTE_TEXT_REQUIRED_BOB.toString()));
    }

    @Test
    void getNonExistingFieldTestServiceClassAndJpaFilteringEntity() throws Exception {
        // Get the fieldTestServiceClassAndJpaFilteringEntity
        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(get("/api/field-test-service-class-and-jpa-filtering-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateFieldTestServiceClassAndJpaFilteringEntity() throws Exception {
        // Initialize the database
        fieldTestServiceClassAndJpaFilteringEntityRepository.save(fieldTestServiceClassAndJpaFilteringEntity);

        int databaseSizeBeforeUpdate = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();

        // Update the fieldTestServiceClassAndJpaFilteringEntity
        FieldTestServiceClassAndJpaFilteringEntity updatedFieldTestServiceClassAndJpaFilteringEntity = fieldTestServiceClassAndJpaFilteringEntityRepository
            .findById(fieldTestServiceClassAndJpaFilteringEntity.getId())
            .get();
        updatedFieldTestServiceClassAndJpaFilteringEntity
            .stringBob(UPDATED_STRING_BOB)
            .stringRequiredBob(UPDATED_STRING_REQUIRED_BOB)
            .stringMinlengthBob(UPDATED_STRING_MINLENGTH_BOB)
            .stringMaxlengthBob(UPDATED_STRING_MAXLENGTH_BOB)
            .stringPatternBob(UPDATED_STRING_PATTERN_BOB)
            .integerBob(UPDATED_INTEGER_BOB)
            .integerRequiredBob(UPDATED_INTEGER_REQUIRED_BOB)
            .integerMinBob(UPDATED_INTEGER_MIN_BOB)
            .integerMaxBob(UPDATED_INTEGER_MAX_BOB)
            .longBob(UPDATED_LONG_BOB)
            .longRequiredBob(UPDATED_LONG_REQUIRED_BOB)
            .longMinBob(UPDATED_LONG_MIN_BOB)
            .longMaxBob(UPDATED_LONG_MAX_BOB)
            .floatBob(UPDATED_FLOAT_BOB)
            .floatRequiredBob(UPDATED_FLOAT_REQUIRED_BOB)
            .floatMinBob(UPDATED_FLOAT_MIN_BOB)
            .floatMaxBob(UPDATED_FLOAT_MAX_BOB)
            .doubleRequiredBob(UPDATED_DOUBLE_REQUIRED_BOB)
            .doubleMinBob(UPDATED_DOUBLE_MIN_BOB)
            .doubleMaxBob(UPDATED_DOUBLE_MAX_BOB)
            .bigDecimalRequiredBob(UPDATED_BIG_DECIMAL_REQUIRED_BOB)
            .bigDecimalMinBob(UPDATED_BIG_DECIMAL_MIN_BOB)
            .bigDecimalMaxBob(UPDATED_BIG_DECIMAL_MAX_BOB)
            .localDateBob(UPDATED_LOCAL_DATE_BOB)
            .localDateRequiredBob(UPDATED_LOCAL_DATE_REQUIRED_BOB)
            .instantBob(UPDATED_INSTANT_BOB)
            .instanteRequiredBob(UPDATED_INSTANTE_REQUIRED_BOB)
            .zonedDateTimeBob(UPDATED_ZONED_DATE_TIME_BOB)
            .zonedDateTimeRequiredBob(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB)
            .durationBob(UPDATED_DURATION_BOB)
            .durationRequiredBob(UPDATED_DURATION_REQUIRED_BOB)
            .booleanBob(UPDATED_BOOLEAN_BOB)
            .booleanRequiredBob(UPDATED_BOOLEAN_REQUIRED_BOB)
            .enumBob(UPDATED_ENUM_BOB)
            .enumRequiredBob(UPDATED_ENUM_REQUIRED_BOB)
            .uuidBob(UPDATED_UUID_BOB)
            .uuidRequiredBob(UPDATED_UUID_REQUIRED_BOB)
            .byteImageBob(UPDATED_BYTE_IMAGE_BOB)
            .byteImageBobContentType(UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE)
            .byteImageRequiredBob(UPDATED_BYTE_IMAGE_REQUIRED_BOB)
            .byteImageRequiredBobContentType(UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)
            .byteImageMinbytesBob(UPDATED_BYTE_IMAGE_MINBYTES_BOB)
            .byteImageMinbytesBobContentType(UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)
            .byteImageMaxbytesBob(UPDATED_BYTE_IMAGE_MAXBYTES_BOB)
            .byteImageMaxbytesBobContentType(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)
            .byteAnyBob(UPDATED_BYTE_ANY_BOB)
            .byteAnyBobContentType(UPDATED_BYTE_ANY_BOB_CONTENT_TYPE)
            .byteAnyRequiredBob(UPDATED_BYTE_ANY_REQUIRED_BOB)
            .byteAnyRequiredBobContentType(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)
            .byteAnyMinbytesBob(UPDATED_BYTE_ANY_MINBYTES_BOB)
            .byteAnyMinbytesBobContentType(UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)
            .byteAnyMaxbytesBob(UPDATED_BYTE_ANY_MAXBYTES_BOB)
            .byteAnyMaxbytesBobContentType(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)
            .byteTextBob(UPDATED_BYTE_TEXT_BOB)
            .byteTextRequiredBob(UPDATED_BYTE_TEXT_REQUIRED_BOB);

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                put("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isOk());

        // Validate the FieldTestServiceClassAndJpaFilteringEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestServiceClassAndJpaFilteringEntity testFieldTestServiceClassAndJpaFilteringEntity = fieldTestServiceClassAndJpaFilteringEntityList.get(
            fieldTestServiceClassAndJpaFilteringEntityList.size() - 1
        );
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringBob()).isEqualTo(UPDATED_STRING_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringRequiredBob()).isEqualTo(UPDATED_STRING_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMinlengthBob()).isEqualTo(UPDATED_STRING_MINLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMaxlengthBob()).isEqualTo(UPDATED_STRING_MAXLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringPatternBob()).isEqualTo(UPDATED_STRING_PATTERN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerBob()).isEqualTo(UPDATED_INTEGER_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerRequiredBob()).isEqualTo(UPDATED_INTEGER_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMinBob()).isEqualTo(UPDATED_INTEGER_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMaxBob()).isEqualTo(UPDATED_INTEGER_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongBob()).isEqualTo(UPDATED_LONG_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongRequiredBob()).isEqualTo(UPDATED_LONG_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMinBob()).isEqualTo(UPDATED_LONG_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMaxBob()).isEqualTo(UPDATED_LONG_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatBob()).isEqualTo(UPDATED_FLOAT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatRequiredBob()).isEqualTo(UPDATED_FLOAT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMinBob()).isEqualTo(UPDATED_FLOAT_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMaxBob()).isEqualTo(UPDATED_FLOAT_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleRequiredBob()).isEqualTo(UPDATED_DOUBLE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMinBob()).isEqualTo(UPDATED_DOUBLE_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMaxBob()).isEqualTo(UPDATED_DOUBLE_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalRequiredBob()).isEqualTo(UPDATED_BIG_DECIMAL_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMinBob()).isEqualTo(UPDATED_BIG_DECIMAL_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMaxBob()).isEqualTo(UPDATED_BIG_DECIMAL_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateBob()).isEqualTo(UPDATED_LOCAL_DATE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateRequiredBob()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstantBob()).isEqualTo(UPDATED_INSTANT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstanteRequiredBob()).isEqualTo(UPDATED_INSTANTE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeBob()).isEqualTo(UPDATED_ZONED_DATE_TIME_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeRequiredBob())
            .isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationBob()).isEqualTo(UPDATED_DURATION_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationRequiredBob()).isEqualTo(UPDATED_DURATION_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanBob()).isEqualTo(UPDATED_BOOLEAN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanRequiredBob()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumBob()).isEqualTo(UPDATED_ENUM_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumRequiredBob()).isEqualTo(UPDATED_ENUM_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidBob()).isEqualTo(UPDATED_UUID_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidRequiredBob()).isEqualTo(UPDATED_UUID_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBob()).isEqualTo(UPDATED_BYTE_IMAGE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBob()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBob()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBob()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBob()).isEqualTo(UPDATED_BYTE_ANY_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBobContentType()).isEqualTo(UPDATED_BYTE_ANY_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBob()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBob()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBob()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextBob()).isEqualTo(UPDATED_BYTE_TEXT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextRequiredBob()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_BOB);
    }

    @Test
    void updateNonExistingFieldTestServiceClassAndJpaFilteringEntity() throws Exception {
        int databaseSizeBeforeUpdate = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                put("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());

        // Validate the FieldTestServiceClassAndJpaFilteringEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFieldTestServiceClassAndJpaFilteringEntityWithPatch() throws Exception {
        // Initialize the database
        fieldTestServiceClassAndJpaFilteringEntityRepository.save(fieldTestServiceClassAndJpaFilteringEntity);

        int databaseSizeBeforeUpdate = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();

        // Update the fieldTestServiceClassAndJpaFilteringEntity using partial update
        FieldTestServiceClassAndJpaFilteringEntity partialUpdatedFieldTestServiceClassAndJpaFilteringEntity = new FieldTestServiceClassAndJpaFilteringEntity();
        partialUpdatedFieldTestServiceClassAndJpaFilteringEntity.setId(fieldTestServiceClassAndJpaFilteringEntity.getId());

        partialUpdatedFieldTestServiceClassAndJpaFilteringEntity
            .stringMaxlengthBob(UPDATED_STRING_MAXLENGTH_BOB)
            .stringPatternBob(UPDATED_STRING_PATTERN_BOB)
            .integerRequiredBob(UPDATED_INTEGER_REQUIRED_BOB)
            .integerMaxBob(UPDATED_INTEGER_MAX_BOB)
            .longBob(UPDATED_LONG_BOB)
            .longRequiredBob(UPDATED_LONG_REQUIRED_BOB)
            .longMinBob(UPDATED_LONG_MIN_BOB)
            .longMaxBob(UPDATED_LONG_MAX_BOB)
            .floatBob(UPDATED_FLOAT_BOB)
            .floatMinBob(UPDATED_FLOAT_MIN_BOB)
            .doubleMaxBob(UPDATED_DOUBLE_MAX_BOB)
            .bigDecimalRequiredBob(UPDATED_BIG_DECIMAL_REQUIRED_BOB)
            .bigDecimalMaxBob(UPDATED_BIG_DECIMAL_MAX_BOB)
            .localDateBob(UPDATED_LOCAL_DATE_BOB)
            .localDateRequiredBob(UPDATED_LOCAL_DATE_REQUIRED_BOB)
            .instantBob(UPDATED_INSTANT_BOB)
            .zonedDateTimeBob(UPDATED_ZONED_DATE_TIME_BOB)
            .zonedDateTimeRequiredBob(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB)
            .durationBob(UPDATED_DURATION_BOB)
            .durationRequiredBob(UPDATED_DURATION_REQUIRED_BOB)
            .booleanRequiredBob(UPDATED_BOOLEAN_REQUIRED_BOB)
            .enumRequiredBob(UPDATED_ENUM_REQUIRED_BOB)
            .uuidRequiredBob(UPDATED_UUID_REQUIRED_BOB)
            .byteImageMaxbytesBob(UPDATED_BYTE_IMAGE_MAXBYTES_BOB)
            .byteImageMaxbytesBobContentType(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)
            .byteAnyRequiredBob(UPDATED_BYTE_ANY_REQUIRED_BOB)
            .byteAnyRequiredBobContentType(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)
            .byteAnyMaxbytesBob(UPDATED_BYTE_ANY_MAXBYTES_BOB)
            .byteAnyMaxbytesBobContentType(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)
            .byteTextBob(UPDATED_BYTE_TEXT_BOB);

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                patch("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isOk());

        // Validate the FieldTestServiceClassAndJpaFilteringEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestServiceClassAndJpaFilteringEntity testFieldTestServiceClassAndJpaFilteringEntity = fieldTestServiceClassAndJpaFilteringEntityList.get(
            fieldTestServiceClassAndJpaFilteringEntityList.size() - 1
        );
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringBob()).isEqualTo(DEFAULT_STRING_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringRequiredBob()).isEqualTo(DEFAULT_STRING_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMinlengthBob()).isEqualTo(DEFAULT_STRING_MINLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMaxlengthBob()).isEqualTo(UPDATED_STRING_MAXLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringPatternBob()).isEqualTo(UPDATED_STRING_PATTERN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerBob()).isEqualTo(DEFAULT_INTEGER_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerRequiredBob()).isEqualTo(UPDATED_INTEGER_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMinBob()).isEqualTo(DEFAULT_INTEGER_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMaxBob()).isEqualTo(UPDATED_INTEGER_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongBob()).isEqualTo(UPDATED_LONG_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongRequiredBob()).isEqualTo(UPDATED_LONG_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMinBob()).isEqualTo(UPDATED_LONG_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMaxBob()).isEqualTo(UPDATED_LONG_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatBob()).isEqualTo(UPDATED_FLOAT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatRequiredBob()).isEqualTo(DEFAULT_FLOAT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMinBob()).isEqualTo(UPDATED_FLOAT_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMaxBob()).isEqualTo(DEFAULT_FLOAT_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleRequiredBob()).isEqualTo(DEFAULT_DOUBLE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMinBob()).isEqualTo(DEFAULT_DOUBLE_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMaxBob()).isEqualTo(UPDATED_DOUBLE_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalRequiredBob())
            .isEqualByComparingTo(UPDATED_BIG_DECIMAL_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMinBob()).isEqualByComparingTo(DEFAULT_BIG_DECIMAL_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMaxBob()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateBob()).isEqualTo(UPDATED_LOCAL_DATE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateRequiredBob()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstantBob()).isEqualTo(UPDATED_INSTANT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstanteRequiredBob()).isEqualTo(DEFAULT_INSTANTE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeBob()).isEqualTo(UPDATED_ZONED_DATE_TIME_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeRequiredBob())
            .isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationBob()).isEqualTo(UPDATED_DURATION_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationRequiredBob()).isEqualTo(UPDATED_DURATION_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanBob()).isEqualTo(DEFAULT_BOOLEAN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanRequiredBob()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumBob()).isEqualTo(DEFAULT_ENUM_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumRequiredBob()).isEqualTo(UPDATED_ENUM_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidBob()).isEqualTo(DEFAULT_UUID_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidRequiredBob()).isEqualTo(UPDATED_UUID_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBob()).isEqualTo(DEFAULT_BYTE_IMAGE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBobContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBob()).isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBobContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBob()).isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBobContentType())
            .isEqualTo(DEFAULT_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBob()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBob()).isEqualTo(DEFAULT_BYTE_ANY_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBobContentType()).isEqualTo(DEFAULT_BYTE_ANY_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBob()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBob()).isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBobContentType())
            .isEqualTo(DEFAULT_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBob()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextBob()).isEqualTo(UPDATED_BYTE_TEXT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextRequiredBob()).isEqualTo(DEFAULT_BYTE_TEXT_REQUIRED_BOB);
    }

    @Test
    void fullUpdateFieldTestServiceClassAndJpaFilteringEntityWithPatch() throws Exception {
        // Initialize the database
        fieldTestServiceClassAndJpaFilteringEntityRepository.save(fieldTestServiceClassAndJpaFilteringEntity);

        int databaseSizeBeforeUpdate = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();

        // Update the fieldTestServiceClassAndJpaFilteringEntity using partial update
        FieldTestServiceClassAndJpaFilteringEntity partialUpdatedFieldTestServiceClassAndJpaFilteringEntity = new FieldTestServiceClassAndJpaFilteringEntity();
        partialUpdatedFieldTestServiceClassAndJpaFilteringEntity.setId(fieldTestServiceClassAndJpaFilteringEntity.getId());

        partialUpdatedFieldTestServiceClassAndJpaFilteringEntity
            .stringBob(UPDATED_STRING_BOB)
            .stringRequiredBob(UPDATED_STRING_REQUIRED_BOB)
            .stringMinlengthBob(UPDATED_STRING_MINLENGTH_BOB)
            .stringMaxlengthBob(UPDATED_STRING_MAXLENGTH_BOB)
            .stringPatternBob(UPDATED_STRING_PATTERN_BOB)
            .integerBob(UPDATED_INTEGER_BOB)
            .integerRequiredBob(UPDATED_INTEGER_REQUIRED_BOB)
            .integerMinBob(UPDATED_INTEGER_MIN_BOB)
            .integerMaxBob(UPDATED_INTEGER_MAX_BOB)
            .longBob(UPDATED_LONG_BOB)
            .longRequiredBob(UPDATED_LONG_REQUIRED_BOB)
            .longMinBob(UPDATED_LONG_MIN_BOB)
            .longMaxBob(UPDATED_LONG_MAX_BOB)
            .floatBob(UPDATED_FLOAT_BOB)
            .floatRequiredBob(UPDATED_FLOAT_REQUIRED_BOB)
            .floatMinBob(UPDATED_FLOAT_MIN_BOB)
            .floatMaxBob(UPDATED_FLOAT_MAX_BOB)
            .doubleRequiredBob(UPDATED_DOUBLE_REQUIRED_BOB)
            .doubleMinBob(UPDATED_DOUBLE_MIN_BOB)
            .doubleMaxBob(UPDATED_DOUBLE_MAX_BOB)
            .bigDecimalRequiredBob(UPDATED_BIG_DECIMAL_REQUIRED_BOB)
            .bigDecimalMinBob(UPDATED_BIG_DECIMAL_MIN_BOB)
            .bigDecimalMaxBob(UPDATED_BIG_DECIMAL_MAX_BOB)
            .localDateBob(UPDATED_LOCAL_DATE_BOB)
            .localDateRequiredBob(UPDATED_LOCAL_DATE_REQUIRED_BOB)
            .instantBob(UPDATED_INSTANT_BOB)
            .instanteRequiredBob(UPDATED_INSTANTE_REQUIRED_BOB)
            .zonedDateTimeBob(UPDATED_ZONED_DATE_TIME_BOB)
            .zonedDateTimeRequiredBob(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB)
            .durationBob(UPDATED_DURATION_BOB)
            .durationRequiredBob(UPDATED_DURATION_REQUIRED_BOB)
            .booleanBob(UPDATED_BOOLEAN_BOB)
            .booleanRequiredBob(UPDATED_BOOLEAN_REQUIRED_BOB)
            .enumBob(UPDATED_ENUM_BOB)
            .enumRequiredBob(UPDATED_ENUM_REQUIRED_BOB)
            .uuidBob(UPDATED_UUID_BOB)
            .uuidRequiredBob(UPDATED_UUID_REQUIRED_BOB)
            .byteImageBob(UPDATED_BYTE_IMAGE_BOB)
            .byteImageBobContentType(UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE)
            .byteImageRequiredBob(UPDATED_BYTE_IMAGE_REQUIRED_BOB)
            .byteImageRequiredBobContentType(UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE)
            .byteImageMinbytesBob(UPDATED_BYTE_IMAGE_MINBYTES_BOB)
            .byteImageMinbytesBobContentType(UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE)
            .byteImageMaxbytesBob(UPDATED_BYTE_IMAGE_MAXBYTES_BOB)
            .byteImageMaxbytesBobContentType(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE)
            .byteAnyBob(UPDATED_BYTE_ANY_BOB)
            .byteAnyBobContentType(UPDATED_BYTE_ANY_BOB_CONTENT_TYPE)
            .byteAnyRequiredBob(UPDATED_BYTE_ANY_REQUIRED_BOB)
            .byteAnyRequiredBobContentType(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE)
            .byteAnyMinbytesBob(UPDATED_BYTE_ANY_MINBYTES_BOB)
            .byteAnyMinbytesBobContentType(UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE)
            .byteAnyMaxbytesBob(UPDATED_BYTE_ANY_MAXBYTES_BOB)
            .byteAnyMaxbytesBobContentType(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE)
            .byteTextBob(UPDATED_BYTE_TEXT_BOB)
            .byteTextRequiredBob(UPDATED_BYTE_TEXT_REQUIRED_BOB);

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                patch("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isOk());

        // Validate the FieldTestServiceClassAndJpaFilteringEntity in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeUpdate);
        FieldTestServiceClassAndJpaFilteringEntity testFieldTestServiceClassAndJpaFilteringEntity = fieldTestServiceClassAndJpaFilteringEntityList.get(
            fieldTestServiceClassAndJpaFilteringEntityList.size() - 1
        );
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringBob()).isEqualTo(UPDATED_STRING_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringRequiredBob()).isEqualTo(UPDATED_STRING_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMinlengthBob()).isEqualTo(UPDATED_STRING_MINLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringMaxlengthBob()).isEqualTo(UPDATED_STRING_MAXLENGTH_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getStringPatternBob()).isEqualTo(UPDATED_STRING_PATTERN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerBob()).isEqualTo(UPDATED_INTEGER_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerRequiredBob()).isEqualTo(UPDATED_INTEGER_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMinBob()).isEqualTo(UPDATED_INTEGER_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getIntegerMaxBob()).isEqualTo(UPDATED_INTEGER_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongBob()).isEqualTo(UPDATED_LONG_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongRequiredBob()).isEqualTo(UPDATED_LONG_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMinBob()).isEqualTo(UPDATED_LONG_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLongMaxBob()).isEqualTo(UPDATED_LONG_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatBob()).isEqualTo(UPDATED_FLOAT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatRequiredBob()).isEqualTo(UPDATED_FLOAT_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMinBob()).isEqualTo(UPDATED_FLOAT_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getFloatMaxBob()).isEqualTo(UPDATED_FLOAT_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleRequiredBob()).isEqualTo(UPDATED_DOUBLE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMinBob()).isEqualTo(UPDATED_DOUBLE_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDoubleMaxBob()).isEqualTo(UPDATED_DOUBLE_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalRequiredBob())
            .isEqualByComparingTo(UPDATED_BIG_DECIMAL_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMinBob()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_MIN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBigDecimalMaxBob()).isEqualByComparingTo(UPDATED_BIG_DECIMAL_MAX_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateBob()).isEqualTo(UPDATED_LOCAL_DATE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getLocalDateRequiredBob()).isEqualTo(UPDATED_LOCAL_DATE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstantBob()).isEqualTo(UPDATED_INSTANT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getInstanteRequiredBob()).isEqualTo(UPDATED_INSTANTE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeBob()).isEqualTo(UPDATED_ZONED_DATE_TIME_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getZonedDateTimeRequiredBob())
            .isEqualTo(UPDATED_ZONED_DATE_TIME_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationBob()).isEqualTo(UPDATED_DURATION_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getDurationRequiredBob()).isEqualTo(UPDATED_DURATION_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanBob()).isEqualTo(UPDATED_BOOLEAN_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getBooleanRequiredBob()).isEqualTo(UPDATED_BOOLEAN_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumBob()).isEqualTo(UPDATED_ENUM_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getEnumRequiredBob()).isEqualTo(UPDATED_ENUM_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidBob()).isEqualTo(UPDATED_UUID_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getUuidRequiredBob()).isEqualTo(UPDATED_UUID_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBob()).isEqualTo(UPDATED_BYTE_IMAGE_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBob()).isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageRequiredBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBob()).isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMinbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBob()).isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteImageMaxbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_IMAGE_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBob()).isEqualTo(UPDATED_BYTE_ANY_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyBobContentType()).isEqualTo(UPDATED_BYTE_ANY_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBob()).isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyRequiredBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_REQUIRED_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBob()).isEqualTo(UPDATED_BYTE_ANY_MINBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMinbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MINBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBob()).isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteAnyMaxbytesBobContentType())
            .isEqualTo(UPDATED_BYTE_ANY_MAXBYTES_BOB_CONTENT_TYPE);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextBob()).isEqualTo(UPDATED_BYTE_TEXT_BOB);
        assertThat(testFieldTestServiceClassAndJpaFilteringEntity.getByteTextRequiredBob()).isEqualTo(UPDATED_BYTE_TEXT_REQUIRED_BOB);
    }

    @Test
    void partialUpdateFieldTestServiceClassAndJpaFilteringEntityShouldThrown() throws Exception {
        // Update the fieldTestServiceClassAndJpaFilteringEntity without id should throw
        FieldTestServiceClassAndJpaFilteringEntity partialUpdatedFieldTestServiceClassAndJpaFilteringEntity = new FieldTestServiceClassAndJpaFilteringEntity();

        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                patch("/api/field-test-service-class-and-jpa-filtering-entities")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFieldTestServiceClassAndJpaFilteringEntity))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteFieldTestServiceClassAndJpaFilteringEntity() throws Exception {
        // Initialize the database
        fieldTestServiceClassAndJpaFilteringEntityRepository.save(fieldTestServiceClassAndJpaFilteringEntity);

        int databaseSizeBeforeDelete = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll().size();

        // Delete the fieldTestServiceClassAndJpaFilteringEntity
        restFieldTestServiceClassAndJpaFilteringEntityMockMvc
            .perform(
                delete("/api/field-test-service-class-and-jpa-filtering-entities/{id}", fieldTestServiceClassAndJpaFilteringEntity.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<FieldTestServiceClassAndJpaFilteringEntity> fieldTestServiceClassAndJpaFilteringEntityList = fieldTestServiceClassAndJpaFilteringEntityRepository.findAll();
        assertThat(fieldTestServiceClassAndJpaFilteringEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
