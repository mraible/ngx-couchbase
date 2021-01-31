export interface IEntityWithServiceClassAndPagination {
  id?: string;
  enzo?: string | null;
}

export class EntityWithServiceClassAndPagination implements IEntityWithServiceClassAndPagination {
  constructor(public id?: string, public enzo?: string | null) {}
}
