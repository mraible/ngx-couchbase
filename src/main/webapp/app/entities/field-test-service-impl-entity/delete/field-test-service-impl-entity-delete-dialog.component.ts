import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityService } from '../service/field-test-service-impl-entity.service';

@Component({
  templateUrl: './field-test-service-impl-entity-delete-dialog.component.html',
})
export class FieldTestServiceImplEntityDeleteDialogComponent {
  fieldTestServiceImplEntity?: IFieldTestServiceImplEntity;

  constructor(protected fieldTestServiceImplEntityService: FieldTestServiceImplEntityService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.fieldTestServiceImplEntityService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
