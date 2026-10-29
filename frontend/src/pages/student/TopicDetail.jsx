import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { curriculumApi, studentApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import MaterialCard from '../../components/MaterialCard';
import { BookOpen, FileText, Play, CheckCircle2, ArrowRight } from 'lucide-react';

const TopicDetail = () => {
  const { subjectId } = useParams();
  const [chapters, setChapters] = useState([]);
  const [selectedTopic, setSelectedTopic] = useState(null);
  const [materials, setMaterials] = useState([]);
  const [quizzes, setQuizzes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedMaterial, setSelectedMaterial] = useState(null);

  const { lang, t } = useLanguage();

  useEffect(() => {
    curriculumApi.getChapters(subjectId)
      .then(async (res) => {
        setChapters(res.data);
        if (res.data.length > 0) {
          const ch1 = res.data[0];
          const topicsRes = await curriculumApi.getTopics(ch1.id);
          if (topicsRes.data.length > 0) {
            loadTopicData(topicsRes.data[0]);
          }
        }
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [subjectId]);

  const loadTopicData = async (topic) => {
    setSelectedTopic(topic);
    setSelectedMaterial(null);
    try {
      const mRes = await curriculumApi.getMaterials(topic.id);
      setMaterials(mRes.data);
      const qRes = await studentApi.getQuizzesByTopic(topic.id);
      setQuizzes(qRes.data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="space-y-6">
      
      {/* Header */}
      <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm flex flex-col md:flex-row justify-between items-start md:items-center">
        <div>
          <span className="text-xs font-semibold text-emerald-700 uppercase tracking-wider">Telangana State Board Curriculum</span>
          <h1 className="text-2xl font-extrabold text-slate-900 mt-1">
            {selectedTopic ? (lang === 'te' ? selectedTopic.nameTe : selectedTopic.nameEn) : 'Topic Explorer'}
          </h1>
          <p className="text-xs text-slate-500 mt-1">
            {selectedTopic ? (lang === 'te' ? selectedTopic.descriptionTe : selectedTopic.descriptionEn) : 'Select a topic from chapters below'}
          </p>
        </div>

        {selectedTopic && quizzes.length > 0 && (
          <Link
            to={`/student/quizzes/${quizzes[0].id}`}
            className="mt-4 md:mt-0 px-4 py-2.5 bg-amber-400 hover:bg-amber-300 text-emerald-950 font-bold text-xs rounded-xl transition shadow flex items-center"
          >
            <span>{t('startQuiz')}</span>
            <ArrowRight className="w-4 h-4 ml-1" />
          </Link>
        )}
      </div>

      {/* Chapters & Topics Browser */}
      <div className="grid grid-cols-1 lg:grid-cols-4 gap-6">
        
        {/* Sidebar Chapter Selector */}
        <div className="lg:col-span-1 bg-white p-4 rounded-2xl border border-slate-200 shadow-sm space-y-4">
          <h3 className="font-bold text-slate-900 text-sm border-b pb-2">Chapters & Topics</h3>

          {chapters.map((ch) => (
            <div key={ch.id} className="space-y-1">
              <p className="text-xs font-bold text-emerald-800 uppercase tracking-wide">
                Ch {ch.chapterNumber}: {lang === 'te' ? ch.titleTe : ch.titleEn}
              </p>
              <TopicList chapterId={ch.id} selectedTopicId={selectedTopic?.id} onSelectTopic={loadTopicData} lang={lang} />
            </div>
          ))}
        </div>

        {/* Content & Materials Area */}
        <div className="lg:col-span-3 space-y-6">
          
          {/* Material Reader Modal / Card View */}
          {selectedMaterial && (
            <div className="bg-emerald-950 text-emerald-50 p-6 rounded-2xl border border-emerald-800 shadow-lg relative">
              <button
                onClick={() => setSelectedMaterial(null)}
                className="absolute top-4 right-4 text-emerald-400 hover:text-amber-300 text-xs font-bold"
              >
                ✕ Close Reader
              </button>

              <span className="inline-block px-2.5 py-1 bg-amber-400 text-emerald-950 text-[10px] font-bold uppercase rounded mb-2">
                {selectedMaterial.materialType} Reader
              </span>
              <h3 className="text-xl font-bold mb-3">{lang === 'te' ? selectedMaterial.titleTe : selectedMaterial.titleEn}</h3>

              {selectedMaterial.materialType === 'VIDEO' ? (
                <div className="p-4 bg-emerald-900 rounded-xl text-center text-xs text-amber-200 border border-emerald-700">
                  <Play className="w-8 h-8 mx-auto mb-2 text-amber-400 animate-bounce" />
                  <p className="font-bold text-sm">Low-Bandwidth Rural Stream Player</p>
                  <p className="text-[11px] opacity-80 mt-1">Compressed for 2G/3G connectivity ({selectedMaterial.fileSizeKb} KB)</p>
                </div>
              ) : (
                <div className="bg-white/10 p-4 rounded-xl text-xs leading-relaxed whitespace-pre-wrap font-mono border border-white/10">
                  {lang === 'te' ? selectedMaterial.contentTextTe : selectedMaterial.contentTextEn}
                </div>
              )}
            </div>
          )}

          {/* Available Study Materials Grid */}
          <div>
            <h3 className="text-lg font-bold text-slate-900 mb-3 flex items-center">
              <FileText className="w-5 h-5 text-emerald-700 mr-2" />
              Learning Resources ({materials.length})
            </h3>

            {materials.length === 0 ? (
              <p className="text-xs text-slate-500 italic">No materials uploaded yet for this topic.</p>
            ) : (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {materials.map((m) => (
                  <MaterialCard key={m.id} material={m} onSelect={(mat) => setSelectedMaterial(mat)} />
                ))}
              </div>
            )}
          </div>

          {/* Associated Topic Quizzes */}
          <div>
            <h3 className="text-lg font-bold text-slate-900 mb-3 flex items-center">
              <CheckCircle2 className="w-5 h-5 text-amber-600 mr-2" />
              Topic Quizzes ({quizzes.length})
            </h3>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {quizzes.map((q) => (
                <div key={q.id} className="bg-white p-4 rounded-xl border border-slate-200 shadow-sm flex items-center justify-between">
                  <div>
                    <span className="px-2 py-0.5 text-[10px] font-bold bg-amber-100 text-amber-900 rounded">
                      {q.testCategory || 'CHAPTER_QUIZ'}
                    </span>
                    <h4 className="font-bold text-slate-900 text-sm mt-1">{lang === 'te' ? q.titleTe : q.titleEn}</h4>
                    <p className="text-xs text-slate-500">Duration: {q.durationMinutes} mins | Total: {q.totalMarks} Marks</p>
                  </div>

                  <Link
                    to={`/student/quizzes/${q.id}`}
                    className="px-3 py-1.5 bg-emerald-800 hover:bg-emerald-700 text-white rounded-lg text-xs font-bold transition flex items-center"
                  >
                    <span>{t('startQuiz')}</span>
                    <ArrowRight className="w-3.5 h-3.5 ml-1" />
                  </Link>
                </div>
              ))}
            </div>
          </div>

        </div>
      </div>
    </div>
  );
};

const TopicList = ({ chapterId, selectedTopicId, onSelectTopic, lang }) => {
  const [topics, setTopics] = useState([]);

  useEffect(() => {
    curriculumApi.getTopics(chapterId).then((res) => setTopics(res.data));
  }, [chapterId]);

  return (
    <div className="space-y-1 pl-2">
      {topics.map((t) => (
        <button
          key={t.id}
          onClick={() => onSelectTopic(t)}
          className={`w-full text-left px-2.5 py-1.5 rounded-lg text-xs font-medium transition ${
            selectedTopicId === t.id
              ? 'bg-emerald-100 text-emerald-950 font-bold border border-emerald-300'
              : 'hover:bg-slate-100 text-slate-700'
          }`}
        >
          • {lang === 'te' ? t.nameTe : t.nameEn}
        </button>
      ))}
    </div>
  );
};

export default TopicDetail;
