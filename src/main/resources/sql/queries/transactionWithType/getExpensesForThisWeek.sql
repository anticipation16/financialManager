SELECT *
from vw_transactions_with_type
where type = 'expense'
  and substr(created_at, 1, 10) BETWEEN substr(datetime('now', '-7 days'), 1, 10) AND
    substr(datetime('now', 'localtime'), 1, 10);