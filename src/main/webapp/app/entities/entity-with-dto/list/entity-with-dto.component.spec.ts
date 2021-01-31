import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EntityWithDTOService } from '../service/entity-with-dto.service';
import { EntityWithDTO } from '../entity-with-dto.model';

import { EntityWithDTOComponent } from './entity-with-dto.component';

describe('Component Tests', () => {
  describe('EntityWithDTO Management Component', () => {
    let comp: EntityWithDTOComponent;
    let fixture: ComponentFixture<EntityWithDTOComponent>;
    let service: EntityWithDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithDTOComponent],
      })
        .overrideTemplate(EntityWithDTOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithDTOComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithDTOService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityWithDTO('123')],
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
      expect(comp.entityWithDTOS?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
