drop table if exists TO_DO CASCADE;
drop table if exists TASK_LIST CASCADE;
 
 create table if not exists TASK_LIST (
    ID bigint AUTO_INCREMENT,
    GROUP_NAME varchar(255),
    PRIMARY KEY(ID)
);
 
create table if not exists TO_DO (
    ID bigint AUTO_INCREMENT,
    COMPLETE boolean not null, 
    TASK varchar(255) not null, 
    TASK_LIST_ID varchar(255),
    PRIMARY KEY(ID),
    FOREIGN KEY(TASK_LIST_ID) REFERENCES TASK_LIST(ID) ON DELETE CASCADE
);