import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import {
  IFieldTestServiceClassAndJpaFilteringEntity,
  FieldTestServiceClassAndJpaFilteringEntity,
} from '../field-test-service-class-and-jpa-filtering-entity.model';
import { FieldTestServiceClassAndJpaFilteringEntityService } from '../service/field-test-service-class-and-jpa-filtering-entity.service';

@Injectable({ providedIn: 'root' })
export class FieldTestServiceClassAndJpaFilteringEntityRoutingResolveService
  implements Resolve<IFieldTestServiceClassAndJpaFilteringEntity> {
  constructor(protected service: FieldTestServiceClassAndJpaFilteringEntityService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFieldTestServiceClassAndJpaFilteringEntity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((fieldTestServiceClassAndJpaFilteringEntity: HttpResponse<FieldTestServiceClassAndJpaFilteringEntity>) => {
          if (fieldTestServiceClassAndJpaFilteringEntity.body) {
            return of(fieldTestServiceClassAndJpaFilteringEntity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FieldTestServiceClassAndJpaFilteringEntity());
  }
}
