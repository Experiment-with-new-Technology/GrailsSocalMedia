package com.extremecoder.grailssocial.enums

/**
 * Created by rakib on 12/7/2016.
 */
enum RoleType {
    USER('ROLE_USER'),
    ADMIN('ROLE_ADMIN')


    final String value
    public RoleType(String value) {
        this.value = value
    }
    public String getValue() {
        value
    }
    public String getKey() {
        name()
    }
    public String toString() {
        value
    }
}
