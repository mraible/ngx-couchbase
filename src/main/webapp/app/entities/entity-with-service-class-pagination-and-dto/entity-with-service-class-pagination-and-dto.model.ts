export interface IEntityWithServiceClassPaginationAndDTO {
  id?: string;
  lena?: string | null;
}

export class EntityWithServiceClassPaginationAndDTO implements IEntityWithServiceClassPaginationAndDTO {
  constructor(public id?: string, public lena?: string | null) {}
}
