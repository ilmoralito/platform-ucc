package ni.edu.uccleon

class Voucher {
    def employeeService

    Date date
    BigDecimal value
    Boolean refreshment
    Boolean breakfast
    Boolean lunch
    Boolean dinner
    User createdBy
    Date dateNotification
    User approvedBy
    Date approvalDate
    String status = "pending"

    Date dateCreated
    Date lastUpdated

    static constraints = {
        date validator: { date ->
            date >= new Date().clearTime()
        }
        value min: 1.0
        refreshment nullable: true
        breakfast nullable: true
        lunch nullable: true
        dinner nullable: true, validator: { dinner, obj ->
            obj.refreshment || obj.breakfast || obj.lunch || dinner
        }
        createdBy nullable: false
        dateNotification nullable: true
        approvedBy nullable: true, validator: { approvedBy ->
            if (approvedBy) {
                employeeService.getEmployeeCoordination(approvedBy.id) == "Administracion"
            }
        }
        approvalDate nullable: true, validator: { approvalDate, obj ->
            if (obj.approvedBy) {
                approvalDate >= new Date()
            }
        }
        status inList: ["pending", "notified", "approved"], maxSize: 255
    }
}
