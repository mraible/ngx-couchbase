import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityWithServiceImplPaginationAndDTO } from '../entity-with-service-impl-pagination-and-dto.model';

import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { EntityWithServiceImplPaginationAndDTOService } from '../service/entity-with-service-impl-pagination-and-dto.service';
import { EntityWithServiceImplPaginationAndDTODeleteDialogComponent } from '../delete/entity-with-service-impl-pagination-and-dto-delete-dialog.component';

@Component({
  selector: 'jhi-entity-with-service-impl-pagination-and-dto',
  templateUrl: './entity-with-service-impl-pagination-and-dto.component.html',
})
export class EntityWithServiceImplPaginationAndDTOComponent implements OnInit {
  entityWithServiceImplPaginationAndDTOS?: IEntityWithServiceImplPaginationAndDTO[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected entityWithServiceImplPaginationAndDTOService: EntityWithServiceImplPaginationAndDTOService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.entityWithServiceImplPaginationAndDTOService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IEntityWithServiceImplPaginationAndDTO[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        () => {
          this.isLoading = false;
          this.onError();
        }
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
  }

  trackId(index: number, item: IEntityWithServiceImplPaginationAndDTO): string {
    return item.id!;
  }

  delete(entityWithServiceImplPaginationAndDTO: IEntityWithServiceImplPaginationAndDTO): void {
    const modalRef = this.modalService.open(EntityWithServiceImplPaginationAndDTODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entityWithServiceImplPaginationAndDTO = entityWithServiceImplPaginationAndDTO;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
  }

  protected sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    });
  }

  protected onSuccess(data: IEntityWithServiceImplPaginationAndDTO[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/entity-with-service-impl-pagination-and-dto'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.entityWithServiceImplPaginationAndDTOS = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
