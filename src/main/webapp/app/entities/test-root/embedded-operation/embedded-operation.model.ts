import * as dayjs from 'dayjs';
import { IDocumentBankAccount } from 'app/entities/document-bank-account/document-bank-account.model';

export interface IEmbeddedOperation {
  date?: dayjs.Dayjs;
  description?: string | null;
  amount?: number;
  documentBankAccount?: IDocumentBankAccount | null;
}

export class EmbeddedOperation implements IEmbeddedOperation {
  constructor(
    public date?: dayjs.Dayjs,
    public description?: string | null,
    public amount?: number,
    public documentBankAccount?: IDocumentBankAccount | null
  ) {}
}
