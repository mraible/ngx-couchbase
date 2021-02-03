package tech.jhipster.sample.config.sdc;

import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.data.couchbase.repository.support.CouchbaseRepositoryFactory;
import org.springframework.data.couchbase.repository.support.CouchbaseRepositoryFactoryBean;

/**
 * Created by mmonti on 2/1/21.
 */
public class CustomCouchbaseRepositoryFactoryBean extends CouchbaseRepositoryFactoryBean {

    /**
     * Creates a new {@link CouchbaseRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public CustomCouchbaseRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected CouchbaseRepositoryFactory getFactoryInstance(RepositoryOperationsMapping operationsMapping) {
        return new CustomCouchbaseRepositoryFactory(operationsMapping);
    }
}
