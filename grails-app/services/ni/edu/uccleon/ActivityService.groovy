package ni.edu.uccleon

import grails.transaction.Transactional

@Transactional
class ActivityService {

    String getStatus(Activity activity) {
        if (activity.grantedBy == null && activity.approvedBy == null) {
            "Pendiente de aprobacion"
        } else if (activity.grantedBy != null && activity.approvedBy == null) {
            "Aprovado"
        } else if (activity.grantedBy == null && activity.approvedBy != null) {
            "Autorizado"
        }
    }
}
