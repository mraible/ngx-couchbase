import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';

import { EntityWithPaginationAndDTODetailComponent } from './entity-with-pagination-and-dto-detail.component';

describe('Component Tests', () => {
  describe('EntityWithPaginationAndDTO Management Detail Component', () => {
    let comp: EntityWithPaginationAndDTODetailComponent;
    let fixture: ComponentFixture<EntityWithPaginationAndDTODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EntityWithPaginationAndDTODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ entityWithPaginationAndDTO: new EntityWithPaginationAndDTO('123') }) },
          },
        ],
      })
        .overrideTemplate(EntityWithPaginationAndDTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityWithPaginationAndDTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityWithPaginationAndDTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityWithPaginationAndDTO).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
