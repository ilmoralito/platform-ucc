<div class="row">
    <div class="col-md-6">
        <label for="dateOfTheEvent">Fecha del evento</label>
        <div class="form-group">
            <g:textField
                name="dateOfTheEvent"
                class="form-control"/>
        </div>
    </div>

    <div class="col-md-6">
        <div class="form-group">
            <label for="numberOfPeople">Numero de asistentes</label>
            <input
                type="number"
                name="numberOfPeople"
                id="numberOfPeople"
                class="form-control"
                min="1">
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <label for="startTime">Hora inicial</label>
        <div class="form-group">
            <g:select
                name="startTime"
                from="['8:00', '9:00', '10:00', '11:00', '12:00', '1:00', '2:00', '3:00', '4:00']"
                class="form-control"/>
        </div>
    </div>
    
    <div class="col-md-6">
        <div class="form-group">
            <label for="endingTime">Hora de finalizacion</label>
            <g:select
                name="endingTime"
                from="['9:00', '10:00', '11:00', '12:00', '1:00', '2:00', '3:00', '4:00', '5:00']"
                class="form-control"/>
        </div>
    </div>
</div>

<ucc:classrooms/>

<div class="row">
    <div class="col-md-3">
        <p>Medios</p>

        <div class="checkbox">
            <label>
                <g:checkBox name="audiovisual"/>
                Datashow
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="wifi"/>
                Wifi
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="audio"/>
                Audio
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="speaker"/>
                Parlantes de computadora
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="microfone"/>
                Microfono
            </label>
        </div>
    </div>

    <div class="col-md-3">
        <p>Refrescos</p>

        <div class="checkbox">
            <label>
                <g:checkBox name="water"/>
                Agua
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="coffee"/>
                Cafe
            </label>
        </div>

        <div class="form-group">
            <label for="cookies">Galletas</label>
            <input
                type="number"
                name="cookies"
                id="cookies"
                value="${event?.cookies}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="waterBottles">Botellas de agua</label>
            <input
                type="number"
                name="waterBottles"
                id="waterBottles"
                value="${event?.waterBottles}"
                class="form-control"
                min="1">
        </div>
    </div>

    <div class="col-md-3">
        <p>Montaje</p>
        <ucc:mountingType/>

        <p>Elementos</p>
        <div class="checkbox">
            <label>
                <g:checkBox name="flags"/>
                Banderas
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="podium"/>
                Podium
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="tableForSpeaker"/>
                Mesa para expositor
            </label>
        </div>

        <div class="checkbox">
            <label>
                <g:checkBox name="tablecloths"/>
                Manteles
            </label>
        </div>

        <div class="form-group">
            <label for="presidiumTable">Meza presidium</label>
                <input
                    type="number"
                    id="presidiumTable"
                    name="presidiumTable"
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
                value="${event?.refreshment}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="breakfast">Desayunos</label>
            <input
                type="number"
                name="breakfast"
                id="breakfast"
                value="${event?.breakfast}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="lunch">Almuerzo</label>
            <input
                type="number"
                name="lunch"
                id="lunch"
                value="${event?.lunch}"
                class="form-control"
                min="1">
        </div>

        <div class="form-group">
            <label for="dinner">Cena</label>
            <input
                type="number"
                name="dinner"
                id="dinner"
                value="${event?.dinner}"
                class="form-control"
                min="1">
        </div>
    </div>
</div>

<div class="form-group">
    <label for="observation">Observacion</label>
    <g:textArea
        name="observation"
        value=""
        class="form-control"
        />
</div>
