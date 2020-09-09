package com.mycompany.app;

import java.util.Properties;

import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String caUrl = "https://caendpoint:443"; // ensure that port is of CA
        String caName = "ca.TestOrg";
        String pemStr = "-----BEGIN CERTIFICATE-----\n***\n-----END CERTIFICATE-----\n";

        Properties properties = new Properties();
        properties.put("pemBytes", pemStr.getBytes());

        HFCAClient hfcaClient = HFCAClient.createNewInstance(caName, caUrl, properties);

        CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        hfcaClient.setCryptoSuite(cryptoSuite);

        final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
        enrollmentRequestTLS.addHost("https://caendpoint:443");
        enrollmentRequestTLS.setProfile("tls");
        Enrollment adminEnrollment = hfcaClient.enroll("<fabric-ca-admin-username>", "<fabric-ca-admin-password>");
        System.out.println(adminEnrollment.getCert());
    }
}
