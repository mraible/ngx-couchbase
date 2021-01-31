import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import {
  IFieldTestMapstructAndServiceClassEntity,
  FieldTestMapstructAndServiceClassEntity,
} from '../field-test-mapstruct-and-service-class-entity.model';
import { FieldTestMapstructAndServiceClassEntityService } from '../service/field-test-mapstruct-and-service-class-entity.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-mapstruct-and-service-class-entity-update',
  templateUrl: './field-test-mapstruct-and-service-class-entity-update.component.html',
})
export class FieldTestMapstructAndServiceClassEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stringEva: [],
    stringRequiredEva: [null, [Validators.required]],
    stringMinlengthEva: [null, [Validators.minLength(0)]],
    stringMaxlengthEva: [null, [Validators.maxLength(20)]],
    stringPatternEva: [null, [Validators.pattern('^[a-zA-Z0-9]*$')]],
    integerEva: [],
    integerRequiredEva: [null, [Validators.required]],
    integerMinEva: [null, [Validators.min(0)]],
    integerMaxEva: [null, [Validators.max(100)]],
    longEva: [],
    longRequiredEva: [null, [Validators.required]],
    longMinEva: [null, [Validators.min(0)]],
    longMaxEva: [null, [Validators.max(100)]],
    floatEva: [],
    floatRequiredEva: [null, [Validators.required]],
    floatMinEva: [null, [Validators.min(0)]],
    floatMaxEva: [null, [Validators.max(100)]],
    doubleRequiredEva: [null, [Validators.required]],
    doubleMinEva: [null, [Validators.min(0)]],
    doubleMaxEva: [null, [Validators.max(100)]],
    bigDecimalRequiredEva: [null, [Validators.required]],
    bigDecimalMinEva: [null, [Validators.min(0)]],
    bigDecimalMaxEva: [null, [Validators.max(100)]],
    localDateEva: [],
    localDateRequiredEva: [null, [Validators.required]],
    instantEva: [],
    instanteRequiredEva: [null, [Validators.required]],
    zonedDateTimeEva: [],
    zonedDateTimeRequiredEva: [null, [Validators.required]],
    durationEva: [],
    durationRequiredEva: [null, [Validators.required]],
    booleanEva: [],
    booleanRequiredEva: [null, [Validators.required]],
    enumEva: [],
    enumRequiredEva: [null, [Validators.required]],
    uuidEva: [],
    uuidRequiredEva: [null, [Validators.required]],
    byteImageEva: [],
    byteImageEvaContentType: [],
    byteImageRequiredEva: [null, [Validators.required]],
    byteImageRequiredEvaContentType: [],
    byteImageMinbytesEva: [null, []],
    byteImageMinbytesEvaContentType: [],
    byteImageMaxbytesEva: [null, []],
    byteImageMaxbytesEvaContentType: [],
    byteAnyEva: [],
    byteAnyEvaContentType: [],
    byteAnyRequiredEva: [null, [Validators.required]],
    byteAnyRequiredEvaContentType: [],
    byteAnyMinbytesEva: [null, []],
    byteAnyMinbytesEvaContentType: [],
    byteAnyMaxbytesEva: [null, []],
    byteAnyMaxbytesEvaContentType: [],
    byteTextEva: [],
    byteTextRequiredEva: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected fieldTestMapstructAndServiceClassEntityService: FieldTestMapstructAndServiceClassEntityService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldTestMapstructAndServiceClassEntity }) => {
      if (fieldTestMapstructAndServiceClassEntity.id === undefined) {
        const today = dayjs().startOf('day');
        fieldTestMapstructAndServiceClassEntity.instantEva = today;
        fieldTestMapstructAndServiceClassEntity.instanteRequiredEva = today;
        fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva = today;
        fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva = today;
      }

      this.updateForm(fieldTestMapstructAndServiceClassEntity);
    });
  }

  updateForm(fieldTestMapstructAndServiceClassEntity: IFieldTestMapstructAndServiceClassEntity): void {
    this.editForm.patchValue({
      id: fieldTestMapstructAndServiceClassEntity.id,
      stringEva: fieldTestMapstructAndServiceClassEntity.stringEva,
      stringRequiredEva: fieldTestMapstructAndServiceClassEntity.stringRequiredEva,
      stringMinlengthEva: fieldTestMapstructAndServiceClassEntity.stringMinlengthEva,
      stringMaxlengthEva: fieldTestMapstructAndServiceClassEntity.stringMaxlengthEva,
      stringPatternEva: fieldTestMapstructAndServiceClassEntity.stringPatternEva,
      integerEva: fieldTestMapstructAndServiceClassEntity.integerEva,
      integerRequiredEva: fieldTestMapstructAndServiceClassEntity.integerRequiredEva,
      integerMinEva: fieldTestMapstructAndServiceClassEntity.integerMinEva,
      integerMaxEva: fieldTestMapstructAndServiceClassEntity.integerMaxEva,
      longEva: fieldTestMapstructAndServiceClassEntity.longEva,
      longRequiredEva: fieldTestMapstructAndServiceClassEntity.longRequiredEva,
      longMinEva: fieldTestMapstructAndServiceClassEntity.longMinEva,
      longMaxEva: fieldTestMapstructAndServiceClassEntity.longMaxEva,
      floatEva: fieldTestMapstructAndServiceClassEntity.floatEva,
      floatRequiredEva: fieldTestMapstructAndServiceClassEntity.floatRequiredEva,
      floatMinEva: fieldTestMapstructAndServiceClassEntity.floatMinEva,
      floatMaxEva: fieldTestMapstructAndServiceClassEntity.floatMaxEva,
      doubleRequiredEva: fieldTestMapstructAndServiceClassEntity.doubleRequiredEva,
      doubleMinEva: fieldTestMapstructAndServiceClassEntity.doubleMinEva,
      doubleMaxEva: fieldTestMapstructAndServiceClassEntity.doubleMaxEva,
      bigDecimalRequiredEva: fieldTestMapstructAndServiceClassEntity.bigDecimalRequiredEva,
      bigDecimalMinEva: fieldTestMapstructAndServiceClassEntity.bigDecimalMinEva,
      bigDecimalMaxEva: fieldTestMapstructAndServiceClassEntity.bigDecimalMaxEva,
      localDateEva: fieldTestMapstructAndServiceClassEntity.localDateEva,
      localDateRequiredEva: fieldTestMapstructAndServiceClassEntity.localDateRequiredEva,
      instantEva: fieldTestMapstructAndServiceClassEntity.instantEva
        ? fieldTestMapstructAndServiceClassEntity.instantEva.format(DATE_TIME_FORMAT)
        : null,
      instanteRequiredEva: fieldTestMapstructAndServiceClassEntity.instanteRequiredEva
        ? fieldTestMapstructAndServiceClassEntity.instanteRequiredEva.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeEva: fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva
        ? fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeRequiredEva: fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva
        ? fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva.format(DATE_TIME_FORMAT)
        : null,
      durationEva: fieldTestMapstructAndServiceClassEntity.durationEva,
      durationRequiredEva: fieldTestMapstructAndServiceClassEntity.durationRequiredEva,
      booleanEva: fieldTestMapstructAndServiceClassEntity.booleanEva,
      booleanRequiredEva: fieldTestMapstructAndServiceClassEntity.booleanRequiredEva,
      enumEva: fieldTestMapstructAndServiceClassEntity.enumEva,
      enumRequiredEva: fieldTestMapstructAndServiceClassEntity.enumRequiredEva,
      uuidEva: fieldTestMapstructAndServiceClassEntity.uuidEva,
      uuidRequiredEva: fieldTestMapstructAndServiceClassEntity.uuidRequiredEva,
      byteImageEva: fieldTestMapstructAndServiceClassEntity.byteImageEva,
      byteImageEvaContentType: fieldTestMapstructAndServiceClassEntity.byteImageEvaContentType,
      byteImageRequiredEva: fieldTestMapstructAndServiceClassEntity.byteImageRequiredEva,
      byteImageRequiredEvaContentType: fieldTestMapstructAndServiceClassEntity.byteImageRequiredEvaContentType,
      byteImageMinbytesEva: fieldTestMapstructAndServiceClassEntity.byteImageMinbytesEva,
      byteImageMinbytesEvaContentType: fieldTestMapstructAndServiceClassEntity.byteImageMinbytesEvaContentType,
      byteImageMaxbytesEva: fieldTestMapstructAndServiceClassEntity.byteImageMaxbytesEva,
      byteImageMaxbytesEvaContentType: fieldTestMapstructAndServiceClassEntity.byteImageMaxbytesEvaContentType,
      byteAnyEva: fieldTestMapstructAndServiceClassEntity.byteAnyEva,
      byteAnyEvaContentType: fieldTestMapstructAndServiceClassEntity.byteAnyEvaContentType,
      byteAnyRequiredEva: fieldTestMapstructAndServiceClassEntity.byteAnyRequiredEva,
      byteAnyRequiredEvaContentType: fieldTestMapstructAndServiceClassEntity.byteAnyRequiredEvaContentType,
      byteAnyMinbytesEva: fieldTestMapstructAndServiceClassEntity.byteAnyMinbytesEva,
      byteAnyMinbytesEvaContentType: fieldTestMapstructAndServiceClassEntity.byteAnyMinbytesEvaContentType,
      byteAnyMaxbytesEva: fieldTestMapstructAndServiceClassEntity.byteAnyMaxbytesEva,
      byteAnyMaxbytesEvaContentType: fieldTestMapstructAndServiceClassEntity.byteAnyMaxbytesEvaContentType,
      byteTextEva: fieldTestMapstructAndServiceClassEntity.byteTextEva,
      byteTextRequiredEva: fieldTestMapstructAndServiceClassEntity.byteTextRequiredEva,
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
    const fieldTestMapstructAndServiceClassEntity = this.createFromForm();
    if (fieldTestMapstructAndServiceClassEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.fieldTestMapstructAndServiceClassEntityService.update(fieldTestMapstructAndServiceClassEntity));
    } else {
      this.subscribeToSaveResponse(this.fieldTestMapstructAndServiceClassEntityService.create(fieldTestMapstructAndServiceClassEntity));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFieldTestMapstructAndServiceClassEntity>>): void {
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

  protected createFromForm(): IFieldTestMapstructAndServiceClassEntity {
    return {
      ...new FieldTestMapstructAndServiceClassEntity(),
      id: this.editForm.get(['id'])!.value,
      stringEva: this.editForm.get(['stringEva'])!.value,
      stringRequiredEva: this.editForm.get(['stringRequiredEva'])!.value,
      stringMinlengthEva: this.editForm.get(['stringMinlengthEva'])!.value,
      stringMaxlengthEva: this.editForm.get(['stringMaxlengthEva'])!.value,
      stringPatternEva: this.editForm.get(['stringPatternEva'])!.value,
      integerEva: this.editForm.get(['integerEva'])!.value,
      integerRequiredEva: this.editForm.get(['integerRequiredEva'])!.value,
      integerMinEva: this.editForm.get(['integerMinEva'])!.value,
      integerMaxEva: this.editForm.get(['integerMaxEva'])!.value,
      longEva: this.editForm.get(['longEva'])!.value,
      longRequiredEva: this.editForm.get(['longRequiredEva'])!.value,
      longMinEva: this.editForm.get(['longMinEva'])!.value,
      longMaxEva: this.editForm.get(['longMaxEva'])!.value,
      floatEva: this.editForm.get(['floatEva'])!.value,
      floatRequiredEva: this.editForm.get(['floatRequiredEva'])!.value,
      floatMinEva: this.editForm.get(['floatMinEva'])!.value,
      floatMaxEva: this.editForm.get(['floatMaxEva'])!.value,
      doubleRequiredEva: this.editForm.get(['doubleRequiredEva'])!.value,
      doubleMinEva: this.editForm.get(['doubleMinEva'])!.value,
      doubleMaxEva: this.editForm.get(['doubleMaxEva'])!.value,
      bigDecimalRequiredEva: this.editForm.get(['bigDecimalRequiredEva'])!.value,
      bigDecimalMinEva: this.editForm.get(['bigDecimalMinEva'])!.value,
      bigDecimalMaxEva: this.editForm.get(['bigDecimalMaxEva'])!.value,
      localDateEva: this.editForm.get(['localDateEva'])!.value,
      localDateRequiredEva: this.editForm.get(['localDateRequiredEva'])!.value,
      instantEva: this.editForm.get(['instantEva'])!.value ? dayjs(this.editForm.get(['instantEva'])!.value, DATE_TIME_FORMAT) : undefined,
      instanteRequiredEva: this.editForm.get(['instanteRequiredEva'])!.value
        ? dayjs(this.editForm.get(['instanteRequiredEva'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeEva: this.editForm.get(['zonedDateTimeEva'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeEva'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeRequiredEva: this.editForm.get(['zonedDateTimeRequiredEva'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeRequiredEva'])!.value, DATE_TIME_FORMAT)
        : undefined,
      durationEva: this.editForm.get(['durationEva'])!.value,
      durationRequiredEva: this.editForm.get(['durationRequiredEva'])!.value,
      booleanEva: this.editForm.get(['booleanEva'])!.value,
      booleanRequiredEva: this.editForm.get(['booleanRequiredEva'])!.value,
      enumEva: this.editForm.get(['enumEva'])!.value,
      enumRequiredEva: this.editForm.get(['enumRequiredEva'])!.value,
      uuidEva: this.editForm.get(['uuidEva'])!.value,
      uuidRequiredEva: this.editForm.get(['uuidRequiredEva'])!.value,
      byteImageEvaContentType: this.editForm.get(['byteImageEvaContentType'])!.value,
      byteImageEva: this.editForm.get(['byteImageEva'])!.value,
      byteImageRequiredEvaContentType: this.editForm.get(['byteImageRequiredEvaContentType'])!.value,
      byteImageRequiredEva: this.editForm.get(['byteImageRequiredEva'])!.value,
      byteImageMinbytesEvaContentType: this.editForm.get(['byteImageMinbytesEvaContentType'])!.value,
      byteImageMinbytesEva: this.editForm.get(['byteImageMinbytesEva'])!.value,
      byteImageMaxbytesEvaContentType: this.editForm.get(['byteImageMaxbytesEvaContentType'])!.value,
      byteImageMaxbytesEva: this.editForm.get(['byteImageMaxbytesEva'])!.value,
      byteAnyEvaContentType: this.editForm.get(['byteAnyEvaContentType'])!.value,
      byteAnyEva: this.editForm.get(['byteAnyEva'])!.value,
      byteAnyRequiredEvaContentType: this.editForm.get(['byteAnyRequiredEvaContentType'])!.value,
      byteAnyRequiredEva: this.editForm.get(['byteAnyRequiredEva'])!.value,
      byteAnyMinbytesEvaContentType: this.editForm.get(['byteAnyMinbytesEvaContentType'])!.value,
      byteAnyMinbytesEva: this.editForm.get(['byteAnyMinbytesEva'])!.value,
      byteAnyMaxbytesEvaContentType: this.editForm.get(['byteAnyMaxbytesEvaContentType'])!.value,
      byteAnyMaxbytesEva: this.editForm.get(['byteAnyMaxbytesEva'])!.value,
      byteTextEva: this.editForm.get(['byteTextEva'])!.value,
      byteTextRequiredEva: this.editForm.get(['byteTextRequiredEva'])!.value,
    };
  }
}
