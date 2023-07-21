-- Utilisateur
create table profil(
    id serial primary key ,
    designation varchar(40)
);
insert into profil(id, designation) values (10, 'Emp'),(20, 'Admin'),(0, 'client');

create table utilisateur(
    id serial primary key ,
    id_profil int references profil(id),
    nom varchar(40),
    mot_de_passe varchar(40)
);
insert into utilisateur(id_profil, nom, mot_de_passe) values (0, 'jean', ' ');

insert into utilisateur(id_profil, nom, mot_de_passe) values (10, 'Emp', '456'),(20, 'Admin', '123');

create table avion(
    id serial primary key,
    nom varchar(40),
    place_economique int,
    place_affaire int,
    id_type int references type_avion(id)
);

create table type_avion(
    id serial primary key ,
    type varchar(40)
);

-- view join avion avion_type
create or replace view v_avion as
select avion.*, ta.type nom_type from avion join type_avion ta on ta.id = avion.id_type;


-- getsion vol
create table lieu(
    id serial primary key ,
    designation varchar(40)
);

insert into lieu(designation) values ('Antananarivo'), ('Paris'),
                                     ('Toronto'), ('Johannesburg');

create table vol(
    id serial primary key ,
    id_avion int references avion(id),
    id_lieu_depart int references lieu(id),
    id_lieu_arrive int references lieu(id),
    duree int,                  -- duree de preparation ou jspq
    aller_retour bool,           -- Aller simple ou aller retour
    prix_eco numeric,
    prix_affaire numeric,
    debut_vente timestamp,
    fin_vente timestamp
);

create table promotion(
    id serial primary key,
    pourcentageaffaire numeric,
    pourcentageeconomique numeric,
    remise_affaire numeric,
    remise_eco numeric,
    id_vol int references vol(id) unique
);

select * from reservation;
select * from v_vol_nonvalider;
select * from vol_valider;

create table vol_valider(
    id serial primary key ,
    id_vol int references  vol (id),
    date_validation date
);
select * from reservation;

create or replace view v_vol_valider as
select * from v_vol where id in (select id_vol from vol_valider);

create or replace view v_vol_nonvalider as
select * from v_vol where id not in (select id_vol from vol_valider);
select * from vol_valider;
select * from v_vol_nonvalider;

-- drop table vol cascade ;
select * from avion;

create or replace view v_vol as
select vol.*, a.nom nom_avion, place_economique, place_affaire, l2.designation lieu_depart, l.designation lieu_arrive from vol
    join avion a on a.id = vol.id_avion
    join lieu l on l.id = vol.id_lieu_arrive
    join lieu l2 on l2.id = vol.id_lieu_depart;

select * from reservation;

select * from v_vol_valider;


create or replace view v_vol_disponible as
select v_vol.id,
       id_avion, id_lieu_depart, id_lieu_arrive, duree, aller_retour,
       prix_eco, prix_affaire, debut_vente, fin_vente, nom_avion,
       v_vol.place_economique - COALESCE(sum(r.place_eco), 0) + coalesce(sum(r_a.place_eco), 0) place_economique,
       v_vol.place_affaire - COALESCE(sum(r.place_affaire), 0) + coalesce(sum(r_a.place_affaire), 0) place_affaire, lieu_depart, lieu_arrive, v_vol.date_vol date_vol
        from v_vol_valider v_vol
            left join reservation r on v_vol.id = r.id_vol
            left join v_reservation_annuler r_a on r.id = r_a.id
        group by id_avion, v_vol.id, v_vol.id, id_lieu_depart, id_lieu_arrive, duree, aller_retour,
                 prix_eco, prix_affaire, debut_vente, fin_vente, nom_avion, place_economique, v_vol.place_affaire, v_vol.date_vol,
                 lieu_depart, lieu_arrive
;

select * from v_vol_disponible;
select * from v_vol_valider;



select * from v_avion;

select * from vol;

select * from type_avion;

create table classe(
  id serial primary key,
  designation varchar(60)
);

insert into classe (designation) values ('Classe economique');
insert into classe (designation) values ('Classe affaire');

create table reservation
(
    id        serial primary key,
    token     varchar(255),
    nom       varchar(40),
    id_vol    int references vol (id),
    place_eco int,
    place_affaire int,
    prix numeric,       -- Prix à laquelle le vol doit être payé
    date      timestamp
);


drop table reservation cascade ;

select * from reservation;


drop table reservation_annuler cascade ;
create table reservation_annuler(
  id serial primary key,
  id_reservation int references reservation(id),
  date_annulation TIMESTAMP
);

create view v_reservation_actif as
    select * from reservation where id not in (select id_reservation from reservation_annuler);

create view v_reservation_annuler as
    select * from reservation where id in (select id_reservation from reservation_annuler);

select * from v_reservation_actif;


create table promotion(
    idPromotion serial primary key,
    id_vol

);

select * from vol;

-- alter table vol add column date_vol timestamp;

select * from v_vol join;
select * from avion;
create table promotion(
    id_promotion serial primary key
);

select * from profil;