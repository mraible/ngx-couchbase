import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDocumentBankAccount, DocumentBankAccount } from '../document-bank-account.model';
import { DocumentBankAccountService } from '../service/document-bank-account.service';

@Injectable({ providedIn: 'root' })
export class DocumentBankAccountRoutingResolveService implements Resolve<IDocumentBankAccount> {
  constructor(protected service: DocumentBankAccountService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDocumentBankAccount> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((documentBankAccount: HttpResponse<DocumentBankAccount>) => {
          if (documentBankAccount.body) {
            return of(documentBankAccount.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DocumentBankAccount());
  }
}
