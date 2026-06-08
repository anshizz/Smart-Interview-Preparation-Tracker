
let debounceTimer;
function debouncedSearch() {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(fetchQuestions, 300);
}

async function fetchQuestions() {
    const search = document.getElementById('searchInput')?.value || '';
    const diff = document.getElementById('difficultyFilter')?.value || '';
    const status = document.getElementById('statusFilter')?.value || '';
    
    // Normally we pass filters to backend, but let's fetch all and filter in JS for now or use the endpoint
    const url = `${contextPath}/QuestionServlet?action=list`;
    const questions = await apiCall(url);
    if(questions) {
        renderQuestions(questions.filter(q => 
            (search === '' || q.title.toLowerCase().includes(search.toLowerCase()) || q.topic.toLowerCase().includes(search.toLowerCase())) &&
            (diff === '' || q.difficulty === diff) &&
            (status === '' || q.status === status)
        ));
    }
}

function renderQuestions(questions) {
    const tbody = document.getElementById('questionsTableBody');
    if(!tbody) return;
    
    tbody.innerHTML = '';
    questions.forEach(q => {
        const tr = document.createElement('tr');
        
        let diffBadge = 'bg-success';
        if(q.difficulty === 'Medium') diffBadge = 'bg-warning text-dark';
        if(q.difficulty === 'Hard') diffBadge = 'bg-danger';

        let statusBadge = q.status === 'Solved' ? 'bg-primary' : 'bg-secondary';
        
        tr.innerHTML = `
            <td class="fw-medium">${q.title}</td>
            <td><span class="text-secondary small">${q.platform}</span></td>
            <td>${q.topic}</td>
            <td><span class="badge ${diffBadge}">${q.difficulty}</span></td>
            <td><span class="badge ${statusBadge}">${q.status}</span></td>
            <td>
                ${q.status !== 'Solved' ? `<button class="btn btn-sm btn-outline-success" onclick="markSolved(${q.questionId})"><i class="fas fa-check"></i></button>` : ''}
                <button class="btn btn-sm btn-outline-danger" onclick="deleteQuestion(${q.questionId})"><i class="fas fa-trash"></i></button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function addQuestion() {
    const title = document.getElementById('qTitle').value;
    const platform = document.getElementById('qPlatform').value;
    const topic = document.getElementById('qTopic').value;
    const diff = document.getElementById('qDifficulty').value;
    
    const params = new URLSearchParams({ action: 'add', title, platform, topic, difficulty: diff });
    const res = await apiCall(`${contextPath}/QuestionServlet`, { method: 'POST', body: params, headers: { 'Content-Type': 'application/x-www-form-urlencoded' } });
    
    if(res && res.success) {
        bootstrap.Modal.getInstance(document.getElementById('addQuestionModal')).hide();
        document.getElementById('addQuestionForm').reset();
        fetchQuestions();
    }
}

async function markSolved(id) {
    const params = new URLSearchParams({ action: 'markSolved', questionId: id });
    const res = await apiCall(`${contextPath}/QuestionServlet`, { method: 'POST', body: params, headers: { 'Content-Type': 'application/x-www-form-urlencoded' } });
    if(res && res.success) fetchQuestions();
}

async function deleteQuestion(id) {
    if(!confirm('Are you sure you want to delete this question?')) return;
    const params = new URLSearchParams({ action: 'delete', questionId: id });
    const res = await apiCall(`${contextPath}/QuestionServlet`, { method: 'POST', body: params, headers: { 'Content-Type': 'application/x-www-form-urlencoded' } });
    if(res && res.success) fetchQuestions();
}
