jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEntityWithServiceImplAndPagination, EntityWithServiceImplAndPagination } from '../entity-with-service-impl-and-pagination.model';
import { EntityWithServiceImplAndPaginationService } from '../service/entity-with-service-impl-and-pagination.service';

import { EntityWithServiceImplAndPaginationRoutingResolveService } from './entity-with-service-impl-and-pagination-routing-resolve.service';

describe('Service Tests', () => {
  describe('EntityWithServiceImplAndPagination routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EntityWithServiceImplAndPaginationRoutingResolveService;
    let service: EntityWithServiceImplAndPaginationService;
    let resultEntityWithServiceImplAndPagination: IEntityWithServiceImplAndPagination | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EntityWithServiceImplAndPaginationRoutingResolveService);
      service = TestBed.inject(EntityWithServiceImplAndPaginationService);
      resultEntityWithServiceImplAndPagination = undefined;
    });

    describe('resolve', () => {
      it('should return existing IEntityWithServiceImplAndPagination for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new EntityWithServiceImplAndPagination(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithServiceImplAndPagination = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithServiceImplAndPagination).toEqual(new EntityWithServiceImplAndPagination('123'));
      });

      it('should return new IEntityWithServiceImplAndPagination if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithServiceImplAndPagination = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEntityWithServiceImplAndPagination).toEqual(new EntityWithServiceImplAndPagination());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithServiceImplAndPagination = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithServiceImplAndPagination).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
