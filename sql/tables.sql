create database rubrica;

use rubrica;

CREATE TABLE contacts (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  name varchar(25) DEFAULT NULL,
  surname varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE emails (
	email varchar(30) NOT NULL,
	id_contact bigint unsigned NOT NULL,
	PRIMARY KEY (email)
);

CREATE TABLE phones (
	phone varchar(30) NOT NULL,
	id_contact bigint unsigned NOT NULL,
	PRIMARY KEY (phone)
);

alter table emails 
	add constraint FK_contact_email foreign key (id_contact) references contacts(id) ON DELETE CASCADE;
alter table phones 
	add constraint FK_contact_phone foreign key (id_contact) references contacts(id)  ON DELETE CASCADE;


insert into contacts (name, surname) values
	('Anna', 'Eliotropio'),
	('Alessia', 'Quintavalle'),
	('Lorenzo', 'Pantaleo');
	
insert into emails (email, id_contact) values
	('a.eliotropio@gmail.com', 1),
	('pantaleo@gmail.com', 3),
	('alessia.quintavalle@gmail.com', 2);

insert into emails (email, id_contact) values
	('anna.eliotropio@gmail.com', 1),
	('pantalor5@gmail.com', 3),
	('egghybho@gmail.com', 2);
	
SELECT c.id, c.name, c.surname, e.email, p.phone FROM contacts as c LEFT OUTER JOIN emails as e ON c.id=e.id_contact  LEFT OUTER JOIN phones as p on p.id_contact=c.id