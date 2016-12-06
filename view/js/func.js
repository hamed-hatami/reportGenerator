var time = null;
var outOfWindow = false;
var bAltF4AsLast = false;
var pageRefreshed = false;
var modalVisibility = false;
var charactersRestrictedInFunctions = new Array(48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 8, 0);
var signPin;
var errorDivCounter = 1;

$("#login-button").click(function (event) {
    event.preventDefault();

    $('form').fadeOut(500);
    $('.wrapper').addClass('form-success');
});

function checkControlKeys(e) {
    //alert('checkControlKeys');
    var sKey;
    var objEvent;

    if (window.event) {
        sKey = window.event.keyCode;
    }
    else if (e) {
        sKey = e.which;
    }

    objEvent = e || window.event;

    if (sKey == 116 && objEvent.ctrlKey) {
        pageRefreshed = true;
    } else {
        pageRefreshed = false;
    }

    if (sKey == 116) {
        pageRefreshed = true;
    } else {
        pageRefreshed = false;
    }

    if (sKey == 115 && objEvent.altKey) {
        bAltF4AsLast = true;
        // logout();
    }
}

function checkClosed() {
    if (!pageRefreshed && outOfWindow) {
        //logout();
    }
}

document.onmouseout = function outOfwindowTrue() {
    outOfWindow = true;
}

document.onmouseover = function outOfwindowFalse() {
    outOfWindow = false;
}

function checkUnload() {
    if (!bAltF4AsLast) {
        checkClosed();
    }
}

function reload() {
    var container = document.getElementById("requestForm:parameterTable");
    var content = container.innerHTML;
    container.innerHTML = content;
}

function downloadTool() {
    document.getElementById("responseForm:downloadButtonId").click();
}

function initSignature() {
    try {
        if (document.getElementById("pinId").value == "") {
            return true;
        }
        document.getElementById("frm:signatureId").value = "";
        var txt = document.app.signAgent(document.getElementById("frm:usernameId").value, document.getElementById("pinId").value);
        if (txt == "-1") {
            alert('#{bundle.token_removed}');
        } else if (txt == "-2") {
            alert('#{bundle.pin_incorrect}');
        } else {
            document.getElementById("frm:signatureId").value = txt;
            document.getElementById("frm:pinVal").value = document.getElementById("pinId").value;
            return true;
        }
    } catch (e) {
        alert(e);
    }
    return false;
}

function showErrorDiv(errorMsg, locale) {
    var containerId = "errorDivContainer" + errorDivCounter++;
    var localeClass = locale == 'fa' ? "persian-message-notify" : "english-message-notify";
    var errorContainer = "<div id=" + containerId + " class='rf-ntf rf-ntf-wrn " + localeClass + "'> <div class='rf-ntf-cnt'> <div class='rf-ntf-sum' id='showErrorDiv'>" + errorMsg + "</div> </div> </div>";
    $("body").append(errorContainer);
    setTimeout(function () {
        $("#" + containerId).hide(250);
    }, 3000);
    setTimeout(function () {
        $("#" + containerId).remove();
    }, 4000);
}

function usedInReturnPopup() {
}
function loginToSystem(e) {

    var key;
    if (window.event) {
        key = window.event.keyCode;
    } else {
        key = e.which;
    }
    if (key == 13) {
        document.getElementById('loginForm').submit();
    }
    else {
        return false;
    }
}


function newWindow(file, window, width, height, scroll, toolbar, menubar, directory, location, resizable, status) {
    var leftPosition, topPosition;
    leftPosition = (this.window.screen.width / 2) - ((width / 2) + 10);
    topPosition = (this.window.screen.height / 2) - ((height / 2) + 50);
    msgWindow = open(file, window, 'copyhistory=no,directories=' + directory + ',location=' + location + ',menubar=' + menubar + ',resizable=' + resizable + ',toolbar=' + toolbar + ',status=' + status + ',width=' + width + ',height=' + height + ',scrollbars=' + scroll + ',left=' + leftPosition + ',top=' + topPosition);
    if (msgWindow.opener == null)
        msgWindow.opener = self;
}

