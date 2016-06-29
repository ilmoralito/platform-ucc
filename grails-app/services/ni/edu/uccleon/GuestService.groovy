package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class GuestService {

    Guest getGuest(Long id) {
        Guest.get(id)
    }

    List<Guest> getGuests(Boolean enabled = true) {
        Guest.findAllByEnabled(enabled)
    }
}
