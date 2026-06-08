<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Interview Tracker</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
    <style>
        .strength-meter { height: 4px; border-radius: 2px; margin-top: 5px; transition: all 0.3s; width: 0%; }
        .strength-text { font-size: 0.8rem; margin-top: 2px; }
    </style>
</head>
<body>
    <jsp:include page="../components/alert.jsp" />

    <div class="auth-container">
        <!-- Left Section: Branding -->
        <div class="auth-left d-none d-lg-flex">
            <h1>Start Your Journey</h1>
            <p>Join thousands of students preparing for their dream jobs.</p>
        </div>

        <!-- Right Section: Register Form -->
        <div class="auth-right">
            <div class="card auth-card glass">
                <h3 class="auth-title">Create Account</h3>
                <p class="auth-subtitle">Get started with your free account.</p>
                
                <form action="${pageContext.request.contextPath}/RegisterServlet" method="POST" onsubmit="return validateForm()">
                    <div class="mb-3">
                        <label for="name" class="form-label fw-medium">Full Name</label>
                        <input type="text" class="form-control" id="name" name="name" required placeholder="John Doe">
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label fw-medium">Email Address</label>
                        <input type="email" class="form-control" id="email" name="email" required placeholder="john@example.com">
                    </div>
                    
                    <div class="mb-3">
                        <label for="password" class="form-label fw-medium">Password</label>
                        <div class="password-wrapper">
                            <input type="password" class="form-control" id="password" name="password" required placeholder="••••••••" onkeyup="checkStrength()">
                            <i class="fas fa-eye password-toggle" onclick="togglePassword('password', this)"></i>
                        </div>
                        <div class="strength-meter bg-secondary mt-2" id="strengthMeter"></div>
                        <div class="strength-text text-secondary" id="strengthText">Must be at least 8 characters, with 1 uppercase, 1 lowercase & 1 number.</div>
                    </div>

                    <div class="mb-4">
                        <label for="confirmPassword" class="form-label fw-medium">Confirm Password</label>
                        <div class="password-wrapper">
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required placeholder="••••••••">
                            <i class="fas fa-eye password-toggle" onclick="togglePassword('confirmPassword', this)"></i>
                        </div>
                        <div id="matchText" class="strength-text text-danger d-none">Passwords do not match</div>
                    </div>
                    
                    <button type="submit" class="btn btn-primary w-100 py-2 mb-3" id="submitBtn">Sign Up</button>
                    
                    <div class="text-center text-secondary small">
                        Already have an account? <a href="${pageContext.request.contextPath}/auth/login.jsp" class="text-primary text-decoration-none fw-medium">Sign in</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap Bundle JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function togglePassword(inputId, iconElement) {
            const input = document.getElementById(inputId);
            if (input.type === "password") {
                input.type = "text";
                iconElement.classList.remove('fa-eye');
                iconElement.classList.add('fa-eye-slash');
            } else {
                input.type = "password";
                iconElement.classList.remove('fa-eye-slash');
                iconElement.classList.add('fa-eye');
            }
        }

        const pwdRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d\w\W]{8,}$/;

        function checkStrength() {
            const pwd = document.getElementById('password').value;
            const meter = document.getElementById('strengthMeter');
            const text = document.getElementById('strengthText');
            
            if(pwd.length === 0) {
                meter.style.width = '0%';
                meter.className = 'strength-meter bg-secondary mt-2';
                text.innerText = 'Must be at least 8 characters, with 1 uppercase, 1 lowercase & 1 number.';
                return false;
            }

            if(pwdRegex.test(pwd)) {
                meter.style.width = '100%';
                meter.className = 'strength-meter bg-success mt-2';
                text.innerText = 'Strong Password';
                text.className = 'strength-text text-success';
                return true;
            } else {
                meter.style.width = '50%';
                meter.className = 'strength-meter bg-warning mt-2';
                text.innerText = 'Weak: Include uppercase, lowercase, and a number (min 8 chars).';
                text.className = 'strength-text text-warning';
                return false;
            }
        }

        function validateForm() {
            const pwd = document.getElementById('password').value;
            const confirm = document.getElementById('confirmPassword').value;
            const matchText = document.getElementById('matchText');

            if(!checkStrength()) {
                showToast('Please enter a strong password.', 'warning');
                return false;
            }

            if(pwd !== confirm) {
                matchText.classList.remove('d-none');
                return false;
            }
            matchText.classList.add('d-none');
            return true;
        }
    </script>
</body>
</html>
