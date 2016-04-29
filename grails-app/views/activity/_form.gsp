<div class="form-group">
    <g:textField
        name="name"
        value="${activity?.name}"
        class="form-control"
        autofocus="true"
        placeholder="Nombre de la actividad"/>
</div>

<div class="events">
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <g:textField
                    name="dateOfTheEvent"
                    class="form-control"
                    placeholder="Fecha del evento"/>
            </div>
        </div>

        <div class="col-md-6">
            <div class="form-group">
                <input
                    type="number"
                    name="numberOfPeople"
                    id="numberOfPeople"
                    class="form-control"
                    min="1"
                    placeholder="Numero de asistentes">
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <g:select
                    name="startTime"
                    from="['8:00', '9:00', '10:00', '11:00', '12:00', '1:00', '2:00', '3:00', '4:00']"
                    class="form-control"/>
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="form-group">
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
                    <g:checkBox name="audio" value=""/>
                    Audio
                </label>
            </div>

            <div class="checkbox">
                <label>
                    <g:checkBox name="speaker" value=""/>
                    Parlantes de computadora
                </label>
            </div>

            <div class="checkbox">
                <label>
                    <g:checkBox name="microfone" value=""/>
                    Microfono
                </label>
            </div>
        </div>

        <div class="col-md-3">
            <p>Refrigerios</p>
    
            <div class="checkbox">
                <label>
                    <g:checkBox name="water" value=""/>
                    Agua
                </label>
            </div>

            <div class="form-group">
                <label for="coffee">Cafe</label>
                <input
                    type="number"
                    name="coffee"
                    id="coffee"
                    value="${event?.coffee}"
                    class="form-control"
                    min="1">
            </div>

            <div class="form-group">
                <label for="tea">Te</label>
                <input
                    type="number"
                    name="tea"
                    id="tea"
                    value="${event?.tea}"
                    class="form-control"
                    min="1">
            </div>

            <div class="form-group">
                <label for="cakeShop">Resposteria</label>
                <input
                    type="number"
                    name="cakeShop"
                    id="cakeShop"
                    value="${event?.cakeShop}"
                    class="form-control"
                    min="1">
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
                    <g:checkBox name="flags" value=""/>
                    Banderas
                </label>
            </div>

            <div class="checkbox">
                <label>
                    <g:checkBox name="podium" value=""/>
                    Podium
                </label>
            </div>

            <div class="checkbox">
                <label>
                    <g:checkBox name="tableForSpeaker" value=""/>
                    Mesa para expositor
                </label>
            </div>

            <div class="checkbox">
                <label>
                    <g:checkBox name="tablecloths" value=""/>
                    Manteles
                </label>
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
        <g:textArea
            name="observation"
            value=""
            class="form-control"
            placeholder="Observacion"
            style="resize:vertical; min-height: 50px; max-height:125px;"
            />
    </div>
</div>
