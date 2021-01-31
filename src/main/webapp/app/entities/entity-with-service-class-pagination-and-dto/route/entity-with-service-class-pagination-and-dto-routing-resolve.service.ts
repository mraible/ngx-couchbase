import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import {
  IEntityWithServiceClassPaginationAndDTO,
  EntityWithServiceClassPaginationAndDTO,
} from '../entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOService } from '../service/entity-with-service-class-pagination-and-dto.service';

@Injectable({ providedIn: 'root' })
export class EntityWithServiceClassPaginationAndDTORoutingResolveService implements Resolve<IEntityWithServiceClassPaginationAndDTO> {
  constructor(protected service: EntityWithServiceClassPaginationAndDTOService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntityWithServiceClassPaginationAndDTO> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((entityWithServiceClassPaginationAndDTO: HttpResponse<EntityWithServiceClassPaginationAndDTO>) => {
          if (entityWithServiceClassPaginationAndDTO.body) {
            return of(entityWithServiceClassPaginationAndDTO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntityWithServiceClassPaginationAndDTO());
  }
}
