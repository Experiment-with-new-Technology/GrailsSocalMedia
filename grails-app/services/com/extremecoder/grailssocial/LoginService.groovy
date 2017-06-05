package com.extremecoder.grailssocial

import com.extremecoder.grailssocial.enums.RoleType
import grails.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder

import java.util.concurrent.TimeUnit

@Transactional
class LoginService {

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
            userLoginInternallyWithoutPassword(user.username)
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
            def providerUserRole = SecRole.findByAuthority(RoleType.PROVIDER_USER.value)
            SecUserSecRole secUserSecRole = new SecUserSecRole(secUser: user, secRole: providerUserRole).save(flush: true)
            providerUser = new ProviderUser(accessToken: params.accessToken,
                    accessTokenExpires: new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(params.getLong('expireIn'))),
                    userId: params.userAccountId, secUser: user, providerName: params.providerName
            ).save(flush: true)
            userLoginInternallyWithoutPassword(user.username)
            responseData = ['hasError': false, 'message': 'Sucessfully Registered']
        }
        responseData
    }

    def userLoginInternallyWithoutPassword(String username) {
        SecUser details = SecUser.findByUsername(username)
        Authentication authentication = new UsernamePasswordAuthenticationToken(details, null,
                AuthorityUtils.createAuthorityList(getUserRoleType(details)))
        SecurityContextHolder.getContext().setAuthentication(authentication)
        def w = 1
    }

    def getUserRoleType(SecUser secUser) {
        SecUserSecRole secUserSecRole = SecUserSecRole.findBySecUser(secUser)
        secUserSecRole.secRole.authority
    }
}
