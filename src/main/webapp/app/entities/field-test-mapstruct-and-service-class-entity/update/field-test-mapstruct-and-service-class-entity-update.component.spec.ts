jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestMapstructAndServiceClassEntityService } from '../service/field-test-mapstruct-and-service-class-entity.service';
import { FieldTestMapstructAndServiceClassEntity } from '../field-test-mapstruct-and-service-class-entity.model';

import { FieldTestMapstructAndServiceClassEntityUpdateComponent } from './field-test-mapstruct-and-service-class-entity-update.component';

describe('Component Tests', () => {
  describe('FieldTestMapstructAndServiceClassEntity Management Update Component', () => {
    let comp: FieldTestMapstructAndServiceClassEntityUpdateComponent;
    let fixture: ComponentFixture<FieldTestMapstructAndServiceClassEntityUpdateComponent>;
    let service: FieldTestMapstructAndServiceClassEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestMapstructAndServiceClassEntityUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(FieldTestMapstructAndServiceClassEntityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestMapstructAndServiceClassEntityUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestMapstructAndServiceClassEntityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FieldTestMapstructAndServiceClassEntity('123');
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
        const entity = new FieldTestMapstructAndServiceClassEntity();
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
