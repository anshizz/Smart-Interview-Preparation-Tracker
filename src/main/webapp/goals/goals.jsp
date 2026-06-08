<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Goal Tracker</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
    <div class="wrapper">
        <jsp:include page="../components/sidebar.jsp" />
        <div class="main-content">
            <jsp:include page="../components/navbar.jsp" />
            <div class="content-body">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2 class="fw-bold m-0">Goal Tracker</h2>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addGoalModal"><i class="fas fa-plus"></i> Create Goal</button>
                </div>
                
                <div class="row g-4" id="goalsContainer">
                    <!-- Populated via AJAX -->
                </div>
            </div>
            <jsp:include page="../components/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
