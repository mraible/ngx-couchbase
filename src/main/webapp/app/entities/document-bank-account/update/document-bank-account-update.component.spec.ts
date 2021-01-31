jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DocumentBankAccountService } from '../service/document-bank-account.service';
import { DocumentBankAccount } from '../document-bank-account.model';

import { DocumentBankAccountUpdateComponent } from './document-bank-account-update.component';

describe('Component Tests', () => {
  describe('DocumentBankAccount Management Update Component', () => {
    let comp: DocumentBankAccountUpdateComponent;
    let fixture: ComponentFixture<DocumentBankAccountUpdateComponent>;
    let service: DocumentBankAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [DocumentBankAccountUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(DocumentBankAccountUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DocumentBankAccountUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(DocumentBankAccountService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DocumentBankAccount('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DocumentBankAccount();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
