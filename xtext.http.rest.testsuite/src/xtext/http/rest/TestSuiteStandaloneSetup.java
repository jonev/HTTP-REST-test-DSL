/*
 * generated by Xtext 2.22.0
 */
package xtext.http.rest;

import org.eclipse.emf.ecore.EPackage;

import com.google.inject.Injector;

import xtext.http.rest.testSuite.TestSuitePackage;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class TestSuiteStandaloneSetup extends TestSuiteStandaloneSetupGenerated {

	public static void doSetup() {
		new TestSuiteStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
	
	@Override
	public void register(Injector injector) {
		super.register(injector);
		EPackage.Registry.INSTANCE.put(TestSuitePackage.eNS_URI, TestSuitePackage.eINSTANCE);
	}

}