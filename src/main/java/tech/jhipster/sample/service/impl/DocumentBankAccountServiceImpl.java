package tech.jhipster.sample.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.jhipster.sample.domain.DocumentBankAccount;
import tech.jhipster.sample.repository.DocumentBankAccountRepository;
import tech.jhipster.sample.service.DocumentBankAccountService;
import tech.jhipster.sample.service.dto.DocumentBankAccountDTO;
import tech.jhipster.sample.service.mapper.DocumentBankAccountMapper;

/**
 * Service Implementation for managing {@link DocumentBankAccount}.
 */
@Service
public class DocumentBankAccountServiceImpl implements DocumentBankAccountService {

    private final Logger log = LoggerFactory.getLogger(DocumentBankAccountServiceImpl.class);

    private final DocumentBankAccountRepository documentBankAccountRepository;

    private final DocumentBankAccountMapper documentBankAccountMapper;

    public DocumentBankAccountServiceImpl(
        DocumentBankAccountRepository documentBankAccountRepository,
        DocumentBankAccountMapper documentBankAccountMapper
    ) {
        this.documentBankAccountRepository = documentBankAccountRepository;
        this.documentBankAccountMapper = documentBankAccountMapper;
    }

    @Override
    public DocumentBankAccountDTO save(DocumentBankAccountDTO documentBankAccountDTO) {
        log.debug("Request to save DocumentBankAccount : {}", documentBankAccountDTO);
        DocumentBankAccount documentBankAccount = documentBankAccountMapper.toEntity(documentBankAccountDTO);
        documentBankAccount = documentBankAccountRepository.save(documentBankAccount);
        return documentBankAccountMapper.toDto(documentBankAccount);
    }

    @Override
    public Optional<DocumentBankAccountDTO> partialUpdate(DocumentBankAccountDTO documentBankAccountDTO) {
        log.debug("Request to partially update DocumentBankAccount : {}", documentBankAccountDTO);

        return documentBankAccountRepository
            .findById(documentBankAccountDTO.getId())
            .map(
                existingDocumentBankAccount -> {
                    if (documentBankAccountDTO.getName() != null) {
                        existingDocumentBankAccount.setName(documentBankAccountDTO.getName());
                    }

                    if (documentBankAccountDTO.getBankNumber() != null) {
                        existingDocumentBankAccount.setBankNumber(documentBankAccountDTO.getBankNumber());
                    }

                    if (documentBankAccountDTO.getAgencyNumber() != null) {
                        existingDocumentBankAccount.setAgencyNumber(documentBankAccountDTO.getAgencyNumber());
                    }

                    if (documentBankAccountDTO.getLastOperationDuration() != null) {
                        existingDocumentBankAccount.setLastOperationDuration(documentBankAccountDTO.getLastOperationDuration());
                    }

                    if (documentBankAccountDTO.getMeanOperationDuration() != null) {
                        existingDocumentBankAccount.setMeanOperationDuration(documentBankAccountDTO.getMeanOperationDuration());
                    }

                    if (documentBankAccountDTO.getBalance() != null) {
                        existingDocumentBankAccount.setBalance(documentBankAccountDTO.getBalance());
                    }

                    if (documentBankAccountDTO.getOpeningDay() != null) {
                        existingDocumentBankAccount.setOpeningDay(documentBankAccountDTO.getOpeningDay());
                    }

                    if (documentBankAccountDTO.getLastOperationDate() != null) {
                        existingDocumentBankAccount.setLastOperationDate(documentBankAccountDTO.getLastOperationDate());
                    }

                    if (documentBankAccountDTO.getActive() != null) {
                        existingDocumentBankAccount.setActive(documentBankAccountDTO.getActive());
                    }

                    if (documentBankAccountDTO.getAccountType() != null) {
                        existingDocumentBankAccount.setAccountType(documentBankAccountDTO.getAccountType());
                    }

                    if (documentBankAccountDTO.getAttachment() != null) {
                        existingDocumentBankAccount.setAttachment(documentBankAccountDTO.getAttachment());
                    }
                    if (documentBankAccountDTO.getAttachmentContentType() != null) {
                        existingDocumentBankAccount.setAttachmentContentType(documentBankAccountDTO.getAttachmentContentType());
                    }

                    if (documentBankAccountDTO.getDescription() != null) {
                        existingDocumentBankAccount.setDescription(documentBankAccountDTO.getDescription());
                    }

                    return existingDocumentBankAccount;
                }
            )
            .map(documentBankAccountRepository::save)
            .map(documentBankAccountMapper::toDto);
    }

    @Override
    public List<DocumentBankAccountDTO> findAll() {
        log.debug("Request to get all DocumentBankAccounts");
        return documentBankAccountRepository
            .findAll()
            .stream()
            .map(documentBankAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<DocumentBankAccountDTO> findOne(String id) {
        log.debug("Request to get DocumentBankAccount : {}", id);
        return documentBankAccountRepository.findById(id).map(documentBankAccountMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete DocumentBankAccount : {}", id);
        documentBankAccountRepository.deleteById(id);
    }
}
