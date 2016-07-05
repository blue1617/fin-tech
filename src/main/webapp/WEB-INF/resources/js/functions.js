function updateBasket(id) {
    var selected = $('#' + id)[0].checked;
    $.post("/select",{id: id, selected: selected}, function(data)
    {
        $("#resultBlock").html(data);
    });
}
