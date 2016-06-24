/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import lapr.project.model.Actor;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Organizer;
import lapr.project.model.StaffMember;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.GenerateKeywordsRankingPanel;
import lapr.project.ui.components.UseCaseButtonsGenerator;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface for dashboard.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DashboardUI extends JFrame {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The actor logged in.
     */
    private final Actor actor;

    /**
     * The JTabbedPane.
     */
    private JTabbedPane tabPane;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Dashboard";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Creates the dashboard user interface.
     *
     * @param exhibitionCenter exhibition center with data
     * @param actor actor logged in
     */
    public DashboardUI(ExhibitionCenter exhibitionCenter, Actor actor) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.actor = actor;

        setJMenuBar(new CustomMenuBar(this.exhibitionCenter, this));

        if (this.actor instanceof ExhibitionsManager || this.actor instanceof Organizer) {
            createComponentsAdmin();
        } else if (this.actor instanceof StaffMember || this.actor instanceof ExhibitorResponsible) {
            createComponents();
        }

        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setSize(WINDOW_DIMEMNSION);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createComponentsAdmin() {
        JPanel componentsPanel = new JPanel(new BorderLayout(0, 10));

        componentsPanel.add(createTabPane(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanelAdmin(), BorderLayout.SOUTH);

        add(componentsPanel);
    }

    private JTabbedPane createTabPane() {
        this.tabPane = new JTabbedPane();

        if (this.actor instanceof ExhibitionsManager) {
            // TODO review this
            //tabPane.add("Keywords Ranking", new GenerateKeywordsRankingPanel(this.exhibitionCenter));
        } else if (this.actor instanceof Organizer) {
            // TODO add the available panes
        }

        return this.tabPane;
    }

    private JPanel createButtonsPanelAdmin() {
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 0, 10));

        JPanel buttonsPanelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel buttonsPanelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        if (this.actor instanceof ExhibitionsManager) {
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase1Button(this.exhibitionCenter, (ExhibitionsManager)this.actor));
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase7Button(this.exhibitionCenter, (ExhibitionsManager)this.actor));
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase9Button(this.exhibitionCenter, (ExhibitionsManager)this.actor));
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase12Button(this.exhibitionCenter, (ExhibitionsManager)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase22Button(this.exhibitionCenter, (ExhibitionsManager)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase23Button(this.exhibitionCenter, (ExhibitionsManager)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase24Button(this.exhibitionCenter, (ExhibitionsManager)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase10Button(this.exhibitionCenter, this.actor.getUser()));
        } else if (this.actor instanceof Organizer) {
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase2Button(this.exhibitionCenter, (Organizer)this.actor));
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase3Button(this.exhibitionCenter, (Organizer)this.actor));
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase8Button(this.exhibitionCenter, (Organizer)this.actor));
            buttonsPanelTop.add(UseCaseButtonsGenerator.generateUseCase15Button(this.exhibitionCenter, (Organizer)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase17Button(this.exhibitionCenter, (Organizer)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase20Button(this.exhibitionCenter, (Organizer)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase21Button(this.exhibitionCenter, (Organizer)this.actor));
            buttonsPanelBottom.add(UseCaseButtonsGenerator.generateUseCase10Button(this.exhibitionCenter, this.actor.getUser()));
        }

        buttonsPanel.add(buttonsPanelTop);
        buttonsPanel.add(buttonsPanelBottom);

        return buttonsPanel;
    }

    private void createComponents() {
        JPanel componentsPanel = new JPanel();

        // TODO
        add(componentsPanel);
    }

    public static void main(String[] args) {
        ExhibitionCenter exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        Actor actor = exhibitionCenter.getExhibitionsManagerRegister().getExhibitionsManagerList().get(0); // ExhibitionsManager
        //Actor actor = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0); // Organizer
        //Actor actor = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0).getExhibitor().getExhibitorResponsible(); // ExhibitorResponsible
        //Actor actor = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getStaffList().getStaffList().get(0); // StaffMember
        new DashboardUI(exhibitionCenter, actor);
    }
}
