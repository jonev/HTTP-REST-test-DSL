/*
 * generated by Xtext 2.22.0
 */
package xtext.http.rest.validation;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.Text;

import org.eclipse.xtext.validation.Check;

import xtext.http.rest.testSuite.BodyAssertion;
import xtext.http.rest.testSuite.EnvironmentVariable;
import xtext.http.rest.testSuite.Header;
import xtext.http.rest.testSuite.HttpRequest;
import xtext.http.rest.testSuite.HttpResource;
import xtext.http.rest.testSuite.JsonObject;
import xtext.http.rest.testSuite.StatusCodeAssertion;
import xtext.http.rest.testSuite.Test;
import xtext.http.rest.testSuite.TestCollection;
import xtext.http.rest.testSuite.TestElement;
import xtext.http.rest.testSuite.TestSuitePackage;
import xtext.http.rest.testSuite.TextAssertion;

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class TestSuiteValidator extends AbstractTestSuiteValidator {
	
	public static final String INVALID_URL = "invalidUrl";
	public static final String NOT_UNIQUE = "notUnique";
	public static final String NR_OF_STATUS_CODE_ASSERTIONS = "nrOfStatusCodeAssertions";
	public static final String NR_OF_BODY_ASSERTIONS = "nrOfBodyAssertions";
	public static final String NR_OF_TEXT_ASSERTIONS = "nrOfTextAssertions";
	

	@Check
	public void checkResourceUrlIsValid(HttpResource resource) {
		Matcher matcher = Pattern.compile("http(s)?:\\/\\/[a-zA-Z.]+(:[0-9]+)?(\\/[-a-zA-Z0-9]*|\\{[-a-zA-Z0-9]*\\})*").matcher(resource.getUrl());
		if (!matcher.matches()) {
			warning("Url is not valid",
					TestSuitePackage.Literals.HTTP_RESOURCE__URL,
					INVALID_URL);
		}
	}
	
	@Check
	public void checkUniqNamesForHttpResources(HttpResource resource) {
		if(resource.eContainer() instanceof TestCollection) {
			TestCollection testCollection = (TestCollection) resource.eContainer();
			if(testCollection.getResources().stream().filter(h -> h.getName().equals(resource.getName())).count() > 1) {
				error("Http resources must have unique name",
						TestSuitePackage.Literals.HEADER__NAME,
						NOT_UNIQUE);
			}			
		}
	}
	
	@Check
	public void checkUniqNamesForTest(Test test) {
		TestCollection testCollection = (TestCollection) test.eContainer();
		if(testCollection.getTests().stream().filter(h -> h.getName().equals(test.getName())).count() > 1) {
			error("Tests must have unique name",
					TestSuitePackage.Literals.TEST__NAME,
					NOT_UNIQUE);
		}			
		
	}
	
	@Check
	public void checkUniqNamesForJsonObject(JsonObject jsonObject) {
		Test test = (Test) jsonObject.eContainer();
		if(test.getTestBodies().stream().filter(h -> h.getName().equals(jsonObject.getName())).count() > 1) {
			error("Object variables must have unique name",
					TestSuitePackage.Literals.JSON_OBJECT__NAME,
					NOT_UNIQUE);
		}			
		
	}
	
	@Check
	public void checkUniqNamesForEnvironmentVariable(EnvironmentVariable var) {
		Test test = (Test) var.eContainer();
		if(test.getVars().stream().filter(h -> h.getName().equals(var.getName())).count() > 1) {
			error("Environment variables must have unique name",
					TestSuitePackage.Literals.ENVIRONMENT_VARIABLE__NAME,
					NOT_UNIQUE);
		}			
		
	}
	
	@Check
	public void checkUniqNamesForHeader(Header header) {
		if(header.eContainer() instanceof TestCollection) {
			TestCollection testCollection = (TestCollection) header.eContainer();
			if(testCollection.getHeaders().stream().filter(h -> h.getName().equals(header.getName())).count() > 1) {
				error("Headers must have unique name",
						TestSuitePackage.Literals.HEADER__NAME,
						NOT_UNIQUE);
			}			
		}
		if(header.eContainer() instanceof Test) {
			Test testCollection = (Test) header.eContainer();
			if(testCollection.getHeaders().stream().filter(h -> h.getName().equals(header.getName())).count() > 1) {
				error("Headers must have unique name",
						TestSuitePackage.Literals.HEADER__NAME,
						NOT_UNIQUE);
			}			
		}
	}

	@Check
	public void checkThatMultipleAssertionOfTheSameTypeDontExist(TestElement testElement) {
		if(testElement.getAssertions().stream().filter(a -> a instanceof StatusCodeAssertion).count() > 1) {
			error("Only one status code assertion is possible. Client will only respond with one code.",
					TestSuitePackage.Literals.TEST_ELEMENT__ASSERTIONS,
					NR_OF_STATUS_CODE_ASSERTIONS);
		}
		if(testElement.getAssertions().stream().filter(a -> a instanceof BodyAssertion).count() > 1) {
			error("Only one body assertion is possible. Client will only respond with one body.",
					TestSuitePackage.Literals.TEST_ELEMENT__ASSERTIONS,
					NR_OF_BODY_ASSERTIONS);
		}
		if(testElement.getAssertions().stream().filter(a -> a instanceof TextAssertion).count() > 1) {
			error("Only one text assertion is possible. Client will only respond with one text.",
					TestSuitePackage.Literals.TEST_ELEMENT__ASSERTIONS,
					NR_OF_TEXT_ASSERTIONS);
		}
	}
	
}
