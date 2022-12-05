CREATE TABLE if not exists contact_type(
  	id UUID PRIMARY KEY,
    type varchar(40) NOT NULL,
    description varchar(100)
  );

  CREATE TABLE if not exists users(
  	id UUID PRIMARY KEY,
  	first_name varchar (40),
  	last_name varchar(40),
  	email varchar(40) unique,
  	password varchar(100) NOT NULL,
  	role varchar(40) NOT NULL
  );

  CREATE TABLE if not exists contacts(
  	id UUID PRIMARY KEY,
  	first_name varchar(40),
  	last_name varchar(40),
  	address varchar(50),
  	phone_number varchar(50),
  	email varchar(40) unique,
  	user_id UUID references users(id),
      contact_type_id UUID REFERENCES contact_type(id)
  );