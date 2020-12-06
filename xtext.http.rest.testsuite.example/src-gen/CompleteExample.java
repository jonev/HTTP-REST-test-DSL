import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.util.Arrays;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CompleteExample {
	// This is an example test collection to show functionality
	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	
	@AfterAll
	static void afterAll() throws IOException {
		httpclient.close();
	}
	
	@Test
	void Global_resource() throws Exception{
		// ----- GET http://localhost:3000/text -----
		System.out.println("GET " + "http://localhost:3000/text");
		HttpGet request0 = new HttpGet("http://localhost:3000/text");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Local_resource() throws Exception{
		// ----- GET http://localhost:3000/text -----
		System.out.println("GET " + "http://localhost:3000/text");
		HttpGet request0 = new HttpGet("http://localhost:3000/text");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Inject_var_into_url() throws Exception{
		String person1Id = "23a1c108-97bf-4906-be5a-5872a566767b";
		// ----- GET http://localhost:3000/persons/{personId} -----
		System.out.println("GET " + "http://localhost:3000/persons/" + person1Id);
		HttpGet request0 = new HttpGet("http://localhost:3000/persons/" + person1Id);
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Inject_var_into_subsequent_calls_url() throws Exception{
		String person1Id = "null";
		// ----- POST http://localhost:3000/persons -----
		System.out.println("POST " + "http://localhost:3000/persons");
		HttpPost request0 = new HttpPost("http://localhost:3000/persons");
		request0.addHeader("Content-type", "application/json");
		request0.setEntity(new StringEntity("{\"name\":\"Jone\",\"job\":\"Developer\"}"));
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			JsonObject jsonObject = responseEntity == null ? null : JsonParser.parseString(responseEntity).getAsJsonObject();
			person1Id = jsonObject.get("personId").getAsString();
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
		// ----- GET http://localhost:3000/persons/{personId} -----
		System.out.println("GET " + "http://localhost:3000/persons/" + person1Id);
		HttpGet request1 = new HttpGet("http://localhost:3000/persons/" + person1Id);
		request1.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request1.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request1)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Assert_status_code() throws Exception{
		// ----- GET http://localhost:3000/text -----
		System.out.println("GET " + "http://localhost:3000/text");
		HttpGet request0 = new HttpGet("http://localhost:3000/text");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Assert_text() throws Exception{
		// ----- GET http://localhost:3000/text -----
		System.out.println("GET " + "http://localhost:3000/text");
		HttpGet request0 = new HttpGet("http://localhost:3000/text");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals("This is the test text", responseEntity);
		}
		
	}
	@Test
	void Assert_body_with_injected_personId() throws Exception{
		String person1Id = "23a1c108-97bf-4906-be5a-5872a566767b";
		// ----- GET http://localhost:3000/persons/{personId} -----
		System.out.println("GET " + "http://localhost:3000/persons/" + person1Id);
		HttpGet request0 = new HttpGet("http://localhost:3000/persons/" + person1Id);
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			JsonObject responseBody = JsonParser.parseString(responseEntity).getAsJsonObject();
			responseBody.addProperty("personId", person1Id);
			assertEquals(responseBody.toString(), responseEntity);
		}
		
	}
	@Test
	void Assert_headers() throws Exception{
		String headerValue = "Header-value";
		// ----- GET http://localhost:3000/text -----
		System.out.println("GET " + "http://localhost:3000/text");
		HttpGet request0 = new HttpGet("http://localhost:3000/text");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		request0.addHeader("Header-with-ref", headerValue);
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			Header[] headers = response.getAllHeaders();
			assertTrue(Arrays.stream(headers).anyMatch((h) -> h.getName().toLowerCase().equals("X-Powered-By".toLowerCase()) && h.getValue().equals("Express")));
			assertTrue(Arrays.stream(headers).anyMatch((h) -> h.getName().toLowerCase().equals("Api-Key".toLowerCase()) && h.getValue().equals("xxx-api-xxx-key-xxx")));
			assertTrue(Arrays.stream(headers).anyMatch((h) -> h.getName().toLowerCase().equals("secret".toLowerCase()) && h.getValue().equals("ThisIsTheSecret")));
			assertTrue(Arrays.stream(headers).anyMatch((h) -> h.getName().toLowerCase().equals("Header-with-ref".toLowerCase()) && h.getValue().equals(headerValue)));
		}
		
	}
	@Test
	void Assert_multiple() throws Exception{
		// ----- GET http://localhost:3000/text -----
		System.out.println("GET " + "http://localhost:3000/text");
		HttpGet request0 = new HttpGet("http://localhost:3000/text");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals("This is the test text", responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Globaly_included_headers() throws Exception{
		// ----- GET http://localhost:3000/persons -----
		System.out.println("GET " + "http://localhost:3000/persons");
		HttpGet request0 = new HttpGet("http://localhost:3000/persons");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Global_headers() throws Exception{
		// ----- GET http://localhost:3000/persons -----
		System.out.println("GET " + "http://localhost:3000/persons");
		HttpGet request0 = new HttpGet("http://localhost:3000/persons");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		request0.addHeader("Client-Name", "Norway-Testing");
		request0.addHeader("Behalf-of", "Sverige-Testing");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Local_headers() throws Exception{
		// ----- GET http://localhost:3000/persons -----
		System.out.println("GET " + "http://localhost:3000/persons");
		HttpGet request0 = new HttpGet("http://localhost:3000/persons");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		request0.addHeader("Local-header-1", "local-header-value-1");
		request0.addHeader("Local-header-2", "local-header-value-2");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Global_and_Local_header() throws Exception{
		// ----- GET http://localhost:3000/persons -----
		System.out.println("GET " + "http://localhost:3000/persons");
		HttpGet request0 = new HttpGet("http://localhost:3000/persons");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		request0.addHeader("Client-Name", "Norway-Testing");
		request0.addHeader("Local-header-1", "local-header-value-1");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Headers_with_reference_to_var() throws Exception{
		String personJob = "null";
		// ----- GET http://localhost:3000/persons/23a1c108-97bf-4906-be5a-5872a566767b -----
		System.out.println("GET " + "http://localhost:3000/persons/23a1c108-97bf-4906-be5a-5872a566767b");
		HttpGet request0 = new HttpGet("http://localhost:3000/persons/23a1c108-97bf-4906-be5a-5872a566767b");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			JsonObject jsonObject = responseEntity == null ? null : JsonParser.parseString(responseEntity).getAsJsonObject();
			personJob = jsonObject.get("job").getAsString();
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
		// ----- GET http://localhost:3000/persons -----
		System.out.println("GET " + "http://localhost:3000/persons");
		HttpGet request1 = new HttpGet("http://localhost:3000/persons");
		request1.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request1.addHeader("secret", "ThisIsTheSecret");
		request1.addHeader("person-job", personJob);
		
		try(CloseableHttpResponse response = httpclient.execute(request1)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Post_body() throws Exception{
		// ----- POST http://localhost:3000/persons -----
		System.out.println("POST " + "http://localhost:3000/persons");
		HttpPost request0 = new HttpPost("http://localhost:3000/persons");
		request0.addHeader("Content-type", "application/json");
		request0.setEntity(new StringEntity("{\"name\":\"Jone\",\"job\":\"Developer\"}"));
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Post_body_containing_body() throws Exception{
		// ----- POST http://localhost:3000/persons -----
		System.out.println("POST " + "http://localhost:3000/persons");
		HttpPost request0 = new HttpPost("http://localhost:3000/persons");
		request0.addHeader("Content-type", "application/json");
		request0.setEntity(new StringEntity("{\"name\":\"Jone\",\"job\":\"Developer\",\"address\":{\"street\":\"Test street\",\"popularity\":\"High\"}}"));
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void SeperateAddress_test() throws Exception{
		String addressId = "null";
		String person1Id = "null";
		// ----- POST http://localhost:3000/addresses -----
		System.out.println("POST " + "http://localhost:3000/addresses");
		HttpPost request0 = new HttpPost("http://localhost:3000/addresses");
		request0.addHeader("Content-type", "application/json");
		request0.setEntity(new StringEntity("{\"street\":\"Test street\",\"popularity\":\"High\"}"));
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			JsonObject jsonObject = responseEntity == null ? null : JsonParser.parseString(responseEntity).getAsJsonObject();
			addressId = jsonObject.get("addressId").getAsString();
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
		// ----- POST http://localhost:3000/persons -----
		System.out.println("POST " + "http://localhost:3000/persons");
		HttpPost request1 = new HttpPost("http://localhost:3000/persons");
		request1.addHeader("Content-type", "application/json");
		JsonObject requestBody = JsonParser.parseString("{\"name\":\"Body\",\"tests\":\"Person without address\"}").getAsJsonObject();
		requestBody.addProperty("addressId", addressId);
		request1.setEntity(new StringEntity(requestBody.toString()));
		request1.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request1.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request1)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			JsonObject jsonObject = responseEntity == null ? null : JsonParser.parseString(responseEntity).getAsJsonObject();
			person1Id = jsonObject.get("personId").getAsString();
			assertEquals(200, response.getStatusLine().getStatusCode());
			JsonObject responseBody = JsonParser.parseString(responseEntity).getAsJsonObject();
			responseBody.addProperty("addressId", addressId);
			responseBody.addProperty("personId", person1Id);
			assertEquals(responseBody.toString(), responseEntity);
		}
		
		// ----- PUT http://localhost:3000/persons/{personId} -----
		System.out.println("PUT " + "http://localhost:3000/persons/" + person1Id);
		HttpPut request2 = new HttpPut("http://localhost:3000/persons/" + person1Id);
		request2.addHeader("Content-type", "application/json");
		request2.setEntity(new StringEntity("{\"name\":\"Ola\",\"job\":\"CEO\"}"));
		request2.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request2.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request2)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			JsonObject responseBody = JsonParser.parseString(responseEntity).getAsJsonObject();
			responseBody.addProperty("personId", person1Id);
			assertEquals(responseBody.toString(), responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
		// ----- DELETE http://localhost:3000/persons/{personId} -----
		System.out.println("DELETE " + "http://localhost:3000/persons/" + person1Id);
		HttpDelete request3 = new HttpDelete("http://localhost:3000/persons/" + person1Id);
		request3.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request3.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request3)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
		// ----- DELETE http://localhost:3000/addresses/{addressId} -----
		System.out.println("DELETE " + "http://localhost:3000/addresses/" + addressId);
		HttpDelete request4 = new HttpDelete("http://localhost:3000/addresses/" + addressId);
		request4.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request4.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request4)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
		}
		
	}
	@Test
	void Url1_test() throws Exception{
		String TestId1 = "value1";
		String TestId2 = "value2";
		// ----- GET http://localhost:3000/urltest1/{firstId}/test/{secondId} -----
		System.out.println("GET " + "http://localhost:3000/urltest1/" + TestId1 + "/test/" + TestId2);
		HttpGet request0 = new HttpGet("http://localhost:3000/urltest1/" + TestId1 + "/test/" + TestId2);
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
			JsonObject responseBody = JsonParser.parseString(responseEntity).getAsJsonObject();
			assertEquals(responseBody.toString(), responseEntity);
		}
		
	}
	@Test
	void Url2_test() throws Exception{
		String TestId1 = "value1";
		String TestId2 = "value2";
		// ----- GET http://localhost:3000/urltest2/{firstId}/{secondId}/test -----
		System.out.println("GET " + "http://localhost:3000/urltest2/" + TestId1 + "/" + TestId2 + "/test");
		HttpGet request0 = new HttpGet("http://localhost:3000/urltest2/" + TestId1 + "/" + TestId2 + "/test");
		request0.addHeader("Api-Key", "xxx-api-xxx-key-xxx");
		request0.addHeader("secret", "ThisIsTheSecret");
		
		try(CloseableHttpResponse response = httpclient.execute(request0)){
			String responseEntity = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
			System.out.println("Result: " + responseEntity);
			assertEquals(200, response.getStatusLine().getStatusCode());
			JsonObject responseBody = JsonParser.parseString(responseEntity).getAsJsonObject();
			assertEquals(responseBody.toString(), responseEntity);
		}
		
	}
}

