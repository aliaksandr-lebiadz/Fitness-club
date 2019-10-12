$(document).ready(function(){
    $('.display-table tr').on('click', function() {
        $('.display-table tr').removeClass('selected');
        $(this).addClass('selected');
        var $currentRow = $(this).closest('tr');
        var $id = $currentRow.find('td:eq(0)').text();
        $('.hidden-id').val($id);
    });
});