jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceClassPaginationAndDTOService } from '../service/entity-with-service-class-pagination-and-dto.service';
import { EntityWithServiceClassPaginationAndDTO } from '../entity-with-service-class-pagination-and-dto.model';

import { EntityWithServiceClassPaginationAndDTOComponent } from './entity-with-service-class-pagination-and-dto.component';

describe('Component Tests', () => {
  describe('EntityWithServiceClassPaginationAndDTO Management Component', () => {
    let comp: EntityWithServiceClassPaginationAndDTOComponent;
    let fixture: ComponentFixture<EntityWithServiceClassPaginationAndDTOComponent>;
    let service: EntityWithServiceClassPaginationAndDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithServiceClassPaginationAndDTOComponent],
        providers: [
          Router,
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                jest.requireActual('@angular/router').convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(EntityWithServiceClassPaginationAndDTOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithServiceClassPaginationAndDTOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithServiceClassPaginationAndDTOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityWithServiceClassPaginationAndDTO('123')],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityWithServiceClassPaginationAndDTOS?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityWithServiceClassPaginationAndDTOS?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalledWith(expect.objectContaining({ sort: ['id,desc'] }));
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['name,desc', 'id'] }));
    });
  });
});
