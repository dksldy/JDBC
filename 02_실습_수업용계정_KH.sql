-- 회원관리 1단계 프로젝트 멤버테이블 생성(제약조건포함)
DROP TABLE MEMBER;
CREATE TABLE MEMBER (
    USERNO     NUMBER,               -- PK
    USERID     VARCHAR2(20) NOT NULL,-- UNIQUE / NOT NULL 을 줌으로써 PK 격이 됨(중복 불가, NULL불가)
    USERPW     VARCHAR2(20) NOT NULL,
    USERNAME   VARCHAR2(20) NOT NULL,
    GENDER     CHAR(1),              -- M OR F
    AGE        NUMBER,
    EMAIL      VARCHAR2(30),
    ADDRESS    VARCHAR2(100),
    PHONE      VARCHAR2(13),
    HOBBY      VARCHAR2(50),
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL,

    -- 테이블 레벨 제약조건 정의
    CONSTRAINT pk_member_userno PRIMARY KEY (USERNO),
    CONSTRAINT uq_member_userid UNIQUE (USERID),
    CONSTRAINT chk_member_gender CHECK (GENDER IN ('M', 'F'))
);

-- SEQUENCE 생성
DROP SEQUENCE SEQ_USERNO;
CREATE SEQUENCE SEQ_USERNO
NOCACHE;

-- 멤버 입력
INSERT INTO MEMBER
    VALUES (SEQ_USERNO.NEXTVAL, 'admin', '1234', '관리자', 'M', 20, 'admin@kh.or.kr', 
                                                '서울', '010-1010-0101', null, '2020-07-30');
INSERT INTO MEMBER
    VALUES (SEQ_USERNO.NEXTVAL, 'sjlim', '1234', '홍길동', null, 20, 'sjlim@kh.or.kr', 
                                                        null, '010-9090-0101', null, default);

SELECT * FROM MEMBER;
SELECT * FROM USER_SEQUENCES;

COMMIT;

SELECT * FROM MEMBER WHERE USERID = 'admin';

UPDATE MEMBER SET USERPW = '12345', USERNAME = '사용자', ADDRESS = '인천', 
                PHONE = '010-1234-5678', HOBBY = '농구'
WHERE USERID = 'admin';

DELETE FROM MEMBER WHERE USERID = 'admin';

ROLLBACK;

