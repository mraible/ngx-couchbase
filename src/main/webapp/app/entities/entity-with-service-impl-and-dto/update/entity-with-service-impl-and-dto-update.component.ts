import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntityWithServiceImplAndDTO, EntityWithServiceImplAndDTO } from '../entity-with-service-impl-and-dto.model';
import { EntityWithServiceImplAndDTOService } from '../service/entity-with-service-impl-and-dto.service';

@Component({
  selector: 'jhi-entity-with-service-impl-and-dto-update',
  templateUrl: './entity-with-service-impl-and-dto-update.component.html',
})
export class EntityWithServiceImplAndDTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    louis: [],
  });

  constructor(
    protected entityWithServiceImplAndDTOService: EntityWithServiceImplAndDTOService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityWithServiceImplAndDTO }) => {
      this.updateForm(entityWithServiceImplAndDTO);
    });
  }

  updateForm(entityWithServiceImplAndDTO: IEntityWithServiceImplAndDTO): void {
    this.editForm.patchValue({
      id: entityWithServiceImplAndDTO.id,
      louis: entityWithServiceImplAndDTO.louis,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityWithServiceImplAndDTO = this.createFromForm();
    if (entityWithServiceImplAndDTO.id !== undefined) {
      this.subscribeToSaveResponse(this.entityWithServiceImplAndDTOService.update(entityWithServiceImplAndDTO));
    } else {
      this.subscribeToSaveResponse(this.entityWithServiceImplAndDTOService.create(entityWithServiceImplAndDTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityWithServiceImplAndDTO>>): void {
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

  protected createFromForm(): IEntityWithServiceImplAndDTO {
    return {
      ...new EntityWithServiceImplAndDTO(),
      id: this.editForm.get(['id'])!.value,
      louis: this.editForm.get(['louis'])!.value,
    };
  }
}
