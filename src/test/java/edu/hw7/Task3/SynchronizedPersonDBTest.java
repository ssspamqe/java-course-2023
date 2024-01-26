package edu.hw7.Task3;

import edu.hw7.Task3.sleepy.SleepySynchronizedPersonDB;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SynchronizedPersonDBTest {

    PersonDB personDAO;

    @BeforeEach
    void initPersonDAO() {
        personDAO = new SynchronizedPersonDB();
    }

    @Test
    @DisplayName("SynchronizedPersonDB should be able to return a list of persons with specified name")
    void synchronizedPersonDB_should_returnListOfPersonsWithSameName() {
        String name1 = "name1";
        String name2 = "name2";

        Person person1WithName1 = new Person(1, name1, "", "1");
        Person person2WithName1 = new Person(2, name1, "", "2");
        Person person3WithName2 = new Person(3, name2, "", "3");


        personDAO.add(person1WithName1);
        personDAO.add(person2WithName1);
        personDAO.add(person3WithName2);
        List<Person> returnedList = personDAO.findByName(name1);


        assertThat(returnedList).containsExactlyInAnyOrder(person1WithName1, person2WithName1);
    }

    @Test
    @DisplayName("SynchronizedPersonDB should be able to return a list of persons with specified")
    void synchronizedPersonDB_should_returnListOfPersonsWithSameAddresses() {
        String address1 = "address1";
        String address2 = "address2";

        Person person1WithAddress1 = new Person(1, "", address1, "1");
        Person person2WithAddress1 = new Person(2, "", address1, "2");
        Person person3WithAddress2 = new Person(3, "", address2, "3");

        personDAO.add(person1WithAddress1);
        personDAO.add(person2WithAddress1);
        personDAO.add(person3WithAddress2);
        List<Person> returnedList = personDAO.findByAddress(address1);

        assertThat(returnedList).containsExactlyInAnyOrder(person1WithAddress1, person2WithAddress1);
    }

    @Test
    @DisplayName("SynchronizedPersonDB should be able to return a person with specified phone")
    void synchronizedPersonDB_should_returnListOfPersonsWithSpecifiedPhone() {
        String phone1 = "phone1";
        String phone2 = "phone2";

        Person personWithPhone1 = new Person(1, "", "", phone1);
        Person personWithPhone2 = new Person(2, "", "", phone2);

        personDAO.add(personWithPhone1);
        personDAO.add(personWithPhone2);
        Person returnedPerson = personDAO.findByPhone(phone1);

        assertThat(returnedPerson).isEqualTo(personWithPhone1);
    }

    @Test
    @DisplayName("SynchronizedPersonDB should be able to delete person by id")
    void synchronizedPersonDB_should_beAble_to_deletePersonById() {
        int id = 0;
        String phone = "121";
        Person person = new Person(id, "", "", phone);

        personDAO.add(person);
        personDAO.delete(id);
        Person returnedPerson = personDAO.findByPhone(phone);

        assertThat(returnedPerson).isNull();
    }

    @Test
    @Disabled
    @DisplayName("SynchronizedPersonDB should store Persons and work synchronizely")
    void synchronizedPersonDB_should_workSynchronizely() throws Exception {
        personDAO = new SleepySynchronizedPersonDB();
        String name = "default_name";

        Person person = new Person(0, name, "default_address", "default_phone");
        List<Person> returnedPersonList = new ArrayList<>();
        var writingThread = new Thread(() -> personDAO.add(person));
        var readingThread = new Thread(() -> returnedPersonList.addAll(personDAO.findByName(name)));

        writingThread.start();
        readingThread.start();

        writingThread.join();
        readingThread.join();

        Person returnedPerson = returnedPersonList.get(0);

        assertThat(returnedPerson).isEqualTo(person);
    }

}
