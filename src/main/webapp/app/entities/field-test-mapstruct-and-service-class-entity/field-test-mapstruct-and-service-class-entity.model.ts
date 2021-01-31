import * as dayjs from 'dayjs';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';

export interface IFieldTestMapstructAndServiceClassEntity {
  id?: string;
  stringEva?: string | null;
  stringRequiredEva?: string;
  stringMinlengthEva?: string | null;
  stringMaxlengthEva?: string | null;
  stringPatternEva?: string | null;
  integerEva?: number | null;
  integerRequiredEva?: number;
  integerMinEva?: number | null;
  integerMaxEva?: number | null;
  longEva?: number | null;
  longRequiredEva?: number;
  longMinEva?: number | null;
  longMaxEva?: number | null;
  floatEva?: number | null;
  floatRequiredEva?: number;
  floatMinEva?: number | null;
  floatMaxEva?: number | null;
  doubleRequiredEva?: number;
  doubleMinEva?: number | null;
  doubleMaxEva?: number | null;
  bigDecimalRequiredEva?: number;
  bigDecimalMinEva?: number | null;
  bigDecimalMaxEva?: number | null;
  localDateEva?: dayjs.Dayjs | null;
  localDateRequiredEva?: dayjs.Dayjs;
  instantEva?: dayjs.Dayjs | null;
  instanteRequiredEva?: dayjs.Dayjs;
  zonedDateTimeEva?: dayjs.Dayjs | null;
  zonedDateTimeRequiredEva?: dayjs.Dayjs;
  durationEva?: string | null;
  durationRequiredEva?: string;
  booleanEva?: boolean | null;
  booleanRequiredEva?: boolean;
  enumEva?: EnumFieldClass | null;
  enumRequiredEva?: EnumRequiredFieldClass;
  uuidEva?: string | null;
  uuidRequiredEva?: string;
  byteImageEvaContentType?: string | null;
  byteImageEva?: string | null;
  byteImageRequiredEvaContentType?: string;
  byteImageRequiredEva?: string;
  byteImageMinbytesEvaContentType?: string | null;
  byteImageMinbytesEva?: string | null;
  byteImageMaxbytesEvaContentType?: string | null;
  byteImageMaxbytesEva?: string | null;
  byteAnyEvaContentType?: string | null;
  byteAnyEva?: string | null;
  byteAnyRequiredEvaContentType?: string;
  byteAnyRequiredEva?: string;
  byteAnyMinbytesEvaContentType?: string | null;
  byteAnyMinbytesEva?: string | null;
  byteAnyMaxbytesEvaContentType?: string | null;
  byteAnyMaxbytesEva?: string | null;
  byteTextEva?: string | null;
  byteTextRequiredEva?: string;
}

export class FieldTestMapstructAndServiceClassEntity implements IFieldTestMapstructAndServiceClassEntity {
  constructor(
    public id?: string,
    public stringEva?: string | null,
    public stringRequiredEva?: string,
    public stringMinlengthEva?: string | null,
    public stringMaxlengthEva?: string | null,
    public stringPatternEva?: string | null,
    public integerEva?: number | null,
    public integerRequiredEva?: number,
    public integerMinEva?: number | null,
    public integerMaxEva?: number | null,
    public longEva?: number | null,
    public longRequiredEva?: number,
    public longMinEva?: number | null,
    public longMaxEva?: number | null,
    public floatEva?: number | null,
    public floatRequiredEva?: number,
    public floatMinEva?: number | null,
    public floatMaxEva?: number | null,
    public doubleRequiredEva?: number,
    public doubleMinEva?: number | null,
    public doubleMaxEva?: number | null,
    public bigDecimalRequiredEva?: number,
    public bigDecimalMinEva?: number | null,
    public bigDecimalMaxEva?: number | null,
    public localDateEva?: dayjs.Dayjs | null,
    public localDateRequiredEva?: dayjs.Dayjs,
    public instantEva?: dayjs.Dayjs | null,
    public instanteRequiredEva?: dayjs.Dayjs,
    public zonedDateTimeEva?: dayjs.Dayjs | null,
    public zonedDateTimeRequiredEva?: dayjs.Dayjs,
    public durationEva?: string | null,
    public durationRequiredEva?: string,
    public booleanEva?: boolean | null,
    public booleanRequiredEva?: boolean,
    public enumEva?: EnumFieldClass | null,
    public enumRequiredEva?: EnumRequiredFieldClass,
    public uuidEva?: string | null,
    public uuidRequiredEva?: string,
    public byteImageEvaContentType?: string | null,
    public byteImageEva?: string | null,
    public byteImageRequiredEvaContentType?: string,
    public byteImageRequiredEva?: string,
    public byteImageMinbytesEvaContentType?: string | null,
    public byteImageMinbytesEva?: string | null,
    public byteImageMaxbytesEvaContentType?: string | null,
    public byteImageMaxbytesEva?: string | null,
    public byteAnyEvaContentType?: string | null,
    public byteAnyEva?: string | null,
    public byteAnyRequiredEvaContentType?: string,
    public byteAnyRequiredEva?: string,
    public byteAnyMinbytesEvaContentType?: string | null,
    public byteAnyMinbytesEva?: string | null,
    public byteAnyMaxbytesEvaContentType?: string | null,
    public byteAnyMaxbytesEva?: string | null,
    public byteTextEva?: string | null,
    public byteTextRequiredEva?: string
  ) {
    this.booleanEva = this.booleanEva ?? false;
    this.booleanRequiredEva = this.booleanRequiredEva ?? false;
  }
}
