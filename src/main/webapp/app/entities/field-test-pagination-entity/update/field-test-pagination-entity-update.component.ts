import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IFieldTestPaginationEntity, FieldTestPaginationEntity } from '../field-test-pagination-entity.model';
import { FieldTestPaginationEntityService } from '../service/field-test-pagination-entity.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-pagination-entity-update',
  templateUrl: './field-test-pagination-entity-update.component.html',
})
export class FieldTestPaginationEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stringAlice: [],
    stringRequiredAlice: [null, [Validators.required]],
    stringMinlengthAlice: [null, [Validators.minLength(0)]],
    stringMaxlengthAlice: [null, [Validators.maxLength(20)]],
    stringPatternAlice: [null, [Validators.pattern('^[a-zA-Z0-9]*$')]],
    integerAlice: [],
    integerRequiredAlice: [null, [Validators.required]],
    integerMinAlice: [null, [Validators.min(0)]],
    integerMaxAlice: [null, [Validators.max(100)]],
    longAlice: [],
    longRequiredAlice: [null, [Validators.required]],
    longMinAlice: [null, [Validators.min(0)]],
    longMaxAlice: [null, [Validators.max(100)]],
    floatAlice: [],
    floatRequiredAlice: [null, [Validators.required]],
    floatMinAlice: [null, [Validators.min(0)]],
    floatMaxAlice: [null, [Validators.max(100)]],
    doubleRequiredAlice: [null, [Validators.required]],
    doubleMinAlice: [null, [Validators.min(0)]],
    doubleMaxAlice: [null, [Validators.max(100)]],
    bigDecimalRequiredAlice: [null, [Validators.required]],
    bigDecimalMinAlice: [null, [Validators.min(0)]],
    bigDecimalMaxAlice: [null, [Validators.max(100)]],
    localDateAlice: [],
    localDateRequiredAlice: [null, [Validators.required]],
    instantAlice: [],
    instanteRequiredAlice: [null, [Validators.required]],
    zonedDateTimeAlice: [],
    zonedDateTimeRequiredAlice: [null, [Validators.required]],
    durationAlice: [],
    durationRequiredAlice: [null, [Validators.required]],
    booleanAlice: [],
    booleanRequiredAlice: [null, [Validators.required]],
    enumAlice: [],
    enumRequiredAlice: [null, [Validators.required]],
    uuidAlice: [],
    uuidRequiredAlice: [null, [Validators.required]],
    byteImageAlice: [],
    byteImageAliceContentType: [],
    byteImageRequiredAlice: [null, [Validators.required]],
    byteImageRequiredAliceContentType: [],
    byteImageMinbytesAlice: [null, []],
    byteImageMinbytesAliceContentType: [],
    byteImageMaxbytesAlice: [null, []],
    byteImageMaxbytesAliceContentType: [],
    byteAnyAlice: [],
    byteAnyAliceContentType: [],
    byteAnyRequiredAlice: [null, [Validators.required]],
    byteAnyRequiredAliceContentType: [],
    byteAnyMinbytesAlice: [null, []],
    byteAnyMinbytesAliceContentType: [],
    byteAnyMaxbytesAlice: [null, []],
    byteAnyMaxbytesAliceContentType: [],
    byteTextAlice: [],
    byteTextRequiredAlice: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected fieldTestPaginationEntityService: FieldTestPaginationEntityService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldTestPaginationEntity }) => {
      if (fieldTestPaginationEntity.id === undefined) {
        const today = dayjs().startOf('day');
        fieldTestPaginationEntity.instantAlice = today;
        fieldTestPaginationEntity.instanteRequiredAlice = today;
        fieldTestPaginationEntity.zonedDateTimeAlice = today;
        fieldTestPaginationEntity.zonedDateTimeRequiredAlice = today;
      }

      this.updateForm(fieldTestPaginationEntity);
    });
  }

  updateForm(fieldTestPaginationEntity: IFieldTestPaginationEntity): void {
    this.editForm.patchValue({
      id: fieldTestPaginationEntity.id,
      stringAlice: fieldTestPaginationEntity.stringAlice,
      stringRequiredAlice: fieldTestPaginationEntity.stringRequiredAlice,
      stringMinlengthAlice: fieldTestPaginationEntity.stringMinlengthAlice,
      stringMaxlengthAlice: fieldTestPaginationEntity.stringMaxlengthAlice,
      stringPatternAlice: fieldTestPaginationEntity.stringPatternAlice,
      integerAlice: fieldTestPaginationEntity.integerAlice,
      integerRequiredAlice: fieldTestPaginationEntity.integerRequiredAlice,
      integerMinAlice: fieldTestPaginationEntity.integerMinAlice,
      integerMaxAlice: fieldTestPaginationEntity.integerMaxAlice,
      longAlice: fieldTestPaginationEntity.longAlice,
      longRequiredAlice: fieldTestPaginationEntity.longRequiredAlice,
      longMinAlice: fieldTestPaginationEntity.longMinAlice,
      longMaxAlice: fieldTestPaginationEntity.longMaxAlice,
      floatAlice: fieldTestPaginationEntity.floatAlice,
      floatRequiredAlice: fieldTestPaginationEntity.floatRequiredAlice,
      floatMinAlice: fieldTestPaginationEntity.floatMinAlice,
      floatMaxAlice: fieldTestPaginationEntity.floatMaxAlice,
      doubleRequiredAlice: fieldTestPaginationEntity.doubleRequiredAlice,
      doubleMinAlice: fieldTestPaginationEntity.doubleMinAlice,
      doubleMaxAlice: fieldTestPaginationEntity.doubleMaxAlice,
      bigDecimalRequiredAlice: fieldTestPaginationEntity.bigDecimalRequiredAlice,
      bigDecimalMinAlice: fieldTestPaginationEntity.bigDecimalMinAlice,
      bigDecimalMaxAlice: fieldTestPaginationEntity.bigDecimalMaxAlice,
      localDateAlice: fieldTestPaginationEntity.localDateAlice,
      localDateRequiredAlice: fieldTestPaginationEntity.localDateRequiredAlice,
      instantAlice: fieldTestPaginationEntity.instantAlice ? fieldTestPaginationEntity.instantAlice.format(DATE_TIME_FORMAT) : null,
      instanteRequiredAlice: fieldTestPaginationEntity.instanteRequiredAlice
        ? fieldTestPaginationEntity.instanteRequiredAlice.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeAlice: fieldTestPaginationEntity.zonedDateTimeAlice
        ? fieldTestPaginationEntity.zonedDateTimeAlice.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeRequiredAlice: fieldTestPaginationEntity.zonedDateTimeRequiredAlice
        ? fieldTestPaginationEntity.zonedDateTimeRequiredAlice.format(DATE_TIME_FORMAT)
        : null,
      durationAlice: fieldTestPaginationEntity.durationAlice,
      durationRequiredAlice: fieldTestPaginationEntity.durationRequiredAlice,
      booleanAlice: fieldTestPaginationEntity.booleanAlice,
      booleanRequiredAlice: fieldTestPaginationEntity.booleanRequiredAlice,
      enumAlice: fieldTestPaginationEntity.enumAlice,
      enumRequiredAlice: fieldTestPaginationEntity.enumRequiredAlice,
      uuidAlice: fieldTestPaginationEntity.uuidAlice,
      uuidRequiredAlice: fieldTestPaginationEntity.uuidRequiredAlice,
      byteImageAlice: fieldTestPaginationEntity.byteImageAlice,
      byteImageAliceContentType: fieldTestPaginationEntity.byteImageAliceContentType,
      byteImageRequiredAlice: fieldTestPaginationEntity.byteImageRequiredAlice,
      byteImageRequiredAliceContentType: fieldTestPaginationEntity.byteImageRequiredAliceContentType,
      byteImageMinbytesAlice: fieldTestPaginationEntity.byteImageMinbytesAlice,
      byteImageMinbytesAliceContentType: fieldTestPaginationEntity.byteImageMinbytesAliceContentType,
      byteImageMaxbytesAlice: fieldTestPaginationEntity.byteImageMaxbytesAlice,
      byteImageMaxbytesAliceContentType: fieldTestPaginationEntity.byteImageMaxbytesAliceContentType,
      byteAnyAlice: fieldTestPaginationEntity.byteAnyAlice,
      byteAnyAliceContentType: fieldTestPaginationEntity.byteAnyAliceContentType,
      byteAnyRequiredAlice: fieldTestPaginationEntity.byteAnyRequiredAlice,
      byteAnyRequiredAliceContentType: fieldTestPaginationEntity.byteAnyRequiredAliceContentType,
      byteAnyMinbytesAlice: fieldTestPaginationEntity.byteAnyMinbytesAlice,
      byteAnyMinbytesAliceContentType: fieldTestPaginationEntity.byteAnyMinbytesAliceContentType,
      byteAnyMaxbytesAlice: fieldTestPaginationEntity.byteAnyMaxbytesAlice,
      byteAnyMaxbytesAliceContentType: fieldTestPaginationEntity.byteAnyMaxbytesAliceContentType,
      byteTextAlice: fieldTestPaginationEntity.byteTextAlice,
      byteTextRequiredAlice: fieldTestPaginationEntity.byteTextRequiredAlice,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(
          new EventWithContent<AlertError>('sampleCouchbaseNoCacheApp.error', { ...err, key: 'error.file.' + err.key })
        ),
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fieldTestPaginationEntity = this.createFromForm();
    if (fieldTestPaginationEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.fieldTestPaginationEntityService.update(fieldTestPaginationEntity));
    } else {
      this.subscribeToSaveResponse(this.fieldTestPaginationEntityService.create(fieldTestPaginationEntity));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFieldTestPaginationEntity>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  protected createFromForm(): IFieldTestPaginationEntity {
    return {
      ...new FieldTestPaginationEntity(),
      id: this.editForm.get(['id'])!.value,
      stringAlice: this.editForm.get(['stringAlice'])!.value,
      stringRequiredAlice: this.editForm.get(['stringRequiredAlice'])!.value,
      stringMinlengthAlice: this.editForm.get(['stringMinlengthAlice'])!.value,
      stringMaxlengthAlice: this.editForm.get(['stringMaxlengthAlice'])!.value,
      stringPatternAlice: this.editForm.get(['stringPatternAlice'])!.value,
      integerAlice: this.editForm.get(['integerAlice'])!.value,
      integerRequiredAlice: this.editForm.get(['integerRequiredAlice'])!.value,
      integerMinAlice: this.editForm.get(['integerMinAlice'])!.value,
      integerMaxAlice: this.editForm.get(['integerMaxAlice'])!.value,
      longAlice: this.editForm.get(['longAlice'])!.value,
      longRequiredAlice: this.editForm.get(['longRequiredAlice'])!.value,
      longMinAlice: this.editForm.get(['longMinAlice'])!.value,
      longMaxAlice: this.editForm.get(['longMaxAlice'])!.value,
      floatAlice: this.editForm.get(['floatAlice'])!.value,
      floatRequiredAlice: this.editForm.get(['floatRequiredAlice'])!.value,
      floatMinAlice: this.editForm.get(['floatMinAlice'])!.value,
      floatMaxAlice: this.editForm.get(['floatMaxAlice'])!.value,
      doubleRequiredAlice: this.editForm.get(['doubleRequiredAlice'])!.value,
      doubleMinAlice: this.editForm.get(['doubleMinAlice'])!.value,
      doubleMaxAlice: this.editForm.get(['doubleMaxAlice'])!.value,
      bigDecimalRequiredAlice: this.editForm.get(['bigDecimalRequiredAlice'])!.value,
      bigDecimalMinAlice: this.editForm.get(['bigDecimalMinAlice'])!.value,
      bigDecimalMaxAlice: this.editForm.get(['bigDecimalMaxAlice'])!.value,
      localDateAlice: this.editForm.get(['localDateAlice'])!.value,
      localDateRequiredAlice: this.editForm.get(['localDateRequiredAlice'])!.value,
      instantAlice: this.editForm.get(['instantAlice'])!.value
        ? dayjs(this.editForm.get(['instantAlice'])!.value, DATE_TIME_FORMAT)
        : undefined,
      instanteRequiredAlice: this.editForm.get(['instanteRequiredAlice'])!.value
        ? dayjs(this.editForm.get(['instanteRequiredAlice'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeAlice: this.editForm.get(['zonedDateTimeAlice'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeAlice'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeRequiredAlice: this.editForm.get(['zonedDateTimeRequiredAlice'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeRequiredAlice'])!.value, DATE_TIME_FORMAT)
        : undefined,
      durationAlice: this.editForm.get(['durationAlice'])!.value,
      durationRequiredAlice: this.editForm.get(['durationRequiredAlice'])!.value,
      booleanAlice: this.editForm.get(['booleanAlice'])!.value,
      booleanRequiredAlice: this.editForm.get(['booleanRequiredAlice'])!.value,
      enumAlice: this.editForm.get(['enumAlice'])!.value,
      enumRequiredAlice: this.editForm.get(['enumRequiredAlice'])!.value,
      uuidAlice: this.editForm.get(['uuidAlice'])!.value,
      uuidRequiredAlice: this.editForm.get(['uuidRequiredAlice'])!.value,
      byteImageAliceContentType: this.editForm.get(['byteImageAliceContentType'])!.value,
      byteImageAlice: this.editForm.get(['byteImageAlice'])!.value,
      byteImageRequiredAliceContentType: this.editForm.get(['byteImageRequiredAliceContentType'])!.value,
      byteImageRequiredAlice: this.editForm.get(['byteImageRequiredAlice'])!.value,
      byteImageMinbytesAliceContentType: this.editForm.get(['byteImageMinbytesAliceContentType'])!.value,
      byteImageMinbytesAlice: this.editForm.get(['byteImageMinbytesAlice'])!.value,
      byteImageMaxbytesAliceContentType: this.editForm.get(['byteImageMaxbytesAliceContentType'])!.value,
      byteImageMaxbytesAlice: this.editForm.get(['byteImageMaxbytesAlice'])!.value,
      byteAnyAliceContentType: this.editForm.get(['byteAnyAliceContentType'])!.value,
      byteAnyAlice: this.editForm.get(['byteAnyAlice'])!.value,
      byteAnyRequiredAliceContentType: this.editForm.get(['byteAnyRequiredAliceContentType'])!.value,
      byteAnyRequiredAlice: this.editForm.get(['byteAnyRequiredAlice'])!.value,
      byteAnyMinbytesAliceContentType: this.editForm.get(['byteAnyMinbytesAliceContentType'])!.value,
      byteAnyMinbytesAlice: this.editForm.get(['byteAnyMinbytesAlice'])!.value,
      byteAnyMaxbytesAliceContentType: this.editForm.get(['byteAnyMaxbytesAliceContentType'])!.value,
      byteAnyMaxbytesAlice: this.editForm.get(['byteAnyMaxbytesAlice'])!.value,
      byteTextAlice: this.editForm.get(['byteTextAlice'])!.value,
      byteTextRequiredAlice: this.editForm.get(['byteTextRequiredAlice'])!.value,
    };
  }
}
