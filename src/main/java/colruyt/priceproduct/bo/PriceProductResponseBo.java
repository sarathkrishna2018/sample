package colruyt.priceproduct.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PriceProductResponseBo implements Serializable {

	private Long id;

	private List<String> name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PriceProductResponseBo that = (PriceProductResponseBo) o;
		return id.equals(that.id) &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
