<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Smart Interview Tracker</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .heatmap-grid { display: grid; grid-template-rows: repeat(7, 1fr); grid-auto-flow: column; gap: 3px; overflow-x: auto; padding-bottom: 10px; }
        .heatmap-cell { width: 12px; height: 12px; background-color: #ebedf0; border-radius: 2px; }
        .heatmap-cell[data-level="1"] { background-color: #9be9a8; }
        .heatmap-cell[data-level="2"] { background-color: #40c463; }
        .heatmap-cell[data-level="3"] { background-color: #30a14e; }
        .heatmap-cell[data-level="4"] { background-color: #216e39; }
    </style>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="../components/sidebar.jsp" />
        <div class="main-content">
            <jsp:include page="../components/navbar.jsp" />
            <div class="content-body">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2 class="fw-bold m-0">Dashboard Overview</h2>
                    <a href="${pageContext.request.contextPath}/questions/questions.jsp" class="btn btn-primary"><i class="fas fa-plus"></i> Add Question</a>
                </div>
                
                <!-- Metrics Row -->
                <div class="row g-4 mb-4">
                    <div class="col-md-3">
                        <div class="card h-100 d-flex flex-row align-items-center p-3">
                            <div class="bg-primary bg-opacity-10 text-primary p-3 rounded-circle me-3"><i class="fas fa-code fa-lg"></i></div>
                            <div><p class="text-secondary m-0 small fw-medium">Total Solved</p><h3 class="m-0 fw-bold">${analytics.totalQuestionsSolved}</h3></div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card h-100 d-flex flex-row align-items-center p-3">
                            <div class="bg-success bg-opacity-10 text-success p-3 rounded-circle me-3"><i class="fas fa-check-circle fa-lg"></i></div>
                            <div><p class="text-secondary m-0 small fw-medium">Easy Questions</p><h3 class="m-0 fw-bold">${analytics.easyCount}</h3></div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card h-100 d-flex flex-row align-items-center p-3">
                            <div class="bg-warning bg-opacity-10 text-warning p-3 rounded-circle me-3"><i class="fas fa-layer-group fa-lg"></i></div>
                            <div><p class="text-secondary m-0 small fw-medium">Medium Questions</p><h3 class="m-0 fw-bold">${analytics.mediumCount}</h3></div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card h-100 d-flex flex-row align-items-center p-3">
                            <div class="bg-danger bg-opacity-10 text-danger p-3 rounded-circle me-3"><i class="fas fa-fire fa-lg"></i></div>
                            <div><p class="text-secondary m-0 small fw-medium">Hard Questions</p><h3 class="m-0 fw-bold">${analytics.hardCount}</h3></div>
                        </div>
                    </div>
                </div>

                <!-- Readiness & Insights -->
                <div class="row g-4 mb-4">
                    <div class="col-md-4">
                        <div class="card h-100 text-center p-4">
                            <h5 class="fw-bold mb-4">Readiness Score</h5>
                            <div class="position-relative d-inline-block mx-auto mb-3" style="width: 150px; height: 150px;">
                                <svg class="w-100 h-100" viewBox="0 0 36 36">
                                    <path d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="#eee" stroke-width="3"/>
                                    <path d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="var(--primary)" stroke-width="3" stroke-dasharray="${readinessScore}, 100" class="circular-progress"/>
                                </svg>
                                <div class="position-absolute top-50 start-50 translate-middle">
                                    <h2 class="m-0 fw-bold">${readinessScore}%</h2>
                                </div>
                            </div>
                            <span class="badge bg-primary fs-6 px-3 py-2 rounded-pill">Placement Ready</span>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="card h-100">
                            <div class="card-header bg-transparent border-0 pt-3 pb-0"><h5 class="fw-bold"><i class="fas fa-lightbulb text-warning"></i> Smart Insights</h5></div>
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <c:forEach var="insight" items="${insights}">
                                        <li class="list-group-item px-0 py-3 d-flex align-items-center">
                                            <i class="fas fa-angle-right text-primary me-3"></i><span class="fw-medium">${insight}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Charts -->
                <div class="row g-4 mb-4">
                    <div class="col-md-6">
                        <div class="card p-3 h-100">
                            <h6 class="fw-bold mb-3">Difficulty Distribution</h6>
                            <canvas id="difficultyChart" height="200"></canvas>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card p-3 h-100">
                            <h6 class="fw-bold mb-3">Weekly Progress</h6>
                            <canvas id="weeklyChart" height="200"></canvas>
                        </div>
                    </div>
                </div>

                <!-- Heatmap -->
                <div class="card p-4 mb-4">
                    <h6 class="fw-bold mb-3">Preparation Activity Heatmap (Last 365 Days)</h6>
                    <div class="heatmap-grid" id="heatmapGrid"></div>
                </div>

            </div>
            <jsp:include page="../components/footer.jsp" />
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/js/analytics.js"></script>
    <script>
        // Inject server data into JS
        const analyticsData = {
            easy: ${analytics.easyCount},
            medium: ${analytics.mediumCount},
            hard: ${analytics.hardCount}
        };
        initCharts(analyticsData);
        generateHeatmap();
    </script>
</body>
</html>
