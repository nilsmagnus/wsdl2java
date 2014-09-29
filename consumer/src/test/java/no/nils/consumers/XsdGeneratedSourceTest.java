package no.nils.consumers;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class XsdGeneratedSourceTest {

    @Test
    public void compileWithXsdGeneratedCode() {
        no.nils.xsd2java.sample.ShipInfoType shipInfoType = new no.nils.xsd2java.sample.ShipInfoType();
        assertNotNull(shipInfoType);
    }
}
