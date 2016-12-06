var IDLE_TIMEOUT = 600; //seconds
var _idleSecondsCounter = 0;
document.onclick = function () {
    _idleSecondsCounter = 0;
};
document.onmousemove = function () {
    _idleSecondsCounter = 0;
};
document.onkeypress = function () {
    _idleSecondsCounter = 0;
};


window.setInterval(CheckIdleTime, 1000);


function CheckIdleTime() {
    _idleSecondsCounter++;
    if (_idleSecondsCounter >= IDLE_TIMEOUT) {
        logout();
    }

}
function setIdleTimeout(time){
    IDLE_TIMEOUT = time * 60;
}
