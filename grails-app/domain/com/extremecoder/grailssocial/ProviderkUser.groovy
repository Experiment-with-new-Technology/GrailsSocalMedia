package com.extremecoder.grailssocial

import com.extremecoder.grailssocial.SecUser

class ProviderkUser {

    Long id
    String userId
    String accessToken
    Date accessTokenExpires
    String providerName //GOOGLE FACEBOOK LINKEDIN

    static belongsTo = [secUser: SecUser]

    static constraints = {

    }
}
