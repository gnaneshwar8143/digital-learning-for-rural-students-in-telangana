import React from 'react';
import { NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useLanguage } from '../context/LanguageContext';
import { 
  LayoutDashboard, 
  BookOpen, 
  Target, 
  BarChart3, 
  Bot, 
  Calendar, 
  PlusCircle, 
  Upload, 
  Users, 
  LogOut,
  Sparkles
} from 'lucide-react';

const Sidebar = () => {
  const { user, logout } = useAuth();
  const { t } = useLanguage();

  if (!user) return null;

  return (
    <aside className="w-64 bg-slate-900 text-slate-300 min-h-[calc(100vh-4rem)] p-4 flex flex-col justify-between shadow-xl">
      <div className="space-y-6">
        
        {/* User Badge */}
        <div className="bg-slate-800/80 p-3 rounded-xl border border-slate-700/60 flex items-center space-x-3">
          <div className="w-10 h-10 rounded-full bg-emerald-700 text-white font-bold flex items-center justify-center text-sm shadow">
            {user.name ? user.name.charAt(0).toUpperCase() : 'U'}
          </div>
          <div className="overflow-hidden">
            <h4 className="text-sm font-bold text-white truncate">{user.name}</h4>
            <span className="text-[10px] uppercase font-bold tracking-wider px-2 py-0.5 rounded bg-emerald-950 text-emerald-400 border border-emerald-800/60 inline-block">
              {user.role} {user.classLevel ? `(Class ${user.classLevel})` : ''}
            </span>
          </div>
        </div>

        {/* Navigation Section */}
        <div className="space-y-1">
          <p className="text-[11px] font-bold text-slate-400 uppercase tracking-wider px-3 mb-2">Main Menu</p>

          {/* Student Links */}
          {user.role === 'STUDENT' && (
            <>
              <NavLink
                to="/student/dashboard"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <LayoutDashboard className="w-4 h-4" />
                <span>{t('dashboard')}</span>
              </NavLink>

              <NavLink
                to="/student/subjects"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <BookOpen className="w-4 h-4" />
                <span>{t('subjects')}</span>
              </NavLink>

              {/* AI Features */}
              <div className="pt-2 pb-1">
                <p className="text-[10px] font-bold text-amber-400 uppercase tracking-wider px-3 flex items-center">
                  <Sparkles className="w-3 h-3 mr-1 text-amber-400" /> AI Learning Suite
                </p>
              </div>

              <NavLink
                to="/student/ai-tutor"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-bold text-sm transition ${
                    isActive ? 'bg-emerald-800 text-amber-300 shadow-md border border-amber-400/40' : 'hover:bg-slate-800 text-amber-300/90'
                  }`
                }
              >
                <Bot className="w-4 h-4 text-amber-400" />
                <span>{t('aiTutor')}</span>
              </NavLink>

              <NavLink
                to="/student/study-plan"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-bold text-sm transition ${
                    isActive ? 'bg-emerald-800 text-amber-300 shadow-md border border-amber-400/40' : 'hover:bg-slate-800 text-amber-300/90'
                  }`
                }
              >
                <Calendar className="w-4 h-4 text-amber-400" />
                <span>{t('aiStudyPlan')}</span>
              </NavLink>

              <NavLink
                to="/student/recommendations"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <Target className="w-4 h-4" />
                <span>{t('recommendations')}</span>
              </NavLink>

              <NavLink
                to="/student/evaluation"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <BarChart3 className="w-4 h-4" />
                <span>{t('evaluation')}</span>
              </NavLink>
            </>
          )}

          {/* Teacher Links */}
          {user.role === 'TEACHER' && (
            <>
              <NavLink
                to="/teacher/dashboard"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <LayoutDashboard className="w-4 h-4" />
                <span>{t('teacherWelcome')}</span>
              </NavLink>

              <NavLink
                to="/teacher/create-quiz"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <PlusCircle className="w-4 h-4 text-amber-400" />
                <span>{t('createQuiz')}</span>
              </NavLink>

              <NavLink
                to="/student/evaluation"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <BarChart3 className="w-4 h-4" />
                <span>{t('evaluation')}</span>
              </NavLink>
            </>
          )}

          {/* Admin Links */}
          {user.role === 'ADMIN' && (
            <>
              <NavLink
                to="/admin/dashboard"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <LayoutDashboard className="w-4 h-4" />
                <span>{t('adminPanel')}</span>
              </NavLink>

              <NavLink
                to="/student/evaluation"
                className={({ isActive }) =>
                  `flex items-center space-x-3 px-3.5 py-2.5 rounded-xl font-medium text-sm transition ${
                    isActive ? 'bg-emerald-800 text-white shadow-md' : 'hover:bg-slate-800 text-slate-300'
                  }`
                }
              >
                <BarChart3 className="w-4 h-4" />
                <span>{t('evaluation')}</span>
              </NavLink>
            </>
          )}

        </div>
      </div>

      {/* Logout */}
      <button
        onClick={logout}
        className="w-full mt-6 flex items-center justify-center space-x-2 px-3.5 py-2.5 rounded-xl bg-slate-800 hover:bg-rose-950 text-slate-300 hover:text-rose-300 border border-slate-700 transition font-medium text-sm"
      >
        <LogOut className="w-4 h-4" />
        <span>{t('logout')}</span>
      </button>
    </aside>
  );
};

export default Sidebar;
