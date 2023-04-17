DROP TABLE loan;
DROP TABLE member;
DROP TABLE book;
DROP TABLE publisher;

CREATE TABLE book (
    b_id             CHAR(16) NOT NULL,
    title            VARCHAR2(150) NOT NULL,
    author           VARCHAR2(50) NOT NULL,
    genre            VARCHAR2(90) NOT NULL,
    publication_year NUMBER(4) NOT NULL,
    isbn_num         NUMBER(13) NOT NULL,
    callsign_num     VARCHAR2(20) NOT NULL,
    loan_yn          CHAR(2) DEFAULT 'N' NOT NULL,
    p_name           VARCHAR2(100) NOT NULL,
    price            NUMBER(8) NULL
);
CREATE UNIQUE INDEX xpk책 ON
    book (
        b_id
    ASC );
ALTER TABLE book ADD CONSTRAINT xpk책 PRIMARY KEY ( b_id );
CREATE TABLE loan (
    loan_date        DATE NOT NULL,
    ex_return_date   DATE NOT NULL,
    real_return_date DATE NULL,
    b_id             CHAR(16) NOT NULL,
    l_number         CHAR(11) NOT NULL,
    m_id             CHAR(8) NOT NULL
);
CREATE UNIQUE INDEX xpk대출 ON
    loan (
        l_number
    ASC );
ALTER TABLE loan ADD CONSTRAINT xpk대출 PRIMARY KEY ( l_number );
CREATE TABLE member (
    m_id       CHAR(8) NOT NULL,
    name       VARCHAR2(50) NOT NULL,
    password VARCHAR2(20) NOT NULL, --비밀번호
    birth_date DATE NOT NULL,
    phone_num  VARCHAR2(14) NOT NULL,
    email      VARCHAR2(50) NOT NULL,
    address    VARCHAR2(120) NOT NULL,
    loans_num  NUMBER NULL
);

CREATE UNIQUE INDEX xpk회원 ON
    member (
        m_id
    ASC );
ALTER TABLE member ADD CONSTRAINT xpk회원 PRIMARY KEY ( m_id );
CREATE TABLE publisher (
    p_name    VARCHAR2(100) NOT NULL,
    id        NUMBER(3) NOT NULL,
    phone_num CHAR(18) NULL
);
CREATE UNIQUE INDEX xpk출판사 ON
    publisher (
        p_name
    ASC );
ALTER TABLE publisher ADD CONSTRAINT xpk출판사 PRIMARY KEY ( p_name );
ALTER TABLE book ADD (
    CONSTRAINT r_3 FOREIGN KEY ( p_name )
        REFERENCES publisher ( p_name )
);
-- Publisher 테이블 id 값 자동 증가
CREATE SEQUENCE publisher_id_seq
  START WITH 1
  INCREMENT BY 1;
CREATE OR REPLACE TRIGGER trg_insert_book BEFORE
    INSERT ON book
    FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    SELECT
        COUNT(*)
    INTO v_count
    FROM
        publisher
    WHERE
        p_name = :new.p_name;
    IF v_count = 0 THEN
        INSERT INTO publisher (
            id,
            p_name
        ) VALUES (
            publisher_id_seq.NEXTVAL,
            :new.p_name
        );
    END IF;
END;
/
  
ALTER TABLE loan ADD (
    CONSTRAINT r_2 FOREIGN KEY ( b_id )
        REFERENCES book ( b_id )
);
ALTER TABLE loan ADD (
    CONSTRAINT r_1 FOREIGN KEY ( m_id )
        REFERENCES member ( m_id )
);
-- 대출 시퀸스
CREATE SEQUENCE loan_seq
    INCREMENT BY 1
    START WITH 1
    MAXVALUE 999
CYCLE 
    NOCACHE 
    NOORDER;
-- 대출 트리거
CREATE OR REPLACE TRIGGER loan_trigger_num  -- 대출번호 L_number 생성 하는 트리거
    BEFORE INSERT ON loan
    FOR EACH ROW
BEGIN
    SELECT TO_CHAR(SYSDATE, 'YYMMDDHH') || LPAD(loan_seq.NEXTVAL, 3, '0')
    INTO :new.L_NUMBER
    FROM dual;
    
    :NEW.loan_date := SYSDATE;
    :NEW.ex_return_date := SYSDATE + 7;
