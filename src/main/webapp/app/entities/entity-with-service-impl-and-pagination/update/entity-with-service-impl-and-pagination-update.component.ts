import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntityWithServiceImplAndPagination, EntityWithServiceImplAndPagination } from '../entity-with-service-impl-and-pagination.model';
import { EntityWithServiceImplAndPaginationService } from '../service/entity-with-service-impl-and-pagination.service';

@Component({
  selector: 'jhi-entity-with-service-impl-and-pagination-update',
  templateUrl: './entity-with-service-impl-and-pagination-update.component.html',
})
export class EntityWithServiceImplAndPaginationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    hugo: [],
  });

  constructor(
    protected entityWithServiceImplAndPaginationService: EntityWithServiceImplAndPaginationService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityWithServiceImplAndPagination }) => {
      this.updateForm(entityWithServiceImplAndPagination);
    });
  }

  updateForm(entityWithServiceImplAndPagination: IEntityWithServiceImplAndPagination): void {
    this.editForm.patchValue({
      id: entityWithServiceImplAndPagination.id,
      hugo: entityWithServiceImplAndPagination.hugo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityWithServiceImplAndPagination = this.createFromForm();
    if (entityWithServiceImplAndPagination.id !== undefined) {
      this.subscribeToSaveResponse(this.entityWithServiceImplAndPaginationService.update(entityWithServiceImplAndPagination));
    } else {
      this.subscribeToSaveResponse(this.entityWithServiceImplAndPaginationService.create(entityWithServiceImplAndPagination));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityWithServiceImplAndPagination>>): void {
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

  protected createFromForm(): IEntityWithServiceImplAndPagination {
    return {
      ...new EntityWithServiceImplAndPagination(),
      id: this.editForm.get(['id'])!.value,
      hugo: this.editForm.get(['hugo'])!.value,
    };
  }
}
