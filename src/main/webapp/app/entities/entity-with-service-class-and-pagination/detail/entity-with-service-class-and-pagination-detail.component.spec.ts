import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceClassAndPagination } from '../entity-with-service-class-and-pagination.model';

import { EntityWithServiceClassAndPaginationDetailComponent } from './entity-with-service-class-and-pagination-detail.component';

describe('Component Tests', () => {
  describe('EntityWithServiceClassAndPagination Management Detail Component', () => {
    let comp: EntityWithServiceClassAndPaginationDetailComponent;
    let fixture: ComponentFixture<EntityWithServiceClassAndPaginationDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EntityWithServiceClassAndPaginationDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ entityWithServiceClassAndPagination: new EntityWithServiceClassAndPagination('123') }) },
          },
        ],
      })
        .overrideTemplate(EntityWithServiceClassAndPaginationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityWithServiceClassAndPaginationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityWithServiceClassAndPagination on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityWithServiceClassAndPagination).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
