create table companies
(
    company_name text ,
        id         integer
        constraint companies_pk
            primary key
);
insert into companies(company_name, id) values('Google', 0);
insert into companies(company_name, id) values('Amazon',1);
insert into companies(company_name, id) values('LinkedIn',2);
insert into companies(company_name, id) values('Ubuntu',3);
insert into companies(company_name, id) values('Microsoft',4);

create table employs
(
    id         uuid
        primary key,
    first_name varchar(45),
    last_name  varchar(45),
    role       varchar(45),
    email      varchar(45),
    company_id integer
        constraint employs_companies_id_fk
            references companies
);
insert into employs (id, first_name, last_name, role, email, company_id) values ('563dbd52-0f82-4fd5-b74d-8ac2c13e881c', 'Craggie', 'Anglish', 'Supervisor', 'canglish0@chicagotribune.com', 1);
insert into employs (id, first_name, last_name, role, email, company_id) values ('57af8054-11ed-471b-b03e-b97d6ff32215', 'Ives', 'Caherny', 'Project Manager', 'icaherny1@about.me', 2);
insert into employs (id, first_name, last_name, role, email, company_id) values ('1befcd4d-c3b6-4615-b256-e81db9fb21a0', 'Seward', 'O''Scollee', 'Electrician', 'soscollee2@arstechnica.com', 3);
insert into employs (id, first_name, last_name, role, email, company_id) values ('b87023ff-9ecb-4e13-9d22-52dd492d486c', 'Renado', 'Dahlback', 'Supervisor', 'rdahlback3@google.co.jp', 4);
insert into employs (id, first_name, last_name, role, email, company_id) values ('04ea22f3-23f5-484d-99ba-40b2d0baf9c7', 'Huberto', 'Boother', 'Construction Expeditor', 'hboother4@sina.com.cn', 1);
insert into employs (id, first_name, last_name, role, email, company_id) values ('548980a1-47b8-45d8-8d96-8807f39cfcc2', 'Vivi', 'Jiru', 'Construction Manager', 'vjiru5@godaddy.com', 2);
insert into employs (id, first_name, last_name, role, email, company_id) values ('e4d43478-32f3-4065-b016-86adb4260e01', 'Eamon', 'Elcy', 'Electrician', 'eelcy6@theglobeandmail.com', 3);
insert into employs (id, first_name, last_name, role, email, company_id) values ('2b41d81f-f9cb-493e-a4a4-b34510032f6a', 'Bartlett', 'Wingfield', 'Construction Expeditor', 'bwingfield7@deliciousdays.com', 4);
insert into employs (id, first_name, last_name, role, email, company_id) values ('a083e2ac-23d1-4986-8a93-2db58a7410ab', 'Georgina', 'Alentyev', 'Subcontractor', 'galentyev8@smugmug.com', 1);
insert into employs (id, first_name, last_name, role, email, company_id) values ('205ae2d6-1b5d-4b45-8dc0-1065e09c33e1', 'Cybil', 'Harker', 'Architect', 'charker9@sun.com', 2);

create table projects
(
    name      text not null
        constraint projects_pk
            primary key,
    startdate date not null
);

insert into projects (name, startdate) values ('STARWOOD PROPERTY TRUST, INC.', '4/6/2021');
insert into projects (name, startdate) values ('Biostar Pharmaceuticals, Inc.', '11/17/2020');
insert into projects (name, startdate) values ('Liberty Interactive Corporation', '11/8/2019');
insert into projects (name, startdate) values ('Tredegar Corporation', '10/5/2021');
insert into projects (name, startdate) values ('VSE Corporation', '5/24/2022');
insert into projects (name, startdate) values ('PIMCO Municipal Income Fund', '3/31/2020');
insert into projects (name, startdate) values ('NOW Inc.', '9/4/2020');
insert into projects (name, startdate) values ('Attunity Ltd.', '11/24/2023');
insert into projects (name, startdate) values ('Dell Technologies Inc.', '7/2/2022');
insert into projects (name, startdate) values ('First Trust Developed Markets Ex-US AlphaDEX Fund', '3/10/2022');

create table employes_projects
(
    employee_uuid uuid not null
        constraint employs_id_fk
            references employs,
    projects_name text not null
        constraint projects_fk
            references projects,
    id            integer
        constraint employes_projects_pk
            primary key
);
insert into employes_projects (employee_uuid, projects_name, id) values ('563dbd52-0f82-4fd5-b74d-8ac2c13e881c','STARWOOD PROPERTY TRUST, INC.', '0');
insert into employes_projects (employee_uuid, projects_name, id) values ('205ae2d6-1b5d-4b45-8dc0-1065e09c33e1','Biostar Pharmaceuticals, Inc.', '1');
insert into employes_projects (employee_uuid, projects_name, id) values ('563dbd52-0f82-4fd5-b74d-8ac2c13e881c','Liberty Interactive Corporation', '2');
insert into employes_projects (employee_uuid, projects_name, id) values ('1befcd4d-c3b6-4615-b256-e81db9fb21a0','NOW Inc.', '3');
insert into employes_projects (employee_uuid, projects_name, id) values ('e4d43478-32f3-4065-b016-86adb4260e01','NOW Inc.', '4');

