package ni.edu.uccleon

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService

    String username
    String email
    Integer employee
    Integer thirdPartyEmployee
    String password = 'temporal'
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    User(String username, Integer employee, String email, String password) {
        this()
        this.username = username
        this.employee = employee
        this.email = email
        this.password = password
    }

    // User(String username, Integer thirdPartyEmployee, String email, String password) {
    //     this()
    //     this.username = username
    //     this.thirdPartyEmployee = thirdPartyEmployee
    //     this.email = email
    //     this.password = password
    // }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true
        employee nullable: true, min: 1
        thirdPartyEmployee nullable: true, min: 1
        // , validator: { thirdPartyEmployee, obj ->
        //     thirdPartyEmployee != null || obj.employee != null
        // }
        email blank: false, email: true, unique: true, validator: { email, obj ->
            if (obj.employee) {
                List<String> emailTokenized = email.tokenize('@')
                Boolean isValidUsername = emailTokenized[0].tokenize('.').size() == 2
                Boolean isValidDomainName = emailTokenized[1] == 'ucc.edu.ni'

                if (!isValidUsername || !isValidDomainName) {
                  'not.valid.email'
                }
            }
        }
        password blank: false, password: true
    }

    static mapping = {
        password column: "`password`"
        sort 'username'
    }

}
