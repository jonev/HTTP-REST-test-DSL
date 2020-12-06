package xtext.http.rest.generator

import xtext.http.rest.testSuite.Header
import xtext.http.rest.testSuite.Assertion
import xtext.http.rest.testSuite.StatusCodeAssertion
import xtext.http.rest.testSuite.BodyAssertion
import xtext.http.rest.testSuite.TextAssertion
import xtext.http.rest.testSuite.HeaderAssertion
import xtext.http.rest.testSuite.EnvironmentVariable
import org.eclipse.emf.common.util.EList

class AssertionGenerator {
	
	def String getAssertion(Assertion it){
		switch it{
			StatusCodeAssertion: 
			'''
			assertEquals(«code», response.getStatusLine().getStatusCode());
			'''
			BodyAssertion: 
			'''
			«getAssertionBodInjections(bodyInjectionValues)»
			assertEquals(responseBody.toString(), responseEntity);
			'''
			TextAssertion: '''assertEquals("«text»", responseEntity);'''
			HeaderAssertion:
			'''
			Header[] headers = response.getAllHeaders();
			«FOR header:headers»
			«getHeaderAssertion(header)»
			«ENDFOR»
			«FOR header:headerRefs»
			«getHeaderAssertion(header)»
			«ENDFOR»
			'''
		}
	}
	
	def String getHeaderAssertion(Header header){
		var headerValue = header.value === null ? header.ref.name : "\"" + header.value + "\""
		return '''
		assertTrue(Arrays.stream(headers).anyMatch((h) -> h.getName().toLowerCase().equals("«header.headerName»".toLowerCase()) && h.getValue().equals(«headerValue»)));
		'''
	}
	
	def getAssertionBodInjections(EList<EnvironmentVariable> injectionValues)'''
		JsonObject responseBody = JsonParser.parseString(responseEntity).getAsJsonObject();
		«FOR value:injectionValues»
		responseBody.addProperty("«value.reference»", «value.name»);
		«ENDFOR»
	'''
}