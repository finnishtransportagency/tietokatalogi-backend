package fi.liike.rest;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import fi.liike.externalApi.ExternalSovellusController;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExternalSovellusRestTest {
    private ExternalSovellusController rest;

    @Before
    public void setUp() {
        this.rest = new ExternalSovellusController();
    }

    @Test
    public void mapCSVToListTest() throws IOException {


        String name = "sdjfkl";
        String field1 = "fdsfsdf";
        String field2 = "sdfsdf";
        String objectID = "{abc-123}";

        String csv = format("\"%s\",\"%s\",\"%s\",\"%s\"\n\"%s\",\"%s\",\"%s\",\"%s\"",
                "name", "field1", "field2", "objectID",
                name, field1, field2, objectID);
        List<TestClass> testClassList = rest.mapCSVToList(csv, TestClass.class, TestClass.csvSchema);
        assertThat(testClassList.size(), is(1));
        TestClass testClass = testClassList.get(0);

        assertThat(testClass.name, is(name));
        assertThat(testClass.field1, is(field1));
        assertThat(testClass.field2, is(field2));
        assertThat(testClass.objectID, is(objectID));
    }

    static class TestClass {
        String name;
        String field1;
        String field2;
        String objectID;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        static final CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("name")
                .addColumn("field1")
                .addColumn("field2")
                .addColumn("objectID")
                .build();

        @Override
        public String toString() {
            return "TestClass{" +
                    "name='" + name + '\'' +
                    ", field1='" + field1 + '\'' +
                    ", field2='" + field2 + '\'' +
                    ", objectID='" + objectID + '\'' +
                    '}';
        }
    }
}
