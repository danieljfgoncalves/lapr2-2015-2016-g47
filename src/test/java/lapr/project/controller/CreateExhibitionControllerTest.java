/**
 * Package location for Apllication Controllers tests.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.Demonstration;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.OrganizersList;
import lapr.project.model.Place;
import lapr.project.model.StaffAttributionsList;
import lapr.project.model.StaffList;
import lapr.project.model.User;
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
public class CreateExhibitionControllerTest {

    /**
     * Instance of CreateExhibitionController.
     */
    private CreateExhibitionController controller;

    @Before
    public void setUp() {
        this.controller = new CreateExhibitionController(new ExhibitionCenter());
    }

    /**
     * Test of newExhibition method, of class CreateExhibitionController.
     */
    @Test
    public void testNewExhibition() {
        System.out.println("newExhibition");
        Exhibition expResult = new Exhibition();
        this.controller.newExhibition();

        assertEquals(this.controller.getExhibition(), expResult);
    }

    /**
     * Test of newOrganizer method, of class CreateExhibitionController.
     */
    @Test
    public void testNewOrganizer() {
        System.out.println("newOrganizer");
        User user = new User();
        this.controller.newExhibition(); // TODO: Verify if this is correct.

        boolean result = this.controller.newOrganizer(user);
        assertTrue(result);
    }

    /**
     * Test of registerExhibition method, of class CreateExhibitionController.
     */
    @Test
    public void testRegisterExhibition() {

        System.out.println("registerExhibition");

        List<Organizer> organizerslist = new ArrayList<>();
        organizerslist.add(new Organizer());
        organizerslist.add(new Organizer());

        Exhibition exhibition = new Exhibition("title", "description", new Date(2016, 0, 1),
                new Date(2016, 3, 1), new Date(2016, 0, 10), new Date(2016, 1, 1),
                new Date(2016, 1, 10), new Date(2016, 2, 1),
                new Place(), new StaffList(), new OrganizersList(organizerslist),
                new ArrayList<Demonstration>(), new StaffAttributionsList());

        this.controller.setExhibition(exhibition);

        boolean result = this.controller.registerExhibition();
        assertTrue(result);
    }
}
