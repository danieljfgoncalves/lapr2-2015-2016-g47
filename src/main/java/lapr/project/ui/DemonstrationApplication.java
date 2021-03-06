/**
 * Package location for UI concepts.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import lapr.project.controller.CreateDemonstrationApplicationController;
import lapr.project.model.Demonstration;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Keyword;
import lapr.project.model.Product;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.ui.components.ModelTableDemonstrationsList;
import lapr.project.utils.DefaultInstantiator;

/**
 * GUI for demonstration applications
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DemonstrationApplication extends JFrame {

    /**
     * The exhibition selected.
     */
    private Exhibition selectedExhibition;
    /**
     * The selected demonstration.
     */
    private Demonstration selectedDemonstration;
    /**
     * The list of exhibitions.
     */
    private final List<Exhibition> exhibitionList;
    /**
     * The list of demonstrations.
     */
    private List<Demonstration> demonstrationsList;
    /**
     * The exhibition application controller.
     */
    private final CreateDemonstrationApplicationController demonstrationApplicationController;
    /**
     * Exhibition Center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitor responsible logged in.
     */
    private final ExhibitorResponsible exhibitorResponsible;

    /**
     * the product list.
     */
    private List<Product> productsList;
    /**
     * the keywords list
     */
    private List<Keyword> keywordList;
    /**
     * JList to insert products.
     */
    private JList jListProduct;
    /**
     * JList to insert keywords.
     */
    private JList jListKeyword;
    /**
     * JTable for demonstrations.
     */
    private JTable demonstrationsListJTable;
    /**
     * Remove product button.
     */
    private JButton buttonRemoveProduct;
    /**
     * button to add keyword.
     */
    private JButton buttonAddKeyword;
    /**
     * Text field for company name.
     */
    private JTextField txtCompanyName;
    /**
     * Text field for address.
     */
    private JTextArea txtAddress;
    /**
     * Text field for cellphone.
     */
    private JTextField txtCellphone;
    /**
     * Text field for exhibitor area.
     */
    private JTextField txtExhibitorArea;
    /**
     * Text field for invites number.
     */
    private JTextField txtNumberInvites;
    /**
     * Text field for title.
     */
    private JTextField txtTitle;

    /**
     * Label size.
     */
    final Dimension LBL_SIZE = new Dimension(94, 16);
    /**
     * Window size.
     */
    final Dimension WINDOW_SIZE = new Dimension(1500, 600);
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
    /**
     * The model for the demonstrations list.
     */
    private ModelTableDemonstrationsList modelDemonstration;

    private static final String WINDOW_TITLE = "Demonstration application";

    /**
     * Constructs instance of this class.
     *
     * @param exhibitorResponsible the exhibitor responsible logged in
     * @param exhibitionCenter the exhibition center
     */
    public DemonstrationApplication(ExhibitorResponsible exhibitorResponsible, ExhibitionCenter exhibitionCenter) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
        this.demonstrationApplicationController = new CreateDemonstrationApplicationController(exhibitorResponsible, exhibitionCenter);
        this.exhibitionList = demonstrationApplicationController.getExhibitionListWithApplicationsInSubmission();

        DialogSelectable dialogSelectable = new DialogSelectable(this, this.exhibitionList);
        this.selectedExhibition = (Exhibition) dialogSelectable.getSelectedItem();

        if (this.selectedExhibition != null) {

            this.demonstrationsList = demonstrationApplicationController.getDemonstrationsList(selectedExhibition);
            dialogSelectable = new DialogSelectable(this, demonstrationsList);
            this.selectedDemonstration = (Demonstration) dialogSelectable.getSelectedItem();
            if (this.selectedDemonstration != null) {
                demonstrationApplicationController.newDemonstrationApplication(selectedDemonstration);
                this.setLayout(new GridLayout(1, 3));
                createComponents();
                CustomMenuBar customMenuBar = new CustomMenuBar(this.exhibitionCenter, this);
                setJMenuBar(customMenuBar);
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        customMenuBar.exit();
                    }
                });

                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                pack();
                setSize(WINDOW_SIZE);
                setMinimumSize(new Dimension(getWidth(), getHeight()));
                setLocationRelativeTo(null);
                setVisible(true);
            } else {
                dispose();
                new DashboardUI(exhibitionCenter, exhibitorResponsible);
            }
        } else {
            dispose();
            new DashboardUI(exhibitionCenter, exhibitorResponsible);
        }
    }

    /**
     * Creates window components.
     */
    private void createComponents() {

        add(createDataPanel());
        add(createPanelProduct());
        add(createPanelKeywordt());
        //add(createPanelDemonstrations());
    }

    /**
     * Returns data panel.
     *
     * @return the panel to write data
     */
    private JPanel createDataPanel() {

        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 10));

        JLabel lblTitle = new JLabel("Title:", JLabel.RIGHT);
        lblTitle.setSize(LBL_SIZE);
        this.txtTitle = new JTextField(FIELD_TXT_WIDTH);
        JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitle.setBorder(new EmptyBorder(MARGIN_S_FIELD, MARGIN_E_FIELD,
                MARGIN_I_FIELD, MARGIN_D_FIELD));
        panelTitle.add(lblTitle);
        panelTitle.add(txtTitle);

        JLabel lblCompanyName = new JLabel("Company name:", JLabel.RIGHT);
        lblCompanyName.setSize(LBL_SIZE);
        this.txtCompanyName = new JTextField(FIELD_TXT_WIDTH);
        this.txtCompanyName.setText(demonstrationApplicationController.getApplicationOfExhibitorResponsible().getExhibitor().getName());
        this.txtCompanyName.setEditable(false);
        JPanel panelCompanyName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCompanyName.setBorder(new EmptyBorder(MARGIN_S_FIELD, MARGIN_E_FIELD,
                MARGIN_I_FIELD, MARGIN_D_FIELD));
        panelCompanyName.add(lblCompanyName);
        panelCompanyName.add(txtCompanyName);

        JLabel lblCellphone = new JLabel("Cellphone: ", JLabel.RIGHT);
        lblCellphone.setSize(LBL_SIZE);
        this.txtCellphone = new JTextField(FIELD_TXT_WIDTH);
        this.txtCellphone.setText(demonstrationApplicationController.getApplicationOfExhibitorResponsible().getExhibitor().getMobileNumber());
        this.txtCellphone.setEditable(false);
        JPanel panelCellphone = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCellphone.setBorder(new EmptyBorder(MARGIN_S_FIELD, MARGIN_E_FIELD,
                MARGIN_I_FIELD, MARGIN_D_FIELD));
        panelCellphone.add(lblCellphone);
        panelCellphone.add(txtCellphone);

        JLabel lblExhibitorArea = new JLabel("Exhibitor's area: ", JLabel.RIGHT);
        lblExhibitorArea.setSize(LBL_SIZE);
        this.txtExhibitorArea = new JTextField(FIELD_TXT_WIDTH);
        this.txtExhibitorArea.setText(Float.toString(demonstrationApplicationController.getApplicationOfExhibitorResponsible().getArea()));
        this.txtExhibitorArea.setEditable(false);
        JPanel panelExhibitorArea = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelExhibitorArea.setBorder(new EmptyBorder(MARGIN_S_FIELD, MARGIN_E_FIELD,
                MARGIN_I_FIELD, MARGIN_D_FIELD));
        panelExhibitorArea.add(lblExhibitorArea);
        panelExhibitorArea.add(txtExhibitorArea);

        JLabel lblNumberInvites = new JLabel("Number of invitations: ", JLabel.RIGHT);
        lblNumberInvites.setSize(LBL_SIZE);;
        this.txtNumberInvites = new JTextField(FIELD_TXT_WIDTH);
        JPanel panelNumberInvites = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNumberInvites.setBorder(new EmptyBorder(MARGIN_S_FIELD, MARGIN_E_FIELD,
                MARGIN_I_FIELD, MARGIN_D_FIELD));
        panelNumberInvites.add(lblNumberInvites);
        panelNumberInvites.add(txtNumberInvites);
        dataPanel.add(panelTitle);
        dataPanel.add(panelCompanyName);
        dataPanel.add(createAddressPanel());
        dataPanel.add(panelCellphone);
        dataPanel.add(panelExhibitorArea);
        dataPanel.add(panelNumberInvites);

        return dataPanel;
    }

    /**
     * Returns panel to handle products
     *
     * @return panel that handles products.«
     */
    private JPanel createPanelProduct() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(PADDING_BORDER);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(createButtonAddProduct());
        panelButton.add(createButtonRemoveProduct());

        panel.add(createScrollPaneProducts(), BorderLayout.NORTH);
        panel.add(panelButton, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates panel for keywords
     *
     * @return panel for keywords
     */
    private JPanel createPanelKeywordt() {

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBorder(PADDING_BORDER);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButton.add(createButtonAddKeyword());

        panel2.add(createScrollPaneKeyWords(), BorderLayout.NORTH);
        panel2.add(panelButton, BorderLayout.CENTER);
        panel2.add(createConfirmButtonsPanel(), BorderLayout.SOUTH);
        return panel2;
    }

    /**
     * Returns demonstrations panel.
     *
     * @return demonstrations panel
     */
    private JPanel createPanelDemonstrations() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(PADDING_BORDER);

        panel.add(createScrollPaneDemonstrations(), BorderLayout.NORTH);

        return panel;
    }

    /**
     * Returns demonstrations list scroll pane
     *
     * @return demonstrations list scroll pane
     */
    private JPanel createScrollPaneDemonstrations() {

        JPanel panelScroll = new JPanel(new GridLayout());
        panelScroll.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Select the desired demonstrations:", TitledBorder.LEFT, TitledBorder.TOP));
        modelDemonstration = new ModelTableDemonstrationsList(demonstrationsList);
        demonstrationsListJTable = new JTable(modelDemonstration);
        demonstrationsListJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        demonstrationsListJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //demonstrationApplicationController.newDemonstrationApplication(((Demonstration)modelDemonstration.getValueAt(demonstrationsListJTable.getSelectedRow(), demonstrationsListJTable.getSelectedColumn())));
            }
        });

        JScrollPane scrollPane = new JScrollPane(demonstrationsListJTable);
        scrollPane.setBorder(PADDING_BORDER);

        panelScroll.setMinimumSize(scrollPane.getMinimumSize());
        panelScroll.add(scrollPane);

        return panelScroll;
    }

    /**
     * Returns products list scroll pane
     *
     * @return products list scroll pane
     */
    private JPanel createScrollPaneProducts() {

        JPanel panelScroll = new JPanel(new GridLayout());
        panelScroll.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Products List: ", TitledBorder.LEFT, TitledBorder.TOP));

        this.productsList = this.demonstrationApplicationController.getProductsList();
        this.jListProduct = new JList(new ModelListSelectable(this.productsList));
        JScrollPane scrollPane = new JScrollPane(jListProduct);
        scrollPane.setBorder(PADDING_BORDER);

        panelScroll.add(scrollPane);

        return panelScroll;
    }

    /**
     * Creates the keyword scrollpanel
     *
     * @return a scroll panel
     */
    private JPanel createScrollPaneKeyWords() {

        JPanel panelScroll = new JPanel(new GridLayout());
        panelScroll.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Keywords List ", TitledBorder.LEFT, TitledBorder.TOP));

        this.keywordList = demonstrationApplicationController.getKeywordsList();
        this.jListKeyword = new JList(new ModelListSelectable(keywordList));

        JScrollPane scrollPane = new JScrollPane(jListKeyword);
        scrollPane.setBorder(PADDING_BORDER);

        panelScroll.add(scrollPane);

        return panelScroll;
    }

    /**
     * Creates panel with buttons confirm and cancel
     *
     * @return the panel with the buttons
     */
    private JPanel createConfirmButtonsPanel() {
        JPanel p = new JPanel(new FlowLayout());
        p.add(createConfirmButton());
        p.add(createCancelButton());
     
        return p;
    }

    

    /**
     * Creates confirm button
     *
     * @return the confirm button
     */
    private JButton createConfirmButton() {
        JButton btn = new JButton("Confirm");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtTitle.getText().isEmpty()
                            || txtCompanyName.getText().isEmpty()
                            || txtAddress.getText().isEmpty()
                            || txtCellphone.getText().isEmpty()
                            || txtExhibitorArea.getText().isEmpty()
                            || txtNumberInvites.getText().isEmpty()) {
                        throw new IllegalArgumentException("Some data is missing.");
                    }
                    if (jListProduct.getSelectedIndices().length < 1) {
                        throw new IllegalArgumentException("Please select at least one product.");
                    }
                    if (jListKeyword.getSelectedIndices().length < 2 || jListKeyword.getSelectedIndices().length > 5) {
                        throw new IllegalArgumentException("Please select a minimum of two keywords and a maximum of five.");
                    }

                    Pattern p = Pattern.compile("[a-zA-Z]+");
                    Matcher m = p.matcher(txtCellphone.getText());
                    if (txtCellphone.getText().length() != 9 || m.find()) {
                        throw new IllegalArgumentException("Invalid phone number length or letter inserted.");
                    }

                    String companyName = txtCompanyName.getText();
                    String address = txtAddress.getText();
                    String cellphone = txtCellphone.getText();
                    String title = txtTitle.getText();
                    float exhibitorArea = Float.parseFloat(txtExhibitorArea.getText());
                    int numberInvites = Integer.parseInt(txtNumberInvites.getText());
                    demonstrationApplicationController.setData(title, numberInvites);
                    List<Product> tmpListProduct = jListProduct.getSelectedValuesList();
                    demonstrationApplicationController.setProductsList(tmpListProduct);
                    List<Keyword> tmpKeywordList = jListKeyword.getSelectedValuesList();
                    demonstrationApplicationController.setKeywordsList(tmpKeywordList);

