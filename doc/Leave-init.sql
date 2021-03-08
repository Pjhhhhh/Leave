DROP TABLE IF EXISTS leave_form;
DROP TABLE IF EXISTS leave_permission_role_relation;
DROP TABLE IF EXISTS leave_role_user_relation;
DROP TABLE IF EXISTS leave_permission;
DROP TABLE IF EXISTS leave_user;
DROP TABLE IF EXISTS leave_role;

CREATE TABLE leave_user
(
    user_id varchar(10) PRIMARY KEY,
    user_name varchar(10) NOT NULL,
    username varchar(20) NOT NULL,
    name varchar(20) NOT NULL,
    password varchar(20) NOT NULL,
    "securePassword" varchar(130) NOT NULL,
    dept varchar(20) NOT NULL,
    post varchar(20) NOT NULL
);

INSERT INTO leave_user (user_id, user_name, username, name, password, "securePassword", dept, post)
VALUES
('1', '领导1', '1', '1', '1', 'c4ca4238a0b923820dcc509a6f75849b', '1', '1'),
('2', '考勤员1', '2', '2', '2', 'c81e728d9d4c2f636f067f89cc14862c', '2', '2'),
('3', '职员1', '3', '3', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '3', '3');


CREATE TABLE leave_role
(
    role_id varchar(10) PRIMARY KEY,
    role_name varchar(10) NOT NULL,
    parent_id varchar(10) NOT NULL
);

INSERT INTO leave_role (role_id, role_name, parent_id)
VALUES
('1', '领导', '0'),
('2', '考勤员', '1'),
('3', '职员', '1');



CREATE TABLE leave_permission
(
    per_id varchar(10) PRIMARY KEY,
    per_name varchar(20) NOT NULL
);

INSERT INTO leave_permission (per_id, per_name)
VALUES
('1', '申请'),
('2', '审核'),
('3', '查询');


CREATE TABLE leave_role_user_relation
(
    id varchar(10) PRIMARY KEY,
    role_id varchar(10) NOT NULL,
    user_id varchar(10) NOT NULL,
    CONSTRAINT fk_relation_role_id_to_leave_role FOREIGN KEY (role_id)
    REFERENCES leave_role (role_id),
    CONSTRAINT fk_relation_user_id_to_leave_user FOREIGN KEY (user_id)
    REFERENCES leave_user (user_id)
);

INSERT INTO leave_role_user_relation (id, role_id, user_id)
VALUES
('1', '1', '1'),
('2', '2', '2'),
('3', '3', '3');


CREATE TABLE leave_permission_role_relation
(
    id varchar(10) PRIMARY KEY,
    per_id varchar(10) NOT NULL,
    role_id varchar(10) NOT NULL,
    CONSTRAINT fk_relation_per_id_to_leave_permission FOREIGN KEY (per_id)
    REFERENCES leave_permission (per_id),
    CONSTRAINT fk_relation_role_id_to_leave_role FOREIGN KEY (role_id)
    REFERENCES leave_role (role_id)
);

INSERT INTO leave_permission_role_relation (id, per_id, role_id)
VALUES
('1', '1', '1'),
('2', '2', '1'),
('3', '1', '2'),
('4', '3', '2'),
('5', '1', '3');


CREATE TABLE leave_form
(
    uuid varchar(130) PRIMARY KEY,
    "startDate" varchar(10) NOT NULL,
    "endDate" varchar(10) NOT NULL,
    name varchar(10) NOT NULL,
    dept varchar(20) NOT NULL,
    post varchar(20) NOT NULL,
    reason varchar(50) NOT NULL,
    type varchar(10) NOT NULL,
    day varchar(10) NOT NULL,
    hour varchar(10) NOT NULL,
    state varchar(10) NOT NULL,
    opinion varchar(50)
);