import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IFieldTestInfiniteScrollEntity, FieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';
import { FieldTestInfiniteScrollEntityService } from '../service/field-test-infinite-scroll-entity.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-infinite-scroll-entity-update',
  templateUrl: './field-test-infinite-scroll-entity-update.component.html',
})
export class FieldTestInfiniteScrollEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stringHugo: [],
    stringRequiredHugo: [null, [Validators.required]],
    stringMinlengthHugo: [null, [Validators.minLength(0)]],
    stringMaxlengthHugo: [null, [Validators.maxLength(20)]],
    stringPatternHugo: [null, [Validators.pattern('^[a-zA-Z0-9]*$')]],
    integerHugo: [],
    integerRequiredHugo: [null, [Validators.required]],
    integerMinHugo: [null, [Validators.min(0)]],
    integerMaxHugo: [null, [Validators.max(100)]],
    longHugo: [],
    longRequiredHugo: [null, [Validators.required]],
    longMinHugo: [null, [Validators.min(0)]],
    longMaxHugo: [null, [Validators.max(100)]],
    floatHugo: [],
    floatRequiredHugo: [null, [Validators.required]],
    floatMinHugo: [null, [Validators.min(0)]],
    floatMaxHugo: [null, [Validators.max(100)]],
    doubleRequiredHugo: [null, [Validators.required]],
    doubleMinHugo: [null, [Validators.min(0)]],
    doubleMaxHugo: [null, [Validators.max(100)]],
    bigDecimalRequiredHugo: [null, [Validators.required]],
    bigDecimalMinHugo: [null, [Validators.min(0)]],
    bigDecimalMaxHugo: [null, [Validators.max(100)]],
    localDateHugo: [],
    localDateRequiredHugo: [null, [Validators.required]],
    instantHugo: [],
    instanteRequiredHugo: [null, [Validators.required]],
    zonedDateTimeHugo: [],
    zonedDateTimeRequiredHugo: [null, [Validators.required]],
    durationHugo: [],
    durationRequiredHugo: [null, [Validators.required]],
    booleanHugo: [],
    booleanRequiredHugo: [null, [Validators.required]],
    enumHugo: [],
    enumRequiredHugo: [null, [Validators.required]],
    uuidHugo: [],
    uuidRequiredHugo: [null, [Validators.required]],
    byteImageHugo: [],
    byteImageHugoContentType: [],
    byteImageRequiredHugo: [null, [Validators.required]],
    byteImageRequiredHugoContentType: [],
    byteImageMinbytesHugo: [null, []],
    byteImageMinbytesHugoContentType: [],
    byteImageMaxbytesHugo: [null, []],
    byteImageMaxbytesHugoContentType: [],
    byteAnyHugo: [],
    byteAnyHugoContentType: [],
    byteAnyRequiredHugo: [null, [Validators.required]],
    byteAnyRequiredHugoContentType: [],
    byteAnyMinbytesHugo: [null, []],
    byteAnyMinbytesHugoContentType: [],
    byteAnyMaxbytesHugo: [null, []],
    byteAnyMaxbytesHugoContentType: [],
    byteTextHugo: [],
    byteTextRequiredHugo: [null, [Validators.required]],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected fieldTestInfiniteScrollEntityService: FieldTestInfiniteScrollEntityService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldTestInfiniteScrollEntity }) => {
      if (fieldTestInfiniteScrollEntity.id === undefined) {
        const today = dayjs().startOf('day');
        fieldTestInfiniteScrollEntity.instantHugo = today;
        fieldTestInfiniteScrollEntity.instanteRequiredHugo = today;
        fieldTestInfiniteScrollEntity.zonedDateTimeHugo = today;
        fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo = today;
      }

      this.updateForm(fieldTestInfiniteScrollEntity);
    });
  }

  updateForm(fieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity): void {
    this.editForm.patchValue({
      id: fieldTestInfiniteScrollEntity.id,
      stringHugo: fieldTestInfiniteScrollEntity.stringHugo,
      stringRequiredHugo: fieldTestInfiniteScrollEntity.stringRequiredHugo,
      stringMinlengthHugo: fieldTestInfiniteScrollEntity.stringMinlengthHugo,
      stringMaxlengthHugo: fieldTestInfiniteScrollEntity.stringMaxlengthHugo,
      stringPatternHugo: fieldTestInfiniteScrollEntity.stringPatternHugo,
      integerHugo: fieldTestInfiniteScrollEntity.integerHugo,
      integerRequiredHugo: fieldTestInfiniteScrollEntity.integerRequiredHugo,
      integerMinHugo: fieldTestInfiniteScrollEntity.integerMinHugo,
      integerMaxHugo: fieldTestInfiniteScrollEntity.integerMaxHugo,
      longHugo: fieldTestInfiniteScrollEntity.longHugo,
      longRequiredHugo: fieldTestInfiniteScrollEntity.longRequiredHugo,
      longMinHugo: fieldTestInfiniteScrollEntity.longMinHugo,
      longMaxHugo: fieldTestInfiniteScrollEntity.longMaxHugo,
      floatHugo: fieldTestInfiniteScrollEntity.floatHugo,
      floatRequiredHugo: fieldTestInfiniteScrollEntity.floatRequiredHugo,
      floatMinHugo: fieldTestInfiniteScrollEntity.floatMinHugo,
      floatMaxHugo: fieldTestInfiniteScrollEntity.floatMaxHugo,
      doubleRequiredHugo: fieldTestInfiniteScrollEntity.doubleRequiredHugo,
      doubleMinHugo: fieldTestInfiniteScrollEntity.doubleMinHugo,
      doubleMaxHugo: fieldTestInfiniteScrollEntity.doubleMaxHugo,
      bigDecimalRequiredHugo: fieldTestInfiniteScrollEntity.bigDecimalRequiredHugo,
      bigDecimalMinHugo: fieldTestInfiniteScrollEntity.bigDecimalMinHugo,
      bigDecimalMaxHugo: fieldTestInfiniteScrollEntity.bigDecimalMaxHugo,
      localDateHugo: fieldTestInfiniteScrollEntity.localDateHugo,
      localDateRequiredHugo: fieldTestInfiniteScrollEntity.localDateRequiredHugo,
      instantHugo: fieldTestInfiniteScrollEntity.instantHugo ? fieldTestInfiniteScrollEntity.instantHugo.format(DATE_TIME_FORMAT) : null,
      instanteRequiredHugo: fieldTestInfiniteScrollEntity.instanteRequiredHugo
        ? fieldTestInfiniteScrollEntity.instanteRequiredHugo.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeHugo: fieldTestInfiniteScrollEntity.zonedDateTimeHugo
        ? fieldTestInfiniteScrollEntity.zonedDateTimeHugo.format(DATE_TIME_FORMAT)
        : null,
      zonedDateTimeRequiredHugo: fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo
        ? fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo.format(DATE_TIME_FORMAT)
        : null,
      durationHugo: fieldTestInfiniteScrollEntity.durationHugo,
      durationRequiredHugo: fieldTestInfiniteScrollEntity.durationRequiredHugo,
      booleanHugo: fieldTestInfiniteScrollEntity.booleanHugo,
      booleanRequiredHugo: fieldTestInfiniteScrollEntity.booleanRequiredHugo,
      enumHugo: fieldTestInfiniteScrollEntity.enumHugo,
      enumRequiredHugo: fieldTestInfiniteScrollEntity.enumRequiredHugo,
      uuidHugo: fieldTestInfiniteScrollEntity.uuidHugo,
      uuidRequiredHugo: fieldTestInfiniteScrollEntity.uuidRequiredHugo,
      byteImageHugo: fieldTestInfiniteScrollEntity.byteImageHugo,
      byteImageHugoContentType: fieldTestInfiniteScrollEntity.byteImageHugoContentType,
      byteImageRequiredHugo: fieldTestInfiniteScrollEntity.byteImageRequiredHugo,
      byteImageRequiredHugoContentType: fieldTestInfiniteScrollEntity.byteImageRequiredHugoContentType,
      byteImageMinbytesHugo: fieldTestInfiniteScrollEntity.byteImageMinbytesHugo,
      byteImageMinbytesHugoContentType: fieldTestInfiniteScrollEntity.byteImageMinbytesHugoContentType,
      byteImageMaxbytesHugo: fieldTestInfiniteScrollEntity.byteImageMaxbytesHugo,
      byteImageMaxbytesHugoContentType: fieldTestInfiniteScrollEntity.byteImageMaxbytesHugoContentType,
      byteAnyHugo: fieldTestInfiniteScrollEntity.byteAnyHugo,
      byteAnyHugoContentType: fieldTestInfiniteScrollEntity.byteAnyHugoContentType,
      byteAnyRequiredHugo: fieldTestInfiniteScrollEntity.byteAnyRequiredHugo,
      byteAnyRequiredHugoContentType: fieldTestInfiniteScrollEntity.byteAnyRequiredHugoContentType,
      byteAnyMinbytesHugo: fieldTestInfiniteScrollEntity.byteAnyMinbytesHugo,
      byteAnyMinbytesHugoContentType: fieldTestInfiniteScrollEntity.byteAnyMinbytesHugoContentType,
      byteAnyMaxbytesHugo: fieldTestInfiniteScrollEntity.byteAnyMaxbytesHugo,
      byteAnyMaxbytesHugoContentType: fieldTestInfiniteScrollEntity.byteAnyMaxbytesHugoContentType,
      byteTextHugo: fieldTestInfiniteScrollEntity.byteTextHugo,
      byteTextRequiredHugo: fieldTestInfiniteScrollEntity.byteTextRequiredHugo,
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
    const fieldTestInfiniteScrollEntity = this.createFromForm();
    if (fieldTestInfiniteScrollEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.fieldTestInfiniteScrollEntityService.update(fieldTestInfiniteScrollEntity));
    } else {
      this.subscribeToSaveResponse(this.fieldTestInfiniteScrollEntityService.create(fieldTestInfiniteScrollEntity));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFieldTestInfiniteScrollEntity>>): void {
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

  protected createFromForm(): IFieldTestInfiniteScrollEntity {
    return {
      ...new FieldTestInfiniteScrollEntity(),
      id: this.editForm.get(['id'])!.value,
      stringHugo: this.editForm.get(['stringHugo'])!.value,
      stringRequiredHugo: this.editForm.get(['stringRequiredHugo'])!.value,
      stringMinlengthHugo: this.editForm.get(['stringMinlengthHugo'])!.value,
      stringMaxlengthHugo: this.editForm.get(['stringMaxlengthHugo'])!.value,
      stringPatternHugo: this.editForm.get(['stringPatternHugo'])!.value,
      integerHugo: this.editForm.get(['integerHugo'])!.value,
      integerRequiredHugo: this.editForm.get(['integerRequiredHugo'])!.value,
      integerMinHugo: this.editForm.get(['integerMinHugo'])!.value,
      integerMaxHugo: this.editForm.get(['integerMaxHugo'])!.value,
      longHugo: this.editForm.get(['longHugo'])!.value,
      longRequiredHugo: this.editForm.get(['longRequiredHugo'])!.value,
      longMinHugo: this.editForm.get(['longMinHugo'])!.value,
      longMaxHugo: this.editForm.get(['longMaxHugo'])!.value,
      floatHugo: this.editForm.get(['floatHugo'])!.value,
      floatRequiredHugo: this.editForm.get(['floatRequiredHugo'])!.value,
      floatMinHugo: this.editForm.get(['floatMinHugo'])!.value,
      floatMaxHugo: this.editForm.get(['floatMaxHugo'])!.value,
      doubleRequiredHugo: this.editForm.get(['doubleRequiredHugo'])!.value,
      doubleMinHugo: this.editForm.get(['doubleMinHugo'])!.value,
      doubleMaxHugo: this.editForm.get(['doubleMaxHugo'])!.value,
      bigDecimalRequiredHugo: this.editForm.get(['bigDecimalRequiredHugo'])!.value,
      bigDecimalMinHugo: this.editForm.get(['bigDecimalMinHugo'])!.value,
      bigDecimalMaxHugo: this.editForm.get(['bigDecimalMaxHugo'])!.value,
      localDateHugo: this.editForm.get(['localDateHugo'])!.value,
      localDateRequiredHugo: this.editForm.get(['localDateRequiredHugo'])!.value,
      instantHugo: this.editForm.get(['instantHugo'])!.value
        ? dayjs(this.editForm.get(['instantHugo'])!.value, DATE_TIME_FORMAT)
        : undefined,
      instanteRequiredHugo: this.editForm.get(['instanteRequiredHugo'])!.value
        ? dayjs(this.editForm.get(['instanteRequiredHugo'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeHugo: this.editForm.get(['zonedDateTimeHugo'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeHugo'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zonedDateTimeRequiredHugo: this.editForm.get(['zonedDateTimeRequiredHugo'])!.value
        ? dayjs(this.editForm.get(['zonedDateTimeRequiredHugo'])!.value, DATE_TIME_FORMAT)
        : undefined,
      durationHugo: this.editForm.get(['durationHugo'])!.value,
      durationRequiredHugo: this.editForm.get(['durationRequiredHugo'])!.value,
      booleanHugo: this.editForm.get(['booleanHugo'])!.value,
      booleanRequiredHugo: this.editForm.get(['booleanRequiredHugo'])!.value,
      enumHugo: this.editForm.get(['enumHugo'])!.value,
      enumRequiredHugo: this.editForm.get(['enumRequiredHugo'])!.value,
      uuidHugo: this.editForm.get(['uuidHugo'])!.value,
      uuidRequiredHugo: this.editForm.get(['uuidRequiredHugo'])!.value,
      byteImageHugoContentType: this.editForm.get(['byteImageHugoContentType'])!.value,
      byteImageHugo: this.editForm.get(['byteImageHugo'])!.value,
      byteImageRequiredHugoContentType: this.editForm.get(['byteImageRequiredHugoContentType'])!.value,
      byteImageRequiredHugo: this.editForm.get(['byteImageRequiredHugo'])!.value,
      byteImageMinbytesHugoContentType: this.editForm.get(['byteImageMinbytesHugoContentType'])!.value,
      byteImageMinbytesHugo: this.editForm.get(['byteImageMinbytesHugo'])!.value,
      byteImageMaxbytesHugoContentType: this.editForm.get(['byteImageMaxbytesHugoContentType'])!.value,
      byteImageMaxbytesHugo: this.editForm.get(['byteImageMaxbytesHugo'])!.value,
      byteAnyHugoContentType: this.editForm.get(['byteAnyHugoContentType'])!.value,
      byteAnyHugo: this.editForm.get(['byteAnyHugo'])!.value,
      byteAnyRequiredHugoContentType: this.editForm.get(['byteAnyRequiredHugoContentType'])!.value,
      byteAnyRequiredHugo: this.editForm.get(['byteAnyRequiredHugo'])!.value,
      byteAnyMinbytesHugoContentType: this.editForm.get(['byteAnyMinbytesHugoContentType'])!.value,
      byteAnyMinbytesHugo: this.editForm.get(['byteAnyMinbytesHugo'])!.value,
      byteAnyMaxbytesHugoContentType: this.editForm.get(['byteAnyMaxbytesHugoContentType'])!.value,
      byteAnyMaxbytesHugo: this.editForm.get(['byteAnyMaxbytesHugo'])!.value,
      byteTextHugo: this.editForm.get(['byteTextHugo'])!.value,
      byteTextRequiredHugo: this.editForm.get(['byteTextRequiredHugo'])!.value,
    };
  }
}
