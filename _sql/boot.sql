create table if not exists role (
  id   bigint(19) unsigned auto_increment not null,
  name varchar(5)                         not null,
  primary key (id)
);

create table if not exists permission (
  id   bigint(19) unsigned auto_increment not null,
  name varchar(5)                         not null,
  primary key (id)
);

create table if not exists role_permission (
  role_id       bigint(19) unsigned not null,
  permission_id bigint(19) unsigned not null,
  primary key (role_id, permission_id)
);

create table if not exists user (
  id           bigint(19) unsigned auto_increment not null,
  email        varchar(30)                        not null,
  password     varchar(20)                        not null,
  first_name   varchar(20)                        not null,
  last_name    varchar(20)                        not null,
  phone_number varchar(20)                        not null,
  add_info     varchar(100) default null,
  role_id      bigint(19) unsigned                not null,
  primary key (id),
  unique (email),
  foreign key (role_id) references role (id)
    on update cascade
    on delete restrict
);

create table if not exists item (
  id          bigint(19) unsigned auto_increment unique not null,
  name        varchar(20)                        not null,
  vendor_code bigint(11)                         not null,
  description varchar(100)                       not null,
  price       decimal(20, 15) unsigned           not null,
  primary key (vendor_code),
  index (id)
);

create table if not exists `order` (
  id       bigint(19) unsigned auto_increment not null,
  uuid     varchar(36)                        not null,
  user_id  bigint(19) unsigned                not null,
  item_id  int unsigned                       not null,
  created  datetime default now()             not null,
  quantity int unsigned                       not null,
  primary key (id),
  foreign key (user_id) references user (id)
    on update cascade
    on delete restrict,
  foreign key (item_id) references item (id)
    on update cascade
    on delete restrict
);

create table if not exists feedback (
  id      int unsigned auto_increment not null,
  user_id bigint(19) unsigned         not null,
  message varchar(200)                not null,
  primary key (id),
  foreign key (user_id) references user (id)
    on update cascade
    on delete restrict
);

create table if not exists audit (
  id         bigint(19) unsigned auto_increment not null,
  user_id    bigint(19) unsigned                not null,
  event_type varchar(30)                        not null,
  created    datetime default now()             not null,
  primary key (id),
  foreign key (user_id) references user (id)
    on update cascade
    on delete restrict
);

create table if not exists news (
  id      bigint(19) unsigned auto_increment not null,
  title   varchar(40)                        not null,
  content varchar(180)                       not null,
  created datetime default now()             not null,
  user_id bigint(19) unsigned                not null,
  primary key (id),
  foreign key (user_id) references user (id)
    on update cascade
    on delete restrict
);

create table if not exists comment (
  id      bigint(19) unsigned auto_increment not null,
  content varchar(180)                       not null,
  created datetime default now()             not null,
  user_id bigint(19) unsigned                not null,
  primary key (id),
  foreign key (user_id) references user (id)
    on update cascade
    on delete restrict
);

create table if not exists profile (
  user_id   bigint(19) unsigned auto_increment not null,
  address   varchar(90)                        not null,
  telephone varchar(25)                        not null,
  primary key (user_id),
  foreign key (user_id) references user (id)
    on update cascade
    on delete restrict
);

insert into role (id, name)
values (1, 'admin'),
       (2, 'user')
on duplicate key update id = id;

insert into user (id, email, password, first_name, last_name, phone_number, add_info, role_id)
values (1, 'root@admin', 'root', 'root', 'admin', '1-800-admin', 'unknown', (select id from role where name = 'admin')),
       (2, 'root@user', 'root', 'root', 'user', '1-800-user', 'unknown', (select id from role where name = 'user'))
on duplicate key update id = id;