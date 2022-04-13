select transaction_category, sum(amount) as sum
from vw_transactions_with_type
where type = 'expense'
  and substr(created_at, 1, 10) BETWEEN substr(datetime('now', '-30 days'), 1, 10) AND
    substr(datetime('now', 'localtime'), 1, 10)
group by transaction_category order by sum;

select * from vw_transactions_with_type;