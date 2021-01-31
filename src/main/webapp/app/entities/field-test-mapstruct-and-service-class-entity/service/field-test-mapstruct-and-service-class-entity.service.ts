import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IFieldTestMapstructAndServiceClassEntity } from '../field-test-mapstruct-and-service-class-entity.model';

export type EntityResponseType = HttpResponse<IFieldTestMapstructAndServiceClassEntity>;
export type EntityArrayResponseType = HttpResponse<IFieldTestMapstructAndServiceClassEntity[]>;

@Injectable({ providedIn: 'root' })
export class FieldTestMapstructAndServiceClassEntityService {
  public resourceUrl = SERVER_API_URL + 'api/field-test-mapstruct-and-service-class-entities';

  constructor(protected http: HttpClient) {}

  create(fieldTestMapstructAndServiceClassEntity: IFieldTestMapstructAndServiceClassEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestMapstructAndServiceClassEntity);
    return this.http
      .post<IFieldTestMapstructAndServiceClassEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fieldTestMapstructAndServiceClassEntity: IFieldTestMapstructAndServiceClassEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestMapstructAndServiceClassEntity);
    return this.http
      .put<IFieldTestMapstructAndServiceClassEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFieldTestMapstructAndServiceClassEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFieldTestMapstructAndServiceClassEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(
    fieldTestMapstructAndServiceClassEntity: IFieldTestMapstructAndServiceClassEntity
  ): IFieldTestMapstructAndServiceClassEntity {
    const copy: IFieldTestMapstructAndServiceClassEntity = Object.assign({}, fieldTestMapstructAndServiceClassEntity, {
      localDateEva: fieldTestMapstructAndServiceClassEntity.localDateEva?.isValid()
        ? fieldTestMapstructAndServiceClassEntity.localDateEva.format(DATE_FORMAT)
        : undefined,
      localDateRequiredEva: fieldTestMapstructAndServiceClassEntity.localDateRequiredEva?.isValid()
        ? fieldTestMapstructAndServiceClassEntity.localDateRequiredEva.format(DATE_FORMAT)
        : undefined,
      instantEva: fieldTestMapstructAndServiceClassEntity.instantEva?.isValid()
        ? fieldTestMapstructAndServiceClassEntity.instantEva.toJSON()
        : undefined,
      instanteRequiredEva: fieldTestMapstructAndServiceClassEntity.instanteRequiredEva?.isValid()
        ? fieldTestMapstructAndServiceClassEntity.instanteRequiredEva.toJSON()
        : undefined,
      zonedDateTimeEva: fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva?.isValid()
        ? fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva.toJSON()
        : undefined,
      zonedDateTimeRequiredEva: fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva?.isValid()
        ? fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva.toJSON()
        : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.localDateEva = res.body.localDateEva ? dayjs(res.body.localDateEva) : undefined;
      res.body.localDateRequiredEva = res.body.localDateRequiredEva ? dayjs(res.body.localDateRequiredEva) : undefined;
      res.body.instantEva = res.body.instantEva ? dayjs(res.body.instantEva) : undefined;
      res.body.instanteRequiredEva = res.body.instanteRequiredEva ? dayjs(res.body.instanteRequiredEva) : undefined;
      res.body.zonedDateTimeEva = res.body.zonedDateTimeEva ? dayjs(res.body.zonedDateTimeEva) : undefined;
      res.body.zonedDateTimeRequiredEva = res.body.zonedDateTimeRequiredEva ? dayjs(res.body.zonedDateTimeRequiredEva) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fieldTestMapstructAndServiceClassEntity: IFieldTestMapstructAndServiceClassEntity) => {
        fieldTestMapstructAndServiceClassEntity.localDateEva = fieldTestMapstructAndServiceClassEntity.localDateEva
          ? dayjs(fieldTestMapstructAndServiceClassEntity.localDateEva)
          : undefined;
        fieldTestMapstructAndServiceClassEntity.localDateRequiredEva = fieldTestMapstructAndServiceClassEntity.localDateRequiredEva
          ? dayjs(fieldTestMapstructAndServiceClassEntity.localDateRequiredEva)
          : undefined;
        fieldTestMapstructAndServiceClassEntity.instantEva = fieldTestMapstructAndServiceClassEntity.instantEva
          ? dayjs(fieldTestMapstructAndServiceClassEntity.instantEva)
          : undefined;
        fieldTestMapstructAndServiceClassEntity.instanteRequiredEva = fieldTestMapstructAndServiceClassEntity.instanteRequiredEva
          ? dayjs(fieldTestMapstructAndServiceClassEntity.instanteRequiredEva)
          : undefined;
        fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva = fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva
          ? dayjs(fieldTestMapstructAndServiceClassEntity.zonedDateTimeEva)
          : undefined;
        fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva = fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva
          ? dayjs(fieldTestMapstructAndServiceClassEntity.zonedDateTimeRequiredEva)
          : undefined;
      });
    }
    return res;
  }
}
