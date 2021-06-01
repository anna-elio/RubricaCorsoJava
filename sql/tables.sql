create database rubrica;

use rubrica;

CREATE TABLE `contacts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `surname` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE emails (
	id bigint unsigned NOT NULL auto_increment,
	email varchar(30) DEFAULT NULL,
	id_contact bigint unsigned,
	PRIMARY KEY (id)
);

CREATE TABLE phone (
	id bigint unsigned NOT NULL auto_increment,
	phone varchar(30) DEFAULT NULL,
	id_contact bigint unsigned,
	PRIMARY KEY (id)
);

ALTER TABLE contacts ADD id_email BIGINT UNSIGNED;
ALTER TABLE contacts ADD id_phone BIGINT UNSIGNED;

alter table contacts add constraint FK_email foreign key (id_email) references emails(id);
alter table contacts add constraint FK_phone foreign key (id_phone) references phone(id);

alter table emails add constraint FK_contact_email foreign key (id_contact) references contacts(id);
alter table phone add constraint FK_contact_phone foreign key (id_contact) references contacts(id);


insert into contacts (name, surname) values
	('Anna', 'Eliotropio'),
	('Alessia', 'Quintavalle'),
	('Lorenzo', 'Pantaleo');
	
insert into emails (email, id_contact) values
	('a.eliotropio@gmail.com', 1),
	('pantaleo@gmail.com', 3),
	('alessia.quintavalle@gmail.com', 2);

SELECT contacts.id, name, surname, email 
	FROM contacts JOIN emails ON contacts.id=emails.id_contact;
	
SELECT email FROM emails
	WHERE id_contact = 1;