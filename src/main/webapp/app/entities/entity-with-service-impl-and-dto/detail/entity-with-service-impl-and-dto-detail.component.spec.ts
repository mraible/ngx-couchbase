import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';

import { EntityWithServiceImplAndDTODetailComponent } from './entity-with-service-impl-and-dto-detail.component';

describe('Component Tests', () => {
  describe('EntityWithServiceImplAndDTO Management Detail Component', () => {
    let comp: EntityWithServiceImplAndDTODetailComponent;
    let fixture: ComponentFixture<EntityWithServiceImplAndDTODetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EntityWithServiceImplAndDTODetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ entityWithServiceImplAndDTO: new EntityWithServiceImplAndDTO('123') }) },
          },
        ],
      })
        .overrideTemplate(EntityWithServiceImplAndDTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityWithServiceImplAndDTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityWithServiceImplAndDTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityWithServiceImplAndDTO).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
