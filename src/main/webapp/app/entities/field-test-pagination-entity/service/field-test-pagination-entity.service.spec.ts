import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';
import { IFieldTestPaginationEntity, FieldTestPaginationEntity } from '../field-test-pagination-entity.model';

import { FieldTestPaginationEntityService } from './field-test-pagination-entity.service';

describe('Service Tests', () => {
  describe('FieldTestPaginationEntity Service', () => {
    let service: FieldTestPaginationEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IFieldTestPaginationEntity;
    let expectedResult: IFieldTestPaginationEntity | IFieldTestPaginationEntity[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(FieldTestPaginationEntityService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = new FieldTestPaginationEntity(
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
            localDateAlice: currentDate.format(DATE_FORMAT),
            localDateRequiredAlice: currentDate.format(DATE_FORMAT),
            instantAlice: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FieldTestPaginationEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            localDateAlice: currentDate.format(DATE_FORMAT),
            localDateRequiredAlice: currentDate.format(DATE_FORMAT),
            instantAlice: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateAlice: currentDate,
            localDateRequiredAlice: currentDate,
            instantAlice: currentDate,
            instanteRequiredAlice: currentDate,
            zonedDateTimeAlice: currentDate,
            zonedDateTimeRequiredAlice: currentDate,
          },
          returnedFromService
        );

        service.create(new FieldTestPaginationEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FieldTestPaginationEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringAlice: 'BBBBBB',
            stringRequiredAlice: 'BBBBBB',
            stringMinlengthAlice: 'BBBBBB',
            stringMaxlengthAlice: 'BBBBBB',
            stringPatternAlice: 'BBBBBB',
            integerAlice: 1,
            integerRequiredAlice: 1,
            integerMinAlice: 1,
            integerMaxAlice: 1,
            longAlice: 1,
            longRequiredAlice: 1,
            longMinAlice: 1,
            longMaxAlice: 1,
            floatAlice: 1,
            floatRequiredAlice: 1,
            floatMinAlice: 1,
            floatMaxAlice: 1,
            doubleRequiredAlice: 1,
            doubleMinAlice: 1,
            doubleMaxAlice: 1,
            bigDecimalRequiredAlice: 1,
            bigDecimalMinAlice: 1,
            bigDecimalMaxAlice: 1,
            localDateAlice: currentDate.format(DATE_FORMAT),
            localDateRequiredAlice: currentDate.format(DATE_FORMAT),
            instantAlice: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
            durationAlice: 'BBBBBB',
            durationRequiredAlice: 'BBBBBB',
            booleanAlice: true,
            booleanRequiredAlice: true,
            enumAlice: 'BBBBBB',
            enumRequiredAlice: 'BBBBBB',
            uuidAlice: 'BBBBBB',
            uuidRequiredAlice: 'BBBBBB',
            byteImageAlice: 'BBBBBB',
            byteImageRequiredAlice: 'BBBBBB',
            byteImageMinbytesAlice: 'BBBBBB',
            byteImageMaxbytesAlice: 'BBBBBB',
            byteAnyAlice: 'BBBBBB',
            byteAnyRequiredAlice: 'BBBBBB',
            byteAnyMinbytesAlice: 'BBBBBB',
            byteAnyMaxbytesAlice: 'BBBBBB',
            byteTextAlice: 'BBBBBB',
            byteTextRequiredAlice: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateAlice: currentDate,
            localDateRequiredAlice: currentDate,
            instantAlice: currentDate,
            instanteRequiredAlice: currentDate,
            zonedDateTimeAlice: currentDate,
            zonedDateTimeRequiredAlice: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FieldTestPaginationEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringAlice: 'BBBBBB',
            stringRequiredAlice: 'BBBBBB',
            stringMinlengthAlice: 'BBBBBB',
            stringMaxlengthAlice: 'BBBBBB',
            stringPatternAlice: 'BBBBBB',
            integerAlice: 1,
            integerRequiredAlice: 1,
            integerMinAlice: 1,
            integerMaxAlice: 1,
            longAlice: 1,
            longRequiredAlice: 1,
            longMinAlice: 1,
            longMaxAlice: 1,
            floatAlice: 1,
            floatRequiredAlice: 1,
            floatMinAlice: 1,
            floatMaxAlice: 1,
            doubleRequiredAlice: 1,
            doubleMinAlice: 1,
            doubleMaxAlice: 1,
            bigDecimalRequiredAlice: 1,
            bigDecimalMinAlice: 1,
            bigDecimalMaxAlice: 1,
            localDateAlice: currentDate.format(DATE_FORMAT),
            localDateRequiredAlice: currentDate.format(DATE_FORMAT),
            instantAlice: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeAlice: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredAlice: currentDate.format(DATE_TIME_FORMAT),
            durationAlice: 'BBBBBB',
            durationRequiredAlice: 'BBBBBB',
            booleanAlice: true,
            booleanRequiredAlice: true,
            enumAlice: 'BBBBBB',
            enumRequiredAlice: 'BBBBBB',
            uuidAlice: 'BBBBBB',
            uuidRequiredAlice: 'BBBBBB',
            byteImageAlice: 'BBBBBB',
            byteImageRequiredAlice: 'BBBBBB',
            byteImageMinbytesAlice: 'BBBBBB',
            byteImageMaxbytesAlice: 'BBBBBB',
            byteAnyAlice: 'BBBBBB',
            byteAnyRequiredAlice: 'BBBBBB',
            byteAnyMinbytesAlice: 'BBBBBB',
            byteAnyMaxbytesAlice: 'BBBBBB',
            byteTextAlice: 'BBBBBB',
            byteTextRequiredAlice: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateAlice: currentDate,
            localDateRequiredAlice: currentDate,
            instantAlice: currentDate,
            instanteRequiredAlice: currentDate,
            zonedDateTimeAlice: currentDate,
            zonedDateTimeRequiredAlice: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FieldTestPaginationEntity', () => {
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
