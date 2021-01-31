import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IEntityWithDTO } from '../entity-with-dto.model';

export type EntityResponseType = HttpResponse<IEntityWithDTO>;
export type EntityArrayResponseType = HttpResponse<IEntityWithDTO[]>;

@Injectable({ providedIn: 'root' })
export class EntityWithDTOService {
  public resourceUrl = SERVER_API_URL + 'api/entity-with-dtos';

  constructor(protected http: HttpClient) {}

  create(entityWithDTO: IEntityWithDTO): Observable<EntityResponseType> {
    return this.http.post<IEntityWithDTO>(this.resourceUrl, entityWithDTO, { observe: 'response' });
  }

  update(entityWithDTO: IEntityWithDTO): Observable<EntityResponseType> {
    return this.http.put<IEntityWithDTO>(this.resourceUrl, entityWithDTO, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IEntityWithDTO>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityWithDTO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
