import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IFieldTestServiceImplEntity } from '../field-test-service-impl-entity.model';

export type EntityResponseType = HttpResponse<IFieldTestServiceImplEntity>;
export type EntityArrayResponseType = HttpResponse<IFieldTestServiceImplEntity[]>;

@Injectable({ providedIn: 'root' })
export class FieldTestServiceImplEntityService {
  public resourceUrl = SERVER_API_URL + 'api/field-test-service-impl-entities';

  constructor(protected http: HttpClient) {}

  create(fieldTestServiceImplEntity: IFieldTestServiceImplEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestServiceImplEntity);
    return this.http
      .post<IFieldTestServiceImplEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fieldTestServiceImplEntity: IFieldTestServiceImplEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestServiceImplEntity);
    return this.http
      .put<IFieldTestServiceImplEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFieldTestServiceImplEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFieldTestServiceImplEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fieldTestServiceImplEntity: IFieldTestServiceImplEntity): IFieldTestServiceImplEntity {
    const copy: IFieldTestServiceImplEntity = Object.assign({}, fieldTestServiceImplEntity, {
      localDateMika: fieldTestServiceImplEntity.localDateMika?.isValid()
        ? fieldTestServiceImplEntity.localDateMika.format(DATE_FORMAT)
        : undefined,
      localDateRequiredMika: fieldTestServiceImplEntity.localDateRequiredMika?.isValid()
        ? fieldTestServiceImplEntity.localDateRequiredMika.format(DATE_FORMAT)
        : undefined,
      instantMika: fieldTestServiceImplEntity.instantMika?.isValid() ? fieldTestServiceImplEntity.instantMika.toJSON() : undefined,
      instanteRequiredMika: fieldTestServiceImplEntity.instanteRequiredMika?.isValid()
        ? fieldTestServiceImplEntity.instanteRequiredMika.toJSON()
        : undefined,
      zonedDateTimeMika: fieldTestServiceImplEntity.zonedDateTimeMika?.isValid()
        ? fieldTestServiceImplEntity.zonedDateTimeMika.toJSON()
        : undefined,
      zonedDateTimeRequiredMika: fieldTestServiceImplEntity.zonedDateTimeRequiredMika?.isValid()
        ? fieldTestServiceImplEntity.zonedDateTimeRequiredMika.toJSON()
        : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.localDateMika = res.body.localDateMika ? dayjs(res.body.localDateMika) : undefined;
      res.body.localDateRequiredMika = res.body.localDateRequiredMika ? dayjs(res.body.localDateRequiredMika) : undefined;
      res.body.instantMika = res.body.instantMika ? dayjs(res.body.instantMika) : undefined;
      res.body.instanteRequiredMika = res.body.instanteRequiredMika ? dayjs(res.body.instanteRequiredMika) : undefined;
      res.body.zonedDateTimeMika = res.body.zonedDateTimeMika ? dayjs(res.body.zonedDateTimeMika) : undefined;
      res.body.zonedDateTimeRequiredMika = res.body.zonedDateTimeRequiredMika ? dayjs(res.body.zonedDateTimeRequiredMika) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fieldTestServiceImplEntity: IFieldTestServiceImplEntity) => {
        fieldTestServiceImplEntity.localDateMika = fieldTestServiceImplEntity.localDateMika
          ? dayjs(fieldTestServiceImplEntity.localDateMika)
          : undefined;
        fieldTestServiceImplEntity.localDateRequiredMika = fieldTestServiceImplEntity.localDateRequiredMika
          ? dayjs(fieldTestServiceImplEntity.localDateRequiredMika)
          : undefined;
        fieldTestServiceImplEntity.instantMika = fieldTestServiceImplEntity.instantMika
          ? dayjs(fieldTestServiceImplEntity.instantMika)
          : undefined;
        fieldTestServiceImplEntity.instanteRequiredMika = fieldTestServiceImplEntity.instanteRequiredMika
          ? dayjs(fieldTestServiceImplEntity.instanteRequiredMika)
          : undefined;
        fieldTestServiceImplEntity.zonedDateTimeMika = fieldTestServiceImplEntity.zonedDateTimeMika
          ? dayjs(fieldTestServiceImplEntity.zonedDateTimeMika)
          : undefined;
        fieldTestServiceImplEntity.zonedDateTimeRequiredMika = fieldTestServiceImplEntity.zonedDateTimeRequiredMika
          ? dayjs(fieldTestServiceImplEntity.zonedDateTimeRequiredMika)
          : undefined;
      });
    }
    return res;
  }
}
