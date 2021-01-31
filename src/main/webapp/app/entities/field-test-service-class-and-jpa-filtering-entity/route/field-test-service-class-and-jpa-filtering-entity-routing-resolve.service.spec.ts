jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import {
  IFieldTestServiceClassAndJpaFilteringEntity,
  FieldTestServiceClassAndJpaFilteringEntity,
} from '../field-test-service-class-and-jpa-filtering-entity.model';
import { FieldTestServiceClassAndJpaFilteringEntityService } from '../service/field-test-service-class-and-jpa-filtering-entity.service';

import { FieldTestServiceClassAndJpaFilteringEntityRoutingResolveService } from './field-test-service-class-and-jpa-filtering-entity-routing-resolve.service';

describe('Service Tests', () => {
  describe('FieldTestServiceClassAndJpaFilteringEntity routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: FieldTestServiceClassAndJpaFilteringEntityRoutingResolveService;
    let service: FieldTestServiceClassAndJpaFilteringEntityService;
    let resultFieldTestServiceClassAndJpaFilteringEntity: IFieldTestServiceClassAndJpaFilteringEntity | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(FieldTestServiceClassAndJpaFilteringEntityRoutingResolveService);
      service = TestBed.inject(FieldTestServiceClassAndJpaFilteringEntityService);
      resultFieldTestServiceClassAndJpaFilteringEntity = undefined;
    });

    describe('resolve', () => {
      it('should return existing IFieldTestServiceClassAndJpaFilteringEntity for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new FieldTestServiceClassAndJpaFilteringEntity(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestServiceClassAndJpaFilteringEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestServiceClassAndJpaFilteringEntity).toEqual(new FieldTestServiceClassAndJpaFilteringEntity('123'));
      });

      it('should return new IFieldTestServiceClassAndJpaFilteringEntity if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestServiceClassAndJpaFilteringEntity = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultFieldTestServiceClassAndJpaFilteringEntity).toEqual(new FieldTestServiceClassAndJpaFilteringEntity());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestServiceClassAndJpaFilteringEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestServiceClassAndJpaFilteringEntity).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
