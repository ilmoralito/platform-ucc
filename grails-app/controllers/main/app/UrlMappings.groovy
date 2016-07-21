package main.app

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" controller: "birthday", action: "index"
        // "notAllowed" view: "/notAllowed"
        "500" view: "/error"
        "404" view: "/notFound"
    }
}
