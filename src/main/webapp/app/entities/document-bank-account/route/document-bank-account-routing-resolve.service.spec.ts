jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IDocumentBankAccount, DocumentBankAccount } from '../document-bank-account.model';
import { DocumentBankAccountService } from '../service/document-bank-account.service';

import { DocumentBankAccountRoutingResolveService } from './document-bank-account-routing-resolve.service';

describe('Service Tests', () => {
  describe('DocumentBankAccount routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: DocumentBankAccountRoutingResolveService;
    let service: DocumentBankAccountService;
    let resultDocumentBankAccount: IDocumentBankAccount | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(DocumentBankAccountRoutingResolveService);
      service = TestBed.inject(DocumentBankAccountService);
      resultDocumentBankAccount = undefined;
    });

    describe('resolve', () => {
      it('should return existing IDocumentBankAccount for existing id', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: new DocumentBankAccount(id) })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultDocumentBankAccount = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultDocumentBankAccount).toEqual(new DocumentBankAccount('123'));
      });

      it('should return new IDocumentBankAccount if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultDocumentBankAccount = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultDocumentBankAccount).toEqual(new DocumentBankAccount());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: '123' };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultDocumentBankAccount = result;
        });

        // THEN
        expect(service.find).toBeCalledWith('123');
        expect(resultDocumentBankAccount).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
