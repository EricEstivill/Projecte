/*SELECT NOM,SALARI 
FROM empleats
WHERE DEPT_NO=20;*/
/*SELECT nom,salari,ofici
FROM empleats
WHERE ofici='programador' AND SALARI<=20000 ;
*/
/*select  distinct ofici
from empleats;*/
/*select nom,ofici
from empleats
Where dept_no=20 OR SALARI between 15000 and 25000
order by nom;*/
/*select *
from empleats
where (dept_no =20 or dept_no=30 or dept_no=40) and nom like 'r%';*/
/*select nom,ofici 
from empleats
where DEPT_NO=30;*/
/*select *
from empleats
where ofici="operari"
order by nom 
,salari desc; */

/*select *
from empleats
where (nom like 'a%' or nom like 'j%') and (ofici="analista" or dept_no=20);
*/
/*select *
from empleats
where (salari >10000) and (dept_no=10 or DEPT_NO=20)
order by salari,nom desc;*/
/*select nom,salari
from empleats
where dept_no=10 or DEPT_NO=30 or DEPT_NO=100;*/
/*select nom,salari
from empleats
where salari>=16000 and (dept_no=10 or dept_no=20);*/
/*select nom,salari 
from empleats
where (dept_no!=10 or dept_no!=100) or (salari>=10000 and salari<=30000);*/
/*select nom,salari
from empleats
where (dept_no<10 or dept_no>30) or 
(salari=18000);*/
select nom,dept_no
from empleats
where (ofici like 'O____%') or (ofici like 't____%');
