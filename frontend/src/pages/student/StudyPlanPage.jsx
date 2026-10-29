import React, { useEffect, useState } from 'react';
import { aiApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import { useAuth } from '../../context/AuthContext';
import { Calendar, Sparkles, CheckCircle2, Circle, Clock, BookOpen, Video, RotateCcw } from 'lucide-react';

const StudyPlanPage = () => {
  const { user } = useAuth();
  const { lang, t } = useLanguage();

  const [planData, setPlanData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    aiApi.getStudyPlan({
      studentId: user?.id,
      classLevel: user?.classLevel || 10,
      preferredLanguage: lang,
    })
      .then((res) => setPlanData(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [user, lang]);

  const toggleTask = (dayIndex) => {
    if (!planData || !planData.days) return;
    const newDays = [...planData.days];
    newDays[dayIndex].completed = !newDays[dayIndex].completed;
    setPlanData({ ...planData, days: newDays });
  };

  const getMaterialIcon = (type) => {
    switch (type) {
      case 'VIDEO': return <Video className="w-4 h-4 text-blue-600" />;
      case 'PRACTICE': return <BookOpen className="w-4 h-4 text-emerald-600" />;
      case 'RETEST': return <RotateCcw className="w-4 h-4 text-amber-600" />;
      default: return <BookOpen className="w-4 h-4 text-indigo-600" />;
    }
  };

  if (loading) return <div className="p-8 text-center text-slate-500 font-semibold">Generating Your AI Study Plan...</div>;

  const days = planData?.days || [];
  const completedCount = days.filter((d) => d.completed).length;
  const progressPct = days.length > 0 ? Math.round((completedCount / days.length) * 100) : 0;

  return (
    <div className="max-w-4xl mx-auto space-y-6">
      
      {/* Header */}
      <div className="bg-gradient-to-r from-emerald-950 via-slate-900 to-indigo-950 rounded-2xl p-6 text-white shadow-md flex flex-col sm:flex-row justify-between items-start sm:items-center">
        <div>
          <div className="inline-flex items-center space-x-2 px-3 py-1 bg-emerald-800 text-amber-300 text-xs font-semibold rounded-full mb-2">
            <Sparkles className="w-4 h-4 text-amber-400" />
            <span>AI Adaptive Weekly Schedule</span>
          </div>
          <h1 className="text-2xl sm:text-3xl font-extrabold">{t('aiStudyPlan')}</h1>
          <p className="text-xs text-emerald-200 mt-1">
            Tailored 5-day step-by-step remediation schedule for {planData?.studentName || 'Learner'}
          </p>
        </div>

        <div className="mt-4 sm:mt-0 text-center bg-white/10 px-4 py-3 rounded-xl border border-white/20">
          <p className="text-[10px] uppercase font-bold text-amber-300">Plan Completion</p>
          <p className="text-2xl font-extrabold">{progressPct}%</p>
          <p className="text-[11px] text-emerald-200">{completedCount} of {days.length} Tasks Done</p>
        </div>
      </div>

      {/* Mandated AI Status Label */}
      <div className="flex items-center justify-between bg-white p-3 px-4 rounded-xl border border-slate-200 text-xs font-semibold text-slate-700">
        <span className="flex items-center text-slate-500">
          <Calendar className="w-4 h-4 mr-1.5 text-emerald-700" /> Target Topic: Fractions & Operations Remediation
        </span>
        <span className={`px-2.5 py-0.5 rounded text-xs font-bold ${
          planData?.isFallbackMode ? 'bg-amber-100 text-amber-900 border border-amber-300' : 'bg-emerald-100 text-emerald-900'
        }`}>
          {planData?.modeLabel || 'AI Demo/Fallback Mode'}
        </span>
      </div>

      {/* Day-by-Day Schedule List */}
      <div className="space-y-4">
        {days.map((d, idx) => {
          const taskTitle = lang === 'te' ? d.taskTitleTe : d.taskTitleEn;
          return (
            <div
              key={idx}
              onClick={() => toggleTask(idx)}
              className={`p-5 rounded-2xl border transition cursor-pointer flex items-center justify-between ${
                d.completed
                  ? 'bg-emerald-50/70 border-emerald-300 text-slate-900 shadow-sm'
                  : 'bg-white border-slate-200 hover:border-emerald-400 shadow-sm'
              }`}
            >
              <div className="flex items-center space-x-4">
                <button className="text-emerald-700 focus:outline-none">
                  {d.completed ? (
                    <CheckCircle2 className="w-6 h-6 text-emerald-600 fill-emerald-100" />
                  ) : (
                    <Circle className="w-6 h-6 text-slate-300" />
                  )}
                </button>

                <div>
                  <div className="flex items-center space-x-2 mb-1">
                    <span className="px-2 py-0.5 bg-slate-900 text-white rounded text-[10px] font-bold uppercase">
                      {d.dayName}
                    </span>
                    <span className="inline-flex items-center text-[11px] font-semibold text-slate-500">
                      {getMaterialIcon(d.materialType)}
                      <span className="ml-1 uppercase text-[10px] font-bold">{d.materialType}</span>
                    </span>
                  </div>
                  <h3 className={`text-base font-bold ${d.completed ? 'line-through text-slate-500' : 'text-slate-900'}`}>
                    {taskTitle}
                  </h3>
                </div>
              </div>

              <div className="flex items-center space-x-1 text-slate-500 text-xs font-mono font-semibold bg-slate-100 px-3 py-1.5 rounded-lg border border-slate-200">
                <Clock className="w-3.5 h-3.5 text-slate-400" />
                <span>{d.durationMinutes} mins</span>
              </div>
            </div>
          );
        })}
      </div>

    </div>
  );
};

export default StudyPlanPage;
