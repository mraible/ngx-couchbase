import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEntityWithPaginationAndDTO, EntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';

import { EntityWithPaginationAndDTOService } from './entity-with-pagination-and-dto.service';

describe('Service Tests', () => {
  describe('EntityWithPaginationAndDTO Service', () => {
    let service: EntityWithPaginationAndDTOService;
    let httpMock: HttpTestingController;
    let elemDefault: IEntityWithPaginationAndDTO;
    let expectedResult: IEntityWithPaginationAndDTO | IEntityWithPaginationAndDTO[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EntityWithPaginationAndDTOService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = new EntityWithPaginationAndDTO('AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EntityWithPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EntityWithPaginationAndDTO()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EntityWithPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            lea: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EntityWithPaginationAndDTO', () => {
        const returnedFromService = Object.assign(
          {
            id: 'BBBBBB',
            lea: 'BBBBBB',
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

      it('should delete a EntityWithPaginationAndDTO', () => {
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
