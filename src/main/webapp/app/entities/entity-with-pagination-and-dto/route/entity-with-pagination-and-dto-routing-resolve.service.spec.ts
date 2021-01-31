jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEntityWithPaginationAndDTO, EntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOService } from '../service/entity-with-pagination-and-dto.service';

import { EntityWithPaginationAndDTORoutingResolveService } from './entity-with-pagination-and-dto-routing-resolve.service';

describe('Service Tests', () => {
  describe('EntityWithPaginationAndDTO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EntityWithPaginationAndDTORoutingResolveService;
    let service: EntityWithPaginationAndDTOService;
    let resultEntityWithPaginationAndDTO: IEntityWithPaginationAndDTO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EntityWithPaginationAndDTORoutingResolveService);
      service = TestBed.inject(EntityWithPaginationAndDTOService);
      resultEntityWithPaginationAndDTO = undefined;
    });

    describe('resolve', () => {
      it('should return existing IEntityWithPaginationAndDTO for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new EntityWithPaginationAndDTO(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithPaginationAndDTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithPaginationAndDTO).toEqual(new EntityWithPaginationAndDTO('123'));
      });

      it('should return new IEntityWithPaginationAndDTO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithPaginationAndDTO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEntityWithPaginationAndDTO).toEqual(new EntityWithPaginationAndDTO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithPaginationAndDTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithPaginationAndDTO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
