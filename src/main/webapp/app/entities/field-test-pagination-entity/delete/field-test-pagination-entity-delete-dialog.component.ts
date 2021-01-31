import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestPaginationEntity } from '../field-test-pagination-entity.model';
import { FieldTestPaginationEntityService } from '../service/field-test-pagination-entity.service';

@Component({
  templateUrl: './field-test-pagination-entity-delete-dialog.component.html',
})
export class FieldTestPaginationEntityDeleteDialogComponent {
  fieldTestPaginationEntity?: IFieldTestPaginationEntity;

  constructor(protected fieldTestPaginationEntityService: FieldTestPaginationEntityService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.fieldTestPaginationEntityService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
