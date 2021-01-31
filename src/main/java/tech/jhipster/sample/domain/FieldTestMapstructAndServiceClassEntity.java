package tech.jhipster.sample.domain;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;
import static tech.jhipster.sample.config.Constants.ID_DELIMITER;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import tech.jhipster.sample.domain.enumeration.EnumFieldClass;
import tech.jhipster.sample.domain.enumeration.EnumRequiredFieldClass;

/**
 * A FieldTestMapstructAndServiceClassEntity.
 */
@Document
public class FieldTestMapstructAndServiceClassEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX = "fieldtestmapstructandserviceclassentity";

    @SuppressWarnings("unused")
    @IdPrefix
    private String prefix = PREFIX;

    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String id;

    @Field("string_eva")
    private String stringEva;

    @NotNull
    @Field("string_required_eva")
    private String stringRequiredEva;

    @Size(min = 0)
    @Field("string_minlength_eva")
    private String stringMinlengthEva;

    @Size(max = 20)
    @Field("string_maxlength_eva")
    private String stringMaxlengthEva;

    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Field("string_pattern_eva")
    private String stringPatternEva;

    @Field("integer_eva")
    private Integer integerEva;

    @NotNull
    @Field("integer_required_eva")
    private Integer integerRequiredEva;

    @Min(value = 0)
    @Field("integer_min_eva")
    private Integer integerMinEva;

    @Max(value = 100)
    @Field("integer_max_eva")
    private Integer integerMaxEva;

    @Field("long_eva")
    private Long longEva;

    @NotNull
    @Field("long_required_eva")
    private Long longRequiredEva;

    @Min(value = 0L)
    @Field("long_min_eva")
    private Long longMinEva;

    @Max(value = 100L)
    @Field("long_max_eva")
    private Long longMaxEva;

    @Field("float_eva")
    private Float floatEva;

    @NotNull
    @Field("float_required_eva")
    private Float floatRequiredEva;

    @DecimalMin(value = "0")
    @Field("float_min_eva")
    private Float floatMinEva;

    @DecimalMax(value = "100")
    @Field("float_max_eva")
    private Float floatMaxEva;

    @NotNull
    @Field("double_required_eva")
    private Double doubleRequiredEva;

    @DecimalMin(value = "0")
    @Field("double_min_eva")
    private Double doubleMinEva;

    @DecimalMax(value = "100")
    @Field("double_max_eva")
    private Double doubleMaxEva;

    @NotNull
    @Field("big_decimal_required_eva")
    private BigDecimal bigDecimalRequiredEva;

    @DecimalMin(value = "0")
    @Field("big_decimal_min_eva")
    private BigDecimal bigDecimalMinEva;

    @DecimalMax(value = "100")
    @Field("big_decimal_max_eva")
    private BigDecimal bigDecimalMaxEva;

    @Field("local_date_eva")
    private LocalDate localDateEva;

    @NotNull
    @Field("local_date_required_eva")
    private LocalDate localDateRequiredEva;

    @Field("instant_eva")
    private Instant instantEva;

    @NotNull
    @Field("instante_required_eva")
    private Instant instanteRequiredEva;

    @Field("zoned_date_time_eva")
    private ZonedDateTime zonedDateTimeEva;

    @NotNull
    @Field("zoned_date_time_required_eva")
    private ZonedDateTime zonedDateTimeRequiredEva;

    @Field("duration_eva")
    private Duration durationEva;

    @NotNull
    @Field("duration_required_eva")
    private Duration durationRequiredEva;

    @Field("boolean_eva")
    private Boolean booleanEva;

    @NotNull
    @Field("boolean_required_eva")
    private Boolean booleanRequiredEva;

    @Field("enum_eva")
    private EnumFieldClass enumEva;

    @NotNull
    @Field("enum_required_eva")
    private EnumRequiredFieldClass enumRequiredEva;

    @Field("uuid_eva")
    private UUID uuidEva;

    @NotNull
    @Field("uuid_required_eva")
    private UUID uuidRequiredEva;

    @Field("byte_image_eva")
    private byte[] byteImageEva;

    @Field("byte_image_eva_content_type")
    private String byteImageEvaContentType;

    @Field("byte_image_required_eva")
    private byte[] byteImageRequiredEva;

    @Field("byte_image_required_eva_content_type")
    private String byteImageRequiredEvaContentType;

    @Field("byte_image_minbytes_eva")
    private byte[] byteImageMinbytesEva;

    @Field("byte_image_minbytes_eva_content_type")
    private String byteImageMinbytesEvaContentType;

    @Field("byte_image_maxbytes_eva")
    private byte[] byteImageMaxbytesEva;

    @Field("byte_image_maxbytes_eva_content_type")
    private String byteImageMaxbytesEvaContentType;

    @Field("byte_any_eva")
    private byte[] byteAnyEva;

    @Field("byte_any_eva_content_type")
    private String byteAnyEvaContentType;

    @Field("byte_any_required_eva")
    private byte[] byteAnyRequiredEva;

    @Field("byte_any_required_eva_content_type")
    private String byteAnyRequiredEvaContentType;

    @Field("byte_any_minbytes_eva")
    private byte[] byteAnyMinbytesEva;

    @Field("byte_any_minbytes_eva_content_type")
    private String byteAnyMinbytesEvaContentType;

    @Field("byte_any_maxbytes_eva")
    private byte[] byteAnyMaxbytesEva;

    @Field("byte_any_maxbytes_eva_content_type")
    private String byteAnyMaxbytesEvaContentType;

    @Field("byte_text_eva")
    private String byteTextEva;

    @Field("byte_text_required_eva")
    private String byteTextRequiredEva;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FieldTestMapstructAndServiceClassEntity id(String id) {
        this.id = id;
        return this;
    }

    public String getStringEva() {
        return this.stringEva;
    }

    public FieldTestMapstructAndServiceClassEntity stringEva(String stringEva) {
        this.stringEva = stringEva;
        return this;
    }

    public void setStringEva(String stringEva) {
        this.stringEva = stringEva;
    }

    public String getStringRequiredEva() {
        return this.stringRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity stringRequiredEva(String stringRequiredEva) {
        this.stringRequiredEva = stringRequiredEva;
        return this;
    }

    public void setStringRequiredEva(String stringRequiredEva) {
        this.stringRequiredEva = stringRequiredEva;
    }

    public String getStringMinlengthEva() {
        return this.stringMinlengthEva;
    }

    public FieldTestMapstructAndServiceClassEntity stringMinlengthEva(String stringMinlengthEva) {
        this.stringMinlengthEva = stringMinlengthEva;
        return this;
    }

    public void setStringMinlengthEva(String stringMinlengthEva) {
        this.stringMinlengthEva = stringMinlengthEva;
    }

    public String getStringMaxlengthEva() {
        return this.stringMaxlengthEva;
    }

    public FieldTestMapstructAndServiceClassEntity stringMaxlengthEva(String stringMaxlengthEva) {
        this.stringMaxlengthEva = stringMaxlengthEva;
        return this;
    }

    public void setStringMaxlengthEva(String stringMaxlengthEva) {
        this.stringMaxlengthEva = stringMaxlengthEva;
    }

    public String getStringPatternEva() {
        return this.stringPatternEva;
    }

    public FieldTestMapstructAndServiceClassEntity stringPatternEva(String stringPatternEva) {
        this.stringPatternEva = stringPatternEva;
        return this;
    }

    public void setStringPatternEva(String stringPatternEva) {
        this.stringPatternEva = stringPatternEva;
    }

    public Integer getIntegerEva() {
        return this.integerEva;
    }

    public FieldTestMapstructAndServiceClassEntity integerEva(Integer integerEva) {
        this.integerEva = integerEva;
        return this;
    }

    public void setIntegerEva(Integer integerEva) {
        this.integerEva = integerEva;
    }

    public Integer getIntegerRequiredEva() {
        return this.integerRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity integerRequiredEva(Integer integerRequiredEva) {
        this.integerRequiredEva = integerRequiredEva;
        return this;
    }

    public void setIntegerRequiredEva(Integer integerRequiredEva) {
        this.integerRequiredEva = integerRequiredEva;
    }

    public Integer getIntegerMinEva() {
        return this.integerMinEva;
    }

    public FieldTestMapstructAndServiceClassEntity integerMinEva(Integer integerMinEva) {
        this.integerMinEva = integerMinEva;
        return this;
    }

    public void setIntegerMinEva(Integer integerMinEva) {
        this.integerMinEva = integerMinEva;
    }

    public Integer getIntegerMaxEva() {
        return this.integerMaxEva;
    }

    public FieldTestMapstructAndServiceClassEntity integerMaxEva(Integer integerMaxEva) {
        this.integerMaxEva = integerMaxEva;
        return this;
    }

    public void setIntegerMaxEva(Integer integerMaxEva) {
        this.integerMaxEva = integerMaxEva;
    }

    public Long getLongEva() {
        return this.longEva;
    }

    public FieldTestMapstructAndServiceClassEntity longEva(Long longEva) {
        this.longEva = longEva;
        return this;
    }

    public void setLongEva(Long longEva) {
        this.longEva = longEva;
    }

    public Long getLongRequiredEva() {
        return this.longRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity longRequiredEva(Long longRequiredEva) {
        this.longRequiredEva = longRequiredEva;
        return this;
    }

    public void setLongRequiredEva(Long longRequiredEva) {
        this.longRequiredEva = longRequiredEva;
    }

    public Long getLongMinEva() {
        return this.longMinEva;
    }

    public FieldTestMapstructAndServiceClassEntity longMinEva(Long longMinEva) {
        this.longMinEva = longMinEva;
        return this;
    }

    public void setLongMinEva(Long longMinEva) {
        this.longMinEva = longMinEva;
    }

    public Long getLongMaxEva() {
        return this.longMaxEva;
    }

    public FieldTestMapstructAndServiceClassEntity longMaxEva(Long longMaxEva) {
        this.longMaxEva = longMaxEva;
        return this;
    }

    public void setLongMaxEva(Long longMaxEva) {
        this.longMaxEva = longMaxEva;
    }

    public Float getFloatEva() {
        return this.floatEva;
    }

    public FieldTestMapstructAndServiceClassEntity floatEva(Float floatEva) {
        this.floatEva = floatEva;
        return this;
    }

    public void setFloatEva(Float floatEva) {
        this.floatEva = floatEva;
    }

    public Float getFloatRequiredEva() {
        return this.floatRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity floatRequiredEva(Float floatRequiredEva) {
        this.floatRequiredEva = floatRequiredEva;
        return this;
    }

    public void setFloatRequiredEva(Float floatRequiredEva) {
        this.floatRequiredEva = floatRequiredEva;
    }

    public Float getFloatMinEva() {
        return this.floatMinEva;
    }

    public FieldTestMapstructAndServiceClassEntity floatMinEva(Float floatMinEva) {
        this.floatMinEva = floatMinEva;
        return this;
    }

    public void setFloatMinEva(Float floatMinEva) {
        this.floatMinEva = floatMinEva;
    }

    public Float getFloatMaxEva() {
        return this.floatMaxEva;
    }

    public FieldTestMapstructAndServiceClassEntity floatMaxEva(Float floatMaxEva) {
        this.floatMaxEva = floatMaxEva;
        return this;
    }

    public void setFloatMaxEva(Float floatMaxEva) {
        this.floatMaxEva = floatMaxEva;
    }

    public Double getDoubleRequiredEva() {
        return this.doubleRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity doubleRequiredEva(Double doubleRequiredEva) {
        this.doubleRequiredEva = doubleRequiredEva;
        return this;
    }

    public void setDoubleRequiredEva(Double doubleRequiredEva) {
        this.doubleRequiredEva = doubleRequiredEva;
    }

    public Double getDoubleMinEva() {
        return this.doubleMinEva;
    }

    public FieldTestMapstructAndServiceClassEntity doubleMinEva(Double doubleMinEva) {
        this.doubleMinEva = doubleMinEva;
        return this;
    }

    public void setDoubleMinEva(Double doubleMinEva) {
        this.doubleMinEva = doubleMinEva;
    }

    public Double getDoubleMaxEva() {
        return this.doubleMaxEva;
    }

    public FieldTestMapstructAndServiceClassEntity doubleMaxEva(Double doubleMaxEva) {
        this.doubleMaxEva = doubleMaxEva;
        return this;
    }

    public void setDoubleMaxEva(Double doubleMaxEva) {
        this.doubleMaxEva = doubleMaxEva;
    }

    public BigDecimal getBigDecimalRequiredEva() {
        return this.bigDecimalRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity bigDecimalRequiredEva(BigDecimal bigDecimalRequiredEva) {
        this.bigDecimalRequiredEva = bigDecimalRequiredEva;
        return this;
    }

    public void setBigDecimalRequiredEva(BigDecimal bigDecimalRequiredEva) {
        this.bigDecimalRequiredEva = bigDecimalRequiredEva;
    }

    public BigDecimal getBigDecimalMinEva() {
        return this.bigDecimalMinEva;
    }

    public FieldTestMapstructAndServiceClassEntity bigDecimalMinEva(BigDecimal bigDecimalMinEva) {
        this.bigDecimalMinEva = bigDecimalMinEva;
        return this;
    }

    public void setBigDecimalMinEva(BigDecimal bigDecimalMinEva) {
        this.bigDecimalMinEva = bigDecimalMinEva;
    }

    public BigDecimal getBigDecimalMaxEva() {
        return this.bigDecimalMaxEva;
    }

    public FieldTestMapstructAndServiceClassEntity bigDecimalMaxEva(BigDecimal bigDecimalMaxEva) {
        this.bigDecimalMaxEva = bigDecimalMaxEva;
        return this;
    }

    public void setBigDecimalMaxEva(BigDecimal bigDecimalMaxEva) {
        this.bigDecimalMaxEva = bigDecimalMaxEva;
    }

    public LocalDate getLocalDateEva() {
        return this.localDateEva;
    }

    public FieldTestMapstructAndServiceClassEntity localDateEva(LocalDate localDateEva) {
        this.localDateEva = localDateEva;
        return this;
    }

    public void setLocalDateEva(LocalDate localDateEva) {
        this.localDateEva = localDateEva;
    }

    public LocalDate getLocalDateRequiredEva() {
        return this.localDateRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity localDateRequiredEva(LocalDate localDateRequiredEva) {
        this.localDateRequiredEva = localDateRequiredEva;
        return this;
    }

    public void setLocalDateRequiredEva(LocalDate localDateRequiredEva) {
        this.localDateRequiredEva = localDateRequiredEva;
    }

    public Instant getInstantEva() {
        return this.instantEva;
    }

    public FieldTestMapstructAndServiceClassEntity instantEva(Instant instantEva) {
        this.instantEva = instantEva;
        return this;
    }

    public void setInstantEva(Instant instantEva) {
        this.instantEva = instantEva;
    }

    public Instant getInstanteRequiredEva() {
        return this.instanteRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity instanteRequiredEva(Instant instanteRequiredEva) {
        this.instanteRequiredEva = instanteRequiredEva;
        return this;
    }

    public void setInstanteRequiredEva(Instant instanteRequiredEva) {
        this.instanteRequiredEva = instanteRequiredEva;
    }

    public ZonedDateTime getZonedDateTimeEva() {
        return this.zonedDateTimeEva;
    }

    public FieldTestMapstructAndServiceClassEntity zonedDateTimeEva(ZonedDateTime zonedDateTimeEva) {
        this.zonedDateTimeEva = zonedDateTimeEva;
        return this;
    }

    public void setZonedDateTimeEva(ZonedDateTime zonedDateTimeEva) {
        this.zonedDateTimeEva = zonedDateTimeEva;
    }

    public ZonedDateTime getZonedDateTimeRequiredEva() {
        return this.zonedDateTimeRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity zonedDateTimeRequiredEva(ZonedDateTime zonedDateTimeRequiredEva) {
        this.zonedDateTimeRequiredEva = zonedDateTimeRequiredEva;
        return this;
    }

    public void setZonedDateTimeRequiredEva(ZonedDateTime zonedDateTimeRequiredEva) {
        this.zonedDateTimeRequiredEva = zonedDateTimeRequiredEva;
    }

    public Duration getDurationEva() {
        return this.durationEva;
    }

    public FieldTestMapstructAndServiceClassEntity durationEva(Duration durationEva) {
        this.durationEva = durationEva;
        return this;
    }

    public void setDurationEva(Duration durationEva) {
        this.durationEva = durationEva;
    }

    public Duration getDurationRequiredEva() {
        return this.durationRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity durationRequiredEva(Duration durationRequiredEva) {
        this.durationRequiredEva = durationRequiredEva;
        return this;
    }

    public void setDurationRequiredEva(Duration durationRequiredEva) {
        this.durationRequiredEva = durationRequiredEva;
    }

    public Boolean getBooleanEva() {
        return this.booleanEva;
    }

    public FieldTestMapstructAndServiceClassEntity booleanEva(Boolean booleanEva) {
        this.booleanEva = booleanEva;
        return this;
    }

    public void setBooleanEva(Boolean booleanEva) {
        this.booleanEva = booleanEva;
    }

    public Boolean getBooleanRequiredEva() {
        return this.booleanRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity booleanRequiredEva(Boolean booleanRequiredEva) {
        this.booleanRequiredEva = booleanRequiredEva;
        return this;
    }

    public void setBooleanRequiredEva(Boolean booleanRequiredEva) {
        this.booleanRequiredEva = booleanRequiredEva;
    }

    public EnumFieldClass getEnumEva() {
        return this.enumEva;
    }

    public FieldTestMapstructAndServiceClassEntity enumEva(EnumFieldClass enumEva) {
        this.enumEva = enumEva;
        return this;
    }

    public void setEnumEva(EnumFieldClass enumEva) {
        this.enumEva = enumEva;
    }

    public EnumRequiredFieldClass getEnumRequiredEva() {
        return this.enumRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity enumRequiredEva(EnumRequiredFieldClass enumRequiredEva) {
        this.enumRequiredEva = enumRequiredEva;
        return this;
    }

    public void setEnumRequiredEva(EnumRequiredFieldClass enumRequiredEva) {
        this.enumRequiredEva = enumRequiredEva;
    }

    public UUID getUuidEva() {
        return this.uuidEva;
    }

    public FieldTestMapstructAndServiceClassEntity uuidEva(UUID uuidEva) {
        this.uuidEva = uuidEva;
        return this;
    }

    public void setUuidEva(UUID uuidEva) {
        this.uuidEva = uuidEva;
    }

    public UUID getUuidRequiredEva() {
        return this.uuidRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity uuidRequiredEva(UUID uuidRequiredEva) {
        this.uuidRequiredEva = uuidRequiredEva;
        return this;
    }

    public void setUuidRequiredEva(UUID uuidRequiredEva) {
        this.uuidRequiredEva = uuidRequiredEva;
    }

    public byte[] getByteImageEva() {
        return this.byteImageEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageEva(byte[] byteImageEva) {
        this.byteImageEva = byteImageEva;
        return this;
    }

    public void setByteImageEva(byte[] byteImageEva) {
        this.byteImageEva = byteImageEva;
    }

    public String getByteImageEvaContentType() {
        return this.byteImageEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageEvaContentType(String byteImageEvaContentType) {
        this.byteImageEvaContentType = byteImageEvaContentType;
        return this;
    }

    public void setByteImageEvaContentType(String byteImageEvaContentType) {
        this.byteImageEvaContentType = byteImageEvaContentType;
    }

    public byte[] getByteImageRequiredEva() {
        return this.byteImageRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageRequiredEva(byte[] byteImageRequiredEva) {
        this.byteImageRequiredEva = byteImageRequiredEva;
        return this;
    }

    public void setByteImageRequiredEva(byte[] byteImageRequiredEva) {
        this.byteImageRequiredEva = byteImageRequiredEva;
    }

    public String getByteImageRequiredEvaContentType() {
        return this.byteImageRequiredEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageRequiredEvaContentType(String byteImageRequiredEvaContentType) {
        this.byteImageRequiredEvaContentType = byteImageRequiredEvaContentType;
        return this;
    }

    public void setByteImageRequiredEvaContentType(String byteImageRequiredEvaContentType) {
        this.byteImageRequiredEvaContentType = byteImageRequiredEvaContentType;
    }

    public byte[] getByteImageMinbytesEva() {
        return this.byteImageMinbytesEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageMinbytesEva(byte[] byteImageMinbytesEva) {
        this.byteImageMinbytesEva = byteImageMinbytesEva;
        return this;
    }

    public void setByteImageMinbytesEva(byte[] byteImageMinbytesEva) {
        this.byteImageMinbytesEva = byteImageMinbytesEva;
    }

    public String getByteImageMinbytesEvaContentType() {
        return this.byteImageMinbytesEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageMinbytesEvaContentType(String byteImageMinbytesEvaContentType) {
        this.byteImageMinbytesEvaContentType = byteImageMinbytesEvaContentType;
        return this;
    }

    public void setByteImageMinbytesEvaContentType(String byteImageMinbytesEvaContentType) {
        this.byteImageMinbytesEvaContentType = byteImageMinbytesEvaContentType;
    }

    public byte[] getByteImageMaxbytesEva() {
        return this.byteImageMaxbytesEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageMaxbytesEva(byte[] byteImageMaxbytesEva) {
        this.byteImageMaxbytesEva = byteImageMaxbytesEva;
        return this;
    }

    public void setByteImageMaxbytesEva(byte[] byteImageMaxbytesEva) {
        this.byteImageMaxbytesEva = byteImageMaxbytesEva;
    }

    public String getByteImageMaxbytesEvaContentType() {
        return this.byteImageMaxbytesEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteImageMaxbytesEvaContentType(String byteImageMaxbytesEvaContentType) {
        this.byteImageMaxbytesEvaContentType = byteImageMaxbytesEvaContentType;
        return this;
    }

    public void setByteImageMaxbytesEvaContentType(String byteImageMaxbytesEvaContentType) {
        this.byteImageMaxbytesEvaContentType = byteImageMaxbytesEvaContentType;
    }

    public byte[] getByteAnyEva() {
        return this.byteAnyEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyEva(byte[] byteAnyEva) {
        this.byteAnyEva = byteAnyEva;
        return this;
    }

    public void setByteAnyEva(byte[] byteAnyEva) {
        this.byteAnyEva = byteAnyEva;
    }

    public String getByteAnyEvaContentType() {
        return this.byteAnyEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyEvaContentType(String byteAnyEvaContentType) {
        this.byteAnyEvaContentType = byteAnyEvaContentType;
        return this;
    }

    public void setByteAnyEvaContentType(String byteAnyEvaContentType) {
        this.byteAnyEvaContentType = byteAnyEvaContentType;
    }

    public byte[] getByteAnyRequiredEva() {
        return this.byteAnyRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyRequiredEva(byte[] byteAnyRequiredEva) {
        this.byteAnyRequiredEva = byteAnyRequiredEva;
        return this;
    }

    public void setByteAnyRequiredEva(byte[] byteAnyRequiredEva) {
        this.byteAnyRequiredEva = byteAnyRequiredEva;
    }

    public String getByteAnyRequiredEvaContentType() {
        return this.byteAnyRequiredEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyRequiredEvaContentType(String byteAnyRequiredEvaContentType) {
        this.byteAnyRequiredEvaContentType = byteAnyRequiredEvaContentType;
        return this;
    }

    public void setByteAnyRequiredEvaContentType(String byteAnyRequiredEvaContentType) {
        this.byteAnyRequiredEvaContentType = byteAnyRequiredEvaContentType;
    }

    public byte[] getByteAnyMinbytesEva() {
        return this.byteAnyMinbytesEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyMinbytesEva(byte[] byteAnyMinbytesEva) {
        this.byteAnyMinbytesEva = byteAnyMinbytesEva;
        return this;
    }

    public void setByteAnyMinbytesEva(byte[] byteAnyMinbytesEva) {
        this.byteAnyMinbytesEva = byteAnyMinbytesEva;
    }

    public String getByteAnyMinbytesEvaContentType() {
        return this.byteAnyMinbytesEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyMinbytesEvaContentType(String byteAnyMinbytesEvaContentType) {
        this.byteAnyMinbytesEvaContentType = byteAnyMinbytesEvaContentType;
        return this;
    }

    public void setByteAnyMinbytesEvaContentType(String byteAnyMinbytesEvaContentType) {
        this.byteAnyMinbytesEvaContentType = byteAnyMinbytesEvaContentType;
    }

    public byte[] getByteAnyMaxbytesEva() {
        return this.byteAnyMaxbytesEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyMaxbytesEva(byte[] byteAnyMaxbytesEva) {
        this.byteAnyMaxbytesEva = byteAnyMaxbytesEva;
        return this;
    }

    public void setByteAnyMaxbytesEva(byte[] byteAnyMaxbytesEva) {
        this.byteAnyMaxbytesEva = byteAnyMaxbytesEva;
    }

    public String getByteAnyMaxbytesEvaContentType() {
        return this.byteAnyMaxbytesEvaContentType;
    }

    public FieldTestMapstructAndServiceClassEntity byteAnyMaxbytesEvaContentType(String byteAnyMaxbytesEvaContentType) {
        this.byteAnyMaxbytesEvaContentType = byteAnyMaxbytesEvaContentType;
        return this;
    }

    public void setByteAnyMaxbytesEvaContentType(String byteAnyMaxbytesEvaContentType) {
        this.byteAnyMaxbytesEvaContentType = byteAnyMaxbytesEvaContentType;
    }

    public String getByteTextEva() {
        return this.byteTextEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteTextEva(String byteTextEva) {
        this.byteTextEva = byteTextEva;
        return this;
    }

    public void setByteTextEva(String byteTextEva) {
        this.byteTextEva = byteTextEva;
    }

    public String getByteTextRequiredEva() {
        return this.byteTextRequiredEva;
    }

    public FieldTestMapstructAndServiceClassEntity byteTextRequiredEva(String byteTextRequiredEva) {
        this.byteTextRequiredEva = byteTextRequiredEva;
        return this;
    }

    public void setByteTextRequiredEva(String byteTextRequiredEva) {
        this.byteTextRequiredEva = byteTextRequiredEva;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldTestMapstructAndServiceClassEntity)) {
            return false;
        }
        return id != null && id.equals(((FieldTestMapstructAndServiceClassEntity) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FieldTestMapstructAndServiceClassEntity{" +
            "id=" + getId() +
            ", stringEva='" + getStringEva() + "'" +
            ", stringRequiredEva='" + getStringRequiredEva() + "'" +
            ", stringMinlengthEva='" + getStringMinlengthEva() + "'" +
            ", stringMaxlengthEva='" + getStringMaxlengthEva() + "'" +
            ", stringPatternEva='" + getStringPatternEva() + "'" +
            ", integerEva=" + getIntegerEva() +
            ", integerRequiredEva=" + getIntegerRequiredEva() +
            ", integerMinEva=" + getIntegerMinEva() +
            ", integerMaxEva=" + getIntegerMaxEva() +
            ", longEva=" + getLongEva() +
            ", longRequiredEva=" + getLongRequiredEva() +
            ", longMinEva=" + getLongMinEva() +
            ", longMaxEva=" + getLongMaxEva() +
            ", floatEva=" + getFloatEva() +
            ", floatRequiredEva=" + getFloatRequiredEva() +
            ", floatMinEva=" + getFloatMinEva() +
            ", floatMaxEva=" + getFloatMaxEva() +
            ", doubleRequiredEva=" + getDoubleRequiredEva() +
            ", doubleMinEva=" + getDoubleMinEva() +
            ", doubleMaxEva=" + getDoubleMaxEva() +
            ", bigDecimalRequiredEva=" + getBigDecimalRequiredEva() +
            ", bigDecimalMinEva=" + getBigDecimalMinEva() +
            ", bigDecimalMaxEva=" + getBigDecimalMaxEva() +
            ", localDateEva='" + getLocalDateEva() + "'" +
            ", localDateRequiredEva='" + getLocalDateRequiredEva() + "'" +
            ", instantEva='" + getInstantEva() + "'" +
            ", instanteRequiredEva='" + getInstanteRequiredEva() + "'" +
            ", zonedDateTimeEva='" + getZonedDateTimeEva() + "'" +
            ", zonedDateTimeRequiredEva='" + getZonedDateTimeRequiredEva() + "'" +
            ", durationEva='" + getDurationEva() + "'" +
            ", durationRequiredEva='" + getDurationRequiredEva() + "'" +
            ", booleanEva='" + getBooleanEva() + "'" +
            ", booleanRequiredEva='" + getBooleanRequiredEva() + "'" +
            ", enumEva='" + getEnumEva() + "'" +
            ", enumRequiredEva='" + getEnumRequiredEva() + "'" +
            ", uuidEva='" + getUuidEva() + "'" +
            ", uuidRequiredEva='" + getUuidRequiredEva() + "'" +
            ", byteImageEva='" + getByteImageEva() + "'" +
            ", byteImageEvaContentType='" + getByteImageEvaContentType() + "'" +
            ", byteImageRequiredEva='" + getByteImageRequiredEva() + "'" +
            ", byteImageRequiredEvaContentType='" + getByteImageRequiredEvaContentType() + "'" +
            ", byteImageMinbytesEva='" + getByteImageMinbytesEva() + "'" +
            ", byteImageMinbytesEvaContentType='" + getByteImageMinbytesEvaContentType() + "'" +
            ", byteImageMaxbytesEva='" + getByteImageMaxbytesEva() + "'" +
            ", byteImageMaxbytesEvaContentType='" + getByteImageMaxbytesEvaContentType() + "'" +
            ", byteAnyEva='" + getByteAnyEva() + "'" +
            ", byteAnyEvaContentType='" + getByteAnyEvaContentType() + "'" +
            ", byteAnyRequiredEva='" + getByteAnyRequiredEva() + "'" +
            ", byteAnyRequiredEvaContentType='" + getByteAnyRequiredEvaContentType() + "'" +
            ", byteAnyMinbytesEva='" + getByteAnyMinbytesEva() + "'" +
            ", byteAnyMinbytesEvaContentType='" + getByteAnyMinbytesEvaContentType() + "'" +
            ", byteAnyMaxbytesEva='" + getByteAnyMaxbytesEva() + "'" +
            ", byteAnyMaxbytesEvaContentType='" + getByteAnyMaxbytesEvaContentType() + "'" +
            ", byteTextEva='" + getByteTextEva() + "'" +
            ", byteTextRequiredEva='" + getByteTextRequiredEva() + "'" +
            "}";
    }
}
