$(function() {
    verify(1)
})

function goBack() {
    sessionStorage.removeItem('token')
    jump('/index.html')
}