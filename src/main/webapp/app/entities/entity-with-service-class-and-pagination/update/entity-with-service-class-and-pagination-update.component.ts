import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import {
  IEntityWithServiceClassAndPagination,
  EntityWithServiceClassAndPagination,
} from '../entity-with-service-class-and-pagination.model';
import { EntityWithServiceClassAndPaginationService } from '../service/entity-with-service-class-and-pagination.service';

@Component({
  selector: 'jhi-entity-with-service-class-and-pagination-update',
  templateUrl: './entity-with-service-class-and-pagination-update.component.html',
})
export class EntityWithServiceClassAndPaginationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    enzo: [],
  });

  constructor(
    protected entityWithServiceClassAndPaginationService: EntityWithServiceClassAndPaginationService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityWithServiceClassAndPagination }) => {
      this.updateForm(entityWithServiceClassAndPagination);
    });
  }

  updateForm(entityWithServiceClassAndPagination: IEntityWithServiceClassAndPagination): void {
    this.editForm.patchValue({
      id: entityWithServiceClassAndPagination.id,
      enzo: entityWithServiceClassAndPagination.enzo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityWithServiceClassAndPagination = this.createFromForm();
    if (entityWithServiceClassAndPagination.id !== undefined) {
      this.subscribeToSaveResponse(this.entityWithServiceClassAndPaginationService.update(entityWithServiceClassAndPagination));
    } else {
      this.subscribeToSaveResponse(this.entityWithServiceClassAndPaginationService.create(entityWithServiceClassAndPagination));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityWithServiceClassAndPagination>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  protected createFromForm(): IEntityWithServiceClassAndPagination {
    return {
      ...new EntityWithServiceClassAndPagination(),
      id: this.editForm.get(['id'])!.value,
      enzo: this.editForm.get(['enzo'])!.value,
    };
  }
}
