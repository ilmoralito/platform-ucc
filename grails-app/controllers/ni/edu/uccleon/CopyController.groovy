package ni.edu.uccleon

import grails.plugins.rest.client.RestResponse
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured('IS_AUTHENTICATED_FULLY')
class CopyController {
    SpringSecurityService springSecurityService
    EmployeeService employeeService
    CopyService copyService

    static allowedMethods = [
        index: 'GET',
        create: 'POST',
        post: 'POST',
        postExtraCopy: 'POST',
        show: 'GET',
        edit: 'GET',
        update: 'POST',
        updateStatus: 'GET',
        delete: 'GET',
        summary: 'GET'
    ]

    def index() {
        [
            copyStatus: createCopyStatus(),
            copyOption: createCopyOption(),
            copyList: copyService.copiesToDateByEmployee(springSecurityService.currentUser.employee)
        ]
    }

    def create(CopyOptionCommand command) {
        if (command.hasErrors()) {
            flash.message = 'Parametros incorrectos'
            redirect action: 'index'
        }

        // TODO: check for better solution for this problem
        List<Map> result =  createCopyStatus().status.findAll { it.id == command.coordination }

        [copyStatus: [status: result]]
    }

    def post() {
        params.employee = springSecurityService.currentUser.employee
        RestResponse result = copyService.post(params.subMap(['documentDescription', 'copies', 'employee', 'coordination']))

        flash.message = result.status >= 400 ? 'Parametros incorrectos' : "Orden creada"
        redirect action: 'index'
    }

    def postExtraCopy() {
        params.employee = springSecurityService.currentUser.employee
        RestResponse result = copyService.postExtraCopy(params.subMap(['documentDescription', 'copies', 'employee', 'coordination', 'description']))

        flash.message = result.status >= 400 ? 'Parametros incorrectos' : "Orden extra creada"
        redirect action: 'index', params: [extra: true]
    }

    def show(Integer id) {
        Map copy = copyService.get(id)

        if (copy.message == 'Not Found') {
             response.sendError 404
        } else {
            [copy: copy]
        }
    }

    def edit(Integer id) {
        Map copy = copyService.get(id)

        if (copy.message == 'Not Found') {
             response.sendError 404
        } else {
            [copy: copy]
        }
    }

    def update(Integer id) {
        Map copy = copyService.get(id)

        if (copy.message == 'Not Found') {
             response.sendError 404
        } else {
            RestResponse result = copyService.put(params)

            flash.message = result.status >= 400 ? 'Parametros incorrectos' : "Orden actualizada"
            redirect action: 'edit', id: id
        }
    }

    def updateStatus(Integer id, String status) {
        RestResponse result = copyService.put([id: id, status: status])

        flash.message = result.status >= 400 ? 'Parametros incorrectos' : "Orden ${status == 'NOTIFIED' ? 'NOTIFICADA' : 'APROBADA'}"
        redirect action: 'index'
    }

    def delete(Integer id) {
        copyService.delete(id)

        flash.message = 'Orden eliminada'
        redirect action: 'index'
    }

    def summary() {
        
    }

    private CopyStatus createCopyStatus() {
        new CopyStatus(
            status: copyService.copieStatus(springSecurityService.currentUser.employee)
        )
    }

    private CopyOption createCopyOption() {
        new CopyOption(
            coordinationList: employeeService.getEmployee(springSecurityService.currentUser.employee).coordinations.sort { it.name }
        )
    }
}


