import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { FieldTestServiceClassAndJpaFilteringEntityService } from '../service/field-test-service-class-and-jpa-filtering-entity.service';
import { FieldTestServiceClassAndJpaFilteringEntity } from '../field-test-service-class-and-jpa-filtering-entity.model';

import { FieldTestServiceClassAndJpaFilteringEntityComponent } from './field-test-service-class-and-jpa-filtering-entity.component';

describe('Component Tests', () => {
  describe('FieldTestServiceClassAndJpaFilteringEntity Management Component', () => {
    let comp: FieldTestServiceClassAndJpaFilteringEntityComponent;
    let fixture: ComponentFixture<FieldTestServiceClassAndJpaFilteringEntityComponent>;
    let service: FieldTestServiceClassAndJpaFilteringEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FieldTestServiceClassAndJpaFilteringEntityComponent],
      })
        .overrideTemplate(FieldTestServiceClassAndJpaFilteringEntityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldTestServiceClassAndJpaFilteringEntityComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(FieldTestServiceClassAndJpaFilteringEntityService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FieldTestServiceClassAndJpaFilteringEntity('123')],
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
      expect(comp.fieldTestServiceClassAndJpaFilteringEntities?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
