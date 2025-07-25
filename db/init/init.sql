CREATE SEQUENCE IF NOT EXISTS rabbitmq_logs_seq;

CREATE TABLE IF NOT EXISTS public.rabbitmq_logs (
    id BIGINT NOT NULL DEFAULT nextval('rabbitmq_logs_seq'),
    queue text COLLATE pg_catalog."default",
    log_date timestamp with time zone,
    message text COLLATE pg_catalog."default",
    CONSTRAINT "rabbitmq_logs_pkey" PRIMARY KEY (id)
);