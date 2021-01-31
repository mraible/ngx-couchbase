jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestServiceClassAndJpaFilteringEntityService } from '../service/field-test-service-class-and-jpa-filtering-entity.service';
import { FieldTestServiceClassAndJpaFilteringEntity } from '../field-test-service-class-and-jpa-filtering-entity.model';

import { FieldTestServiceClassAndJpaFilteringEntityUpdateComponent } from './field-test-service-class-and-jpa-filtering-entity-update.component';

describe('Component Tests', () => {
  describe('FieldTestServiceClassAndJpaFilteringEntity Management Update Component', () => {
    let comp: FieldTestServiceClassAndJpaFilteringEntityUpdateComponent;
    let fixture: ComponentFixture<FieldTestServiceClassAndJpaFilteringEntityUpdateComponent>;
    let service: FieldTestServiceClassAndJpaFilteringEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestServiceClassAndJpaFilteringEntityUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(FieldTestServiceClassAndJpaFilteringEntityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestServiceClassAndJpaFilteringEntityUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestServiceClassAndJpaFilteringEntityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FieldTestServiceClassAndJpaFilteringEntity('123');
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
        const entity = new FieldTestServiceClassAndJpaFilteringEntity();
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
