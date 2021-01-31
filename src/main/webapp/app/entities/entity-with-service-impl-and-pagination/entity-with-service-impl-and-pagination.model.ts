export interface IEntityWithServiceImplAndPagination {
  id?: string;
  hugo?: string | null;
}

export class EntityWithServiceImplAndPagination implements IEntityWithServiceImplAndPagination {
  constructor(public id?: string, public hugo?: string | null) {}
}
