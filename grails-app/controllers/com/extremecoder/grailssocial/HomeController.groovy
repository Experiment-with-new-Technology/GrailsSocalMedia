package com.extremecoder.grailssocial

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class HomeController {

    def springSecurityService

    def index() {
        render(view: 'index')
    }
}
