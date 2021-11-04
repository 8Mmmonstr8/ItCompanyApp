CREATE SEQUENCE IF NOT EXISTS person_id_seq
    AS BIGINT
    INCREMENT 1
    MINVALUE 6
    MAXVALUE 9223372036854775807
    START 6
    CACHE 1;

CREATE TABLE person (
    role character varying(20) NOT NULL,
    id bigint NOT NULL DEFAULT nextval('person_id_seq'),
    date_of_birth date,
    name character varying(30),
    sex character varying(6),
    surname character varying(30),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

ALTER SEQUENCE person_id_seq OWNED BY person.id;

CREATE SEQUENCE IF NOT EXISTS project_id_seq
    AS BIGINT
    INCREMENT 1
    MINVALUE 6
    MAXVALUE 9223372036854775807
    START 6
    CACHE 1;

CREATE TABLE project (
    id bigint NOT NULL DEFAULT nextval('project_id_seq'),
    description character varying(255),
    duration_in_month integer NOT NULL,
    name character varying(20),
    CONSTRAINT project_pkey PRIMARY KEY (id)
);

ALTER SEQUENCE person_id_seq OWNED BY project.id;

CREATE SEQUENCE IF NOT EXISTS skill_id_seq
    AS BIGINT
    INCREMENT 1
    MINVALUE 6
    MAXVALUE 9223372036854775807
    START 6
    CACHE 1;

CREATE TABLE skill (
    id bigint NOT NULL DEFAULT nextval('skill_id_seq'),
    name character varying(255) UNIQUE NOT NULL,
    CONSTRAINT skill_pkey PRIMARY KEY (id)
);

ALTER SEQUENCE skill_id_seq OWNED BY skill.id;

CREATE TABLE person_skill (
    level integer,
    person_id bigint NOT NULL,
    skill_id bigint NOT NULL,
    PRIMARY KEY (person_id, skill_id),
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES person(id),
    CONSTRAINT fk_person_skill FOREIGN KEY (skill_id) REFERENCES skill(id)
);

CREATE TABLE project_skill (
    level integer,
    project_id bigint NOT NULL,
    skill_id bigint NOT NULL,
    PRIMARY KEY (project_id, skill_id),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES project(id),
    CONSTRAINT fk_project_skill FOREIGN KEY (skill_id) REFERENCES skill(id)
);