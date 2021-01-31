jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEntityWithDTO, EntityWithDTO } from '../entity-with-dto.model';
import { EntityWithDTOService } from '../service/entity-with-dto.service';

import { EntityWithDTORoutingResolveService } from './entity-with-dto-routing-resolve.service';

describe('Service Tests', () => {
  describe('EntityWithDTO routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EntityWithDTORoutingResolveService;
    let service: EntityWithDTOService;
    let resultEntityWithDTO: IEntityWithDTO | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EntityWithDTORoutingResolveService);
      service = TestBed.inject(EntityWithDTOService);
      resultEntityWithDTO = undefined;
    });

    describe('resolve', () => {
      it('should return existing IEntityWithDTO for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new EntityWithDTO(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithDTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithDTO).toEqual(new EntityWithDTO('123'));
      });

      it('should return new IEntityWithDTO if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithDTO = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEntityWithDTO).toEqual(new EntityWithDTO());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEntityWithDTO = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultEntityWithDTO).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
