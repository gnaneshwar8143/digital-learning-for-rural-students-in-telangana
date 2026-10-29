import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { curriculumApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import { useAuth } from '../../context/AuthContext';
import { Calculator, Atom, Globe, BookOpen, Languages, ArrowRight } from 'lucide-react';

const SubjectExplorer = () => {
  const [subjects, setSubjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const { lang, t } = useLanguage();
  const { user } = useAuth();

  const classLevel = user?.classLevel || 10;
  const classId = classLevel - 5; // mapping Class 10 -> 5

  useEffect(() => {
    curriculumApi.getSubjects(classId > 0 ? classId : 5)
      .then((res) => setSubjects(res.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [classId]);

  const getIcon = (iconName) => {
    switch (iconName) {
      case 'Calculator': return <Calculator className="w-8 h-8 text-emerald-600" />;
      case 'Atom': return <Atom className="w-8 h-8 text-blue-600" />;
      case 'Globe': return <Globe className="w-8 h-8 text-amber-600" />;
      case 'Languages': return <Languages className="w-8 h-8 text-purple-600" />;
      default: return <BookOpen className="w-8 h-8 text-indigo-600" />;
    }
  };

  return (
    <div className="space-y-6">
      <div className="border-b border-slate-200 pb-4">
        <span className="px-2.5 py-1 bg-emerald-100 text-emerald-800 text-xs font-bold rounded">
          Class {classLevel} Syllabus
        </span>
        <h1 className="text-2xl font-extrabold text-slate-900 mt-2">{t('subjects')}</h1>
        <p className="text-xs text-slate-500">Telangana State Board of Secondary Education Curriculum</p>
      </div>

      {loading ? (
        <div className="p-8 text-center text-slate-500 font-semibold">Loading Telangana Subjects...</div>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {subjects.map((sub) => {
            const name = lang === 'te' ? sub.nameTe : sub.nameEn;
            return (
              <div key={sub.id} className="bg-white rounded-2xl border border-slate-200 p-6 shadow-sm hover:shadow-md transition flex flex-col justify-between">
                <div>
                  <div className="flex items-center justify-between mb-4">
                    <div className="p-3 bg-slate-50 rounded-xl border border-slate-100">
                      {getIcon(sub.icon)}
                    </div>
                    <span className="text-xs font-mono font-semibold text-slate-400">{sub.code}</span>
                  </div>
                  <h3 className="text-xl font-bold text-slate-900 mb-1">{name}</h3>
                  <p className="text-xs text-slate-500">Telangana Board Standard Syllabus</p>
                </div>

                <div className="mt-6 pt-4 border-t border-slate-100">
                  <Link
                    to={`/student/subjects/${sub.id}/chapters`}
                    className="w-full py-2.5 px-4 bg-emerald-800 hover:bg-emerald-700 text-white font-semibold text-xs rounded-xl transition flex items-center justify-center shadow-sm"
                  >
                    <span>View Chapters & Topics</span>
                    <ArrowRight className="w-4 h-4 ml-1" />
                  </Link>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default SubjectExplorer;
