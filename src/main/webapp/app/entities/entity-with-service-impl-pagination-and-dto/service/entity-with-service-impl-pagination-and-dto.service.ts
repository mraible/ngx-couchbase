import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IEntityWithServiceImplPaginationAndDTO } from '../entity-with-service-impl-pagination-and-dto.model';

export type EntityResponseType = HttpResponse<IEntityWithServiceImplPaginationAndDTO>;
export type EntityArrayResponseType = HttpResponse<IEntityWithServiceImplPaginationAndDTO[]>;

@Injectable({ providedIn: 'root' })
export class EntityWithServiceImplPaginationAndDTOService {
  public resourceUrl = SERVER_API_URL + 'api/entity-with-service-impl-pagination-and-dtos';

  constructor(protected http: HttpClient) {}

  create(entityWithServiceImplPaginationAndDTO: IEntityWithServiceImplPaginationAndDTO): Observable<EntityResponseType> {
    return this.http.post<IEntityWithServiceImplPaginationAndDTO>(this.resourceUrl, entityWithServiceImplPaginationAndDTO, {
      observe: 'response',
    });
  }

  update(entityWithServiceImplPaginationAndDTO: IEntityWithServiceImplPaginationAndDTO): Observable<EntityResponseType> {
    return this.http.put<IEntityWithServiceImplPaginationAndDTO>(this.resourceUrl, entityWithServiceImplPaginationAndDTO, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEntityWithServiceImplPaginationAndDTO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityWithServiceImplPaginationAndDTO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
