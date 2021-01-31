jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IFieldTestServiceImplEntity, FieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityService } from '../service/field-test-service-impl-entity.service';

import { FieldTestServiceImplEntityRoutingResolveService } from './field-test-service-impl-entity-routing-resolve.service';

describe('Service Tests', () => {
  describe('FieldTestServiceImplEntity routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: FieldTestServiceImplEntityRoutingResolveService;
    let service: FieldTestServiceImplEntityService;
    let resultFieldTestServiceImplEntity: IFieldTestServiceImplEntity | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(FieldTestServiceImplEntityRoutingResolveService);
      service = TestBed.inject(FieldTestServiceImplEntityService);
      resultFieldTestServiceImplEntity = undefined;
    });

    describe('resolve', () => {
      it('should return existing IFieldTestServiceImplEntity for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new FieldTestServiceImplEntity(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestServiceImplEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestServiceImplEntity).toEqual(new FieldTestServiceImplEntity('123'));
      });

      it('should return new IFieldTestServiceImplEntity if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestServiceImplEntity = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultFieldTestServiceImplEntity).toEqual(new FieldTestServiceImplEntity());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestServiceImplEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestServiceImplEntity).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
