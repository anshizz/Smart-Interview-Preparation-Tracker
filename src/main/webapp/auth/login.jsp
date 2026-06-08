<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Interview Tracker</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../components/alert.jsp" />

    <div class="auth-container">
        <!-- Left Section: Branding -->
        <div class="auth-left d-none d-lg-flex">
            <h1>Smart Interview Preparation</h1>
            <p>Track your DSA progress, set goals, and prepare for placements with analytics.</p>
            <div class="mt-5 text-white-50">
                <i class="fas fa-chart-line fa-4x mb-3"></i><br>
                <span>Analytics Driven Preparation</span>
            </div>
        </div>

        <!-- Right Section: Login Form -->
        <div class="auth-right">
            <div class="card auth-card glass">
                <h3 class="auth-title">Welcome back</h3>
                <p class="auth-subtitle">Enter your credentials to access your dashboard.</p>
                
                <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                    <div class="mb-3">
                        <label for="email" class="form-label fw-medium">Email Address</label>
                        <input type="email" class="form-control" id="email" name="email" value="${cookie.rememberedEmail.value}" required placeholder="john@example.com">
                    </div>
                    
                    <div class="mb-3">
                        <label for="password" class="form-label fw-medium d-flex justify-content-between">
                            <span>Password</span>
                            <a href="${pageContext.request.contextPath}/auth/forgot-password.jsp" class="text-decoration-none small">Forgot password?</a>
                        </label>
                        <div class="password-wrapper">
                            <input type="password" class="form-control" id="password" name="password" required placeholder="••••••••">
                            <i class="fas fa-eye password-toggle" onclick="togglePassword('password', this)"></i>
                        </div>
                    </div>
                    
                    <div class="mb-4 form-check">
                        <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe" ${not empty cookie.rememberedEmail.value ? 'checked' : ''}>
                        <label class="form-check-label text-secondary" for="rememberMe">Remember me</label>
                    </div>
                    
                    <button type="submit" class="btn btn-primary w-100 py-2 mb-3">Sign In</button>
                    
                    <div class="text-center text-secondary small">
                        Don't have an account? <a href="${pageContext.request.contextPath}/auth/register.jsp" class="text-primary text-decoration-none fw-medium">Create account</a>
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
    </script>
</body>
</html>
