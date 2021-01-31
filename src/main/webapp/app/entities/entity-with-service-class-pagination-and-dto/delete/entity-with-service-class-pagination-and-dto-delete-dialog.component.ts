import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithServiceClassPaginationAndDTO } from '../entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOService } from '../service/entity-with-service-class-pagination-and-dto.service';

@Component({
  templateUrl: './entity-with-service-class-pagination-and-dto-delete-dialog.component.html',
})
export class EntityWithServiceClassPaginationAndDTODeleteDialogComponent {
  entityWithServiceClassPaginationAndDTO?: IEntityWithServiceClassPaginationAndDTO;

  constructor(
    protected entityWithServiceClassPaginationAndDTOService: EntityWithServiceClassPaginationAndDTOService,
    public activeModal: NgbActiveModal
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.entityWithServiceClassPaginationAndDTOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
