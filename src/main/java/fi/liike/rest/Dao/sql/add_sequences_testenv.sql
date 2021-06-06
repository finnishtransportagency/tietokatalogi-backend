-- TIETOJÄRJESTELMÄSALKKU
drop sequence jarjestelma_seq;
drop sequence jarjestelma_id_seq;
create sequence jarjestelma_seq start with 1;
create sequence jarjestelma_id_seq start with 1;

-- TIETOJÄRJESTELMÄSALKKUHISTORIA
drop sequence jarjestelmahist_seq;
drop sequence jarjestelmahist_id_seq;
create sequence jarjestelmahist_seq start with 1;
create sequence jarjestelmahist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger jarjestelmahist_trig
before insert on TIETOJARJESTELMASALKKUHISTORIA
for each row
begin 
    select jarjestelmahist_id_seq.NEXTVAL
    into :new.rivi_id
    from dual;
end;
/

-- PALVELUKATALOGI
drop sequence palvelu_seq;
drop sequence palvelu_id_seq;
create sequence palvelu_seq start with 1;
create sequence palvelu_id_seq start with 1;

-- PALVELUKATALOGIHISTORIA
drop sequence palveluhist_seq;
drop sequence palveluhist_id_seq;
create sequence palveluhist_seq start with 1;
create sequence palveluhist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger palveluhist_trig
before insert on palveluhistoria
for each row
begin 
    select palveluhist_id_seq.NEXTVAL
    into :new.rivi_id
    from dual;
end;
/

-- LOOGINENTIETOVARANTO
drop sequence looginentv_seq;
drop sequence looginentv_id_seq;
create sequence looginentv_seq start with 1;
create sequence looginentv_id_seq start with 1;

-- LOOGINENTIETOVARANTOHISTORIA
drop sequence looginentvhist_seq;
drop sequence looginentvhist_id_seq;
create sequence looginentvhist_seq start with 1;
create sequence looginentvhist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger looginentvhist_trig
before insert on looginentietovarantohistoria
for each row
begin 
    select looginentvhist_id_seq.NEXTVAL
    into :new.rivi_id
    from dual;
end;
/

-- FYYSINENTIETOVARANTO
drop sequence fyysinentv_seq;
drop sequence fyysinentv_id_seq;
create sequence fyysinentv_seq start with 1;
create sequence fyysinentv_id_seq start with 1;

-- FYYSINENTIETOVARANTOHISTORIA
drop sequence fyysinentvhist_seq;
drop sequence fyysinentvhist_id_seq;
create sequence fyysinentvhist_seq start with 1;
create sequence fyysinentvhist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger fyysinentvhist_trig
before insert on fyysinentietovarantohistoria
for each row
begin 
    select fyysinentv_id_seq.NEXTVAL
    into :new.rivi_id
    from dual;
end;
/

-- PÄÄTIETORYHMÄ
drop sequence paatietoryhma_seq;
drop sequence paatietoryhma_id_seq;
create sequence paatietoryhma_seq start with 1;
create sequence paatietoryhma_id_seq start with 1;

-- PÄÄTIETORYHMÄHISTORIA
drop sequence paatietoryhmahist_seq;
drop sequence paatietoryhmahist_id_seq;
create sequence paatietoryhmahist_seq start with 1;
create sequence paatietoryhmahist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger paatietoryhmahist_trig
before insert on paatietoryhmahistoria
for each row
begin 
    select paatietoryhmahist_id_seq.NEXTVAL
    into :new.rivi_id
    from dual;
end;
/

-- TIETORYHMÄ
drop sequence tietoryhma_seq;
drop sequence tietoryhma_id_seq;
create sequence tietoryhma_seq start with 1;
create sequence tietoryhma_id_seq start with 1;


-- TIETORYHMÄHISTORIA
drop sequence tietoryhmahist_seq;
drop sequence tietoryhmahist_id_seq;
create sequence tietoryhmahist_seq start with 1;
create sequence tietoryhmahist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger tietoryhmahist_trig
before insert on tietoryhmahistoria
for each row
begin 
    select tietoryhmahist_id_seq.NEXTVAL
    into :new.rivi_id
    from dual;
end;
/

-- TIETOLAJI
drop sequence tietolaji_seq;
drop sequence tietolaji_id_seq;
create sequence tietolaji_seq start with 1;
create sequence tietolaji_id_seq start with 1;

-- TIETOLAJIHISTORIA
drop sequence tietolajihist_seq;
drop sequence tietolajihist_id_seq;
create sequence tietolajihist_seq start with 1;
create sequence tietolajihist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger tietolajihist_trig
before insert on tietohistoria
for each row
begin 
    select tietolajihist_id_seq.NEXTVAL
    into :new.rivi_id
    from dual;
end;
/

-- JOINLOOGINENFYYSINEN
drop sequence joinlooginenfyysinen_seq;
create sequence joinlooginenfyysinen_seq start with 1;

drop sequence joinlooginenfyysinen_id_seq;
create sequence joinlooginenfyysinen_id_seq start with 1;

-- update rivitunnus automatically. this is needed only for unit tests. Not in product environment
create or replace trigger joinlooginenfyysinen_trig
before insert on looginentietovarantofyysinenti
for each row
begin 
    select joinlooginenfyysinen_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

