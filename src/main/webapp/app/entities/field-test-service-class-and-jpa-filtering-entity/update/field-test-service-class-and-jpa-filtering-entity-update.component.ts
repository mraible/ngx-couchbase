import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import {
  IFieldTestServiceClassAndJpaFilteringEntity,
  FieldTestServiceClassAndJpaFilteringEntity,
} from '../field-test-service-class-and-jpa-filtering-entity.model';
import { FieldTestServiceClassAndJpaFilteringEntityService } from '../service/field-test-service-class-and-jpa-filtering-entity.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-service-class-and-jpa-filtering-entity-update',
  templateUrl: './field-test-service-class-and-jpa-filtering-entity-update.component.html',
})
export class FieldTestServiceClassAndJpaFilteringEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stringBob: [],
    stringRequiredBob: [null, [Validators.required]],
    stringMinlengthBob: [null, [Validators.minLength(0)]],
    stringMaxlengthBob: [null, [Validators.maxLength(20)]],
    stringPatternBob: [null, [Validators.pattern('^[a-zA-Z0-9]*$')]],
    integerBob: [],
    integerRequiredBob: [null, [Validators.required]],
    integerMinBob: [null, [Validators.min(0)]],
    integerMaxBob: [null, [Validators.max(100)]],
    longBob: [],
    longRequiredBob: [null, [Validators.required]],
    longMinBob: [null, [Validators.min(0)]],
    longMaxBob: [null, [Validators.max(100)]],
    floatBob: [],
    floatRequiredBob: [null, [Validators.required]],
    floatMinBob: [null, [Validators.min(0)]],
    floatMaxBob: [null, [Validators.max(100)]],
    doubleRequiredBob: [null, [Validators.required]],
    doubleMinBob: [null, [Validators.min(0)]],
    doubleMaxBob: [null, [Validators.max(100)]],
    bigDecimalRequiredBob: [null, [Validators.required]],
    bigDecimalMinBob: [null, [Validators.min(0)]],
    bigDecimalMaxBob: [null, [Validators.max(100)]],
    localDateBob: [],
    localDateRequiredBob: [null, [Validators.required]],
    instantBob: [],
    instanteRequiredBob: [null, [Validators.required]],
    zonedDateTimeBob: [],
    zonedDateTimeRequiredBob: [null, [Validators.required]],
    durationBob: [],
    durationRequiredBob: [null, [Validators.required]],
    booleanBob: [],
    booleanRequiredBob: [null, [Validators.required]],
    enumBob: [],
    enumRequiredBob: [null, [Validators.required]],
    uuidBob: [],
    uuidRequiredBob: [null, [Validators.required]],
    byteImageBob: [],
    byteImageBobContentType: [],
    byteImageRequiredBob: [null, [Validators.required]],
    byteImageRequiredBobContentType: [],
    byteImageMinbytesBob: [null, []],
    byteImageMinbytesBobContentType: [],
    byteImageMaxbytesBob: [null, []],
    byteImageMaxbytesBobContentType: [],
    byteAnyBob: [],
    byteAnyBobContentType: [],
    byteAnyRequiredBob: [null, [Validators.required]],
    byteAnyRequiredBobContentType: [],
    byteAnyMinbytesBob: [null, []],
    byteAnyMinbytesBobContentType: [],
    byteAnyMaxbytesBob: [null, []],
    byteAnyMaxbytesBobContentType: [],
    byteTextBob: [],
    byteTextRequiredBob: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected fieldTestServiceClassAndJpaFilteringEntityService: FieldTestServiceClassAndJpaFilteringEntityService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldTestServiceClassAndJpaFilteringEntity }) => {
      if (fieldTestServiceClassAndJpaFilteringEntity.id === undefined) {
        const today = dayjs().startOf('day');
        fieldTestServiceClassAndJpaFilteringEntity.instantBob = today;
        fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob = today;
        fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob = today;
        fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob = today;
      }

      this.updateForm(fieldTestServiceClassAndJpaFilteringEntity);
    });
  }

  updateForm(fieldTestServiceClassAndJpaFilteringEntity: IFieldTestServiceClassAndJpaFilteringEntity): void {
    this.editForm.patchValue({
      id: fieldTestServiceClassAndJpaFilteringEntity.id,
      stringBob: fieldTestServiceClassAndJpaFilteringEntity.stringBob,
      stringRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.stringRequiredBob,
      stringMinlengthBob: fieldTestServiceClassAndJpaFilteringEntity.stringMinlengthBob,
      stringMaxlengthBob: fieldTestServiceClassAndJpaFilteringEntity.stringMaxlengthBob,
      stringPatternBob: fieldTestServiceClassAndJpaFilteringEntity.stringPatternBob,
      integerBob: fieldTestServiceClassAndJpaFilteringEntity.integerBob,
      integerRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.integerRequiredBob,
      integerMinBob: fieldTestServiceClassAndJpaFilteringEntity.integerMinBob,
      integerMaxBob: fieldTestServiceClassAndJpaFilteringEntity.integerMaxBob,
      longBob: fieldTestServiceClassAndJpaFilteringEntity.longBob,
      longRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.longRequiredBob,
      longMinBob: fieldTestServiceClassAndJpaFilteringEntity.longMinBob,
      longMaxBob: fieldTestServiceClassAndJpaFilteringEntity.longMaxBob,
      floatBob: fieldTestServiceClassAndJpaFilteringEntity.floatBob,
      floatRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.floatRequiredBob,
      floatMinBob: fieldTestServiceClassAndJpaFilteringEntity.floatMinBob,
      floatMaxBob: fieldTestServiceClassAndJpaFilteringEntity.floatMaxBob,
      doubleRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.doubleRequiredBob,
      doubleMinBob: fieldTestServiceClassAndJpaFilteringEntity.doubleMinBob,
      doubleMaxBob: fieldTestServiceClassAndJpaFilteringEntity.doubleMaxBob,
      bigDecimalRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.bigDecimalRequiredBob,
      bigDecimalMinBob: fieldTestServiceClassAndJpaFilteringEntity.bigDecimalMinBob,
      bigDecimalMaxBob: fieldTestServiceClassAndJpaFilteringEntity.bigDecimalMaxBob,
      localDateBob: fieldTestServiceClassAndJpaFilteringEntity.localDateBob,
      localDateRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.localDateRequiredBob,
      instantBob: fieldTestServiceClassAndJpaFilteringEntity.instantBob
        ? fieldTestServiceClassAndJpaFilteringEntity.instantBob.format(DATE_TIME_FORMAT)
        : null,
      instanteRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob
        ? fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeBob: fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob
        ? fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob
        ? fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob.format(DATE_TIME_FORMAT)
        : null,
      durationBob: fieldTestServiceClassAndJpaFilteringEntity.durationBob,
      durationRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.durationRequiredBob,
      booleanBob: fieldTestServiceClassAndJpaFilteringEntity.booleanBob,
      booleanRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.booleanRequiredBob,
      enumBob: fieldTestServiceClassAndJpaFilteringEntity.enumBob,
      enumRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.enumRequiredBob,
      uuidBob: fieldTestServiceClassAndJpaFilteringEntity.uuidBob,
      uuidRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.uuidRequiredBob,
      byteImageBob: fieldTestServiceClassAndJpaFilteringEntity.byteImageBob,
      byteImageBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteImageBobContentType,
      byteImageRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.byteImageRequiredBob,
      byteImageRequiredBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteImageRequiredBobContentType,
      byteImageMinbytesBob: fieldTestServiceClassAndJpaFilteringEntity.byteImageMinbytesBob,
      byteImageMinbytesBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteImageMinbytesBobContentType,
      byteImageMaxbytesBob: fieldTestServiceClassAndJpaFilteringEntity.byteImageMaxbytesBob,
      byteImageMaxbytesBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteImageMaxbytesBobContentType,
      byteAnyBob: fieldTestServiceClassAndJpaFilteringEntity.byteAnyBob,
      byteAnyBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteAnyBobContentType,
      byteAnyRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.byteAnyRequiredBob,
      byteAnyRequiredBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteAnyRequiredBobContentType,
      byteAnyMinbytesBob: fieldTestServiceClassAndJpaFilteringEntity.byteAnyMinbytesBob,
      byteAnyMinbytesBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteAnyMinbytesBobContentType,
      byteAnyMaxbytesBob: fieldTestServiceClassAndJpaFilteringEntity.byteAnyMaxbytesBob,
      byteAnyMaxbytesBobContentType: fieldTestServiceClassAndJpaFilteringEntity.byteAnyMaxbytesBobContentType,
      byteTextBob: fieldTestServiceClassAndJpaFilteringEntity.byteTextBob,
      byteTextRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.byteTextRequiredBob,
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
    const fieldTestServiceClassAndJpaFilteringEntity = this.createFromForm();
    if (fieldTestServiceClassAndJpaFilteringEntity.id !== undefined) {
      this.subscribeToSaveResponse(
        this.fieldTestServiceClassAndJpaFilteringEntityService.update(fieldTestServiceClassAndJpaFilteringEntity)
      );
    } else {
      this.subscribeToSaveResponse(
        this.fieldTestServiceClassAndJpaFilteringEntityService.create(fieldTestServiceClassAndJpaFilteringEntity)
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFieldTestServiceClassAndJpaFilteringEntity>>): void {
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

  protected createFromForm(): IFieldTestServiceClassAndJpaFilteringEntity {
    return {
      ...new FieldTestServiceClassAndJpaFilteringEntity(),
      id: this.editForm.get(['id'])!.value,
      stringBob: this.editForm.get(['stringBob'])!.value,
      stringRequiredBob: this.editForm.get(['stringRequiredBob'])!.value,
      stringMinlengthBob: this.editForm.get(['stringMinlengthBob'])!.value,
      stringMaxlengthBob: this.editForm.get(['stringMaxlengthBob'])!.value,
      stringPatternBob: this.editForm.get(['stringPatternBob'])!.value,
      integerBob: this.editForm.get(['integerBob'])!.value,
      integerRequiredBob: this.editForm.get(['integerRequiredBob'])!.value,
      integerMinBob: this.editForm.get(['integerMinBob'])!.value,
      integerMaxBob: this.editForm.get(['integerMaxBob'])!.value,
      longBob: this.editForm.get(['longBob'])!.value,
      longRequiredBob: this.editForm.get(['longRequiredBob'])!.value,
      longMinBob: this.editForm.get(['longMinBob'])!.value,
      longMaxBob: this.editForm.get(['longMaxBob'])!.value,
      floatBob: this.editForm.get(['floatBob'])!.value,
      floatRequiredBob: this.editForm.get(['floatRequiredBob'])!.value,
      floatMinBob: this.editForm.get(['floatMinBob'])!.value,
      floatMaxBob: this.editForm.get(['floatMaxBob'])!.value,
      doubleRequiredBob: this.editForm.get(['doubleRequiredBob'])!.value,
      doubleMinBob: this.editForm.get(['doubleMinBob'])!.value,
      doubleMaxBob: this.editForm.get(['doubleMaxBob'])!.value,
      bigDecimalRequiredBob: this.editForm.get(['bigDecimalRequiredBob'])!.value,
      bigDecimalMinBob: this.editForm.get(['bigDecimalMinBob'])!.value,
      bigDecimalMaxBob: this.editForm.get(['bigDecimalMaxBob'])!.value,
      localDateBob: this.editForm.get(['localDateBob'])!.value,
      localDateRequiredBob: this.editForm.get(['localDateRequiredBob'])!.value,
      instantBob: this.editForm.get(['instantBob'])!.value ? dayjs(this.editForm.get(['instantBob'])!.value, DATE_TIME_FORMAT) : undefined,
      instanteRequiredBob: this.editForm.get(['instanteRequiredBob'])!.value
        ? dayjs(this.editForm.get(['instanteRequiredBob'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeBob: this.editForm.get(['zonedDateTimeBob'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeBob'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeRequiredBob: this.editForm.get(['zonedDateTimeRequiredBob'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeRequiredBob'])!.value, DATE_TIME_FORMAT)
        : undefined,
      durationBob: this.editForm.get(['durationBob'])!.value,
      durationRequiredBob: this.editForm.get(['durationRequiredBob'])!.value,
      booleanBob: this.editForm.get(['booleanBob'])!.value,
      booleanRequiredBob: this.editForm.get(['booleanRequiredBob'])!.value,
      enumBob: this.editForm.get(['enumBob'])!.value,
      enumRequiredBob: this.editForm.get(['enumRequiredBob'])!.value,
      uuidBob: this.editForm.get(['uuidBob'])!.value,
      uuidRequiredBob: this.editForm.get(['uuidRequiredBob'])!.value,
      byteImageBobContentType: this.editForm.get(['byteImageBobContentType'])!.value,
      byteImageBob: this.editForm.get(['byteImageBob'])!.value,
      byteImageRequiredBobContentType: this.editForm.get(['byteImageRequiredBobContentType'])!.value,
      byteImageRequiredBob: this.editForm.get(['byteImageRequiredBob'])!.value,
      byteImageMinbytesBobContentType: this.editForm.get(['byteImageMinbytesBobContentType'])!.value,
      byteImageMinbytesBob: this.editForm.get(['byteImageMinbytesBob'])!.value,
      byteImageMaxbytesBobContentType: this.editForm.get(['byteImageMaxbytesBobContentType'])!.value,
      byteImageMaxbytesBob: this.editForm.get(['byteImageMaxbytesBob'])!.value,
      byteAnyBobContentType: this.editForm.get(['byteAnyBobContentType'])!.value,
      byteAnyBob: this.editForm.get(['byteAnyBob'])!.value,
      byteAnyRequiredBobContentType: this.editForm.get(['byteAnyRequiredBobContentType'])!.value,
      byteAnyRequiredBob: this.editForm.get(['byteAnyRequiredBob'])!.value,
      byteAnyMinbytesBobContentType: this.editForm.get(['byteAnyMinbytesBobContentType'])!.value,
      byteAnyMinbytesBob: this.editForm.get(['byteAnyMinbytesBob'])!.value,
      byteAnyMaxbytesBobContentType: this.editForm.get(['byteAnyMaxbytesBobContentType'])!.value,
      byteAnyMaxbytesBob: this.editForm.get(['byteAnyMaxbytesBob'])!.value,
      byteTextBob: this.editForm.get(['byteTextBob'])!.value,
      byteTextRequiredBob: this.editForm.get(['byteTextRequiredBob'])!.value,
    };
  }
}