function printModal(str) {
    var d = document.getElementById(str);
    d.className = d.className + " noprint";
    var title = document.getElementById('templateCollapsiblePanel');
    title.className = title.className + " noprint";
    window.print();
}

function closePrintModal(str) {
    var d = document.getElementById(str);
    if (d.className.indexOf("noprint") != -1) {
        d.className = d.className.replace
        (/(?:^|\s)noprint(?!\S)/g, '')
    }
    var title = document.getElementById('templateCollapsiblePanel');
    if (title.className.indexOf("noprint") != -1) {
        title.className = title.className.replace
        (/(?:^|\s)noprint(?!\S)/g, '')
    }
}


function printDiv(divName) {

    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}

function printPage() {
    var noPrintWindow = document.getElementById('noprint');
    noPrintWindow.className = noPrintWindow.className + " noprint"

    window.print();

    if (noPrintWindow.className.indexOf("noprint") != -1) {
        noPrintWindow.className = noPrintWindow.className.replace(/(?:^|\s)noprint(?!\S)/g, '')
    }
}

function mouseOver(Obj) {
    Obj.style.color = "#cc0033";
}

function mouseOut(Obj) {
    Obj.style.color = "#000066";
}

function setFocus() {
}

function cacheValue(param1, param2) {
    document.getElementById(param1).value = param2;
}

function assignmentToAccountTextBox(responsibleId, responsibleFieldId) {
    document.getElementById(responsibleFieldId).value = responsibleId;
}
function invokeWorkgroup() {
}
function invokeRole() {
}
function invokeMember() {
}
function invokeUser() {
}
function invokeRing() {
}
function invokeOrganization() {
}
function invokeBaseInformationParent() {
}
function invokeBaseInformationChild() {
}
function invokeExit() {
}

function fillFamilyHandlePageFields(textBoxNationalCode, textBoxname, textBoxlastname, textBoxBirthDate, textBoxfatherName, textBoxRelationship, textBoxjob) {
    document.getElementById('FamilyInformation-lov-subview:FamilyInformationFRM:textBoxNationalCode').value = textBoxNationalCode;
    document.getElementById('FamilyInformation-lov-subview:FamilyInformationFRM:textBoxname').value = textBoxname;
    document.getElementById('FamilyInformation-lov-subview:FamilyInformationFRM:textBoxlastname').value = textBoxlastname;
    document.getElementById('FamilyInformation-lov-subview:FamilyInformationFRM:textBoxBirthDate').value = textBoxBirthDate;
    document.getElementById('FamilyInformation-lov-subview:FamilyInformationFRM:textBoxfatherName').value = textBoxfatherName;
    document.getElementById('FamilyInformation-lov-subview:FamilyInformationFRM:textBoxRelationship').value = textBoxRelationship;
    document.getElementById('FamilyInformation-lov-subview:FamilyInformationFRM:textBoxjob').value = textBoxjob;
}

function timerForReload() {
    setTimeout("submitByTimer()", 1000);
}

function submitByTimer() {
    document.forms['progressForm'].submit();
}

function checkShowLockScreen() {
    if (document.getElementById('lockValue').value == 'true') {
        Richfaces.showModalPanel('lockscreenPanel');
    }
}

function checkHideLockScreen() {
    if (document.getElementById('lockValue').value == 'false') {
        Richfaces.hideModalPanel('lockscreenPanel');
    }
}

function showLockScreen() {
    Richfaces.showModalPanel('lockscreenPanel');
}

function hideLockScreen() {
    Richfaces.hideModalPanel('lockscreenPanel');
}


function showModal() {
    Richfaces.showModalPanel('ajaxLoadingModalBox');
    modalVisibility = true;
}

function hideModal() {
    Richfaces.hideModalPanel('ajaxLoadingModalBox');
    modalVisibility = false;
}

function backDisabled() {
    if (window.history.forward(1) != null) {
        window.history.forward(1);
    }
    if (window.event && window.event.keyCode == 8) {
        window.event.keyCode = 123;
    }
}


