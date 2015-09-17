package net.nortlam.study;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="Person")
@Table(name="PERSON")
@XmlRootElement(name="Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PERSON_ID")
    @XmlAttribute(name = "ID", required = true)
    private long ID;
    
    public static final int LENGTH_FIRST_NAME = 35;
    @Column(name="FIRST_NAME", nullable = false, length = LENGTH_FIRST_NAME)
    @XmlElement(name="FirstName", type=String.class, required=true)
    private String firstName;
    
    public static final int LENGTH_LAST_NAME = 35;
    @Column(name="LAST_NAME", nullable = false, length = LENGTH_LAST_NAME)
    @XmlElement(name="LastName", type=String.class, required=true)
    private String lastName;

    public Person() {
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<Person ID=\"").append(ID).append("\">");
        buffer.append("<FirstName>").append(firstName != null ? firstName : "NULL")
                .append("</FirstName>");
        buffer.append("<LastName>").append(lastName != null ? lastName : "NULL")
                .append("</LastName>");
        buffer.append("</Person>");
        
        return buffer.toString();
    }
}
