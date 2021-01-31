import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IDocumentBankAccount } from '../document-bank-account.model';

export type EntityResponseType = HttpResponse<IDocumentBankAccount>;
export type EntityArrayResponseType = HttpResponse<IDocumentBankAccount[]>;

@Injectable({ providedIn: 'root' })
export class DocumentBankAccountService {
  public resourceUrl = SERVER_API_URL + 'api/document-bank-accounts';

  constructor(protected http: HttpClient) {}

  create(documentBankAccount: IDocumentBankAccount): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(documentBankAccount);
    return this.http
      .post<IDocumentBankAccount>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(documentBankAccount: IDocumentBankAccount): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(documentBankAccount);
    return this.http
      .put<IDocumentBankAccount>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IDocumentBankAccount>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDocumentBankAccount[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(documentBankAccount: IDocumentBankAccount): IDocumentBankAccount {
    const copy: IDocumentBankAccount = Object.assign({}, documentBankAccount, {
      openingDay: documentBankAccount.openingDay?.isValid() ? documentBankAccount.openingDay.format(DATE_FORMAT) : undefined,
      lastOperationDate: documentBankAccount.lastOperationDate?.isValid() ? documentBankAccount.lastOperationDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.openingDay = res.body.openingDay ? dayjs(res.body.openingDay) : undefined;
      res.body.lastOperationDate = res.body.lastOperationDate ? dayjs(res.body.lastOperationDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((documentBankAccount: IDocumentBankAccount) => {
        documentBankAccount.openingDay = documentBankAccount.openingDay ? dayjs(documentBankAccount.openingDay) : undefined;
        documentBankAccount.lastOperationDate = documentBankAccount.lastOperationDate
          ? dayjs(documentBankAccount.lastOperationDate)
          : undefined;
      });
    }
    return res;
  }
}
