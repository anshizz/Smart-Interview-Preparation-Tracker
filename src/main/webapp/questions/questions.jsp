<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Question Tracker</title>
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
                    <h2 class="fw-bold m-0">Question Tracker</h2>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addQuestionModal"><i class="fas fa-plus"></i> Add Question</button>
                </div>
                
                <div class="card p-4 mb-4">
                    <div class="row g-3 mb-4">
                        <div class="col-md-4">
                            <input type="text" id="searchInput" class="form-control" placeholder="Search by title, topic..." onkeyup="debouncedSearch()">
                        </div>
                        <div class="col-md-2">
                            <select id="difficultyFilter" class="form-select" onchange="fetchQuestions()">
                                <option value="">All Difficulties</option>
                                <option value="Easy">Easy</option><option value="Medium">Medium</option><option value="Hard">Hard</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <select id="statusFilter" class="form-select" onchange="fetchQuestions()">
                                <option value="">All Status</option>
                                <option value="Solved">Solved</option><option value="To Do">To Do</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="table-responsive">
                        <table class="table table-hover align-middle">
                            <thead class="table-light">
                                <tr><th>Title</th><th>Platform</th><th>Topic</th><th>Difficulty</th><th>Status</th><th>Actions</th></tr>
                            </thead>
                            <tbody id="questionsTableBody">
                                <!-- Data populated via AJAX -->
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <jsp:include page="../components/footer.jsp" />
        </div>
    </div>

    <!-- Add Question Modal -->
    <div class="modal fade" id="addQuestionModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header"><h5 class="modal-title">Add New Question</h5><button type="button" class="btn-close" data-bs-dismiss="modal"></button></div>
                <div class="modal-body">
                    <form id="addQuestionForm">
                        <div class="mb-3"><label>Title</label><input type="text" id="qTitle" class="form-control" required></div>
                        <div class="mb-3"><label>Platform</label><input type="text" id="qPlatform" class="form-control" required></div>
                        <div class="mb-3"><label>Topic</label><input type="text" id="qTopic" class="form-control" required></div>
                        <div class="mb-3"><label>Difficulty</label>
                            <select id="qDifficulty" class="form-select"><option>Easy</option><option>Medium</option><option>Hard</option></select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addQuestion()">Save Question</button>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajax.js"></script>
    <script src="${pageContext.request.contextPath}/js/question.js"></script>
    <script>
        const contextPath = '${pageContext.request.contextPath}';
        document.addEventListener('DOMContentLoaded', fetchQuestions);
    </script>
</body>
</html>
