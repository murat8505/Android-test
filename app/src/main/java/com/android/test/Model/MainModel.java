
package com.android.test.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class MainModel implements Serializable{

    @SerializedName("first_name")
    private String FirstName;
    @SerializedName("email")
    private String Email;
    @SerializedName("gender")
    private String Gender;
    @SerializedName("id")
    private Long Id;
    @SerializedName("ip_address")
    private String IpAddress;
    @SerializedName("last_name")
    private String LastName;
    @SerializedName("photo")
    private String Photo;

    private String Name;
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    @SerializedName("employment")
    private Employment Employment;

    public Employment getEmployment() {
        return Employment;
    }

    public void setEmployment(Employment employment) {
        Employment = employment;
    }

    public String getName() {
        return FirstName+" "+LastName;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public class Employment {

        @SerializedName("name")
        private String Name;
        @SerializedName("position")
        private String Position;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getPosition() {
            return Position;
        }

        public void setPosition(String position) {
            Position = position;
        }

        public String getText() {
            return Name+", "+Position;
        }

    }

}
