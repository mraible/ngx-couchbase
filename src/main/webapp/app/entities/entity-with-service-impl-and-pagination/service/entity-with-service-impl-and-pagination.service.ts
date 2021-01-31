import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IEntityWithServiceImplAndPagination } from '../entity-with-service-impl-and-pagination.model';

export type EntityResponseType = HttpResponse<IEntityWithServiceImplAndPagination>;
export type EntityArrayResponseType = HttpResponse<IEntityWithServiceImplAndPagination[]>;

@Injectable({ providedIn: 'root' })
export class EntityWithServiceImplAndPaginationService {
  public resourceUrl = SERVER_API_URL + 'api/entity-with-service-impl-and-paginations';

  constructor(protected http: HttpClient) {}

  create(entityWithServiceImplAndPagination: IEntityWithServiceImplAndPagination): Observable<EntityResponseType> {
    return this.http.post<IEntityWithServiceImplAndPagination>(this.resourceUrl, entityWithServiceImplAndPagination, {
      observe: 'response',
    });
  }

  update(entityWithServiceImplAndPagination: IEntityWithServiceImplAndPagination): Observable<EntityResponseType> {
    return this.http.put<IEntityWithServiceImplAndPagination>(this.resourceUrl, entityWithServiceImplAndPagination, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEntityWithServiceImplAndPagination>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityWithServiceImplAndPagination[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
