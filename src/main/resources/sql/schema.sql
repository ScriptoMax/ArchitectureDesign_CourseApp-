create database archbase

CREATE TABLE users (	
	id BIGINT NOT NULL PRIMARY KEY,
	user_role VARCHAR(15),
	corporate_type BOOLEAN,
	status VARCHAR(10),
	login VARCHAR(15) NOT NULL,
	password VARCHAR(20) NOT NULL,
	first_name VARCHAR(30) NOT NULL,
	second_name VARCHAR(30) DEFAULT NULL,	
	last_name VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL,	
	city VARCHAR(30) DEFAULT NULL,	
	affiliation_info VARCHAR(300) DEFAULT NULL,	
	account_number VARCHAR(15) DEFAULT NULL
);

CREATE TABLE administrators (
	user_id BIGINT REFERENCES users (id)	
);

CREATE TABLE visitors (
	user_id BIGINT REFERENCES users (id),
	background VARCHAR(200) DEFAULT NULL,
	portfolio VARCHAR(500) DEFAULT NULL	
);

CREATE TABLE organisers (
	user_id BIGINT REFERENCES users (id),
	affiliation VARCHAR(50) DEFAULT NULL,
	open_contests SMALLINT DEFAULT NULL,
	total_contests SMALLINT DEFAULT NULL,
	site VARCHAR(30) DEFAULT NULL,	
);

CREATE TABLE trainers (
	user_id BIGINT REFERENCES users (id),
	total_trainings SMALLINT DEFAULT NULL,
	experience FLOAT DEFAULT NULL,
	affiliation VARCHAR(50) DEFAULT NULL,
	average_rate FLOAT DEFAULT NULL
);

CREATE TABLE contests (
	id BIGINT NOT NULL PRIMARY KEY,
	title VARCHAR(50) DEFAULT NULL,
	specialization  VARCHAR(30),
	annotation VARCHAR(1000) DEFAULT NULL,
	quota INT NOT NULL,
	current_submissions INT DEFAULT NULL,
	registration_open BOOLEAN,
	organiser_id BIGINT REFERENCES organisers (user_id),
	period VARCHAR(30) NOT NULL,
	place VARCHAR(40) NOT NULL,
	level SMALLINT NOT NULL,
	requirements VARCHAR(800) DEFAULT NULL
);

CREATE TABLE trainings(
	id BIGINT NOT NULL PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	specialization VARCHAR(30),
	trainer_id BIGINT REFERENCES trainers (user_id),
	language VARCHAR(20) NOT NULL,
	annotation VARCHAR(1000) DEFAULT NULL,
	average_rate FLOAT DEFAULT NULL,
	rates_provided INT DEFAULT NULL,
	students INT DEFAULT NULL,
	duration TIME NOT NULL,
	prerequisites VARCHAR(800) DEFAULT NULL,
	cost_free BOOLEAN,	
	level INT NOT NULL,
	certified_credit BOOLEAN
);

CREATE TABLE reviews (
	id BIGINT NOT NULL PRIMARY KEY,
	visitor_id BIGINT NOT NULL REFERENCES visitors (user_id),
	training_id BIGINT NOT NULL REFERENCES trainings (id),
	rate INT DEFAULT NULL,
	comment VARCHAR(3000) DEFAULT NULL,
	postedOn TIMESTAMP 
);

CREATE TABLE transactions (
	id BIGINT NOT NULL PRIMARY KEY,
	order_id BIGINT NOT NULL REFERENCES orders(id), 
	status VARCHAR(30),  
	payer_account VARCHAR(15) NOT NULL,
	recip_account VARCHAR(15) NOT NULL,
	cost INT NOT NULL,
	bank_id INT NOT NULL,
	resolvedOn TIMESTAMP,
	CONSTRAINT order_transaction UNIQUE (order_id)  
);

CREATE TABLE requests (
	id BIGINT NOT NULL PRIMARY KEY,
	user_id BIGINT REFERENCES users (id),
	admin_id BIGINT REFERENCES administrators (user_id),
	status VARCHAR(30),  
	category VARCHAR(30),
	body VARCHAR(1000) NOT NULL
);

CREATE TABLE submissions (
	id BIGINT NOT NULL PRIMARY KEY,
	status VARCHAR(15),  
	visitor_id BIGINT REFERENCES visitors (user_id),
	contest_id BIGINT REFERENCES contests (id),
	registeredOn TIMESTAMP
);

CREATE TABLE orders (
	id BIGINT NOT NULL PRIMARY KEY,
	visitor_id BIGINT REFERENCES users (id),
	status VARCHAR(15),  
	items SMALLINT NOT NULL,
	cost FLOAT NOT NULL,
	confirmedOn TIMESTAMP
);

CREATE TABLE order_items (
	id BIGINT NOT NULL PRIMARY KEY,
	status VARCHAR(15),  
	order_id BIGINT REFERENCES orders (id),
	training_id BIGINT NOT NULL REFERENCES trainings (id),
	price FLOAT NOT NULL	
);





