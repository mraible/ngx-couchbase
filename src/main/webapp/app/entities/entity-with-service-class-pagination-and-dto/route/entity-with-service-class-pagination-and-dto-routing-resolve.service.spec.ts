jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import {
  IEntityWithServiceClassPaginationAndDTO,
  EntityWithServiceClassPaginationAndDTO,
} from '../entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOService } from '../service/entity-with-service-class-pagination-and-dto.service';

import { EntityWithServiceClassPaginationAndDTORoutingResolveService } from './entity-with-service-class-pagination-and-dto-routing-resolve.service';

describe('Service Tests', () => {
  describe('EntityWithServiceClassPaginationAndDTO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EntityWithServiceClassPaginationAndDTORoutingResolveService;
    let service: EntityWithServiceClassPaginationAndDTOService;
    let resultEntityWithServiceClassPaginationAndDTO: IEntityWithServiceClassPaginationAndDTO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EntityWithServiceClassPaginationAndDTORoutingResolveService);
      service = TestBed.inject(EntityWithServiceClassPaginationAndDTOService);
      resultEntityWithServiceClassPaginationAndDTO = undefined;
    });

    describe('resolve', () => {
      it('should return existing IEntityWithServiceClassPaginationAndDTO for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new EntityWithServiceClassPaginationAndDTO(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithServiceClassPaginationAndDTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithServiceClassPaginationAndDTO).toEqual(new EntityWithServiceClassPaginationAndDTO('123'));
      });

      it('should return new IEntityWithServiceClassPaginationAndDTO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithServiceClassPaginationAndDTO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEntityWithServiceClassPaginationAndDTO).toEqual(new EntityWithServiceClassPaginationAndDTO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithServiceClassPaginationAndDTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithServiceClassPaginationAndDTO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
