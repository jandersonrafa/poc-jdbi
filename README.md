CREATE TABLE jdbi.orders (
	id varchar(36) NOT NULL,
	amount numeric NOT NULL,
	one varchar(36)  NULL,
	two varchar(36)  NULL,
	tree varchar(36)  NULL,
	four varchar(36)  NULL,
	five varchar(36)  NULL,
	six varchar(36)  NULL,
	seven varchar(36)  NULL,
	eight varchar(36)  NULL,
	nine varchar(36)  NULL,
	ten varchar(36)  NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);



CREATE TABLE jdbi.items (
	id varchar(36) NOT NULL,
	orderid varchar(36) NOT NULL,
	one varchar(36)  NULL,
	two varchar(36)  NULL,
	tree varchar(36)  NULL,
	four varchar(36)  NULL,
	five varchar(36)  NULL,
	six varchar(36)  NULL,
	seven varchar(36)  NULL,
	eight varchar(36)  NULL,
	nine varchar(36)  NULL,
	ten varchar(36)  NULL,
	CONSTRAINT items_pkey PRIMARY KEY (id),
	constraint items_fkey foreign key (orderid) references orders (id)
);

GRANT ALL ON jdbi.items TO jdbi
