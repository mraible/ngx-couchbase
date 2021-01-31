import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDocumentBankAccount } from '../document-bank-account.model';
import { DocumentBankAccountService } from '../service/document-bank-account.service';
import { DocumentBankAccountDeleteDialogComponent } from '../delete/document-bank-account-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-document-bank-account',
  templateUrl: './document-bank-account.component.html',
})
export class DocumentBankAccountComponent implements OnInit {
  documentBankAccounts?: IDocumentBankAccount[];
  isLoading = false;

  constructor(
    protected documentBankAccountService: DocumentBankAccountService,
    protected dataUtils: DataUtils,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.isLoading = true;

    this.documentBankAccountService.query().subscribe(
      (res: HttpResponse<IDocumentBankAccount[]>) => {
        this.isLoading = false;
        this.documentBankAccounts = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IDocumentBankAccount): string {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    return this.dataUtils.openFile(base64String, contentType);
  }

  delete(documentBankAccount: IDocumentBankAccount): void {
    const modalRef = this.modalService.open(DocumentBankAccountDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.documentBankAccount = documentBankAccount;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
