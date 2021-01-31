package tech.jhipster.sample.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.sample.web.rest.TestUtil.sameNumber;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import tech.jhipster.sample.IntegrationTest;
import tech.jhipster.sample.domain.DocumentBankAccount;
import tech.jhipster.sample.domain.enumeration.BankAccountType;
import tech.jhipster.sample.repository.DocumentBankAccountRepository;
import tech.jhipster.sample.service.dto.DocumentBankAccountDTO;
import tech.jhipster.sample.service.mapper.DocumentBankAccountMapper;

/**
 * Integration tests for the {@link DocumentBankAccountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentBankAccountResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_BANK_NUMBER = 1;
    private static final Integer UPDATED_BANK_NUMBER = 2;

    private static final Long DEFAULT_AGENCY_NUMBER = 1L;
    private static final Long UPDATED_AGENCY_NUMBER = 2L;

    private static final Float DEFAULT_LAST_OPERATION_DURATION = 1F;
    private static final Float UPDATED_LAST_OPERATION_DURATION = 2F;

    private static final Double DEFAULT_MEAN_OPERATION_DURATION = 1D;
    private static final Double UPDATED_MEAN_OPERATION_DURATION = 2D;

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final LocalDate DEFAULT_OPENING_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OPENING_DAY = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_LAST_OPERATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_OPERATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final BankAccountType DEFAULT_ACCOUNT_TYPE = BankAccountType.CHECKING;
    private static final BankAccountType UPDATED_ACCOUNT_TYPE = BankAccountType.SAVINGS;

    private static final byte[] DEFAULT_ATTACHMENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ATTACHMENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ATTACHMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ATTACHMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DocumentBankAccountRepository documentBankAccountRepository;

    @Autowired
    private DocumentBankAccountMapper documentBankAccountMapper;

    @Autowired
    private MockMvc restDocumentBankAccountMockMvc;

    private DocumentBankAccount documentBankAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentBankAccount createEntity() {
        DocumentBankAccount documentBankAccount = new DocumentBankAccount()
            .name(DEFAULT_NAME)
            .bankNumber(DEFAULT_BANK_NUMBER)
            .agencyNumber(DEFAULT_AGENCY_NUMBER)
            .lastOperationDuration(DEFAULT_LAST_OPERATION_DURATION)
            .meanOperationDuration(DEFAULT_MEAN_OPERATION_DURATION)
            .balance(DEFAULT_BALANCE)
            .openingDay(DEFAULT_OPENING_DAY)
            .lastOperationDate(DEFAULT_LAST_OPERATION_DATE)
            .active(DEFAULT_ACTIVE)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .attachment(DEFAULT_ATTACHMENT)
            .attachmentContentType(DEFAULT_ATTACHMENT_CONTENT_TYPE)
            .description(DEFAULT_DESCRIPTION);
        return documentBankAccount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentBankAccount createUpdatedEntity() {
        DocumentBankAccount documentBankAccount = new DocumentBankAccount()
            .name(UPDATED_NAME)
            .bankNumber(UPDATED_BANK_NUMBER)
            .agencyNumber(UPDATED_AGENCY_NUMBER)
            .lastOperationDuration(UPDATED_LAST_OPERATION_DURATION)
            .meanOperationDuration(UPDATED_MEAN_OPERATION_DURATION)
            .balance(UPDATED_BALANCE)
            .openingDay(UPDATED_OPENING_DAY)
            .lastOperationDate(UPDATED_LAST_OPERATION_DATE)
            .active(UPDATED_ACTIVE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .attachment(UPDATED_ATTACHMENT)
            .attachmentContentType(UPDATED_ATTACHMENT_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION);
        return documentBankAccount;
    }

    @BeforeEach
    public void initTest() {
        documentBankAccountRepository.deleteAll();
        documentBankAccount = createEntity();
    }

    @Test
    void createDocumentBankAccount() throws Exception {
        int databaseSizeBeforeCreate = documentBankAccountRepository.findAll().size();
        // Create the DocumentBankAccount
        DocumentBankAccountDTO documentBankAccountDTO = documentBankAccountMapper.toDto(documentBankAccount);
        restDocumentBankAccountMockMvc
            .perform(
                post("/api/document-bank-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentBankAccountDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DocumentBankAccount in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentBankAccount testDocumentBankAccount = documentBankAccountList.get(documentBankAccountList.size() - 1);
        assertThat(testDocumentBankAccount.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDocumentBankAccount.getBankNumber()).isEqualTo(DEFAULT_BANK_NUMBER);
        assertThat(testDocumentBankAccount.getAgencyNumber()).isEqualTo(DEFAULT_AGENCY_NUMBER);
        assertThat(testDocumentBankAccount.getLastOperationDuration()).isEqualTo(DEFAULT_LAST_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getMeanOperationDuration()).isEqualTo(DEFAULT_MEAN_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getBalance()).isEqualByComparingTo(DEFAULT_BALANCE);
        assertThat(testDocumentBankAccount.getOpeningDay()).isEqualTo(DEFAULT_OPENING_DAY);
        assertThat(testDocumentBankAccount.getLastOperationDate()).isEqualTo(DEFAULT_LAST_OPERATION_DATE);
        assertThat(testDocumentBankAccount.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testDocumentBankAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testDocumentBankAccount.getAttachment()).isEqualTo(DEFAULT_ATTACHMENT);
        assertThat(testDocumentBankAccount.getAttachmentContentType()).isEqualTo(DEFAULT_ATTACHMENT_CONTENT_TYPE);
        assertThat(testDocumentBankAccount.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    void createDocumentBankAccountWithExistingId() throws Exception {
        // Create the DocumentBankAccount with an existing ID
        documentBankAccount.setId("existing_id");
        DocumentBankAccountDTO documentBankAccountDTO = documentBankAccountMapper.toDto(documentBankAccount);

        int databaseSizeBeforeCreate = documentBankAccountRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentBankAccountMockMvc
            .perform(
                post("/api/document-bank-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentBankAccountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentBankAccount in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentBankAccountRepository.findAll().size();
        // set the field null
        documentBankAccount.setName(null);

        // Create the DocumentBankAccount, which fails.
        DocumentBankAccountDTO documentBankAccountDTO = documentBankAccountMapper.toDto(documentBankAccount);

        restDocumentBankAccountMockMvc
            .perform(
                post("/api/document-bank-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentBankAccountDTO))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBalanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentBankAccountRepository.findAll().size();
        // set the field null
        documentBankAccount.setBalance(null);

        // Create the DocumentBankAccount, which fails.
        DocumentBankAccountDTO documentBankAccountDTO = documentBankAccountMapper.toDto(documentBankAccount);

        restDocumentBankAccountMockMvc
            .perform(
                post("/api/document-bank-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentBankAccountDTO))
            )
            .andExpect(status().isBadRequest());

        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDocumentBankAccounts() throws Exception {
        // Initialize the database
        documentBankAccountRepository.save(documentBankAccount);

        // Get all the documentBankAccountList
        restDocumentBankAccountMockMvc
            .perform(get("/api/document-bank-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentBankAccount.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].bankNumber").value(hasItem(DEFAULT_BANK_NUMBER)))
            .andExpect(jsonPath("$.[*].agencyNumber").value(hasItem(DEFAULT_AGENCY_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].lastOperationDuration").value(hasItem(DEFAULT_LAST_OPERATION_DURATION.doubleValue())))
            .andExpect(jsonPath("$.[*].meanOperationDuration").value(hasItem(DEFAULT_MEAN_OPERATION_DURATION.doubleValue())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(sameNumber(DEFAULT_BALANCE))))
            .andExpect(jsonPath("$.[*].openingDay").value(hasItem(DEFAULT_OPENING_DAY.toString())))
            .andExpect(jsonPath("$.[*].lastOperationDate").value(hasItem(DEFAULT_LAST_OPERATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].attachmentContentType").value(hasItem(DEFAULT_ATTACHMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(Base64Utils.encodeToString(DEFAULT_ATTACHMENT))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    void getDocumentBankAccount() throws Exception {
        // Initialize the database
        documentBankAccountRepository.save(documentBankAccount);

        // Get the documentBankAccount
        restDocumentBankAccountMockMvc
            .perform(get("/api/document-bank-accounts/{id}", documentBankAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documentBankAccount.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.bankNumber").value(DEFAULT_BANK_NUMBER))
            .andExpect(jsonPath("$.agencyNumber").value(DEFAULT_AGENCY_NUMBER.intValue()))
            .andExpect(jsonPath("$.lastOperationDuration").value(DEFAULT_LAST_OPERATION_DURATION.doubleValue()))
            .andExpect(jsonPath("$.meanOperationDuration").value(DEFAULT_MEAN_OPERATION_DURATION.doubleValue()))
            .andExpect(jsonPath("$.balance").value(sameNumber(DEFAULT_BALANCE)))
            .andExpect(jsonPath("$.openingDay").value(DEFAULT_OPENING_DAY.toString()))
            .andExpect(jsonPath("$.lastOperationDate").value(DEFAULT_LAST_OPERATION_DATE.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE.toString()))
            .andExpect(jsonPath("$.attachmentContentType").value(DEFAULT_ATTACHMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.attachment").value(Base64Utils.encodeToString(DEFAULT_ATTACHMENT)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    void getNonExistingDocumentBankAccount() throws Exception {
        // Get the documentBankAccount
        restDocumentBankAccountMockMvc.perform(get("/api/document-bank-accounts/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void updateDocumentBankAccount() throws Exception {
        // Initialize the database
        documentBankAccountRepository.save(documentBankAccount);

        int databaseSizeBeforeUpdate = documentBankAccountRepository.findAll().size();

        // Update the documentBankAccount
        DocumentBankAccount updatedDocumentBankAccount = documentBankAccountRepository.findById(documentBankAccount.getId()).get();
        updatedDocumentBankAccount
            .name(UPDATED_NAME)
            .bankNumber(UPDATED_BANK_NUMBER)
            .agencyNumber(UPDATED_AGENCY_NUMBER)
            .lastOperationDuration(UPDATED_LAST_OPERATION_DURATION)
            .meanOperationDuration(UPDATED_MEAN_OPERATION_DURATION)
            .balance(UPDATED_BALANCE)
            .openingDay(UPDATED_OPENING_DAY)
            .lastOperationDate(UPDATED_LAST_OPERATION_DATE)
            .active(UPDATED_ACTIVE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .attachment(UPDATED_ATTACHMENT)
            .attachmentContentType(UPDATED_ATTACHMENT_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION);
        DocumentBankAccountDTO documentBankAccountDTO = documentBankAccountMapper.toDto(updatedDocumentBankAccount);

        restDocumentBankAccountMockMvc
            .perform(
                put("/api/document-bank-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentBankAccountDTO))
            )
            .andExpect(status().isOk());

        // Validate the DocumentBankAccount in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeUpdate);
        DocumentBankAccount testDocumentBankAccount = documentBankAccountList.get(documentBankAccountList.size() - 1);
        assertThat(testDocumentBankAccount.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDocumentBankAccount.getBankNumber()).isEqualTo(UPDATED_BANK_NUMBER);
        assertThat(testDocumentBankAccount.getAgencyNumber()).isEqualTo(UPDATED_AGENCY_NUMBER);
        assertThat(testDocumentBankAccount.getLastOperationDuration()).isEqualTo(UPDATED_LAST_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getMeanOperationDuration()).isEqualTo(UPDATED_MEAN_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testDocumentBankAccount.getOpeningDay()).isEqualTo(UPDATED_OPENING_DAY);
        assertThat(testDocumentBankAccount.getLastOperationDate()).isEqualTo(UPDATED_LAST_OPERATION_DATE);
        assertThat(testDocumentBankAccount.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testDocumentBankAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testDocumentBankAccount.getAttachment()).isEqualTo(UPDATED_ATTACHMENT);
        assertThat(testDocumentBankAccount.getAttachmentContentType()).isEqualTo(UPDATED_ATTACHMENT_CONTENT_TYPE);
        assertThat(testDocumentBankAccount.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    void updateNonExistingDocumentBankAccount() throws Exception {
        int databaseSizeBeforeUpdate = documentBankAccountRepository.findAll().size();

        // Create the DocumentBankAccount
        DocumentBankAccountDTO documentBankAccountDTO = documentBankAccountMapper.toDto(documentBankAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentBankAccountMockMvc
            .perform(
                put("/api/document-bank-accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentBankAccountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentBankAccount in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDocumentBankAccountWithPatch() throws Exception {
        // Initialize the database
        documentBankAccountRepository.save(documentBankAccount);

        int databaseSizeBeforeUpdate = documentBankAccountRepository.findAll().size();

        // Update the documentBankAccount using partial update
        DocumentBankAccount partialUpdatedDocumentBankAccount = new DocumentBankAccount();
        partialUpdatedDocumentBankAccount.setId(documentBankAccount.getId());

        partialUpdatedDocumentBankAccount
            .name(UPDATED_NAME)
            .bankNumber(UPDATED_BANK_NUMBER)
            .agencyNumber(UPDATED_AGENCY_NUMBER)
            .lastOperationDuration(UPDATED_LAST_OPERATION_DURATION)
            .meanOperationDuration(UPDATED_MEAN_OPERATION_DURATION)
            .balance(UPDATED_BALANCE)
            .active(UPDATED_ACTIVE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .description(UPDATED_DESCRIPTION);

        restDocumentBankAccountMockMvc
            .perform(
                patch("/api/document-bank-accounts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentBankAccount))
            )
            .andExpect(status().isOk());

        // Validate the DocumentBankAccount in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeUpdate);
        DocumentBankAccount testDocumentBankAccount = documentBankAccountList.get(documentBankAccountList.size() - 1);
        assertThat(testDocumentBankAccount.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDocumentBankAccount.getBankNumber()).isEqualTo(UPDATED_BANK_NUMBER);
        assertThat(testDocumentBankAccount.getAgencyNumber()).isEqualTo(UPDATED_AGENCY_NUMBER);
        assertThat(testDocumentBankAccount.getLastOperationDuration()).isEqualTo(UPDATED_LAST_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getMeanOperationDuration()).isEqualTo(UPDATED_MEAN_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getBalance()).isEqualByComparingTo(UPDATED_BALANCE);
        assertThat(testDocumentBankAccount.getOpeningDay()).isEqualTo(DEFAULT_OPENING_DAY);
        assertThat(testDocumentBankAccount.getLastOperationDate()).isEqualTo(DEFAULT_LAST_OPERATION_DATE);
        assertThat(testDocumentBankAccount.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testDocumentBankAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testDocumentBankAccount.getAttachment()).isEqualTo(DEFAULT_ATTACHMENT);
        assertThat(testDocumentBankAccount.getAttachmentContentType()).isEqualTo(DEFAULT_ATTACHMENT_CONTENT_TYPE);
        assertThat(testDocumentBankAccount.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    void fullUpdateDocumentBankAccountWithPatch() throws Exception {
        // Initialize the database
        documentBankAccountRepository.save(documentBankAccount);

        int databaseSizeBeforeUpdate = documentBankAccountRepository.findAll().size();

        // Update the documentBankAccount using partial update
        DocumentBankAccount partialUpdatedDocumentBankAccount = new DocumentBankAccount();
        partialUpdatedDocumentBankAccount.setId(documentBankAccount.getId());

        partialUpdatedDocumentBankAccount
            .name(UPDATED_NAME)
            .bankNumber(UPDATED_BANK_NUMBER)
            .agencyNumber(UPDATED_AGENCY_NUMBER)
            .lastOperationDuration(UPDATED_LAST_OPERATION_DURATION)
            .meanOperationDuration(UPDATED_MEAN_OPERATION_DURATION)
            .balance(UPDATED_BALANCE)
            .openingDay(UPDATED_OPENING_DAY)
            .lastOperationDate(UPDATED_LAST_OPERATION_DATE)
            .active(UPDATED_ACTIVE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .attachment(UPDATED_ATTACHMENT)
            .attachmentContentType(UPDATED_ATTACHMENT_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION);

        restDocumentBankAccountMockMvc
            .perform(
                patch("/api/document-bank-accounts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentBankAccount))
            )
            .andExpect(status().isOk());

        // Validate the DocumentBankAccount in the database
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeUpdate);
        DocumentBankAccount testDocumentBankAccount = documentBankAccountList.get(documentBankAccountList.size() - 1);
        assertThat(testDocumentBankAccount.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDocumentBankAccount.getBankNumber()).isEqualTo(UPDATED_BANK_NUMBER);
        assertThat(testDocumentBankAccount.getAgencyNumber()).isEqualTo(UPDATED_AGENCY_NUMBER);
        assertThat(testDocumentBankAccount.getLastOperationDuration()).isEqualTo(UPDATED_LAST_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getMeanOperationDuration()).isEqualTo(UPDATED_MEAN_OPERATION_DURATION);
        assertThat(testDocumentBankAccount.getBalance()).isEqualByComparingTo(UPDATED_BALANCE);
        assertThat(testDocumentBankAccount.getOpeningDay()).isEqualTo(UPDATED_OPENING_DAY);
        assertThat(testDocumentBankAccount.getLastOperationDate()).isEqualTo(UPDATED_LAST_OPERATION_DATE);
        assertThat(testDocumentBankAccount.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testDocumentBankAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testDocumentBankAccount.getAttachment()).isEqualTo(UPDATED_ATTACHMENT);
        assertThat(testDocumentBankAccount.getAttachmentContentType()).isEqualTo(UPDATED_ATTACHMENT_CONTENT_TYPE);
        assertThat(testDocumentBankAccount.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    void partialUpdateDocumentBankAccountShouldThrown() throws Exception {
        // Update the documentBankAccount without id should throw
        DocumentBankAccount partialUpdatedDocumentBankAccount = new DocumentBankAccount();

        restDocumentBankAccountMockMvc
            .perform(
                patch("/api/document-bank-accounts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentBankAccount))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void deleteDocumentBankAccount() throws Exception {
        // Initialize the database
        documentBankAccountRepository.save(documentBankAccount);

        int databaseSizeBeforeDelete = documentBankAccountRepository.findAll().size();

        // Delete the documentBankAccount
        restDocumentBankAccountMockMvc
            .perform(delete("/api/document-bank-accounts/{id}", documentBankAccount.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        SecurityContextHolder.setContext(TestSecurityContextHolder.getContext());
        List<DocumentBankAccount> documentBankAccountList = documentBankAccountRepository.findAll();
        assertThat(documentBankAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
