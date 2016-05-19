grails.databinding.dateFormats = ["yyyy-MM-dd"]

grails.plugin.springsecurity.userLookup.usernamePropertyName = "email"
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.successHandler.defaultTargetUrl = "/panel"

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = "ni.edu.uccleon.User"
grails.plugin.springsecurity.userLookup.authorityJoinClassName = "ni.edu.uccleon.UserRole"
grails.plugin.springsecurity.authority.className = "ni.edu.uccleon.Role"
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
    [pattern: "/",               access: ["permitAll"]],
    [pattern: "/error",          access: ["permitAll"]],
    [pattern: "/index",          access: ["permitAll"]],
    [pattern: "/index.gsp",      access: ["permitAll"]],
    [pattern: "/shutdown",       access: ["permitAll"]],
    [pattern: "/assets/**",      access: ["permitAll"]],
    [pattern: "/**/js/**",       access: ["permitAll"]],
    [pattern: "/**/css/**",      access: ["permitAll"]],
    [pattern: "/**/images/**",   access: ["permitAll"]],
    [pattern: "/**/favicon.ico", access: ["permitAll"]],
    [pattern: "/dbconsole/**", access: ["permitAll"]]
]

grails.plugin.springsecurity.filterChain.chainMap = [
    [pattern: "/assets/**",      filters: "none"],
    [pattern: "/**/js/**",       filters: "none"],
    [pattern: "/**/css/**",      filters: "none"],
    [pattern: "/**/images/**",   filters: "none"],
    [pattern: "/**/favicon.ico", filters: "none"],
    [pattern: "/**",             filters: "JOINED_FILTERS"]
]

ni {
    edu {
        uccleon {
            companyInformation = [
                name: "UCC Campus Leon",
                slogan: "Para la gente que trinufa",
                address: "Frente al campus medico",
                telephoneNumber: ["23110812", "23110814"],
                rucNumber: "78458569",
                logo: ""
            ]

            tableTypes = ["Azules", "Blancas", "Plegable"]

            chairTypes = ["Pupitre", "Amueblado"]

            mountingTypes = [
                "Libre",
                "Forma U",
                "Auditorium con mesas",
                "Auditorium sin mesas",
                "Sala de reunion",
                "Grupo"
            ]

            serverUrl = "192.168.7.99:8181"
        }
    }
}

grails {
   mail {
        host = "smtp.gmail.com"
        port = 465
        username = System.env.GMAIL_USERNAME
        password = System.env.GMAIL_PASSWORD
        props = ["mail.smtp.auth":"true",
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]
   }
}

