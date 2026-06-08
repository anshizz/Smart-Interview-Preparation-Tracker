
function generateHeatmap() {
    const grid = document.getElementById('heatmapGrid');
    if(!grid) return;
    
    // Generate 365 cells for demo
    for(let i=0; i<365; i++) {
        const cell = document.createElement('div');
        cell.className = 'heatmap-cell';
        // Random activity level for demo
        const isActivity = Math.random() > 0.6;
        if(isActivity) {
            cell.setAttribute('data-level', Math.floor(Math.random() * 4) + 1);
        }
        grid.appendChild(cell);
    }
}
