import * as dayjs from 'dayjs';
import { IEmbeddedOperation } from 'app/entities/test-root/embedded-operation/embedded-operation.model';
import { BankAccountType } from 'app/entities/enumerations/bank-account-type.model';

export interface IDocumentBankAccount {
  id?: string;
  name?: string;
  bankNumber?: number | null;
  agencyNumber?: number | null;
  lastOperationDuration?: number | null;
  meanOperationDuration?: number | null;
  balance?: number;
  openingDay?: dayjs.Dayjs | null;
  lastOperationDate?: dayjs.Dayjs | null;
  active?: boolean | null;
  accountType?: BankAccountType | null;
  attachmentContentType?: string | null;
  attachment?: string | null;
  description?: string | null;
  embeddedOperations?: IEmbeddedOperation[] | null;
}

export class DocumentBankAccount implements IDocumentBankAccount {
  constructor(
    public id?: string,
    public name?: string,
    public bankNumber?: number | null,
    public agencyNumber?: number | null,
    public lastOperationDuration?: number | null,
    public meanOperationDuration?: number | null,
    public balance?: number,
    public openingDay?: dayjs.Dayjs | null,
    public lastOperationDate?: dayjs.Dayjs | null,
    public active?: boolean | null,
    public accountType?: BankAccountType | null,
    public attachmentContentType?: string | null,
    public attachment?: string | null,
    public description?: string | null,
    public embeddedOperations?: IEmbeddedOperation[] | null
  ) {
    this.active = this.active ?? false;
  }
}
