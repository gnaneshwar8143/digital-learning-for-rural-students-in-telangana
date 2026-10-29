import React, { useEffect, useState } from 'react';
import { teacherApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import WeakTopicBadge from '../../components/WeakTopicBadge';
import { Users, AlertCircle, Award } from 'lucide-react';

const StudentPerformancePage = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const { lang, t } = useLanguage();

  useEffect(() => {
    teacherApi.getDashboard()
      .then((res) => setData(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="p-8 text-center text-slate-500">Loading Student Records...</div>;

  const students = data?.recentStudentActivities || [];

  return (
    <div className="space-y-6">
      <div className="border-b border-slate-200 pb-4">
        <h1 className="text-2xl font-extrabold text-slate-900">{t('studentList')}</h1>
        <p className="text-xs text-slate-500">
          Drill-down evaluation matrix identifying individual student topic weaknesses & pre/post test gains.
        </p>
      </div>

      <div className="space-y-4">
        {students.map((s) => (
          <div key={s.studentId} className="bg-white rounded-2xl border border-slate-200 p-6 shadow-sm space-y-4">
            <div className="flex flex-col sm:flex-row sm:items-center justify-between border-b border-slate-100 pb-3">
              <div>
                <h3 className="text-xl font-bold text-slate-900">{s.studentName}</h3>
                <p className="text-xs text-slate-500 font-mono">{s.email} • Class {s.classLevel}</p>
              </div>
              <div className="mt-2 sm:mt-0 flex items-center space-x-3">
                <span className="text-xs font-bold text-slate-700">Avg Quiz Score:</span>
                <span className="px-3 py-1 bg-emerald-100 text-emerald-800 font-mono font-extrabold rounded-lg text-sm">
                  {s.averageScore}%
                </span>
              </div>
            </div>

            {/* Weak Topics */}
            <div>
              <h4 className="text-xs font-bold text-slate-400 uppercase tracking-wider mb-2">Identified Weak Topics</h4>
              {s.weakTopics?.length === 0 ? (
                <p className="text-xs text-emerald-700 font-semibold">No weak topics detected for this student.</p>
              ) : (
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
                  {s.weakTopics?.map((wt) => (
                    <div key={wt.topicId} className="p-3 bg-rose-50 border border-rose-200 rounded-xl flex items-center justify-between">
                      <div>
                        <span className="text-[10px] font-bold text-rose-800 uppercase">{wt.subjectNameEn}</span>
                        <p className="font-bold text-slate-900 text-xs">{lang === 'te' ? wt.topicNameTe : wt.topicNameEn}</p>
                        <p className="text-[11px] text-rose-700 font-semibold">Score: {wt.scorePercentage}%</p>
                      </div>
                      <WeakTopicBadge status={wt.status} />
                    </div>
                  ))}
                </div>
              )}
            </div>

          </div>
        ))}
      </div>
    </div>
  );
};

export default StudentPerformancePage;
