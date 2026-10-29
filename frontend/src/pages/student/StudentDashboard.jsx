import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { studentApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import StatCard from '../../components/StatCard';
import WeakTopicBadge from '../../components/WeakTopicBadge';
import { 
  BookOpen, 
  CheckCircle2, 
  Award, 
  TrendingUp, 
  AlertCircle, 
  ArrowRight, 
  FileText, 
  RotateCcw,
  Sparkles
} from 'lucide-react';
import { ResponsiveContainer, BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts';

const StudentDashboard = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const { t, lang } = useLanguage();

  useEffect(() => {
    studentApi.getDashboard()
      .then((res) => setData(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <div className="p-8 text-center text-slate-500">
        <div className="animate-spin w-8 h-8 border-4 border-emerald-600 border-t-transparent rounded-full mx-auto mb-3"></div>
        <p className="text-sm font-semibold">Loading Student Dashboard...</p>
      </div>
    );
  }

  const weakTopics = data?.weakTopics || [];
  const recs = data?.activeRecommendations || [];
  const attempts = data?.recentQuizAttempts || [];

  const chartData = attempts.map((a) => ({
    name: a.topicNameEn,
    Score: a.percentage,
  }));

  return (
    <div className="space-y-6">
      
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-emerald-900 to-slate-900 rounded-2xl p-6 text-white shadow-md flex flex-col md:flex-row justify-between items-start md:items-center">
        <div>
          <span className="inline-block px-2.5 py-1 bg-amber-400 text-emerald-950 text-xs font-bold rounded mb-2">
            Class {data?.classLevel || 10} - Telangana Board
          </span>
          <h1 className="text-2xl sm:text-3xl font-extrabold">{t('welcomeStudent')}, {data?.studentName}!</h1>
          <p className="text-xs text-emerald-200 mt-1">
            Personalized Learning Dashboard • Rural Telangana Digital Education Initiative
          </p>
        </div>

        <Link
          to="/student/subjects"
          className="mt-4 md:mt-0 px-4 py-2 bg-amber-400 hover:bg-amber-300 text-emerald-950 font-bold text-xs rounded-xl shadow transition flex items-center"
        >
          <span>{t('subjects')}</span>
          <ArrowRight className="w-4 h-4 ml-1" />
        </Link>
      </div>

      {/* Overview Stat Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <StatCard title={t('totalSubjects')} value={data?.totalSubjects || 5} icon={BookOpen} color="emerald" subtext="Telangana State Curriculum" />
        <StatCard title={t('completedLessons')} value={data?.completedLessons || 0} icon={CheckCircle2} color="indigo" subtext="Quizzes Completed" />
        <StatCard title={t('avgScore')} value={`${data?.averageQuizScore || 0}%`} icon={Award} color="amber" subtext="Average Score" />
        <StatCard title={t('overallImprovement')} value={`+${data?.overallImprovementPercentage || 0}%`} icon={TrendingUp} color="blue" subtext="Pre/Post Test Gain" />
      </div>

      {/* Core Research Component: Weak Topic Alert Drawer */}
      {weakTopics.length > 0 ? (
        <div className="bg-rose-50 border-2 border-rose-300 rounded-2xl p-5 shadow-sm">
          <div className="flex items-center space-x-2 text-rose-800 font-bold text-lg mb-2">
            <AlertCircle className="w-6 h-6 text-rose-600 animate-pulse" />
            <h2>{t('weakTopicAlert')} ({weakTopics.length})</h2>
          </div>
          <p className="text-xs text-rose-700 mb-4">{t('weakTopicDesc')}</p>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {weakTopics.map((wt) => {
              const topicTitle = lang === 'te' ? wt.topicNameTe : wt.topicNameEn;
              return (
                <div key={wt.topicId} className="bg-white rounded-xl p-4 border border-rose-200 shadow-sm flex flex-col justify-between">
                  <div>
                    <div className="flex items-center justify-between mb-2">
                      <span className="text-xs font-bold text-slate-500 uppercase">{wt.subjectNameEn}</span>
                      <WeakTopicBadge status={wt.status} />
                    </div>
                    <h3 className="font-bold text-slate-900 text-base">{topicTitle}</h3>
                    <p className="text-xs text-rose-600 font-semibold mt-1">Quiz Score: {wt.scorePercentage}%</p>
                  </div>

                  <div className="mt-4 pt-3 border-t border-slate-100 flex items-center justify-between">
                    <span className="text-[11px] text-slate-500 font-medium">Automated Remediation Ready</span>
                    <Link
                      to="/student/recommendations"
                      className="px-3 py-1.5 bg-rose-700 hover:bg-rose-800 text-white font-semibold text-xs rounded-lg transition flex items-center"
                    >
                      <span>{t('studyMaterial')}</span>
                      <ArrowRight className="w-3.5 h-3.5 ml-1" />
                    </Link>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      ) : (
        <div className="bg-emerald-50 border border-emerald-200 rounded-2xl p-5 text-emerald-800 text-sm font-semibold flex items-center space-x-3">
          <Sparkles className="w-5 h-5 text-emerald-600" />
          <span>{t('noWeakTopics')}</span>
        </div>
      )}

      {/* Recommendations & Recent Quiz Graph Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        
        {/* Active Personalized Recommendations */}
        <div className="bg-white rounded-2xl border border-slate-200 p-5 shadow-sm">
          <h2 className="text-lg font-bold text-slate-900 mb-3 flex items-center">
            <FileText className="w-5 h-5 text-amber-500 mr-2" />
            {t('activeRecommendations')}
          </h2>

          {recs.length === 0 ? (
            <p className="text-xs text-slate-500 italic">No pending recommendations. Take a quiz to receive feedback!</p>
          ) : (
            <div className="space-y-3">
              {recs.slice(0, 3).map((r) => (
                <div key={r.id} className="p-3 bg-amber-50/60 border border-amber-200 rounded-xl">
                  <div className="flex items-center justify-between text-xs font-semibold text-amber-900 mb-1">
                    <span>{lang === 'te' ? r.topicNameTe : r.topicNameEn}</span>
                    <span className="px-2 py-0.5 bg-amber-200 text-amber-900 rounded font-mono">{r.recommendationType}</span>
                  </div>
                  <p className="text-xs text-slate-700 mb-2">{lang === 'te' ? r.messageTe : r.messageEn}</p>
                  
                  <div className="flex items-center justify-between pt-2 border-t border-amber-200/60">
                    <span className="text-[11px] text-slate-600 font-medium">Material: {lang === 'te' ? r.recommendedMaterialTitleTe : r.recommendedMaterialTitleEn}</span>
                    <Link
                      to="/student/recommendations"
                      className="px-2.5 py-1 bg-amber-500 hover:bg-amber-600 text-slate-950 font-bold text-xs rounded transition flex items-center"
                    >
                      <RotateCcw className="w-3 h-3 mr-1" />
                      {t('takeRetest')}
                    </Link>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>

        {/* Recharts Score History Chart */}
        <div className="bg-white rounded-2xl border border-slate-200 p-5 shadow-sm">
          <h2 className="text-lg font-bold text-slate-900 mb-3 flex items-center">
            <TrendingUp className="w-5 h-5 text-emerald-600 mr-2" />
            {t('recentQuizAttempts')}
          </h2>

          {chartData.length === 0 ? (
            <p className="text-xs text-slate-500 italic">No quiz history available yet.</p>
          ) : (
            <div className="h-60 w-full pt-2">
              <ResponsiveContainer width="100%" height="100%">
                <BarChart data={chartData}>
                  <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
                  <XAxis dataKey="name" stroke="#64748b" fontSize={11} tickLine={false} />
                  <YAxis domain={[0, 100]} stroke="#64748b" fontSize={11} />
                  <Tooltip />
                  <Bar dataKey="Score" fill="#047857" radius={[6, 6, 0, 0]} />
                </BarChart>
              </ResponsiveContainer>
            </div>
          )}
        </div>

      </div>
    </div>
  );
};

export default StudentDashboard;
