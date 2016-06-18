/**
 * Package location for Model concept tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a confirm stand controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ConfirmStandControllerTest {

    /**
     * The confirm stand controller to be tested.
     */
    ConfirmStandController confirmStandController;

    /**
     * The exhibition center used for tests.
     */
    ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitor responsible used for tests.
     */
    ExhibitorResponsible exhibitorResponsible;

    @Before
    public void setUp() {
        this.exhibitionCenter = DefaultInstantiator.createExhibitionCenter();

        this.exhibitorResponsible = ((ExhibitionApplication) this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).
                getApplicationsList().getApplicationsList().get(0)).getExhibitor().getExhibitorResponsible();

        this.confirmStandController = new ConfirmStandController(this.exhibitionCenter, this.exhibitorResponsible);
    }

//    /**
//     * Test of getExhibitionApplicationsByExhibitorResponsible method, of class
//     * ConfirmStandController.
//     */
//    @Test
//    public void testGetExhibitionApplicationsByExhibitorResponsible() {
//        System.out.println("getExhibitionApplicationsByExhibitorResponsible");
//
//        List<ExhibitionApplication> expResult = new ArrayList<>();
//        expResult.add((ExhibitionApplication) this.exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0));
//
//        List<ExhibitionApplication> result = this.confirmStandController.getExhibitionApplicationsByExhibitorResponsible();
//        assertEquals(expResult, result);
//    }

}