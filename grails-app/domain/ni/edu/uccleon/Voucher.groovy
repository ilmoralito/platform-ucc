package ni.edu.uccleon

class Voucher {
    def employeeService

    Date date
    User createdBy
    User approvedBy
    BigDecimal value
    Date approvalDate
    Date dateNotification
    String status = "pending"

    Boolean refreshment
    Boolean breakfast
    Boolean dinner
    Boolean lunch

    Date dateCreated
    Date lastUpdated

    static constraints = {
        date validator: { date -> date >= new Date().clearTime() }
        value min: 1.0
        createdBy nullable: false
        dateNotification nullable: true
        approvedBy nullable: true, validator: { approvedBy ->
            if (approvedBy) {
                return "Administracion" in employeeService.getEmployeeCoordinations(approvedBy.id).name
            }
        }
        approvalDate nullable: true, validator: { approvalDate, obj ->
            if (obj.approvedBy) {
                approvalDate >= new Date()
            }
        }
        status inList: ["pending", "notified", "approved"], maxSize: 255
        refreshment nullable: true
        breakfast nullable: true
        lunch nullable: true
        dinner nullable: true, validator: { dinner, obj ->
            obj.refreshment || obj.breakfast || obj.lunch || dinner
        }
    }
}
