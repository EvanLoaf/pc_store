drop database computer_store;
create database computer_store;
use computer_store;

create table if not exists t_role (
  f_id   bigint(19) unsigned auto_increment not null,
  f_name varchar(20)                        not null,
  primary key (f_id)
);

create table if not exists t_permission (
  f_id   bigint(19) unsigned auto_increment not null,
  f_name varchar(40)                        not null,
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

create table if not exists t_discount (
  f_id          bigint(19) unsigned auto_increment not null,
  f_name        varchar(30)                        not null,
  f_percent     int(3) unsigned                    not null,
  f_finish_date datetime                           not null,
  primary key (f_id)
);

create table if not exists t_user (
  f_id          bigint(19) unsigned auto_increment             not null,
  f_email       varchar(30)                                    not null,
  f_first_name  varchar(20)                                    not null,
  f_last_name   varchar(20)                                    not null,
  f_password    varchar(70)                                    not null,
  f_is_disabled boolean default false                          not null,
  f_role_id     bigint(19) unsigned                            not null,
  f_discount_id bigint(19) unsigned,
  primary key (f_id),
  unique (f_email),
  foreign key (f_role_id) references t_role (f_id)
    on update cascade
    on delete restrict,
  foreign key (f_discount_id) references t_discount (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_profile (
  f_user_id      bigint(19) unsigned auto_increment not null,
  f_address      varchar(90) default '',
  f_phone_number varchar(20) default '',
  primary key (f_user_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_item (
  f_id          bigint(19) unsigned auto_increment           not null,
  f_name        varchar(20)                                  not null,
  f_vendor_code varchar(20) unique                           not null,
  f_description varchar(100)                                 not null,
  f_price       decimal(10, 5) unsigned                      not null,
  f_is_deleted  boolean default false                        not null,
  primary key (f_id),
  index (f_vendor_code)
);

create table if not exists t_order (
  f_user_id     bigint(19) unsigned                not null,
  f_item_id     bigint(19) unsigned                not null,
  f_uuid        varchar(36)                        not null,
  f_created     datetime default now()             not null,
  f_total_price decimal(10, 5) unsigned            not null,
  f_quantity    int unsigned                       not null,
  f_status      varchar(20)                        not null,
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
  f_id         bigint(19) unsigned auto_increment            not null,
  f_title      varchar(40)                                   not null,
  f_content    varchar(500)                                  not null,
  f_created    datetime default now()                        not null,
  f_is_deleted boolean default false                         not null,
  f_user_id    bigint(19) unsigned                           not null,
  primary key (f_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_comment (
  f_id         bigint(19) unsigned auto_increment             not null,
  f_content    varchar(180)                                   not null,
  f_created    datetime default now()                         not null,
  f_is_deleted boolean default false                          not null,
  f_user_id    bigint(19) unsigned                            not null,
  f_news_id    bigint(19) unsigned                            not null,
  primary key (f_id),
  foreign key (f_user_id) references t_user (f_id)
    on update cascade
    on delete restrict,
  foreign key (f_news_id) references t_news (f_id)
    on update cascade
    on delete restrict
);

create table if not exists t_item_discount (
  f_item_id     bigint(19) unsigned not null,
  f_discount_id bigint(19) unsigned not null,
  primary key (f_item_id, f_discount_id),
  foreign key (f_item_id) references t_item (f_id)
    on update cascade
    on delete restrict,
  foreign key (f_discount_id) references t_discount (f_id)
    on update cascade
    on delete restrict
);

insert into t_role (f_id, f_name)
values (1, 'security_admin'),
       (2, 'sales_admin'),
       (3, 'api_admin'),
       (4, 'user')
on duplicate key update f_id = f_id;

insert into t_permission (f_id, f_name)
values (1, 'SECURITY_ADMIN_BASIC_PERMISSION'),
       (2, 'SALES_ADMIN_BASIC_PERMISSION'),
       (3, 'API_ADMIN_BASIC_PERMISSION'),
       (4, 'USER_BASIC_PERMISSION'),
       (11, 'VIEW_ITEMS'),
       (12, 'VIEW_ORDERS_SELF'),
       (13, 'CREATE_ORDER'),
       (14, 'DELETE_ORDER_SELF'),
       (15, 'VIEW_PROFILE'),
       (16, 'UPDATE_USER_SELF'),
       (17, 'CREATE_FEEDBACK'),
       (18, 'VIEW_NEWS'),
       (19, 'CREATE_COMMENT'),
       (20, 'VIEW_USERS_ALL'),
       (21, 'UPDATE_USERS_ALL'),
       (22, 'DISABLE_USER'),
       (23, 'DELETE_USER'),
       (24, 'VIEW_AUDIT'),
       (25, 'VIEW_ORDERS_ALL'),
       (26, 'UPDATE_ORDER_STATUS'),
       (27, 'CREATE_NEWS'),
       (28, 'UPDATE_NEWS_ALL'),
       (29, 'REMOVE_NEWS_ALL'),
       (30, 'REMOVE_COMMENTS_ALL'),
       (31, 'CREATE_ITEM,'),
       (32, 'COPY_ITEM'),
       (33, 'REMOVE_ITEM'),
       (34, 'UPLOAD_ITEMS'),
       (35, 'UPDATE_DISCOUNT_ITEM'),
       (36, 'UPDATE_DISCOUNT_USERS'),
       (37, 'VIEW_FEEDBACK'),
       (38, 'VIEW_ITEMS_API'),
       (39, 'CREATE_ITEM_API'),
       (40, 'UPDATE_ITEM_API'),
       (41, 'REMOVE_ITEM_API'),
       (42, 'DELETE_ITEMS_IF_NOT_IN_ORDER_API')
on duplicate key update f_id = f_id;

insert into t_role_permission (f_role_id, f_permission_id)
values (1, 1),
       (1, 20),
       (1, 21),
       (1, 22),
       (1, 23),
       (1, 24),
       (2, 2),
       (2, 25),
       (2, 26),
       (2, 27),
       (2, 28),
       (2, 29),
       (2, 30),
       (2, 31),
       (2, 32),
       (2, 33),
       (2, 34),
       (2, 35),
       (2, 36),
       (2, 37),
       (2, 11),
       (2, 18),
       (3, 3),
       (3, 38),
       (3, 39),
       (3, 40),
       (3, 41),
       (3, 42),
       (4, 4),
       (4, 11),
       (4, 12),
       (4, 13),
       (4, 14),
       (4, 15),
       (4, 16),
       (4, 17),
       (4, 18),
       (4, 19)
on duplicate key update f_role_id = f_role_id;

insert into t_discount (f_id, f_name, f_percent, f_finish_date)
values (1, 'ten_percent', 10, now() + interval 365 day),
       (2, 'twenty_percent', 20, now() + interval 365 day),
       (3, 'thirty_percent', 30, now() + interval 365 day)
on duplicate key update f_id = f_id;

insert into t_user (f_id, f_email, f_first_name, f_last_name, f_password, f_is_disabled, f_role_id, f_discount_id)
values (1,
        'security@pcst.by',
        'security',
        'admin',
        '$2y$04$RKa4VWVKsl7ZlCTm5zEo/ePftabLjuPgy5qwVPX8nhoivbZxas9kK',
        false,
        (select r.f_id from t_role r where r.f_name = 'security_admin'),
        null),
       (2,
        'sales@pcst.by',
        'sales',
        'admin',
        '$2y$04$0wadQHuVWc0xbOFTTTyPbuEETKyiwuPmA5hbVYXvBShtWfMquDrae',
        false,
        (select r.f_id from t_role r where r.f_name = 'sales_admin'),
        null),
       (3,
        'api@pcst.by',
        'api',
        'admin',
        '$2y$04$VOHrSUaVjmEh3wzvJCKnxONijs.bnCF55UDoVAYWxayDEzOa6.VpO',
        false,
        (select r.f_id from t_role r where r.f_name = 'api_admin'),
        null),
       (4,
        'user@pcst.by',
        'user',
        'default',
        '$2y$04$L63EAi1vz9lJBXGPwNBvqOSEUBHo5GzgEVhmRO2/V.JdHNn3tLovq',
        false,
        (select r.f_id from t_role r where r.f_name = 'user'),
        null)
on duplicate key update f_id = f_id;