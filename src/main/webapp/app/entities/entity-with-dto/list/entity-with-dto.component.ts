import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithDTO } from '../entity-with-dto.model';
import { EntityWithDTOService } from '../service/entity-with-dto.service';
import { EntityWithDTODeleteDialogComponent } from '../delete/entity-with-dto-delete-dialog.component';

@Component({
  selector: 'jhi-entity-with-dto',
  templateUrl: './entity-with-dto.component.html',
})
export class EntityWithDTOComponent implements OnInit {
  entityWithDTOS?: IEntityWithDTO[];
  isLoading = false;

  constructor(protected entityWithDTOService: EntityWithDTOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.entityWithDTOService.query().subscribe(
      (res: HttpResponse<IEntityWithDTO[]>) => {
        this.isLoading = false;
        this.entityWithDTOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEntityWithDTO): string {
    return item.id!;
  }

  delete(entityWithDTO: IEntityWithDTO): void {
    const modalRef = this.modalService.open(EntityWithDTODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entityWithDTO = entityWithDTO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
