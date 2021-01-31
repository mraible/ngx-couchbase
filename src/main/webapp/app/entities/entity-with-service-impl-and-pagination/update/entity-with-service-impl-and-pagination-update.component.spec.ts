jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceImplAndPaginationService } from '../service/entity-with-service-impl-and-pagination.service';
import { EntityWithServiceImplAndPagination } from '../entity-with-service-impl-and-pagination.model';

import { EntityWithServiceImplAndPaginationUpdateComponent } from './entity-with-service-impl-and-pagination-update.component';

describe('Component Tests', () => {
  describe('EntityWithServiceImplAndPagination Management Update Component', () => {
    let comp: EntityWithServiceImplAndPaginationUpdateComponent;
    let fixture: ComponentFixture<EntityWithServiceImplAndPaginationUpdateComponent>;
    let service: EntityWithServiceImplAndPaginationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithServiceImplAndPaginationUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EntityWithServiceImplAndPaginationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithServiceImplAndPaginationUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithServiceImplAndPaginationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithServiceImplAndPagination('123');
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
        const entity = new EntityWithServiceImplAndPagination();
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
