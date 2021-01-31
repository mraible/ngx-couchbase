import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IFieldTestServiceImplEntity, FieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';
import { FieldTestServiceImplEntityService } from '../service/field-test-service-impl-entity.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-service-impl-entity-update',
  templateUrl: './field-test-service-impl-entity-update.component.html',
})
export class FieldTestServiceImplEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stringMika: [],
    stringRequiredMika: [null, [Validators.required]],
    stringMinlengthMika: [null, [Validators.minLength(0)]],
    stringMaxlengthMika: [null, [Validators.maxLength(20)]],
    stringPatternMika: [null, [Validators.pattern('^[a-zA-Z0-9]*$')]],
    integerMika: [],
    integerRequiredMika: [null, [Validators.required]],
    integerMinMika: [null, [Validators.min(0)]],
    integerMaxMika: [null, [Validators.max(100)]],
    longMika: [],
    longRequiredMika: [null, [Validators.required]],
    longMinMika: [null, [Validators.min(0)]],
    longMaxMika: [null, [Validators.max(100)]],
    floatMika: [],
    floatRequiredMika: [null, [Validators.required]],
    floatMinMika: [null, [Validators.min(0)]],
    floatMaxMika: [null, [Validators.max(100)]],
    doubleRequiredMika: [null, [Validators.required]],
    doubleMinMika: [null, [Validators.min(0)]],
    doubleMaxMika: [null, [Validators.max(100)]],
    bigDecimalRequiredMika: [null, [Validators.required]],
    bigDecimalMinMika: [null, [Validators.min(0)]],
    bigDecimalMaxMika: [null, [Validators.max(100)]],
    localDateMika: [],
    localDateRequiredMika: [null, [Validators.required]],
    instantMika: [],
    instanteRequiredMika: [null, [Validators.required]],
    zonedDateTimeMika: [],
    zonedDateTimeRequiredMika: [null, [Validators.required]],
    durationMika: [],
    durationRequiredMika: [null, [Validators.required]],
    booleanMika: [],
    booleanRequiredMika: [null, [Validators.required]],
    enumMika: [],
    enumRequiredMika: [null, [Validators.required]],
    uuidMika: [],
    uuidRequiredMika: [null, [Validators.required]],
    byteImageMika: [],
    byteImageMikaContentType: [],
    byteImageRequiredMika: [null, [Validators.required]],
    byteImageRequiredMikaContentType: [],
    byteImageMinbytesMika: [null, []],
    byteImageMinbytesMikaContentType: [],
    byteImageMaxbytesMika: [null, []],
    byteImageMaxbytesMikaContentType: [],
    byteAnyMika: [],
    byteAnyMikaContentType: [],
    byteAnyRequiredMika: [null, [Validators.required]],
    byteAnyRequiredMikaContentType: [],
    byteAnyMinbytesMika: [null, []],
    byteAnyMinbytesMikaContentType: [],
    byteAnyMaxbytesMika: [null, []],
    byteAnyMaxbytesMikaContentType: [],
    byteTextMika: [],
    byteTextRequiredMika: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected fieldTestServiceImplEntityService: FieldTestServiceImplEntityService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldTestServiceImplEntity }) => {
      if (fieldTestServiceImplEntity.id === undefined) {
        const today = dayjs().startOf('day');
        fieldTestServiceImplEntity.instantMika = today;
        fieldTestServiceImplEntity.instanteRequiredMika = today;
        fieldTestServiceImplEntity.zonedDateTimeMika = today;
        fieldTestServiceImplEntity.zonedDateTimeRequiredMika = today;
      }

      this.updateForm(fieldTestServiceImplEntity);
    });
  }

  updateForm(fieldTestServiceImplEntity: IFieldTestServiceImplEntity): void {
    this.editForm.patchValue({
      id: fieldTestServiceImplEntity.id,
      stringMika: fieldTestServiceImplEntity.stringMika,
      stringRequiredMika: fieldTestServiceImplEntity.stringRequiredMika,
      stringMinlengthMika: fieldTestServiceImplEntity.stringMinlengthMika,
      stringMaxlengthMika: fieldTestServiceImplEntity.stringMaxlengthMika,
      stringPatternMika: fieldTestServiceImplEntity.stringPatternMika,
      integerMika: fieldTestServiceImplEntity.integerMika,
      integerRequiredMika: fieldTestServiceImplEntity.integerRequiredMika,
      integerMinMika: fieldTestServiceImplEntity.integerMinMika,
      integerMaxMika: fieldTestServiceImplEntity.integerMaxMika,
      longMika: fieldTestServiceImplEntity.longMika,
      longRequiredMika: fieldTestServiceImplEntity.longRequiredMika,
      longMinMika: fieldTestServiceImplEntity.longMinMika,
      longMaxMika: fieldTestServiceImplEntity.longMaxMika,
      floatMika: fieldTestServiceImplEntity.floatMika,
      floatRequiredMika: fieldTestServiceImplEntity.floatRequiredMika,
      floatMinMika: fieldTestServiceImplEntity.floatMinMika,
      floatMaxMika: fieldTestServiceImplEntity.floatMaxMika,
      doubleRequiredMika: fieldTestServiceImplEntity.doubleRequiredMika,
      doubleMinMika: fieldTestServiceImplEntity.doubleMinMika,
      doubleMaxMika: fieldTestServiceImplEntity.doubleMaxMika,
      bigDecimalRequiredMika: fieldTestServiceImplEntity.bigDecimalRequiredMika,
      bigDecimalMinMika: fieldTestServiceImplEntity.bigDecimalMinMika,
      bigDecimalMaxMika: fieldTestServiceImplEntity.bigDecimalMaxMika,
      localDateMika: fieldTestServiceImplEntity.localDateMika,
      localDateRequiredMika: fieldTestServiceImplEntity.localDateRequiredMika,
      instantMika: fieldTestServiceImplEntity.instantMika ? fieldTestServiceImplEntity.instantMika.format(DATE_TIME_FORMAT) : null,
      instanteRequiredMika: fieldTestServiceImplEntity.instanteRequiredMika
        ? fieldTestServiceImplEntity.instanteRequiredMika.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeMika: fieldTestServiceImplEntity.zonedDateTimeMika
        ? fieldTestServiceImplEntity.zonedDateTimeMika.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeRequiredMika: fieldTestServiceImplEntity.zonedDateTimeRequiredMika
        ? fieldTestServiceImplEntity.zonedDateTimeRequiredMika.format(DATE_TIME_FORMAT)
        : null,
      durationMika: fieldTestServiceImplEntity.durationMika,
      durationRequiredMika: fieldTestServiceImplEntity.durationRequiredMika,
      booleanMika: fieldTestServiceImplEntity.booleanMika,
      booleanRequiredMika: fieldTestServiceImplEntity.booleanRequiredMika,
      enumMika: fieldTestServiceImplEntity.enumMika,
      enumRequiredMika: fieldTestServiceImplEntity.enumRequiredMika,
      uuidMika: fieldTestServiceImplEntity.uuidMika,
      uuidRequiredMika: fieldTestServiceImplEntity.uuidRequiredMika,
      byteImageMika: fieldTestServiceImplEntity.byteImageMika,
      byteImageMikaContentType: fieldTestServiceImplEntity.byteImageMikaContentType,
      byteImageRequiredMika: fieldTestServiceImplEntity.byteImageRequiredMika,
      byteImageRequiredMikaContentType: fieldTestServiceImplEntity.byteImageRequiredMikaContentType,
      byteImageMinbytesMika: fieldTestServiceImplEntity.byteImageMinbytesMika,
      byteImageMinbytesMikaContentType: fieldTestServiceImplEntity.byteImageMinbytesMikaContentType,
      byteImageMaxbytesMika: fieldTestServiceImplEntity.byteImageMaxbytesMika,
      byteImageMaxbytesMikaContentType: fieldTestServiceImplEntity.byteImageMaxbytesMikaContentType,
      byteAnyMika: fieldTestServiceImplEntity.byteAnyMika,
      byteAnyMikaContentType: fieldTestServiceImplEntity.byteAnyMikaContentType,
      byteAnyRequiredMika: fieldTestServiceImplEntity.byteAnyRequiredMika,
      byteAnyRequiredMikaContentType: fieldTestServiceImplEntity.byteAnyRequiredMikaContentType,
      byteAnyMinbytesMika: fieldTestServiceImplEntity.byteAnyMinbytesMika,
      byteAnyMinbytesMikaContentType: fieldTestServiceImplEntity.byteAnyMinbytesMikaContentType,
      byteAnyMaxbytesMika: fieldTestServiceImplEntity.byteAnyMaxbytesMika,
      byteAnyMaxbytesMikaContentType: fieldTestServiceImplEntity.byteAnyMaxbytesMikaContentType,
      byteTextMika: fieldTestServiceImplEntity.byteTextMika,
      byteTextRequiredMika: fieldTestServiceImplEntity.byteTextRequiredMika,
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
    const fieldTestServiceImplEntity = this.createFromForm();
    if (fieldTestServiceImplEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.fieldTestServiceImplEntityService.update(fieldTestServiceImplEntity));
    } else {
      this.subscribeToSaveResponse(this.fieldTestServiceImplEntityService.create(fieldTestServiceImplEntity));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFieldTestServiceImplEntity>>): void {
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

  protected createFromForm(): IFieldTestServiceImplEntity {
    return {
      ...new FieldTestServiceImplEntity(),
      id: this.editForm.get(['id'])!.value,
      stringMika: this.editForm.get(['stringMika'])!.value,
      stringRequiredMika: this.editForm.get(['stringRequiredMika'])!.value,
      stringMinlengthMika: this.editForm.get(['stringMinlengthMika'])!.value,
      stringMaxlengthMika: this.editForm.get(['stringMaxlengthMika'])!.value,
      stringPatternMika: this.editForm.get(['stringPatternMika'])!.value,
      integerMika: this.editForm.get(['integerMika'])!.value,
      integerRequiredMika: this.editForm.get(['integerRequiredMika'])!.value,
      integerMinMika: this.editForm.get(['integerMinMika'])!.value,
      integerMaxMika: this.editForm.get(['integerMaxMika'])!.value,
      longMika: this.editForm.get(['longMika'])!.value,
      longRequiredMika: this.editForm.get(['longRequiredMika'])!.value,
      longMinMika: this.editForm.get(['longMinMika'])!.value,
      longMaxMika: this.editForm.get(['longMaxMika'])!.value,
      floatMika: this.editForm.get(['floatMika'])!.value,
      floatRequiredMika: this.editForm.get(['floatRequiredMika'])!.value,
      floatMinMika: this.editForm.get(['floatMinMika'])!.value,
      floatMaxMika: this.editForm.get(['floatMaxMika'])!.value,
      doubleRequiredMika: this.editForm.get(['doubleRequiredMika'])!.value,
      doubleMinMika: this.editForm.get(['doubleMinMika'])!.value,
      doubleMaxMika: this.editForm.get(['doubleMaxMika'])!.value,
      bigDecimalRequiredMika: this.editForm.get(['bigDecimalRequiredMika'])!.value,
      bigDecimalMinMika: this.editForm.get(['bigDecimalMinMika'])!.value,
      bigDecimalMaxMika: this.editForm.get(['bigDecimalMaxMika'])!.value,
      localDateMika: this.editForm.get(['localDateMika'])!.value,
      localDateRequiredMika: this.editForm.get(['localDateRequiredMika'])!.value,
      instantMika: this.editForm.get(['instantMika'])!.value
        ? dayjs(this.editForm.get(['instantMika'])!.value, DATE_TIME_FORMAT)
        : undefined,
      instanteRequiredMika: this.editForm.get(['instanteRequiredMika'])!.value
        ? dayjs(this.editForm.get(['instanteRequiredMika'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeMika: this.editForm.get(['zonedDateTimeMika'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeMika'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeRequiredMika: this.editForm.get(['zonedDateTimeRequiredMika'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeRequiredMika'])!.value, DATE_TIME_FORMAT)
        : undefined,
      durationMika: this.editForm.get(['durationMika'])!.value,
      durationRequiredMika: this.editForm.get(['durationRequiredMika'])!.value,
      booleanMika: this.editForm.get(['booleanMika'])!.value,
      booleanRequiredMika: this.editForm.get(['booleanRequiredMika'])!.value,
      enumMika: this.editForm.get(['enumMika'])!.value,
      enumRequiredMika: this.editForm.get(['enumRequiredMika'])!.value,
      uuidMika: this.editForm.get(['uuidMika'])!.value,
      uuidRequiredMika: this.editForm.get(['uuidRequiredMika'])!.value,
      byteImageMikaContentType: this.editForm.get(['byteImageMikaContentType'])!.value,
      byteImageMika: this.editForm.get(['byteImageMika'])!.value,
      byteImageRequiredMikaContentType: this.editForm.get(['byteImageRequiredMikaContentType'])!.value,
      byteImageRequiredMika: this.editForm.get(['byteImageRequiredMika'])!.value,
      byteImageMinbytesMikaContentType: this.editForm.get(['byteImageMinbytesMikaContentType'])!.value,
      byteImageMinbytesMika: this.editForm.get(['byteImageMinbytesMika'])!.value,
      byteImageMaxbytesMikaContentType: this.editForm.get(['byteImageMaxbytesMikaContentType'])!.value,
      byteImageMaxbytesMika: this.editForm.get(['byteImageMaxbytesMika'])!.value,
      byteAnyMikaContentType: this.editForm.get(['byteAnyMikaContentType'])!.value,
      byteAnyMika: this.editForm.get(['byteAnyMika'])!.value,
      byteAnyRequiredMikaContentType: this.editForm.get(['byteAnyRequiredMikaContentType'])!.value,
      byteAnyRequiredMika: this.editForm.get(['byteAnyRequiredMika'])!.value,
      byteAnyMinbytesMikaContentType: this.editForm.get(['byteAnyMinbytesMikaContentType'])!.value,
      byteAnyMinbytesMika: this.editForm.get(['byteAnyMinbytesMika'])!.value,
      byteAnyMaxbytesMikaContentType: this.editForm.get(['byteAnyMaxbytesMikaContentType'])!.value,
      byteAnyMaxbytesMika: this.editForm.get(['byteAnyMaxbytesMika'])!.value,
      byteTextMika: this.editForm.get(['byteTextMika'])!.value,
      byteTextRequiredMika: this.editForm.get(['byteTextRequiredMika'])!.value,
    };
  }
}
