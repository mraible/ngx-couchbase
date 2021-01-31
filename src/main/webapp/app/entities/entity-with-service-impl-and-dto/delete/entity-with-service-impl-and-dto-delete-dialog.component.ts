import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOService } from '../service/entity-with-service-impl-and-dto.service';

@Component({
  templateUrl: './entity-with-service-impl-and-dto-delete-dialog.component.html',
})
export class EntityWithServiceImplAndDTODeleteDialogComponent {
  entityWithServiceImplAndDTO?: IEntityWithServiceImplAndDTO;

  constructor(protected entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.entityWithServiceImplAndDTOService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
