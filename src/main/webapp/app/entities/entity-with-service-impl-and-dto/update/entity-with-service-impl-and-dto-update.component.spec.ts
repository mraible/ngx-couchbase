jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceImplAndDTOService } from '../service/entity-with-service-impl-and-dto.service';
import { EntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';

import { EntityWithServiceImplAndDTOUpdateComponent } from './entity-with-service-impl-and-dto-update.component';

describe('Component Tests', () => {
  describe('EntityWithServiceImplAndDTO Management Update Component', () => {
    let comp: EntityWithServiceImplAndDTOUpdateComponent;
    let fixture: ComponentFixture<EntityWithServiceImplAndDTOUpdateComponent>;
    let service: EntityWithServiceImplAndDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithServiceImplAndDTOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EntityWithServiceImplAndDTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithServiceImplAndDTOUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithServiceImplAndDTOService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithServiceImplAndDTO('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithServiceImplAndDTO();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
