
--테이블삭제
drop table users;
--시퀀스 삭제
DROP SEQUENCE seq_user_no;


--users 테이블 생성
create table users (
    no number,
    id VARCHAR2(20) unique not null,    
    password VARCHAR2(20) not null,
    name varchar2(20),
    gender varchar2(10),
    PRIMARY KEY(no)
);


--시퀀스 생성
create sequence seq_user_no
increment by 1
start with 1
nocache;


--insert
insert into users   
values (seq_user_no.nextval, 'wonho', '123', '�ֿ�ȣ', 'male'); 


--확인용
select  *
from users;


commit;
