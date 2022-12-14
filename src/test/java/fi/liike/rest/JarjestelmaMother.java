package fi.liike.rest;

import fi.liike.rest.api.dto.JarjestelmaDto;

public class JarjestelmaMother {

	private Integer id;
	private String lifeSpan;
	private String name;
	private String type;
	private String area;
	private String owningOrganization;

	private JarjestelmaMother() {
	}

	public static JarjestelmaMother def() {
		return new JarjestelmaMother();
	}

	public JarjestelmaMother tunnus(int id) {
		this.id = id;
		return this;
	}

	public JarjestelmaMother nimi(String name) {
		this.name = name;
		return this;
	}

	public JarjestelmaMother elinkaaritila(String lifeSpan) {
		this.lifeSpan = lifeSpan;
		return this;
	}

	public JarjestelmaMother jarjestelmatyyppi(String type) {
		this.type = type;
		return this;
	}

	public JarjestelmaMother jarjestelmaalue(String area) {
		this.area = area;
		return this;
	}

	public JarjestelmaMother owningOrganization(String owningOrganization) {
		this.owningOrganization = owningOrganization;
		return this;
	}

	public JarjestelmaDto build() {
		JarjestelmaDto jarjestelma = new JarjestelmaDto();
		jarjestelma.setTunnus(id);
		jarjestelma.setNimi(name);
		jarjestelma.setElinkaaritila(lifeSpan);
		jarjestelma.setJarjestelmatyyppi(type);
		jarjestelma.setJarjestelmaalue(area);
		jarjestelma.setOmistava_organisaatio(owningOrganization);
		return jarjestelma;
	}
}
