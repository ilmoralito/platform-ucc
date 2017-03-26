package ni.edu.uccleon

class CopyReportFilter {
    List<Integer> yearList = []
    List<Map<String, String>> monthList = [
        [name: 'Enero'],
        [name: 'Febrero'],
        [name: 'Marzo'],
        [name: 'Abril'],
        [name: 'Mayo'],
        [name: 'Junio'],
        [name: 'Julio'],
        [name: 'Agosto'],
        [name: 'Septiembre'],
        [name: 'Octubre'],
        [name: 'Noviembre'],
        [name: 'Diciembre']
    ]
    Integer currentYear
    Integer currentMonth
}
