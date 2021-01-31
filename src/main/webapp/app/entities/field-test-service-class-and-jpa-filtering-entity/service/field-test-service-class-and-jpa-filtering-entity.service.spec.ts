import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';
import {
  IFieldTestServiceClassAndJpaFilteringEntity,
  FieldTestServiceClassAndJpaFilteringEntity,
} from '../field-test-service-class-and-jpa-filtering-entity.model';

import { FieldTestServiceClassAndJpaFilteringEntityService } from './field-test-service-class-and-jpa-filtering-entity.service';

describe('Service Tests', () => {
  describe('FieldTestServiceClassAndJpaFilteringEntity Service', () => {
    let service: FieldTestServiceClassAndJpaFilteringEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IFieldTestServiceClassAndJpaFilteringEntity;
    let expectedResult: IFieldTestServiceClassAndJpaFilteringEntity | IFieldTestServiceClassAndJpaFilteringEntity[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(FieldTestServiceClassAndJpaFilteringEntityService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = new FieldTestServiceClassAndJpaFilteringEntity(
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
            localDateBob: currentDate.format(DATE_FORMAT),
            localDateRequiredBob: currentDate.format(DATE_FORMAT),
            instantBob: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredBob: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FieldTestServiceClassAndJpaFilteringEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            localDateBob: currentDate.format(DATE_FORMAT),
            localDateRequiredBob: currentDate.format(DATE_FORMAT),
            instantBob: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredBob: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateBob: currentDate,
            localDateRequiredBob: currentDate,
            instantBob: currentDate,
            instanteRequiredBob: currentDate,
            zonedDateTimeBob: currentDate,
            zonedDateTimeRequiredBob: currentDate,
          },
          returnedFromService
        );

        service.create(new FieldTestServiceClassAndJpaFilteringEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FieldTestServiceClassAndJpaFilteringEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringBob: 'BBBBBB',
            stringRequiredBob: 'BBBBBB',
            stringMinlengthBob: 'BBBBBB',
            stringMaxlengthBob: 'BBBBBB',
            stringPatternBob: 'BBBBBB',
            integerBob: 1,
            integerRequiredBob: 1,
            integerMinBob: 1,
            integerMaxBob: 1,
            longBob: 1,
            longRequiredBob: 1,
            longMinBob: 1,
            longMaxBob: 1,
            floatBob: 1,
            floatRequiredBob: 1,
            floatMinBob: 1,
            floatMaxBob: 1,
            doubleRequiredBob: 1,
            doubleMinBob: 1,
            doubleMaxBob: 1,
            bigDecimalRequiredBob: 1,
            bigDecimalMinBob: 1,
            bigDecimalMaxBob: 1,
            localDateBob: currentDate.format(DATE_FORMAT),
            localDateRequiredBob: currentDate.format(DATE_FORMAT),
            instantBob: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredBob: currentDate.format(DATE_TIME_FORMAT),
            durationBob: 'BBBBBB',
            durationRequiredBob: 'BBBBBB',
            booleanBob: true,
            booleanRequiredBob: true,
            enumBob: 'BBBBBB',
            enumRequiredBob: 'BBBBBB',
            uuidBob: 'BBBBBB',
            uuidRequiredBob: 'BBBBBB',
            byteImageBob: 'BBBBBB',
            byteImageRequiredBob: 'BBBBBB',
            byteImageMinbytesBob: 'BBBBBB',
            byteImageMaxbytesBob: 'BBBBBB',
            byteAnyBob: 'BBBBBB',
            byteAnyRequiredBob: 'BBBBBB',
            byteAnyMinbytesBob: 'BBBBBB',
            byteAnyMaxbytesBob: 'BBBBBB',
            byteTextBob: 'BBBBBB',
            byteTextRequiredBob: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateBob: currentDate,
            localDateRequiredBob: currentDate,
            instantBob: currentDate,
            instanteRequiredBob: currentDate,
            zonedDateTimeBob: currentDate,
            zonedDateTimeRequiredBob: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FieldTestServiceClassAndJpaFilteringEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringBob: 'BBBBBB',
            stringRequiredBob: 'BBBBBB',
            stringMinlengthBob: 'BBBBBB',
            stringMaxlengthBob: 'BBBBBB',
            stringPatternBob: 'BBBBBB',
            integerBob: 1,
            integerRequiredBob: 1,
            integerMinBob: 1,
            integerMaxBob: 1,
            longBob: 1,
            longRequiredBob: 1,
            longMinBob: 1,
            longMaxBob: 1,
            floatBob: 1,
            floatRequiredBob: 1,
            floatMinBob: 1,
            floatMaxBob: 1,
            doubleRequiredBob: 1,
            doubleMinBob: 1,
            doubleMaxBob: 1,
            bigDecimalRequiredBob: 1,
            bigDecimalMinBob: 1,
            bigDecimalMaxBob: 1,
            localDateBob: currentDate.format(DATE_FORMAT),
            localDateRequiredBob: currentDate.format(DATE_FORMAT),
            instantBob: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeBob: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredBob: currentDate.format(DATE_TIME_FORMAT),
            durationBob: 'BBBBBB',
            durationRequiredBob: 'BBBBBB',
            booleanBob: true,
            booleanRequiredBob: true,
            enumBob: 'BBBBBB',
            enumRequiredBob: 'BBBBBB',
            uuidBob: 'BBBBBB',
            uuidRequiredBob: 'BBBBBB',
            byteImageBob: 'BBBBBB',
            byteImageRequiredBob: 'BBBBBB',
            byteImageMinbytesBob: 'BBBBBB',
            byteImageMaxbytesBob: 'BBBBBB',
            byteAnyBob: 'BBBBBB',
            byteAnyRequiredBob: 'BBBBBB',
            byteAnyMinbytesBob: 'BBBBBB',
            byteAnyMaxbytesBob: 'BBBBBB',
            byteTextBob: 'BBBBBB',
            byteTextRequiredBob: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateBob: currentDate,
            localDateRequiredBob: currentDate,
            instantBob: currentDate,
            instanteRequiredBob: currentDate,
            zonedDateTimeBob: currentDate,
            zonedDateTimeRequiredBob: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FieldTestServiceClassAndJpaFilteringEntity', () => {
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
