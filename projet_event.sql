--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-07-08 13:59:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 17265)
-- Name: Activity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Activity" (
    id_activity integer NOT NULL,
    name_activity character varying NOT NULL,
    start_activity timestamp without time zone NOT NULL,
    finish_activity timestamp without time zone NOT NULL,
    manager integer NOT NULL,
    id_event integer NOT NULL,
    img_activity character varying DEFAULT 'defaultActivity.jpg'::character varying
);


ALTER TABLE public."Activity" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17271)
-- Name: Activity_id_activity_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Activity_id_activity_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Activity_id_activity_seq" OWNER TO postgres;

--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 197
-- Name: Activity_id_activity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Activity_id_activity_seq" OWNED BY public."Activity".id_activity;


--
-- TOC entry 198 (class 1259 OID 17273)
-- Name: Activity_people; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Activity_people" (
    id_activity integer NOT NULL,
    id_people integer NOT NULL
);


ALTER TABLE public."Activity_people" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17276)
-- Name: Event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Event" (
    id_event integer NOT NULL,
    name_event character varying NOT NULL,
    start_event timestamp without time zone NOT NULL,
    finish_event timestamp without time zone NOT NULL,
    img_event character varying DEFAULT 'defaultEvent.jpg'::character varying
);


ALTER TABLE public."Event" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17282)
-- Name: Event_id_event_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Event_id_event_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Event_id_event_seq" OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 200
-- Name: Event_id_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Event_id_event_seq" OWNED BY public."Event".id_event;


--
-- TOC entry 201 (class 1259 OID 17284)
-- Name: People; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."People" (
    id_people integer NOT NULL,
    firstname_people character varying NOT NULL,
    lastname_people character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL
);


ALTER TABLE public."People" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17290)
-- Name: People_id_people_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."People_id_people_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."People_id_people_seq" OWNER TO postgres;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 202
-- Name: People_id_people_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."People_id_people_seq" OWNED BY public."People".id_people;


--
-- TOC entry 2704 (class 2604 OID 17292)
-- Name: Activity id_activity; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity" ALTER COLUMN id_activity SET DEFAULT nextval('public."Activity_id_activity_seq"'::regclass);


--
-- TOC entry 2706 (class 2604 OID 17293)
-- Name: Event id_event; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Event" ALTER COLUMN id_event SET DEFAULT nextval('public."Event_id_event_seq"'::regclass);


--
-- TOC entry 2708 (class 2604 OID 17294)
-- Name: People id_people; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People" ALTER COLUMN id_people SET DEFAULT nextval('public."People_id_people_seq"'::regclass);


--
-- TOC entry 2839 (class 0 OID 17265)
-- Dependencies: 196
-- Data for Name: Activity; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Dimitri Vegas', '2019-07-20 00:00:00', '2019-07-20 00:00:00', 1, 1, 'dimitrivegas.jpg');
INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Coone', '2019-07-20 23:00:00', '2019-07-20 00:00:00', 1, 1, 'coone.jpg');
INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Nekfeu', '2019-07-04 19:00:00', '2019-07-04 21:00:00', 2, 3, 'nekfeu.jpg');
INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Orelsan', '2019-07-18 20:00:00', '2019-07-18 23:00:00', 2, 2, 'orelsan.jpg');
INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Damso', '2019-07-04 15:00:00', '2019-07-04 18:00:00', 2, 3, 'damso.jpg');
INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Lorenzo', '2019-07-05 22:00:00', '2019-07-05 23:00:00', 2, 3, 'lorenzo.jpg');
INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Patrick Bruel', '2019-07-18 14:00:00', '2019-07-18 15:00:00', 1, 2, 'patrickbruel.jpg');
INSERT INTO public."Activity" (name_activity, start_activity, finish_activity, manager, id_event, img_activity) VALUES ('Concert Bhad Bhabie', '2019-07-07 21:00:00', '2019-07-07 23:00:00', 1, 3, 'defaultActivity.jpg');


