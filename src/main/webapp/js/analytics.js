
function initCharts(data) {
    const ctxDiff = document.getElementById('difficultyChart');
    if(ctxDiff) {
        new Chart(ctxDiff, {
            type: 'doughnut',
            data: {
                labels: ['Easy', 'Medium', 'Hard'],
                datasets: [{
                    data: [data.easy, data.medium, data.hard],
                    backgroundColor: ['#22C55E', '#F59E0B', '#EF4444'],
                    borderWidth: 0
                }]
            },
            options: { cutout: '75%', responsive: true, maintainAspectRatio: false }
        });
    }

    const ctxWeek = document.getElementById('weeklyChart');
    if(ctxWeek) {
        new Chart(ctxWeek, {
            type: 'line',
            data: {
                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                datasets: [{
                    label: 'Questions Solved',
                    data: [2, 5, 3, 7, 4, 10, 8],
                    borderColor: '#2563EB',
                    tension: 0.4,
                    fill: true,
                    backgroundColor: 'rgba(37, 99, 235, 0.1)'
                }]
            },
            options: { responsive: true, maintainAspectRatio: false }
        });
    }
}
