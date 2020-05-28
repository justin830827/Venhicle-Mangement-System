DROP TABLE LOGIN;

DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE SERVICE_CENTER CASCADE CONSTRAINTS;

DROP TABLE APPOINTMENT CASCADE CONSTRAINTS;

DROP TABLE TIME_AVALIABLE CASCADE CONSTRAINTS;
DROP TABLE DATE_STATUS CASCADE CONSTRAINTS;
DROP TABLE TIMESLOT CASCADE CONSTRAINTS;

DROP TABLE CARSERVICE CASCADE CONSTRAINTS;

DROP TABLE CARTYPE CASCADE CONSTRAINTS;
DROP TABLE BASIC_SERVICE CASCADE CONSTRAINTS;
DROP TABLE CARTYPE_HAS_BASIC_SERVICE;

DROP TABLE REPAIR_SERVICE CASCADE CONSTRAINTS;
DROP TABLE REPAIR_HAS_BASIC_SERVICE;

DROP TABLE VEHICLE CASCADE CONSTRAINTS;

DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;

DROP TABLE PAYROLL CASCADE CONSTRAINTS;

DROP TABLE INVENTORY CASCADE CONSTRAINTS;
DROP TABLE CENTER_HAS_INVENTORY;

DROP TABLE DISTRIBUTOR CASCADE CONSTRAINTS;

DROP TABLE PORDER CASCADE CONSTRAINTS;
DROP TABLE INVENTORY_AUTOGEN_PORDER;

DROP TABLE NOTIF CASCADE CONSTRAINTS;

DROP TABLE MAINT_HAS_BASIC_SERVICE CASCADE CONSTRAINTS;

DROP TABLE M_VALUES;
DROP TABLE R_VALUES;

CREATE TABLE LOGIN 
(
    USER_ID VARCHAR2(40) NOT NULL, 
    LOGIN_ROLE VARCHAR2(20) NOT NULL, 
    USER_PW VARCHAR2(50) NOT NULL,
    CONSTRAINT LOGIN_PK PRIMARY KEY 
    (
        USER_ID
    )
);

CREATE TABLE SERVICE_CENTER 
(
    CENTER_ID VARCHAR2(20) NOT NULL, 
    CENTER_NAME VARCHAR2(20) NOT NULL, 
    CENTER_ADDR VARCHAR2(50) NOT NULL,
    CENTER_PHONENUM VARCHAR2(20) NOT NULL,
    OPEN_DAY VARCHAR2(20), 
    OPEN_HOUR VARCHAR2(20), 
    EMP_QUAN NUMBER, 
    CONSTRAINT SERVICE_CENTER_PK PRIMARY KEY 
    (
        CENTER_ID 
    )
);

CREATE TABLE DISTRIBUTOR 
(
    DISTRIBUTOR_ID VARCHAR2(20) NOT NULL,
    DISTRIBUTOR_NAME VARCHAR2(20) NOT NULL,  
    CONSTRAINT DISTRIBUTOR_PK PRIMARY KEY 
    (
        DISTRIBUTOR_ID 
    )
);


CREATE TABLE CUSTOMER 
(
    CUSTOMER_ID NUMBER NOT NULL,
    CUSTOMER_PHONENUM VARCHAR2(20) NOT NULL,
    CUSTOMER_ADDR VARCHAR2(50) NOT NULL,
    CUSTOMER_NAME VARCHAR2(20) NOT NULL,
    CUSTOMER_EMAIL VARCHAR2(20) NOT NULL UNIQUE,
    CENTER_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT CUSTOMER_PK PRIMARY KEY 
    (
        CUSTOMER_ID 
    )
    ,CONSTRAINT CUSTOMER_FK1 FOREIGN KEY
    (
        CENTER_ID
    )
    REFERENCES SERVICE_CENTER (CENTER_ID)
    ON DELETE CASCADE
);