-- JOINLOOGINENFYYSINENHISTORIA
drop sequence joinloogfyyshistoria_seq;
drop sequence joinloogfyyshistoria_id_seq;
create sequence joinloogfyyshistoria_seq start with 1;
create sequence joinloogfyyshistoria_id_seq start with 1;

-- update rivitunnus automatically. this is needed only for unit tests. Not in product environment
create or replace trigger joinloogfyyshistoria_trig
before insert on looginenfyysinenhistoria
for each row
begin 
    select joinloogfyyshistoria_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

-- JOINTIETOLAJILOOGINEN
drop sequence jointietolajilooginen_seq;
create sequence jointietolajilooginen_seq start with 1;

drop sequence jointietolajilooginen_id_seq;
create sequence jointietolajilooginen_id_seq start with 1;

-- update rivitunnus automatically. this is needed only for unit tests. Not in product environment
create or replace trigger jointietolajilooginen_trig
before insert on tietolooginentietovaranto
for each row
begin 
    select jointietolajilooginen_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

-- JOINTIETOLAJILOOGINENHISTORY
drop sequence jointietolooghistoria_seq;
drop sequence jointietolooghistoria_id_seq;
create sequence jointietolooghistoria_seq start with 1;
create sequence jointietolooghistoria_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger jtietolooghist_trig
before insert on tietolooginenhistoria
for each row
begin 
    select jointietolooghistoria_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

-- JOINTIETOLAJITIETORYHMA
drop sequence jointietolajitietoryhma_seq;
drop sequence jointietolajitietoryhma_id_seq;
create sequence jointietolajitietoryhma_seq start with 1;
create sequence jointietolajitietoryhma_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger jtietotietoryhma_trig
before insert on tietotietoryhma
for each row
begin 
    select jointietolajitietoryhma_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

-- JOINTIETOLAJITIETORYHMAHISTORY
drop sequence jtietotietoryhmahist_seq;
drop sequence jtietotietoryhmahist_id_seq;
create sequence jtietotietoryhmahist_seq start with 1;
create sequence jtietotietoryhmahist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger jtietotietoryhmahist_trig
before insert on tietotietoryhmahistoria
for each row
begin 
    select jtietotietoryhmahist_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

-- JOINTIETORYHMAPAATIETORYHMA
drop sequence jointietorpaatietor_seq;
drop sequence jointietorpaatietor_id_seq;
create sequence jointietorpaatietor_seq start with 1;
create sequence jointietorpaatietor_id_seq start with 1;

-- JOINJARJESTELMALINKKAUS
drop sequence joinjarjestelmalinkkaus_seq;
drop sequence joinjarjestelmalinkkaus_id_seq;
create sequence joinjarjestelmalinkkaus_seq start with 1;
create sequence joinjarjestelmalinkkaus_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger jointietorpaatietor_trig
before insert on tietoryhmapaatietoryhma
for each row
begin 
    select jointietorpaatietor_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

create or replace trigger joinjarjestelmalinkkaus_trig
before insert on jarjestelmalinkkaus
for each row
    begin
        select joinjarjestelmalinkkaus_id_seq.NEXTVAL
        into :new.rivitunnus
        from dual;
    end;
/

-- JOINTIETORYHMAPAATIETORYHMAHISTORY
drop sequence jtietorpaatietorhist_seq;
drop sequence jtietorpaatietorhist_id_seq;
create sequence jtietorpaatietorhist_seq start with 1;
create sequence jtietorpaatietorhist_id_seq start with 1;

-- update rivi_id automatically. this is needed only for unit tests. Not in product environment
create or replace trigger jtietorpaatietorhist_trig
before insert on tietoryhmapaatietohistoria
for each row
begin 
    select jtietorpaatietorhist_id_seq.NEXTVAL
    into :new.rivitunnus
    from dual;
end;
/

-- JOINTIETORYHMAPAATIETORYHMAHISTORY
drop sequence joinjlinkhist_seq;
drop sequence joinjlinkhist_id_seq;
create sequence joinjlinkhist_seq start with 1;
create sequence joinjlinkhist_id_seq start with 1;

create or replace trigger joinjlinkhist_trig
before insert on jarjestelmalinkkaushistoria
for each row
    begin
        select joinjarjestelmalinkkaus_id_seq.NEXTVAL
        into :new.rivitunnus
        from dual;
    end;
/

-- JARJESTELMAHENKILOROOLI
drop sequence joinjarjhenkrooli_seq;
drop sequence joinjarjhenkrooli_id_seq;
create sequence joinjarjhenkrooli_seq start with 1;
create sequence joinjarjhenkrooli_id_seq start with 1;

-- JARJESTELMAHENKILOROOLIHISTORY
drop sequence jarjhenkroolihist_seq;
drop sequence jarjhenkroolihist_id_seq;
create sequence jarjhenkroolihist_seq start with 1;
create sequence jarjhenkroolihist_id_seq start with 1;

-- HENKILO
drop sequence henkilo_seq;
drop sequence henkilo_id_seq;
create sequence henkilo_seq start with 1;
create sequence henkilo_id_seq start with 1;

-- HENKILO_TEMP
drop sequence henkilo_temp_seq;
drop sequence henkilo_temp_id_seq;
create sequence henkilo_temp_seq start with 1;
create sequence henkilo_temp_id_seq start with 1;

-- ROOLI
drop sequence rooli_seq;
drop sequence rooli_id_seq;
create sequence rooli_seq start with 1;
create sequence rooli_id_seq start with 1;
