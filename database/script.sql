create table roles
(
    id   uuid default gen_random_uuid() not null
        primary key
        unique,
    role varchar                        not null
        unique
);

alter table roles
    owner to postgres;

create table users
(
    id          uuid    default gen_random_uuid() not null
        primary key
        unique,
    login       varchar                           not null
        unique,
    password    varchar                           not null,
    is_verified boolean default false             not null,
    role_id     uuid                              not null
        constraint users_roles_id_fk
            references roles
            on update cascade on delete restrict
);

alter table users
    owner to postgres;

create table user_details
(
    id        uuid default gen_random_uuid() not null
        constraint users_details_pkey
            primary key
        constraint users_details_id_key
            unique,
    name      varchar,
    surname   varchar,
    email     varchar,
    birthday  date,
    id_number varchar,
    user_id   uuid                           not null
        unique
        constraint user_details_users_id_fk
            references users
            on update cascade on delete cascade
);

alter table user_details
    owner to postgres;

create table addresses
(
    id              uuid default gen_random_uuid() not null
        primary key
        unique,
    country         varchar                        not null,
    sector          varchar,
    city            varchar                        not null,
    street          varchar,
    number          varchar                        not null,
    zip             varchar,
    user_details_id uuid                           not null
        unique
        constraint addresses_user_details_id_fk
            references user_details
            on update cascade on delete cascade
);

alter table addresses
    owner to postgres;

create table accounts
(
    id      uuid           default gen_random_uuid() not null
        primary key
        unique,
    user_id uuid                                     not null
        unique
        constraint accounts_users_id_fk
            references users
            on update cascade on delete cascade,
    balance numeric(20, 2) default 0                 not null,
    number  varchar                                  not null
        unique
);

alter table accounts
    owner to postgres;

create table transactions
(
    id           uuid default gen_random_uuid() not null
        primary key
        unique,
    from_account uuid                           not null
        constraint transactions_accounts_id_fk
            references accounts
            on update cascade on delete set null,
    to_account   uuid                           not null
        constraint transactions_accounts_id_fk2
            references accounts
            on update cascade on delete set null,
    date         timestamp                      not null,
    amount       numeric(20, 2)                 not null
);

alter table transactions
    owner to postgres;

create table loans
(
    id        uuid           default gen_random_uuid() not null
        primary key
        unique,
    name      varchar                                  not null,
    interest  numeric(8, 4)  default 0                 not null,
    date_from timestamp                                not null,
    date_to   timestamp                                not null,
    amount    numeric(20, 2) default 0                 not null,
    user_id   uuid                                     not null
        constraint loans_users_id_fk
            references users
            on update cascade on delete cascade
);

alter table loans
    owner to postgres;

create table cards
(
    id          uuid default gen_random_uuid() not null
        unique,
    number      varchar(16)                    not null
        unique,
    account_id  uuid                           not null
        constraint cards_accounts_id_fk
            references accounts
            on update cascade on delete cascade,
    expiry_date timestamp                      not null,
    cvv         varchar(3)                     not null
);

alter table cards
    owner to postgres;

create table user_options
(
    id                 uuid    default gen_random_uuid() not null
        primary key
        unique,
    email_subscription boolean default false             not null,
    user_id            uuid                              not null
        unique
        constraint user_options_users_id_fk
            references users
            on update cascade on delete cascade
);

alter table user_options
    owner to postgres;

create table announcements
(
    id        uuid default gen_random_uuid() not null
        primary key,
    date_from timestamp                      not null,
    date_to   timestamp                      not null,
    content   varchar                        not null
);

alter table announcements
    owner to postgres;


