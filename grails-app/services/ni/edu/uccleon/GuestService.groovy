package ni.edu.uccleon

import grails.transaction.Transactional
import grails.gorm.DetachedCriteria

@Transactional
class GuestService {

    Guest getGuest(final Long id) {
        Guest.get(id)
    }

    List<Guest> getAll() {
        Guest.list()
    }

    List<Guest> getByEnabled(final List<Boolean> enabledList) {
        DetachedCriteria query = Guest.where {
            enabled in enabledList
        }

        query.list()
    }
}
