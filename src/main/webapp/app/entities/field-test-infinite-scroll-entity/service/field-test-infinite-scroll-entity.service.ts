import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/core/request/request-util';
import { IFieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';

export type EntityResponseType = HttpResponse<IFieldTestInfiniteScrollEntity>;
export type EntityArrayResponseType = HttpResponse<IFieldTestInfiniteScrollEntity[]>;

@Injectable({ providedIn: 'root' })
export class FieldTestInfiniteScrollEntityService {
  public resourceUrl = SERVER_API_URL + 'api/field-test-infinite-scroll-entities';

  constructor(protected http: HttpClient) {}

  create(fieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestInfiniteScrollEntity);
    return this.http
      .post<IFieldTestInfiniteScrollEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fieldTestInfiniteScrollEntity);
    return this.http
      .put<IFieldTestInfiniteScrollEntity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IFieldTestInfiniteScrollEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFieldTestInfiniteScrollEntity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity): IFieldTestInfiniteScrollEntity {
    const copy: IFieldTestInfiniteScrollEntity = Object.assign({}, fieldTestInfiniteScrollEntity, {
      localDateHugo: fieldTestInfiniteScrollEntity.localDateHugo?.isValid()
        ? fieldTestInfiniteScrollEntity.localDateHugo.format(DATE_FORMAT)
        : undefined,
      localDateRequiredHugo: fieldTestInfiniteScrollEntity.localDateRequiredHugo?.isValid()
        ? fieldTestInfiniteScrollEntity.localDateRequiredHugo.format(DATE_FORMAT)
        : undefined,
      instantHugo: fieldTestInfiniteScrollEntity.instantHugo?.isValid() ? fieldTestInfiniteScrollEntity.instantHugo.toJSON() : undefined,
      instanteRequiredHugo: fieldTestInfiniteScrollEntity.instanteRequiredHugo?.isValid()
        ? fieldTestInfiniteScrollEntity.instanteRequiredHugo.toJSON()
        : undefined,
      zonedDateTimeHugo: fieldTestInfiniteScrollEntity.zonedDateTimeHugo?.isValid()
        ? fieldTestInfiniteScrollEntity.zonedDateTimeHugo.toJSON()
        : undefined,
      zonedDateTimeRequiredHugo: fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo?.isValid()
        ? fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo.toJSON()
        : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.localDateHugo = res.body.localDateHugo ? dayjs(res.body.localDateHugo) : undefined;
      res.body.localDateRequiredHugo = res.body.localDateRequiredHugo ? dayjs(res.body.localDateRequiredHugo) : undefined;
      res.body.instantHugo = res.body.instantHugo ? dayjs(res.body.instantHugo) : undefined;
      res.body.instanteRequiredHugo = res.body.instanteRequiredHugo ? dayjs(res.body.instanteRequiredHugo) : undefined;
      res.body.zonedDateTimeHugo = res.body.zonedDateTimeHugo ? dayjs(res.body.zonedDateTimeHugo) : undefined;
      res.body.zonedDateTimeRequiredHugo = res.body.zonedDateTimeRequiredHugo ? dayjs(res.body.zonedDateTimeRequiredHugo) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity) => {
        fieldTestInfiniteScrollEntity.localDateHugo = fieldTestInfiniteScrollEntity.localDateHugo
          ? dayjs(fieldTestInfiniteScrollEntity.localDateHugo)
          : undefined;
        fieldTestInfiniteScrollEntity.localDateRequiredHugo = fieldTestInfiniteScrollEntity.localDateRequiredHugo
          ? dayjs(fieldTestInfiniteScrollEntity.localDateRequiredHugo)
          : undefined;
        fieldTestInfiniteScrollEntity.instantHugo = fieldTestInfiniteScrollEntity.instantHugo
          ? dayjs(fieldTestInfiniteScrollEntity.instantHugo)
          : undefined;
        fieldTestInfiniteScrollEntity.instanteRequiredHugo = fieldTestInfiniteScrollEntity.instanteRequiredHugo
          ? dayjs(fieldTestInfiniteScrollEntity.instanteRequiredHugo)
          : undefined;
        fieldTestInfiniteScrollEntity.zonedDateTimeHugo = fieldTestInfiniteScrollEntity.zonedDateTimeHugo
          ? dayjs(fieldTestInfiniteScrollEntity.zonedDateTimeHugo)
          : undefined;
        fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo = fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo
          ? dayjs(fieldTestInfiniteScrollEntity.zonedDateTimeRequiredHugo)
          : undefined;
      });
    }
    return res;
  }
}
