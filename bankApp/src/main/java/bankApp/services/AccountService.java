package bankApp.services;

import bankApp.DTOs.AccountDTO;
import bankApp.entities.Account;
import bankApp.entities.User;
import bankApp.entities.UserDetails;
import bankApp.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserDetailsService userDetailsService;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(UUID accountId) {
        return accountRepository.findById(accountId);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccountBalance(Account account) {
        Account accountUpdated = accountRepository.findById(account.getId()).orElse(null);

        if (accountUpdated != null) {
            accountUpdated.setBalance(account.getBalance());
            return accountRepository.save(account);
        } else {
            return null;
        }
    }

    public Optional<Account> findAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    public boolean deleteAccount(UUID accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        // TODO if both transaction are null, then delete transaction, if not, set to null
        // delete only if balance is >= 0
        if (account != null) {
            accountRepository.delete(account);
            return true;
        } else {
            return false;
        }
    }


    public User getUserByAccountId(UUID accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.map(Account::getUser).orElse(null);
    }

    public Account findAccountByUserId(UUID userId) {
        Optional<Account> account = accountRepository.findByUserId(userId);
        return account.orElse(null);
    }



    public String getFullNameByAccountId(UUID accountId) {
        User user = this.getUserByAccountId(accountId);
        if(user == null)
            return "";
        Optional<UserDetails> userDetails = userDetailsService.getByUserId(user.getId());
        return userDetails.map(UserDetails::getFullName).orElse("");
    }

    public static AccountDTO convertAccountToDto(Account account) {
        return new AccountDTO(account.getBalance(), account.getNumber());
    }


}
