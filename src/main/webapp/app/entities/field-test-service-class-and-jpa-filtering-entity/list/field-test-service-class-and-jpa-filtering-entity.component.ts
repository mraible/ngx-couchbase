import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestServiceClassAndJpaFilteringEntity } from '../field-test-service-class-and-jpa-filtering-entity.model';
import { FieldTestServiceClassAndJpaFilteringEntityService } from '../service/field-test-service-class-and-jpa-filtering-entity.service';
import { FieldTestServiceClassAndJpaFilteringEntityDeleteDialogComponent } from '../delete/field-test-service-class-and-jpa-filtering-entity-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-service-class-and-jpa-filtering-entity',
  templateUrl: './field-test-service-class-and-jpa-filtering-entity.component.html',
})
export class FieldTestServiceClassAndJpaFilteringEntityComponent implements OnInit {
  fieldTestServiceClassAndJpaFilteringEntities?: IFieldTestServiceClassAndJpaFilteringEntity[];
  isLoading = false;

  constructor(
    protected fieldTestServiceClassAndJpaFilteringEntityService: FieldTestServiceClassAndJpaFilteringEntityService,
    protected dataUtils: DataUtils,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.isLoading = true;

    this.fieldTestServiceClassAndJpaFilteringEntityService.query().subscribe(
      (res: HttpResponse<IFieldTestServiceClassAndJpaFilteringEntity[]>) => {
        this.isLoading = false;
        this.fieldTestServiceClassAndJpaFilteringEntities = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IFieldTestServiceClassAndJpaFilteringEntity): string {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    return this.dataUtils.openFile(base64String, contentType);
  }

  delete(fieldTestServiceClassAndJpaFilteringEntity: IFieldTestServiceClassAndJpaFilteringEntity): void {
    const modalRef = this.modalService.open(FieldTestServiceClassAndJpaFilteringEntityDeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static',
    });
    modalRef.componentInstance.fieldTestServiceClassAndJpaFilteringEntity = fieldTestServiceClassAndJpaFilteringEntity;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
