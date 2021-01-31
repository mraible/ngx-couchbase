import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOService } from '../service/entity-with-pagination-and-dto.service';

@Component({
  templateUrl: './entity-with-pagination-and-dto-delete-dialog.component.html',
})
export class EntityWithPaginationAndDTODeleteDialogComponent {
  entityWithPaginationAndDTO?: IEntityWithPaginationAndDTO;

  constructor(protected entityWithPaginationAndDTOService: EntityWithPaginationAndDTOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.entityWithPaginationAndDTOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
