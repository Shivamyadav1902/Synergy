// School LMS - JavaScript Utilities

// API Base URL
const API_BASE = window.location.origin + '/SchoolLMS';

// Show alert message
function showAlert(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;
    
    const container = document.querySelector('.container') || document.body;
    container.insertBefore(alertDiv, container.firstChild);
    
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

// Show loading spinner
function showLoading(show = true) {
    let spinner = document.querySelector('.spinner');
    
    if (show) {
        if (!spinner) {
            spinner = document.createElement('div');
            spinner.className = 'spinner';
            document.body.appendChild(spinner);
        }
    } else {
        if (spinner) {
            spinner.remove();
        }
    }
}

// Make API request
async function apiRequest(url, options = {}) {
    try {
        showLoading(true);
        const response = await fetch(API_BASE + url, {
            ...options,
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            }
        });
        
        showLoading(false);
        
        if (!response.ok && response.status === 401) {
            window.location.href = API_BASE + '/login.html';
            return null;
        }
        
        return await response.json();
    } catch (error) {
        showLoading(false);
        console.error('API Request Error:', error);
        showAlert('An error occurred. Please try again.', 'error');
        return null;
    }
}

// Format date
function formatDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    });
}

// Format datetime
function formatDateTime(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
}

// Get URL parameter
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    const regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    const results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

// Logout function
function logout() {
    if (confirm('Are you sure you want to logout?')) {
        window.location.href = API_BASE + '/logout';
    }
}

// Form validation
function validateForm(formId) {
    const form = document.getElementById(formId);
    if (!form) return false;
    
    const inputs = form.querySelectorAll('input[required], select[required], textarea[required]');
    let isValid = true;
    
    inputs.forEach(input => {
        if (!input.value.trim()) {
            input.style.borderColor = '#f56565';
            isValid = false;
        } else {
            input.style.borderColor = '#475569';
        }
    });
    
    return isValid;
}

// Email validation
function isValidEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

// Get session info
function getSessionInfo() {
    return {
        userId: sessionStorage.getItem('userId'),
        username: sessionStorage.getItem('username'),
        fullName: sessionStorage.getItem('fullName'),
        role: sessionStorage.getItem('role')
    };
}

// Set session info
function setSessionInfo(userInfo) {
    sessionStorage.setItem('userId', userInfo.userId);
    sessionStorage.setItem('username', userInfo.username);
    sessionStorage.setItem('fullName', userInfo.fullName);
    sessionStorage.setItem('role', userInfo.role);
}

// Clear session
function clearSession() {
    sessionStorage.clear();
}

// Debounce function for search
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Initialize tooltips (if needed)
function initTooltips() {
    const tooltips = document.querySelectorAll('[data-tooltip]');
    tooltips.forEach(elem => {
        elem.addEventListener('mouseenter', (e) => {
            const tooltip = document.createElement('div');
            tooltip.className = 'tooltip';
            tooltip.textContent = e.target.getAttribute('data-tooltip');
            document.body.appendChild(tooltip);
            
            const rect = e.target.getBoundingClientRect();
            tooltip.style.top = rect.top - tooltip.offsetHeight - 5 + 'px';
            tooltip.style.left = rect.left + (rect.width / 2) - (tooltip.offsetWidth / 2) + 'px';
        });
        
        elem.addEventListener('mouseleave', () => {
            const tooltip = document.querySelector('.tooltip');
            if (tooltip) tooltip.remove();
        });
    });
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', () => {
    console.log('School LMS Initialized');
    initTooltips();
});
