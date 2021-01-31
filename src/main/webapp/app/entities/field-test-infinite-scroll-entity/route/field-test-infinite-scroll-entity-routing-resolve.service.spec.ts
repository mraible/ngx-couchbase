jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IFieldTestInfiniteScrollEntity, FieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';
import { FieldTestInfiniteScrollEntityService } from '../service/field-test-infinite-scroll-entity.service';

import { FieldTestInfiniteScrollEntityRoutingResolveService } from './field-test-infinite-scroll-entity-routing-resolve.service';

describe('Service Tests', () => {
  describe('FieldTestInfiniteScrollEntity routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: FieldTestInfiniteScrollEntityRoutingResolveService;
    let service: FieldTestInfiniteScrollEntityService;
    let resultFieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(FieldTestInfiniteScrollEntityRoutingResolveService);
      service = TestBed.inject(FieldTestInfiniteScrollEntityService);
      resultFieldTestInfiniteScrollEntity = undefined;
    });

    describe('resolve', () => {
      it('should return existing IFieldTestInfiniteScrollEntity for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new FieldTestInfiniteScrollEntity(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestInfiniteScrollEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestInfiniteScrollEntity).toEqual(new FieldTestInfiniteScrollEntity('123'));
      });

      it('should return new IFieldTestInfiniteScrollEntity if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestInfiniteScrollEntity = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultFieldTestInfiniteScrollEntity).toEqual(new FieldTestInfiniteScrollEntity());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultFieldTestInfiniteScrollEntity = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultFieldTestInfiniteScrollEntity).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
