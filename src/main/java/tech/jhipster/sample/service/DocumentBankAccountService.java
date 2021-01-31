package tech.jhipster.sample.service;

import java.util.List;
import java.util.Optional;
import tech.jhipster.sample.service.dto.DocumentBankAccountDTO;

/**
 * Service Interface for managing {@link tech.jhipster.sample.domain.DocumentBankAccount}.
 */
public interface DocumentBankAccountService {
    /**
     * Save a documentBankAccount.
     *
     * @param documentBankAccountDTO the entity to save.
     * @return the persisted entity.
     */
    DocumentBankAccountDTO save(DocumentBankAccountDTO documentBankAccountDTO);

    /**
     * Partially updates a documentBankAccount.
     *
     * @param documentBankAccountDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DocumentBankAccountDTO> partialUpdate(DocumentBankAccountDTO documentBankAccountDTO);

    /**
     * Get all the documentBankAccounts.
     *
     * @return the list of entities.
     */
    List<DocumentBankAccountDTO> findAll();

    /**
     * Get the "id" documentBankAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DocumentBankAccountDTO> findOne(String id);

    /**
     * Delete the "id" documentBankAccount.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
