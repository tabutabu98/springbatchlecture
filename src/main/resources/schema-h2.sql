create table BATCH_JOB_INSTANCE
(
    JOB_INSTANCE_ID BIGINT auto_increment
        primary key,
    VERSION         BIGINT,
    JOB_NAME        VARCHAR(100) not null,
    JOB_KEY         VARCHAR(32)  not null,
    constraint JOB_INST_UN
        unique (JOB_NAME, JOB_KEY)
);

create table BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID           BIGINT auto_increment
        primary key,
    VERSION                    BIGINT,
    JOB_INSTANCE_ID            BIGINT    not null,
    CREATE_TIME                TIMESTAMP not null,
    START_TIME                 TIMESTAMP default NULL,
    END_TIME                   TIMESTAMP default NULL,
    STATUS                     VARCHAR(10),
    EXIT_CODE                  VARCHAR(2500),
    EXIT_MESSAGE               VARCHAR(2500),
    LAST_UPDATED               TIMESTAMP,
    JOB_CONFIGURATION_LOCATION VARCHAR(2500),
    constraint JOB_INST_EXEC_FK
        foreign key (JOB_INSTANCE_ID) references BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
);

create table BATCH_JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID BIGINT       not null,
    TYPE_CD          VARCHAR(6)   not null,
    KEY_NAME         VARCHAR(100) not null,
    STRING_VAL       VARCHAR(250),
    DATE_VAL         TIMESTAMP default NULL,
    LONG_VAL         BIGINT,
    DOUBLE_VAL       DOUBLE PRECISION,
    IDENTIFYING      CHAR(1)      not null,
    constraint JOB_EXEC_PARAMS_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_STEP_EXECUTION
(
    STEP_EXECUTION_ID  BIGINT auto_increment
        primary key,
    VERSION            BIGINT       not null,
    STEP_NAME          VARCHAR(100) not null,
    JOB_EXECUTION_ID   BIGINT       not null,
    START_TIME         TIMESTAMP    not null,
    END_TIME           TIMESTAMP default NULL,
    STATUS             VARCHAR(10),
    COMMIT_COUNT       BIGINT,
    READ_COUNT         BIGINT,
    FILTER_COUNT       BIGINT,
    WRITE_COUNT        BIGINT,
    READ_SKIP_COUNT    BIGINT,
    WRITE_SKIP_COUNT   BIGINT,
    PROCESS_SKIP_COUNT BIGINT,
    ROLLBACK_COUNT     BIGINT,
    EXIT_CODE          VARCHAR(2500),
    EXIT_MESSAGE       VARCHAR(2500),
    LAST_UPDATED       TIMESTAMP,
    constraint JOB_EXEC_STEP_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_STEP_EXECUTION_CONTEXT
(
    STEP_EXECUTION_ID  BIGINT        not null
        primary key,
    SHORT_CONTEXT      VARCHAR(2500) not null,
    SERIALIZED_CONTEXT LONGVARCHAR,
    constraint STEP_EXEC_CTX_FK
        foreign key (STEP_EXECUTION_ID) references BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
);

create table BATCH_JOB_EXECUTION_CONTEXT
(
    JOB_EXECUTION_ID   BIGINT        not null
        primary key,
    SHORT_CONTEXT      VARCHAR(2500) not null,
    SERIALIZED_CONTEXT LONGVARCHAR,
    constraint JOB_EXEC_CTX_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