function mouseOver(Obj) {
    Obj.style.color = "#cc0033";
}

function mouseOut(Obj) {
    Obj.style.color = "#000066";
}

function topPage() {
    window.scrollTo(0, 0);
}

function moveText() {
    //self.moveBy(x,y);
}

function refreshText() {
    window.setTimeout('moveText()', 100);
}

//============================================= usage :  onkeypress="return convert(this,event);" ===========================
var lastkey = 0;
var farsi = true;
var s = new Array(32, 33, 34, 35, 36, 37, 1548, 1711, 41, 40, 215, 43, 1608, 45, 46, 47, 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 58, 1603, 44, 61, 46, 1567, 64, 1616, 1584, 125, 1609, 1615, 1609, 1604, 1570, 247, 1600, 1548, 47, 8217, 1583, 215, 1563, 1614, 1569, 1613, 1601, 8216, 123, 1611, 1618, 1573, 126, 1580, 1688, 1670, 94, 95, 1662, 1588, 1584, 1586, 1610, 1579, 1576, 1604, 1575, 1607, 1578, 1606, 1605, 1574, 1583, 1582, 1581, 1590, 1602, 1587, 1601, 1593, 1585, 1589, 1591, 1594, 1592, 60, 124, 62, 1617);
var b = navigator.userAgent.toLowerCase();
var msie = (b.indexOf('msie') > -1) ? true : false;
var gecko = (b.indexOf('gecko') > -1) ? true : false;
var opera = (b.indexOf('opera') > -1) ? true : false;
if (opera) {
    msie = false;
}


function changelang(fld) {
    if (farsi) {
        farsi = false;
    } else {
        farsi = true;
    }
    if (fld) {
        document.getElementsByName(fld)[0].focus();
    }
}


function PersianOnly(fld, e, locale) {
    var key;
    if (window.event) {
        key = window.event.keyCode;
    } else {
        key = e.which;
    }

    if (((key >= 33) && (key <= 38)) || ((key >= 40) && (key <= 43))
        || (key == 94) || (key == 95) || (key == 1567)) {
        alertPersianOnlyError(locale);
        return false;
    }

    if (msie) {
        k = event.keyCode;
        if (farsi && k > 32 && k < 128) event.keyCode = s[k - 32];
    }

    if (gecko) {
        k = e.which;
        if (farsi && (k > 32 && k < 128) && !e.ctrlKey && !e.altKey && !e.metaKey) {
            fld.value = fld.value + String.fromCharCode(s[k - 32]);
            alertPersianOnlyError(locale);
            return false;
        }
    }

    if (opera) {
        k = event.keyCode;
        if (k > 32 && k < 128 && !event.ctrlKey && !event.altKey && !event.metaKey) {
            fld.value = fld.value + String.fromCharCode(s[k - 32]);
            alertPersianOnlyError(locale);
            return false;
        }
    }
}

function alertPersianOnlyError(locale) {
    if (locale == 'fa') {
        showErrorDiv("صفحه کلید خود را در حالت فارسی قرار دهید.", locale);
    } else {
        showErrorDiv("Change your keyboard to Persian Mode", locale);
    }
}


function englishOnly(e, locale) {
    var key;
    if (window.event) {
        key = window.event.keyCode;
    } else {
        key = e.which;
    }

    if (((key >= 48) && (key <= 57)) || ((key >= 97) && (key <= 122)) || key == 13 || key == 8 || key == 0 || ((key >= 65) && (key <= 90)))
        return true;
    else if (locale == 'fa') {
        showErrorDiv("صفحه کلید خود را در حالت انگلیسی قرار دهید", locale);
    } else {
        showErrorDiv("Change your keyboard to English Mode", locale);
    }
    return false;
}

