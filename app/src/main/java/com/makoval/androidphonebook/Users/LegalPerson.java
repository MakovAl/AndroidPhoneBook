package com.makoval.androidphonebook.Users;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Наследник класса User дополняющий класс так, чтобы иметь возможность хранить и обрабатывать данные юридических лицо
 * в телефонной книге
 */
@JsonTypeName("legalPerson")
@NoArgsConstructor
@Setter
@Getter
public class LegalPerson extends User {
    /**
     * Название организации
     */
    private String nameOrganization;
    /**
     * Часы работы
     */
    private String workingHours;
    /**
     * Услуги предоставляемые юридическим лицом
     */
    private String services;


    public LegalPerson(int id, String type, String nameOrganization, String workingHours, String service, String phoneNumber,
                       String address) {
        super(id, type, phoneNumber, address);
        this.nameOrganization = nameOrganization;
        this.workingHours = workingHours;
        this.services = service;
    }

    /**
     * @return Строка содержащая все поля класса в формате Поле:{данные}.
     */
    @Override
    public String getUserData() {
        return super.getUserData() + " NameOrganization:{" + nameOrganization + "} WorkingHours:{" + workingHours +
                "} Services:{" + services + "}";
    }

    @Override
    public Map<String, String> getMapClass() {
        Map<String, String> mapClass = new HashMap<>();
        mapClass.put("id", String.valueOf(super.getId()));
        mapClass.put("type", super.getType());
        mapClass.put("phoneNumber", getUserPhoneNumber());
        mapClass.put("address", super.getAddress());
        return  mapClass;
    }
}
