package ua.app.model;

import javax.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long created;
    @Column
    private long updated;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column
    private String email;
    @Column
    private String phone;
    @Column(name = "is_active")
    private Boolean isActive;

    public Customer(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Customer(long created, long updated, String fullName, String email, String phone, boolean isActive) {
        this.created = created;
        this.updated = updated;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (created != customer.created) return false;
        if (updated != customer.updated) return false;
        if (isActive != customer.isActive) return false;
        if (!fullName.equals(customer.fullName)) return false;
        if (!email.equals(customer.email)) return false;
        return phone.equals(customer.phone);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (created ^ (created >>> 32));
        result = 31 * result + (int) (updated ^ (updated >>> 32));
        result = 31 * result + fullName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
