import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEntityWithPaginationAndDTO, EntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOService } from '../service/entity-with-pagination-and-dto.service';

@Injectable({ providedIn: 'root' })
export class EntityWithPaginationAndDTORoutingResolveService implements Resolve<IEntityWithPaginationAndDTO> {
  constructor(protected service: EntityWithPaginationAndDTOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntityWithPaginationAndDTO> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((entityWithPaginationAndDTO: HttpResponse<EntityWithPaginationAndDTO>) => {
          if (entityWithPaginationAndDTO.body) {
            return of(entityWithPaginationAndDTO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntityWithPaginationAndDTO());
  }
}
