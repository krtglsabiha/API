package get_requests;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersMethods {

    @Test
    public  void methods() {


    /*
    Core Matchers:

    is(value):            Matches if the examined object is equal to the specified value.
    equalTo(value):       Same as is(value).
    not(value):           Matches if the examined object is not equal to the specified value.
    nullValue():          Matches if the examined object is null.
    notNullValue():       Matches if the examined object is not null.

    Logical Matchers:

    allOf(matcher1, matcher2, ...):     Matches if all of the specified matchers match.
    anyOf(matcher1, matcher2, ...):     Matches if any of the specified matchers match.
    not(matcher):                       Matches if the specified matcher does not match.

    Text Matchers:

    containsString(substring):          Matches if the examined string contains the specified substring.
    startsWith(prefix):                 Matches if the examined string starts with the specified prefix.
    endsWith(suffix):                   Matches if the examined string ends with the specified suffix.

    Number Matchers:

    closeTo(expected, delta):           Matches if the examined value is within a specified delta of the expected value.
    greaterThan(value), greaterThanOrEqualTo(value): Matches if the examined value is greater than or equal to the specified value.
    lessThan(value), lessThanOrEqualTo(value):       Matches if the examined value is less than or equal to the specified value.

    Collection Matchers:

    hasItem(value):                 Matches if the examined iterable has at least one item that is equal to the specified value.
    hasItems(value1, value2, ...):  Matches if the examined iterable has at least one item that matches each specified value.
    hasSize(matcher):               Checks size of matchers in the collection
    Object Matchers:

    equalToIgnoringCase(string):    Matches if the examined string is equal to the specified string, ignoring case.
    sameInstance(expected):         Matches if the examined object is the same instance as the specified object.
    hasProperty(propertyName):      Matches if the examined object has a JavaBean property with the specified name.

        These are just some of the commonly used Hamcrest matchers and methods. Hamcrest provides a rich set of matchers, allowing you to create expressive and readable assertions in your tests.

     */

        int actual = 42;
        int expected = 42;

// Both of these assertions are equivalent
        assertThat(actual, is(expected));
        assertThat(actual, equalTo(expected));

        String actual1 = "Hello";
        String unexpected = "World";

// Assert that actual is not equal to "World"
        assertThat(actual1, not(unexpected));

        Object nullObject = null;
        Object nonNullObject = new Object();

// Assert that the object is null
        assertThat(nullObject, nullValue());

// Assert that the object is not null
        assertThat(nonNullObject, notNullValue());

        String actual4 = "Hello";

// Assert that actual is either equal to "Hello" or is an integer
        assertThat(actual4, anyOf(equalTo("Hello"), is(Integer.class)));

// Assert that actual is not equal to 43
        assertThat(actual, not(equalTo(43)));

        String sentence = "This is a sentence.";

// Assert that the sentence contains the word "is"
        assertThat(sentence, containsString("is"));

// Assert that the sentence starts with "This"
        assertThat(sentence, startsWith("This"));

// Assert that the sentence ends with "sentence."
        assertThat(sentence, endsWith("sentence."));

        String actualStr = "hello";
        String expectedStr = "HELLO";

// Assert that actual is equal to "HELLO" ignoring case
        assertThat(actualStr, equalToIgnoringCase(expectedStr));

        double actual3 = 3.0;
        double expected3 = 3.2;
        double delta = 0.3;

// Assert that actual is close to 3.2 with a delta of 0.3
        assertThat(actual3, closeTo(expected3, delta));

// Assert that actual is greater than 4
        assertThat(actual, greaterThan(41));

// Assert that actual is greater than or equal to 5
        assertThat(actual, greaterThanOrEqualTo(42));

// Assert that actual is less than 6
        assertThat(actual, lessThan(46));

// Assert that actual is less than or equal to 5
        assertThat(actual, lessThanOrEqualTo(45));

        List<String> names = Arrays.asList("John", "Doe", "Jane");

// Assert that the list has the item "Doe"
        assertThat(names, hasItem("Doe"));

// Assert that the list has both "John" and "Jane"
        assertThat(names, hasItems("John", "Jane"));



        class Person {
            private String name;

            public Person(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        Person person = new Person("John");

// Assert that the person object has a property named "name"
        assertThat(person, hasProperty("name"));

    }

}