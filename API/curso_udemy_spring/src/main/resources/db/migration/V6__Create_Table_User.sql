-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id serial,
    account_non_expired boolean,
    account_non_locked boolean,
    credentials_non_expired boolean,
    enabled boolean,
    full_name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    user_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_k8d0f2n7n88w1a16yhua64onx UNIQUE (user_name)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;