import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEntityWithServiceImplAndPagination, EntityWithServiceImplAndPagination } from '../entity-with-service-impl-and-pagination.model';

import { EntityWithServiceImplAndPaginationService } from './entity-with-service-impl-and-pagination.service';

describe('Service Tests', () => {
  describe('EntityWithServiceImplAndPagination Service', () => {
    let service: EntityWithServiceImplAndPaginationService;
    let httpMock: HttpTestingController;
    let elemDefault: IEntityWithServiceImplAndPagination;
    let expectedResult: IEntityWithServiceImplAndPagination | IEntityWithServiceImplAndPagination[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EntityWithServiceImplAndPaginationService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = new EntityWithServiceImplAndPagination('AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EntityWithServiceImplAndPagination', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EntityWithServiceImplAndPagination()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EntityWithServiceImplAndPagination', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            hugo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EntityWithServiceImplAndPagination', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            hugo: 'BBBBBB',
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

      it('should delete a EntityWithServiceImplAndPagination', () => {
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
