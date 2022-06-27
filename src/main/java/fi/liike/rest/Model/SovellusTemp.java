package fi.liike.rest.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity @IdClass(SovellusTempID.class)
@Table(name = "SOVELLUS_TEMP")
public class SovellusTemp implements Serializable, Cloneable {
    // TODO: move
    @Entity
    @Table(name = "TEST_TABLE")
    public static class TestObj implements Serializable, Cloneable {
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
        @SequenceGenerator(name = "seq_gen", sequenceName = "seq_TEST_TABLE")
        private Integer id;

        @Column(name = "OBJECT_ID")
        private String objectID;

        @Column(name = "NAME")
        private String name;

        @Column(name = "POISTUNUT")
        private Integer inactive;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setInactive(Integer inactive) {
            this.inactive = inactive;
        }

        public Integer getInactive() {
            return inactive;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        @Override
        public String toString() {
            return "TestObj{" +
                    "id=" + id +
                    ", objectID='" + objectID + '\'' +
                    ", name='" + name + '\'' +
                    ", inactive=" + inactive +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestObj testObj = (TestObj) o;

            if (id != null ? !id.equals(testObj.id) : testObj.id != null) return false;
            if (objectID != null ? !objectID.equals(testObj.objectID) : testObj.objectID != null) return false;
            if (name != null ? !name.equals(testObj.name) : testObj.name != null) return false;
            return inactive != null ? inactive.equals(testObj.inactive) : testObj.inactive == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (objectID != null ? objectID.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (inactive != null ? inactive.hashCode() : 0);
            return result;
        }
    }

    @Entity
    @Table(name = "TEST_TABLE_TEMP")
    public static class TestObjTemp implements Serializable, Cloneable {
        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
        @SequenceGenerator(name = "seq_gen", sequenceName = "seq_TEST_TABLE_TEMP")
        private Integer id;

        @Column(name = "OBJECT_ID")
        private String objectID;

        @Column(name = "NAME")
        private String name;

        @Column(name = "POISTUNUT")
        private Integer inactive;


        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getInactive() {
            return inactive;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        @Override
        public String toString() {
            return "TestObjTemp{" +
                    "id=" + id +
                    ", objectID='" + objectID + '\'' +
                    ", name='" + name + '\'' +
                    ", inactive=" + inactive +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestObjTemp testObj = (TestObjTemp) o;

            if (id != null ? !id.equals(testObj.id) : testObj.id != null) return false;
            if (objectID != null ? !objectID.equals(testObj.objectID) : testObj.objectID != null) return false;
            if (name != null ? !name.equals(testObj.name) : testObj.name != null) return false;
            return inactive != null ? inactive.equals(testObj.inactive) : testObj.inactive == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (objectID != null ? objectID.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (inactive != null ? inactive.hashCode() : 0);
            return result;
        }
    }
}
