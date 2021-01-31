jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IFieldTestEntity, FieldTestEntity } from '../field-test-entity.model';
import { FieldTestEntityService } from '../service/field-test-entity.service';

import { FieldTestEntityRoutingResolveService } from './field-test-entity-routing-resolve.service';

describe('Service Tests', () => {
  describe('FieldTestEntity routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: FieldTestEntityRoutingResolveService;
    let service: FieldTestEntityService;
    let resultFieldTestEntity: IFieldTestEntity | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(FieldTestEntityRoutingResolveService);
      service = TestBed.inject(FieldTestEntityService);
      resultFieldTestEntity = undefined;
    });

    describe('resolve', () => {
      it('should return existing IFieldTestEntity for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new FieldTestEntity(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestEntity).toEqual(new FieldTestEntity('123'));
      });

      it('should return new IFieldTestEntity if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestEntity = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultFieldTestEntity).toEqual(new FieldTestEntity());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestEntity).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
