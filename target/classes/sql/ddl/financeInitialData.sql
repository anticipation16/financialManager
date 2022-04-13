insert into account_type
values ('savings account', 'asset');

insert into account_type
values ('current account', 'asset');

insert into account_type
values ('investment account', 'investment');

insert into account_type
values ('credit card account', 'liability');

insert into account
values (1231, 1000, 'HDFC', 'savings account');
insert into account
values (1232, 2000, 'HDFC', 'current account');
insert into account
values (1233, 5000, 'Axis Bank', 'investment account');
insert into account
values (1234, 1500, 'Indian Bank', 'credit card account');


insert into txn_type
values ('gas', 'expense');
insert into txn_type
values ('salary', 'income');
insert into txn_type
values ('food', 'expense');
insert into txn_type
values ('transport', 'expense');

insert into txn(account_number, transaction_category, amount, created_at)
values (1232, 'food', -100,
        '2022-03-13 03:43pm');

insert into txn(account_number, transaction_category, amount, created_at)
values (1233, 'transport', -900,
        '2022-04-01 03:45am');

insert into txn(account_number, transaction_category, amount, created_at)
values (1234, 'transport', -500,
        '2022-04-02 03:40pm');

insert into txn(account_number, transaction_category, amount, created_at)
values (1231, 'salary', 12000,
        '2022-04-03 02:42pm');

insert into txn(account_number, transaction_category, amount, created_at)
values (1231, 'food', -700,
        '2022-04-04 01:42pm');

insert into txn(account_number, transaction_category, amount, created_at)
values (1232, 'salary', 10000,
        '2022-04-12 03:42am');

insert into txn(account_number, transaction_category, amount, created_at)
values (1231, 'gas',
        -200, '2022-04-13 03:45am');

insert into txn(account_number, transaction_category, amount, created_at)
values (1233, 'salary', 9000,
        '2022-04-14 03:42pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1234, 'salary', 11000,
        '2022-04-14 08:42am');
insert into txn(account_number, transaction_category, amount, created_at)
values (1233, 'food', -400,
        '2022-04-14 09:42pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1231, 'transport', -300,
        '2022-04-14 10:50pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1232, 'gas', -1000,
        '2022-04-14 11:42pm');

insert into txn(account_number, transaction_category, amount, created_at)
values (1232, 'gas', -1000,
        '2022-04-15 07:42am');