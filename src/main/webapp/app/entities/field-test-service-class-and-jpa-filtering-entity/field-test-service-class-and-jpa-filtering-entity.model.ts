import * as dayjs from 'dayjs';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';

export interface IFieldTestServiceClassAndJpaFilteringEntity {
  id?: string;
  stringBob?: string | null;
  stringRequiredBob?: string;
  stringMinlengthBob?: string | null;
  stringMaxlengthBob?: string | null;
  stringPatternBob?: string | null;
  integerBob?: number | null;
  integerRequiredBob?: number;
  integerMinBob?: number | null;
  integerMaxBob?: number | null;
  longBob?: number | null;
  longRequiredBob?: number;
  longMinBob?: number | null;
  longMaxBob?: number | null;
  floatBob?: number | null;
  floatRequiredBob?: number;
  floatMinBob?: number | null;
  floatMaxBob?: number | null;
  doubleRequiredBob?: number;
  doubleMinBob?: number | null;
  doubleMaxBob?: number | null;
  bigDecimalRequiredBob?: number;
  bigDecimalMinBob?: number | null;
  bigDecimalMaxBob?: number | null;
  localDateBob?: dayjs.Dayjs | null;
  localDateRequiredBob?: dayjs.Dayjs;
  instantBob?: dayjs.Dayjs | null;
  instanteRequiredBob?: dayjs.Dayjs;
  zonedDateTimeBob?: dayjs.Dayjs | null;
  zonedDateTimeRequiredBob?: dayjs.Dayjs;
  durationBob?: string | null;
  durationRequiredBob?: string;
  booleanBob?: boolean | null;
  booleanRequiredBob?: boolean;
  enumBob?: EnumFieldClass | null;
  enumRequiredBob?: EnumRequiredFieldClass;
  uuidBob?: string | null;
  uuidRequiredBob?: string;
  byteImageBobContentType?: string | null;
  byteImageBob?: string | null;
  byteImageRequiredBobContentType?: string;
  byteImageRequiredBob?: string;
  byteImageMinbytesBobContentType?: string | null;
  byteImageMinbytesBob?: string | null;
  byteImageMaxbytesBobContentType?: string | null;
  byteImageMaxbytesBob?: string | null;
  byteAnyBobContentType?: string | null;
  byteAnyBob?: string | null;
  byteAnyRequiredBobContentType?: string;
  byteAnyRequiredBob?: string;
  byteAnyMinbytesBobContentType?: string | null;
  byteAnyMinbytesBob?: string | null;
  byteAnyMaxbytesBobContentType?: string | null;
  byteAnyMaxbytesBob?: string | null;
  byteTextBob?: string | null;
  byteTextRequiredBob?: string;
}

export class FieldTestServiceClassAndJpaFilteringEntity implements IFieldTestServiceClassAndJpaFilteringEntity {
  constructor(
    public id?: string,
    public stringBob?: string | null,
    public stringRequiredBob?: string,
    public stringMinlengthBob?: string | null,
    public stringMaxlengthBob?: string | null,
    public stringPatternBob?: string | null,
    public integerBob?: number | null,
    public integerRequiredBob?: number,
    public integerMinBob?: number | null,
    public integerMaxBob?: number | null,
    public longBob?: number | null,
    public longRequiredBob?: number,
    public longMinBob?: number | null,
    public longMaxBob?: number | null,
    public floatBob?: number | null,
    public floatRequiredBob?: number,
    public floatMinBob?: number | null,
    public floatMaxBob?: number | null,
    public doubleRequiredBob?: number,
    public doubleMinBob?: number | null,
    public doubleMaxBob?: number | null,
    public bigDecimalRequiredBob?: number,
    public bigDecimalMinBob?: number | null,
    public bigDecimalMaxBob?: number | null,
    public localDateBob?: dayjs.Dayjs | null,
    public localDateRequiredBob?: dayjs.Dayjs,
    public instantBob?: dayjs.Dayjs | null,
    public instanteRequiredBob?: dayjs.Dayjs,
    public zonedDateTimeBob?: dayjs.Dayjs | null,
    public zonedDateTimeRequiredBob?: dayjs.Dayjs,
    public durationBob?: string | null,
    public durationRequiredBob?: string,
    public booleanBob?: boolean | null,
    public booleanRequiredBob?: boolean,
    public enumBob?: EnumFieldClass | null,
    public enumRequiredBob?: EnumRequiredFieldClass,
    public uuidBob?: string | null,
    public uuidRequiredBob?: string,
    public byteImageBobContentType?: string | null,
    public byteImageBob?: string | null,
    public byteImageRequiredBobContentType?: string,
    public byteImageRequiredBob?: string,
    public byteImageMinbytesBobContentType?: string | null,
    public byteImageMinbytesBob?: string | null,
    public byteImageMaxbytesBobContentType?: string | null,
    public byteImageMaxbytesBob?: string | null,
    public byteAnyBobContentType?: string | null,
    public byteAnyBob?: string | null,
    public byteAnyRequiredBobContentType?: string,
    public byteAnyRequiredBob?: string,
    public byteAnyMinbytesBobContentType?: string | null,
    public byteAnyMinbytesBob?: string | null,
    public byteAnyMaxbytesBobContentType?: string | null,
    public byteAnyMaxbytesBob?: string | null,
    public byteTextBob?: string | null,
    public byteTextRequiredBob?: string
  ) {
    this.booleanBob = this.booleanBob ?? false;
    this.booleanRequiredBob = this.booleanRequiredBob ?? false;
  }
}