CREATE TABLE INVENTORY 
(
    PART_ID VARCHAR2(20) NOT NULL,
    PART_NAME VARCHAR2(20) NOT NULL,
    CAR_TYPE_INVEN VARCHAR2(20) NOT NULL,
    PRICE REAL ,
    WARRANTY VARCHAR2(20),
    WINDOWS NUMBER,
    DISTRIBUTOR_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT INVENTORY_PK PRIMARY KEY 
    (
        PART_ID
    )
    ,CONSTRAINT INVENTORY_FK1 FOREIGN KEY
    (
        DISTRIBUTOR_ID
    )
    REFERENCES DISTRIBUTOR (DISTRIBUTOR_ID)
    ON DELETE CASCADE
);

CREATE TABLE BASIC_SERVICE
(
    BASIC_SERVICE_ID VARCHAR2(20) NOT NULL,
    CAR_MODEL VARCHAR2(20),
    BASIC_DETAIL_NAME VARCHAR2(50),
    BASIC_CHARGE_TYPE VARCHAR2(20),
    BASIC_CHARGE_RATE REAL,
    BASIC_TIME_REQUIRE REAL,
    REQUIRED_QUANTITY NUMBER,
    PART_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT BASIC_SERVICE PRIMARY KEY 
    (
        BASIC_SERVICE_ID
    )
    ,CONSTRAINT BASIC_SERVICE_FK1 FOREIGN KEY
    (
        PART_ID
    )
    REFERENCES INVENTORY (PART_ID)
    ON DELETE CASCADE
);

CREATE TABLE PORDER 
(
    PORDER_ID VARCHAR2(20) NOT NULL,
    PORDER_GEN_DATE DATE NOT NULL,
    PORDER_EXP_DATE DATE NOT NULL,
    PORDER_ACT_DATE DATE,
    PORDER_QUAN NUMBER NOT NULL,
    PORDER_ORIGIN VARCHAR2(20) NOT NULL,
    PORDER_DESTINATION VARCHAR2(20) NOT NULL,
    PORDER_STATUS VARCHAR2(20) NOT NULL,
    PART_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT PORDER_PK PRIMARY KEY 
    (
        PORDER_ID 
    )
    ,CONSTRAINT PORDER_FK1 FOREIGN KEY
    (
        PART_ID
    )
    REFERENCES INVENTORY (PART_ID)
    ON DELETE CASCADE
    ,CONSTRAINT PORDER_FK2 FOREIGN KEY
    (
        PORDER_DESTINATION
    )
    REFERENCES SERVICE_CENTER (CENTER_ID)
    ON DELETE CASCADE
);


CREATE TABLE NOTIF
(
    NOTIF_ID VARCHAR2(20) NOT NULL,
    NOTIF_DATE DATE NOT NULL,
    NOTIF_CENTER VARCHAR2(20),
    NOTIF_MES VARCHAR2(100),
    PORDER_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT NOTIF_PK PRIMARY KEY 
    (
        NOTIF_ID
    )
    ,CONSTRAINT NOTIF_FK1 FOREIGN KEY
    (
        PORDER_ID
    )
    REFERENCES PORDER (PORDER_ID)
    ON DELETE CASCADE
);

CREATE TABLE CARTYPE 
(
    BRAND VARCHAR2(20) NOT NULL,
    MODEL VARCHAR2(20) NOT NULL,
    YEAR NUMBER,
    A_MILEAGE_INCREMENT NUMBER,
    B_MILEAGE_INCREMENT NUMBER, 
    C_MILEAGE_INCREMENT NUMBER, 
    CONSTRAINT CARTYPE_PK PRIMARY KEY 
    (
        BRAND,
        MODEL
    )
);

