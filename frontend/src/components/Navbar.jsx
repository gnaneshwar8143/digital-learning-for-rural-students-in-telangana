import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useLanguage } from '../context/LanguageContext';
import { GraduationCap, Languages, LogOut, Wifi, UserCheck } from 'lucide-react';

const Navbar = () => {
  const { user, logoutUser } = useAuth();
  const { lang, switchLanguage, t } = useLanguage();
  const navigate = useNavigate();

  const handleLogout = () => {
    logoutUser();
    navigate('/login');
  };

  return (
    <header className="bg-emerald-900 text-white shadow-md sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
        
        {/* Brand / Logo */}
        <Link to="/" className="flex items-center space-x-3 group">
          <div className="w-10 h-10 rounded-lg bg-amber-400 text-emerald-950 flex items-center justify-center font-bold text-xl shadow">
            <GraduationCap className="w-6 h-6" />
          </div>
          <div>
            <span className="font-bold text-lg leading-tight block tracking-wide text-emerald-100 group-hover:text-white transition">
              {t('appName')}
            </span>
            <span className="text-xs text-emerald-300 hidden sm:block">
              Telangana State Board School Education (Classes 6-10)
            </span>
          </div>
        </Link>

        {/* Right Section: Language Switcher, Bandwidth Badge & Auth */}
        <div className="flex items-center space-x-3">
          
          {/* Low Bandwidth Rural Indicator Badge */}
          <div className="hidden md:flex items-center space-x-1 bg-emerald-950/80 px-2.5 py-1 rounded-full text-xs text-amber-300 border border-emerald-700">
            <Wifi className="w-3.5 h-3.5 text-amber-400 animate-pulse" />
            <span>{t('lowBandwidthMode')}</span>
          </div>

          {/* Bilingual Language Switcher Button (English | తెలుగు) */}
          <div className="flex items-center bg-emerald-800 rounded-lg p-1 border border-emerald-700">
            <Languages className="w-4 h-4 text-amber-300 ml-1 mr-1" />
            <button
              onClick={() => switchLanguage('en')}
              className={`px-2 py-0.5 text-xs font-semibold rounded ${
                lang === 'en' ? 'bg-amber-400 text-emerald-950 shadow' : 'text-emerald-200 hover:text-white'
              }`}
            >
              English
            </button>
            <button
              onClick={() => switchLanguage('te')}
              className={`px-2 py-0.5 text-xs font-semibold rounded ${
                lang === 'te' ? 'bg-amber-400 text-emerald-950 shadow' : 'text-emerald-200 hover:text-white'
              }`}
            >
              తెలుగు
            </button>
          </div>

          {/* User Profile / Auth State */}
          {user ? (
            <div className="flex items-center space-x-3 pl-2 border-l border-emerald-700">
              <div className="text-right hidden sm:block">
                <p className="text-sm font-semibold text-white">{user.name}</p>
                <p className="text-xs text-amber-300 font-mono">
                  {user.role === 'ROLE_STUDENT' ? `Student (Class ${user.classLevel})` : user.role.replace('ROLE_', '')}
                </p>
              </div>
              <button
                onClick={handleLogout}
                className="p-2 text-emerald-200 hover:text-amber-300 hover:bg-emerald-800 rounded-lg transition"
                title={t('logout')}
              >
                <LogOut className="w-5 h-5" />
              </button>
            </div>
          ) : (
            <div className="flex items-center space-x-2">
              <Link
                to="/login"
                className="px-3.5 py-1.5 text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-700 rounded-lg transition border border-emerald-600"
              >
                {t('login')}
              </Link>
              <Link
                to="/register"
                className="px-3.5 py-1.5 text-sm font-medium text-emerald-950 bg-amber-400 hover:bg-amber-300 rounded-lg transition font-semibold shadow"
              >
                {t('register')}
              </Link>
            </div>
          )}
        </div>
      </div>
    </header>
  );
};

export default Navbar;
