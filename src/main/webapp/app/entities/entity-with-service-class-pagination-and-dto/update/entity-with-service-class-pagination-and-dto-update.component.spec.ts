jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceClassPaginationAndDTOService } from '../service/entity-with-service-class-pagination-and-dto.service';
import { EntityWithServiceClassPaginationAndDTO } from '../entity-with-service-class-pagination-and-dto.model';

import { EntityWithServiceClassPaginationAndDTOUpdateComponent } from './entity-with-service-class-pagination-and-dto-update.component';

describe('Component Tests', () => {
  describe('EntityWithServiceClassPaginationAndDTO Management Update Component', () => {
    let comp: EntityWithServiceClassPaginationAndDTOUpdateComponent;
    let fixture: ComponentFixture<EntityWithServiceClassPaginationAndDTOUpdateComponent>;
    let service: EntityWithServiceClassPaginationAndDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithServiceClassPaginationAndDTOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EntityWithServiceClassPaginationAndDTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithServiceClassPaginationAndDTOUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithServiceClassPaginationAndDTOService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithServiceClassPaginationAndDTO('123');
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
        const entity = new EntityWithServiceClassPaginationAndDTO();
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
