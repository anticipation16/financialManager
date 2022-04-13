select *
from vw_transactions_with_type
where type = 'expense'
  and substr(created_at, 1, 10)
    between substr(datetime('now', '-30 days'), 1, 10)
    and
    substr(datetime('now', 'localtime'), 1, 10)
order by amount
limit ?