package com.extremecoder.grailssocial

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER', 'ROLE_ADMIN', 'ROLE_PROVIDER_USER'])
class HomeController {

    def index() { }
}
