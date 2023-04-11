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


-- book 테이블 제목, 지은이, 출판사, 발행연도, ISBN, 가격, 주제(장르), 등록번호, 청구기호 
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('이방인', '알베르 카뮈', '브라운힐', 2022, 9791158251215, 13000, '프랑스 소설', 'SE0000648797','863-22-53');



