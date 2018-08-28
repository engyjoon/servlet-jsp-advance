select
	t4.triggerid, t4.description, t4.status, t4.value, t4.priority, t4.state
from hosts t1, items t2, functions t3, triggers t4
where 1=1
  and t1.hostid = t2.hostid
  and t2.itemid = t3.itemid
  and t3.triggerid = t4.triggerid
  and t1.host = 'Zabbix server';
  
-- triggers.status
-- 0 - (default) enabled;
-- 1 - disabled.

-- triggers.value
-- 0 - (default) OK; 
-- 1 - problem.
  
-- triggers.priority
-- 0 - (default) not classified; 
-- 1 - information; 
-- 2 - warning; 
-- 3 - average; 
-- 4 - high; 
-- 5 - disaster.

with recursive w1(num) as (values(0) union all select num+1 from w1 where num < 5)
select w1.num, cnt
from (
	select t4.priority, count(*) cnt
	from hosts t1, items t2, functions t3, triggers t4
	where 1=1
	  and t1.hostid = t2.hostid and t2.itemid = t3.itemid and t3.triggerid = t4.triggerid
	  and t1.host = 'Zabbix server'
	group by t4.priority) v1 right join w1 on (v1.priority = w1.num)
order by 1 desc;

with recursive w1(num) as (values(0) union all select num + 1 from w1 where num < 5)
select w1.num, count(*)
from hosts t1 inner join items t2 on (t1.hostid = t2.hostid) 
	          inner join functions t3 on (t2.itemid = t3.itemid)
	          inner join triggers t4 on (t3.triggerid = t4.triggerid)
	          right join w1 on (t4.priority = w1.num)
where 1=1
  and t1.hostid = t2.hostid and t2.itemid = t3.itemid and t3.triggerid = t4.triggerid
  and t1.host = 'Zabbix server'
group by w1.num
order by 1 desc;

select 
	t2.itemid, t2.key_, t2.name, 
	t3.clock, to_timestamp(t3.clock), t3.value
from hosts t1, items t2, history_uint t3
where t1.hostid = t2.hostid and t2.itemid = t3.itemid
  and t2.key_ like 'vm%'
order by t3.clock;

select 
	t2.itemid, t2.key_, t2.name, 
	t3.clock, to_timestamp(t3.clock), t3.value
from hosts t1, items t2, history t3
where t1.hostid = t2.hostid and t2.itemid = t3.itemid
  and t1.host = 'Zabbix server'
  and t2.key_ = 'system.cpu.util[,user]'
  and t3.clock > extract(epoch from date_trunc('second', now() - interval '1 hour'))
order by t3.clock;