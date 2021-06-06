package fi.liike.rest.Dao.Hibernate;

import java.util.Comparator;

import fi.liike.rest.Model.Haettava;

public class KatalogiComparator implements Comparator<Haettava> {
	@Override
	public int compare(Haettava o1, Haettava o2) {
		if (o2.getRiviluotupvm() == null && o1.getRiviluotupvm() == null)
			return 0;
		if (o2.getRiviluotupvm() == null)
			return -1;
		if (o1.getRiviluotupvm() == null)
			return 1;
		return o2.getRiviluotupvm().compareTo(o1.getRiviluotupvm());
	}
}