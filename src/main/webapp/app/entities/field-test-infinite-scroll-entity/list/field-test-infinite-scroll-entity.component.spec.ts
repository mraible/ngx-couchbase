import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { FieldTestInfiniteScrollEntityService } from '../service/field-test-infinite-scroll-entity.service';
import { FieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';

import { FieldTestInfiniteScrollEntityComponent } from './field-test-infinite-scroll-entity.component';

describe('Component Tests', () => {
  describe('FieldTestInfiniteScrollEntity Management Component', () => {
    let comp: FieldTestInfiniteScrollEntityComponent;
    let fixture: ComponentFixture<FieldTestInfiniteScrollEntityComponent>;
    let service: FieldTestInfiniteScrollEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestInfiniteScrollEntityComponent],
      })
        .overrideTemplate(FieldTestInfiniteScrollEntityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestInfiniteScrollEntityComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestInfiniteScrollEntityService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FieldTestInfiniteScrollEntity('123')],
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
      expect(comp.fieldTestInfiniteScrollEntities[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', () => {
      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fieldTestInfiniteScrollEntities[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalledWith(expect.objectContaining({ sort: ['id,asc'] }));
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['name,asc', 'id'] }));
    });

    it('should re-initialize the page', () => {
      // WHEN
      comp.loadPage(1);
      comp.reset();

      // THEN
      expect(comp.page).toEqual(0);
      expect(service.query).toHaveBeenCalledTimes(2);
      expect(comp.fieldTestInfiniteScrollEntities[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
