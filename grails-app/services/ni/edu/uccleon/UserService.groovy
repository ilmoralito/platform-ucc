package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class UserService {

    List<User> getAll() {
        User.list()
    }

    List<User> getAllByEnabled(final Boolean enabled = true) {
        User.findAllByEnabled(enabled)
    }
}
