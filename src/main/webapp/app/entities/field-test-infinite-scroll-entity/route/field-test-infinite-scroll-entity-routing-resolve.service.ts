import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFieldTestInfiniteScrollEntity, FieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';
import { FieldTestInfiniteScrollEntityService } from '../service/field-test-infinite-scroll-entity.service';

@Injectable({ providedIn: 'root' })
export class FieldTestInfiniteScrollEntityRoutingResolveService implements Resolve<IFieldTestInfiniteScrollEntity> {
  constructor(protected service: FieldTestInfiniteScrollEntityService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFieldTestInfiniteScrollEntity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((fieldTestInfiniteScrollEntity: HttpResponse<FieldTestInfiniteScrollEntity>) => {
          if (fieldTestInfiniteScrollEntity.body) {
            return of(fieldTestInfiniteScrollEntity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FieldTestInfiniteScrollEntity());
  }
}
