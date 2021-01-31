import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOService } from '../service/entity-with-service-impl-and-dto.service';
import { EntityWithServiceImplAndDTODeleteDialogComponent } from '../delete/entity-with-service-impl-and-dto-delete-dialog.component';

@Component({
  selector: 'jhi-entity-with-service-impl-and-dto',
  templateUrl: './entity-with-service-impl-and-dto.component.html',
})
export class EntityWithServiceImplAndDTOComponent implements OnInit {
  entityWithServiceImplAndDTOS?: IEntityWithServiceImplAndDTO[];
  isLoading = false;

  constructor(protected entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.entityWithServiceImplAndDTOService.query().subscribe(
      (res: HttpResponse<IEntityWithServiceImplAndDTO[]>) => {
        this.isLoading = false;
        this.entityWithServiceImplAndDTOS = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEntityWithServiceImplAndDTO): string {
    return item.id!;
  }

  delete(entityWithServiceImplAndDTO: IEntityWithServiceImplAndDTO): void {
    const modalRef = this.modalService.open(EntityWithServiceImplAndDTODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entityWithServiceImplAndDTO = entityWithServiceImplAndDTO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
