package com.extremecoder.grailssocial

import com.extremecoder.grailssocial.enums.RoleType
import grails.transaction.Transactional

@Transactional
class RegisterService {

    def register(def params) {
        def response
        SecUser user = new SecUser(params)
        if(user.hasErrors()) {
            response = [hasError: true, message: 'Please all information & try again.', user: null]
            return response
        }
        user.save(flush: true)
        SecRole secRole = SecRole.findByAuthority(RoleType.USER.value)
        SecUserSecRole.create(user, secRole)
        response = [hasError: false, message: 'Successfully Registered.', user: user]
        return response
    }
}
