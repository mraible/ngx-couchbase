jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithServiceImplPaginationAndDTOService } from '../service/entity-with-service-impl-pagination-and-dto.service';
import { EntityWithServiceImplPaginationAndDTO } from '../entity-with-service-impl-pagination-and-dto.model';

import { EntityWithServiceImplPaginationAndDTOUpdateComponent } from './entity-with-service-impl-pagination-and-dto-update.component';

describe('Component Tests', () => {
  describe('EntityWithServiceImplPaginationAndDTO Management Update Component', () => {
    let comp: EntityWithServiceImplPaginationAndDTOUpdateComponent;
    let fixture: ComponentFixture<EntityWithServiceImplPaginationAndDTOUpdateComponent>;
    let service: EntityWithServiceImplPaginationAndDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithServiceImplPaginationAndDTOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EntityWithServiceImplPaginationAndDTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithServiceImplPaginationAndDTOUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithServiceImplPaginationAndDTOService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithServiceImplPaginationAndDTO('123');
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
        const entity = new EntityWithServiceImplPaginationAndDTO();
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
