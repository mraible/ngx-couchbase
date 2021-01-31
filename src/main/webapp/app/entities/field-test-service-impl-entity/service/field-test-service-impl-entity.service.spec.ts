import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';
import { IFieldTestServiceImplEntity, FieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';

import { FieldTestServiceImplEntityService } from './field-test-service-impl-entity.service';

describe('Service Tests', () => {
  describe('FieldTestServiceImplEntity Service', () => {
    let service: FieldTestServiceImplEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IFieldTestServiceImplEntity;
    let expectedResult: IFieldTestServiceImplEntity | IFieldTestServiceImplEntity[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(FieldTestServiceImplEntityService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = new FieldTestServiceImplEntity(
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
            localDateMika: currentDate.format(DATE_FORMAT),
            localDateRequiredMika: currentDate.format(DATE_FORMAT),
            instantMika: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredMika: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FieldTestServiceImplEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            localDateMika: currentDate.format(DATE_FORMAT),
            localDateRequiredMika: currentDate.format(DATE_FORMAT),
            instantMika: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredMika: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateMika: currentDate,
            localDateRequiredMika: currentDate,
            instantMika: currentDate,
            instanteRequiredMika: currentDate,
            zonedDateTimeMika: currentDate,
            zonedDateTimeRequiredMika: currentDate,
          },
          returnedFromService
        );

        service.create(new FieldTestServiceImplEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FieldTestServiceImplEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringMika: 'BBBBBB',
            stringRequiredMika: 'BBBBBB',
            stringMinlengthMika: 'BBBBBB',
            stringMaxlengthMika: 'BBBBBB',
            stringPatternMika: 'BBBBBB',
            integerMika: 1,
            integerRequiredMika: 1,
            integerMinMika: 1,
            integerMaxMika: 1,
            longMika: 1,
            longRequiredMika: 1,
            longMinMika: 1,
            longMaxMika: 1,
            floatMika: 1,
            floatRequiredMika: 1,
            floatMinMika: 1,
            floatMaxMika: 1,
            doubleRequiredMika: 1,
            doubleMinMika: 1,
            doubleMaxMika: 1,
            bigDecimalRequiredMika: 1,
            bigDecimalMinMika: 1,
            bigDecimalMaxMika: 1,
            localDateMika: currentDate.format(DATE_FORMAT),
            localDateRequiredMika: currentDate.format(DATE_FORMAT),
            instantMika: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredMika: currentDate.format(DATE_TIME_FORMAT),
            durationMika: 'BBBBBB',
            durationRequiredMika: 'BBBBBB',
            booleanMika: true,
            booleanRequiredMika: true,
            enumMika: 'BBBBBB',
            enumRequiredMika: 'BBBBBB',
            uuidMika: 'BBBBBB',
            uuidRequiredMika: 'BBBBBB',
            byteImageMika: 'BBBBBB',
            byteImageRequiredMika: 'BBBBBB',
            byteImageMinbytesMika: 'BBBBBB',
            byteImageMaxbytesMika: 'BBBBBB',
            byteAnyMika: 'BBBBBB',
            byteAnyRequiredMika: 'BBBBBB',
            byteAnyMinbytesMika: 'BBBBBB',
            byteAnyMaxbytesMika: 'BBBBBB',
            byteTextMika: 'BBBBBB',
            byteTextRequiredMika: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateMika: currentDate,
            localDateRequiredMika: currentDate,
            instantMika: currentDate,
            instanteRequiredMika: currentDate,
            zonedDateTimeMika: currentDate,
            zonedDateTimeRequiredMika: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FieldTestServiceImplEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringMika: 'BBBBBB',
            stringRequiredMika: 'BBBBBB',
            stringMinlengthMika: 'BBBBBB',
            stringMaxlengthMika: 'BBBBBB',
            stringPatternMika: 'BBBBBB',
            integerMika: 1,
            integerRequiredMika: 1,
            integerMinMika: 1,
            integerMaxMika: 1,
            longMika: 1,
            longRequiredMika: 1,
            longMinMika: 1,
            longMaxMika: 1,
            floatMika: 1,
            floatRequiredMika: 1,
            floatMinMika: 1,
            floatMaxMika: 1,
            doubleRequiredMika: 1,
            doubleMinMika: 1,
            doubleMaxMika: 1,
            bigDecimalRequiredMika: 1,
            bigDecimalMinMika: 1,
            bigDecimalMaxMika: 1,
            localDateMika: currentDate.format(DATE_FORMAT),
            localDateRequiredMika: currentDate.format(DATE_FORMAT),
            instantMika: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeMika: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredMika: currentDate.format(DATE_TIME_FORMAT),
            durationMika: 'BBBBBB',
            durationRequiredMika: 'BBBBBB',
            booleanMika: true,
            booleanRequiredMika: true,
            enumMika: 'BBBBBB',
            enumRequiredMika: 'BBBBBB',
            uuidMika: 'BBBBBB',
            uuidRequiredMika: 'BBBBBB',
            byteImageMika: 'BBBBBB',
            byteImageRequiredMika: 'BBBBBB',
            byteImageMinbytesMika: 'BBBBBB',
            byteImageMaxbytesMika: 'BBBBBB',
            byteAnyMika: 'BBBBBB',
            byteAnyRequiredMika: 'BBBBBB',
            byteAnyMinbytesMika: 'BBBBBB',
            byteAnyMaxbytesMika: 'BBBBBB',
            byteTextMika: 'BBBBBB',
            byteTextRequiredMika: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateMika: currentDate,
            localDateRequiredMika: currentDate,
            instantMika: currentDate,
            instanteRequiredMika: currentDate,
            zonedDateTimeMika: currentDate,
            zonedDateTimeRequiredMika: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FieldTestServiceImplEntity', () => {
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
