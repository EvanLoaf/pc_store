create table if not exists t_role (
  f_id   bigint(19) unsigned auto_increment not null,
  f_name varchar(15)                        not null,
  primary key (f_id)
);

create table if not exists t_permission (
  f_id   bigint(19) unsigned auto_increment not null,
  f_name varchar(30)                        not null,
  primary key (f_id)
);

create table if not exists t_role_permission (
  f_role_id       bigint(19) unsigned not null,
  f_permission_id bigint(19) unsigned not null,
  primary key (f_role_id, f_permission_id),
  foreign key (f_role_id) references t_role (f_id)
    on update cascade
    on delete restrict,
  foreign key (f_permission_id) references t_permission (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_user (
  f_id         bigint(19) unsigned auto_increment not null,
  f_email      varchar(30)                        not null,
  f_first_name varchar(20)                        not null,
  f_last_name  varchar(20)                        not null,
  f_password   varchar(20)                        not null,
  f_role_id    bigint(19) unsigned                not null,
  primary key (f_id),
  unique (f_email),
  foreign key (f_role_id) references t_role (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_profile (
  f_user_id      bigint(19) unsigned auto_increment not null,
  f_address      varchar(90)                        not null,
  f_phone_number varchar(20)                        not null,
  primary key (f_user_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_item (
  f_id          bigint(19) unsigned auto_increment not null,
  f_name        varchar(20)                        not null,
  f_vendor_code bigint(11) unsigned unique         not null,
  f_description varchar(100)                       not null,
  f_price       decimal(10, 5) unsigned            not null,
  primary key (f_id),
  index (f_vendor_code)
);

create table if not exists t_order (
  f_user_id  bigint(19) unsigned                not null,
  f_item_id  bigint(19) unsigned                not null,
  f_uuid     varchar(36)                        not null,
  f_created  datetime default now()             not null,
  f_quantity int unsigned                       not null,
  f_status   varchar(20)                        not null,
  primary key (f_user_id, f_item_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict,
  foreign key (f_item_id) references t_item (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_feedback (
  f_id      bigint(19) unsigned auto_increment not null,
  f_user_id bigint(19) unsigned                not null,
  f_message varchar(200)                       not null,
  primary key (f_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_audit (
  f_id         bigint(19) unsigned auto_increment not null,
  f_user_id    bigint(19) unsigned                not null,
  f_event_type varchar(30)                        not null,
  f_created    datetime default now()             not null,
  primary key (f_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_news (
  f_id      bigint(19) unsigned auto_increment not null,
  f_title   varchar(40)                        not null,
  f_content varchar(500)                       not null,
  f_created datetime default now()             not null,
  f_user_id bigint(19) unsigned                not null,
  primary key (f_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_comment (
  f_id      bigint(19) unsigned auto_increment not null,
  f_content varchar(180)                       not null,
  f_created datetime default now()             not null,
  f_user_id bigint(19) unsigned                not null,
  f_news_id bigint(19) unsigned                not null,
  primary key (f_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict,
  foreign key (f_news_id) references t_news (f_id)
    on update cascade
    on delete restrict
);

insert into t_role (f_id, f_name)
values (1, 'admin'),
       (2, 'user')
on duplicate key update f_id = f_id;

insert into t_permission (f_id, f_name)
values (1, 'admin_permission_set'),
       (2, 'user_permission_set')
on duplicate key update f_id = f_id;

insert into t_role_permission (f_role_id, f_permission_id)
values (1, 1),
       (2, 2)
on duplicate key update f_role_id = f_role_id;

insert into t_user (f_id, f_email, f_password, f_first_name, f_last_name, f_role_id)
values (1, 'root@admin', 'root', 'root', 'admin', (select f_id from t_role where f_name = 'admin')),
       (2, 'root@user', 'root', 'root', 'user', (select f_id from t_role where f_name = 'user'))
on duplicate key update f_id = f_id;

insert into t_profile (f_user_id, f_address, f_phone_number)
VALUES (1, 'admin address', 'admin pn'),
       (2, 'user address', 'user pn')
on duplicate key update f_user_id = f_user_id;