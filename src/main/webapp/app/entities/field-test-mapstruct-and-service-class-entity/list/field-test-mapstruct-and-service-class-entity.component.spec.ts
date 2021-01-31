import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { FieldTestMapstructAndServiceClassEntityService } from '../service/field-test-mapstruct-and-service-class-entity.service';
import { FieldTestMapstructAndServiceClassEntity } from '../field-test-mapstruct-and-service-class-entity.model';

import { FieldTestMapstructAndServiceClassEntityComponent } from './field-test-mapstruct-and-service-class-entity.component';

describe('Component Tests', () => {
  describe('FieldTestMapstructAndServiceClassEntity Management Component', () => {
    let comp: FieldTestMapstructAndServiceClassEntityComponent;
    let fixture: ComponentFixture<FieldTestMapstructAndServiceClassEntityComponent>;
    let service: FieldTestMapstructAndServiceClassEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestMapstructAndServiceClassEntityComponent],
      })
        .overrideTemplate(FieldTestMapstructAndServiceClassEntityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestMapstructAndServiceClassEntityComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestMapstructAndServiceClassEntityService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FieldTestMapstructAndServiceClassEntity('123')],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fieldTestMapstructAndServiceClassEntities?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
