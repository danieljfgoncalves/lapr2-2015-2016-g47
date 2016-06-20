/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.demonstration.DemonstrationCreatedState;
import lapr.project.model.demonstration.DemonstrationDecidedState;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a CreateExhibitionController class.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineEfectiveDemosControllerTest {

    private DefineEfectiveDemosController controller;

    @Before
    public void setUp() {

        controller = new DefineEfectiveDemosController(DefaultInstantiator.createExhibitionCenter());

        Demonstration demonstration = new Demonstration("demo1");
        demonstration.setCurrentState(new DemonstrationCreatedState(demonstration));
        demonstration.setStartDate(new Date(2016, 02, 01));
        demonstration.setEndDate(new Date(2016, 02, 30));

        controller.setEfectiveDemonstration(demonstration);
    }

    /**
     * Test of updateDemonstration method, of class
     * DefineEfectiveDemosController, returns true.
     */
    @Test
    public void testUpdateDemonstrationTrue() {
        System.out.println("updateDemonstration");

        Demonstration demonstration1 = new Demonstration("demo1");
        demonstration1.setCurrentState(new DemonstrationCreatedState(demonstration1));
        List<Demonstration> demonsList1 = new ArrayList<>();
        demonsList1.add(demonstration1);

        Exhibition exhibition1 = new Exhibition();
        exhibition1.setDemonstrationsList(new DemonstrationsList(demonsList1));
        controller.setDemonstrationsList(exhibition1);

        boolean result = controller.updateDemonstration();
        assertTrue(result);
    }

    /**
     * Test of updateDemonstration method, of class
     * DefineEfectiveDemosController.
     */
    @Test
    public void testUpdateDemonstration() {
        System.out.println("updateDemonstration");

        Demonstration demonstration1 = new Demonstration("demo1");
        demonstration1.setCurrentState(new DemonstrationCreatedState(demonstration1));
        List<Demonstration> demonsList1 = new ArrayList<>();
        demonsList1.add(demonstration1);

        Exhibition exhibition1 = new Exhibition();
        exhibition1.setDemonstrationsList(new DemonstrationsList(demonsList1));
        controller.setDemonstrationsList(exhibition1);

        controller.updateDemonstration();
        
        demonstration1.setCurrentState(new DemonstrationDecidedState(demonstration1));
        List<Demonstration> expResult = demonsList1;
        
        List<Demonstration> result = controller.getDemonstrationsList();
        assertEquals(expResult, result);
    }

}
