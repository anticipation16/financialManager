select *
from txn
order by transaction_number DESC
LIMIT ?;