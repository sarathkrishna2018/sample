package colruyt.rearulmgtdmnejb.service.bl;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import colruyt.coreutillib.security.ass.AssException;
import colruyt.coreutillib.security.ass.AssToolUtil;
import colruyt.rearulmgtdmnejb.util.ReactionRuleDmnConstants;


@Stateless
@LocalBean
public class CookieService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private byte[] SECRET_KEY_BYTES = null;
	
	@PostConstruct
	public void init(){
		try{
		String assSecretKey = AssToolUtil.getSecureValue(ReactionRuleDmnConstants.PRICECONFIG_SECRET_KEY);
			SECRET_KEY_BYTES = assSecretKey.getBytes(StandardCharsets.UTF_8);
		}catch(AssException e){
			throw new RuntimeException(e);
		}
	}
	
	public String generateJWT() {
		try {
			JwtClaims claims = new JwtClaims();
			claims.setExpirationTimeMinutesInTheFuture(ReactionRuleDmnConstants.EXPIRY_MINUTES);
			claims.setSubject(ReactionRuleDmnConstants.APPLICATION_NAME);
			claims.setClaim(ReactionRuleDmnConstants.SUB, ReactionRuleDmnConstants.APPLICATION_NAME);
			Key key = new HmacKey(SECRET_KEY_BYTES);
			JsonWebSignature jws = new JsonWebSignature();
			jws.setPayload(claims.toJson());
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
			jws.setKey(key);
			jws.setDoKeyValidation(false); // relaxes the key length requirement
			return jws.getCompactSerialization();
		} catch (JoseException e) {
			throw new RuntimeException(e);
		}
	}
}
