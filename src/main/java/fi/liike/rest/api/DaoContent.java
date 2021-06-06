package fi.liike.rest.api;

import java.util.ArrayList;
import java.util.List;

import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Model.Haettava;

public class DaoContent {

	private Haettava content;
	private List<JoinPublicDao> joinDaos;

	public DaoContent() {
		joinDaos = new ArrayList<JoinPublicDao>();
	}
	public void setHaettava(Haettava content) {
		this.content = content;
	}

	public void addJoinDao(JoinPublicDao joinDao) {
		joinDaos.add(joinDao);
	}

	public Haettava getContent() {
		return content;
	}

	public List<JoinPublicDao> getJoinDaos() {
		return joinDaos;
	}

}
