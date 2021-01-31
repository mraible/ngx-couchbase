import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IEntityWithServiceClassAndPagination } from '../entity-with-service-class-and-pagination.model';

export type EntityResponseType = HttpResponse<IEntityWithServiceClassAndPagination>;
export type EntityArrayResponseType = HttpResponse<IEntityWithServiceClassAndPagination[]>;

@Injectable({ providedIn: 'root' })
export class EntityWithServiceClassAndPaginationService {
  public resourceUrl = SERVER_API_URL + 'api/entity-with-service-class-and-paginations';

  constructor(protected http: HttpClient) {}

  create(entityWithServiceClassAndPagination: IEntityWithServiceClassAndPagination): Observable<EntityResponseType> {
    return this.http.post<IEntityWithServiceClassAndPagination>(this.resourceUrl, entityWithServiceClassAndPagination, {
      observe: 'response',
    });
  }

  update(entityWithServiceClassAndPagination: IEntityWithServiceClassAndPagination): Observable<EntityResponseType> {
    return this.http.put<IEntityWithServiceClassAndPagination>(this.resourceUrl, entityWithServiceClassAndPagination, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEntityWithServiceClassAndPagination>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityWithServiceClassAndPagination[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
