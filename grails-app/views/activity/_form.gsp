<div class="row">
    <div class="col-md-6">
        <label for="date">Fecha del evento</label>
        <div class="form-group">
            <g:textField
                name="date"
                value="${events.getAt(index)?.date?.format('yyyy-MM-dd') ?: params?.date}"
                class="form-control"/>
        </div>
    </div>

    <div class="col-md-6">
        <ucc:classrooms id="${events.getAt(index)?.location ?: params?.location}"/>
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
                value="${events.getAt(index)?.startTime ?: params.int('startTime')}"
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
                value="${events.getAt(index)?.endingTime ?: params.int('endingTime')}"
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
        value="${events.getAt(index)?.numberOfPeople ?: params?.numberOfPeople}"
        class="form-control"
        min="1">

        <span id="helpBlock" class="helpBlock hide">
            Fuera de limite. Recomendado <span id="recommendedCapactity"></span> personas.
        </span>
</div>

<div class="row">
    <div class="col-md-3">
        <p>Medios</p>

        <div class="checkbox">
            <label>
                <g:checkBox name="audiovisual" value="${events.getAt(index)?.audiovisual ?: params?.audiovisual}"/>
                Datashow
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="wifi" value="${events.getAt(index)?.wifi ?: params?.wifi}"/>
                Wifi
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="sound" value="${events.getAt(index)?.sound ?: params?.sound}"/>
                Audio
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="speaker" value="${events.getAt(index)?.speaker ?: params?.speaker}"/>
                Parlantes de computadora
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="microfone" value="${events.getAt(index)?.microfone ?: params?.microfone}"/>
                Microfono
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="pointer" value="${events.getAt(index)?.pointer ?: params?.pointer}"/>
                Puntero
            </label>
        </div>
    </div>

    <div class="col-md-3">
        <p>Refrescos</p>

        <div class="checkbox">
            <label>
                <g:checkBox name="water" value="${events.getAt(index)?.water ?: params?.water}"/>
                Agua
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="coffee" value="${events.getAt(index)?.coffee ?: params?.coffee}"/>
                Cafe
            </label>
        </div>

        <div class="form-group">
            <label for="cookies">Galletas</label>
            <input
                type="number"
                name="cookies"
                id="cookies"
                value="${!events.getAt(index)?.cookies && !params?.cookies ? 0 : events.getAt(index)?.cookies ?: params?.cookies}"
                class="form-control"
                min="0">
        </div>

        <div class="form-group">
            <label for="waterBottles">Botellas de agua</label>
            <input
                type="number"
                name="waterBottles"
                id="waterBottles"
                value="${!events.getAt(index)?.waterBottles && !params?.waterBottles ? 0 : events.getAt(index)?.waterBottles ?: params?.waterBottles}"
                class="form-control"
                min="0">
        </div>
    </div>

    <div class="col-md-3">
        <p>Montaje</p>
        <ucc:mountingType mountingTypeInstance="${events.getAt(index)?.mountingType ?: params?.mountingType}"/>

        <label>Elementos</label>
        <div class="checkbox">
            <label>
                <g:checkBox name="flags" value="${events.getAt(index)?.flags ?: params?.flags}"/>
                Banderas
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="podium" value="${events.getAt(index)?.podium ?: params?.podium}"/>
                Podium
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="tableForSpeaker" value="${events.getAt(index)?.tableForSpeaker ?: params?.tableForSpeaker}"/>
                Mesa para expositor
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="tablecloths" value="${events.getAt(index)?.tablecloths ?: params?.tablecloths}"/>
                Manteles
            </label>
        </div>

        <ucc:tableclothColor
            tableclothColorList="${events.getAt(index)?.tableclothColors?.name ?: params?.tableclothColors}"/>

        <ucc:tableType
            tableTypeList="${events.getAt(index)?.tableTypes?.name ?: params?.tableTypes}"/>

        <ucc:chairs
            chairTypeList="${events.getAt(index)?.chairTypes?.name ?: params?.chairTypes}"/>

        <div class="form-group">
            <label for="presidiumTable">Meza presidium</label>
            <input
                type="number"
                name="presidiumTable"
                id="presidiumTable"
                value="${!events.getAt(index)?.presidiumTable && !params?.presidiumTable ? 0 : events.getAt(index)?.presidiumTable ?: params?.presidiumTable}"
                min="0"
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
                value="${!events.getAt(index)?.refreshment && !params?.refreshment ? 0 : events.getAt(index)?.refreshment ?: params?.refreshment}"
                class="form-control"
                min="0">
        </div>

        <div class="form-group">
            <label for="breakfast">Desayunos</label>
            <input
                type="number"
                name="breakfast"
                id="breakfast"
                value="${!events.getAt(index)?.breakfast && !params?.breakfast ? 0 : events.getAt(index)?.breakfast ?: params?.breakfast}"
                class="form-control"
                min="0">
        </div>

        <div class="form-group">
            <label for="lunch">Almuerzo</label>
            <input
                type="number"
                name="lunch"
                id="lunch"
                value="${!events.getAt(index)?.lunch && !params?.lunch ? 0 : events.getAt(index)?.lunch ?: params?.lunch}"
                class="form-control"
                min="0">
        </div>

        <div class="form-group">
            <label for="dinner">Cena</label>
            <input
                type="number"
                name="dinner"
                id="dinner"
                value="${!events.getAt(index)?.dinner && !params?.dinner ? 0 : events.getAt(index)?.dinner ?: params?.dinner}"
                class="form-control"
                min="0">
        </div>
    </div>
</div>

<div class="form-group">
    <label for="observation">Observacion</label>
    <g:textArea
        name="observation"
        value="${events.getAt(index)?.observation ?: params?.observation}"
        rows="1"
        class="form-control"/>
</div>
