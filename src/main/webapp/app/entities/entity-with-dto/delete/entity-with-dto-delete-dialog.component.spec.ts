jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { EntityWithDTOService } from '../service/entity-with-dto.service';

import { EntityWithDTODeleteDialogComponent } from './entity-with-dto-delete-dialog.component';

describe('Component Tests', () => {
  describe('EntityWithDTO Management Delete Component', () => {
    let comp: EntityWithDTODeleteDialogComponent;
    let fixture: ComponentFixture<EntityWithDTODeleteDialogComponent>;
    let service: EntityWithDTOService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithDTODeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(EntityWithDTODeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityWithDTODeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithDTOService);
      mockActiveModal = TestBed.inject(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.close).not.toHaveBeenCalled();
        expect(mockActiveModal.dismiss).toHaveBeenCalled();
      });
    });
  });
});
