import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IEntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';

export type EntityResponseType = HttpResponse<IEntityWithServiceImplAndDTO>;
export type EntityArrayResponseType = HttpResponse<IEntityWithServiceImplAndDTO[]>;

@Injectable({ providedIn: 'root' })
export class EntityWithServiceImplAndDTOService {
  public resourceUrl = SERVER_API_URL + 'api/entity-with-service-impl-and-dtos';

  constructor(protected http: HttpClient) {}

  create(entityWithServiceImplAndDTO: IEntityWithServiceImplAndDTO): Observable<EntityResponseType> {
    return this.http.post<IEntityWithServiceImplAndDTO>(this.resourceUrl, entityWithServiceImplAndDTO, { observe: 'response' });
  }

  update(entityWithServiceImplAndDTO: IEntityWithServiceImplAndDTO): Observable<EntityResponseType> {
    return this.http.put<IEntityWithServiceImplAndDTO>(this.resourceUrl, entityWithServiceImplAndDTO, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEntityWithServiceImplAndDTO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityWithServiceImplAndDTO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
