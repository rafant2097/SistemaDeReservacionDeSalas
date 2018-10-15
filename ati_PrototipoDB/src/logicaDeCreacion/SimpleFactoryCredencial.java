package logicaDeCreacion;


import org.json.simple.JSONObject;

import logicaDeNegocio.Credential;
import logicaDeNegocio.ReaderJson;

public class SimpleFactoryCredencial {
	
	public Credential createCredential(String pPath) {
		
		
		JSONObject jsonObject = ReaderJson.readJson(pPath);
		
		Credential credential = new Credential();
		credential.setDatabase(jsonObject.get("database").toString());
		credential.setHost(jsonObject.get("host").toString());
		credential.setPassword(jsonObject.get("password").toString());
		credential.setUserName(jsonObject.get("user").toString());
		
		return credential;
		
		
	}
	

}
