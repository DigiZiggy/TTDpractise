package tdd.greeting;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GreeterTest {

    private Greeter greeter;

    @Before
    public void setUp() {
        //Arrange
        greeter = new Greeter();
    }

    @Test
    public void should_return_hello_name_when_one_name_is_given() {
        List<String> names = Collections.singletonList("Bob");

        //Act
        String greeting = greeter.greet(names);

        //Assert
        String resultString = "Hello, ";
        for (String name : names) {
            resultString = resultString.concat(name + ".");
        }
        assertEquals(resultString, greeting);
    }

    @Test
    public void should_return_hello_name_when_name_list_is_empty() {
        List<String> name = Collections.singletonList("");

        //Act
        String greeting = greeter.greet(name);

        //Assert
        assertEquals("Hello, my friend.", greeting);
    }

    @Test
    public void should_return_hello_name_when_name_in_list_uppercase() {
        List<String> names = Collections.singletonList("LARRY");

        //Act
        String greeting = greeter.greet(names);

        //Assert
        String resultString = "HELLO, ";
        for (String name : names) {
            resultString = resultString.concat(name);
        }
        assertEquals(resultString + "!", greeting);
    }

    @Test
    public void should_return_hello_name_when_all_names_in_list_uppercase() {
        List<String> names = Arrays.asList("LARRY", "JAMES", "MILES");

        //Act
        String greeting = greeter.greet(names);

        //Assert
        assertEquals("HELLO, LARRY, JAMES AND MILES!", greeting);
    }

    @Test
    public void should_return_hello_name_when_some_names_in_list_uppercase() {
        List<String> names = Arrays.asList("Amy", "BRIAN", "Charlotte");

        //Act
        String greeting = greeter.greet(names);

        //Assert
        assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN!", greeting);
    }

    @Test
    public void should_return_hello_name_when_two_names_in_list() {
        List<String> name = Arrays.asList("Jill", "Jane");

        //Act
        String greeting = greeter.greet(name);

        //Assert
        assertEquals("Hello, Jill and Jane.", greeting);
    }

    @Test
    public void should_return_hello_name_when_multiple_names_in_list() {
        List<String> name = Arrays.asList("Amy", "Brian", "Charlotte");

        //Act
        String greeting = greeter.greet(name);

        //Assert
        assertEquals("Hello, Amy, Brian and Charlotte.", greeting);
    }

    @Test
    public void should_return_hello_name_when_one_name_contains_many_separated_by_comma() {
        List<String> name = Arrays.asList("Bob", "Charlie, Dianne");

        //Act
        String greeting = greeter.greet(name);

        //Assert
        assertEquals("Hello, Bob, Charlie and Dianne.", greeting);
    }

    @Test
    public void should_return_hello_name_when_name_contains_intentional_escape_commas() {
        List<String> name = Arrays.asList("Bob", "\"Charlie, Dianne\"");

        //Act
        String greeting = greeter.greet(name);

        //Assert
        assertEquals("Hello, Bob, Charlie and Dianne.", greeting);
    }
}
