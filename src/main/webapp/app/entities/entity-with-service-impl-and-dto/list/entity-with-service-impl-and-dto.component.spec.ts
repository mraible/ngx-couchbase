import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EntityWithServiceImplAndDTOService } from '../service/entity-with-service-impl-and-dto.service';
import { EntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';

import { EntityWithServiceImplAndDTOComponent } from './entity-with-service-impl-and-dto.component';

describe('Component Tests', () => {
  describe('EntityWithServiceImplAndDTO Management Component', () => {
    let comp: EntityWithServiceImplAndDTOComponent;
    let fixture: ComponentFixture<EntityWithServiceImplAndDTOComponent>;
    let service: EntityWithServiceImplAndDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithServiceImplAndDTOComponent],
      })
        .overrideTemplate(EntityWithServiceImplAndDTOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithServiceImplAndDTOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithServiceImplAndDTOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityWithServiceImplAndDTO('123')],
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
      expect(comp.entityWithServiceImplAndDTOS?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
