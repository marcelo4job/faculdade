

    $.post('VerificaSessaoUsuarioServlet', {}, function (data) {
        if (!data) {
            window.location.href = "index.html";
        }
    })



