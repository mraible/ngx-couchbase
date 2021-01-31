import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import {
  IEntityWithServiceImplPaginationAndDTO,
  EntityWithServiceImplPaginationAndDTO,
} from '../entity-with-service-impl-pagination-and-dto.model';

import { EntityWithServiceImplPaginationAndDTOService } from './entity-with-service-impl-pagination-and-dto.service';

describe('Service Tests', () => {
  describe('EntityWithServiceImplPaginationAndDTO Service', () => {
    let service: EntityWithServiceImplPaginationAndDTOService;
    let httpMock: HttpTestingController;
    let elemDefault: IEntityWithServiceImplPaginationAndDTO;
    let expectedResult: IEntityWithServiceImplPaginationAndDTO | IEntityWithServiceImplPaginationAndDTO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EntityWithServiceImplPaginationAndDTOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = new EntityWithServiceImplPaginationAndDTO('AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EntityWithServiceImplPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EntityWithServiceImplPaginationAndDTO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EntityWithServiceImplPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            theo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EntityWithServiceImplPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            theo: 'BBBBBB',
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

      it('should delete a EntityWithServiceImplPaginationAndDTO', () => {
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
