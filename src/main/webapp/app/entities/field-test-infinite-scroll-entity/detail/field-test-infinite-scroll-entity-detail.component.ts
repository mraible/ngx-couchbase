import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-field-test-infinite-scroll-entity-detail',
  templateUrl: './field-test-infinite-scroll-entity-detail.component.html',
})
export class FieldTestInfiniteScrollEntityDetailComponent implements OnInit {
  fieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldTestInfiniteScrollEntity }) => {
      this.fieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntity;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  previousState(): void {
    window.history.back();
  }
}
