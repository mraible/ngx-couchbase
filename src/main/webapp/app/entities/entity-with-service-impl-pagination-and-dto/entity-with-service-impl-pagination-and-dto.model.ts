export interface IEntityWithServiceImplPaginationAndDTO {
  id?: string;
  theo?: string | null;
}

export class EntityWithServiceImplPaginationAndDTO implements IEntityWithServiceImplPaginationAndDTO {
  constructor(public id?: string, public theo?: string | null) {}
}
