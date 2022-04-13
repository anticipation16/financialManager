insert into account_type
values ('Savings Account', 'asset');

insert into account_type
values ('Current Account', 'asset');

insert into account_type
values ('Investment Account', 'investment');

insert into account_type
values ('Credit Card Account', 'liability');

insert into account
values (1231, 1000, 'HDFC', 'Savings Account');
insert into account
values (1232, 2000, 'HDFC', 'Current Account');
insert into account
values (1233, 5000, 'Axis Bank', 'Investment Account');
insert into account
values (1234, 1500, 'Indian Bank', 'Credit Card Account');


insert into txn_type
values ('Gas', 'expense');
insert into txn_type
values ('Salary', 'income');
insert into txn_type
values ('Food', 'expense');
insert into txn_type
values ('Transport', 'expense');

insert into txn(account_number,transaction_category, amount, created_at)
values (1231, 'Gas',
        -200, '2022/04/13 03:45am');
insert into txn(account_number, transaction_category, amount, created_at)
values (1232, 'Salary', 10000,
        '2022/04/12 03:42am');
insert into txn(account_number, transaction_category, amount, created_at)
values (1232, 'Food', -100,
        '2022/03/13 03:43pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1233, 'Transport', -900,
        '2022/04/01 03:45am');
insert into txn(account_number, transaction_category, amount, created_at)
values (1234, 'Transport', -500,
        '2022/04/02 03:40pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1231, 'Salary', 12000,
        '2022/04/03 02:42pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1231, 'Food', -700,
        '2022/04/04 01:42pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1233, 'Salary', 9000,
        '2022/04/13 03:42pm');
insert into txn(account_number, transaction_category, amount, created_at)
values (1234, 'Salary', 11000,
        '2022/04/05 08:42am');
insert into txn(account_number, transaction_category, amount, created_at)
values (1233, 'Food', -400,
        '2022/04/13 09:42am');
insert into txn(account_number, transaction_category, amount, created_at)
values (1231, 'Transport', -300,
        '2022/04/06 08:50am');
insert into txn(account_number, transaction_category, amount, created_at)
values (1232, 'Gas', -1000,
        '2022/04/13 07:42am');