import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestPaginationEntity } from '../field-test-pagination-entity.model';
import { DataUtils } from 'app/core/util/data-util.service';

import { FieldTestPaginationEntityDetailComponent } from './field-test-pagination-entity-detail.component';

describe('Component Tests', () => {
  describe('FieldTestPaginationEntity Management Detail Component', () => {
    let comp: FieldTestPaginationEntityDetailComponent;
    let fixture: ComponentFixture<FieldTestPaginationEntityDetailComponent>;
    let dataUtils: DataUtils;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [FieldTestPaginationEntityDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ fieldTestPaginationEntity: new FieldTestPaginationEntity('123') }) },
          },
        ],
      })
        .overrideTemplate(FieldTestPaginationEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FieldTestPaginationEntityDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = TestBed.inject(DataUtils);
    });

    describe('OnInit', () => {
      it('Should load fieldTestPaginationEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fieldTestPaginationEntity).toEqual(jasmine.objectContaining({ id: '123' }));
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
