import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import {
  IEntityWithServiceClassPaginationAndDTO,
  EntityWithServiceClassPaginationAndDTO,
} from '../entity-with-service-class-pagination-and-dto.model';

import { EntityWithServiceClassPaginationAndDTOService } from './entity-with-service-class-pagination-and-dto.service';

describe('Service Tests', () => {
  describe('EntityWithServiceClassPaginationAndDTO Service', () => {
    let service: EntityWithServiceClassPaginationAndDTOService;
    let httpMock: HttpTestingController;
    let elemDefault: IEntityWithServiceClassPaginationAndDTO;
    let expectedResult: IEntityWithServiceClassPaginationAndDTO | IEntityWithServiceClassPaginationAndDTO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EntityWithServiceClassPaginationAndDTOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = new EntityWithServiceClassPaginationAndDTO('AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EntityWithServiceClassPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EntityWithServiceClassPaginationAndDTO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EntityWithServiceClassPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            lena: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EntityWithServiceClassPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            lena: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EntityWithServiceClassPaginationAndDTO', () => {
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
