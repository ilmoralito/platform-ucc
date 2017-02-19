import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

// logger 'org.hibernate.type.descriptor.sql.BasicBinder', TRACE, ['STDOUT']
// logger 'org.hibernate.SQL', TRACE, ['STDOUT']

root(ERROR, ['STDOUT'])

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
    logger "grails.app.controllers.ni.edu.uccleon.ActivityInterceptor", DEBUG, ["STDOUT"], false
    logger "grails.app.controllers.ni.edu.uccleon.NotificationInterceptor", DEBUG, ["STDOUT"], false
    logger "grails.app.controllers.ni.edu.uccleon.ProtocolInterceptor", DEBUG, ["STDOUT"], false
}
