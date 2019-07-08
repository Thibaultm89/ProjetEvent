--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-07-08 09:49:24

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
-- TOC entry 200 (class 1259 OID 17406)
-- Name: Activity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Activity" (
    id_activity integer NOT NULL,
    name_activity character varying NOT NULL,
    start_activity timestamp without time zone NOT NULL,
    finish_activity timestamp without time zone NOT NULL,
    manager integer NOT NULL,
    id_event integer NOT NULL,
    img_activity character varying
);


ALTER TABLE public."Activity" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17404)
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
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 199
-- Name: Activity_id_activity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Activity_id_activity_seq" OWNED BY public."Activity".id_activity;


--
-- TOC entry 196 (class 1259 OID 17212)
-- Name: Activity_people; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Activity_people" (
    id_activity integer NOT NULL,
    id_people integer NOT NULL
);


ALTER TABLE public."Activity_people" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17432)
-- Name: Event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Event" (
    id_event integer NOT NULL,
    name_event character varying NOT NULL,
    start_event timestamp without time zone NOT NULL,
    finish_event timestamp without time zone NOT NULL,
    img_event character varying
);


ALTER TABLE public."Event" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17430)
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
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 201
-- Name: Event_id_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Event_id_event_seq" OWNED BY public."Event".id_event;


--
-- TOC entry 197 (class 1259 OID 17223)
-- Name: People; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."People" (
    firstname_people character varying NOT NULL,
    lastname_people character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    id_people integer NOT NULL
);


ALTER TABLE public."People" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17269)
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
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 198
-- Name: People_id_people_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."People_id_people_seq" OWNED BY public."People".id_people;


--
-- TOC entry 2705 (class 2604 OID 17409)
-- Name: Activity id_activity; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity" ALTER COLUMN id_activity SET DEFAULT nextval('public."Activity_id_activity_seq"'::regclass);


--
-- TOC entry 2706 (class 2604 OID 17435)
-- Name: Event id_event; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Event" ALTER COLUMN id_event SET DEFAULT nextval('public."Event_id_event_seq"'::regclass);


--
-- TOC entry 2704 (class 2604 OID 17271)
-- Name: People id_people; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People" ALTER COLUMN id_people SET DEFAULT nextval('public."People_id_people_seq"'::regclass);


--
-- TOC entry 2841 (class 0 OID 17406)
-- Dependencies: 200
-- Data for Name: Activity; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Activity" VALUES (2, 'Concert Coone', '2019-07-20 23:00:00', '2019-07-20 00:00:00', 1, 1, 'coone.jpg');
INSERT INTO public."Activity" VALUES (3, 'Concert Nekfeu', '2019-07-04 19:00:00', '2019-07-04 21:00:00', 2, 3, 'nekfeu.jpg');
INSERT INTO public."Activity" VALUES (4, 'Concert Orelsan', '2019-07-18 20:00:00', '2019-07-18 23:00:00', 2, 2, 'orelsan.jpg');
INSERT INTO public."Activity" VALUES (5, 'Concert Damso', '2019-07-04 15:00:00', '2019-07-04 18:00:00', 2, 3, 'damso.jpg');
INSERT INTO public."Activity" VALUES (7, 'Concert Lorenzo', '2019-07-05 22:00:00', '2019-07-05 23:00:00', 2, 3, 'lorenzo.jpg');
INSERT INTO public."Activity" VALUES (8, 'Concert Patrick Bruel', '2019-07-18 14:00:00', '2019-07-18 15:00:00', 1, 2, 'patrickbruel.jpg');
INSERT INTO public."Activity" VALUES (1, 'Concert Dimitri Vegas', '2019-07-20 00:00:00', '2019-07-20 00:00:00', 1, 1, 'dimitrivegas.jpg');


