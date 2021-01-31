import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import {
  IFieldTestMapstructAndServiceClassEntity,
  FieldTestMapstructAndServiceClassEntity,
} from '../field-test-mapstruct-and-service-class-entity.model';
import { FieldTestMapstructAndServiceClassEntityService } from '../service/field-test-mapstruct-and-service-class-entity.service';

@Injectable({ providedIn: 'root' })
export class FieldTestMapstructAndServiceClassEntityRoutingResolveService implements Resolve<IFieldTestMapstructAndServiceClassEntity> {
  constructor(protected service: FieldTestMapstructAndServiceClassEntityService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFieldTestMapstructAndServiceClassEntity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((fieldTestMapstructAndServiceClassEntity: HttpResponse<FieldTestMapstructAndServiceClassEntity>) => {
          if (fieldTestMapstructAndServiceClassEntity.body) {
            return of(fieldTestMapstructAndServiceClassEntity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FieldTestMapstructAndServiceClassEntity());
  }
}
