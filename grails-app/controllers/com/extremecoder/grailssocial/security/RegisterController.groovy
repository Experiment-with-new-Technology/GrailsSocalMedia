package com.extremecoder.grailssocial.security

import org.springframework.security.access.annotation.Secured

class RegisterController {

    def registerService
    def loginService

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
                }
                break
        }
    }

    @Secured('permitAll')
    def registerWithProvider() {

    }

    @Secured('permitAll')
    def fromRegister() {

    }
}
