create table if not exists urls
(
    id             bigserial  primary key,
    hash           varchar(7) not null,
    original_url  text       not null,
    expiration     timestamp,
    create_date    timestamp default current_timestamp not null
);

grant select, insert, update on table urls to vlad_os;
