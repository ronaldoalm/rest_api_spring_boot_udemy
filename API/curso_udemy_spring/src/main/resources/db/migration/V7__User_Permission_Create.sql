-- Table: public.user_permission

-- DROP TABLE public.user_permission;

CREATE TABLE public.user_permission
(
    user_id serial,
    permission_id serial,
    CONSTRAINT fkbklmo9kchans5u3e4va0ouo1s FOREIGN KEY (permission_id)
        REFERENCES public.permission (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkn8ba4v3gvw1d82t3hofelr82t FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.user_permission
    OWNER to postgres;