import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';
import {
  IFieldTestMapstructAndServiceClassEntity,
  FieldTestMapstructAndServiceClassEntity,
} from '../field-test-mapstruct-and-service-class-entity.model';

import { FieldTestMapstructAndServiceClassEntityService } from './field-test-mapstruct-and-service-class-entity.service';

describe('Service Tests', () => {
  describe('FieldTestMapstructAndServiceClassEntity Service', () => {
    let service: FieldTestMapstructAndServiceClassEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IFieldTestMapstructAndServiceClassEntity;
    let expectedResult: IFieldTestMapstructAndServiceClassEntity | IFieldTestMapstructAndServiceClassEntity[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(FieldTestMapstructAndServiceClassEntityService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = new FieldTestMapstructAndServiceClassEntity(
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        'PT1S',
        'PT1S',
        false,
        false,
        EnumFieldClass.ENUM_VALUE_1,
        EnumRequiredFieldClass.ENUM_VALUE_1,
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            localDateEva: currentDate.format(DATE_FORMAT),
            localDateRequiredEva: currentDate.format(DATE_FORMAT),
            instantEva: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredEva: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FieldTestMapstructAndServiceClassEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            localDateEva: currentDate.format(DATE_FORMAT),
            localDateRequiredEva: currentDate.format(DATE_FORMAT),
            instantEva: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredEva: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateEva: currentDate,
            localDateRequiredEva: currentDate,
            instantEva: currentDate,
            instanteRequiredEva: currentDate,
            zonedDateTimeEva: currentDate,
            zonedDateTimeRequiredEva: currentDate,
          },
          returnedFromService
        );

        service.create(new FieldTestMapstructAndServiceClassEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FieldTestMapstructAndServiceClassEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringEva: 'BBBBBB',
            stringRequiredEva: 'BBBBBB',
            stringMinlengthEva: 'BBBBBB',
            stringMaxlengthEva: 'BBBBBB',
            stringPatternEva: 'BBBBBB',
            integerEva: 1,
            integerRequiredEva: 1,
            integerMinEva: 1,
            integerMaxEva: 1,
            longEva: 1,
            longRequiredEva: 1,
            longMinEva: 1,
            longMaxEva: 1,
            floatEva: 1,
            floatRequiredEva: 1,
            floatMinEva: 1,
            floatMaxEva: 1,
            doubleRequiredEva: 1,
            doubleMinEva: 1,
            doubleMaxEva: 1,
            bigDecimalRequiredEva: 1,
            bigDecimalMinEva: 1,
            bigDecimalMaxEva: 1,
            localDateEva: currentDate.format(DATE_FORMAT),
            localDateRequiredEva: currentDate.format(DATE_FORMAT),
            instantEva: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredEva: currentDate.format(DATE_TIME_FORMAT),
            durationEva: 'BBBBBB',
            durationRequiredEva: 'BBBBBB',
            booleanEva: true,
            booleanRequiredEva: true,
            enumEva: 'BBBBBB',
            enumRequiredEva: 'BBBBBB',
            uuidEva: 'BBBBBB',
            uuidRequiredEva: 'BBBBBB',
            byteImageEva: 'BBBBBB',
            byteImageRequiredEva: 'BBBBBB',
            byteImageMinbytesEva: 'BBBBBB',
            byteImageMaxbytesEva: 'BBBBBB',
            byteAnyEva: 'BBBBBB',
            byteAnyRequiredEva: 'BBBBBB',
            byteAnyMinbytesEva: 'BBBBBB',
            byteAnyMaxbytesEva: 'BBBBBB',
            byteTextEva: 'BBBBBB',
            byteTextRequiredEva: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateEva: currentDate,
            localDateRequiredEva: currentDate,
            instantEva: currentDate,
            instanteRequiredEva: currentDate,
            zonedDateTimeEva: currentDate,
            zonedDateTimeRequiredEva: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FieldTestMapstructAndServiceClassEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringEva: 'BBBBBB',
            stringRequiredEva: 'BBBBBB',
            stringMinlengthEva: 'BBBBBB',
            stringMaxlengthEva: 'BBBBBB',
            stringPatternEva: 'BBBBBB',
            integerEva: 1,
            integerRequiredEva: 1,
            integerMinEva: 1,
            integerMaxEva: 1,
            longEva: 1,
            longRequiredEva: 1,
            longMinEva: 1,
            longMaxEva: 1,
            floatEva: 1,
            floatRequiredEva: 1,
            floatMinEva: 1,
            floatMaxEva: 1,
            doubleRequiredEva: 1,
            doubleMinEva: 1,
            doubleMaxEva: 1,
            bigDecimalRequiredEva: 1,
            bigDecimalMinEva: 1,
            bigDecimalMaxEva: 1,
            localDateEva: currentDate.format(DATE_FORMAT),
            localDateRequiredEva: currentDate.format(DATE_FORMAT),
            instantEva: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeEva: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredEva: currentDate.format(DATE_TIME_FORMAT),
            durationEva: 'BBBBBB',
            durationRequiredEva: 'BBBBBB',
            booleanEva: true,
            booleanRequiredEva: true,
            enumEva: 'BBBBBB',
            enumRequiredEva: 'BBBBBB',
            uuidEva: 'BBBBBB',
            uuidRequiredEva: 'BBBBBB',
            byteImageEva: 'BBBBBB',
            byteImageRequiredEva: 'BBBBBB',
            byteImageMinbytesEva: 'BBBBBB',
            byteImageMaxbytesEva: 'BBBBBB',
            byteAnyEva: 'BBBBBB',
            byteAnyRequiredEva: 'BBBBBB',
            byteAnyMinbytesEva: 'BBBBBB',
            byteAnyMaxbytesEva: 'BBBBBB',
            byteTextEva: 'BBBBBB',
            byteTextRequiredEva: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateEva: currentDate,
            localDateRequiredEva: currentDate,
            instantEva: currentDate,
            instanteRequiredEva: currentDate,
            zonedDateTimeEva: currentDate,
            zonedDateTimeRequiredEva: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FieldTestMapstructAndServiceClassEntity', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
