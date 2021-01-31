import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IFieldTestPaginationEntity } from '../field-test-pagination-entity.model';

export type EntityResponseType = HttpResponse<IFieldTestPaginationEntity>;
export type EntityArrayResponseType = HttpResponse<IFieldTestPaginationEntity[]>;

@Injectable({ providedIn: 'root' })
export class FieldTestPaginationEntityService {
  public resourceUrl = SERVER_API_URL + 'api/field-test-pagination-entities';

  constructor(protected http: HttpClient) {}

  create(fieldTestPaginationEntity: IFieldTestPaginationEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestPaginationEntity);
    return this.http
      .post<IFieldTestPaginationEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fieldTestPaginationEntity: IFieldTestPaginationEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestPaginationEntity);
    return this.http
      .put<IFieldTestPaginationEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFieldTestPaginationEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFieldTestPaginationEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fieldTestPaginationEntity: IFieldTestPaginationEntity): IFieldTestPaginationEntity {
    const copy: IFieldTestPaginationEntity = Object.assign({}, fieldTestPaginationEntity, {
      localDateAlice: fieldTestPaginationEntity.localDateAlice?.isValid()
        ? fieldTestPaginationEntity.localDateAlice.format(DATE_FORMAT)
        : undefined,
      localDateRequiredAlice: fieldTestPaginationEntity.localDateRequiredAlice?.isValid()
        ? fieldTestPaginationEntity.localDateRequiredAlice.format(DATE_FORMAT)
        : undefined,
      instantAlice: fieldTestPaginationEntity.instantAlice?.isValid() ? fieldTestPaginationEntity.instantAlice.toJSON() : undefined,
      instanteRequiredAlice: fieldTestPaginationEntity.instanteRequiredAlice?.isValid()
        ? fieldTestPaginationEntity.instanteRequiredAlice.toJSON()
        : undefined,
      zonedDateTimeAlice: fieldTestPaginationEntity.zonedDateTimeAlice?.isValid()
        ? fieldTestPaginationEntity.zonedDateTimeAlice.toJSON()
        : undefined,
      zonedDateTimeRequiredAlice: fieldTestPaginationEntity.zonedDateTimeRequiredAlice?.isValid()
        ? fieldTestPaginationEntity.zonedDateTimeRequiredAlice.toJSON()
        : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.localDateAlice = res.body.localDateAlice ? dayjs(res.body.localDateAlice) : undefined;
      res.body.localDateRequiredAlice = res.body.localDateRequiredAlice ? dayjs(res.body.localDateRequiredAlice) : undefined;
      res.body.instantAlice = res.body.instantAlice ? dayjs(res.body.instantAlice) : undefined;
      res.body.instanteRequiredAlice = res.body.instanteRequiredAlice ? dayjs(res.body.instanteRequiredAlice) : undefined;
      res.body.zonedDateTimeAlice = res.body.zonedDateTimeAlice ? dayjs(res.body.zonedDateTimeAlice) : undefined;
      res.body.zonedDateTimeRequiredAlice = res.body.zonedDateTimeRequiredAlice ? dayjs(res.body.zonedDateTimeRequiredAlice) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fieldTestPaginationEntity: IFieldTestPaginationEntity) => {
        fieldTestPaginationEntity.localDateAlice = fieldTestPaginationEntity.localDateAlice
          ? dayjs(fieldTestPaginationEntity.localDateAlice)
          : undefined;
        fieldTestPaginationEntity.localDateRequiredAlice = fieldTestPaginationEntity.localDateRequiredAlice
          ? dayjs(fieldTestPaginationEntity.localDateRequiredAlice)
          : undefined;
        fieldTestPaginationEntity.instantAlice = fieldTestPaginationEntity.instantAlice
          ? dayjs(fieldTestPaginationEntity.instantAlice)
          : undefined;
        fieldTestPaginationEntity.instanteRequiredAlice = fieldTestPaginationEntity.instanteRequiredAlice
          ? dayjs(fieldTestPaginationEntity.instanteRequiredAlice)
          : undefined;
        fieldTestPaginationEntity.zonedDateTimeAlice = fieldTestPaginationEntity.zonedDateTimeAlice
          ? dayjs(fieldTestPaginationEntity.zonedDateTimeAlice)
          : undefined;
        fieldTestPaginationEntity.zonedDateTimeRequiredAlice = fieldTestPaginationEntity.zonedDateTimeRequiredAlice
          ? dayjs(fieldTestPaginationEntity.zonedDateTimeRequiredAlice)
          : undefined;
      });
    }
    return res;
  }
}
