package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class ActivityService {
    def springSecurityService

    String getStatus(Activity activity) {
        if (activity.grantedBy == null && activity.approvedBy == null) {
            "Pendiente"
        } else if (activity.grantedBy != null && activity.approvedBy == null) {
            "Aprovado"
        } else if (activity.grantedBy == null && activity.approvedBy != null) {
            "Autorizado"
        }
    }
}