CREATE TABLE VEHICLE 
(
    LICENSE_NUM VARCHAR2(20) NOT NULL,
    PURCHASE_DATE DATE NOT NULL,
    MILEAGE NUMBER,
    MADE_YEAR NUMBER,
    LAST_SERVICE_TYPE VARCHAR2(20),
    LAST_SERVICE_DATE DATE,
    BRAND VARCHAR2(20) NOT NULL,
    MODEL VARCHAR2(20) NOT NULL,
    CUSTOMER_ID NUMBER NOT NULL,
    CONSTRAINT VEHICLE_PK PRIMARY KEY 
    (
        LICENSE_NUM 
    )
    ,CONSTRAINT VEHICLE_FK1 FOREIGN KEY
    (
        CUSTOMER_ID
    )
    REFERENCES CUSTOMER (CUSTOMER_ID)
    ON DELETE CASCADE
    ,CONSTRAINT VEHICLE_FK2 FOREIGN KEY
    (
        BRAND,
        MODEL
    )
    REFERENCES CARTYPE (BRAND,MODEL)
    ON DELETE CASCADE
);



CREATE TABLE APPOINTMENT 
(
    APPOINTMENT_ID VARCHAR2(20) NOT NULL,
    SERVICE_TYPE VARCHAR2(100) NOT NULL,
    SERVICE_SPECIFY VARCHAR2(100) NOT NULL,
    MECHANIC_NAME VARCHAR2(20),  
    APPOINTMENT_DATE DATE,
    BEGIN_TIME VARCHAR(45),
    END_TIME VARCHAR(45),
    LICENSE_NUM VARCHAR2(20) NOT NULL,
    CUSTOMER_ID NUMBER NOT NULL,
    CONSTRAINT APPOINTMENT_PK PRIMARY KEY 
    (
        APPOINTMENT_ID 
    )
    ,CONSTRAINT APPOINTMENT_FK1 FOREIGN KEY
    (
        CUSTOMER_ID
    )
    REFERENCES CUSTOMER (CUSTOMER_ID)
    ON DELETE CASCADE
    ,CONSTRAINT APPOINTMENT_FK2 FOREIGN KEY
    (
        LICENSE_NUM
    )
    REFERENCES VEHICLE (LICENSE_NUM)
    ON DELETE CASCADE
);

CREATE TABLE TIME_AVALIABLE (
    CENTER_ID VARCHAR2(20) NOT NULL,
    THE_DATE DATE NOT NULL,
    BEGIN_SLOT NUMBER NOT NULL,
    END_SLOT NUMBER NOT NULL,
    CONSTRAINT TIME_AVALIABLE_PK PRIMARY KEY
    (
        THE_DATE,
        CENTER_ID,
        BEGIN_SLOT
    )
    ,CONSTRAINT TIME_AVALIABLE_FK1 FOREIGN KEY
    (
        CENTER_ID
    )
    REFERENCES SERVICE_CENTER (CENTER_ID)
    ON DELETE CASCADE
);

CREATE TABLE TIMESLOT 
(
    TIME_ID NUMBER NOT NULL,
    SLOT VARCHAR(45) NOT NULL,
    CONSTRAINT TIMESLOT_PK PRIMARY KEY
    (
        TIME_ID
    )
);

CREATE TABLE DATE_STATUS 
(
    CENTER_ID VARCHAR2(20) NOT NULL,
    APP_DATE DATE NOT NULL,
    MAINTENANCE_NUM NUMBER NOT NULL,
    CONSTRAINT DATE_STATUS_PK PRIMARY KEY
    (
        CENTER_ID,
        APP_DATE
    )
    ,CONSTRAINT DATE_STATUS_FK1 FOREIGN KEY
    (
        CENTER_ID
    )
    REFERENCES SERVICE_CENTER (CENTER_ID)
    ON DELETE CASCADE
); 



