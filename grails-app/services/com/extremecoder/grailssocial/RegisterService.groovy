package com.extremecoder.grailssocial

import com.extremecoder.grailssocial.enums.RoleType
import grails.transaction.Transactional
import com.extremecoder.grailssocial.enums.RoleType
import java.util.concurrent.TimeUnit

@Transactional
class RegisterService {
    def springSecurityService
    def loginService

    def register(def params) {
        def response
        SecUser user = new SecUser(params)
        if(user.hasErrors()) {
            response = [hasError: true, message: 'Please all information & try again.', user: null]
            return response
        }
        SecUser secUser = SecUser.findByUsername(user.username)
        if(secUser) {
            response = [hasError: true, message: 'Already Registered.', user: user]
            return response
        }
        user = user.save(flush: true)
        SecRole secRole = SecRole.findByAuthority(RoleType.USER.value)
        SecUserSecRole.create(user, secRole)
        response = [hasError: false, message: 'Successfully Registered.', user: user]
        return response
    }

    def providerRegistration(def params) {
        def responseData
        ProviderUser providerUser = ProviderUser.findByUserId(params.userAccountId)
        if(providerUser) {
            if(providerUser.accessToken != params.accessToken) {
                providerUser.accessToken = params.accessToken
                providerUser.accessTokenExpires = new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(params.getLong('expireIn')))
                providerUser.save(flush: true)
            }
            SecUser user = providerUser.secUser
            loginService.userLoginInternallyWithoutPassword(user.username)
        } else {
            SecUser user = SecUser.findByUsername(params.email) ?: new SecUser(
                    password: params.accessToken, //not really necessary
                    enabled: true,
                    isValidated: true,
                    accountExpired: false,
                    accountLocked: false,
                    passwordExpired: false,
                    username: params.email
            ).save(flush: true)
            def providerUserRole = SecRole.findByAuthority(RoleType.USER.value)
            SecUserSecRole secUserSecRole = new SecUserSecRole(secUser: user, secRole: providerUserRole).save(flush: true)
            providerUser = new ProviderUser(accessToken: params.accessToken,
                    accessTokenExpires: new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(params.getLong('expireIn'))),
                    userId: params.userAccountId, secUser: user, providerName: params.providerName
            ).save(flush: true)
            loginService.userLoginInternallyWithoutPassword(user.username)

        }
        responseData = ['hasError': false, 'message': 'Sucessfully Registered']
        responseData
    }

    def linkWithFacebook(def params) {
        def responseData
        ProviderUser providerUser = ProviderUser.findByUserId(params.userId)
        if(providerUser) {
            if(providerUser.accessToken != params.accessToken) {
                providerUser.accessToken = params.accessToken
                providerUser.accessTokenExpires = new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(params.getLong('expireIn')))
                providerUser.save(flush: true)
            }
        } else {
            SecUser user = springSecurityService.currentUser
            providerUser = new ProviderUser(accessToken: params.accessToken,
                    accessTokenExpires: new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(params.getLong('expireIn'))),
                    userId: params.userAccountId, secUser: user, providerName: params.providerName
            ).save(flush: true)
        }
        responseData = ['hasError': false, 'message': 'Successfully Linked']
        responseData
    }
}
