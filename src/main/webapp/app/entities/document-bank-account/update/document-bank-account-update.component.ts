import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IDocumentBankAccount, DocumentBankAccount } from '../document-bank-account.model';
import { DocumentBankAccountService } from '../service/document-bank-account.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-document-bank-account-update',
  templateUrl: './document-bank-account-update.component.html',
})
export class DocumentBankAccountUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    bankNumber: [],
    agencyNumber: [],
    lastOperationDuration: [],
    meanOperationDuration: [],
    balance: [null, [Validators.required]],
    openingDay: [],
    lastOperationDate: [],
    active: [],
    accountType: [],
    attachment: [],
    attachmentContentType: [],
    description: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected documentBankAccountService: DocumentBankAccountService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ documentBankAccount }) => {
      if (documentBankAccount.id === undefined) {
        const today = dayjs().startOf('day');
        documentBankAccount.lastOperationDate = today;
      }

      this.updateForm(documentBankAccount);
    });
  }

  updateForm(documentBankAccount: IDocumentBankAccount): void {
    this.editForm.patchValue({
      id: documentBankAccount.id,
      name: documentBankAccount.name,
      bankNumber: documentBankAccount.bankNumber,
      agencyNumber: documentBankAccount.agencyNumber,
      lastOperationDuration: documentBankAccount.lastOperationDuration,
      meanOperationDuration: documentBankAccount.meanOperationDuration,
      balance: documentBankAccount.balance,
      openingDay: documentBankAccount.openingDay,
      lastOperationDate: documentBankAccount.lastOperationDate ? documentBankAccount.lastOperationDate.format(DATE_TIME_FORMAT) : null,
      active: documentBankAccount.active,
      accountType: documentBankAccount.accountType,
      attachment: documentBankAccount.attachment,
      attachmentContentType: documentBankAccount.attachmentContentType,
      description: documentBankAccount.description,
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

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const documentBankAccount = this.createFromForm();
    if (documentBankAccount.id !== undefined) {
      this.subscribeToSaveResponse(this.documentBankAccountService.update(documentBankAccount));
    } else {
      this.subscribeToSaveResponse(this.documentBankAccountService.create(documentBankAccount));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocumentBankAccount>>): void {
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

  protected createFromForm(): IDocumentBankAccount {
    return {
      ...new DocumentBankAccount(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      bankNumber: this.editForm.get(['bankNumber'])!.value,
      agencyNumber: this.editForm.get(['agencyNumber'])!.value,
      lastOperationDuration: this.editForm.get(['lastOperationDuration'])!.value,
      meanOperationDuration: this.editForm.get(['meanOperationDuration'])!.value,
      balance: this.editForm.get(['balance'])!.value,
      openingDay: this.editForm.get(['openingDay'])!.value,
      lastOperationDate: this.editForm.get(['lastOperationDate'])!.value
        ? dayjs(this.editForm.get(['lastOperationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      active: this.editForm.get(['active'])!.value,
      accountType: this.editForm.get(['accountType'])!.value,
      attachmentContentType: this.editForm.get(['attachmentContentType'])!.value,
      attachment: this.editForm.get(['attachment'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }
}
