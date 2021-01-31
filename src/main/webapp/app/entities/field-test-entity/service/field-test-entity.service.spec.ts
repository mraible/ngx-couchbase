import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';
import { IFieldTestEntity, FieldTestEntity } from '../field-test-entity.model';

import { FieldTestEntityService } from './field-test-entity.service';

describe('Service Tests', () => {
  describe('FieldTestEntity Service', () => {
    let service: FieldTestEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IFieldTestEntity;
    let expectedResult: IFieldTestEntity | IFieldTestEntity[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(FieldTestEntityService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = new FieldTestEntity(
        'AAAAAAA',
        'AAAAAAA',
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
            localDateTom: currentDate.format(DATE_FORMAT),
            localDateRequiredTom: currentDate.format(DATE_FORMAT),
            instantTom: currentDate.format(DATE_TIME_FORMAT),
            instantRequiredTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredTom: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FieldTestEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            localDateTom: currentDate.format(DATE_FORMAT),
            localDateRequiredTom: currentDate.format(DATE_FORMAT),
            instantTom: currentDate.format(DATE_TIME_FORMAT),
            instantRequiredTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredTom: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateTom: currentDate,
            localDateRequiredTom: currentDate,
            instantTom: currentDate,
            instantRequiredTom: currentDate,
            zonedDateTimeTom: currentDate,
            zonedDateTimeRequiredTom: currentDate,
          },
          returnedFromService
        );

        service.create(new FieldTestEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FieldTestEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringTom: 'BBBBBB',
            stringRequiredTom: 'BBBBBB',
            stringMinlengthTom: 'BBBBBB',
            stringMaxlengthTom: 'BBBBBB',
            stringPatternTom: 'BBBBBB',
            numberPatternTom: 'BBBBBB',
            numberPatternRequiredTom: 'BBBBBB',
            integerTom: 1,
            integerRequiredTom: 1,
            integerMinTom: 1,
            integerMaxTom: 1,
            longTom: 1,
            longRequiredTom: 1,
            longMinTom: 1,
            longMaxTom: 1,
            floatTom: 1,
            floatRequiredTom: 1,
            floatMinTom: 1,
            floatMaxTom: 1,
            doubleRequiredTom: 1,
            doubleMinTom: 1,
            doubleMaxTom: 1,
            bigDecimalRequiredTom: 1,
            bigDecimalMinTom: 1,
            bigDecimalMaxTom: 1,
            localDateTom: currentDate.format(DATE_FORMAT),
            localDateRequiredTom: currentDate.format(DATE_FORMAT),
            instantTom: currentDate.format(DATE_TIME_FORMAT),
            instantRequiredTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredTom: currentDate.format(DATE_TIME_FORMAT),
            durationTom: 'BBBBBB',
            durationRequiredTom: 'BBBBBB',
            booleanTom: true,
            booleanRequiredTom: true,
            enumTom: 'BBBBBB',
            enumRequiredTom: 'BBBBBB',
            uuidTom: 'BBBBBB',
            uuidRequiredTom: 'BBBBBB',
            byteImageTom: 'BBBBBB',
            byteImageRequiredTom: 'BBBBBB',
            byteImageMinbytesTom: 'BBBBBB',
            byteImageMaxbytesTom: 'BBBBBB',
            byteAnyTom: 'BBBBBB',
            byteAnyRequiredTom: 'BBBBBB',
            byteAnyMinbytesTom: 'BBBBBB',
            byteAnyMaxbytesTom: 'BBBBBB',
            byteTextTom: 'BBBBBB',
            byteTextRequiredTom: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateTom: currentDate,
            localDateRequiredTom: currentDate,
            instantTom: currentDate,
            instantRequiredTom: currentDate,
            zonedDateTimeTom: currentDate,
            zonedDateTimeRequiredTom: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FieldTestEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringTom: 'BBBBBB',
            stringRequiredTom: 'BBBBBB',
            stringMinlengthTom: 'BBBBBB',
            stringMaxlengthTom: 'BBBBBB',
            stringPatternTom: 'BBBBBB',
            numberPatternTom: 'BBBBBB',
            numberPatternRequiredTom: 'BBBBBB',
            integerTom: 1,
            integerRequiredTom: 1,
            integerMinTom: 1,
            integerMaxTom: 1,
            longTom: 1,
            longRequiredTom: 1,
            longMinTom: 1,
            longMaxTom: 1,
            floatTom: 1,
            floatRequiredTom: 1,
            floatMinTom: 1,
            floatMaxTom: 1,
            doubleRequiredTom: 1,
            doubleMinTom: 1,
            doubleMaxTom: 1,
            bigDecimalRequiredTom: 1,
            bigDecimalMinTom: 1,
            bigDecimalMaxTom: 1,
            localDateTom: currentDate.format(DATE_FORMAT),
            localDateRequiredTom: currentDate.format(DATE_FORMAT),
            instantTom: currentDate.format(DATE_TIME_FORMAT),
            instantRequiredTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeTom: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredTom: currentDate.format(DATE_TIME_FORMAT),
            durationTom: 'BBBBBB',
            durationRequiredTom: 'BBBBBB',
            booleanTom: true,
            booleanRequiredTom: true,
            enumTom: 'BBBBBB',
            enumRequiredTom: 'BBBBBB',
            uuidTom: 'BBBBBB',
            uuidRequiredTom: 'BBBBBB',
            byteImageTom: 'BBBBBB',
            byteImageRequiredTom: 'BBBBBB',
            byteImageMinbytesTom: 'BBBBBB',
            byteImageMaxbytesTom: 'BBBBBB',
            byteAnyTom: 'BBBBBB',
            byteAnyRequiredTom: 'BBBBBB',
            byteAnyMinbytesTom: 'BBBBBB',
            byteAnyMaxbytesTom: 'BBBBBB',
            byteTextTom: 'BBBBBB',
            byteTextRequiredTom: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateTom: currentDate,
            localDateRequiredTom: currentDate,
            instantTom: currentDate,
            instantRequiredTom: currentDate,
            zonedDateTimeTom: currentDate,
            zonedDateTimeRequiredTom: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FieldTestEntity', () => {
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
