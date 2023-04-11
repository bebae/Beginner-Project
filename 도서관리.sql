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
CREATE UNIQUE INDEX xpkå ON
    book (
        b_id
    ASC );
ALTER TABLE book ADD CONSTRAINT xpkå PRIMARY KEY ( b_id );
CREATE TABLE loan (
    loan_date        DATE NOT NULL,
    ex_return_date   DATE NOT NULL,
    real_return_date DATE NULL,
    b_id             CHAR(16) NOT NULL,
    l_number         CHAR(11) NOT NULL,
    m_id             CHAR(8) NOT NULL
);
CREATE UNIQUE INDEX xpk���� ON
    loan (
        l_number
    ASC );
ALTER TABLE loan ADD CONSTRAINT xpk���� PRIMARY KEY ( l_number );
CREATE TABLE member (
    m_id       CHAR(8) NOT NULL,
    name       VARCHAR2(50) NOT NULL,
    birth_date DATE NOT NULL,
    phone_num  VARCHAR2(14) NOT NULL,
    email      VARCHAR2(50) NOT NULL,
    address    VARCHAR2(120) NOT NULL,
    loans_num  NUMBER NULL
);
CREATE UNIQUE INDEX xpkȸ�� ON
    member (
        m_id
    ASC );
ALTER TABLE member ADD CONSTRAINT xpkȸ�� PRIMARY KEY ( m_id );
CREATE TABLE publisher (
    p_name    VARCHAR2(100) NOT NULL,
    id        NUMBER(3) NOT NULL,
    phone_num CHAR(18) NULL
);
CREATE UNIQUE INDEX xpk���ǻ� ON
    publisher (
        p_name
    ASC );
ALTER TABLE publisher ADD CONSTRAINT xpk���ǻ� PRIMARY KEY ( p_name );
ALTER TABLE book ADD (
    CONSTRAINT r_3 FOREIGN KEY ( p_name )
        REFERENCES publisher ( p_name )
);
-- Publisher ���̺� id �� �ڵ� ����
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


