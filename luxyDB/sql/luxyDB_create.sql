-- Create tables
-- Table: address
CREATE TABLE address (
    id int NOT NULL AUTO_INCREMENT,
    st_address varchar(100) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(20) NOT NULL,
    country varchar(20) NOT NULL,
    zip varchar(10) NOT NULL,
    CONSTRAINT address_pk PRIMARY KEY (id)
);

-- Table: application
CREATE TABLE application (
    id int NOT NULL AUTO_INCREMENT,
    renter_id int NOT NULL,
    requirement varchar(100) NULL,
    app_submit_date date NOT NULL,
    app_status varchar(20) NOT NULL,
    unit_id int NULL,
    CONSTRAINT application_pk PRIMARY KEY (id)
);

-- Table: community
CREATE TABLE community (
    id int NOT NULL AUTO_INCREMENT,
    comm_name varchar(100) NOT NULL,
    address_id int NOT NULL,
    CONSTRAINT community_pk PRIMARY KEY (id)
);

-- Table: payment_log
CREATE TABLE payment_log (
    id int NOT NULL AUTO_INCREMENT,
    unit_leasing_log_id int NOT NULL,
    payment_status varchar(10) NOT NULL,
    payment_date date NOT NULL,
    payment_type varchar(20) NOT NULL,
    check_num int NULL,
    transact_num int NULL,
    CONSTRAINT payment_log_pk PRIMARY KEY (id)
);

-- Table: renter
CREATE TABLE renter (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    identity_proof varchar(50) NOT NULL,
    perm_address varchar(100) NOT NULL,
    CONSTRAINT renter_pk PRIMARY KEY (id)
);

-- Table: service_category
CREATE TABLE service_category (
    id int NOT NULL AUTO_INCREMENT,
    service_category varchar(20) NOT NULL,
    CONSTRAINT service_category_pk PRIMARY KEY (id)
);

-- Table: service_request
CREATE TABLE service_request (
    id int NOT NULL AUTO_INCREMENT,
    unit_leasing_log_id int NOT NULL,
    service_category_id int NOT NULL,
    problem_descrip varchar(200) NOT NULL,
    log_date date NOT NULL,
    closure_date date NULL,
    CONSTRAINT service_request_pk PRIMARY KEY (id)
);

-- Table: unit
CREATE TABLE unit (
    id int NOT NULL AUTO_INCREMENT,
    comm_id int NOT NULL,
    address_id int NOT NULL,
    num_bedroom int NOT NULL,
    num_bathroom int NOT NULL,
    is_avail varchar(1) NULL,
    is_reserved varchar(1) NULL,
    unit_avail_from date NULL,
    unit_lease_id int NOT NULL,
    unit_descrip varchar(200) NULL,
    unit_number int NULL,
    unit_floor int NULL,
    CONSTRAINT unit_pk PRIMARY KEY (id)
);

-- Table: unit_lease
CREATE TABLE unit_lease (
    id int NOT NULL AUTO_INCREMENT,
    leasing_type varchar(50) NOT NULL,
    app_fee int NOT NULL,
    security_deposit int NOT NULL,
    CONSTRAINT unit_lease_pk PRIMARY KEY (id)
);

-- Table: unit_leasing_log
CREATE TABLE unit_leasing_log (
    id int NOT NULL AUTO_INCREMENT,
    app_id int NOT NULL,
    unit_id int NOT NULL,
    monthly_rent int NOT NULL,
    lease_start date NOT NULL,
    lease_end date NOT NULL,
    CONSTRAINT unit_leasing_log_pk PRIMARY KEY (id)
);

-- Foreign keys
-- Reference: application_renter (table: application)
ALTER TABLE application ADD CONSTRAINT application_renter FOREIGN KEY application_renter (renter_id)
    REFERENCES renter (id) ON DELETE CASCADE;

-- Reference: application_unit (table: application)
ALTER TABLE application ADD CONSTRAINT application_unit FOREIGN KEY application_unit (unit_id)
    REFERENCES unit (id) ON DELETE CASCADE;

-- Reference: community_address (table: community)
ALTER TABLE community ADD CONSTRAINT community_address FOREIGN KEY community_address (address_id)
    REFERENCES address (id) ON DELETE CASCADE;

-- Reference: rent_pym_log_unit_leasing_log (table: payment_log)
ALTER TABLE payment_log ADD CONSTRAINT rent_pym_log_unit_leasing_log FOREIGN KEY rent_pym_log_unit_leasing_log (unit_leasing_log_id)
    REFERENCES unit_leasing_log (id) ON DELETE CASCADE;

-- Reference: srv_req_unit_leasing_log (table: service_request)
ALTER TABLE service_request ADD CONSTRAINT srv_req_unit_leasing_log FOREIGN KEY srv_req_unit_leasing_log (unit_leasing_log_id)
    REFERENCES unit_leasing_log (id) ON DELETE CASCADE;

-- Reference: srv_request_srv_cat (table: service_request)
ALTER TABLE service_request ADD CONSTRAINT srv_request_srv_cat FOREIGN KEY srv_request_srv_cat (service_category_id)
    REFERENCES service_category (id) ON DELETE CASCADE;

-- Reference: unit_address (table: unit)
ALTER TABLE unit ADD CONSTRAINT unit_address FOREIGN KEY unit_address (address_id)
    REFERENCES address (id) ON DELETE CASCADE;

-- Reference: unit_leasing_info (table: unit)
ALTER TABLE unit ADD CONSTRAINT unit_leasing_info FOREIGN KEY unit_leasing_info (unit_lease_id)
    REFERENCES unit_lease (id) ON DELETE CASCADE;

-- Reference: unit_leasing_log_application (table: unit_leasing_log)
ALTER TABLE unit_leasing_log ADD CONSTRAINT unit_leasing_log_application FOREIGN KEY unit_leasing_log_application (app_id)
    REFERENCES application (id) ON DELETE CASCADE;

-- Reference: unit_leasing_log_unit (table: unit_leasing_log)
ALTER TABLE unit_leasing_log ADD CONSTRAINT unit_leasing_log_unit FOREIGN KEY unit_leasing_log_unit (unit_id)
    REFERENCES unit (id) ON DELETE CASCADE;

-- Reference: unit_parent_unit (table: unit)
ALTER TABLE unit ADD CONSTRAINT unit_parent_unit FOREIGN KEY unit_parent_unit (comm_id)
    REFERENCES community (id) ON DELETE CASCADE;