package tech.jhipster.sample.repository;

import java.io.Serializable;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.core.mapping.CouchbasePersistentEntity;
import org.springframework.data.couchbase.repository.query.CouchbaseEntityInformation;
import org.springframework.data.couchbase.repository.support.SimpleCouchbaseRepository;

/**
 * A custom implementation of {@code CouchbaseRepository}.
 */
public class CustomCouchbaseRepository<T, ID extends Serializable> extends SimpleCouchbaseRepository<T, ID> {

    private final CouchbasePersistentEntity<?> persistentEntity;
    private final CouchbaseOperations couchbaseOperations;

    /**
     * Create a new Repository.
     *
     * @param metadata            the Metadata for the entity.
     * @param couchbaseOperations the reference to the template used.
     */
    public CustomCouchbaseRepository(CouchbaseEntityInformation<T, String> metadata, CouchbaseOperations couchbaseOperations) {
        super(metadata, couchbaseOperations);
        this.couchbaseOperations = couchbaseOperations;
        persistentEntity = couchbaseOperations.getConverter().getMappingContext().getPersistentEntity(getEntityInformation().getJavaType());
    }

    @Override
    public <S extends T> S save(S entity) {
        return super.save(populateIdIfNecessary(entity));
    }

    /**
     * Add generated ID to entity if not already set.
     *
     * @param entity the entity to update.
     * @return entity with ID set.
     */
    private <S extends T> S populateIdIfNecessary(S entity) {
        if (getEntityInformation().getId(entity) != null) {
            return entity;
        }
        //setId(entity, getCouchbaseOperations().getGeneratedId(entity));
        return entity;
    }

    private <S extends T> void setId(S entity, String generatedId) {
        persistentEntity.getPropertyAccessor(entity).setProperty(persistentEntity.getIdProperty(), generatedId);
    }
}
