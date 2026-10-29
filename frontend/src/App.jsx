import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import { LanguageProvider } from './context/LanguageContext';

import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';

import LandingPage from './pages/public/LandingPage';
import LoginPage from './pages/public/LoginPage';
import RegisterPage from './pages/public/RegisterPage';

import StudentDashboard from './pages/student/StudentDashboard';
import SubjectExplorer from './pages/student/SubjectExplorer';
import TopicDetail from './pages/student/TopicDetail';
import QuizExecution from './pages/student/QuizExecution';
import QuizResultPage from './pages/student/QuizResultPage';
import RecommendationsPage from './pages/student/RecommendationsPage';
import EvaluationPage from './pages/student/EvaluationPage';
import AITutorPage from './pages/student/AITutorPage';
import StudyPlanPage from './pages/student/StudyPlanPage';

import TeacherDashboard from './pages/teacher/TeacherDashboard';
import StudentPerformancePage from './pages/teacher/StudentPerformancePage';
import CreateQuizPage from './pages/teacher/CreateQuizPage';

import AdminDashboard from './pages/admin/AdminDashboard';

const ProtectedRoute = ({ children, allowedRoles }) => {
  const { user } = useAuth();
  if (!user) return <Navigate to="/login" replace />;
  if (allowedRoles && !allowedRoles.includes(user.role)) {
    return <Navigate to="/" replace />;
  }
  return children;
};

const AppLayout = ({ children }) => {
  const { user } = useAuth();
  return (
    <div className="min-h-screen bg-slate-100 flex flex-col font-sans">
      <Navbar />
      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        {user && <Sidebar />}
        <main className="flex-1 p-4 sm:p-6 lg:p-8 overflow-y-auto">
          {children}
        </main>
      </div>
    </div>
  );
};

function App() {
  return (
    <AuthProvider>
      <LanguageProvider>
        <Router>
          <Routes>
            {/* Public Routes */}
            <Route path="/" element={<LandingPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />

            {/* Student Protected Routes */}
            <Route
              path="/student/dashboard"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><StudentDashboard /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/subjects"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><SubjectExplorer /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/subjects/:subjectId/chapters"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><TopicDetail /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/ai-tutor"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><AITutorPage /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/study-plan"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><StudyPlanPage /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/quizzes/:quizId"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><QuizExecution /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/quiz-result"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><QuizResultPage /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/recommendations"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><RecommendationsPage /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/student/evaluation"
              element={
                <ProtectedRoute allowedRoles={['ROLE_STUDENT', 'ROLE_ADMIN']}>
                  <AppLayout><EvaluationPage /></AppLayout>
                </ProtectedRoute>
              }
            />

            {/* Teacher Protected Routes */}
            <Route
              path="/teacher/dashboard"
              element={
                <ProtectedRoute allowedRoles={['ROLE_TEACHER', 'ROLE_ADMIN']}>
                  <AppLayout><TeacherDashboard /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/teacher/students"
              element={
                <ProtectedRoute allowedRoles={['ROLE_TEACHER', 'ROLE_ADMIN']}>
                  <AppLayout><StudentPerformancePage /></AppLayout>
                </ProtectedRoute>
              }
            />
            <Route
              path="/teacher/create-quiz"
              element={
                <ProtectedRoute allowedRoles={['ROLE_TEACHER', 'ROLE_ADMIN']}>
                  <AppLayout><CreateQuizPage /></AppLayout>
                </ProtectedRoute>
              }
            />

            {/* Admin Protected Routes */}
            <Route
              path="/admin/dashboard"
              element={
                <ProtectedRoute allowedRoles={['ROLE_ADMIN']}>
                  <AppLayout><AdminDashboard /></AppLayout>
                </ProtectedRoute>
              }
            />

            {/* Fallback */}
            <Route path="*" element={<Navigate to="/" replace />} />
          </Routes>
        </Router>
      </LanguageProvider>
    </AuthProvider>
  );
}

export default App;
