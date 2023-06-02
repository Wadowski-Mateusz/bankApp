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
    private final UserService userService;
    private final UserDetailsService userDetailsService;

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


    public User getUserByAccountId(UUID accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.map(Account::getUser).orElse(null);
    }

    public Account getAccountByUserId(UUID userId) {
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
