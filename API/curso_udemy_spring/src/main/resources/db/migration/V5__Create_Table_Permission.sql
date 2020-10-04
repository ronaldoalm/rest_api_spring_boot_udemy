-- Table: public.permission

-- DROP TABLE public.permission;

CREATE TABLE public.permission
(
    id serial,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT permission_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.permission
    OWNER to postgres;