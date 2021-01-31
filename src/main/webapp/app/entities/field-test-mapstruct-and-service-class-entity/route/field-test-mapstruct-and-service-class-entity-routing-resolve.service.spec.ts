jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import {
  IFieldTestMapstructAndServiceClassEntity,
  FieldTestMapstructAndServiceClassEntity,
} from '../field-test-mapstruct-and-service-class-entity.model';
import { FieldTestMapstructAndServiceClassEntityService } from '../service/field-test-mapstruct-and-service-class-entity.service';

import { FieldTestMapstructAndServiceClassEntityRoutingResolveService } from './field-test-mapstruct-and-service-class-entity-routing-resolve.service';

describe('Service Tests', () => {
  describe('FieldTestMapstructAndServiceClassEntity routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: FieldTestMapstructAndServiceClassEntityRoutingResolveService;
    let service: FieldTestMapstructAndServiceClassEntityService;
    let resultFieldTestMapstructAndServiceClassEntity: IFieldTestMapstructAndServiceClassEntity | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(FieldTestMapstructAndServiceClassEntityRoutingResolveService);
      service = TestBed.inject(FieldTestMapstructAndServiceClassEntityService);
      resultFieldTestMapstructAndServiceClassEntity = undefined;
    });

    describe('resolve', () => {
      it('should return existing IFieldTestMapstructAndServiceClassEntity for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new FieldTestMapstructAndServiceClassEntity(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestMapstructAndServiceClassEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestMapstructAndServiceClassEntity).toEqual(new FieldTestMapstructAndServiceClassEntity('123'));
      });

      it('should return new IFieldTestMapstructAndServiceClassEntity if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestMapstructAndServiceClassEntity = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultFieldTestMapstructAndServiceClassEntity).toEqual(new FieldTestMapstructAndServiceClassEntity());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestMapstructAndServiceClassEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestMapstructAndServiceClassEntity).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
