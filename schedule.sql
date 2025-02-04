use schedule;

CREATE TABLE schedule
(
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    name VARCHAR(15) COMMENT '작성자',
    password VARCHAR(15) COMMENT '비밀번호',
    todo VARCHAR(50) COMMENT '할 일',
    date DATETIME COMMENT '작성일/수정일'
);