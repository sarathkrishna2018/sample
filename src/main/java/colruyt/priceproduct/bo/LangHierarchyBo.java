package colruyt.priceproduct.bo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LangHierarchyBo implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	private RefIsoLangBO refIsoLanguage;
	private String name;
	
	public RefIsoLangBO getRefIsoLanguage() {
		return refIsoLanguage;
	}
	public void setRefIsoLanguage(RefIsoLangBO refIsoLanguage) {
		this.refIsoLanguage = refIsoLanguage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
