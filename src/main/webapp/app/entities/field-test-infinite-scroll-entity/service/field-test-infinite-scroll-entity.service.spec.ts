import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { EnumFieldClass } from 'app/entities/enumerations/enum-field-class.model';
import { EnumRequiredFieldClass } from 'app/entities/enumerations/enum-required-field-class.model';
import { IFieldTestInfiniteScrollEntity, FieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';

import { FieldTestInfiniteScrollEntityService } from './field-test-infinite-scroll-entity.service';

describe('Service Tests', () => {
  describe('FieldTestInfiniteScrollEntity Service', () => {
    let service: FieldTestInfiniteScrollEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IFieldTestInfiniteScrollEntity;
    let expectedResult: IFieldTestInfiniteScrollEntity | IFieldTestInfiniteScrollEntity[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(FieldTestInfiniteScrollEntityService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = new FieldTestInfiniteScrollEntity(
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
            localDateHugo: currentDate.format(DATE_FORMAT),
            localDateRequiredHugo: currentDate.format(DATE_FORMAT),
            instantHugo: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FieldTestInfiniteScrollEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            localDateHugo: currentDate.format(DATE_FORMAT),
            localDateRequiredHugo: currentDate.format(DATE_FORMAT),
            instantHugo: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateHugo: currentDate,
            localDateRequiredHugo: currentDate,
            instantHugo: currentDate,
            instanteRequiredHugo: currentDate,
            zonedDateTimeHugo: currentDate,
            zonedDateTimeRequiredHugo: currentDate,
          },
          returnedFromService
        );

        service.create(new FieldTestInfiniteScrollEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FieldTestInfiniteScrollEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringHugo: 'BBBBBB',
            stringRequiredHugo: 'BBBBBB',
            stringMinlengthHugo: 'BBBBBB',
            stringMaxlengthHugo: 'BBBBBB',
            stringPatternHugo: 'BBBBBB',
            integerHugo: 1,
            integerRequiredHugo: 1,
            integerMinHugo: 1,
            integerMaxHugo: 1,
            longHugo: 1,
            longRequiredHugo: 1,
            longMinHugo: 1,
            longMaxHugo: 1,
            floatHugo: 1,
            floatRequiredHugo: 1,
            floatMinHugo: 1,
            floatMaxHugo: 1,
            doubleRequiredHugo: 1,
            doubleMinHugo: 1,
            doubleMaxHugo: 1,
            bigDecimalRequiredHugo: 1,
            bigDecimalMinHugo: 1,
            bigDecimalMaxHugo: 1,
            localDateHugo: currentDate.format(DATE_FORMAT),
            localDateRequiredHugo: currentDate.format(DATE_FORMAT),
            instantHugo: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
            durationHugo: 'BBBBBB',
            durationRequiredHugo: 'BBBBBB',
            booleanHugo: true,
            booleanRequiredHugo: true,
            enumHugo: 'BBBBBB',
            enumRequiredHugo: 'BBBBBB',
            uuidHugo: 'BBBBBB',
            uuidRequiredHugo: 'BBBBBB',
            byteImageHugo: 'BBBBBB',
            byteImageRequiredHugo: 'BBBBBB',
            byteImageMinbytesHugo: 'BBBBBB',
            byteImageMaxbytesHugo: 'BBBBBB',
            byteAnyHugo: 'BBBBBB',
            byteAnyRequiredHugo: 'BBBBBB',
            byteAnyMinbytesHugo: 'BBBBBB',
            byteAnyMaxbytesHugo: 'BBBBBB',
            byteTextHugo: 'BBBBBB',
            byteTextRequiredHugo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateHugo: currentDate,
            localDateRequiredHugo: currentDate,
            instantHugo: currentDate,
            instanteRequiredHugo: currentDate,
            zonedDateTimeHugo: currentDate,
            zonedDateTimeRequiredHugo: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FieldTestInfiniteScrollEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            stringHugo: 'BBBBBB',
            stringRequiredHugo: 'BBBBBB',
            stringMinlengthHugo: 'BBBBBB',
            stringMaxlengthHugo: 'BBBBBB',
            stringPatternHugo: 'BBBBBB',
            integerHugo: 1,
            integerRequiredHugo: 1,
            integerMinHugo: 1,
            integerMaxHugo: 1,
            longHugo: 1,
            longRequiredHugo: 1,
            longMinHugo: 1,
            longMaxHugo: 1,
            floatHugo: 1,
            floatRequiredHugo: 1,
            floatMinHugo: 1,
            floatMaxHugo: 1,
            doubleRequiredHugo: 1,
            doubleMinHugo: 1,
            doubleMaxHugo: 1,
            bigDecimalRequiredHugo: 1,
            bigDecimalMinHugo: 1,
            bigDecimalMaxHugo: 1,
            localDateHugo: currentDate.format(DATE_FORMAT),
            localDateRequiredHugo: currentDate.format(DATE_FORMAT),
            instantHugo: currentDate.format(DATE_TIME_FORMAT),
            instanteRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeHugo: currentDate.format(DATE_TIME_FORMAT),
            zonedDateTimeRequiredHugo: currentDate.format(DATE_TIME_FORMAT),
            durationHugo: 'BBBBBB',
            durationRequiredHugo: 'BBBBBB',
            booleanHugo: true,
            booleanRequiredHugo: true,
            enumHugo: 'BBBBBB',
            enumRequiredHugo: 'BBBBBB',
            uuidHugo: 'BBBBBB',
            uuidRequiredHugo: 'BBBBBB',
            byteImageHugo: 'BBBBBB',
            byteImageRequiredHugo: 'BBBBBB',
            byteImageMinbytesHugo: 'BBBBBB',
            byteImageMaxbytesHugo: 'BBBBBB',
            byteAnyHugo: 'BBBBBB',
            byteAnyRequiredHugo: 'BBBBBB',
            byteAnyMinbytesHugo: 'BBBBBB',
            byteAnyMaxbytesHugo: 'BBBBBB',
            byteTextHugo: 'BBBBBB',
            byteTextRequiredHugo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            localDateHugo: currentDate,
            localDateRequiredHugo: currentDate,
            instantHugo: currentDate,
            instanteRequiredHugo: currentDate,
            zonedDateTimeHugo: currentDate,
            zonedDateTimeRequiredHugo: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FieldTestInfiniteScrollEntity', () => {
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
