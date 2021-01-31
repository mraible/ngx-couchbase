import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { BankAccountType } from 'app/entities/enumerations/bank-account-type.model';
import { IDocumentBankAccount, DocumentBankAccount } from '../document-bank-account.model';

import { DocumentBankAccountService } from './document-bank-account.service';

describe('Service Tests', () => {
  describe('DocumentBankAccount Service', () => {
    let service: DocumentBankAccountService;
    let httpMock: HttpTestingController;
    let elemDefault: IDocumentBankAccount;
    let expectedResult: IDocumentBankAccount | IDocumentBankAccount[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(DocumentBankAccountService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = new DocumentBankAccount(
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        currentDate,
        currentDate,
        false,
        BankAccountType.CHECKING,
        'image/png',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            openingDay: currentDate.format(DATE_FORMAT),
            lastOperationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DocumentBankAccount', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            openingDay: currentDate.format(DATE_FORMAT),
            lastOperationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            openingDay: currentDate,
            lastOperationDate: currentDate,
          },
          returnedFromService
        );

        service.create(new DocumentBankAccount()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DocumentBankAccount', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            name: 'BBBBBB',
            bankNumber: 1,
            agencyNumber: 1,
            lastOperationDuration: 1,
            meanOperationDuration: 1,
            balance: 1,
            openingDay: currentDate.format(DATE_FORMAT),
            lastOperationDate: currentDate.format(DATE_TIME_FORMAT),
            active: true,
            accountType: 'BBBBBB',
            attachment: 'BBBBBB',
            description: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            openingDay: currentDate,
            lastOperationDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DocumentBankAccount', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            name: 'BBBBBB',
            bankNumber: 1,
            agencyNumber: 1,
            lastOperationDuration: 1,
            meanOperationDuration: 1,
            balance: 1,
            openingDay: currentDate.format(DATE_FORMAT),
            lastOperationDate: currentDate.format(DATE_TIME_FORMAT),
            active: true,
            accountType: 'BBBBBB',
            attachment: 'BBBBBB',
            description: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            openingDay: currentDate,
            lastOperationDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DocumentBankAccount', () => {
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
