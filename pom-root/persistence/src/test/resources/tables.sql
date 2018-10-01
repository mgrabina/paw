create table if not exists users
(
	name varchar(50) not null,
	password varchar(200) not null,
	phone varchar(50) not null,
	mail varchar(200) not null,
	id identity not null
		constraint users_pkey
			primary key,
	imagesrc varchar(200)
);
create table if not exists property
(
	id identity not null
		constraint property_pkey
			primary key,
	street varchar(50) not null,
	floor integer,
	apartment varchar(10),
	type varchar(50) not null,
	user_id integer not null
		constraint user_id
			references users,
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
--
comment on table property is 'A property model, with all its data to value it.'
;

create unique index if not exists property_id_uindex on property (id);

create table if not exists property_images
(
	id identity not null
		constraint property_images_pkey
			primary key,
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

create table if not exists favourites
(
	id_user integer not null
		constraint wish_users_id_fk
			references users,
	id_property integer
		constraint wish_property_id_fk
			references property,
	constraint wish_id_user_id_property_pk
		unique (id_user, id_property)
)
;

create table if not exists tags
(
	name varchar(50) not null,
	id_property integer not null
		constraint tags_property___fk
			references property,
	type integer,
	constraint tags_name_id_property_type_pk
		unique (name, id_property, type)
)
;

