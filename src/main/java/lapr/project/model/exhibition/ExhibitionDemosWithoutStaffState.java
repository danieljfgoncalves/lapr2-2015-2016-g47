/**
 * Package location for Model concepts.
 */
package lapr.project.model.exhibition;

import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionState;

/**
 * Represents a exhibitions register
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
public class ExhibitionDemosWithoutStaffState implements ExhibitionState {

    /**
     * The exhibition to change state.
     */
    private final Exhibition exhibition;

    /**
     * JAXB only constructor.
     */
    public ExhibitionDemosWithoutStaffState() {
        this.exhibition = new Exhibition();

    }

    /**
     * Default constructor of an exhibition's staffWithoutDemos state.
     *
     * @param exhibition Exhibition to change state
     */
    public ExhibitionDemosWithoutStaffState(Exhibition exhibition) {

        this.exhibition = exhibition;
    }

    @Override
    public boolean isInicial() {
        return false;
    }

    @Override
    public boolean setCreated() {

        return false;
    }

    @Override
    public boolean isCreated() {
        return false;
    }

    @Override
    public boolean setStaffDefined() {

        if (validate()) {

            this.exhibition.setState(new ExhibitionCompleteState(this.exhibition));
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isStaffDefined() {
        return false;
    }

    @Override
    public boolean setDemonstrationsDefined() {

        return false;
    }

    @Override
    public boolean isDemonstrationsDefined() {
        return false;
    }

    @Override
    public boolean setOpenApplication() {
        return false;
    }

    @Override
    public boolean isOpenApplications() {
        return false;
    }

    @Override
    public boolean setClosedApplications() {
        return false;
    }

    @Override
    public boolean isClosedApplications() {
        return false;
    }

    @Override
    public boolean setDetectedConflicts() {
        return false;
    }

    @Override
    public boolean isDetectedConficts() {
        return false;
    }

    @Override
    public boolean setChangedConflicts() {
        return false;
    }

    @Override
    public boolean isChangedConflitcts() {
        return false;
    }

    @Override
    public boolean setApplicationsInEvaluation() {
        return false;
    }

    @Override
    public boolean isApplicationsInEvaluation() {
        return false;
    }

    @Override
    public boolean setApplicationsInDecision() {
        return false;
    }

    @Override
    public boolean isApplicationsInDecision() {
        return false;
    }

    @Override
    public boolean setApplicationsDecided() {
        return false;
    }

    @Override
    public boolean isApplicationsDecided() {
        return false;
    }

    @Override
    public boolean validate() {

        // Verifies if there is at least one staff member defined.
        return !exhibition.getStaffList().getStaffList().isEmpty();
    }
}
