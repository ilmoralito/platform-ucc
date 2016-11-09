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
    [pattern: "/dbconsole/**",   access: ["permitAll"]],
    [pattern: "/console/**",     access: ["ROLE_ADMIN"]],
    [pattern: "/static/console/**", access: ['ROLE_ADMIN']],
    [pattern: "/plugins/console*/**", access: ["ROLE_ADMIN"]]
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
                name: "UCC Leon",
                slogan: "Para la gente que trinufa",
                address: "Frente al campus medico",
                telephoneNumber: ["23110812", "23110814"],
                rucNumber: "78458569",
                logo: ""
            ]

            tableTypes = ["Azules", "Blancas", "Plegable", "Madera"]

            chairTypes = ["Pupitre", "Amueblada"]

            mountingTypes = [
                "Libre",
                "Forma U",
                "Auditorium con mesas",
                "Auditorium sin mesas",
                "Sala de reunion",
                "Grupo"
            ]

            foods = [
                [spanish: "Refrigerio", english: "refreshment"],
                [spanish: "Desayuno", english: "breakfast"],
                [spanish: "Almuerzo", english: "lunch"],
                [spanish: "Cena", english: "dinner"]
            ]

            serverUrl = "192.168.7.99:8181"

            activityList = []
        }
    }
}

grails {
   mail {
        host = "smtp.gmail.com"
        port = 465
        username = "mario.martinez@ucc.edu.ni" // System.env.GMAIL_USERNAME
        password = "hotch^peluso" // System.env.GMAIL_PASSWORD
        props = ["mail.smtp.auth":"true",
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]
   }
}

