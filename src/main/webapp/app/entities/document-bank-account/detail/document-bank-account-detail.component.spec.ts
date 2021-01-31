import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DocumentBankAccount } from '../document-bank-account.model';
import { DataUtils } from 'app/core/util/data-util.service';

import { DocumentBankAccountDetailComponent } from './document-bank-account-detail.component';

describe('Component Tests', () => {
  describe('DocumentBankAccount Management Detail Component', () => {
    let comp: DocumentBankAccountDetailComponent;
    let fixture: ComponentFixture<DocumentBankAccountDetailComponent>;
    let dataUtils: DataUtils;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [DocumentBankAccountDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ documentBankAccount: new DocumentBankAccount('123') }) },
          },
        ],
      })
        .overrideTemplate(DocumentBankAccountDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DocumentBankAccountDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = TestBed.inject(DataUtils);
    });

    describe('OnInit', () => {
      it('Should load documentBankAccount on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.documentBankAccount).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from DataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from DataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeBase64, fakeContentType);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeBase64, fakeContentType);
      });
    });
  });
});
