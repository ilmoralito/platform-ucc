<ul id="nav" class="nav nav-tabs">
    <li role="presentation" id="tab1" class="active">
        <a href="#">1</a>
    </li>
    <li role="presentation">
        <a href="#" id="addTabTrigger">+</a>
    </li>
</ul>
<g:if test="${!session?.events}">
    <div class="row">
        <div class="col-md-12">
            <div class="pull-right">
                <div class="btn-group btn-group-xs">
                    <a href="#">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</g:if>
