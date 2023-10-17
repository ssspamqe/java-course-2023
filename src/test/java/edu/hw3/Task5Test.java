package edu.hw3;

import edu.hw3.Task5.Person;
import edu.hw3.Task5.Task5;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    Task5 task = new Task5();

    @Test
    @DisplayName("Method should sort persons by their last names in ascending order")
    void check_sortByLastName_asc() {
        String[] contacts = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};

        List<Person> sortedPersons = task.parseContacts(contacts, "ASC");

        assertThat(sortedPersons)
            .isEqualTo(List.of(
                new Person("Thomas Aquinas"),
                new Person("Rene Descartes"),
                new Person("David Hume"),
                new Person("John Locke")
            ));
    }

    @Test
    @DisplayName("Method should sort persons by their last names in descending order")
    void check_sortByLastName_desc() {
        String[] contacts = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};

        List<Person> sortedPersons = task.parseContacts(contacts, "ASC");

        assertThat(sortedPersons)
            .isEqualTo(List.of(
                new Person("Thomas Aquinas"),
                new Person("Rene Descartes"),
                new Person("David Hume"),
                new Person("John Locke")
            ));
    }

    @Test
    @DisplayName("Method should sort persons by their names, if no last name in descending order")
    void check_sortByName_asc() {
        String[] contacts = {"John Locke", "Thomas", "David Hume", "Rene Descartes"};

        List<Person> sortedPersons = task.parseContacts(contacts, "ASC");

        assertThat(sortedPersons)
            .isEqualTo(List.of(
                new Person("Rene Descartes"),
                new Person("David Hume"),
                new Person("John Locke"),
                new Person("Thomas")
            ));
    }

    @Test
    @DisplayName("Method should throw an exception if passed wrong order mode")
    void check_wrongOrderMode() {
        String[] contacts = {"John Locke", "Thomas", "David Hume", "Rene Descartes"};

        assertThatThrownBy(() -> task.parseContacts(contacts, "str")).isInstanceOf(IllegalArgumentException.class);
    }

}
