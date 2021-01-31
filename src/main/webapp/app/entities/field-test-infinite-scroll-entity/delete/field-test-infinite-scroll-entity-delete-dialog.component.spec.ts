jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { FieldTestInfiniteScrollEntityService } from '../service/field-test-infinite-scroll-entity.service';

import { FieldTestInfiniteScrollEntityDeleteDialogComponent } from './field-test-infinite-scroll-entity-delete-dialog.component';

describe('Component Tests', () => {
  describe('FieldTestInfiniteScrollEntity Management Delete Component', () => {
    let comp: FieldTestInfiniteScrollEntityDeleteDialogComponent;
    let fixture: ComponentFixture<FieldTestInfiniteScrollEntityDeleteDialogComponent>;
    let service: FieldTestInfiniteScrollEntityService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestInfiniteScrollEntityDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(FieldTestInfiniteScrollEntityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FieldTestInfiniteScrollEntityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestInfiniteScrollEntityService);
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
