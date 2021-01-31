import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import {
  IEntityWithServiceClassAndPagination,
  EntityWithServiceClassAndPagination,
} from '../entity-with-service-class-and-pagination.model';

import { EntityWithServiceClassAndPaginationService } from './entity-with-service-class-and-pagination.service';

describe('Service Tests', () => {
  describe('EntityWithServiceClassAndPagination Service', () => {
    let service: EntityWithServiceClassAndPaginationService;
    let httpMock: HttpTestingController;
    let elemDefault: IEntityWithServiceClassAndPagination;
    let expectedResult: IEntityWithServiceClassAndPagination | IEntityWithServiceClassAndPagination[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EntityWithServiceClassAndPaginationService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = new EntityWithServiceClassAndPagination('AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EntityWithServiceClassAndPagination', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EntityWithServiceClassAndPagination()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EntityWithServiceClassAndPagination', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            enzo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EntityWithServiceClassAndPagination', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            enzo: 'BBBBBB',
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

      it('should delete a EntityWithServiceClassAndPagination', () => {
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
