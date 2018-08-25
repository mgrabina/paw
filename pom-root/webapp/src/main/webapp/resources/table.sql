create table "user"
(
	name varchar(50),
	surname varchar(50),
	password varchar(200) not null,
	phone bigint,
	mail varchar(50) not null,
	image_src varchar(200),
	id serial not null
		constraint user_id_pk
			primary key
)
;

comment on table "user" is 'The users table, with mail as unique and primary key value.'
;

create unique index user_mail_uindex
	on "user" (mail)
;

create unique index user_id_uindex
	on "user" (id)
;

create table property
(
	id serial not null
		constraint property_pkey
			primary key,
	street varchar(50) not null,
	number varchar(50) not null,
	floor integer,
	apartment varchar(10),
	type varchar(50) not null,
	user_id integer not null
		constraint user_id
			references "user",
	price bigint,
	covered_area integer not null,
	total_area integer not null,
	rooms integer not null,
	baths integer not null,
	garage boolean not null
)
;

comment on table property is 'A property model, with all its data to value it.'
;

create unique index property_id_uindex
	on property (id)
;

create table property_images
(
	id serial not null
		constraint property_images_pkey
			primary key,
	description varchar(100),
	priority integer not null,
	image_src varchar(200) not null,
	property_id integer
		constraint property_id
			references property
)
;

comment on table property_images is 'The images from properties.'
;

create unique index property_images_id_uindex
	on property_images (id)
;

create table wish
(
	id_user integer not null
		constraint wish_user_id_fk
			references "user",
	id_property integer
		constraint wish_property_id_fk
			references property,
	constraint wish_id_user_id_property_pk
		unique (id_user, id_property)
)
;

create table operation
(
	buyer integer not null,
	seller integer not null,
	id_property integer not null,
	date timestamp,
	value bigint,
	is_rental boolean not null,
	id serial not null
		constraint operation_pkey
			primary key
)
;

create unique index operation_id_uindex
	on operation (id)
;

