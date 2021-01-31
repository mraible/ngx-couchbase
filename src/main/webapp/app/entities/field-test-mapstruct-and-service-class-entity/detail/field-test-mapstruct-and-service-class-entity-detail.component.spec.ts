import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestMapstructAndServiceClassEntity } from '../field-test-mapstruct-and-service-class-entity.model';
import { DataUtils } from 'app/core/util/data-util.service';

import { FieldTestMapstructAndServiceClassEntityDetailComponent } from './field-test-mapstruct-and-service-class-entity-detail.component';

describe('Component Tests', () => {
  describe('FieldTestMapstructAndServiceClassEntity Management Detail Component', () => {
    let comp: FieldTestMapstructAndServiceClassEntityDetailComponent;
    let fixture: ComponentFixture<FieldTestMapstructAndServiceClassEntityDetailComponent>;
    let dataUtils: DataUtils;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [FieldTestMapstructAndServiceClassEntityDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ fieldTestMapstructAndServiceClassEntity: new FieldTestMapstructAndServiceClassEntity('123') }) },
          },
        ],
      })
        .overrideTemplate(FieldTestMapstructAndServiceClassEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FieldTestMapstructAndServiceClassEntityDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = TestBed.inject(DataUtils);
    });

    describe('OnInit', () => {
      it('Should load fieldTestMapstructAndServiceClassEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fieldTestMapstructAndServiceClassEntity).toEqual(jasmine.objectContaining({ id: '123' }));
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
