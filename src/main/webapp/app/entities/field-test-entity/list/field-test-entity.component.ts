import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestEntity } from '../field-test-entity.model';
import { FieldTestEntityService } from '../service/field-test-entity.service';
import { FieldTestEntityDeleteDialogComponent } from '../delete/field-test-entity-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-entity',
  templateUrl: './field-test-entity.component.html',
})
export class FieldTestEntityComponent implements OnInit {
  fieldTestEntities?: IFieldTestEntity[];
  isLoading = false;

  constructor(protected fieldTestEntityService: FieldTestEntityService, protected dataUtils: DataUtils, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.fieldTestEntityService.query().subscribe(
      (res: HttpResponse<IFieldTestEntity[]>) => {
        this.isLoading = false;
        this.fieldTestEntities = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IFieldTestEntity): string {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    return this.dataUtils.openFile(base64String, contentType);
  }

  delete(fieldTestEntity: IFieldTestEntity): void {
    const modalRef = this.modalService.open(FieldTestEntityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fieldTestEntity = fieldTestEntity;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
