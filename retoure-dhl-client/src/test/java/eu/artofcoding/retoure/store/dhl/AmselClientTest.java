package eu.artofcoding.retoure.store.dhl;

import eu.artofcoding.retoure.delivery.dhl.AmselClient;
import eu.artofcoding.retoure.entity.Customer;
import eu.artofcoding.retoure.entity.ReturnLabel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AmselClientTest {

    private Customer customer;

    @BeforeMethod
    public void setUp() throws Exception {
        // Create customer
        customer = new Customer();
        customer.setFirstname("Ralf");
        customer.setLastname("Bensmann");
        customer.setShippingAddress1("Grüner Weg");
        customer.setShippingAddress1StreetNumber("23a");
        customer.setZipcode("48366");
        customer.setCity("Laer");
        customer.setPhone("02554 912969-0");
    }

    @Test
    public void testMakeLabel() throws Exception {
        // Create label request
        AmselClient amselClient = new AmselClient();
        ReturnLabel returnLabel = amselClient.makeLabel(customer);
        Path labelPath = Paths.get("label.pdf");
        returnLabel.saveBinary(labelPath);
        System.out.println("Wrote label to " + labelPath.toString());
    }

}
