package com.extremecoder.grailssocial


import grails.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder

import java.util.concurrent.TimeUnit

@Transactional
class LoginService {

    def socialLogin(def params) {
        def responseData
        ProviderUser providerUser = ProviderUser.findByUserId(params.userId)
        if(providerUser) {
            if(providerUser.accessToken != params.accessToken) {
                providerUser.accessToken = params.accessToken
                providerUser.accessTokenExpires = new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(params.getLong('expireIn')))
                providerUser.save(flush: true)
            }
            SecUser user = providerUser.secUser
            userLoginInternallyWithoutPassword(user.username)
            responseData = ['hasError': false, 'message': 'Sucessfully Login']
        } else {
            responseData = ['hasError': true, 'message': 'Not Found']
        }
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
