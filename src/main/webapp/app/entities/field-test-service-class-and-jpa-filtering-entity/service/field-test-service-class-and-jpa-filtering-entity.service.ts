import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IFieldTestServiceClassAndJpaFilteringEntity } from '../field-test-service-class-and-jpa-filtering-entity.model';

export type EntityResponseType = HttpResponse<IFieldTestServiceClassAndJpaFilteringEntity>;
export type EntityArrayResponseType = HttpResponse<IFieldTestServiceClassAndJpaFilteringEntity[]>;

@Injectable({ providedIn: 'root' })
export class FieldTestServiceClassAndJpaFilteringEntityService {
  public resourceUrl = SERVER_API_URL + 'api/field-test-service-class-and-jpa-filtering-entities';

  constructor(protected http: HttpClient) {}

  create(fieldTestServiceClassAndJpaFilteringEntity: IFieldTestServiceClassAndJpaFilteringEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestServiceClassAndJpaFilteringEntity);
    return this.http
      .post<IFieldTestServiceClassAndJpaFilteringEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fieldTestServiceClassAndJpaFilteringEntity: IFieldTestServiceClassAndJpaFilteringEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestServiceClassAndJpaFilteringEntity);
    return this.http
      .put<IFieldTestServiceClassAndJpaFilteringEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFieldTestServiceClassAndJpaFilteringEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFieldTestServiceClassAndJpaFilteringEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(
    fieldTestServiceClassAndJpaFilteringEntity: IFieldTestServiceClassAndJpaFilteringEntity
  ): IFieldTestServiceClassAndJpaFilteringEntity {
    const copy: IFieldTestServiceClassAndJpaFilteringEntity = Object.assign({}, fieldTestServiceClassAndJpaFilteringEntity, {
      localDateBob: fieldTestServiceClassAndJpaFilteringEntity.localDateBob?.isValid()
        ? fieldTestServiceClassAndJpaFilteringEntity.localDateBob.format(DATE_FORMAT)
        : undefined,
      localDateRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.localDateRequiredBob?.isValid()
        ? fieldTestServiceClassAndJpaFilteringEntity.localDateRequiredBob.format(DATE_FORMAT)
        : undefined,
      instantBob: fieldTestServiceClassAndJpaFilteringEntity.instantBob?.isValid()
        ? fieldTestServiceClassAndJpaFilteringEntity.instantBob.toJSON()
        : undefined,
      instanteRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob?.isValid()
        ? fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob.toJSON()
        : undefined,
      zonedDateTimeBob: fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob?.isValid()
        ? fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob.toJSON()
        : undefined,
      zonedDateTimeRequiredBob: fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob?.isValid()
        ? fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob.toJSON()
        : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.localDateBob = res.body.localDateBob ? dayjs(res.body.localDateBob) : undefined;
      res.body.localDateRequiredBob = res.body.localDateRequiredBob ? dayjs(res.body.localDateRequiredBob) : undefined;
      res.body.instantBob = res.body.instantBob ? dayjs(res.body.instantBob) : undefined;
      res.body.instanteRequiredBob = res.body.instanteRequiredBob ? dayjs(res.body.instanteRequiredBob) : undefined;
      res.body.zonedDateTimeBob = res.body.zonedDateTimeBob ? dayjs(res.body.zonedDateTimeBob) : undefined;
      res.body.zonedDateTimeRequiredBob = res.body.zonedDateTimeRequiredBob ? dayjs(res.body.zonedDateTimeRequiredBob) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fieldTestServiceClassAndJpaFilteringEntity: IFieldTestServiceClassAndJpaFilteringEntity) => {
        fieldTestServiceClassAndJpaFilteringEntity.localDateBob = fieldTestServiceClassAndJpaFilteringEntity.localDateBob
          ? dayjs(fieldTestServiceClassAndJpaFilteringEntity.localDateBob)
          : undefined;
        fieldTestServiceClassAndJpaFilteringEntity.localDateRequiredBob = fieldTestServiceClassAndJpaFilteringEntity.localDateRequiredBob
          ? dayjs(fieldTestServiceClassAndJpaFilteringEntity.localDateRequiredBob)
          : undefined;
        fieldTestServiceClassAndJpaFilteringEntity.instantBob = fieldTestServiceClassAndJpaFilteringEntity.instantBob
          ? dayjs(fieldTestServiceClassAndJpaFilteringEntity.instantBob)
          : undefined;
        fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob = fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob
          ? dayjs(fieldTestServiceClassAndJpaFilteringEntity.instanteRequiredBob)
          : undefined;
        fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob = fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob
          ? dayjs(fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeBob)
          : undefined;
        fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob = fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob
          ? dayjs(fieldTestServiceClassAndJpaFilteringEntity.zonedDateTimeRequiredBob)
          : undefined;
      });
    }
    return res;
  }
}
