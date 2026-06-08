<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar" id="sidebar">
    <div class="sidebar-brand px-4 py-3 mb-3 border-bottom">
        <h4 class="fw-bold text-primary m-0"><i class="fas fa-rocket me-2"></i>Tracker</h4>
    </div>
    <div class="nav flex-column">
        <a href="${pageContext.request.contextPath}/dashboard/dashboard.jsp" class="nav-link"><i class="fas fa-home"></i> Dashboard</a>
        <a href="${pageContext.request.contextPath}/questions/questions.jsp" class="nav-link"><i class="fas fa-code"></i> Questions</a>
        <a href="${pageContext.request.contextPath}/goals/goals.jsp" class="nav-link"><i class="fas fa-bullseye"></i> Goals</a>
        <a href="${pageContext.request.contextPath}/revisions/revisions.jsp" class="nav-link"><i class="fas fa-calendar-check"></i> Revision Planner</a>
        <a href="${pageContext.request.contextPath}/analytics/analytics.jsp" class="nav-link"><i class="fas fa-chart-pie"></i> Analytics</a>
        <a href="${pageContext.request.contextPath}/notes/notes.jsp" class="nav-link"><i class="fas fa-sticky-note"></i> Notes</a>
        <a href="${pageContext.request.contextPath}/profile/profile.jsp" class="nav-link"><i class="fas fa-user"></i> Profile</a>
    </div>
    <div class="mt-auto px-4 pb-4">
        <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-outline-danger w-100 fw-medium">
            <i class="fas fa-sign-out-alt"></i> Logout
        </a>
    </div>
</div>
