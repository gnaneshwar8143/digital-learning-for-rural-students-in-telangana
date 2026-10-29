import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useLanguage } from '../../context/LanguageContext';
import { useAuth } from '../../context/AuthContext';
import { authApi } from '../../services/api';
import { 
  GraduationCap, 
  BookOpen, 
  BrainCircuit, 
  TrendingUp, 
  WifiOff, 
  Languages, 
  CheckCircle2, 
  ArrowRight,
  ShieldAlert,
  Sparkles
} from 'lucide-react';

const LandingPage = () => {
  const { t, lang } = useLanguage();
  const { loginUser } = useAuth();
  const navigate = useNavigate();

  const handleQuickDemoLogin = async (email, password, redirectPath) => {
    try {
      const res = await authApi.login({ email, password });
      loginUser(res.data);
      navigate(redirectPath);
    } catch (err) {
      alert('Backend authentication error. Please make sure backend is running on port 8080.');
    }
  };

  return (
    <div className="min-h-screen bg-slate-50 flex flex-col font-sans">
      
      {/* Hero Section */}
      <section className="bg-gradient-to-br from-emerald-950 via-emerald-900 to-slate-900 text-white py-16 px-4 sm:px-6 lg:px-8 border-b border-emerald-800">
        <div className="max-w-6xl mx-auto text-center space-y-6">
          
          <div className="inline-flex items-center space-x-2 px-3 py-1 rounded-full bg-emerald-800/80 text-amber-300 text-xs font-semibold border border-emerald-700">
            <Sparkles className="w-4 h-4 text-amber-400" />
            <span>CSE Final-Year Major Research Project</span>
          </div>

          <h1 className="text-3xl sm:text-5xl font-extrabold tracking-tight text-white leading-tight">
            {t('heroTitle')}
          </h1>

          <p className="max-w-3xl mx-auto text-base sm:text-lg text-emerald-100/90 font-normal">
            {t('heroSubtitle')}
          </p>

          <div className="p-4 bg-emerald-900/60 rounded-xl border border-emerald-700/80 max-w-2xl mx-auto text-xs text-amber-200 shadow-inner">
            <p className="font-semibold">{t('basePaperNotice')}</p>
          </div>

          {/* Quick Demo Login Cards */}
          <div className="pt-4 max-w-4xl mx-auto">
            <p className="text-sm font-semibold text-emerald-200 uppercase tracking-wider mb-3">
              {t('demoLoginHint')}
            </p>

            <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 text-left">
              
              <button
                onClick={() => handleQuickDemoLogin('student@telangana.edu', 'student123', '/student/dashboard')}
                className="p-4 rounded-xl bg-white/10 hover:bg-white/20 border border-white/20 transition flex flex-col justify-between group"
              >
                <div>
                  <span className="inline-block px-2 py-0.5 rounded text-[10px] font-bold bg-amber-400 text-emerald-950 uppercase mb-1">
                    Student Demo
                  </span>
                  <h3 className="font-bold text-white text-base group-hover:text-amber-300 transition">Rahul Kumar</h3>
                  <p className="text-xs text-emerald-200 font-mono">Class 10 - Telangana Board</p>
                </div>
                <div className="mt-3 flex items-center text-xs text-amber-300 font-semibold">
                  <span>Enter Student Dashboard</span>
                  <ArrowRight className="w-3.5 h-3.5 ml-1" />
                </div>
              </button>

              <button
                onClick={() => handleQuickDemoLogin('teacher@telangana.edu', 'teacher123', '/teacher/dashboard')}
                className="p-4 rounded-xl bg-white/10 hover:bg-white/20 border border-white/20 transition flex flex-col justify-between group"
              >
                <div>
                  <span className="inline-block px-2 py-0.5 rounded text-[10px] font-bold bg-emerald-400 text-emerald-950 uppercase mb-1">
                    Teacher Demo
                  </span>
                  <h3 className="font-bold text-white text-base group-hover:text-amber-300 transition">Srinivas Rao</h3>
                  <p className="text-xs text-emerald-200 font-mono">High School Educator</p>
                </div>
                <div className="mt-3 flex items-center text-xs text-amber-300 font-semibold">
                  <span>Enter Teacher Dashboard</span>
                  <ArrowRight className="w-3.5 h-3.5 ml-1" />
                </div>
              </button>

              <button
                onClick={() => handleQuickDemoLogin('admin@telangana.edu', 'admin123', '/admin/dashboard')}
                className="p-4 rounded-xl bg-white/10 hover:bg-white/20 border border-white/20 transition flex flex-col justify-between group"
              >
                <div>
                  <span className="inline-block px-2 py-0.5 rounded text-[10px] font-bold bg-purple-400 text-slate-950 uppercase mb-1">
                    Admin Demo
                  </span>
                  <h3 className="font-bold text-white text-base group-hover:text-amber-300 transition">Telangana Admin</h3>
                  <p className="text-xs text-emerald-200 font-mono">System Administrator</p>
                </div>
                <div className="mt-3 flex items-center text-xs text-amber-300 font-semibold">
                  <span>Enter Admin Panel</span>
                  <ArrowRight className="w-3.5 h-3.5 ml-1" />
                </div>
              </button>
            </div>
          </div>
        </div>
      </section>

      {/* Core Research Workflow Visualization */}
      <section className="py-12 px-4 max-w-6xl mx-auto w-full">
        <div className="text-center mb-10">
          <h2 className="text-2xl sm:text-3xl font-extrabold text-slate-900">
            Core Research Contribution & Workflow
          </h2>
          <p className="text-sm text-slate-600 max-w-2xl mx-auto mt-2">
            Automated feedback loop identifying weak topics from quiz performance and recommending targeted remediation.
          </p>
        </div>

        <div className="grid grid-cols-2 md:grid-cols-5 gap-3 text-center">
          {[
            { step: '1', title: 'Curriculum Study', desc: 'Class 6-10 Telangana State Board' },
            { step: '2', title: 'Quiz Execution', desc: 'Bilingual questions & automatic grading' },
            { step: '3', title: 'Weak Topic Engine', desc: '<40% flags Weak Topic status' },
            { step: '4', title: 'Personalized Recs', desc: 'Targeted notes, videos, practice sets' },
            { step: '5', title: 'Pre/Post Retest', desc: 'Evaluates percentage improvement' },
          ].map((item, idx) => (
            <div key={idx} className="bg-white p-4 rounded-xl border border-slate-200 shadow-sm relative flex flex-col items-center">
              <div className="w-8 h-8 rounded-full bg-emerald-800 text-amber-300 font-bold text-sm flex items-center justify-center mb-2 shadow">
                {item.step}
              </div>
              <h4 className="font-bold text-slate-900 text-sm">{item.title}</h4>
              <p className="text-xs text-slate-500 mt-1">{item.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Feature Highlights Grid */}
      <section className="py-12 bg-emerald-900/5 border-y border-emerald-950/10 px-4">
        <div className="max-w-6xl mx-auto">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            
            <div className="bg-white p-6 rounded-xl border border-slate-200 shadow-sm">
              <div className="w-12 h-12 rounded-lg bg-emerald-100 text-emerald-800 flex items-center justify-center mb-4">
                <BrainCircuit className="w-6 h-6" />
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Weak Topic Detection Engine</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Evaluates quiz attempts per topic. Scores under 40% immediately trigger weak topic alerts and remedial recommendations.
              </p>
            </div>

            <div className="bg-white p-6 rounded-xl border border-slate-200 shadow-sm">
              <div className="w-12 h-12 rounded-lg bg-amber-100 text-amber-800 flex items-center justify-center mb-4">
                <Languages className="w-6 h-6" />
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Bilingual Telugu & English</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Full multilingual support for rural Telugu-medium students across all curriculum topics, notes, and quiz questions.
              </p>
            </div>

            <div className="bg-white p-6 rounded-xl border border-slate-200 shadow-sm">
              <div className="w-12 h-12 rounded-lg bg-indigo-100 text-indigo-800 flex items-center justify-center mb-4">
                <WifiOff className="w-6 h-6" />
              </div>
              <h3 className="text-lg font-bold text-slate-900 mb-2">Low-Bandwidth Rural Mode</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Compressed notes and downloadable study material for offline reading in low connectivity rural areas.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="mt-auto bg-slate-900 text-slate-400 py-6 px-4 text-center text-xs">
        <p className="font-semibold text-slate-300">Digital Learning Platform for Rural School Students in Telangana</p>
        <p className="mt-1 text-slate-500">CSE Final-Year Major Project | Spring Boot + React + MySQL Architecture</p>
      </footer>
    </div>
  );
};

export default LandingPage;
