import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestEntity } from '../field-test-entity.model';
import { DataUtils } from 'app/core/util/data-util.service';

import { FieldTestEntityDetailComponent } from './field-test-entity-detail.component';

describe('Component Tests', () => {
  describe('FieldTestEntity Management Detail Component', () => {
    let comp: FieldTestEntityDetailComponent;
    let fixture: ComponentFixture<FieldTestEntityDetailComponent>;
    let dataUtils: DataUtils;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [FieldTestEntityDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ fieldTestEntity: new FieldTestEntity('123') }) },
          },
        ],
      })
        .overrideTemplate(FieldTestEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FieldTestEntityDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = TestBed.inject(DataUtils);
    });

    describe('OnInit', () => {
      it('Should load fieldTestEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fieldTestEntity).toEqual(jasmine.objectContaining({ id: '123' }));
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
