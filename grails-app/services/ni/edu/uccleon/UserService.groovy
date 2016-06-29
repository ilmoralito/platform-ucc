package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class UserService {

    List<User> getUsers(Boolean enabled = true) {
        User.findAllByEnabled(enabled)
    }
}
