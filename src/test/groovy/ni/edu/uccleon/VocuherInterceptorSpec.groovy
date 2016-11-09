package ni.edu.uccleon


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(VocuherInterceptor)
class VocuherInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test vocuher interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"vocuher")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
