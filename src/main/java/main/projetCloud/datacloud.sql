drop database signalement;

drop table imageSignalement;
drop table signalement;
drop table typeSignalement;
drop table responsable;
drop table region;
drop table utilisateur;
drop table affectation;
drop table admin;

create database signalement;
	use signalement;

create table admin(
	id serial primary key not null,
	email varchar(250),
	mdp varchar(250)
);

create table affectation(
	id serial primary key not null,
	idSignalement varchar(100),
	idAdmin varchar(100),
	idRegion varchar(100), 
	dateDeValidation date
);

create table utilisateur(
	id serial primary key not null,
	nom varchar(150),
	prenom varchar(150),
	sexe varchar(150),
	dtn Date,
	adresse varchar(150),
	email varchar(150),
	password varchar(150)
);

create table region(
	id serial primary key not null,
	nom varchar(150)
);

create table responsable(
	id serial primary key not null,
	idRegion integer ,
	idUtilisateur integer
);

create table typeSignalement(
	id serial primary key not null,
	description varchar(150),
	couleur varchar(20)
);

create table signalement(
	id serial primary key not null,
	idRegion integer,
	idTypeSignalement integer,
	idUtilisateur integer,
	longitude decimal,
	latitude decimal,
	commentaire text,
	daty timestamp,
	statut varchar(50),
	isaffecte varchar(100)
);

create table imageSignalement(
	id serial primary key not null,
	idSignalement integer,
	nomPhoto varchar(150)
);

alter table responsable add foreign key (idRegion) references region(id);
alter table responsable add foreign key (idUtilisateur) references utilisateur(id);
alter table signalement add foreign key (idRegion) references region(id);
alter table signalement add foreign key (idTypeSignalement) references typeSignalement(id);
alter table signalement add foreign key (idUtilisateur) references utilisateur(id);
alter table imageSignalement add foreign key (idSignalement) references signalement(id);

-- create view statNombreParRegion as (
-- select region.nom,sum(1) as nombreSignalement from signalement join region on signalement.idregion = region.id group by signalement.idregion);

-- create view statNombreParType as(
-- 	 select typeSignalement.description,typeSignalement.couleur,sum(1) as nombreSignalement from signalement 
-- 	 join region on signalement.region = region.id 
-- 	 join typeSignalement on typeSignalement.id = signalement.type 
-- 	 group by signalement.type order by nombreSignalement desc
-- );


insert into region values(1,'Diana');
insert into region values(2,'Sava');
insert into region values(3,'Itasy');
insert into region values(4,'Analamanga');
insert into region values(5,'Vakinankaratra');
insert into region values(6,'Bongolava');
insert into region values(7,'Sofia');
insert into region values(8,'Boeny');
insert into region values(9,'Betsiboka');
insert into region values(10,'Melaky');
insert into region values(11,'Alaotra-Mangoro');
insert into region values(12,'Atsinana');
insert into region values(13,'Analanjirofo');
insert into region values(14,'AmoroniMania');
insert into region values(15,'HauteMatsiatra');
insert into region values(16,'Vatovavy');
insert into region values(17,'Fitovinany');
insert into region values(18,'AtsimoAtsinana');
insert into region values(19,'Ihorombe');
insert into region values(20,'Menabe');
insert into region values(21,'AtsimoAndrefana');
insert into region values(22,'Androy');
insert into region values(23,'Anosy');

	insert into typeSignalement values(1,'jirama','#eb4034');
	insert into typeSignalement values(2,'tondradrano','#ed883b');
	insert into typeSignalement values(3,'cyclone','#e8c63c');
	insert into typeSignalement values(4,'desastre','#b3e63c');
	insert into typeSignalement values(5,'alerte','#3ade45');
	insert into typeSignalement values(6,'fanafiana','#37de9e');
	insert into typeSignalement values(7,'kere','#37acde');
	insert into typeSignalement values(8,'traboina','#354edb');
	insert into typeSignalement values(9,'fako','#8735de');
	insert into typeSignalement values(10,'accident','#e339b3');

