import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestMapstructAndServiceClassEntity } from '../field-test-mapstruct-and-service-class-entity.model';
import { FieldTestMapstructAndServiceClassEntityService } from '../service/field-test-mapstruct-and-service-class-entity.service';

@Component({
  templateUrl: './field-test-mapstruct-and-service-class-entity-delete-dialog.component.html',
})
export class FieldTestMapstructAndServiceClassEntityDeleteDialogComponent {
  fieldTestMapstructAndServiceClassEntity?: IFieldTestMapstructAndServiceClassEntity;

  constructor(
    protected fieldTestMapstructAndServiceClassEntityService: FieldTestMapstructAndServiceClassEntityService,
    public activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.fieldTestMapstructAndServiceClassEntityService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
