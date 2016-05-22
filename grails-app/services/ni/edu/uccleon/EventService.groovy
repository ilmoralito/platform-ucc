package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class EventService {

    Date getEventMinDate(Activity activityInstance) {
        List<Event> events = Event.where { activity == activityInstance }.list()

        events.date.min()
    }
}
