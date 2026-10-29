import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { useLanguage } from '../../context/LanguageContext';
import { authApi } from '../../services/api';
import { GraduationCap, LogIn, Lock, Mail, ArrowRight } from 'lucide-react';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const { loginUser } = useAuth();
  const { t } = useLanguage();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const res = await authApi.login({ email, password });
      loginUser(res.data);
      if (res.data.role === 'ROLE_TEACHER') {
        navigate('/teacher/dashboard');
      } else if (res.data.role === 'ROLE_ADMIN') {
        navigate('/admin/dashboard');
      } else {
        navigate('/student/dashboard');
      }
    } catch (err) {
      setError(err.response?.data?.message || 'Invalid email or password');
    } finally {
      setLoading(false);
    }
  };

  const handleQuickLogin = (demoEmail, demoPass, target) => {
    setEmail(demoEmail);
    setPassword(demoPass);
    authApi.login({ email: demoEmail, password: demoPass }).then((res) => {
      loginUser(res.data);
      navigate(target);
    }).catch((err) => {
      setError('Demo login failed. Make sure backend is running.');
    });
  };

  return (
    <div className="min-h-[calc(100vh-4rem)] bg-slate-100 flex items-center justify-center p-4">
      <div className="bg-white rounded-2xl border border-slate-200 shadow-lg max-w-md w-full p-6 sm:p-8">
        
        <div className="text-center mb-6">
          <div className="w-12 h-12 rounded-xl bg-emerald-800 text-amber-300 flex items-center justify-center mx-auto mb-3 shadow">
            <GraduationCap className="w-7 h-7" />
          </div>
          <h2 className="text-2xl font-extrabold text-slate-900">{t('login')}</h2>
          <p className="text-xs text-slate-500 mt-1">{t('tagline')}</p>
        </div>

        {error && (
          <div className="mb-4 p-3 bg-rose-50 border border-rose-200 text-rose-700 rounded-lg text-xs font-medium">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Email Address</label>
            <div className="relative">
              <Mail className="w-4 h-4 text-slate-400 absolute left-3 top-3" />
              <input
                type="email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="student@telangana.edu"
                className="w-full pl-9 pr-3 py-2 border border-slate-300 rounded-lg text-sm focus:ring-2 focus:ring-emerald-600 focus:border-transparent outline-none"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Password</label>
            <div className="relative">
              <Lock className="w-4 h-4 text-slate-400 absolute left-3 top-3" />
              <input
                type="password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="••••••••"
                className="w-full pl-9 pr-3 py-2 border border-slate-300 rounded-lg text-sm focus:ring-2 focus:ring-emerald-600 focus:border-transparent outline-none"
              />
            </div>
          </div>

          <button
            type="submit"
            disabled={loading}
            className="w-full py-2.5 bg-emerald-800 hover:bg-emerald-700 text-white rounded-lg text-sm font-semibold transition shadow flex items-center justify-center"
          >
            {loading ? 'Authenticating...' : t('login')}
            <LogIn className="w-4 h-4 ml-2" />
          </button>
        </form>

        {/* Quick Demo Logins Bar */}
        <div className="mt-6 pt-6 border-t border-slate-200 text-center">
          <p className="text-xs font-semibold text-slate-500 uppercase tracking-wider mb-2">Project Presentation Quick Logins</p>
          <div className="grid grid-cols-3 gap-2">
            <button
              onClick={() => handleQuickLogin('student@telangana.edu', 'student123', '/student/dashboard')}
              className="py-1.5 px-2 bg-amber-50 hover:bg-amber-100 text-amber-900 border border-amber-300 rounded text-xs font-bold transition"
            >
              Student
            </button>
            <button
              onClick={() => handleQuickLogin('teacher@telangana.edu', 'teacher123', '/teacher/dashboard')}
              className="py-1.5 px-2 bg-emerald-50 hover:bg-emerald-100 text-emerald-900 border border-emerald-300 rounded text-xs font-bold transition"
            >
              Teacher
            </button>
            <button
              onClick={() => handleQuickLogin('admin@telangana.edu', 'admin123', '/admin/dashboard')}
              className="py-1.5 px-2 bg-purple-50 hover:bg-purple-100 text-purple-900 border border-purple-300 rounded text-xs font-bold transition"
            >
              Admin
            </button>
          </div>
        </div>

        <p className="text-center text-xs text-slate-500 mt-4">
          Don't have an account?{' '}
          <Link to="/register" className="text-emerald-700 font-bold hover:underline">
            {t('register')}
          </Link>
        </p>
      </div>
    </div>
  );
};

export default LoginPage;
