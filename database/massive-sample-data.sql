USE interview_tracker;

-- Prevent Duplicate Overloads
DELETE FROM user_badges WHERE user_id = 1;
DELETE FROM activity_logs WHERE user_id = 1;
DELETE FROM revisions WHERE user_id = 1;
DELETE FROM goals WHERE user_id = 1;
DELETE FROM questions WHERE user_id = 1;

-- Ensure User 1 exists
INSERT IGNORE INTO users (user_id, name, email, password, college, branch) VALUES (1, 'Jane Portfolio', 'jane@example.com', '$2a$10$wN2L5qXG5/Fp1JbXjI.YeeL7lQ9fQ7bO2T4X1K7YfG9M2F7x4U7aC', 'Tech University', 'CS');

-- (Rest of the data generation follows the same pattern as before, handled in python)

-- ----------------------------------------------------
-- DEPLOYMENT DEMO ACCOUNT
-- Email: demo@interviewtracker.com | Pass: Demo123
-- ----------------------------------------------------
DELETE FROM user_badges WHERE user_id = 999;
DELETE FROM activity_logs WHERE user_id = 999;
DELETE FROM revisions WHERE user_id = 999;
DELETE FROM goals WHERE user_id = 999;
DELETE FROM questions WHERE user_id = 999;

-- Password hash for 'Demo123'
INSERT IGNORE INTO users (user_id, name, email, password, college, branch) VALUES (999, 'Recruiter Demo', 'demo@interviewtracker.com', '$2a$10$wN2L5qXG5/Fp1JbXjI.YeeL7lQ9fQ7bO2T4X1K7YfG9M2F7x4U7aC', 'Demo University', 'CS');

-- Inject some baseline questions for the demo user
INSERT INTO questions (user_id, title, platform, topic, difficulty, status, date_solved) VALUES 
(999, 'Two Sum', 'LeetCode', 'Arrays', 'Easy', 'Solved', CURDATE() - INTERVAL 1 DAY),
(999, 'Reverse Linked List', 'LeetCode', 'Linked List', 'Easy', 'Solved', CURDATE() - INTERVAL 2 DAY),
(999, 'LRU Cache', 'LeetCode', 'Linked List', 'Hard', 'Solved', CURDATE() - INTERVAL 3 DAY),
(999, 'Course Schedule', 'LeetCode', 'Graphs', 'Medium', 'Solved', CURDATE() - INTERVAL 5 DAY);

-- Inject demo goals
INSERT INTO goals (user_id, goal_title, target_count, current_count, deadline, status) VALUES 
(999, 'Master DP', 50, 10, CURDATE() + INTERVAL 30 DAY, 'Active');

-- Inject demo activities
INSERT INTO activity_logs (user_id, activity_type, activity_date) VALUES 
(999, 'Solved Two Sum', NOW() - INTERVAL 24 HOUR),
(999, 'Created Goal Master DP', NOW() - INTERVAL 48 HOUR);
