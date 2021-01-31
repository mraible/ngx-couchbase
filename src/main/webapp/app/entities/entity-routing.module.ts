import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'document-bank-account',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.documentBankAccount.home.title' },
        loadChildren: () => import('./document-bank-account/document-bank-account.module').then(m => m.DocumentBankAccountModule),
      },
      {
        path: 'field-test-entity',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.fieldTestEntity.home.title' },
        loadChildren: () => import('./field-test-entity/field-test-entity.module').then(m => m.FieldTestEntityModule),
      },
      {
        path: 'field-test-infinite-scroll-entity',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.fieldTestInfiniteScrollEntity.home.title' },
        loadChildren: () =>
          import('./field-test-infinite-scroll-entity/field-test-infinite-scroll-entity.module').then(
            m => m.FieldTestInfiniteScrollEntityModule
          ),
      },
      {
        path: 'field-test-mapstruct-and-service-class-entity',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.fieldTestMapstructAndServiceClassEntity.home.title' },
        loadChildren: () =>
          import('./field-test-mapstruct-and-service-class-entity/field-test-mapstruct-and-service-class-entity.module').then(
            m => m.FieldTestMapstructAndServiceClassEntityModule
          ),
      },
      {
        path: 'field-test-pagination-entity',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.fieldTestPaginationEntity.home.title' },
        loadChildren: () =>
          import('./field-test-pagination-entity/field-test-pagination-entity.module').then(m => m.FieldTestPaginationEntityModule),
      },
      {
        path: 'field-test-service-class-and-jpa-filtering-entity',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.fieldTestServiceClassAndJpaFilteringEntity.home.title' },
        loadChildren: () =>
          import('./field-test-service-class-and-jpa-filtering-entity/field-test-service-class-and-jpa-filtering-entity.module').then(
            m => m.FieldTestServiceClassAndJpaFilteringEntityModule
          ),
      },
      {
        path: 'field-test-service-impl-entity',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.fieldTestServiceImplEntity.home.title' },
        loadChildren: () =>
          import('./field-test-service-impl-entity/field-test-service-impl-entity.module').then(m => m.FieldTestServiceImplEntityModule),
      },
      {
        path: 'entity-with-dto',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.entityWithDTO.home.title' },
        loadChildren: () => import('./entity-with-dto/entity-with-dto.module').then(m => m.EntityWithDTOModule),
      },
      {
        path: 'entity-with-service-class-and-pagination',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.entityWithServiceClassAndPagination.home.title' },
        loadChildren: () =>
          import('./entity-with-service-class-and-pagination/entity-with-service-class-and-pagination.module').then(
            m => m.EntityWithServiceClassAndPaginationModule
          ),
      },
      {
        path: 'entity-with-service-impl-and-pagination',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.entityWithServiceImplAndPagination.home.title' },
        loadChildren: () =>
          import('./entity-with-service-impl-and-pagination/entity-with-service-impl-and-pagination.module').then(
            m => m.EntityWithServiceImplAndPaginationModule
          ),
      },
      {
        path: 'entity-with-service-impl-and-dto',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.entityWithServiceImplAndDTO.home.title' },
        loadChildren: () =>
          import('./entity-with-service-impl-and-dto/entity-with-service-impl-and-dto.module').then(
            m => m.EntityWithServiceImplAndDTOModule
          ),
      },
      {
        path: 'entity-with-pagination-and-dto',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.entityWithPaginationAndDTO.home.title' },
        loadChildren: () =>
          import('./entity-with-pagination-and-dto/entity-with-pagination-and-dto.module').then(m => m.EntityWithPaginationAndDTOModule),
      },
      {
        path: 'entity-with-service-class-pagination-and-dto',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.entityWithServiceClassPaginationAndDTO.home.title' },
        loadChildren: () =>
          import('./entity-with-service-class-pagination-and-dto/entity-with-service-class-pagination-and-dto.module').then(
            m => m.EntityWithServiceClassPaginationAndDTOModule
          ),
      },
      {
        path: 'entity-with-service-impl-pagination-and-dto',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.entityWithServiceImplPaginationAndDTO.home.title' },
        loadChildren: () =>
          import('./entity-with-service-impl-pagination-and-dto/entity-with-service-impl-pagination-and-dto.module').then(
            m => m.EntityWithServiceImplPaginationAndDTOModule
          ),
      },
      {
        path: 'division',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.testRootDivision.home.title' },
        loadChildren: () => import('./test-root/division/division.module').then(m => m.DivisionModule),
      },
      {
        path: 'place',
        data: { pageTitle: 'sampleCouchbaseNoCacheApp.testRootPlace.home.title' },
        loadChildren: () => import('./test-root/place/place.module').then(m => m.PlaceModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
