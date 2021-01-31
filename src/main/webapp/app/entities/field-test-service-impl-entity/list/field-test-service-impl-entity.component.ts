import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityService } from '../service/field-test-service-impl-entity.service';
import { FieldTestServiceImplEntityDeleteDialogComponent } from '../delete/field-test-service-impl-entity-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-service-impl-entity',
  templateUrl: './field-test-service-impl-entity.component.html',
})
export class FieldTestServiceImplEntityComponent implements OnInit {
  fieldTestServiceImplEntities?: IFieldTestServiceImplEntity[];
  isLoading = false;

  constructor(
    protected fieldTestServiceImplEntityService: FieldTestServiceImplEntityService,
    protected dataUtils: DataUtils,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.isLoading = true;

    this.fieldTestServiceImplEntityService.query().subscribe(
      (res: HttpResponse<IFieldTestServiceImplEntity[]>) => {
        this.isLoading = false;
        this.fieldTestServiceImplEntities = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IFieldTestServiceImplEntity): string {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    return this.dataUtils.openFile(base64String, contentType);
  }

  delete(fieldTestServiceImplEntity: IFieldTestServiceImplEntity): void {
    const modalRef = this.modalService.open(FieldTestServiceImplEntityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fieldTestServiceImplEntity = fieldTestServiceImplEntity;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
