package com.makoval.androidphonebook.Users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.makoval.androidphonebook.PhoneNumber.PhoneNumber;

import java.util.Map;

/**
 * Базовый класс пользователя
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PhysicalPerson.class),
        @JsonSubTypes.Type(value = LegalPerson.class),
        @JsonSubTypes.Type(value = Developer.class),
        @JsonSubTypes.Type(value = Manager.class)
})
public abstract class User {

    /**
     * ID Пользователя
     */
    private int id;
    /**
     * Тип пользователя
     */
    private String type;

    /**
     * Телефоонный номер пользователя
     */
    @JsonUnwrapped
    private PhoneNumber phoneNumber;

    /**
     * Адрес пользователя
     */
    private String address;

    /**
     * Конструктор
     *
     * @param type        Тип пользователя
     * @param phoneNumber Телефоонный номер пользователя
     * @param address     Адрес пользователя
     */
    public User(int id, String type, String phoneNumber, String address) {
        this.phoneNumber = new PhoneNumber();
        this.phoneNumber.splitNumber(phoneNumber);
        this.address = address;
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User() {
    }

    /**
     * @return Строка содержащая все поля класса в формате Поле:{данные}.
     */
    @JsonIgnore
    public String getUserData() {
        return "ID:{" + id + "} Type:{" + type + "} PhoneNumber:{" + getUserPhoneNumber() + "} Address:{" + address + "}";
    }

    @JsonIgnore
    public abstract Map<String, String> getMapClass();

    @JsonIgnore
    public String getUserPhoneNumber() {
        return phoneNumber.getPhoneNumber();
    }

}
