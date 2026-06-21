$(document).ajaxSend(function (e,xhr,opt) {
    var token = localStorage.getItem("user_token");
    xhr.setRequestHeader("user_token_header",token)
});
$(document).ajaxError(function (event, jqXHR, ajaxSettings, thrownError) {
    if (jqXHR !== null && jqXHR.status === 401) {
        location.href = "blog_login.html";
    }
});

function logout() {
    localStorage.removeItem("user_token");
    location.href = "blog_login.html";
}