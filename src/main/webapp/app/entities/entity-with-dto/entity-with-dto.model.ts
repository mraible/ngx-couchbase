export interface IEntityWithDTO {
  id?: string;
  emma?: string | null;
}

export class EntityWithDTO implements IEntityWithDTO {
  constructor(public id?: string, public emma?: string | null) {}
}
