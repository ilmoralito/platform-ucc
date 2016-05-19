<g:set var="index" value="${params.int('index') ?: 0}"/>

<div class="row">
    <div class="col-md-6">
        <label for="date">Fecha del evento</label>
        <div class="form-group">
            <g:textField
                name="date"
                value="${session?.events?.getAt(index)?.date?.format('yyyy-MM-dd') ?: params?.date}"
                class="form-control"/>
        </div>
    </div>

    <div class="col-md-6">
        <ucc:classrooms id="${session?.events?.getAt(index)?.location ?: params?.location}"/>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <label for="startTime">Hora inicial</label>
        <div class="form-group">
            <g:select
                name="startTime"
                from="['8:00', '9:00', '10:00', '11:00', '12:00', '1:00', '2:00', '3:00', '4:00']"
                keys="[8, 9, 10, 11, 12, 13, 14, 15, 16]"
                value="${session?.events?.getAt(index)?.startTime ?: params.int('startTime')}"
                class="form-control"/>
        </div>
    </div>
    
    <div class="col-md-6">
        <div class="form-group">
            <label for="endingTime">Hora de finalizacion</label>
            <g:select
                name="endingTime"
                from="['9:00', '10:00', '11:00', '12:00', '1:00', '2:00', '3:00', '4:00', '5:00']"
                keys="[9, 10, 11, 12, 13, 14, 15, 16, 17]"
                value="${session?.events?.getAt(index)?.endingTime ?: params.int('endingTime')}"
                class="form-control"/>
        </div>
    </div>
</div>

<div class="form-group">
    <label for="numberOfPeople">Numero de asistentes</label>
    <input
        type="number"
        name="numberOfPeople"
        id="numberOfPeople"
        value="${session?.events?.getAt(index)?.numberOfPeople ?: params?.numberOfPeople}"
        class="form-control"
        min="1">
</div>

<div id="numberOfPeopleAlert" class="alert alert-danger hide">Fuera de limite</div>

<div class="row">
    <div class="col-md-3">
        <p>Medios</p>

        <div class="checkbox">
            <label>
                <g:checkBox name="audiovisual" value="${session?.events?.getAt(index)?.audiovisual ?: params?.audiovisual}"/>
                Datashow
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="wifi" value="${session?.events?.getAt(index)?.wifi ?: params?.wifi}"/>
                Wifi
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="sound" value="${session?.events?.getAt(index)?.sound ?: params?.sound}"/>
                Audio
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="speaker" value="${session?.events?.getAt(index)?.speaker ?: params?.speaker}"/>
                Parlantes de computadora
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="microfone" value="${session?.events?.getAt(index)?.microfone ?: params?.microfone}"/>
                Microfono
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="pointer" value="${session?.events?.getAt(index)?.pointer ?: params?.pointer}"/>
                Puntero
            </label>
        </div>
    </div>

    <div class="col-md-3">
        <p>Refrescos</p>

        <div class="checkbox">
            <label>
                <g:checkBox name="water" value="${session?.events?.getAt(index)?.water ?: params?.water}"/>
                Agua
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="coffee" value="${session?.events?.getAt(index)?.coffee ?: params?.coffee}"/>
                Cafe
            </label>
        </div>

        <div class="form-group">
            <label for="cookies">Galletas</label>
            <input
                type="number"
                name="cookies"
                id="cookies"
                value="${session?.events?.getAt(index)?.cookies ?: params?.cookies}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="waterBottles">Botellas de agua</label>
            <input
                type="number"
                name="waterBottles"
                id="waterBottles"
                value="${session?.events?.getAt(index)?.waterBottles ?: params?.waterBottles}"
                class="form-control"
                min="1">
        </div>
    </div>

    <div class="col-md-3">
        <p>Montaje</p>
        <ucc:mountingType mountingTypeInstance="${session?.events?.getAt(index)?.mountingType ?: params?.mountingType}"/>

        <label>Elementos</label>
        <div class="checkbox">
            <label>
                <g:checkBox name="flags" value="${session?.events?.getAt(index)?.flags ?: params?.flags}"/>
                Banderas
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="podium" value="${session?.events?.getAt(index)?.podium ?: params?.podium}"/>
                Podium
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="tableForSpeaker" value="${session?.events?.getAt(index)?.tableForSpeaker ?: params?.tableForSpeaker}"/>
                Mesa para expositor
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="tablecloths" value="${session?.events?.getAt(index)?.tablecloths ?: params?.tablecloths}"/>
                Manteles
            </label>
        </div>

        <ucc:tableclothColor
            tableclothColorList="${session?.events?.getAt(index)?.tableclothColors?.name ?: params?.tableclothColors}"/>

        <ucc:tableType
            tableTypeList="${session?.events?.getAt(index)?.tableTypes?.name ?: params?.tableTypes}"/>

        <ucc:chairs
            chairTypeList="${session?.events?.getAt(index)?.chairTypes?.name ?: params?.chairTypes}"/>

        <div class="form-group">
            <label for="presidiumTable">Meza presidium</label>
            <input
                type="number"
                name="presidiumTable"
                id="presidiumTable"
                value="${session?.events?.getAt(index)?.presidiumTable}"
                min=1
                max="7"
                class="form-control">
        </div>
    </div>

    <div class="col-md-3">
        <p>Alimentos</p>

        <div class="form-group">
            <label for="refreshment">Refrescos</label>
            <input
                type="number"
                name="refreshment"
                id="refreshment"
                value="${session?.events?.getAt(index)?.refreshment ?: params?.refreshment}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="breakfast">Desayunos</label>
            <input
                type="number"
                name="breakfast"
                id="breakfast"
                value="${session?.events?.getAt(index)?.breakfast ?: params?.breakfast}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="lunch">Almuerzo</label>
            <input
                type="number"
                name="lunch"
                id="lunch"
                value="${session?.events?.getAt(index)?.lunch ?: params?.lunch}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="dinner">Cena</label>
            <input
                type="number"
                name="dinner"
                id="dinner"
                value="${session?.events?.getAt(index)?.dinner ?: params?.dinner}"
                class="form-control"
                min="1">
        </div>
    </div>
</div>

<div class="form-group">
    <label for="observation">Observacion</label>
    <g:textArea
        name="observation"
        value="${session?.events?.getAt(index)?.observation ?: params?.dinner}"
        class="form-control"
        />
</div>