function lettersOnly(e, locale) {
    var key;
    if (window.event) {
        key = window.event.keyCode;
    } else {
        key = e.which;
    }

    var isOk = key == 8 || key == 32 || key == 0 || key == 13        //backspace , space, Esc ,enter
        || (key >= 97 && key <= 122) || (key >= 65 && key <= 90)     //english letters
        || (key >= 1575 && key <= 1740) || key == 1570;              //persian letters

    if (isOk) {
        return true;
    } else {
        if (locale == 'fa') {
            showErrorDiv("فقط حروف فارسی و انگلیسی مجاز می باشد", locale);
        } else {
            showErrorDiv("Only English and Persian alphabet is valid", locale);
        }
        return false;
    }
}

function numbersOnlyAllCharacters(fld, e) {
    var KeyID;
    if (window.event) {
        KeyID = window.event.keyCode;
    } else {
        KeyID = e.which;
    }

    if (KeyID == 0) {
        var numbers = "0123456789";
        var value = fld.value;

        for (var index = 0; index < value.length; index++) {
            var character = value.charAt(index);
            if (numbers.indexOf(character) == -1) {
                fld.value = '';
                return false;
            }
        }
    }

    return true;
}


function isUserFriendlyChar(val) {
    if (val == 8 || val == 9 || val == 13 || val == 45 || val == 46)
        return true;

    if ((val > 16 && val < 21) || (val > 34 && val < 41))
        return true;
    return false;
}

function autoTab(current, to) {
    if (current.value.length == current.getAttribute("maxlength")) {
        to.select();
        to.focus();
    }
}

function confirmAndWait(message) {
    var answer = window.confirm(message);
    if (answer) {
        showModal();
    }
    return answer;
}

function setDefaultFocus(elementId) {
    var element = document.getElementById(elementId);
    if (element != null) {
        element.focus();
    }
}

function numbersAndSlashOnly(element, localevalue) {
    var inputString = element.value;
    var size = element.getAttribute('maxlength');
    if (!!localevalue && localevalue == 'fa') {
        inputString = convertNumberToFarsiSlash(inputString);
    } else {
        inputString = convertNumberToEnglishSlash(inputString);
    }
    if (!!size) {
        inputString = inputString.substring(0, size).trim();
    }
    element.value = inputString;
    return false;
}


function convertNumberToFarsiSlash(number) {
    var farsiNumber = '';
    for (var i = 0; i < number.length; i++) {
        var inputChar = number.charAt(i);
        var outputChar = '';
        switch (inputChar) {
            case '0':
            case '۰':
                outputChar = '۰';
                break;
            case '1':
            case '۱':
                outputChar = '۱';
                break;
            case '2':
            case '۲':
                outputChar = '۲';
                break;
            case '3':
            case '۳':
                outputChar = '۳';
                break;
            case '4':
            case '۴':
                outputChar = '۴';
                break;
            case '5':
            case '۵':
                outputChar = '۵';
                break;
            case '6':
            case '۶':
                outputChar = '۶';
                break;
            case '7':
            case '۷':
                outputChar = '۷';
                break;
            case '8':
            case '۸':
                outputChar = '۸';
                break;
            case '9':
            case '۹':
                outputChar = '۹';
                break;
            case '/':
                outputChar = '/';
                break;
            default:
                showErrorDiv("لطفا در این فیلد فقط عدد یا ' / ' وارد کنید", 'fa');
                break;
        }
        farsiNumber += outputChar;
    }
    return farsiNumber;
}

function convertNumberToEnglishSlash(number) {
    var englishNumber = '';
    for (var i = 0; i < number.length; i++) {
        var inputChar = number.charAt(i);
        var outputChar = '';
        switch (inputChar) {
            case '0':
            case '۰':
                outputChar = '0';
                break;
            case '1':
            case '۱':
                outputChar = '1';
                break;
            case '2':
            case '۲':
                outputChar = '2';
                break;
            case '3':
            case '۳':
                outputChar = '3';
                break;
            case '4':
            case '۴':
                outputChar = '4';
                break;
            case '5':
            case '۵':
                outputChar = '5';
                break;
            case '6':
            case '۶':
                outputChar = '6';
                break;
            case '7':
            case '۷':
                outputChar = '7';
                break;
            case '8':
            case '۸':
                outputChar = '8';
                break;
            case '9':
            case '۹':
                outputChar = '9';
                break;
            case '/':
                outputChar = '/';
                break;
            default:
                showErrorDiv("enter number or ' / ' please", 'en');
                break;
        }
        englishNumber += outputChar;
    }
    return englishNumber;
}

