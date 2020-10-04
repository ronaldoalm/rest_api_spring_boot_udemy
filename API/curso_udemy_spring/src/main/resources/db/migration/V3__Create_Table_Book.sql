-- Table: public.books

-- DROP TABLE public.books;

CREATE TABLE public.books
(
    id Serial,
    autor character varying(255) COLLATE pg_catalog."default" NOT NULL,
    launch_date date,
    price double precision,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to postgres;