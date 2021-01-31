import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import {
  IEntityWithServiceClassPaginationAndDTO,
  EntityWithServiceClassPaginationAndDTO,
} from '../entity-with-service-class-pagination-and-dto.model';
import { EntityWithServiceClassPaginationAndDTOService } from '../service/entity-with-service-class-pagination-and-dto.service';

@Component({
  selector: 'jhi-entity-with-service-class-pagination-and-dto-update',
  templateUrl: './entity-with-service-class-pagination-and-dto-update.component.html',
})
export class EntityWithServiceClassPaginationAndDTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lena: [],
  });

  constructor(
    protected entityWithServiceClassPaginationAndDTOService: EntityWithServiceClassPaginationAndDTOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityWithServiceClassPaginationAndDTO }) => {
      this.updateForm(entityWithServiceClassPaginationAndDTO);
    });
  }

  updateForm(entityWithServiceClassPaginationAndDTO: IEntityWithServiceClassPaginationAndDTO): void {
    this.editForm.patchValue({
      id: entityWithServiceClassPaginationAndDTO.id,
      lena: entityWithServiceClassPaginationAndDTO.lena,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityWithServiceClassPaginationAndDTO = this.createFromForm();
    if (entityWithServiceClassPaginationAndDTO.id !== undefined) {
      this.subscribeToSaveResponse(this.entityWithServiceClassPaginationAndDTOService.update(entityWithServiceClassPaginationAndDTO));
    } else {
      this.subscribeToSaveResponse(this.entityWithServiceClassPaginationAndDTOService.create(entityWithServiceClassPaginationAndDTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityWithServiceClassPaginationAndDTO>>): void {
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

  protected createFromForm(): IEntityWithServiceClassPaginationAndDTO {
    return {
      ...new EntityWithServiceClassPaginationAndDTO(),
      id: this.editForm.get(['id'])!.value,
      lena: this.editForm.get(['lena'])!.value,
    };
  }
}
