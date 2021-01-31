import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEntityWithServiceImplAndDTO, EntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOService } from '../service/entity-with-service-impl-and-dto.service';

@Injectable({ providedIn: 'root' })
export class EntityWithServiceImplAndDTORoutingResolveService implements Resolve<IEntityWithServiceImplAndDTO> {
  constructor(protected service: EntityWithServiceImplAndDTOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntityWithServiceImplAndDTO> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((entityWithServiceImplAndDTO: HttpResponse<EntityWithServiceImplAndDTO>) => {
          if (entityWithServiceImplAndDTO.body) {
            return of(entityWithServiceImplAndDTO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntityWithServiceImplAndDTO());
  }
}
