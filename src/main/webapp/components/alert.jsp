<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 1100">
    <div id="liveToast" class="toast align-items-center text-white bg-danger border-0 ${not empty requestScope.error ? 'show' : 'hide'}" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body" id="toastMessage">
                <c:out value="${requestScope.error}"/>
            </div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<script>
    function showToast(message, type = 'danger') {
        const toastEl = document.getElementById('liveToast');
        const toastMsg = document.getElementById('toastMessage');
        toastEl.className = 'toast align-items-center text-white border-0 bg-' + type + ' show';
        toastMsg.innerText = message;
        setTimeout(() => { toastEl.classList.remove('show'); }, 3000);
    }
</script>
