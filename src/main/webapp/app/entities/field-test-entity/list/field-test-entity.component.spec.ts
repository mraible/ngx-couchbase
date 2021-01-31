import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { FieldTestEntityService } from '../service/field-test-entity.service';
import { FieldTestEntity } from '../field-test-entity.model';

import { FieldTestEntityComponent } from './field-test-entity.component';

describe('Component Tests', () => {
  describe('FieldTestEntity Management Component', () => {
    let comp: FieldTestEntityComponent;
    let fixture: ComponentFixture<FieldTestEntityComponent>;
    let service: FieldTestEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestEntityComponent],
      })
        .overrideTemplate(FieldTestEntityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestEntityComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestEntityService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FieldTestEntity('123')],
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
      expect(comp.fieldTestEntities?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
