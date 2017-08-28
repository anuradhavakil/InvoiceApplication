/**
 * Created by avakil on 8/23/17.
 */

$(document).ready(function () {

    $("#Send-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

    var i=1;
    var totalAmount=0.00;
    $('#addItemButton').click(function() {
        $("table tbody").append('<tr id="'+i+'">'+
            '<td><input class="form-control" type="text" name="itemDescription" id="invoiceLineItemDescriptionInput_'+i+'" /></td>' +
            '<td><input class="form-control price" type="text" name="amount" id="invoiceLineAmount_'+i+'" /></td>' +
            '</tr>');
        totalAmount += parseFloat($('#invoiceLineAmount_'+(i-1)).val());
        i++;
        $("#totalAmount").html("Total Amount: $"+parseFloat(totalAmount).toFixed(2));
        event.preventDefault();
    });


});



function fire_ajax_submit() {
    var invoiceObject = {};
    var invoiceLineItems =[];
    invoiceObject["name"] = $("#name").val();
    invoiceObject["email"] = $("#email").val();
    invoiceObject["dueDate"] = $("#dueDate").val();
    
    // read table data in JSON Array
    var tableData=[];
    var isRowEmpty= false;
    $('table').find('tr').each(function(){
        var id=$(this).attr('id');
        if(!$.isEmptyObject(id)){
            var row={};
            $(this).find('input').each(function(){
                if(!$.isEmptyObject($(this).val())){
                    row[$(this).attr('name')]=$(this).val();
                } else  {
                    isRowEmpty = true;
                }
            });
            if(!isRowEmpty){
                tableData[id]=row;
            }
        }

    });

    invoiceObject["invoiceLineItems"] = tableData;

    /* var lineitems = [$("#itemDescription").val(),$("#amount").val()];
    lineitems.map(function(entry) {
        var singleObj = {};
        singleObj['itemDescription'] = $("#itemDescription").val();
        singleObj['amount'] = $("#amount").val();
        return singleObj;
    });
   */

    $("#btn-Send").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/invoice",
        data: JSON.stringify(invoiceObject),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-Send").prop("disabled", true);
            $("#totalAmount").html("Total Amount: $"+parseFloat(data.totalAmount).toFixed(2));
            $('#error').html("");
            $('#successInfo').html("<b><i>Invoice Created with Invoice Id:"+data.invoiceId +"</i></b>");

        },
        error: function (jqXHR, textStatus, errorThrown) {

          var errorJson = jQuery.parseJSON(jqXHR.responseText);

            console.log("ERROR : ", errorJson.msg);
            $("#btn-Send").prop("disabled", false);
            if(!$.isEmptyObject(errorJson.msg)){
                $('#error').html("Please correct following errors: " + errorJson.msg);
            }

        }
    });


}