package bankApp;


import bankApp.entities.*;
import bankApp.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final UserOptionsRepository userOptionsRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransactionRepository transactionRepository;

    private final List<Account> acc = new ArrayList<>();


    @Override
    public void run(String... args) {

        if(roleRepository.findAll().isEmpty())
            insertRoles();

        if(userRepository.findAll().isEmpty()) {
            insertEmpl();
            insertClients();
        }

        if(transactionRepository.findAll().isEmpty())
            insertTransactions();

    }


    private void insertTransactions() {


        Transaction t = new Transaction(
                UUID.randomUUID(),
                LocalDateTime.now().minusDays(2).minusHours(4),
                BigDecimal.valueOf(123.32),
                "for burgers",
                acc.get(0),
                acc.get(1)
        );

        Transaction t1 = new Transaction(
                UUID.randomUUID(),
                LocalDateTime.now().minusHours(2),
                BigDecimal.valueOf(42.54),
                "for pizza",
                acc.get(1),
                acc.get(0)
        );

        Transaction t2 = new Transaction(
                UUID.randomUUID(),
                LocalDateTime.now(),
                BigDecimal.valueOf(8.24),
                "for onion rings",
                acc.get(0),
                acc.get(1)
        );


        transactionRepository.save(t);
        transactionRepository.save(t1);
        transactionRepository.save(t2);


    }

    private void insertRoles() {
        Set<String> roles = Set.of(Consts.ROLE_EMPLOYEE, Consts.ROLE_ADMIN, Consts.ROLE_CLIENT);

        for (String role: roles) {
            roleRepository.save(new Role(UUID.randomUUID(), role));
        }
    }

    private void insertClients() {

        Role roleClient = roleRepository.findByRole(Consts.ROLE_CLIENT).orElseThrow();

        // Lists for names, last names, and address components
        List<String> firstNames = List.of("John", "Sarah", "David", "Emily");
        List<String> lastNames = List.of("Smith", "Johnson", "Garcia", "Lee");
        List<String> ids = List.of("12345678", "87654321", "ABCDEFGH", "HGFEDCBA");
        List<String> countries = List.of("United States", "Canada", "Spain", "Australia");
        List<String> sectors = List.of("Residential", "Residential", "Urban", "Residential");
        List<String> cities = List.of("New York", "Toronto", "Barcelona", "Sydney");
        List<String> streets = List.of("Elm Street", "Maple Avenue", "Rambla de Catalunya", "Bondi Road");
        List<String> numbers = List.of("123", "456", "789", "321");
        List<String> zips = List.of("10001", "M5V 2M6", "08008", "2000");

        for (int j = 1; j < 5; j++) {

            int i = j - 1;

            String loginPasswordEmail = firstNames.get(i) + lastNames.get(i).charAt(0);

            User user = new User(
                    UUID.randomUUID(),
                    loginPasswordEmail,
                    passwordEncoder.encode(loginPasswordEmail),
                    i % 2 == 0,
                    roleClient
            );

            UserDetails userDetails = new UserDetails(
                    UUID.randomUUID(),
                    firstNames.get(i),
                    lastNames.get(i),
                    loginPasswordEmail + "@example.com",
                    LocalDate.now().minusYears(38 + i).minusMonths(6 - i).minusDays(3 + i*2),
                    ids.get(i),
                    "id" + j + ".png",
                    user
            );

            Address address = new Address(
                    UUID.randomUUID(),
                    countries.get(i),
                    sectors.get(i),
                    cities.get(i),
                    streets.get(i),
                    numbers.get(i),
                    zips.get(i),
                    userDetails
            );

            UserOptions userOptions = new UserOptions(UUID.randomUUID(), false, user);

            float balance = 0;
            if (i % 2 == 0)
                balance = 1234 + i * 10;

            Account account = new Account(UUID.randomUUID(), BigDecimal.valueOf(balance), randAccountNumber(), user);
            if(i % 2 == 0)
                acc.add(account);

            userRepository.save(user);
            userDetailsRepository.save(userDetails);
            addressRepository.save(address);
            userOptionsRepository.save(userOptions);
            accountRepository.save(account);
        }

    }

    private void insertEmpl() {
        Role roleEmployee = roleRepository.findByRole(Consts.ROLE_EMPLOYEE).orElseThrow();

        User empl = new User(
                UUID.randomUUID(),
                "empl",
                passwordEncoder.encode("empl"),
                true,
                roleEmployee
        );

        UserDetails emplDetails = new UserDetails(
                UUID.randomUUID(),
                "Rebecka",
                "Smith",
                "email@example.com",
                LocalDate.now().minusYears(38).minusMonths(1).minusDays(3),
                "1231ab41",
                null,
                empl
        );

        Address address = new Address(
                UUID.randomUUID(),
                "Poland",
                "Lesser Poland",
                "Cracow",
                "Slawkowska",
                "99999",
                "00-000",
                emplDetails
        );

        userRepository.save(empl);
        userDetailsRepository.save(emplDetails);
        addressRepository.save(address);
    }



    private String randAccountNumber() {
        final int length = 26;
        return Stream.generate(new Random()::nextInt)
                .limit(length)
                .map(i -> Math.abs(i) % 10)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
