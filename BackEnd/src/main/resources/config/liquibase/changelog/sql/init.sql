CREATE TABLE IF NOT EXISTS public.addresses
(
    id bigint NOT NULL,
    city character varying(255) COLLATE pg_catalog."default",
    postal_code character varying(255) COLLATE pg_catalog."default",
    street character varying(255) COLLATE pg_catalog."default",
    street_number character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.addresses_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY addresses.id;
ALTER TABLE addresses ALTER COLUMN id SET DEFAULT nextval('addresses_id_seq');



CREATE TABLE IF NOT EXISTS public.venues
(
    floor integer,
    "number" integer,
    address_id bigint,
    id bigint NOT NULL,
    CONSTRAINT venues_pkey PRIMARY KEY (id),
    CONSTRAINT venues_address_id_key UNIQUE (address_id),
    CONSTRAINT fk_venues_address FOREIGN KEY (address_id)
    REFERENCES public.addresses (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);
CREATE SEQUENCE IF NOT EXISTS public.venues_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY venues.id;
ALTER TABLE venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq');

CREATE TABLE IF NOT EXISTS public.event_types
(
    id bigint NOT NULL,
    age_restriction character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    sport character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT event_types_pkey PRIMARY KEY (id),
    CONSTRAINT event_types_sport_check CHECK (sport::text = ANY (ARRAY['TENNIS'::character varying, 'VOLLEYBALL'::character varying, 'ATHLETIC'::character varying]::text[]))
);

CREATE SEQUENCE IF NOT EXISTS public.event_types_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY event_types.id;
ALTER TABLE event_types ALTER COLUMN id SET DEFAULT nextval('event_types_id_seq');

CREATE TABLE IF NOT EXISTS public.sport_clubs
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    logo bytea,
    CONSTRAINT sport_clubs_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.sport_clubs_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY sport_clubs.id;
ALTER TABLE sport_clubs ALTER COLUMN id SET DEFAULT nextval('sport_clubs_id_seq');

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    sport_club_id bigint,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT fk_users_sport_clubs FOREIGN KEY (sport_club_id)
    REFERENCES public.sport_clubs (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY users.id;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');

CREATE TABLE IF NOT EXISTS public.sports_events
(
    capacity integer,
    is_available boolean,
    event_type_id bigint,
    id bigint NOT NULL,
    trainer_id bigint,
    venue_id bigint,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT sports_events_pkey PRIMARY KEY (id),
    CONSTRAINT fk_sports_events_event_types FOREIGN KEY (event_type_id)
    REFERENCES public.event_types (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fk_sports_events_trainers FOREIGN KEY (trainer_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fk_sports_events_venues FOREIGN KEY (venue_id)
    REFERENCES public.venues (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.sports_events_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY sports_events.id;
ALTER TABLE sports_events ALTER COLUMN id SET DEFAULT nextval('sports_events_id_seq');

CREATE TABLE IF NOT EXISTS public.schedules
(
    end_time timestamp(6) without time zone,
    id bigint NOT NULL,
    sports_event_id bigint,
    start_time timestamp(6) without time zone,
    CONSTRAINT schedules_pkey PRIMARY KEY (id),
    CONSTRAINT fk_schedules_sports_events FOREIGN KEY (sports_event_id)
    REFERENCES public.sports_events (id) MATCH SIMPLE
                          ON UPDATE NO ACTION
                          ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.schedules_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY schedules.id;
ALTER TABLE schedules ALTER COLUMN id SET DEFAULT nextval('schedules_id_seq');

CREATE TABLE IF NOT EXISTS public.event_signup
(
    id bigint NOT NULL,
    registration_time timestamp(6) without time zone,
    schedule_id bigint,
    user_id bigint,
    CONSTRAINT event_signup_pkey PRIMARY KEY (id),
    CONSTRAINT fk_event_signup_schedules FOREIGN KEY (schedule_id)
    REFERENCES public.schedules (id) MATCH SIMPLE
                                   ON UPDATE NO ACTION
                                   ON DELETE NO ACTION,
    CONSTRAINT fk_event_signup_users FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
                                   ON UPDATE NO ACTION
                                   ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.event_signup_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY event_signup.id;
ALTER TABLE event_signup ALTER COLUMN id SET DEFAULT nextval('event_signup_id_seq');

CREATE TABLE IF NOT EXISTS public.results
(
    id bigint NOT NULL,
    sports_event_id bigint,
    rating character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT results_pkey PRIMARY KEY (id),
    CONSTRAINT results_sports_event_id_key UNIQUE (sports_event_id),
    CONSTRAINT fk_results_sports_events FOREIGN KEY (sports_event_id)
    REFERENCES public.sports_events (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);
CREATE SEQUENCE IF NOT EXISTS public.results_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY results.id;
ALTER TABLE results ALTER COLUMN id SET DEFAULT nextval('results_id_seq');