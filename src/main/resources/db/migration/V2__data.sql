INSERT INTO person (role, id, date_of_birth, name, sex, surname)
VALUES
('DEVELOPER',	1, '1991-11-11', 'Pontiy', 'MALE',	'Pilat'),
('DEVELOPER',	2, '1992-12-12', 'Tom', 'MALE',	'Soyer'),
('DEVELOPER',	3, '1993-10-20', 'Tim',	'MALE', 'Pik'),
('DEVELOPER',	4, '1994-10-19', 'Max',	'MALE',	'Korzh'),
('BA',	5, '1995-10-18', 'Simon', 'MALE', 'Mone');


INSERT INTO project (id, description, duration_in_month, name)
VALUES
(1,	'BigData',	12,	'Amazon'),
(2,	'GCP',	24,	'Google'),
(3,	'Web',	10,	'Spring'),
(4,	'Stream', 11, 'DHL'),
(5,	'Mix', 15, 'Alibaba');

INSERT INTO skill (id, name)
VALUES
(1,	'Java'),
(2,	'SQL'),
(3,	'MongoDB'),
(4,	'Spring'),
(5,	'Python');

INSERT INTO person_skill (level, person_id, skill_id)
VALUES
(3,	1,	1),
(3,	1,	2),
(1,	2,	1),
(1,	2,	2),
(4,	3,	3),
(4,	3,	4),
(0,	4,	4),
(2,	4,	5),
(1,	5,	1),
(1,	5,	2);

INSERT INTO public.project_skill (level, project_id, skill_id)
VALUES
(3,	1,	1),
(3,	1,	2),
(3,	1,	4),
(4,	2,	3),
(0,	2,	5),
(2,	3,	1),
(1,	3,	4),
(2,	3,	5),
(3,	4,	5);
