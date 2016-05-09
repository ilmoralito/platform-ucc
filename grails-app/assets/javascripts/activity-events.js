/*
$(function() {
    var eventCollection = [];

    $(document).on('click', '#addTabTrigger', function(e) {
        e.preventDefault();

        var events = $('#events');
        var event = events.children().last();
        var nav = $('#nav');
        var tabs = nav.children()
        var beforeLastTab = tabs.eq(-2);
        var beforeLastTabValue = beforeLastTab.text().trim();
        var newTab = $('<li>', { role: 'presentation', id: 'tab' + (+beforeLastTabValue + 1) });
        var anchorForNewTab = $('<a>', { href: '#', text: +beforeLastTabValue + 1 });

        newTab.append(anchorForNewTab)
        beforeLastTab.after(newTab);

        event.clone().attr('id', 'event' + (+beforeLastTabValue + 1)).hide().appendTo(events);
    });

    $(document).on('click', '.deleter', function() {
        var $this = $(this),
            events = $('#events').children(),
            eventsLength = events.length - 1,
            currentEvent = $(this).parent().parent().parent().parent().parent(), // TODO: FBS for getting $this event parent
            currentEventId = currentEvent.attr('id'),
            nav = $('#nav'),
            tabs = nav.children(),
            currentTab = '#tab' + currentEventId.match(/\d/g).join('');

        events.hide();

        if (currentEvent.index() === 0) {
            currentEvent.next().show()
            $(currentTab).next().addClass('active');
        } else if (eventsLength >= 1) {
            tabs.removeClass('active');
            currentEvent.prev().show()
            $(currentTab).prev().addClass('active');
        }

        // Delete event box and content
        currentEvent.remove();

        // Delete li tab in nav
        $(currentTab).remove()
    });

    $(document).on('click', '#nav li:not(:last-child)', function(e) {
        e.preventDefault();

        var $this = $(this);
        var index = $this.index() + 1;

        $this.siblings().removeClass('active');
        $this.addClass('active');

        $('#events').children().hide();
        $('#event' + index).show();
    });
});
*/
