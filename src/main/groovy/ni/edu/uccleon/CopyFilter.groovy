package ni.edu.uccleon

class CopyFilter {
    List coordinationList = []
    List employeeList = []
    List authorizedByList = []
    List canceledByList = []
    List copyStatusList = [
        [key: 'NOTIFIED', value: 'Notificado'],
        [key: 'CANCELED', value: 'Cancelado'],
        [key: 'REQUEST_AUTHORIZATION', value: 'Solicitud de autorizacion'],
        [key: 'AUTHORIZED', value: 'Autorizado'],
        [key: 'ATTENDED', value: 'Atendido']
    ]
}