END;
/

CREATE OR REPLACE TRIGGER loan_trigger_Y             -- 대출하면 책 테이블에 대출여부 Y 업데이트
AFTER INSERT ON loan
FOR EACH ROW 
BEGIN
  UPDATE book
  SET loan_yn = 'Y'
  WHERE book.b_id = :NEW.b_id;

  UPDATE member
  SET loans_num = loans_num + 1
  WHERE member.m_id = :NEW.m_id;
END;
/
CREATE OR REPLACE TRIGGER loan_trigger_N           -- 대출하면 책 테이블에 대출여부 N 업데이트
AFTER UPDATE ON loan
FOR EACH ROW
BEGIN
  UPDATE book SET loan_yn = 'N' WHERE book.b_id = :NEW.b_id;
  
   UPDATE member
  SET loans_num = loans_num - 1
  WHERE member.m_id = :NEW.m_id;
END;
/

-- 대출 테이블에 회원과 책 키값 가져오기만 하면 됨
INSERT into LOAN (m_id, b_id)
	VALUES ('rudwls1', 'SE0000648797');

DELETE FROM book;
select * from book;


SELECT    rtrim(b.title),    rtrim(l.ex_return_date) FROM         loan l    JOIN book b ON l.b_id = b.b_id WHERE    l.m_id = (        SELECT            m_id        FROM            member        WHERE            m_id = 'rudwls1'    );

--member 테이블 회원ID, 이름, 비밀번호, 생년월일, 연락처, 이메일, 주소, 대출권수
INSERT
    into member(m_id, name, password, birth_date, phone_num, email, address, loans_num)
    values ('rudwls1', '이설이', 'Aabcd123!', '990611', '010-1234-5678', 'rudwlsaa@naver.com', '대전 중구 선화동 523-2', 2);
INSERT
    into member(m_id, name, password, birth_date, phone_num, email, address, loans_num)
    values ('minjun1', '김민준', 'Aabcdef75', '000725', '010-4567-5823', 'jinja@gamil.com', '대전 서구 갈마동 302-5', 5);
INSERT
    into member(m_id, name, password, birth_date, phone_num, email, address, loans_num)
    values ('jinwoo5', '최진우', 'Bdjkl123!', '981212', '010-5691-2856', 'jinwoo3@naver.com', '대전 동구 낭월동 123-12', 3);
INSERT
    into member(m_id, name, password, birth_date, phone_num, email, address, loans_num)
    values ('dongjin2', '이동진', 'Ekdos45*', '010823', '010-5896-4794', 'dongjin45@gamil.com', '대전 중구 은행동 125-8', 2);
INSERT
    into member(m_id, name, password, birth_date, phone_num, email, address, loans_num)
    values ('seoyean2', '민서연', 'Homeonly12', '990611', '010-1234-5678', 'rudwlsaa@naver.com', '대전 중구 선화동 523-2', 2);
    
