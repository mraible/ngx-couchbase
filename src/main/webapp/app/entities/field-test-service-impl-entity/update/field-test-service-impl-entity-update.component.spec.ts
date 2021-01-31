jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestServiceImplEntityService } from '../service/field-test-service-impl-entity.service';
import { FieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';

import { FieldTestServiceImplEntityUpdateComponent } from './field-test-service-impl-entity-update.component';

describe('Component Tests', () => {
  describe('FieldTestServiceImplEntity Management Update Component', () => {
    let comp: FieldTestServiceImplEntityUpdateComponent;
    let fixture: ComponentFixture<FieldTestServiceImplEntityUpdateComponent>;
    let service: FieldTestServiceImplEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestServiceImplEntityUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(FieldTestServiceImplEntityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestServiceImplEntityUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestServiceImplEntityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FieldTestServiceImplEntity('123');
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
        const entity = new FieldTestServiceImplEntity();
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
