select c.constructorId as idC, r1.driverId as id1, d.surname as name1, r2.driverId as id2, d.surname as name2, count(*) as peso
from results as r1, results as r2, drivers as d, constructors as c
where d.driverId=r1.driverId
and d.driverId=r2.driverId
and r1.constructorId=c.constructorId
and c.constructorId='20'
group by c.constructorId