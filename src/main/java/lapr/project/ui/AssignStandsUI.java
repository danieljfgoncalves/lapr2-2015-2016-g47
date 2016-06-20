/**
 * Package location for UI concepts.
 */
package lapr.project.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import lapr.project.controller.AssignStandsController;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.Submittable;
import lapr.project.ui.components.DialogSelectSubmittable;

/**
 * Represents an evaluation.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class AssignStandsUI extends JFrame{
    
      /**
     * The controller of exhibition importing mechanism.
     */
    private AssignStandsController assignStandsController;
    /**
     * The exhibition selected.
     */
    private Exhibition selectedExhibition;

    /**
     * Label size.
     */
    final Dimension LBL_SIZE = new Dimension(94, 16);
    /**
     * Window size.
     */
    final Dimension WINDOW_SIZE = new Dimension(1200, 600);
    /**
     * Field margins.
     */
    final int MARGIN_S_FIELD = 0, MARGIN_I_FIELD = 0,
            MARGIN_E_FIELD = 10, MARGIN_D_FIELD = 0;
    /**
     * Field width.
     */
    final int FIELD_TXT_WIDTH = 20, FIELD_NUM_HEIGHT = 6;
    /**
     * Empty border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);
    /**
     * Scroll size.
     */
    final Dimension SCROLL_SIZE = new Dimension(300, 500);
    public AssignStandsUI(Organizer organizer,ExhibitionCenter exhibitionCenter){
        this.assignStandsController = new AssignStandsController(organizer, exhibitionCenter);
        this.setSize(WINDOW_SIZE);
        List<Submittable> submittableList = new ArrayList(assignStandsController.getExhibitionsListByOrganizerInApplicationsDecidedState(organizer));
        DialogSelectSubmittable selectSubmittable = new DialogSelectSubmittable(this, submittableList, exhibitionCenter);
        
    }
    
}
