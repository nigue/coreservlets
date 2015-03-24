create table customer (
  id varchar(36) not null,
  name varchar(32) not null,
  primary key (id),
  unique (name)
);

delete from customer;

insert into customer (id, name) values ('jjoe','Java Joe');
insert into customer (id, name) values ('jjohn','Java John');

commit;

