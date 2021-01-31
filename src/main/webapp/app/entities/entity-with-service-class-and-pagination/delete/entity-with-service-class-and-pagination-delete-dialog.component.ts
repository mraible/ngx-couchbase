import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithServiceClassAndPagination } from '../entity-with-service-class-and-pagination.model';
import { EntityWithServiceClassAndPaginationService } from '../service/entity-with-service-class-and-pagination.service';

@Component({
  templateUrl: './entity-with-service-class-and-pagination-delete-dialog.component.html',
})
export class EntityWithServiceClassAndPaginationDeleteDialogComponent {
  entityWithServiceClassAndPagination?: IEntityWithServiceClassAndPagination;

  constructor(
    protected entityWithServiceClassAndPaginationService: EntityWithServiceClassAndPaginationService,
    public activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.entityWithServiceClassAndPaginationService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
