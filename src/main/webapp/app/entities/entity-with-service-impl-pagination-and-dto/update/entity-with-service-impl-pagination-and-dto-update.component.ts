import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import {
  IEntityWithServiceImplPaginationAndDTO,
  EntityWithServiceImplPaginationAndDTO,
} from '../entity-with-service-impl-pagination-and-dto.model';
import { EntityWithServiceImplPaginationAndDTOService } from '../service/entity-with-service-impl-pagination-and-dto.service';

@Component({
  selector: 'jhi-entity-with-service-impl-pagination-and-dto-update',
  templateUrl: './entity-with-service-impl-pagination-and-dto-update.component.html',
})
export class EntityWithServiceImplPaginationAndDTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    theo: [],
  });

  constructor(
    protected entityWithServiceImplPaginationAndDTOService: EntityWithServiceImplPaginationAndDTOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityWithServiceImplPaginationAndDTO }) => {
      this.updateForm(entityWithServiceImplPaginationAndDTO);
    });
  }

  updateForm(entityWithServiceImplPaginationAndDTO: IEntityWithServiceImplPaginationAndDTO): void {
    this.editForm.patchValue({
      id: entityWithServiceImplPaginationAndDTO.id,
      theo: entityWithServiceImplPaginationAndDTO.theo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityWithServiceImplPaginationAndDTO = this.createFromForm();
    if (entityWithServiceImplPaginationAndDTO.id !== undefined) {
      this.subscribeToSaveResponse(this.entityWithServiceImplPaginationAndDTOService.update(entityWithServiceImplPaginationAndDTO));
    } else {
      this.subscribeToSaveResponse(this.entityWithServiceImplPaginationAndDTOService.create(entityWithServiceImplPaginationAndDTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityWithServiceImplPaginationAndDTO>>): void {
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

  protected createFromForm(): IEntityWithServiceImplPaginationAndDTO {
    return {
      ...new EntityWithServiceImplPaginationAndDTO(),
      id: this.editForm.get(['id'])!.value,
      theo: this.editForm.get(['theo'])!.value,
    };
  }
}
