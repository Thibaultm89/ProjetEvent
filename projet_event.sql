--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-07-03 16:58:14

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
-- TOC entry 197 (class 1259 OID 16807)
-- Name: Activity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Activity" (
    id_activity integer NOT NULL,
    name_activity character varying NOT NULL,
    start_activity timestamp without time zone NOT NULL,
    finish_activity timestamp without time zone NOT NULL,
    manager integer NOT NULL,
    id_event integer
);


ALTER TABLE public."Activity" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16823)
-- Name: Activity_people; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Activity_people" (
    id_activity integer NOT NULL,
    id_people integer NOT NULL
);


ALTER TABLE public."Activity_people" OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16799)
-- Name: Event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Event" (
	id_event integer NOT NULL,
    name_event character varying NOT NULL,
    start_event date NOT NULL,
    finish_event date NOT NULL
);


ALTER TABLE public."Event" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16880)
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
-- TOC entry 2844 (class 0 OID 0)
-- Dependencies: 200
-- Name: Event_id_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Event_id_event_seq" OWNED BY public."Event".id_event;


--
-- TOC entry 198 (class 1259 OID 16815)
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
-- TOC entry 2700 (class 2604 OID 16882)
-- Name: Event id_event; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Event" ALTER COLUMN id_event SET DEFAULT nextval('public."Event_id_event_seq"'::regclass);


--
-- TOC entry 2835 (class 0 OID 16807)
-- Dependencies: 197
-- Data for Name: Activity; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Activity" VALUES (1, 'Concert Dimitri Vegas', '2019-07-20 00:00:01', '2019-07-20 01:00:00', 1, 1);
INSERT INTO public."Activity" VALUES (5, 'Concert Coone', '2019-07-20 23:00:00', '2019-07-20 00:00:00', 1, 1);
INSERT INTO public."Activity" VALUES (4, 'Concert Nekfeu', '2019-07-04 19:00:00', '2019-07-04 21:00:00', 2, 3);
INSERT INTO public."Activity" VALUES (2, 'Concert Orelsan', '2019-07-18 20:00:00', '2019-07-18 23:00:00', 2, 2);
INSERT INTO public."Activity" VALUES (3, 'Concert Damso', '2019-07-04 15:00:00', '2019-07-04 18:00:00', 2, 3);


--
-- TOC entry 2837 (class 0 OID 16823)
-- Dependencies: 199
-- Data for Name: Activity_people; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Activity_people" VALUES (1, 1);
INSERT INTO public."Activity_people" VALUES (1, 2);
INSERT INTO public."Activity_people" VALUES (1, 3);
INSERT INTO public."Activity_people" VALUES (2, 4);
INSERT INTO public."Activity_people" VALUES (2, 2);


--
-- TOC entry 2834 (class 0 OID 16799)
-- Dependencies: 196
-- Data for Name: Event; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Event" VALUES ('Tomorrowland', '2019-07-19', '2019-07-28', 1);
INSERT INTO public."Event" VALUES ('Francofolies', '2019-07-18', '2019-07-21', 2);
INSERT INTO public."Event" VALUES ('Les Ardentes', '2019-07-04', '2019-07-07', 3);
INSERT INTO public."Event" VALUES ('PukkelPop', '2019-08-15', '2019-08-19', 4);
INSERT INTO public."Event" VALUES ('RockWerchter', '3919-09-21', '3919-09-21', 7);


--
-- TOC entry 2836 (class 0 OID 16815)
-- Dependencies: 198
-- Data for Name: People; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."People" VALUES (1, 'Thibault', 'Molle', 'thibault@coucou.be', 'coucou');
INSERT INTO public."People" VALUES (2, 'Philippe', 'Vancom', 'philippe@hotmail.com', 'philou');
INSERT INTO public."People" VALUES (3, 'Maxime', 'Quoilin', 'maximusdecimus@hotmailus.com', 'nicotinus');
INSERT INTO public."People" VALUES (4, 'Damien', 'Bouffioux', 'damb@hotmail.com', 'bingbangboum');


--
-- TOC entry 2845 (class 0 OID 0)
-- Dependencies: 200
-- Name: Event_id_event_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Event_id_event_seq"', 7, true);


--
-- TOC entry 2704 (class 2606 OID 16811)
-- Name: Activity Activity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity"
    ADD CONSTRAINT "Activity_pkey" PRIMARY KEY (id_activity);


--
-- TOC entry 2702 (class 2606 OID 16890)
-- Name: Event Event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Event"
    ADD CONSTRAINT "Event_pkey" PRIMARY KEY (id_event);


--
-- TOC entry 2706 (class 2606 OID 16822)
-- Name: People People_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT "People_pkey" PRIMARY KEY (id_people);


--
-- TOC entry 2708 (class 2606 OID 16869)
-- Name: People unique_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."People"
    ADD CONSTRAINT unique_email UNIQUE (email);


--
-- TOC entry 2709 (class 2606 OID 16836)
-- Name: Activity Manager; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity"
    ADD CONSTRAINT "Manager" FOREIGN KEY (manager) REFERENCES public."People"(id_people);


--
-- TOC entry 2710 (class 2606 OID 16891)
-- Name: Activity fk_id_event; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity"
    ADD CONSTRAINT fk_id_event FOREIGN KEY (id_event) REFERENCES public."Event"(id_event);


--
-- TOC entry 2712 (class 2606 OID 16831)
-- Name: Activity_people id_activity; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity_people"
    ADD CONSTRAINT id_activity FOREIGN KEY (id_activity) REFERENCES public."Activity"(id_activity);


--
-- TOC entry 2711 (class 2606 OID 16826)
-- Name: Activity_people id_people; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activity_people"
    ADD CONSTRAINT id_people FOREIGN KEY (id_people) REFERENCES public."People"(id_people);


-- Completed on 2019-07-03 16:58:14

--
-- PostgreSQL database dump complete
--

