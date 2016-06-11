/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.utils.Exportable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Represents a user.
 *
 * @author Daniel Goncalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
public class User  {

    /**
     * The user's name.
     */
    private String name;

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's email.
     */
    private String email;

    /**
     * The user's password
     */
    // TODO: Implement password encryptation when stored.
    private String password;

    /**
     * The user's name by default.
     */
    private static final String DEFAULT_NAME = "no name";

    /**
     * The user's username by default.
     */
    private static final String DEFAULT_USERNAME = "username";

    /**
     * The user's email by default.
     */
    private static final String DEFAULT_EMAIL = "me@email.com";

    /**
     * The user's password by default.
     */
    private static final String DEFAULT_PASSWORD = "password";
    /**
     * Class name for exportable.
     */
    private static final String ROOT_ELEMENT_NAME = "User";
    /**
     * User name html tag.
     */
    private static final String NAME_ELEMENT_NAME = "Name";
    /**
     * Email html tag.
     */
    private static final String EMAIL_ELEMENT_NAME = "Email";
    /**
     * Username html tag.
     */
    private static final String USERNAME_ELEMENT_NAME = "Username";
    /**
     * Password html tag.
     */
    private static final String PASSWORD_ELEMENT_NAME = "Password";

    /**
     * Default constructor of a user class.
     */
    public User() {
        this.name = DEFAULT_NAME;
        this.username = DEFAULT_USERNAME;
        this.email = DEFAULT_EMAIL;
        this.password = DEFAULT_PASSWORD;
    }

    /**
     * Constructor of a user class.
     *
     * @param name user's name
     * @param username user's username
     * @param email user's email
     * @param password user's password
     */
    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Copy constructor of a UsersRegister class.
     *
     * @param user User to copy
     */
    public User(User user) {
        this.name = user.name;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
    }

    /**
     * Obtain the user's name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user's name.
     *
     * @param name the user's name to set
     */
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtain the user's username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user's username.
     *
     * @param username the user's username to set
     */
    @XmlAttribute
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtain the user's email.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email.
     *
     * @param email the user's email to set
     */
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtain the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's name.
     *
     * @param password the user's password to set
     */
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Validates the user.
     *
     * @return true if the user is valid, false otherwise
     */
    boolean validate() {
        return !this.username.isEmpty() && this.username.length() > 3 && !this.email.isEmpty() && this.email.length() > 5;
    }

    /**
     * Return the textual representation of a user.
     *
     * @return the textual representation of a user
     */
    @Override
    public String toString() {
        return String.format("User{%nname=%s%nusername=%s%nemail=%s}", this.name, this.username, this.email);
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it represents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        User otherUser = (User) otherObject;

        return this.username.equals(otherUser.username) || this.email.equals(otherUser.email);
    }

    


}
