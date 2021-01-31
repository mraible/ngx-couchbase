import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEntityWithDTO, EntityWithDTO } from '../entity-with-dto.model';
import { EntityWithDTOService } from '../service/entity-with-dto.service';

@Component({
  selector: 'jhi-entity-with-dto-update',
  templateUrl: './entity-with-dto-update.component.html',
})
export class EntityWithDTOUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    emma: [],
  });

  constructor(protected entityWithDTOService: EntityWithDTOService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityWithDTO }) => {
      this.updateForm(entityWithDTO);
    });
  }

  updateForm(entityWithDTO: IEntityWithDTO): void {
    this.editForm.patchValue({
      id: entityWithDTO.id,
      emma: entityWithDTO.emma,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityWithDTO = this.createFromForm();
    if (entityWithDTO.id !== undefined) {
      this.subscribeToSaveResponse(this.entityWithDTOService.update(entityWithDTO));
    } else {
      this.subscribeToSaveResponse(this.entityWithDTOService.create(entityWithDTO));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityWithDTO>>): void {
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

  protected createFromForm(): IEntityWithDTO {
    return {
      ...new EntityWithDTO(),
      id: this.editForm.get(['id'])!.value,
      emma: this.editForm.get(['emma'])!.value,
    };
  }
}
