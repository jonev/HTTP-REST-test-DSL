package xtext.http.rest.generator

import xtext.http.rest.testSuite.HttpGet
import xtext.http.rest.testSuite.HttpRequest
import xtext.http.rest.testSuite.HttpResource
import xtext.http.rest.testSuite.EnvironmentVariable
import org.eclipse.emf.common.util.EList
import xtext.http.rest.testSuite.JsonObject
import xtext.http.rest.testSuite.HttpPost
import xtext.http.rest.testSuite.HttpPut
import xtext.http.rest.testSuite.HttpDelete
import xtext.http.rest.testSuite.Value
import xtext.http.rest.testSuite.Header

class RequestGenerator {
	
	def getRequest(HttpRequest it, int nr)'''
			«getRequestType(it, nr)»
			«IF body !== null»
			request«nr».addHeader("Content-type", "application/json");
			«IF bodyInjectionValues.size < 1»
				request«nr».setEntity(new StringEntity(«getTestBody(body)»));
			«ELSE»
				«getTestBodyInjections(body, bodyInjectionValues)»
				request«nr».setEntity(new StringEntity(requestBody.toString()));
			«ENDIF»
			«ENDIF»
	'''
	
	def getRequestType(HttpRequest it, int nr){
		val usedResource = resource === null ? resourceRef : resource;
		switch it {
			HttpGet: 
			'''
			// ----- GET «usedResource.url» -----
			System.out.println("GET " + «getHttpResource(usedResource, resourceInjectionValues)»);
			HttpGet request«nr» = new HttpGet(«getHttpResource(usedResource, resourceInjectionValues)»);
			'''
			HttpPost: 
			'''
			// ----- POST «usedResource.url» -----
			System.out.println("POST " + «getHttpResource(usedResource, resourceInjectionValues)»);
			HttpPost request«nr» = new HttpPost(«getHttpResource(usedResource, resourceInjectionValues)»);
			'''
			HttpPut: 
			'''
			// ----- PUT «usedResource.url» -----
			System.out.println("PUT " + «getHttpResource(usedResource, resourceInjectionValues)»);
			HttpPut request«nr» = new HttpPut(«getHttpResource(usedResource, resourceInjectionValues)»);
			'''
			HttpDelete: 
			'''
			// ----- DELETE «usedResource.url» -----
			System.out.println("DELETE " + «getHttpResource(usedResource, resourceInjectionValues)»);
			HttpDelete request«nr» = new HttpDelete(«getHttpResource(usedResource, resourceInjectionValues)»);
			'''
		}
	}
	
	def String getHttpResource(HttpResource it, EList<EnvironmentVariable> injectionValues){
		var result = "\"" + url + "\"";
		for(i:injectionValues){
			result = result.replace("/{" + i.reference + "}/", "/\" + " + i.name + " + \"/")
			result = result.replace("/{" + i.reference + "}\"", "/\" + " + i.name)
		}
		return result;
	}
	
	def getTestBodyInjections(JsonObject body, EList<EnvironmentVariable> injectionValues)'''
		JsonObject requestBody = JsonParser.parseString(«getTestBody(body)»).getAsJsonObject();
		«FOR value:injectionValues»
		requestBody.addProperty("«value.reference»", «value.name»);
		«ENDFOR»
	'''
	
	def String getTestBody(JsonObject it){
		return "\"" + getValueLoop(it) + "\""
	}
	
	def String getValueLoop(JsonObject it){
		return "{" + fields.map[field | getValue(field)].join(",") + "}"
	}
	
	def String getValue(Value field){
		if(field.value !== null){
			return "\\\"" + field.name + "\\\":\\\"" + field.value + "\\\""
		}
		return "\\\"" + field.name + "\\\":" + getValueLoop(field.reference)
	}
	
	def getHeader(Header it, int requestNr){
		if(value !== null){
			return '''request«requestNr».addHeader("«headerName»", "«value»");'''
		}
		return '''request«requestNr».addHeader("«headerName»", «ref.name»);'''
	}
}