--
-- TOC entry 2841 (class 0 OID 17273)
-- Dependencies: 198
-- Data for Name: Activity_people; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Activity_people" VALUES (1, 1);
INSERT INTO public."Activity_people" VALUES (1, 3);
INSERT INTO public."Activity_people" VALUES (2, 1);
INSERT INTO public."Activity_people" VALUES (2, 4);
INSERT INTO public."Activity_people" VALUES (3, 5);
INSERT INTO public."Activity_people" VALUES (4, 2);
INSERT INTO public."Activity_people" VALUES (4, 3);
INSERT INTO public."Activity_people" VALUES (5, 2);
INSERT INTO public."Activity_people" VALUES (6, 2);
INSERT INTO public."Activity_people" VALUES (6, 1);
INSERT INTO public."Activity_people" VALUES (7, 7);
INSERT INTO public."Activity_people" VALUES (7, 8);
INSERT INTO public."Activity_people" VALUES (8, 2);


--
-- TOC entry 2842 (class 0 OID 17276)
-- Dependencies: 199
-- Data for Name: Event; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Event" (name_event, start_event, finish_event, img_event) VALUES ('Tomorrowland', '2019-07-19 00:00:00', '2019-07-28 00:00:00', 'tomorrowland.jpg');
INSERT INTO public."Event" (name_event, start_event, finish_event, img_event) VALUES ('Francofolies', '2019-07-18 00:00:00', '2019-07-21 00:00:00', 'francofolies.jpg');
INSERT INTO public."Event" (name_event, start_event, finish_event, img_event) VALUES ('Les Ardentes', '2019-07-04 00:00:00', '2019-07-07 00:00:00', 'ardentes.jpg');
INSERT INTO public."Event" (name_event, start_event, finish_event, img_event) VALUES ('Dour', '2019-07-10 00:00:00', '2019-07-14 00:00:00', 'defaultEvent.jpg');


--
-- TOC entry 2844 (class 0 OID 17284)
-- Dependencies: 201
-- Data for Name: People; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Thibault', 'Molle', 'mollethibault@hotmail.com', 'non');
INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Philippe', 'Vancom', 'philippe@hotmail.com', 'philou');
INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Maxime', 'Quoilin', 'maximusdecimus@hotmailus.com', 'nicotinus');
INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Damien', 'Bouffioux', 'damb@hotmail.com', 'bingbangboum');
INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Delphine', 'Franquinet', 'Delphinefranquinet@gmail.com', 'Coucou');
INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Zarah', 'Al-Sudani', 'Zara-AlSudani@gmail.com', 'bishmila');
INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Jean', 'Leclercq', 'jeanleclercq@gmail.com', 'legeekdu64');
INSERT INTO public."People" (firstname_people, lastname_people, email, password) VALUES ('Melissa', 'Schyns', 'melissaschyns@gmail.com', 'melimeli');


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 197
-- Name: Activity_id_activity_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Activity_id_activity_seq"', 8, true);


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 200
-- Name: Event_id_event_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Event_id_event_seq"', 4, true);


--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 202
-- Name: People_id_people_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."People_id_people_seq"', 8, true);


--
-- TOC entry 2710 (class 2606 OID 17296)
-- Name: Activity Activity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity"
    ADD CONSTRAINT "Activity_pkey" PRIMARY KEY (id_activity);


--
-- TOC entry 2712 (class 2606 OID 17298)
-- Name: Event Event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Event"
    ADD CONSTRAINT "Event_pkey" PRIMARY KEY (id_event);


--
-- TOC entry 2714 (class 2606 OID 17300)
-- Name: People People_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT "People_pkey" PRIMARY KEY (id_people);


--
-- TOC entry 2716 (class 2606 OID 17302)
-- Name: People unique_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT unique_email UNIQUE (email);


--
-- TOC entry 2717 (class 2606 OID 17303)
-- Name: Activity_people id_people; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity_people"
    ADD CONSTRAINT id_people FOREIGN KEY (id_people) REFERENCES public."People"(id_people);


-- Completed on 2019-07-08 13:59:19

--
-- PostgreSQL database dump complete
--

