create table if not exists operation
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

create unique index if not exists operation_id_uindex
	on operation (id)
;

create table if not exists users
(
	name varchar(50) not null,
	password varchar(200) not null,
	phone varchar(50) not null,
	mail varchar(200) not null,
	id serial not null
		constraint users_pkey
		primary key,
	imagesrc varchar(200) default NULL::character varying
)
;

create table if not exists property
(
	id serial not null
		constraint property_pkey
		primary key,
	street varchar(50) not null,
	floor integer,
	apartment varchar(10),
	type varchar(50) not null,
	user_id integer not null
		constraint user_id
		references users
		on delete cascade,
	price bigint,
	covered_area integer not null,
	total_area integer not null,
	rooms integer not null,
	baths integer not null,
	garage boolean not null,
	tax_price integer,
	neighborhood varchar(50),
	operation_type varchar(20),
	ad_message varchar(300),
	ad_description varchar(500),
	ad_date timestamp,
	inmediate_delivery boolean default true
)
;

comment on table property is 'A property model, with all its data to value it.'
;

create unique index if not exists property_id_uindex
	on property (id)
;

create table if not exists property_images
(
	id serial not null
		constraint property_images_pkey
		primary key,
	image_src varchar(200) not null,
	property_id integer
		constraint property_id
		references property
		on delete cascade
)
;

comment on table property_images is 'The images from properties.'
;

create unique index if not exists property_images_id_uindex
	on property_images (id)
;

create table if not exists favourites
(
	id_user integer not null
		constraint wish_users_id_fk
		references users
		on delete cascade,
	id_property integer
		constraint wish_property_id_fk
		references property
		on delete cascade,
	constraint wish_id_user_id_property_pk
	unique (id_user, id_property)
)
;

create unique index if not exists users_mail_uindex
	on users (mail)
;

create unique index if not exists users_id_uindex
	on users (id)
;

create table if not exists tags
(
	name varchar(50) not null,
	id_property integer not null
		constraint tags_property___fk
		references property
		on delete cascade,
	type integer,
	constraint tags_name_id_property_type_pk
	unique (name, id_property, type)
)
;

