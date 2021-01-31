import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { FieldTestServiceImplEntityService } from '../service/field-test-service-impl-entity.service';
import { FieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';

import { FieldTestServiceImplEntityComponent } from './field-test-service-impl-entity.component';

describe('Component Tests', () => {
  describe('FieldTestServiceImplEntity Management Component', () => {
    let comp: FieldTestServiceImplEntityComponent;
    let fixture: ComponentFixture<FieldTestServiceImplEntityComponent>;
    let service: FieldTestServiceImplEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestServiceImplEntityComponent],
      })
        .overrideTemplate(FieldTestServiceImplEntityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestServiceImplEntityComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestServiceImplEntityService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FieldTestServiceImplEntity('123')],
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
      expect(comp.fieldTestServiceImplEntities?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
