jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestPaginationEntityService } from '../service/field-test-pagination-entity.service';
import { FieldTestPaginationEntity } from '../field-test-pagination-entity.model';

import { FieldTestPaginationEntityUpdateComponent } from './field-test-pagination-entity-update.component';

describe('Component Tests', () => {
  describe('FieldTestPaginationEntity Management Update Component', () => {
    let comp: FieldTestPaginationEntityUpdateComponent;
    let fixture: ComponentFixture<FieldTestPaginationEntityUpdateComponent>;
    let service: FieldTestPaginationEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestPaginationEntityUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(FieldTestPaginationEntityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestPaginationEntityUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestPaginationEntityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FieldTestPaginationEntity('123');
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
        const entity = new FieldTestPaginationEntity();
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
