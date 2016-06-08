/**
 * Package location for Model concepts.
 */
package lapr.project.model;

/**
 * Represents a Staff Member.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Amaral 1151231
 */
public class StaffMember {

    /**
     * The user information associated to this Staff Member.
     */
    private User user;

    /**
     * Default constructor of a Staff Member class.
     */
    public StaffMember() {
        this.user = new User();
    }

    /**
     * Constructor of a Staff Member class.
     *
     * @param user the user associated to the staff member
     */
    public StaffMember(User user) {
        this.user = user;
    }

    /**
     * Copy constructor of a Staff Member class.
     *
     * @param staffMember the Staff Member to copy
     */
    public StaffMember(StaffMember staffMember) {
        this.user = staffMember.user;
    }

    /**
     * Obtain the user associated to the staff member.
     *
     * @return the user associated to the staff member
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user associated to the staff member.
     *
     * @param user the user associated to the staff member to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the textual representation of a staff member.
     *
     * @return the textual representation of a staff member
     */
    @Override
    public String toString() {
        return String.format("StaffMember{%nuser=%s}", this.user);
    }
}