-- book 테이블 제목, 지은이, 출판사, 발행연도, ISBN, 가격, 주제(장르), 등록번호, 청구기호 
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('이방인', '알베르 카뮈', '브라운힐', 2022, 9791158251215, 13000, '프랑스 소설', 'SE0000648797', '863-22-53');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('화학진료학', '테라사와 카츠토시', '수퍼노바', 2020, 9791189396060, 25000, '한의학', 'SE0000673477', '519-23-3');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('현존으로의 초대', '틸든 에드워즈', '한국 살렘 타임교육C&P', 2021,  9791191239461, 18000, '기독교 신앙', 'SE0000673349', '234.8-23-5');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('해와 바람이 쉬어가는 집', '이규환', '예일미디어', 2023, 9791189886202, 18000, '친환경 건축', 'SE0000673362', '544.1-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('다이다이 서점에서', '다지리 히사코', '니라이카나이', 2023, 9791198177803, 15000, '일본 문학', 'SE0000673150', '834-23-3');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('다른 몸들을 위한 디자인', '사라 헨드렌', '김영사', 2023, 9788934943396, 17800, '디자인', 'SE0000673223', '338.1-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('내일의 스타벅스를 찾아라', '마이클 모', '데이원', 2022, 9791168470415, 18000, '주식 투자', 'SE0000673221', '327.856-23-10');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('해적', '제임스 데이비스', '책세상어린이', 2022, 9791159318207, 13000, '세계사', 'SJ0000154800', '아 909-23-8');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('큰 배와 작은 배와 오렌지', '안나 맥그리거', '키즈엠', 2022, 9791164632909, 13000, '영미 문학', 'SJ0000154819', '아 843-23-39');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('첫 인사', '클레르 르부르', '옐로스톤', 2023, 9791187079361, 16000, '프랑스 문학', 'SJ0000154705', '아 863-23-6');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('조국에 핀 도라지꽃', '한상식', '가문비어린이 가문비', 2021, 9788969024183, 11000, '창작 동화', 'SJ0000154789', '아 813.7-23-61');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('웃음이 멈추지 않는 몹쓸 병에 걸린 아이', '수진', '키큰도토리', 2023, 9791192762012, 14000, '창작 그림책', 'SJ0000154691', '아 813.7-23-55');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('아낌없이 주는 나무', '쉘 실버스타인', '선영사', 2022, 9788975584350, 8000, '영미 문학', 'SJ0000154441', '아 843-22-392');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('베이징에 온 서양인, 조선과 마주치다', '손성욱', '동북아역사재단', 2022, 9788961877503, 10000, '조선사', 'SE0000673272', '정책 911.05-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('부는 어디서 오는가', '윌리스 와틀스', '콘텐츠그룹 포레스트', 2022, 9791192625775, 15000, '부자 성공법', 'SE0000670351', '325.2113-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('경주산책:심화령에서 내려오다', '김유경', '눈빛 출판사', 2022, 9788974099671, 25000, '국내 여행', 'SE0000672798', '981.18502-22-3');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('모비 딕', '허먼 멜빌', '현대 지성', 2022, 9791139707137, 19900, '영미 문학', 'SE0000671888', '843.4-22-25');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('마지막 여행:인생 특강', '홍진화', '솔로몬', 2021, 9788982556005, 14000, '기독교 신앙 생활', 'SE0000672362', '234.8-22-219');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('교육은 사랑이다', '심승환', '교육과학사', 2022, 9788925417196, 13000, '교육 철학', 'SE0000672253', '370.1-22-24');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('해결사', '엄민식', '아이러브북', 2022, 9791192644042, 7000, '한국 현대 소설', 'SE0000671874', '813.7-22-782');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('한국어 읽기 교육론', '하채현', '한국 문화사', 2022, 9791169190367, 22000, '한국어 교육', 'SE0000672464', '374.71-22-6');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('피노키오', '카를로 콜로디', '글담출판사', 2022, 9791159351303, 13800, '이탈리아 문학', 'SE0000671783', '883-22-6');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('대중문화의 이해', '정준영', '한국방송통신대학교출판문화원', 2018, 9788920028915, 93300, '대중 문화', 'SE0000673499', '331.53-23-2');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('일본 온천 여행', '인페인터글로벌', '꿈의 지도', 2022, 9791167620361, 18000, '일본 여행', 'SE0000673237', '981.302-23-2');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('인조이 파리', '김지선', '넥서스books 넥서스', 2023, 9791166834264, 18000, '해외 여행 안내', 'SE0000673236', '982.6302-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('운이란 무엇인가:행운과 불운에 관한 오류와 진실', '스티븐 D.헤일스', '소소의 책', 2023, 9791188941919, 19000, '운수', 'SE0000673256', '188-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('코펜하겐 삼부작', '토베 디틀레우센', '을유문화사', 2022, 9788932461311, 13000, '덴마크 문학', 'SE0000673261', '859.81-23-1-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('처음부터 내내 좋아했어', '와타야 리사', '비채 김영사', 2022, 9788934949015, 15800, '일본 현대 소설', 'SE0000673359', '833.6-23-22');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('특별하게 파리=Paris', '김진주', '디스커버리미디어', 2023, 9791188829323, 19000, '여행 정보', 'SE0000673244', '982.6302-23-2');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('디테일의 발견:고객을 사로잡은 101가지 한 끗', '생각노트', '위즈덤하우스', 2023, 9791168125841, 18000, '브랜딩 마케팅 전략', 'SE0000673225', '325.571-23-4');
    



