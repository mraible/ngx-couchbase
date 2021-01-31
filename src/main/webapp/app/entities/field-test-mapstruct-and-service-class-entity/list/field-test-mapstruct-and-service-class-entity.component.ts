import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestMapstructAndServiceClassEntity } from '../field-test-mapstruct-and-service-class-entity.model';
import { FieldTestMapstructAndServiceClassEntityService } from '../service/field-test-mapstruct-and-service-class-entity.service';
import { FieldTestMapstructAndServiceClassEntityDeleteDialogComponent } from '../delete/field-test-mapstruct-and-service-class-entity-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-mapstruct-and-service-class-entity',
  templateUrl: './field-test-mapstruct-and-service-class-entity.component.html',
})
export class FieldTestMapstructAndServiceClassEntityComponent implements OnInit {
  fieldTestMapstructAndServiceClassEntities?: IFieldTestMapstructAndServiceClassEntity[];
  isLoading = false;

  constructor(
    protected fieldTestMapstructAndServiceClassEntityService: FieldTestMapstructAndServiceClassEntityService,
    protected dataUtils: DataUtils,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.isLoading = true;

    this.fieldTestMapstructAndServiceClassEntityService.query().subscribe(
      (res: HttpResponse<IFieldTestMapstructAndServiceClassEntity[]>) => {
        this.isLoading = false;
        this.fieldTestMapstructAndServiceClassEntities = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IFieldTestMapstructAndServiceClassEntity): string {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    return this.dataUtils.openFile(base64String, contentType);
  }

  delete(fieldTestMapstructAndServiceClassEntity: IFieldTestMapstructAndServiceClassEntity): void {
    const modalRef = this.modalService.open(FieldTestMapstructAndServiceClassEntityDeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static',
    });
    modalRef.componentInstance.fieldTestMapstructAndServiceClassEntity = fieldTestMapstructAndServiceClassEntity;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
