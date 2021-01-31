import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IFieldTestEntity, FieldTestEntity } from '../field-test-entity.model';
import { FieldTestEntityService } from '../service/field-test-entity.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-entity-update',
  templateUrl: './field-test-entity-update.component.html',
})
export class FieldTestEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stringTom: [],
    stringRequiredTom: [null, [Validators.required]],
    stringMinlengthTom: [null, [Validators.minLength(0)]],
    stringMaxlengthTom: [null, [Validators.maxLength(20)]],
    stringPatternTom: [null, [Validators.pattern('^[a-zA-Z0-9]*$')]],
    numberPatternTom: [null, [Validators.pattern('^[0-9]+$')]],
    numberPatternRequiredTom: [null, [Validators.required, Validators.pattern('^[0-9]+$')]],
    integerTom: [],
    integerRequiredTom: [null, [Validators.required]],
    integerMinTom: [null, [Validators.min(0)]],
    integerMaxTom: [null, [Validators.max(100)]],
    longTom: [],
    longRequiredTom: [null, [Validators.required]],
    longMinTom: [null, [Validators.min(0)]],
    longMaxTom: [null, [Validators.max(100)]],
    floatTom: [],
    floatRequiredTom: [null, [Validators.required]],
    floatMinTom: [null, [Validators.min(0)]],
    floatMaxTom: [null, [Validators.max(100)]],
    doubleRequiredTom: [null, [Validators.required]],
    doubleMinTom: [null, [Validators.min(0)]],
    doubleMaxTom: [null, [Validators.max(100)]],
    bigDecimalRequiredTom: [null, [Validators.required]],
    bigDecimalMinTom: [null, [Validators.min(0)]],
    bigDecimalMaxTom: [null, [Validators.max(100)]],
    localDateTom: [],
    localDateRequiredTom: [null, [Validators.required]],
    instantTom: [],
    instantRequiredTom: [null, [Validators.required]],
    zonedDateTimeTom: [],
    zonedDateTimeRequiredTom: [null, [Validators.required]],
    durationTom: [],
    durationRequiredTom: [null, [Validators.required]],
    booleanTom: [],
    booleanRequiredTom: [null, [Validators.required]],
    enumTom: [],
    enumRequiredTom: [null, [Validators.required]],
    uuidTom: [],
    uuidRequiredTom: [null, [Validators.required]],
    byteImageTom: [],
    byteImageTomContentType: [],
    byteImageRequiredTom: [null, [Validators.required]],
    byteImageRequiredTomContentType: [],
    byteImageMinbytesTom: [null, []],
    byteImageMinbytesTomContentType: [],
    byteImageMaxbytesTom: [null, []],
    byteImageMaxbytesTomContentType: [],
    byteAnyTom: [],
    byteAnyTomContentType: [],
    byteAnyRequiredTom: [null, [Validators.required]],
    byteAnyRequiredTomContentType: [],
    byteAnyMinbytesTom: [null, []],
    byteAnyMinbytesTomContentType: [],
    byteAnyMaxbytesTom: [null, []],
    byteAnyMaxbytesTomContentType: [],
    byteTextTom: [],
    byteTextRequiredTom: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected fieldTestEntityService: FieldTestEntityService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldTestEntity }) => {
      if (fieldTestEntity.id === undefined) {
        const today = dayjs().startOf('day');
        fieldTestEntity.instantTom = today;
        fieldTestEntity.instantRequiredTom = today;
        fieldTestEntity.zonedDateTimeTom = today;
        fieldTestEntity.zonedDateTimeRequiredTom = today;
      }

      this.updateForm(fieldTestEntity);
    });
  }

  updateForm(fieldTestEntity: IFieldTestEntity): void {
    this.editForm.patchValue({
      id: fieldTestEntity.id,
      stringTom: fieldTestEntity.stringTom,
      stringRequiredTom: fieldTestEntity.stringRequiredTom,
      stringMinlengthTom: fieldTestEntity.stringMinlengthTom,
      stringMaxlengthTom: fieldTestEntity.stringMaxlengthTom,
      stringPatternTom: fieldTestEntity.stringPatternTom,
      numberPatternTom: fieldTestEntity.numberPatternTom,
      numberPatternRequiredTom: fieldTestEntity.numberPatternRequiredTom,
      integerTom: fieldTestEntity.integerTom,
      integerRequiredTom: fieldTestEntity.integerRequiredTom,
      integerMinTom: fieldTestEntity.integerMinTom,
      integerMaxTom: fieldTestEntity.integerMaxTom,
      longTom: fieldTestEntity.longTom,
      longRequiredTom: fieldTestEntity.longRequiredTom,
      longMinTom: fieldTestEntity.longMinTom,
      longMaxTom: fieldTestEntity.longMaxTom,
      floatTom: fieldTestEntity.floatTom,
      floatRequiredTom: fieldTestEntity.floatRequiredTom,
      floatMinTom: fieldTestEntity.floatMinTom,
      floatMaxTom: fieldTestEntity.floatMaxTom,
      doubleRequiredTom: fieldTestEntity.doubleRequiredTom,
      doubleMinTom: fieldTestEntity.doubleMinTom,
      doubleMaxTom: fieldTestEntity.doubleMaxTom,
      bigDecimalRequiredTom: fieldTestEntity.bigDecimalRequiredTom,
      bigDecimalMinTom: fieldTestEntity.bigDecimalMinTom,
      bigDecimalMaxTom: fieldTestEntity.bigDecimalMaxTom,
      localDateTom: fieldTestEntity.localDateTom,
      localDateRequiredTom: fieldTestEntity.localDateRequiredTom,
      instantTom: fieldTestEntity.instantTom ? fieldTestEntity.instantTom.format(DATE_TIME_FORMAT) : null,
      instantRequiredTom: fieldTestEntity.instantRequiredTom ? fieldTestEntity.instantRequiredTom.format(DATE_TIME_FORMAT) : null,
      zonedDateTimeTom: fieldTestEntity.zonedDateTimeTom ? fieldTestEntity.zonedDateTimeTom.format(DATE_TIME_FORMAT) : null,
      zonedDateTimeRequiredTom: fieldTestEntity.zonedDateTimeRequiredTom
        ? fieldTestEntity.zonedDateTimeRequiredTom.format(DATE_TIME_FORMAT)
        : null,
      durationTom: fieldTestEntity.durationTom,
      durationRequiredTom: fieldTestEntity.durationRequiredTom,
      booleanTom: fieldTestEntity.booleanTom,
      booleanRequiredTom: fieldTestEntity.booleanRequiredTom,
      enumTom: fieldTestEntity.enumTom,
      enumRequiredTom: fieldTestEntity.enumRequiredTom,
      uuidTom: fieldTestEntity.uuidTom,
      uuidRequiredTom: fieldTestEntity.uuidRequiredTom,
      byteImageTom: fieldTestEntity.byteImageTom,
      byteImageTomContentType: fieldTestEntity.byteImageTomContentType,
      byteImageRequiredTom: fieldTestEntity.byteImageRequiredTom,
      byteImageRequiredTomContentType: fieldTestEntity.byteImageRequiredTomContentType,
      byteImageMinbytesTom: fieldTestEntity.byteImageMinbytesTom,
      byteImageMinbytesTomContentType: fieldTestEntity.byteImageMinbytesTomContentType,
      byteImageMaxbytesTom: fieldTestEntity.byteImageMaxbytesTom,
      byteImageMaxbytesTomContentType: fieldTestEntity.byteImageMaxbytesTomContentType,
      byteAnyTom: fieldTestEntity.byteAnyTom,
      byteAnyTomContentType: fieldTestEntity.byteAnyTomContentType,
      byteAnyRequiredTom: fieldTestEntity.byteAnyRequiredTom,
      byteAnyRequiredTomContentType: fieldTestEntity.byteAnyRequiredTomContentType,
      byteAnyMinbytesTom: fieldTestEntity.byteAnyMinbytesTom,
      byteAnyMinbytesTomContentType: fieldTestEntity.byteAnyMinbytesTomContentType,
      byteAnyMaxbytesTom: fieldTestEntity.byteAnyMaxbytesTom,
      byteAnyMaxbytesTomContentType: fieldTestEntity.byteAnyMaxbytesTomContentType,
      byteTextTom: fieldTestEntity.byteTextTom,
      byteTextRequiredTom: fieldTestEntity.byteTextRequiredTom,
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
    const fieldTestEntity = this.createFromForm();
    if (fieldTestEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.fieldTestEntityService.update(fieldTestEntity));
    } else {
      this.subscribeToSaveResponse(this.fieldTestEntityService.create(fieldTestEntity));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFieldTestEntity>>): void {
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

  protected createFromForm(): IFieldTestEntity {
    return {
      ...new FieldTestEntity(),
      id: this.editForm.get(['id'])!.value,
      stringTom: this.editForm.get(['stringTom'])!.value,
      stringRequiredTom: this.editForm.get(['stringRequiredTom'])!.value,
      stringMinlengthTom: this.editForm.get(['stringMinlengthTom'])!.value,
      stringMaxlengthTom: this.editForm.get(['stringMaxlengthTom'])!.value,
      stringPatternTom: this.editForm.get(['stringPatternTom'])!.value,
      numberPatternTom: this.editForm.get(['numberPatternTom'])!.value,
      numberPatternRequiredTom: this.editForm.get(['numberPatternRequiredTom'])!.value,
      integerTom: this.editForm.get(['integerTom'])!.value,
      integerRequiredTom: this.editForm.get(['integerRequiredTom'])!.value,
      integerMinTom: this.editForm.get(['integerMinTom'])!.value,
      integerMaxTom: this.editForm.get(['integerMaxTom'])!.value,
      longTom: this.editForm.get(['longTom'])!.value,
      longRequiredTom: this.editForm.get(['longRequiredTom'])!.value,
      longMinTom: this.editForm.get(['longMinTom'])!.value,
      longMaxTom: this.editForm.get(['longMaxTom'])!.value,
      floatTom: this.editForm.get(['floatTom'])!.value,
      floatRequiredTom: this.editForm.get(['floatRequiredTom'])!.value,
      floatMinTom: this.editForm.get(['floatMinTom'])!.value,
      floatMaxTom: this.editForm.get(['floatMaxTom'])!.value,
      doubleRequiredTom: this.editForm.get(['doubleRequiredTom'])!.value,
      doubleMinTom: this.editForm.get(['doubleMinTom'])!.value,
      doubleMaxTom: this.editForm.get(['doubleMaxTom'])!.value,
      bigDecimalRequiredTom: this.editForm.get(['bigDecimalRequiredTom'])!.value,
      bigDecimalMinTom: this.editForm.get(['bigDecimalMinTom'])!.value,
      bigDecimalMaxTom: this.editForm.get(['bigDecimalMaxTom'])!.value,
      localDateTom: this.editForm.get(['localDateTom'])!.value,
      localDateRequiredTom: this.editForm.get(['localDateRequiredTom'])!.value,
      instantTom: this.editForm.get(['instantTom'])!.value ? dayjs(this.editForm.get(['instantTom'])!.value, DATE_TIME_FORMAT) : undefined,
      instantRequiredTom: this.editForm.get(['instantRequiredTom'])!.value
        ? dayjs(this.editForm.get(['instantRequiredTom'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeTom: this.editForm.get(['zonedDateTimeTom'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeTom'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeRequiredTom: this.editForm.get(['zonedDateTimeRequiredTom'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeRequiredTom'])!.value, DATE_TIME_FORMAT)
        : undefined,
      durationTom: this.editForm.get(['durationTom'])!.value,
      durationRequiredTom: this.editForm.get(['durationRequiredTom'])!.value,
      booleanTom: this.editForm.get(['booleanTom'])!.value,
      booleanRequiredTom: this.editForm.get(['booleanRequiredTom'])!.value,
      enumTom: this.editForm.get(['enumTom'])!.value,
      enumRequiredTom: this.editForm.get(['enumRequiredTom'])!.value,
      uuidTom: this.editForm.get(['uuidTom'])!.value,
      uuidRequiredTom: this.editForm.get(['uuidRequiredTom'])!.value,
      byteImageTomContentType: this.editForm.get(['byteImageTomContentType'])!.value,
      byteImageTom: this.editForm.get(['byteImageTom'])!.value,
      byteImageRequiredTomContentType: this.editForm.get(['byteImageRequiredTomContentType'])!.value,
      byteImageRequiredTom: this.editForm.get(['byteImageRequiredTom'])!.value,
      byteImageMinbytesTomContentType: this.editForm.get(['byteImageMinbytesTomContentType'])!.value,
      byteImageMinbytesTom: this.editForm.get(['byteImageMinbytesTom'])!.value,
      byteImageMaxbytesTomContentType: this.editForm.get(['byteImageMaxbytesTomContentType'])!.value,
      byteImageMaxbytesTom: this.editForm.get(['byteImageMaxbytesTom'])!.value,
      byteAnyTomContentType: this.editForm.get(['byteAnyTomContentType'])!.value,
      byteAnyTom: this.editForm.get(['byteAnyTom'])!.value,
      byteAnyRequiredTomContentType: this.editForm.get(['byteAnyRequiredTomContentType'])!.value,
      byteAnyRequiredTom: this.editForm.get(['byteAnyRequiredTom'])!.value,
      byteAnyMinbytesTomContentType: this.editForm.get(['byteAnyMinbytesTomContentType'])!.value,
      byteAnyMinbytesTom: this.editForm.get(['byteAnyMinbytesTom'])!.value,
      byteAnyMaxbytesTomContentType: this.editForm.get(['byteAnyMaxbytesTomContentType'])!.value,
      byteAnyMaxbytesTom: this.editForm.get(['byteAnyMaxbytesTom'])!.value,
      byteTextTom: this.editForm.get(['byteTextTom'])!.value,
      byteTextRequiredTom: this.editForm.get(['byteTextRequiredTom'])!.value,
    };
  }
}
