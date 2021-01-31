import * as dayjs from 'dayjs';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';

export interface IFieldTestInfiniteScrollEntity {
  id?: string;
  stringHugo?: string | null;
  stringRequiredHugo?: string;
  stringMinlengthHugo?: string | null;
  stringMaxlengthHugo?: string | null;
  stringPatternHugo?: string | null;
  integerHugo?: number | null;
  integerRequiredHugo?: number;
  integerMinHugo?: number | null;
  integerMaxHugo?: number | null;
  longHugo?: number | null;
  longRequiredHugo?: number;
  longMinHugo?: number | null;
  longMaxHugo?: number | null;
  floatHugo?: number | null;
  floatRequiredHugo?: number;
  floatMinHugo?: number | null;
  floatMaxHugo?: number | null;
  doubleRequiredHugo?: number;
  doubleMinHugo?: number | null;
  doubleMaxHugo?: number | null;
  bigDecimalRequiredHugo?: number;
  bigDecimalMinHugo?: number | null;
  bigDecimalMaxHugo?: number | null;
  localDateHugo?: dayjs.Dayjs | null;
  localDateRequiredHugo?: dayjs.Dayjs;
  instantHugo?: dayjs.Dayjs | null;
  instanteRequiredHugo?: dayjs.Dayjs;
  zonedDateTimeHugo?: dayjs.Dayjs | null;
  zonedDateTimeRequiredHugo?: dayjs.Dayjs;
  durationHugo?: string | null;
  durationRequiredHugo?: string;
  booleanHugo?: boolean | null;
  booleanRequiredHugo?: boolean;
  enumHugo?: EnumFieldClass | null;
  enumRequiredHugo?: EnumRequiredFieldClass;
  uuidHugo?: string | null;
  uuidRequiredHugo?: string;
  byteImageHugoContentType?: string | null;
  byteImageHugo?: string | null;
  byteImageRequiredHugoContentType?: string;
  byteImageRequiredHugo?: string;
  byteImageMinbytesHugoContentType?: string | null;
  byteImageMinbytesHugo?: string | null;
  byteImageMaxbytesHugoContentType?: string | null;
  byteImageMaxbytesHugo?: string | null;
  byteAnyHugoContentType?: string | null;
  byteAnyHugo?: string | null;
  byteAnyRequiredHugoContentType?: string;
  byteAnyRequiredHugo?: string;
  byteAnyMinbytesHugoContentType?: string | null;
  byteAnyMinbytesHugo?: string | null;
  byteAnyMaxbytesHugoContentType?: string | null;
  byteAnyMaxbytesHugo?: string | null;
  byteTextHugo?: string | null;
  byteTextRequiredHugo?: string;
}

export class FieldTestInfiniteScrollEntity implements IFieldTestInfiniteScrollEntity {
  constructor(
    public id?: string,
    public stringHugo?: string | null,
    public stringRequiredHugo?: string,
    public stringMinlengthHugo?: string | null,
    public stringMaxlengthHugo?: string | null,
    public stringPatternHugo?: string | null,
    public integerHugo?: number | null,
    public integerRequiredHugo?: number,
    public integerMinHugo?: number | null,
    public integerMaxHugo?: number | null,
    public longHugo?: number | null,
    public longRequiredHugo?: number,
    public longMinHugo?: number | null,
    public longMaxHugo?: number | null,
    public floatHugo?: number | null,
    public floatRequiredHugo?: number,
    public floatMinHugo?: number | null,
    public floatMaxHugo?: number | null,
    public doubleRequiredHugo?: number,
    public doubleMinHugo?: number | null,
    public doubleMaxHugo?: number | null,
    public bigDecimalRequiredHugo?: number,
    public bigDecimalMinHugo?: number | null,
    public bigDecimalMaxHugo?: number | null,
    public localDateHugo?: dayjs.Dayjs | null,
    public localDateRequiredHugo?: dayjs.Dayjs,
    public instantHugo?: dayjs.Dayjs | null,
    public instanteRequiredHugo?: dayjs.Dayjs,
    public zonedDateTimeHugo?: dayjs.Dayjs | null,
    public zonedDateTimeRequiredHugo?: dayjs.Dayjs,
    public durationHugo?: string | null,
    public durationRequiredHugo?: string,
    public booleanHugo?: boolean | null,
    public booleanRequiredHugo?: boolean,
    public enumHugo?: EnumFieldClass | null,
    public enumRequiredHugo?: EnumRequiredFieldClass,
    public uuidHugo?: string | null,
    public uuidRequiredHugo?: string,
    public byteImageHugoContentType?: string | null,
    public byteImageHugo?: string | null,
    public byteImageRequiredHugoContentType?: string,
    public byteImageRequiredHugo?: string,
    public byteImageMinbytesHugoContentType?: string | null,
    public byteImageMinbytesHugo?: string | null,
    public byteImageMaxbytesHugoContentType?: string | null,
    public byteImageMaxbytesHugo?: string | null,
    public byteAnyHugoContentType?: string | null,
    public byteAnyHugo?: string | null,
    public byteAnyRequiredHugoContentType?: string,
    public byteAnyRequiredHugo?: string,
    public byteAnyMinbytesHugoContentType?: string | null,
    public byteAnyMinbytesHugo?: string | null,
    public byteAnyMaxbytesHugoContentType?: string | null,
    public byteAnyMaxbytesHugo?: string | null,
    public byteTextHugo?: string | null,
    public byteTextRequiredHugo?: string
  ) {
    this.booleanHugo = this.booleanHugo ?? false;
    this.booleanRequiredHugo = this.booleanRequiredHugo ?? false;
  }
}
