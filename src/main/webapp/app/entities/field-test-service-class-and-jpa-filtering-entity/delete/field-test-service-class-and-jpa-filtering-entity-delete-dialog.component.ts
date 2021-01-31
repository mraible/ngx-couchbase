import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestServiceClassAndJpaFilteringEntity } from '../field-test-service-class-and-jpa-filtering-entity.model';
import { FieldTestServiceClassAndJpaFilteringEntityService } from '../service/field-test-service-class-and-jpa-filtering-entity.service';

@Component({
  templateUrl: './field-test-service-class-and-jpa-filtering-entity-delete-dialog.component.html',
})
export class FieldTestServiceClassAndJpaFilteringEntityDeleteDialogComponent {
  fieldTestServiceClassAndJpaFilteringEntity?: IFieldTestServiceClassAndJpaFilteringEntity;

  constructor(
    protected fieldTestServiceClassAndJpaFilteringEntityService: FieldTestServiceClassAndJpaFilteringEntityService,
    public activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.fieldTestServiceClassAndJpaFilteringEntityService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
