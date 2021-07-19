
--테이블삭제
drop table users;
drop table board;
--시퀀스 삭제
DROP SEQUENCE seq_user_no;
drop sequence seq_board_no;

--유저 테이블 생성
create table users (
    no number,
    id VARCHAR2(20) unique not null,    --유니크 이면서 not null임
    password VARCHAR2(20) not null,
    name varchar2(20),
    gender varchar2(10),
    PRIMARY KEY(no)
);


--유저 시퀀스 생성
create sequence seq_user_no
increment by 1
start with 1
nocache;


--보드 테이블 생성
create table board(
    no number,
    title varchar2(500) not null,
    content varchar2(4000),
    hit number default 0,
    reg_date date not null,
    user_no number not null,
    primary key(no),
    constraint board_fk foreign key (user_no)
    references users(no)
);


--보드 시퀀스 생성
create sequence seq_board_no
increment by 1
start with 1
nocache;

--유저 insert문
insert into users 
values (seq_user_no.nextval, 'wonho', '123', '최원호', 'male'); 


--보드 insert문
insert into board  
values (seq_board_no.nextval, '오늘말이지', '음.머리가 아프구만', default, sysdate, 1);


commit;


--확인용
select  *
from users;


select  *
from guestbook;


select  *
from board;


--전체 목록 조회
select  board.no,
        users.name,
        board.title,
        board.content,
        board.hit,
        to_char(board.reg_date, 'yyyy-mm-dd') reg_date,
        board.user_no
from users, board
where users.no = board.user_no
order by reg_date desc;


--게시판 글 조회하기
select  board.no,
        users.name,
        board.title,
        board.content,
        board.hit,
        to_char(board.reg_date, 'yyyy-mm-dd') reg_date,
        board.user_no
from users, board
where users.no = board.user_no
and board.no = 1;


--조회수 업데이트
update board
set hit = 1-1
where no=1;


--게시글 수정
update board
set title = '내일은',
    content = '내일로'
where no = 1;