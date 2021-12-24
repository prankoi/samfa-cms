CREATE DATABASE IF NOT EXISTS projectdb;
USE projectdb;

CREATE TABLE IF NOT EXISTS Profile (
	profile_id INTEGER PRIMARY KEY,
	username VARCHAR(255),
	password VARCHAR(255),
	profile_type VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Customer (
	customer_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	email_address VARCHAR(255),
	cellphone_number VARCHAR(255),
	password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Vehicle (
	vehicle_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	plate_number VARCHAR(255),
	brand VARCHAR(255),
	model VARCHAR(255),
	passenger_capacity INTEGER,
	load_capacity INTEGER,
	status VARCHAR(255),
	assignability VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Driver (
	driver_id INTEGER AUTO_INCREMENT,
	vehicle_id INTEGER,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	email_address VARCHAR(255),
	cellphone_number VARCHAR(255),
	password VARCHAR(255),
	PRIMARY KEY (driver_id),
	FOREIGN KEY (vehicle_id) REFERENCES Vehicle (vehicle_id)
);

CREATE TABLE IF NOT EXISTS Location (
	location_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	location_name VARCHAR(255),
  location_city VARCHAR(255),
  location_address VARCHAR(255),
  location_longitude INTEGER,
  location_latitude INTEGER
);

CREATE TABLE IF NOT EXISTS Promo (
	promo_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	promo_code VARCHAR(255),
	promo_type VARCHAR(255),
	discount INTEGER,
	start_date DATETIME,
	end_date DATETIME
);

CREATE TABLE IF NOT EXISTS Item (
	item_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	item_category VARCHAR(255),
	item_details VARCHAR(255),
	item_weight INTEGER
);

CREATE TABLE IF NOT EXISTS Payment (
	payment_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	payment_type VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Trip (
	trip_reference_number VARCHAR(255),
	customer_id INTEGER,
	driver_id INTEGER,
	vehicle_id INTEGER,
	confirmation_date DATETIME,
	booking_date DATETIME,
	fulfillment_date DATETIME,
	pickup_location VARCHAR(255),
	dropoff_location VARCHAR(255),
	landmarks VARCHAR(255),
	booking_fee INTEGER,
	service_fee INTEGER,
	amount_paid INTEGER,
	promo_id INTEGER,
	payment_id INTEGER,
	status VARCHAR(255),
	distance INTEGER,
	PRIMARY KEY (trip_reference_number),
	FOREIGN KEY (customer_id) REFERENCES Customer (customer_id),
	FOREIGN KEY (driver_id) REFERENCES Driver (driver_id),
	FOREIGN KEY (vehicle_id) REFERENCES Vehicle (vehicle_id),
	FOREIGN KEY (promo_id) REFERENCES Promo (promo_id),
	FOREIGN KEY (payment_id) REFERENCES Payment (payment_id)
);

CREATE TABLE IF NOT EXISTS Delivery (
	delivery_reference_number VARCHAR(255),
	customer_id INTEGER,
	driver_id INTEGER,
	vehicle_id INTEGER,
	confirmation_date DATETIME,
	booking_date DATETIME,
	fulfillment_date DATETIME,
	pickup_location VARCHAR(255),
	dropoff_location VARCHAR(255),
	recipient_name VARCHAR(255),
	recipient_contact VARCHAR(255),
	landmarks VARCHAR(255),
	item_id INTEGER,
	booking_fee INTEGER,
	service_fee INTEGER,
	amount_paid INTEGER,
	promo_id INTEGER,
	payment_id INTEGER,
	status VARCHAR(255),
	distance INTEGER,
	PRIMARY KEY (delivery_reference_number),
	FOREIGN KEY (customer_id) REFERENCES Customer (customer_id),
	FOREIGN KEY (driver_id) REFERENCES Driver (driver_id),
	FOREIGN KEY (vehicle_id) REFERENCES Vehicle (vehicle_id),
	FOREIGN KEY (item_id) REFERENCES Item (item_id),
	FOREIGN KEY (promo_id) REFERENCES Promo (promo_id),
	FOREIGN KEY (payment_id) REFERENCES Payment (payment_id)
);
