SELECT *
from vw_transactions_with_type
where type = 'expense'
  and substr(created_at, 1, 10) = substr(datetime('now', 'localtime'), 1, 10);
