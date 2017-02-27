package ua.dp.levelup.addressbook.entity;

/**
 * Created by java on 10.01.2017.
 */
public class Citizen extends Entity
{
    private String firstName;
    private String lastName;
    private int age;
    private long streetId;

    public Citizen(String firstName, String lastName, int age, long streetId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.streetId = streetId;
    }

    public Citizen(Long id, String firstName, String lastName, int age, long streetId)
    {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.streetId = streetId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public long getStreetId()
    {
        return streetId;
    }

    public void setStreetId(long streetId)
    {
        this.streetId = streetId;
    }

    @Override
    public String toString()
    {
        return "Citizen{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", streetId=" + streetId +
                '}';
    }
}