function removeLastUploadedFiles(element) {
    var listDiv = element.getElementsByClassName("rf-fu-lst")[0];
    var count = listDiv.getElementsByClassName("rf-fu-itm").length;
    if (count > 1) {
        listDiv.removeChild(listDiv.childNodes[0])
    }
}

function disableAllElementByClassName(className) {
    var inputs = document.getElementsByClassName(className);
    for (var i in inputs) {
        inputs[i].disabled = true;
    }
}

function openLastRequestInfoWindow() {
    var value = document.getElementById("sendForm:sentChequeLabel").innerHTML;
    if (value != undefined && value != "") {
        newWindow('/nejat/print/print-cheque.htm?SeqNo=' + value
            , '#{bundle.viewAck}' + value
            , 600
            , window.screen.height / 3
            , 'yes', 'no', 'no', 'no', 'no', 'no', 'no');
    }
}

function timerForReloadSendCheque() {
    var signPin = document.getElementById("sendForm:book").value;
    if (signPin != null && signPin != "" && signPin != undefined) {
        try {
            if (!document.app.smartCardPluggedChecker(signPin)) {
                logout();
                return;
            }
        } catch (e) {
            logout();
            return;
        }
        var sendChequeButton = document.getElementById("sendForm:sendChequeButton");
        if (sendChequeButton != null) {
            sendChequeButton.disabled = true;
        }
        var signatureChequeButton = document.getElementById("sendForm:signatureChequeButton");
        if (signatureChequeButton != null) {
            signatureChequeButton.disabled = false;
        }
        setTimeout("signContent()", 500);
    }
}

function signContent() {
    var signedContent = null;
    try {
        if (document.getElementById("sendForm:chequeContentId").value != "") {
            showWaitingPanel();
            document.getElementById("sendForm:signatureChequeButton").disabled = true;
            signedContent = document.app.signXML(document.getElementById("sendForm:chequeContentId").value, signPin);
            if (signedContent != null) {
                document.getElementById("sendForm:signedChequeContentId").value = signedContent;
                document.getElementById("sendForm:sendChequeButton").disabled = false;
                disableAllElementByClassName("sendFormInput");
                document.getElementById('buttonsettlementDate').onclick = function () {
                };
            }
            hideWaitingPanel();
        }
    } catch (e) {
        alert(e);
    }
}

function showWaitingPanel() {
    document.getElementById("waitingPanel").style = '';
    document.getElementById("waitingPanel_container").style = "position: absolute; z-index: 5000; left: 600px; top: 200px;";
}

function hideWaitingPanel() {
    document.getElementById("waitingPanel").style.visibility = 'hidden';
}

function cleanSerialErrorMsg(formId) {
    var seriesMsg = document.getElementById(formId + ":chequeSeriesMsg").innerHTML;
    if (seriesMsg != "") {
        document.getElementById(formId + ":chequeSerialMsg").innerHTML = "";
    }
}

function toggleByIds(ids) {
    for (var i = 0; i < ids.length; i++) {
        toggleById(ids[i]);
    }
}
function toggleById(id) {
    var element = document.getElementById(id);
    try {
        var display = element.style.display;
        if (display == 'none') {
            element.style.display = '';
        } else {
            element.style.display = 'none';
        }
    } catch (e) {
    }
}


function setAuthFieldSetDisplay(id) {
    var element = document.getElementById(id);
    try {
        var inputs = document.getElementById('sendResponseForm:accountType').getElementsByTagName('input');
        var checkedInput;
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].checked == true) {
                checkedInput = inputs[i];
                break;
            }
        }
        if (checkedInput.value == '1') {
            element.style.display = 'none';
        } else {
            element.style.display = 'block';
            var hasOtherAuthorised = document.getElementById('sendResponseForm:hasOtherAuthorisedCheckbox').checked;
            if (hasOtherAuthorised) {
                document.getElementById('authorizeTable').style.display = 'block';
            } else {
                document.getElementById('authorizeTable').style.display = 'none';
            }
        }
    } catch (e) {
    }
}

