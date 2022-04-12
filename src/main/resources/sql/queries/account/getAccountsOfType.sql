select *
from account
         INNER JOIN account_type ON account.account_name = account_type.account_name
where account_type = ?