package com.makoval.androidphonebook.Users;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Наследник класса User дополняющий класс так, чтобы иметь возможность хранить и обрабатывать данные физических лиц
 * в телефонной книге
 */
@JsonTypeName("physicalPerson")
@NoArgsConstructor
@Setter
@Getter
public class PhysicalPerson extends User {

    /**
     * Имя физического лица
     */
    private String name;
    /**
     * Фамилия физического лица
     */
    private String surname;


    public PhysicalPerson(int id, String type, String name, String surname, String phoneNumber, String address) {
        super(id, type, phoneNumber, address);
        this.name = name;
        this.surname = surname;
    }

    /**
     * @return Строка содержащая все поля класса в формате Поле:{данные}.
     */
    @Override
    public String getUserData() {
        return super.getUserData() + " Name:{" + name + "} Surname:{" + surname + "}";
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
        return  mapClass;
    }
}
