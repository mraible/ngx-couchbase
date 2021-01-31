import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FieldTestServiceClassAndJpaFilteringEntity } from '../field-test-service-class-and-jpa-filtering-entity.model';
import { DataUtils } from 'app/core/util/data-util.service';

import { FieldTestServiceClassAndJpaFilteringEntityDetailComponent } from './field-test-service-class-and-jpa-filtering-entity-detail.component';

describe('Component Tests', () => {
  describe('FieldTestServiceClassAndJpaFilteringEntity Management Detail Component', () => {
    let comp: FieldTestServiceClassAndJpaFilteringEntityDetailComponent;
    let fixture: ComponentFixture<FieldTestServiceClassAndJpaFilteringEntityDetailComponent>;
    let dataUtils: DataUtils;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [FieldTestServiceClassAndJpaFilteringEntityDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ fieldTestServiceClassAndJpaFilteringEntity: new FieldTestServiceClassAndJpaFilteringEntity('123') }) },
          },
        ],
      })
        .overrideTemplate(FieldTestServiceClassAndJpaFilteringEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FieldTestServiceClassAndJpaFilteringEntityDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = TestBed.inject(DataUtils);
    });

    describe('OnInit', () => {
      it('Should load fieldTestServiceClassAndJpaFilteringEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fieldTestServiceClassAndJpaFilteringEntity).toEqual(jasmine.objectContaining({ id: '123' }));
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
