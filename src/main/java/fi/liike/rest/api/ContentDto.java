package fi.liike.rest.api;

import fi.liike.rest.api.dto.RightsDto;
import fi.liike.rest.auth.Right;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ContentDto implements RightsDto {

	protected static Map<Right, List<String>> mapRightToFields;

	public String rivimuokattupvm;

	public String riviluotupvm;

	public abstract void setNimi(String name);

	public abstract void setTunnus(Integer id);

	public abstract Integer getTunnus();

	public abstract String getNimi();

	public abstract void setRivimuokkaajatunnus(String header);

	public abstract String getRivimuokkaajatunnus();


	public String getRivimuokattupvm() {
		return rivimuokattupvm != null && rivimuokattupvm.isEmpty() ? null : rivimuokattupvm;
	}

	public void setRivimuokattupvm(String rivimuokattupvm) {
		this.rivimuokattupvm = rivimuokattupvm;
	}

	public String getRiviluotupvm() {
		return riviluotupvm != null && riviluotupvm.isEmpty() ? null : riviluotupvm;
	}

	public void setRiviluotupvm(String riviluotupvm) {
		this.riviluotupvm = riviluotupvm;
	}

	public List<Right> getNeededRights() throws NoSuchFieldException, IllegalAccessException {
		return Right.getModifyUnsecuredRights();
	}
	//Later on this could be abstract, TODO
	public List<String> getNoRightsToModify() {return null;}
	public void setNoRightsToModify(Set<Right> userRights) {}

	@Override
	public String toString() {
		return "ContentDto[tunnus=" + getTunnus() + ", nimi=" + getNimi() +"]";
	}
}
