package fi.liike.rest.Dao.Hibernate;

import java.util.Comparator;

import fi.liike.rest.Model.JoinHistory;

public class HistoryComparator implements Comparator<JoinHistory> {
	@Override
	public int compare(JoinHistory o1, JoinHistory o2) {
		if (o1.getRivitunnus() == null || o2.getRivitunnus() == null)
			return 0;
		return o2.getRivitunnus().compareTo(o1.getRivitunnus());
	}
}
