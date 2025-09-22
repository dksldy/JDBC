-- Test 테이블을 생성한다.
DROP TABLE TEST;
CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(30),
    TDATE DATE
);

SELECT * FROM TEST;

INSERT INTO TEST VALUES(1, '홍길동', SYSDATE);

COMMIT;
ROLLBACK;





