-- book ���̺� ����, ������, ���ǻ�, ���࿬��, ISBN, ����, ����(�帣), ��Ϲ�ȣ, û����ȣ 
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�̹���', '�˺��� ī��', '������', 2022, 9791158251215, 13000, '������ �Ҽ�', 'SE0000648797', '863-22-53');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('ȭ��������', '�׶��� ī�����', '���۳��', 2020, 9791189396060, 25000, '������', 'SE0000673477', '519-23-3');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('���������� �ʴ�', 'ƿ�� �������', '�ѱ� �췽 Ÿ�ӱ���C&P', 2021,  9791191239461, 18000, '�⵶�� �ž�', 'SE0000673349', '234.8-23-5');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�ؿ� �ٶ��� ����� ��', '�̱�ȯ', '���Ϲ̵��', 2023, 9791189886202, 18000, 'ģȯ�� ����', 'SE0000673362', '544.1-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('���̴��� ��������', '������ ������', '�϶���ī����', 2023, 9791198177803, 15000, '�Ϻ� ����', 'SE0000673150', '834-23-3');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�ٸ� ������ ���� ������', '��� ��巻', '�迵��', 2023, 9788934943396, 17800, '������', 'SE0000673223', '338.1-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('������ ��Ÿ������ ã�ƶ�', '����Ŭ ��', '���̿�', 2022, 9791168470415, 18000, '�ֽ� ����', 'SE0000673221', '327.856-23-10');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('����', '���ӽ� ���̺�', 'å������', 2022, 9791159318207, 13000, '�����', 'SJ0000154800', '�� 909-23-8');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('ū ��� ���� ��� ������', '�ȳ� �Ʊ׸���', 'Ű�', 2022, 9791164632909, 13000, '���� ����', 'SJ0000154819', '�� 843-23-39');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('ù �λ�', 'Ŭ���� ���θ�', '���ν���', 2023, 9791187079361, 16000, '������ ����', 'SJ0000154705', '�� 863-23-6');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('������ �� ��������', '�ѻ��', '�������� ������', 2021, 9788969024183, 11000, 'â�� ��ȭ', 'SJ0000154789', '�� 813.7-23-61');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('������ ������ �ʴ� ���� ���� �ɸ� ����', '����', 'Űū���丮', 2023, 9791192762012, 14000, 'â�� �׸�å', 'SJ0000154691', '�� 813.7-23-55');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�Ƴ����� �ִ� ����', '�� �ǹ���Ÿ��', '������', 2022, 9788975584350, 8000, '���� ����', 'SJ0000154441', '�� 843-22-392');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('����¡�� �� ������, ������ ����ġ��', '�ռ���', '���Ͼƿ������', 2022, 9788961877503, 10000, '������', 'SE0000673272', '��å 911.05-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�δ� ��� ���°�', '������ ��Ʋ��', '�������׷� ������Ʈ', 2022, 9791192625775, 15000, '���� ������', 'SE0000670351', '325.2113-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('���ֻ�å:��ȭ�ɿ��� ��������', '������', '���� ���ǻ�', 2022, 9788974099671, 25000, '���� ����', 'SE0000672798', '981.18502-22-3');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('��� ��', '��� ���', '���� ����', 2022, 9791139707137, 19900, '���� ����', 'SE0000671888', '843.4-22-25');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('������ ����:�λ� Ư��', 'ȫ��ȭ', '�ַθ�', 2021, 9788982556005, 14000, '�⵶�� �ž� ��Ȱ', 'SE0000672362', '234.8-22-219');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('������ ����̴�', '�ɽ�ȯ', '�������л�', 2022, 9788925417196, 13000, '���� ö��', 'SE0000672253', '370.1-22-24');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�ذ��', '���ν�', '���̷����', 2022, 9791192644042, 7000, '�ѱ� ���� �Ҽ�', 'SE0000671874', '813.7-22-782');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�ѱ��� �б� ������', '��ä��', '�ѱ� ��ȭ��', 2022, 9791169190367, 22000, '�ѱ��� ����', 'SE0000672464', '374.71-22-6');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�ǳ�Ű��', 'ī���� �ݷε�', '�۴����ǻ�', 2022, 9791159351303, 13800, '��Ż���� ����', 'SE0000671783', '883-22-6');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('���߹�ȭ�� ����', '���ؿ�', '�ѱ������Ŵ��б����ǹ�ȭ��', 2018, 9788920028915, 93300, '���� ��ȭ', 'SE0000673499', '331.53-23-2');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�Ϻ� ��õ ����', '�������ͱ۷ι�', '���� ����', 2022, 9791167620361, 18000, '�Ϻ� ����', 'SE0000673237', '981.302-23-2');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('������ �ĸ�', '������', '�ؼ���books �ؼ���', 2023, 9791166834264, 18000, '�ؿ� ���� �ȳ�', 'SE0000673236', '982.6302-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('���̶� �����ΰ�:���� �ҿ ���� ������ ����', '��Ƽ�� D.���Ͻ�', '�Ҽ��� å', 2023, 9791188941919, 19000, '���', 'SE0000673256', '188-23-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�����ϰ� �����', '�亣 ��Ʋ���켾', '������ȭ��', 2022, 9788932461311, 13000, '����ũ ����', 'SE0000673261', '859.81-23-1-1');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('ó������ ���� �����߾�', '��Ÿ�� ����', '��ä �迵��', 2022, 9788934949015, 15800, '�Ϻ� ���� �Ҽ�', 'SE0000673359', '833.6-23-22');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('Ư���ϰ� �ĸ�=Paris', '������', '��Ŀ�����̵��', 2023, 9791188829323, 19000, '���� ����', 'SE0000673244', '982.6302-23-2');
INSERT
    into book(title, author, p_name, publication_year, isbn_num, price, genre, b_id, callsign_num)
    values ('�������� �߰�:���� ������� 101���� �� ��', '������Ʈ', '������Ͽ콺', 2023, 9791168125841, 18000, '�귣�� ������ ����', 'SE0000673225', '325.571-23-4');
    



