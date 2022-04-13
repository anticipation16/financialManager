select *
from vw_transactions_with_type
order by transaction_number DESC
limit ?;