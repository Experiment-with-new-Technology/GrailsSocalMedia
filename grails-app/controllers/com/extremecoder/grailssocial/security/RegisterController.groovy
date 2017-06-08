package com.extremecoder.grailssocial.security

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured

class RegisterController {

    def registerService
    def loginService
    def springSecurityService

    @Secured('permitAll')
    def register() {
        switch (request.method) {
            case 'GET':
                render(view: 'register')
                return
                break
            case 'POST':
                def response = registerService.register(params)
                if(!response.hasError) {
                    loginService.userLoginInternallyWithoutPassword(response.user.username)
                    redirect(controller: 'home', action: 'index')
                } else {
                    flash.message = response.message
                }
                break
        }
    }

    @Secured('permitAll')
    def registerWithProvider() {
        def response = registerService.providerRegistration(params)
        render response as JSON
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def linkUpWIthFacebook() {
        def response = registerService.linkWithFacebook(params)
        render response as JSON
    }
}
