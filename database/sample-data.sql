USE interview_tracker;

-- Insert sample user (Password is 'password123' hashed with BCrypt)
-- The hash below corresponds to 'password123'
INSERT INTO users (name, email, password, college, branch) 
VALUES ('John Doe', 'john@example.com', '$2a$10$wN2L5qXG5/Fp1JbXjI.YeeL7lQ9fQ7bO2T4X1K7YfG9M2F7x4U7aC', 'Engineering College', 'Computer Science');

-- Insert sample questions
INSERT INTO questions (user_id, title, platform, topic, difficulty, status, date_solved) VALUES 
(1, 'Two Sum', 'LeetCode', 'Arrays', 'Easy', 'Solved', CURDATE() - INTERVAL 5 DAY),
(1, 'Reverse Linked List', 'LeetCode', 'Linked List', 'Easy', 'Solved', CURDATE() - INTERVAL 4 DAY),
(1, 'Number of Islands', 'LeetCode', 'Graphs', 'Medium', 'Solved', CURDATE() - INTERVAL 2 DAY),
(1, 'Longest Common Subsequence', 'LeetCode', 'DP', 'Medium', 'To Do', NULL),
(1, 'Merge K Sorted Lists', 'LeetCode', 'Heap', 'Hard', 'To Do', NULL),
(1, 'Valid Parentheses', 'LeetCode', 'Strings', 'Easy', 'Solved', CURDATE() - INTERVAL 1 DAY);

-- Insert sample goals
INSERT INTO goals (user_id, goal_title, target_count, current_count, deadline, status) VALUES 
(1, 'Complete 50 DSA Questions', 50, 4, CURDATE() + INTERVAL 30 DAY, 'Active'),
(1, 'Finish Graph Theory', 10, 1, CURDATE() + INTERVAL 10 DAY, 'Active'),
(1, 'Revise Sorting Algorithms', 5, 5, CURDATE() - INTERVAL 1 DAY, 'Completed');

-- Insert sample revisions
INSERT INTO revisions (user_id, topic, revision_date, completed) VALUES 
(1, 'Dynamic Programming', CURDATE() + INTERVAL 2 DAY, FALSE),
(1, 'Graphs and Trees', CURDATE() - INTERVAL 1 DAY, TRUE),
(1, 'System Design Basics', CURDATE() + INTERVAL 5 DAY, FALSE);

-- Insert sample activity logs
INSERT INTO activity_logs (user_id, activity_type, activity_date) VALUES 
(1, 'Registered on the platform', NOW() - INTERVAL 10 DAY),
(1, 'Solved Two Sum', NOW() - INTERVAL 5 DAY),
(1, 'Created Goal "Complete 50 DSA Questions"', NOW() - INTERVAL 4 DAY),
(1, 'Solved Reverse Linked List', NOW() - INTERVAL 4 DAY),
(1, 'Completed Revision on "Graphs and Trees"', NOW() - INTERVAL 1 DAY);

-- Insert sample notes
INSERT INTO notes (user_id, title, content) VALUES 
(1, 'Graph Traversals', 'BFS uses Queue (shortest path on unweighted graph). DFS uses Stack/Recursion.'),
(1, 'DP Patterns', '1. Knapsack 2. LIS 3. Grid Paths. Always start with recursive solution, then memoize, then tabulation.');

-- Insert sample badges
INSERT INTO user_badges (user_id, badge_name) VALUES 
(1, 'First Question Solved'),
(1, 'Early Adopter');
