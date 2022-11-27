package com.makoval.androidphonebook.Users;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.Map;


@JsonTypeName("manager")
public class Manager extends User {

    private String name;
    private String surname;
    private String position;

    /**
     * Конструктор
     *
     * @param id
     * @param type        Тип пользователя
     * @param phoneNumber Телефоонный номер пользователя
     * @param address     Адрес пользователя
     */
    public Manager(int id, String type, String phoneNumber, String address, String name,
                   String surname, String position) {
        super(id, type, phoneNumber, address);
        this.name = name;
        this.surname = surname;
        this.position = position;
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

    public Manager(){
        super();
    }


    /**
     * @return Строка содержащая все поля класса в формате Поле:{данные}.
     */
    @Override
    public String getUserData() {
        return super.getUserData() + " Name:{" + name + "} Surname:{" + surname +
                "} Position:{" + position + "}";
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
        return  mapClass;
    }

}
