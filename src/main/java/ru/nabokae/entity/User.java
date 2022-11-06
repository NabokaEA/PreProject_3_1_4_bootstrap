package ru.nabokae.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size (min=2, max=30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "lastname")
    @NotEmpty(message = "LastName should not be empty")
    private String lastName;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @Column(name="email")
    @NotEmpty(message = "Email should not be empty")
    private String email;
    
    @Column(name="age")
    @Min(value = 0, message = "Age should be greate than 0")
    private int age;
    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="User_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="Roles_id", referencedColumnName="id")})
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
