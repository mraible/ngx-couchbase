import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntityWithPaginationAndDTO, EntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';
import { EntityWithPaginationAndDTOService } from '../service/entity-with-pagination-and-dto.service';

@Component({
  selector: 'jhi-entity-with-pagination-and-dto-update',
  templateUrl: './entity-with-pagination-and-dto-update.component.html',
})
export class EntityWithPaginationAndDTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lea: [],
  });

  constructor(
    protected entityWithPaginationAndDTOService: EntityWithPaginationAndDTOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityWithPaginationAndDTO }) => {
      this.updateForm(entityWithPaginationAndDTO);
    });
  }

  updateForm(entityWithPaginationAndDTO: IEntityWithPaginationAndDTO): void {
    this.editForm.patchValue({
      id: entityWithPaginationAndDTO.id,
      lea: entityWithPaginationAndDTO.lea,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityWithPaginationAndDTO = this.createFromForm();
    if (entityWithPaginationAndDTO.id !== undefined) {
      this.subscribeToSaveResponse(this.entityWithPaginationAndDTOService.update(entityWithPaginationAndDTO));
    } else {
      this.subscribeToSaveResponse(this.entityWithPaginationAndDTOService.create(entityWithPaginationAndDTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityWithPaginationAndDTO>>): void {
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

  protected createFromForm(): IEntityWithPaginationAndDTO {
    return {
      ...new EntityWithPaginationAndDTO(),
      id: this.editForm.get(['id'])!.value,
      lea: this.editForm.get(['lea'])!.value,
    };
  }
}
