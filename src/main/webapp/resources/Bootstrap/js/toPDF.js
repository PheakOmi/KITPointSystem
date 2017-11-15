var
    form,
 a4 = [1065, 726.67]; // for a4 size paper width and height
$(document).ready(function () {
    $(".create_pdf").on("click", function (e) {
        $('body').scrollTop(0);
        var selector = $(e.target).attr('myprint');
        createPDF(selector);
    });
});

function createPDF(selector) {
    form = $(selector);
    getCanvas().then(function (canvas) {
        var cache_width = form.width();
        var cache_height= form.height();
        var myHeight=300;
        if (cache_height>400)
        	myHeight = cache_height;
        var
         img = canvas.toDataURL("image/png"),
         doc = new jsPDF({
        	 orientation: 'landscape',
             unit: 'px',
             format: [cache_width/1.7, myHeigh-100]
         });
        doc.addImage(img, 'JPEG', 20, 20);
        doc.addPage();
        doc.save('Project_Report.pdf');
        form.width(cache_width);	
        form.css("border-color", "yellow");
    });
}

// create canvas object
function getCanvas() {

    form.width((a4[0] * 1.33333) - 80).css('max-width', 'none');
    return html2canvas(form, {
        imageTimeout: 2000,
        removeContainer: true
    });
}

