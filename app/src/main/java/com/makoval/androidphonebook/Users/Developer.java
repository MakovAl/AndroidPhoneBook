package com.makoval.androidphonebook.Users;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.Map;

@JsonTypeName("developer")
public class Developer extends User {

    private String name;
    private String surname;
    private String position;
    private String programmingLanguages;

    /**
     * Конструктор
     *
     * @param id
     * @param type        Тип пользователя
     * @param phoneNumber Телефоонный номер пользователя
     * @param address     Адрес пользователя
     */
    public Developer(int id, String type, String phoneNumber, String address, String name,
                     String surname, String position, String programmingLanguages) {
        super(id, type, phoneNumber, address);
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.programmingLanguages = programmingLanguages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(String programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public Developer() {
        super();
    }

    /**
     * @return Строка содержащая все поля класса в формате Поле:{данные}.
     */
    @Override
    public String getUserData() {
        return super.getUserData() + " Name:{" + name + "} Surname:{" + surname +
                "} Position:{" + position + "} ProgrammingLanguages:{" + programmingLanguages + "}";
    }

    @Override
    public Map<String, String> getMapClass() {
        Map<String, String> mapClass = new HashMap<>();
        mapClass.put("id", String.valueOf(super.getId()));
        mapClass.put("type", super.getType());
        mapClass.put("phoneNumber", getUserPhoneNumber());
        mapClass.put("address", super.getAddress());
        mapClass.put("name", this.name);
        mapClass.put("surname", this.surname);
        mapClass.put("position", this.position);
        mapClass.put("programmingLanguages", this.programmingLanguages);
        return  mapClass;
    }

}
