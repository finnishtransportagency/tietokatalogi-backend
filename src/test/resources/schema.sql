create table if not exists TIETORYHMA (
  paatietoryhmatunnus int,
  tietoryhmatunnus int identity primary key,
  nimi varchar,
  koodi varchar,
  kuvaus varchar,
  lahde varchar,
  omistaja varchar,
  tila varchar,
  synonyymi varchar,
  tietosuojataso varchar,
  document_id varchar
);
create table if not exists PAATIETORYHMA (
  paatietoryhmatunnus int identity primary key,
  nimi varchar,
  koodi varchar,
  kuvaus varchar,
  lahde varchar,
  omistaja varchar,
  tila varchar,
  synonyymi varchar,
  tietosuojataso varchar,
  julkaisutieto varchar,
  julkaisuhuomio varchar,
  document_id varchar
);
create table if not exists APU_HIERARKIA_3 (
	LIITTYY_1 varchar,
	TUNNUS_1 int,
	VARANTO_1 varchar,
	LIITTYY_2 varchar,
	TUNNUS_2 int,
	VARANTO_2 varchar,
	TYYPPI varchar
);
/*
create table if not exists TIETO (
	TIETOTUNNUS int,
	LOOGINENTIETOVARANTOTUNNUS int
	TIETORYHMATUNNUS int,
	NIMI varchar,
	KOODI varchar,
	KUVAUS varchar,
	OMISTAJA varchar,
	LAHDE varchar,
	TILA varchar,
	SYNONYYMI varchar,
	TIETOSUOJATASO varchar,
	YLEMPITASO varchar,
	RIVITUNNUS varchar,
	RIVITILA varchar,
	RIVIMUOKATTUPVM varchar
	RIVIMUOKKAAJATUNNUS varchar,
	RIVILUOTUPVM varchar,
	DOCUMENT_ID varchar
);
*/