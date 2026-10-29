import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { teacherApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import StatCard from '../../components/StatCard';
import MaterialUploadModal from '../../components/MaterialUploadModal';
import { Users, Award, AlertCircle, PlusCircle, BookOpen, Upload, ArrowRight } from 'lucide-react';
import { ResponsiveContainer, BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts';

const TeacherDashboard = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [uploadModalOpen, setUploadModalOpen] = useState(false);
  const { lang, t } = useLanguage();

  const loadDashboard = () => {
    setLoading(true);
    teacherApi.getDashboard()
      .then((res) => setData(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    loadDashboard();
  }, []);

  if (loading) {
    return <div className="p-8 text-center text-slate-500 font-semibold">Loading Teacher Dashboard...</div>;
  }

  const commonWeak = data?.mostCommonWeakTopics || [];
  const students = data?.recentStudentActivities || [];

  const chartData = commonWeak.map((w) => ({
    name: lang === 'te' ? w.topicNameTe : w.topicNameEn,
    Students: w.studentCount,
  }));

  return (
    <div className="space-y-6">
      
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-emerald-950 via-slate-900 to-emerald-900 rounded-2xl p-6 text-white shadow-md flex flex-col md:flex-row justify-between items-start md:items-center">
        <div>
          <span className="px-2.5 py-1 bg-emerald-800 text-amber-300 text-xs font-bold rounded uppercase">
            Telangana High School Educator Panel
          </span>
          <h1 className="text-2xl sm:text-3xl font-extrabold mt-1">{t('teacherWelcome')}</h1>
          <p className="text-xs text-emerald-200 mt-1">
            Monitor rural student weak topics, assign remediation quizzes & upload targeted study resources.
          </p>
        </div>

        <div className="mt-4 md:mt-0 flex items-center space-x-2">
          <button
            onClick={() => setUploadModalOpen(true)}
            className="px-3.5 py-2 bg-emerald-800 hover:bg-emerald-700 text-white font-bold text-xs rounded-xl shadow transition flex items-center border border-emerald-600"
          >
            <Upload className="w-4 h-4 mr-1 text-amber-300" />
            <span>Upload Material</span>
          </button>

          <Link
            to="/teacher/create-quiz"
            className="px-3.5 py-2 bg-amber-400 hover:bg-amber-300 text-emerald-950 font-extrabold text-xs rounded-xl shadow transition flex items-center"
          >
            <PlusCircle className="w-4 h-4 mr-1" />
            <span>{t('createQuiz')}</span>
          </Link>
        </div>
      </div>

      {/* Overview Stat Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <StatCard title={t('totalStudents')} value={data?.totalStudents || 0} icon={Users} color="emerald" subtext="Enrolled Rural Learners" />
        <StatCard title={t('classAverage')} value={`${data?.averageClassScore || 0}%`} icon={Award} color="amber" subtext="Overall Quiz Performance" />
        <StatCard title={t('studentsNeedingHelp')} value={data?.studentsNeedingHelp || 0} icon={AlertCircle} color="rose" subtext="Students with Weak Topics" />
        <StatCard title="Total Quizzes" value={data?.totalQuizzes || 0} icon={BookOpen} color="indigo" subtext="Created Assessments" />
      </div>

      {/* Weak-Topic Charts & Common Weak Topics Matrix */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        
        {/* Recharts Weak Topic Chart */}
        <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-3">
          <h2 className="text-lg font-bold text-slate-900 flex items-center">
            <AlertCircle className="w-5 h-5 text-rose-600 mr-2" />
            Weak Topics Student Distribution Chart
          </h2>
          {chartData.length === 0 ? (
            <p className="text-xs text-slate-500 italic">No weak topic analytics available.</p>
          ) : (
            <div className="h-60 w-full pt-2">
              <ResponsiveContainer width="100%" height="100%">
                <BarChart data={chartData}>
                  <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
                  <XAxis dataKey="name" stroke="#64748b" fontSize={11} />
                  <YAxis stroke="#64748b" fontSize={11} allowDecimals={false} />
                  <Tooltip />
                  <Bar dataKey="Students" fill="#e11d48" radius={[6, 6, 0, 0]} />
                </BarChart>
              </ResponsiveContainer>
            </div>
          )}
        </div>

        {/* Most Common Weak Topics Matrix Cards */}
        <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-4">
          <h2 className="text-lg font-bold text-slate-900 flex items-center">
            <AlertCircle className="w-5 h-5 text-amber-600 mr-2" />
            {t('commonWeakTopics')}
          </h2>

          {commonWeak.length === 0 ? (
            <p className="text-xs text-slate-500 italic">No aggregated weak topics found across class.</p>
          ) : (
            <div className="space-y-3">
              {commonWeak.map((wt) => (
                <div key={wt.topicId} className="p-3.5 rounded-xl border border-rose-200 bg-rose-50/60 flex items-center justify-between">
                  <div>
                    <span className="text-[10px] font-bold text-rose-800 uppercase">{wt.subjectNameEn}</span>
                    <h4 className="font-bold text-slate-900 text-sm">
                      {lang === 'te' ? wt.topicNameTe : wt.topicNameEn}
                    </h4>
                  </div>
                  <span className="px-3 py-1 bg-rose-700 text-white rounded-full text-xs font-bold shadow-sm">
                    {wt.studentCount} Students Flagged
                  </span>
                </div>
              ))}
            </div>
          )}
        </div>

      </div>

      {/* Student List & Performance Inspection Table */}
      <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-4">
        <div className="flex items-center justify-between">
          <h2 className="text-lg font-bold text-slate-900">{t('studentList')}</h2>
          <Link to="/teacher/students" className="text-xs font-bold text-emerald-700 hover:underline">
            View All Students →
          </Link>
        </div>

        <div className="overflow-x-auto">
          <table className="w-full text-left text-xs">
            <thead className="bg-slate-50 text-slate-500 uppercase tracking-wider font-semibold border-b border-slate-200">
              <tr>
                <th className="p-3">Student Name</th>
                <th className="p-3">Class Level</th>
                <th className="p-3">Average Score</th>
                <th className="p-3">Weak Topics Count</th>
                <th className="p-3">Status</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100">
              {students.map((s) => (
                <tr key={s.studentId} className="hover:bg-slate-50 font-medium">
                  <td className="p-3 font-bold text-slate-900">{s.studentName}</td>
                  <td className="p-3">Class {s.classLevel}</td>
                  <td className="p-3 font-mono font-bold text-emerald-700">{s.averageScore}%</td>
                  <td className="p-3">
                    <span className={`px-2 py-0.5 rounded text-xs font-bold ${
                      s.weakTopicsCount > 0 ? 'bg-rose-100 text-rose-800' : 'bg-emerald-100 text-emerald-800'
                    }`}>
                      {s.weakTopicsCount} Weak
                    </span>
                  </td>
                  <td className="p-3 text-slate-600">
                    {s.weakTopicsCount > 0 ? 'Needs Remediation' : 'Proficient'}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Material Upload Modal */}
      <MaterialUploadModal
        isOpen={uploadModalOpen}
        onClose={() => setUploadModalOpen(false)}
        onSuccess={loadDashboard}
      />

    </div>
  );
};

export default TeacherDashboard;