//                    List<Demonstration> selectedDemonstrationsList = getSelectedDemonstrationsList();
//                    exhibitionApplicationController.setDemonstrationsList(selectedDemonstrationsList);
                    String message = "Do you wish to confirm the application?";
                    int confirm = JOptionPane.showConfirmDialog(rootPane, message, "Confirm application", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {

                        if (demonstrationApplicationController.registerDemonstrationApplication()) {
                            message = String.format("Application submitted sucessfully!");
                            confirm = JOptionPane.showConfirmDialog(rootPane, message, "Sucess!", JOptionPane.PLAIN_MESSAGE);
                            new DashboardUI(exhibitionCenter, exhibitorResponsible);
                            dispose();
                        }
                        //new LoginUI(centroExposicoes);
                    }

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            rootPane,
                            ex.getMessage(),
                            "Invalid number.",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IllegalArgumentException ex) {

                    JOptionPane.showMessageDialog(
                            rootPane,
                            ex.getMessage(),
                            "Invalid data.",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        return btn;
    }

    /**
     * Creates cancel button
     *
     * @return the cancel button
     */
    private JButton createCancelButton() {

        JButton btn = new JButton("Cancel");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardUI( exhibitionCenter,exhibitorResponsible);

            }
        });
        return btn;
    }

    /**
     * Returns the selected demonstrations list
     *
     * @return the selected demonstrations list
     */
    private List<Demonstration> getSelectedDemonstrationsList() {
        List<Demonstration> selectedDemonstrationsList = new ArrayList<>();

        for (int row : demonstrationsListJTable.getSelectedRows()) {
            selectedDemonstrationsList.add(demonstrationsList.get(row));
        }

        return selectedDemonstrationsList;
    }

    /**
     * /* Creates address panel.
     *
     * @return the address panel
     */
    private JPanel createAddressPanel() {

        this.txtAddress = new JTextArea(4, FIELD_TXT_WIDTH);
        JLabel lblAddress = new JLabel("Address: ", JLabel.RIGHT);
        JPanel panelAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAddress.setBorder(new EmptyBorder(MARGIN_S_FIELD, MARGIN_E_FIELD,
                MARGIN_I_FIELD, MARGIN_D_FIELD));
        panelAddress.add(lblAddress);
        panelAddress.add(this.txtAddress);

        return panelAddress;
    }

    /**
     * Creates add product button.
     *
     * @return the add product button
     */
    private JButton createButtonAddProduct() {

        JButton btn = new JButton("Add Product");
        btn.setEnabled(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // new NewProductDialog(DemonstrationApplication.this);
            }
        });

        return btn;
    }

    /**
     * creates button to add keyword
     *
     * @return the button
     */
    private JButton createButtonAddKeyword() {

        JButton btn = new JButton("Add Keyword");
        btn.setEnabled(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newKeyword(JOptionPane.showInputDialog("Insert a Keyword"))) {
                    JOptionPane.showMessageDialog(rootPane, "Keyword inserted sucessfully!", "Sucess", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });

        return btn;
    }

    /**
     * Creates remove product button.
     *
     * @return the remove product button
     */
    private JButton createButtonRemoveProduct() {

        this.buttonRemoveProduct = new JButton("Remove product");
        this.buttonRemoveProduct.setEnabled(false);

        this.buttonRemoveProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int index = jListProduct.getSelectedIndex();
                    boolean removedProduct = removeProduct(index);
                    if (removedProduct) {
                        buttonRemoveProduct.setEnabled(false);
                        JOptionPane.showMessageDialog(rootPane,
                                "Product removed sucessfully.!",
                                "Product removal",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (IllegalArgumentException ex) {

                    JOptionPane.showMessageDialog(DemonstrationApplication.this,
                            ex.getMessage(),
                            "Erro a remover produto",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        return this.buttonRemoveProduct;
    }

    /**
     * Creates a new product
     *
     * @param designation the product designation
     * @return a new product
     */
    public boolean newProduct(String designation) {
        boolean addedProduct = false;
        updateProductsList();

        return addedProduct;

    }

    /**
     * Creates a new keyword
     *
     * @param designation the keyword designation
     * @return a new keyword
     */
    public boolean newKeyword(String designation) {
        boolean addedKeyword = false;
        updateKeywordsList();

        return addedKeyword;

    }

    /**
     * Removes a product.
     *
     * @param index the prodcut index on the list
     * @return true if removed
     */
    public boolean removeProduct(int index) {
        String designation = (String) this.jListProduct.getModel().getElementAt(index);

        boolean productRemoved = false;
        updateProductsList();
        return productRemoved;
    }

    /**
     * Refresh the products list.
     */
    private void updateProductsList() {
        this.productsList = demonstrationApplicationController.getProductsList();
        this.jListProduct.setModel(new ModelListSelectable(this.productsList));
    }

    /**
     * Refresh the keywords list.
     */
    private void updateKeywordsList() {
        this.keywordList = demonstrationApplicationController.getKeywordsList();
        this.jListKeyword.setModel(new ModelListSelectable(this.keywordList));
    }

    /**
     * Modifies the selected exhibition
     *
     * @param exhibition the new exhibition to set
     */
    public void setExhibition(Exhibition exhibition) {

        selectedExhibition = exhibition;
    }

    public static void main(String[] args) {
        ExhibitionCenter ex = DefaultInstantiator.createExhibitionCenter();
        ex.getExhibitionsRegister().getExhibitionsList().get(0).setState(new ExhibitionDecidedApplicationsState(ex.getExhibitionsRegister().getExhibitionsList().get(0)));
        ((ExhibitionApplication) ex.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)).getDemonstrationsList().add(new Demonstration());
        ExhibitorResponsible exR = ((ExhibitionApplication) ex.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)).getExhibitorResponsible();
        DemonstrationApplication test = new DemonstrationApplication(exR, ex);
    }
}
