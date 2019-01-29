package colruyt.rearulmgtdmnejb.util;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import colruyt.rearulmgtdmnejb.service.bl.CookieService;
import colruyt.rearulmgtdmnejb.exception.RRMDomainException;
import colruyt.rearulmgtdmnejb.exception.ServiceDownException;


/**
 * 
 * 
 */
@Stateless
@LocalBean
public class ExternalClientService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CookieService cookieService;
	
	/**
	 * 
	 * @param url
	 * @throws RRMDomainException 
	 */
	public String callGetService(String url) throws  ServiceDownException, RRMDomainException {
		Client client = Client.create();
		WebResource webResource = client.resource(url);		
		Builder builder = webResource.type(MediaType.APPLICATION_JSON);
		builder = builder.accept(MediaType.APPLICATION_JSON);
		builder = builder.header(ReactionRuleMgtConstants.JWT_HEADER_NAME, cookieService.generateJWT());
		ClientResponse response = builder.get(ClientResponse.class);
		String jsonResponse = response.getEntity(String.class);
		if (Response.Status.OK.getStatusCode() == response.getStatus() || Response.Status.CREATED.getStatusCode() == response.getStatus()) {
			return jsonResponse;
		}
		handleException(response, jsonResponse);
		return jsonResponse;
	}
	
	/**
	 * 
	 * @param jsonResponse
	 * @param response
	 * @throws RRMDomainException 
	 */
	private void handleException(ClientResponse response, String jsonResponse) throws ServiceDownException, RRMDomainException  {
		if (Response.Status.INTERNAL_SERVER_ERROR.getStatusCode() == response.getStatus()
				|| Response.Status.UNAUTHORIZED.getStatusCode() == response.getStatus()) {
			throw new RRMDomainException(jsonResponse);
		} 
		if (Response.Status.UNAUTHORIZED.getStatusCode() == response.getStatus()) {
			throw new ServiceDownException(jsonResponse);
		} else {
			throw new RuntimeException();
		}

	}

	public Gson getGsonWithDateDeserializer() {
		// Here GsonBuilder with Type adapters are used for de-serializing date
		// value in long format
		return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) {
				return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
			}
		}).create();

	}
}