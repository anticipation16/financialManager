-- Note that this query is hardcoded in TransactionWithTypeDAOSQLite because of String constraints.
select *
from vw_transactions_with_type
where created_at like
      '%2022/04%'
  and type = 'expense'
order by amount
limit ?