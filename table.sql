drop table if exists student;
drop table if exists mentor;
drop table if exists secretary;
drop table if exists college_leader;
drop table if exists student_leader;  
drop table if exists university_leader; 
drop table if exists account;


create table account (
    id serial primary key,
    username varchar(50) not null unique,
    password varchar(100) not null,
    role varchar(20) check (role in ('student', 'mentor', 'secretary', 'student_leader', 'student_manager', 'student_leader', 'university_leader', 'system_manager', 'auditor_manager', null)),
    failed_times integer default 0,
    lock_time timestamp,
    last_password_change_date timestamp
);


create table mentor (
    id serial primary key,
    name varchar(50) not null,
    college varchar(100),
    account_id integer unique,
    foreign key (account_id) references account(id) on delete set null
);


create table student (
    id serial primary key,
    name varchar(50) not null,
    gender varchar(10),
    nation varchar(50),
    birth_date date,
    birth_place varchar(100),
    id_number varchar(20),
    student_id varchar(20),
    country varchar(50),
    home_address varchar(200),
    phone_number varchar(20),
    email varchar(50),
    dorm_id varchar(20),
    grade integer,
    college varchar(100),
    major varchar(100),
    campus varchar(50),
    entrance_date date,
    class_id varchar(50),
    wechat varchar(50),
    qq varchar(20),
    home_phone_number varchar(20),
    home_contact_man varchar(50),
    account_id integer unique,
    mentor_id integer,
    mentor varchar(50),
    foreign key (account_id) references account(id) on delete set null
);


create table secretary (
    id serial primary key,
    name varchar(50) not null,
    college varchar(100),
    account_id integer unique,
    foreign key (account_id) references account(id) on delete set null
);


create table college_leader (
    id serial primary key,
    name varchar(50) not null,
    college varchar(100),
    account_id integer unique,
    foreign key (account_id) references account(id) on delete set null
);


create table student_leader (
    id serial primary key,
    name varchar(50) not null,
    
    account_id integer unique,
    foreign key (account_id) references account(id) on delete set null
);


create table university_leader (
    id serial primary key,
    name varchar(50) not null,
    
    account_id integer unique,
    foreign key (account_id) references account(id) on delete set null
);

-- 操作日志表
create table operation_log (
    id bigint primary key auto_increment,
    user_id bigint not null comment '用户id',
    username varchar(50) not null comment '用户名',
    operation_type varchar(50) not null comment '操作类型',
    operation_content text comment '操作内容详情',
    ip_address varchar(50) comment 'ip地址',
    user_agent varchar(255) comment '用户代理',
    operation_time datetime not null comment '操作时间',
    status varchar(20) default 'success' comment '操作状态'
);

-- 添加索引以提高查询性能
create index idx_operation_time on operation_log (operation_time);
create index idx_user_id on operation_log (user_id);
create index idx_operation_type on operation_log (operation_type);


create index idx_account_username on account(username);
create index idx_student_college on student(college);
create index idx_student_major on student(major);
create index idx_student_mentor_id on student(mentor_id);

