package com.makoval.androidphonebook.PhoneNumber;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhoneNumber {
    /**
     * Префикс "8"
     */
    String internationalPrefix;
    /**
     * Три цифры после префикса
     */
    String areaCode;
    /**
     * Три цифры после кода зоны
     */
    String subscriber;
    /**
     * Четыре цыфры после номера абонента
     */
    String extension;


    public PhoneNumber() {
        internationalPrefix = "";
        areaCode = "";
        subscriber = "";
        extension = "";
    }

    public PhoneNumber(String phoneNumber) {
        splitNumber(phoneNumber);
    }


    /**
     * @return Строка с номером телефона
     */
    @JsonIgnore
    public String getPhoneNumber() {
        return internationalPrefix + areaCode + subscriber + extension;
    }

    /**
     * Метод раскладывающий номер телефона по полям класса
     *
     * @param phoneNumber Номер телефона
     */
    public void splitNumber(String phoneNumber) {
        for (char number : phoneNumber.toCharArray()) {
            if (internationalPrefix.length() == 0) {
                internationalPrefix += number;
            } else if (areaCode.length() < 3) {
                areaCode += number;
            } else if (subscriber.length() < 3) {
                subscriber += number;
            } else if (extension.length() < 4) {
                extension += number;
            }
        }

    }
}
