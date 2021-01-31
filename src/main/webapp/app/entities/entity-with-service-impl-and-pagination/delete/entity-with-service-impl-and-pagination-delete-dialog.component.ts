import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithServiceImplAndPagination } from '../entity-with-service-impl-and-pagination.model';
import { EntityWithServiceImplAndPaginationService } from '../service/entity-with-service-impl-and-pagination.service';

@Component({
  templateUrl: './entity-with-service-impl-and-pagination-delete-dialog.component.html',
})
export class EntityWithServiceImplAndPaginationDeleteDialogComponent {
  entityWithServiceImplAndPagination?: IEntityWithServiceImplAndPagination;

  constructor(
    protected entityWithServiceImplAndPaginationService: EntityWithServiceImplAndPaginationService,
    public activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.entityWithServiceImplAndPaginationService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