--
-- TOC entry 2837 (class 0 OID 17212)
-- Dependencies: 196
-- Data for Name: Activity_people; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Activity_people" VALUES (1, 1);
INSERT INTO public."Activity_people" VALUES (1, 2);
INSERT INTO public."Activity_people" VALUES (1, 3);
INSERT INTO public."Activity_people" VALUES (2, 4);
INSERT INTO public."Activity_people" VALUES (2, 2);
INSERT INTO public."Activity_people" VALUES (8, 7);
INSERT INTO public."Activity_people" VALUES (8, 8);
INSERT INTO public."Activity_people" VALUES (5, 6);
INSERT INTO public."Activity_people" VALUES (3, 5);
INSERT INTO public."Activity_people" VALUES (7, 2);
INSERT INTO public."Activity_people" VALUES (4, 3);
INSERT INTO public."Activity_people" VALUES (4, 4);


--
-- TOC entry 2843 (class 0 OID 17432)
-- Dependencies: 202
-- Data for Name: Event; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Event" VALUES (1, 'Tomorrowland', '2019-07-19 00:00:00', '2019-07-28 00:00:00', 'tomorrowland.jpg');
INSERT INTO public."Event" VALUES (2, 'Francofolies', '2019-07-18 00:00:00', '2019-07-21 00:00:00', 'francofolies.jpg');
INSERT INTO public."Event" VALUES (3, 'Les Ardentes', '2019-07-04 00:00:00', '2019-07-07 00:00:00', 'ardentes.jpg');


--
-- TOC entry 2838 (class 0 OID 17223)
-- Dependencies: 197
-- Data for Name: People; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."People" VALUES ('Philippe', 'Vancom', 'philippe@hotmail.com', 'philou', 2);
INSERT INTO public."People" VALUES ('Maxime', 'Quoilin', 'maximusdecimus@hotmailus.com', 'nicotinus', 3);
INSERT INTO public."People" VALUES ('Damien', 'Bouffioux', 'damb@hotmail.com', 'bingbangboum', 4);
INSERT INTO public."People" VALUES ('Delphine', 'Franquinet', 'Delphinefranquinet@gmail.com', 'Coucou', 5);
INSERT INTO public."People" VALUES ('Zarah', 'Al-Sudani', 'Zara-AlSudani@gmail.com', 'bishmila', 6);
INSERT INTO public."People" VALUES ('Jean', 'Leclercq', 'jeanleclercq@gmail.com', 'legeekdu64', 7);
INSERT INTO public."People" VALUES ('Melissa', 'Schyns', 'melissaschyns@gmail.com', 'melimeli', 8);
INSERT INTO public."People" VALUES ('Thibault', 'Molle', 'mollethibault@hotmail.com', 'non', 1);


--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 199
-- Name: Activity_id_activity_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Activity_id_activity_seq"', 1, true);


--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 201
-- Name: Event_id_event_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Event_id_event_seq"', 1, false);


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 198
-- Name: People_id_people_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."People_id_people_seq"', 16, true);


--
-- TOC entry 2712 (class 2606 OID 17414)
-- Name: Activity Activity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity"
    ADD CONSTRAINT "Activity_pkey" PRIMARY KEY (id_activity);


--
-- TOC entry 2714 (class 2606 OID 17440)
-- Name: Event Event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Event"
    ADD CONSTRAINT "Event_pkey" PRIMARY KEY (id_event);


--
-- TOC entry 2708 (class 2606 OID 17280)
-- Name: People People_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT "People_pkey" PRIMARY KEY (id_people);


--
-- TOC entry 2710 (class 2606 OID 17236)
-- Name: People unique_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT unique_email UNIQUE (email);


--
-- TOC entry 2715 (class 2606 OID 17281)
-- Name: Activity_people id_people; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity_people"
    ADD CONSTRAINT id_people FOREIGN KEY (id_people) REFERENCES public."People"(id_people);


-- Completed on 2019-07-08 09:49:24

--
-- PostgreSQL database dump complete
--

