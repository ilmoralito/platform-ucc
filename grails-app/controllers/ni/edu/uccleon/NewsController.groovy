package ni.edu.uccleon

import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class NewsController {

    def index() {}
}
