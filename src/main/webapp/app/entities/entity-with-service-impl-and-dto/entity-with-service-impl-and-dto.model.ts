export interface IEntityWithServiceImplAndDTO {
  id?: string;
  louis?: string | null;
}

export class EntityWithServiceImplAndDTO implements IEntityWithServiceImplAndDTO {
  constructor(public id?: string, public louis?: string | null) {}
}