function setPrintedStyleClass(elementId) {
    document.getElementById(elementId).parentNode.parentNode.classList.add('printed');
}

function setCurrentRowPrintId(elementId) {
    var hideInput = document.getElementById("currentRowPrintId");
    hideInput.value = elementId;
}
function setCurrentRowPrintIdThatPrinted(elementId) {
    var hideInput = document.getElementById("printedRowIds");
    if (!hideInput.value.contains(elementId)) {
        hideInput.value = elementId + ',' + hideInput.value;
    }
}

function hideCurrentRowInDashboard(isOk) {
    if (isOk) {
        setCurrentRowPrintIdThatPrinted(document.getElementById("currentRowPrintId"));
    }
    var elementIds = document.getElementById("printedRowIds").value;
    elementIds.split(',').forEach(function (value, index, ar) {
        try {
            if (value != '') {
                var element = document.getElementById(value);
                if (element != null) {
                    element.classList.add('hide');
                    element.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.classList.add('printed');
                }
            }
        } catch (e) {
        }
    })
}
function setCurrentRequestPrintId(elementId) {
    var hideInput = document.getElementById("printedRequestIds");
    if (!hideInput.value.contains(elementId)) {
        hideInput.value = elementId + ',' + hideInput.value;
    }
}

function hideCurrentRequestInDashboard() {
    var elementIds = document.getElementById("printedRequestIds").value;
    elementIds.split(',').forEach(function (value, index, ar) {
        try {
            if (value != '') {
                var element = document.getElementById(value);
                if (element != null) {
                    var trNode = element.parentNode.parentNode;
                    trNode.classList.add('printed');
                    trNode.getElementsByClassName('printedFlag')[0].classList.add('trueSymbol');
                    trNode.getElementsByClassName('printedFlag')[0].classList.remove('falseSymbol');
                }
            }
        } catch (e) {
        }
    })
}

function fixReasonsStyle(elementId) {
    try {
        var element = document.getElementById(elementId);
        if (element.innerHTML == '' || element.innerHTML == ' ') return;
        var separator = '&bull;&nbsp;';

        element.innerHTML = element.innerHTML.replace(',', '<br/>' + separator);
        if (element.innerHTML.contains(',')) {
            fixReasonsStyle(elementId);
        } else {
            element.innerHTML = separator + element.innerHTML;
        }
    } catch (e) {
    }
}

function confirmAction(confirmMsg) {
    if (confirm(confirmMsg)) {
        showWaitingPanel();
        return true
    } else {
        return false;
    }
}

function ibanInformal(element) {
    var inputString = element.value;
    var size = element.getAttribute('maxlength');
    inputString = convertNumberToEnglish(inputString);
    var informalIBAN = inputString;

    if (!!size) {
        inputString = inputString.substring(0, size).trim();
    }

    informalIBAN = inputString.substring(0, 2) + " ";
    for (i = 2; i < 22 && i < inputString.length; i += 4) {
        informalIBAN += inputString.substring(i, i + 4) + " ";
    }
    informalIBAN += inputString.substring(22, 24);

    element.value = informalIBAN.trim();
    return false;
}

jQuery(window).load(function () {
    if (jQuery('.fixed-enabled').length > 0) {
        var headerHeight = jQuery('.fixed-enabled').offset().top;
        var mainNav = jQuery('.fixed-enabled');
        var gafVar = 50
        jQuery(window).scroll(function () {
            var scrollY = jQuery(window).scrollTop();
            if (scrollY > headerHeight + gafVar) {
                mainNav.addClass('fixed-nav');
            } else if (scrollY < headerHeight) {
                mainNav.removeClass('fixed-nav');
            }
        });
    }
});