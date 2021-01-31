import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import {
  IEntityWithServiceClassAndPagination,
  EntityWithServiceClassAndPagination,
} from '../entity-with-service-class-and-pagination.model';
import { EntityWithServiceClassAndPaginationService } from '../service/entity-with-service-class-and-pagination.service';

@Injectable({ providedIn: 'root' })
export class EntityWithServiceClassAndPaginationRoutingResolveService implements Resolve<IEntityWithServiceClassAndPagination> {
  constructor(protected service: EntityWithServiceClassAndPaginationService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntityWithServiceClassAndPagination> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((entityWithServiceClassAndPagination: HttpResponse<EntityWithServiceClassAndPagination>) => {
          if (entityWithServiceClassAndPagination.body) {
            return of(entityWithServiceClassAndPagination.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntityWithServiceClassAndPagination());
  }
}
