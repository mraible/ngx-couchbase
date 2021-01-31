jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceClassAndPaginationService } from '../service/entity-with-service-class-and-pagination.service';
import { EntityWithServiceClassAndPagination } from '../entity-with-service-class-and-pagination.model';

import { EntityWithServiceClassAndPaginationUpdateComponent } from './entity-with-service-class-and-pagination-update.component';

describe('Component Tests', () => {
  describe('EntityWithServiceClassAndPagination Management Update Component', () => {
    let comp: EntityWithServiceClassAndPaginationUpdateComponent;
    let fixture: ComponentFixture<EntityWithServiceClassAndPaginationUpdateComponent>;
    let service: EntityWithServiceClassAndPaginationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithServiceClassAndPaginationUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EntityWithServiceClassAndPaginationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithServiceClassAndPaginationUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithServiceClassAndPaginationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithServiceClassAndPagination('123');
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
        const entity = new EntityWithServiceClassAndPagination();
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
