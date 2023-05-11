package bankApp.services;

import bankApp.DTOs.AccountDTO;
import bankApp.entities.Account;
import bankApp.repositories.AccountRepository;
import bankApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(UUID accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(UUID accountId, Account accountDetails) {
        Account account = accountRepository.findById(accountId).orElse(null);

        if (account != null) {
//            account.setUserId(accountDetails.getUserId());
            account.setBalance(accountDetails.getBalance());
            account.setNumber(accountDetails.getNumber());

            return accountRepository.save(account);
        } else {
            return null;
        }
    }

    public boolean deleteAccount(UUID accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);

        if (account != null) {
            accountRepository.delete(account);
            return true;
        } else {
            return false;
        }
    }


    public static AccountDTO convertAccountToDto(Account account) {
        return new AccountDTO(account.getBalance(), account.getNumber());
    }


}
