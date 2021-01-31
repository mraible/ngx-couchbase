import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IEntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';

export type EntityResponseType = HttpResponse<IEntityWithPaginationAndDTO>;
export type EntityArrayResponseType = HttpResponse<IEntityWithPaginationAndDTO[]>;

@Injectable({ providedIn: 'root' })
export class EntityWithPaginationAndDTOService {
  public resourceUrl = SERVER_API_URL + 'api/entity-with-pagination-and-dtos';

  constructor(protected http: HttpClient) {}

  create(entityWithPaginationAndDTO: IEntityWithPaginationAndDTO): Observable<EntityResponseType> {
    return this.http.post<IEntityWithPaginationAndDTO>(this.resourceUrl, entityWithPaginationAndDTO, { observe: 'response' });
  }

  update(entityWithPaginationAndDTO: IEntityWithPaginationAndDTO): Observable<EntityResponseType> {
    return this.http.put<IEntityWithPaginationAndDTO>(this.resourceUrl, entityWithPaginationAndDTO, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEntityWithPaginationAndDTO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityWithPaginationAndDTO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
