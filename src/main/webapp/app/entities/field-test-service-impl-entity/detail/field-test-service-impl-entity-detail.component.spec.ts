import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';
import { DataUtils } from 'app/core/util/data-util.service';

import { FieldTestServiceImplEntityDetailComponent } from './field-test-service-impl-entity-detail.component';

describe('Component Tests', () => {
  describe('FieldTestServiceImplEntity Management Detail Component', () => {
    let comp: FieldTestServiceImplEntityDetailComponent;
    let fixture: ComponentFixture<FieldTestServiceImplEntityDetailComponent>;
    let dataUtils: DataUtils;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [FieldTestServiceImplEntityDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ fieldTestServiceImplEntity: new FieldTestServiceImplEntity('123') }) },
          },
        ],
      })
        .overrideTemplate(FieldTestServiceImplEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FieldTestServiceImplEntityDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = TestBed.inject(DataUtils);
    });

    describe('OnInit', () => {
      it('Should load fieldTestServiceImplEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fieldTestServiceImplEntity).toEqual(jasmine.objectContaining({ id: '123' }));
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
