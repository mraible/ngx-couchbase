import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceImplPaginationAndDTO } from '../entity-with-service-impl-pagination-and-dto.model';

import { EntityWithServiceImplPaginationAndDTODetailComponent } from './entity-with-service-impl-pagination-and-dto-detail.component';

describe('Component Tests', () => {
  describe('EntityWithServiceImplPaginationAndDTO Management Detail Component', () => {
    let comp: EntityWithServiceImplPaginationAndDTODetailComponent;
    let fixture: ComponentFixture<EntityWithServiceImplPaginationAndDTODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EntityWithServiceImplPaginationAndDTODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ entityWithServiceImplPaginationAndDTO: new EntityWithServiceImplPaginationAndDTO('123') }) },
          },
        ],
      })
        .overrideTemplate(EntityWithServiceImplPaginationAndDTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityWithServiceImplPaginationAndDTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityWithServiceImplPaginationAndDTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityWithServiceImplPaginationAndDTO).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