CREATE TABLE EMPLOYEE 
(
    EMP_ID VARCHAR2(20) NOT NULL, 
    EMP_NAME VARCHAR2(20) NOT NULL, 
    EMP_ROLE VARCHAR2(20) NOT NULL, 
    EMP_ADDR VARCHAR2(50) NOT NULL, 
    EMP_EMAIL VARCHAR2(50) NOT NULL,
    EMP_PHONE VARCHAR2(20) NOT NULL,
    EMP_STARTDATE DATE NOT NULL,
    EMP_WAGE NUMBER NOT NULL,
    EMP_WAGE_FREQ VARCHAR2(20) NOT NULL,
    CENTER_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT EMPLOYEE_PK PRIMARY KEY 
    (
        EMP_ID
    )
    ,CONSTRAINT EMPLOYEE_FK1 FOREIGN KEY
    (
        CENTER_ID
    )
    REFERENCES SERVICE_CENTER (CENTER_ID)
    ON DELETE CASCADE
);


CREATE TABLE CARSERVICE 
(
    CARSERVICE_ID VARCHAR2(20) NOT NULL,
    STATUS VARCHAR2(20),
    APPOINTMENT_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT CARSERVICE_PK PRIMARY KEY 
    (
        CARSERVICE_ID 
    )
    ,CONSTRAINT CARSERVICE_FK1 FOREIGN KEY
    (
        APPOINTMENT_ID
    )
    REFERENCES APPOINTMENT (APPOINTMENT_ID)
    ON DELETE CASCADE
);

CREATE TABLE REPAIR_SERVICE 
(
    REPAIR_ID VARCHAR2(20) NOT NULL,
    REPAIR_CODE VARCHAR2(100) NOT NULL,
    DIAGNOSTIC VARCHAR2(100) NOT NULL,
    REPAIR_FEE REAL NOT NULL,
    CONSTRAINT REPAIR_SERVICE_PK PRIMARY KEY 
    (
       REPAIR_ID
    )
);

CREATE TABLE PAYROLL 
(
    PAYROLL_ID VARCHAR2(20) NOT NULL, 
    PAYCHECK_DATE DATE NOT NULL,  
    UNITS NUMBER(20) NOT NULL,
    PAYTYPE VARCHAR(20) NOT NULL,
    CUR_EARN NUMBER NOT NULL, 
    SOFAR_EARN NUMBER NOT NULL,
    EMP_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT PAYROLL_PK PRIMARY KEY 
    (
        PAYROLL_ID,
        EMP_ID
    )
    ,CONSTRAINT PAYROLL_FK1 FOREIGN KEY
    (
        EMP_ID
    )
    REFERENCES EMPLOYEE (EMP_ID)
    ON DELETE CASCADE
);

CREATE TABLE INVENTORY_AUTOGEN_PORDER
(
    PORDER_ID VARCHAR2(20) NOT NULL,
    PART_ID VARCHAR2(20) NOT NULL,
    CONSTRAINT INVENTORY_AUTOGEN_PORDER_PK PRIMARY KEY 
    (
        PORDER_ID, 
        PART_ID
    )
    ,CONSTRAINT INVENTORY_AUTOGEN_PORDER_FK1 FOREIGN KEY
    (
        PORDER_ID 
    )
    REFERENCES PORDER (PORDER_ID)
    ON DELETE CASCADE
    ,CONSTRAINT INVENTORY_AUTOGEN_PORDER_FK2 FOREIGN KEY
    (
        PART_ID
    )
    REFERENCES INVENTORY (PART_ID)
    ON DELETE CASCADE
);

CREATE TABLE CARTYPE_HAS_BASIC_SERVICE
(
    BRAND VARCHAR2(20) NOT NULL,
    MODEL VARCHAR2(20) NOT NULL,
    SERVICE_TYPE VARCHAR2(20) NOT NULL,
    BASIC_SERVICE_ID VARCHAR2(50) NOT NULL,
   
    CONSTRAINT CARTYPE_HAS_BASIC_SERVICE_PK PRIMARY KEY 
    (
        BRAND,
        MODEL,
        BASIC_SERVICE_ID
    )
    ,CONSTRAINT CARTYPE_HAS_BASIC_SERVICE_FK1 FOREIGN KEY
    (
        BASIC_SERVICE_ID 
    )
    REFERENCES BASIC_SERVICE (BASIC_SERVICE_ID)
    ON DELETE CASCADE
    ,CONSTRAINT CARTYPE_HAS_BASIC_SERVICE_FK2 FOREIGN KEY
    (
        BRAND,
        MODEL 
    )
    REFERENCES CARTYPE (BRAND,MODEL)
    ON DELETE CASCADE
);

