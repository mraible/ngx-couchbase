import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IFieldTestEntity } from '../field-test-entity.model';

export type EntityResponseType = HttpResponse<IFieldTestEntity>;
export type EntityArrayResponseType = HttpResponse<IFieldTestEntity[]>;

@Injectable({ providedIn: 'root' })
export class FieldTestEntityService {
  public resourceUrl = SERVER_API_URL + 'api/field-test-entities';

  constructor(protected http: HttpClient) {}

  create(fieldTestEntity: IFieldTestEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestEntity);
    return this.http
      .post<IFieldTestEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fieldTestEntity: IFieldTestEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestEntity);
    return this.http
      .put<IFieldTestEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFieldTestEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFieldTestEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fieldTestEntity: IFieldTestEntity): IFieldTestEntity {
    const copy: IFieldTestEntity = Object.assign({}, fieldTestEntity, {
      localDateTom: fieldTestEntity.localDateTom?.isValid() ? fieldTestEntity.localDateTom.format(DATE_FORMAT) : undefined,
      localDateRequiredTom: fieldTestEntity.localDateRequiredTom?.isValid()
        ? fieldTestEntity.localDateRequiredTom.format(DATE_FORMAT)
        : undefined,
      instantTom: fieldTestEntity.instantTom?.isValid() ? fieldTestEntity.instantTom.toJSON() : undefined,
      instantRequiredTom: fieldTestEntity.instantRequiredTom?.isValid() ? fieldTestEntity.instantRequiredTom.toJSON() : undefined,
      zonedDateTimeTom: fieldTestEntity.zonedDateTimeTom?.isValid() ? fieldTestEntity.zonedDateTimeTom.toJSON() : undefined,
      zonedDateTimeRequiredTom: fieldTestEntity.zonedDateTimeRequiredTom?.isValid()
        ? fieldTestEntity.zonedDateTimeRequiredTom.toJSON()
        : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.localDateTom = res.body.localDateTom ? dayjs(res.body.localDateTom) : undefined;
      res.body.localDateRequiredTom = res.body.localDateRequiredTom ? dayjs(res.body.localDateRequiredTom) : undefined;
      res.body.instantTom = res.body.instantTom ? dayjs(res.body.instantTom) : undefined;
      res.body.instantRequiredTom = res.body.instantRequiredTom ? dayjs(res.body.instantRequiredTom) : undefined;
      res.body.zonedDateTimeTom = res.body.zonedDateTimeTom ? dayjs(res.body.zonedDateTimeTom) : undefined;
      res.body.zonedDateTimeRequiredTom = res.body.zonedDateTimeRequiredTom ? dayjs(res.body.zonedDateTimeRequiredTom) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fieldTestEntity: IFieldTestEntity) => {
        fieldTestEntity.localDateTom = fieldTestEntity.localDateTom ? dayjs(fieldTestEntity.localDateTom) : undefined;
        fieldTestEntity.localDateRequiredTom = fieldTestEntity.localDateRequiredTom
          ? dayjs(fieldTestEntity.localDateRequiredTom)
          : undefined;
        fieldTestEntity.instantTom = fieldTestEntity.instantTom ? dayjs(fieldTestEntity.instantTom) : undefined;
        fieldTestEntity.instantRequiredTom = fieldTestEntity.instantRequiredTom ? dayjs(fieldTestEntity.instantRequiredTom) : undefined;
        fieldTestEntity.zonedDateTimeTom = fieldTestEntity.zonedDateTimeTom ? dayjs(fieldTestEntity.zonedDateTimeTom) : undefined;
        fieldTestEntity.zonedDateTimeRequiredTom = fieldTestEntity.zonedDateTimeRequiredTom
          ? dayjs(fieldTestEntity.zonedDateTimeRequiredTom)
          : undefined;
      });
    }
    return res;
  }
}
