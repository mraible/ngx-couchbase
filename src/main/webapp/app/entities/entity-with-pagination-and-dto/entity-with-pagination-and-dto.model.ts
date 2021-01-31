export interface IEntityWithPaginationAndDTO {
  id?: string;
  lea?: string | null;
}

export class EntityWithPaginationAndDTO implements IEntityWithPaginationAndDTO {
  constructor(public id?: string, public lea?: string | null) {}
}
