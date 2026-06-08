<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar-top glass">
    <div class="d-flex align-items-center">
        <button class="btn btn-light d-md-none me-2" id="sidebarToggle">
            <i class="fas fa-bars"></i>
        </button>
        <h5 class="m-0 fw-bold d-none d-md-block text-dark">Interview Tracker</h5>
    </div>
    <div class="user-profile d-flex align-items-center gap-3">
        <span class="text-secondary fw-medium">Welcome, ${sessionScope.user.name}</span>
        <img src="${pageContext.request.contextPath}/images/${not empty sessionScope.user.profilePhoto ? sessionScope.user.profilePhoto : 'profile-default.png'}" 
             alt="Profile" class="rounded-circle border" width="40" height="40" style="object-fit: cover;">
    </div>
</div>
