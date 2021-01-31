import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithDTO } from '../entity-with-dto.model';

import { EntityWithDTODetailComponent } from './entity-with-dto-detail.component';

describe('Component Tests', () => {
  describe('EntityWithDTO Management Detail Component', () => {
    let comp: EntityWithDTODetailComponent;
    let fixture: ComponentFixture<EntityWithDTODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EntityWithDTODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ entityWithDTO: new EntityWithDTO('123') }) },
          },
        ],
      })
        .overrideTemplate(EntityWithDTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityWithDTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityWithDTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityWithDTO).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
