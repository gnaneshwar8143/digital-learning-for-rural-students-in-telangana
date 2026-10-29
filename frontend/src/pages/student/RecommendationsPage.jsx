import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { studentApi, aiApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import { useAuth } from '../../context/AuthContext';
import WeakTopicBadge from '../../components/WeakTopicBadge';
import { AlertTriangle, BookOpen, RotateCcw, Download, ArrowRight, CheckCircle2, Sparkles, Bot } from 'lucide-react';

const RecommendationsPage = () => {
  const [recs, setRecs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [aiAdvice, setAiAdvice] = useState({});
  const { lang, t } = useLanguage();
  const { user } = useAuth();

  useEffect(() => {
    studentApi.getRecommendations()
      .then(async (res) => {
        const data = res.data || [];
        setRecs(data);

        // Fetch AI recommendations for weak topics
        data.forEach(async (r) => {
          try {
            const aiRes = await aiApi.recommend({
              studentId: user?.id,
              topicId: r.topicId,
              topicName: r.topicNameEn,
              score: r.previousScore || 35.0,
              classLevel: user?.classLevel || 10,
              preferredLanguage: lang,
            });
            setAiAdvice((prev) => ({ ...prev, [r.id]: aiRes.data }));
          } catch (err) {
            console.error(err);
          }
        });
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [lang, user]);

  return (
    <div className="space-y-6 max-w-4xl mx-auto">
      <div className="border-b border-slate-200 pb-4">
        <div className="inline-flex items-center space-x-2 px-3 py-1 bg-amber-100 text-amber-900 text-xs font-bold rounded-full mb-2">
          <Sparkles className="w-4 h-4 text-amber-600" />
          <span>Hybrid AI + Rule-Based Guidance</span>
        </div>
        <h1 className="text-2xl font-extrabold text-slate-900">{t('recommendations')}</h1>
        <p className="text-xs text-slate-500">
          Rule-based weak topic detection combined with AI personalized study recommendations.
        </p>
      </div>

      {loading ? (
        <div className="p-8 text-center text-slate-500 font-semibold">Analyzing Topic Performances...</div>
      ) : recs.length === 0 ? (
        <div className="bg-white p-8 rounded-2xl border border-slate-200 text-center space-y-3">
          <CheckCircle2 className="w-10 h-10 text-emerald-600 mx-auto" />
          <h3 className="text-lg font-bold text-slate-900">No Weak Topics Currently Detected!</h3>
          <p className="text-xs text-slate-500">You are performing well across all attempted Telangana Board topics.</p>
        </div>
      ) : (
        <div className="space-y-5">
          {recs.map((r) => {
            const topicName = lang === 'te' ? r.topicNameTe : r.topicNameEn;
            const message = lang === 'te' ? r.messageTe : r.messageEn;
            const matTitle = lang === 'te' ? r.recommendedMaterialTitleTe : r.recommendedMaterialTitleEn;
            const aiData = aiAdvice[r.id];

            return (
              <div key={r.id} className="bg-white rounded-2xl border border-slate-200 p-6 shadow-sm space-y-4">
                <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-2 border-b border-slate-100 pb-3">
                  <div>
                    <span className="text-xs font-bold text-amber-700 uppercase tracking-wider">{r.recommendationType}</span>
                    <h3 className="text-xl font-bold text-slate-900 mt-0.5">{topicName}</h3>
                  </div>
                  <div className="flex items-center space-x-2">
                    <span className="text-xs font-semibold text-slate-500">Score: {r.previousScore}%</span>
                    <span className={`px-2.5 py-0.5 rounded text-xs font-bold ${
                      r.status === 'RETESTED' ? 'bg-emerald-100 text-emerald-800' : 'bg-amber-100 text-amber-800'
                    }`}>
                      {r.status}
                    </span>
                  </div>
                </div>

                <p className="text-xs text-slate-700 leading-relaxed font-medium">{message}</p>

                {/* AI Personalized Recommendation Badge */}
                {aiData && (
                  <div className="p-4 bg-gradient-to-r from-slate-900 to-indigo-950 text-white rounded-xl space-y-2">
                    <div className="flex items-center justify-between border-b border-slate-700 pb-1.5">
                      <div className="flex items-center space-x-1.5 text-amber-300 font-bold text-xs">
                        <Bot className="w-4 h-4" />
                        <span>AI Personalized Learning Advice</span>
                      </div>
                      <span className={`px-2 py-0.5 rounded text-[10px] font-bold ${
                        aiData.isFallbackMode ? 'bg-amber-300 text-slate-950' : 'bg-emerald-400 text-slate-950'
                      }`}>
                        {aiData.modeLabel || 'AI Demo/Fallback Mode'}
                      </span>
                    </div>

                    <p className="text-xs leading-relaxed text-slate-200">{aiData.personalizedRecommendationMessage}</p>
                  </div>
                )}

                <div className="bg-slate-50 p-4 rounded-xl border border-slate-200 flex flex-col sm:flex-row items-center justify-between gap-3">
                  <div className="flex items-center space-x-3">
                    <div className="p-2.5 bg-emerald-100 text-emerald-800 rounded-lg">
                      <BookOpen className="w-5 h-5" />
                    </div>
                    <div>
                      <p className="text-[10px] uppercase font-bold text-slate-400">Target Material</p>
                      <h4 className="font-bold text-slate-900 text-sm">{matTitle}</h4>
                    </div>
                  </div>

                  <div className="flex items-center space-x-2">
                    <Link
                      to="/student/subjects"
                      className="px-3.5 py-2 bg-emerald-800 hover:bg-emerald-700 text-white rounded-lg text-xs font-bold transition flex items-center"
                    >
                      <BookOpen className="w-3.5 h-3.5 mr-1" />
                      {t('studyMaterial')}
                    </Link>

                    <Link
                      to="/student/subjects"
                      className="px-3.5 py-2 bg-amber-400 hover:bg-amber-300 text-slate-950 font-extrabold text-xs rounded-lg transition flex items-center shadow-sm"
                    >
                      <RotateCcw className="w-3.5 h-3.5 mr-1" />
                      {t('takeRetest')}
                    </Link>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default RecommendationsPage;
