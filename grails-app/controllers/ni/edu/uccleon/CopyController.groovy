package ni.edu.uccleon

import static java.util.Calendar.*
import org.grails.web.json.JSONObject
import grails.plugins.rest.client.RestResponse
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured(['ROLE_ASSISTANT_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_ACADEMIC_SUPERVISOR', 'ROLE_PROTOCOL_SUPERVISOR', 'ROLE_ADMIN', 'ROLE_USER'])
class CopyController {
    SpringSecurityService springSecurityService
    EmployeeService employeeService
    CopyService copyService

    static allowedMethods = [
        index: 'GET',
        create: 'POST',
        show: 'GET',
        delete: 'GET',
        status: ['GET', 'POST'],
        summary: 'GET',
        changeToAttended: 'POST',
        requestingAuthorization: 'GET',
        requestAuthorizationDetail: 'GET',
        authorizeCopyOrder: 'POST',
        denyCopyOrder: 'POST',
        report: ['GET', 'POST'],
        reportCopyListOutOfRange: 'GET'
    ]

    def index() {
        [
            createCopy: createCopy(),
            copyStatus: createCopyStatus(),
            copyList: copyService.copiesToDateByEmployee(springSecurityService.currentUser.employee)
        ]
    }

    def create() {
        RestResponse restResponse
        params.employee = springSecurityService.currentUser.employee

        if (params.copyType == 'copy') {
            restResponse = copyService.post(params)
        } else {
            params.status = 'REQUEST_AUTHORIZATION'
            restResponse = copyService.postExtraCopy(params)
        }

        flash.message = restResponse.status == 201 ? 'Orden creada' : 'Parametros incorrectos'
        redirect action: 'index'
    }

    def show(Integer id) {
        [copy: copyService.get(id)]
    }

    @Secured(['ROLE_COPY_MANAGER', 'ROLE_COPY_ASSISTANT'])
    def delete(Integer id) {
        RestResponse restResponse = copyService.delete(id)

        flash.message = restResponse.status == 204 ? 'Orden eliminada' : 'A ocurrido un error'
        redirect action: 'status'
    }

    @Secured(['ROLE_COPY_MANAGER', 'ROLE_COPY_ASSISTANT'])
    def status() {
        List copyList

        if (request.method == 'POST') {
            List<Integer> coordinationList = params.list('coordinationList')*.toInteger()
            List<Integer> employeeList = params.list('employeeList')*.toInteger()

            copyList = copyService.filter(coordinationList, employeeList).json
        } else {
            copyList = copyService.getAllNotifiedOrAuthorized()
        }

        [copyList: copyList, copyFilter: createCopyFilter()]
    }

    def summary() {
        [summary: copyService.summaryByEmployee(springSecurityService.currentUser.employee).json]
    }

    def summaryByCoordinationAndYear(Integer coordinationID, Integer year) {
        [summary: copyService.summaryByCoordinationAndYear(coordinationID, year).json]
    }

    def summaryByCoordinationAndYearAndMonth(Integer coordinationID, Integer year, Integer month) {
        [summary: copyService.summaryByCoordinationAndYearAndMonth(coordinationID, year, month).json]
    }

    @Secured(['ROLE_COPY_MANAGER', 'ROLE_COPY_ASSISTANT'])
    def detail(Integer id) {
        [copy: copyService.get(id)]
    }

    @Secured(['ROLE_COPY_MANAGER', 'ROLE_COPY_ASSISTANT'])
    def changeToAttended(Integer id) {
        RestResponse restResponse = copyService.put(params)

        flash.message = restResponse.status == 200 ? 'Marcado como atendido' : 'Parametros incorrectos'
        redirect action: 'status'
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def requestingAuthorization() {
        [extraCopyList: copyService.getAllRequestingAuthorization()]
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def requestAuthorizationDetail(Integer id) {
        [extraCopy: copyService.get(id)]
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def authorizeCopyOrder(Integer id) {
        RestResponse restResponse = copyService.put(
            [
                id: id,
                authorizedBy: springSecurityService.currentUser.id,
                status: 'AUTHORIZED'
            ]
        )

        // TODO: Inform the applicant about the approval of the copy order. SEND email

        flash.message = restResponse.status == 200 ? 'Orden autorizada' : 'Parametros incorrectos'
        redirect action: 'requestingAuthorization'
    }

    @Secured('ROLE_ADMINISTRATIVE_SUPERVISOR')
    def denyCopyOrder(Integer id) {
        RestResponse restResponse = copyService.put(
            [
                id: id,
                canceledBy: springSecurityService.currentUser.id,
                reasonForCancellation: params.reasonForCancellation,
                status: 'CANCELED'
            ]
        )

        // TODO: Inform the applicant of the non-approval of the copy order. SEND email

        if (restResponse.status == 200) {
            flash.message = 'Cancelado'
        } else {
            flash.message = 'Parametros incorrectos'
            redirect action: 'requestAuthorizationDetail', id: id
            return
        }

        redirect action: 'requestingAuthorization'
    }

    @Secured(['ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_COPY_MANAGER'])
    def report() {
        Integer year = params.int('year') ?: new Date()[YEAR]
        Integer month = params.int('monthInstance') ?: new Date()[MONTH]
        [
            report: copyService.report(year, month),
            copyReportFilter: createCopyReportFilter(year, month),
            copiesOutOfRange: copyService.reportCopiesOutOfRange().json.size()
        ]
    }

    @Secured(['ROLE_ADMINISTRATIVE_SUPERVISOR', 'ROLE_COPY_MANAGER'])
    def reportCopyListOutOfRange() {
        [copyListOutOfRange: copyService.reportCopiesOutOfRange().json]
    }

    private CopyStatus createCopyStatus() {
        new CopyStatus(
            statusList: copyService.copieStatus(springSecurityService.currentUser.employee).json
        )
    }

    private CreateCopy createCopy() {
        User currentUser = springSecurityService.currentUser
        List coordinationList = employeeService.getEmployee(currentUser.employee).coordinations.sort { a, b ->
            a.name <=> b.name
        } as List

        new CreateCopy(
            coordinationList: coordinationList,
            documentDescriptionList: copyService.getDocumentDescriptionByCoordination(coordinationList[0].id)
        )
    }

    private CopyFilter createCopyFilter(final List copyList) {
        new CopyFilter(
            coordinationList: copyList.coordination.unique { it.name },
            employeeList: copyList.employee.unique { it.fullName }
        )
    }

    private CopyFilter createCopyFilter() {
        List<JSONObject> copyList = copyService.getAll()

        new CopyFilter(
            coordinationList: copyList.coordination.unique { it.name },
            employeeList: copyList.employee.unique { it.fullName },
            authorizedByList: copyList.findAll { it.authorizedBy }.authorizedBy.unique { it.fullName },
            canceledByList: copyList.findAll { it.canceledBy }.canceledBy.unique { it.fullName }
        )
    }

    private CopyReportFilter createCopyReportFilter(final Integer year, final Integer month) {
        new CopyReportFilter(
            yearList: copyService.yearList(),
            currentYear: year,
            currentMonth: month
        )
    }
}
