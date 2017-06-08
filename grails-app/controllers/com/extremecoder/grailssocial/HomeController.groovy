package com.extremecoder.grailssocial

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class HomeController {

    def springSecurityService

    def index() {
        SecUser user = springSecurityService.currentUser
        render(view: 'index', model: [user: user])
    }
}