CREATE TABLE REPAIR_HAS_BASIC_SERVICE
(
    REPAIR_ID VARCHAR2(20) NOT NULL,
    CAR_MODEL VARCHAR2(50) NOT NULL,
    BASIC_SERVICE_ID VARCHAR2(50) NOT NULL,
    CONSTRAINT REPAIR_HAS_BASIC_SERVICE_PK PRIMARY KEY 
    (
        REPAIR_ID,
        BASIC_SERVICE_ID
    )
    ,CONSTRAINT REPAIR_HAS_BASIC_SERVICE_FK1 FOREIGN KEY
    (
        BASIC_SERVICE_ID 
    )
    REFERENCES BASIC_SERVICE (BASIC_SERVICE_ID)
    ON DELETE CASCADE
    ,CONSTRAINT REPAIR_HAS_BASIC_SERVICE_FK2 FOREIGN KEY
    (
        REPAIR_ID 
    )
    REFERENCES REPAIR_SERVICE (REPAIR_ID)
    ON DELETE CASCADE
);

CREATE TABLE MAINT_HAS_BASIC_SERVICE
(
    MAINTENANCE_ID VARCHAR2(20) NOT NULL,
    CAR_MODEL VARCHAR2(50) NOT NULL,
    SERVICE_TYPE VARCHAR2(20) NOT NULL,
    BASIC_SERVICE_ID VARCHAR2(50) NOT NULL,
    CONSTRAINT MAINT_HAS_BASIC_SERVICE_PK PRIMARY KEY 
    (
        MAINTENANCE_ID,
        BASIC_SERVICE_ID
    )
    ,CONSTRAINT MAINT_HAS_BASIC_SERVICE_FK1 FOREIGN KEY
    (
        BASIC_SERVICE_ID 
    )
    REFERENCES BASIC_SERVICE (BASIC_SERVICE_ID)
    ON DELETE CASCADE
);


CREATE TABLE CENTER_HAS_INVENTORY
(
    PART_ID VARCHAR2(20) NOT NULL,
    CENTER_ID VARCHAR2(20) NOT NULL,
    MIN_STOCK NUMBER NOT NULL,
    MIN_ORDER NUMBER NOT NULL,
    CUR_STOCK NUMBER NOT NULL,
    CONSTRAINT CENTER_HAS_INVENTORY_PK PRIMARY KEY 
    (
        PART_ID,
        CENTER_ID
    )
    ,CONSTRAINT CENTER_HAS_INVENTORY_FK1 FOREIGN KEY
    (
        PART_ID
    )
    REFERENCES INVENTORY (PART_ID)
    ON DELETE CASCADE
    ,CONSTRAINT CENTER_HAS_INVENTORY_FK2 FOREIGN KEY
    (
        CENTER_ID 
    )
    REFERENCES SERVICE_CENTER (CENTER_ID)
    ON DELETE CASCADE
);

CREATE TABLE M_VALUES
(
    MODEL VARCHAR2(20) NOT NULL,
    DETAIL VARCHAR2(20) NOT NULL,
    COST_TIME REAL,
    LABOR REAL,
    PART REAL,
    CONSTRAINT M_VALUES_PK PRIMARY KEY 
    (
        MODEL,
        DETAIL
    )
);

CREATE TABLE R_VALUES
(
    MODEL VARCHAR2(20) NOT NULL,
    DETAIL VARCHAR2(20) NOT NULL,
    COST_TIME REAL,
    LABOR REAL,
    PART REAL,
    DIAGNOSTIC_FEE REAL,
    CONSTRAINT R_VALUES_PK PRIMARY KEY 
    (
        MODEL,
        DETAIL
    )
);