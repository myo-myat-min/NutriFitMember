/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.time.LocalDate;

public class Member {

    private String id;
    private String name;
    private String password;
    private LocalDate dob;
    private String gender; /**********/
    private int  weight ;
    private int  height;
    private  String email;
    private String nrc;
    private String ph;//int
    private String address;
    private String medical_data;
    
//    {
//        if(gender){
//            gender = Boolean.parseBoolean("Male");
//        } else {
//            gender = Boolean.parseBoolean("Female");
//        }
//    }

    public Member(String id, String name, String password,LocalDate dob, String gender, int weight, int height, String email, String nrc, String ph, String address, String medical_data) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.nrc = nrc;
        this.ph = ph;
        this.address = address;
        this.medical_data = medical_data;
        
    }

    public Member(String name, String password, LocalDate dob, String gender, int weight, int height, String email, String nrc, String ph, String address, String medical_data) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.nrc = nrc;
        this.ph = ph;
        this.address = address;
        this.medical_data = medical_data;
        
    }

    public Member( String name, String nrc) {
  
        this.name = name;
        this.nrc = nrc;
        
    }

    public Member(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    
    
    
    public Member() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int  getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getMedical_data() {
        return medical_data;
    }

    public void setMedical_data(String medical_data) {
        this.medical_data = medical_data;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", name=" + name + ", password=" + password + ", dob=" + dob + ", gender=" + gender + ", weight=" + weight + ", height=" + height + ", email=" + email + ", nrc=" + nrc + ", ph=" + ph + ", address=" + address + ", medical_data=" + medical_data + '}';
    }


  
    
}
