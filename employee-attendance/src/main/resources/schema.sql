CREATE TABLE IF NOT EXISTS employee (
                                        id integer NOT NULL,
                                        gender varchar(50),
                                        name varchar(50),
                                        department varchar(50),
                                        PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS employee_sequence (
    next_val bigint
);
INSERT INTO employee_sequence VALUES ( 1000 );
CREATE TABLE IF NOT EXISTS swipe_sequence (
    next_val bigint
);
INSERT INTO swipe_sequence VALUES ( 5000 );
CREATE TABLE IF NOT EXISTS swipe(
                                    id integer not null,
                                    date_time datetime(6),
                                    date date,
                                    swipe_type varchar(50),
                                    emp_id integer,
                                    primary key (id),
                                    foreign key (emp_id) references employee (id)
);