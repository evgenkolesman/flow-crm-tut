SELECT 'CREATE DATABASE flowcrmtut'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'flowcrmtut');
CREATE SCHEMA IF NOT EXISTS flowcrmtut;

CREATE  TABLE IF NOT EXISTS flowcrmtut.contact
(
    id       uuid PRIMARY KEY default gen_random_uuid(),
    first_name  text,
    last_name  text,
    email  text,
    status_id  uuid,
    company_id uuid
);


CREATE TABLE IF NOT EXISTS flowcrmtut.company
(
    id       uuid PRIMARY KEY default gen_random_uuid(),
    name text
);

CREATE TABLE IF NOT EXISTS flowcrmtut.employee
(
    id       uuid PRIMARY KEY default gen_random_uuid(),
    employee_data  text,
    company_id uuid

);

CREATE TABLE IF NOT EXISTS flowcrmtut.status
(
    id       uuid PRIMARY KEY default gen_random_uuid(),
    name text
);

ALTER TABLE IF EXISTS flowcrmtut.contact ADD FOREIGN KEY(status_id)
REFERENCES flowcrmtut.status(id);

ALTER TABLE IF EXISTS flowcrmtut.contact ADD FOREIGN KEY(company_id)
REFERENCES flowcrmtut.company(id);

alter table employee
    add constraint employee_company_id_fk
        foreign key (company_id) references company;
