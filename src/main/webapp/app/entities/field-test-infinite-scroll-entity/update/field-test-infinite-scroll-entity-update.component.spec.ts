jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestInfiniteScrollEntityService } from '../service/field-test-infinite-scroll-entity.service';
import { FieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';

import { FieldTestInfiniteScrollEntityUpdateComponent } from './field-test-infinite-scroll-entity-update.component';

describe('Component Tests', () => {
  describe('FieldTestInfiniteScrollEntity Management Update Component', () => {
    let comp: FieldTestInfiniteScrollEntityUpdateComponent;
    let fixture: ComponentFixture<FieldTestInfiniteScrollEntityUpdateComponent>;
    let service: FieldTestInfiniteScrollEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestInfiniteScrollEntityUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(FieldTestInfiniteScrollEntityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestInfiniteScrollEntityUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestInfiniteScrollEntityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FieldTestInfiniteScrollEntity('123');
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
        const entity = new FieldTestInfiniteScrollEntity();
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
