package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class GuestService {

    Guest getGuest(final Long id) {
        Guest.get(id)
    }

    List<Guest> getAll() {
        Guest.list()
    }

    List<Guest> getAllByEnabled(final Boolean enabled = true) {
        Guest.findAllByEnabled(enabled)
    }
}
