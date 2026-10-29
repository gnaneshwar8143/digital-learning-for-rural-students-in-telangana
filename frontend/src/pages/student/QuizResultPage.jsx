import React, { useState } from 'react';
import { useLocation, Link } from 'react-router-dom';
import { useLanguage } from '../../context/LanguageContext';
import { useAuth } from '../../context/AuthContext';
import { aiApi } from '../../services/api';
import WeakTopicBadge from '../../components/WeakTopicBadge';
import { 
  Award, 
  AlertCircle, 
  CheckCircle2, 
  XCircle, 
  BookOpen, 
  RotateCcw, 
  ArrowRight,
  Sparkles,
  Bot,
  ChevronDown,
  ChevronUp
} from 'lucide-react';

const QuizResultPage = () => {
  const location = useLocation();
  const { lang, t } = useLanguage();
  const { user } = useAuth();
  const result = location.state?.result;

  const [aiExplanations, setAiExplanations] = useState({});
  const [loadingAi, setLoadingAi] = useState({});
  const [expandedAi, setExpandedAi] = useState({});

  if (!result) {
    return (
      <div className="p-8 text-center space-y-4">
        <p className="text-slate-600">No quiz result data found.</p>
        <Link to="/student/dashboard" className="px-4 py-2 bg-emerald-800 text-white rounded-lg text-xs font-bold">
          Go to Dashboard
        </Link>
      </div>
    );
  }

  const rec = result.recommendation;
  const isWeak = result.weakTopicDetected;

  const handleFetchAiExplanation = async (qId, questionText, studentAns, correctAns) => {
    if (expandedAi[qId]) {
      setExpandedAi((prev) => ({ ...prev, [qId]: false }));
      return;
    }

    setExpandedAi((prev) => ({ ...prev, [qId]: true }));
    if (aiExplanations[qId]) return;

    setLoadingAi((prev) => ({ ...prev, [qId]: true }));
    try {
      const res = await aiApi.explainAnswer({
        questionText: questionText || "Sample Quiz Question",
        studentAnswer: studentAns || "Selected Option",
        correctAnswer: correctAns || "Correct Option",
        topicName: result.topicNameEn || "Fractions",
        classLevel: user?.classLevel || 10,
        preferredLanguage: lang,
      });
      setAiExplanations((prev) => ({ ...prev, [qId]: res.data }));
    } catch (err) {
      console.error(err);
    } finally {
      setLoadingAi((prev) => ({ ...prev, [qId]: false }));
    }
  };

  return (
    <div className="max-w-4xl mx-auto space-y-6">
      
      {/* Quiz Result Header Banner */}
      <div className={`p-6 rounded-2xl text-white shadow-md flex flex-col md:flex-row justify-between items-start md:items-center ${
        isWeak ? 'bg-gradient-to-r from-rose-900 to-slate-900' : 'bg-gradient-to-r from-emerald-900 to-slate-900'
      }`}>
        <div>
          <span className="px-2.5 py-1 bg-white/20 text-white text-xs font-bold rounded uppercase">
            {result.topicNameEn} Assessment
          </span>
          <h1 className="text-2xl font-extrabold mt-1">{t('quizResults')}</h1>
          <p className="text-xs opacity-90 mt-1">{result.quizTitle}</p>
        </div>

        <div className="mt-4 md:mt-0 text-center bg-white/10 px-5 py-3 rounded-xl border border-white/20">
          <p className="text-xs uppercase font-bold tracking-wider text-amber-300">{t('yourScore')}</p>
          <p className="text-3xl font-extrabold">{result.score} / {result.totalMarks}</p>
          <p className="text-xs font-semibold text-emerald-200">{result.percentage}% Marks</p>
        </div>
      </div>

      {/* Weak Topic & Recommendation Alert Box */}
      {rec && (
        <div className={`p-6 rounded-2xl border-2 shadow-md ${
          isWeak ? 'bg-rose-50 border-rose-300' : 'bg-amber-50 border-amber-300'
        }`}>
          <div className="flex items-center justify-between mb-3">
            <div className="flex items-center space-x-2">
              <AlertCircle className={`w-6 h-6 ${isWeak ? 'text-rose-600' : 'text-amber-600'}`} />
              <h2 className="text-lg font-bold text-slate-900">{t('recommendationTitle')}</h2>
            </div>
            <WeakTopicBadge status={result.status} />
          </div>

          <p className="text-sm font-semibold text-slate-800 mb-4">
            {lang === 'te' ? rec.messageTe : rec.messageEn}
          </p>

          <div className="bg-white p-4 rounded-xl border border-slate-200 flex flex-col sm:flex-row items-center justify-between gap-3">
            <div>
              <span className="text-[10px] font-bold text-slate-400 uppercase">Targeted Learning Material</span>
              <h4 className="font-bold text-slate-900 text-sm">
                {lang === 'te' ? rec.recommendedMaterialTitleTe : rec.recommendedMaterialTitleEn}
              </h4>
            </div>

            <div className="flex items-center space-x-2">
              <Link
                to="/student/recommendations"
                className="px-4 py-2 bg-emerald-800 hover:bg-emerald-700 text-white text-xs font-bold rounded-lg transition flex items-center shadow-sm"
              >
                <BookOpen className="w-3.5 h-3.5 mr-1" />
                {t('studyMaterial')}
              </Link>
              <Link
                to={`/student/quizzes/${result.quizId}`}
                className="px-4 py-2 bg-amber-400 hover:bg-amber-300 text-slate-950 text-xs font-extrabold rounded-lg transition flex items-center shadow-sm"
              >
                <RotateCcw className="w-3.5 h-3.5 mr-1" />
                {t('takeRetest')}
              </Link>
            </div>
          </div>
        </div>
      )}

      {/* Detailed Question Feedback & AI Explanation Buttons */}
      <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-4">
        <h2 className="text-lg font-bold text-slate-900 border-b pb-3">Detailed Question Breakdown</h2>

        <div className="space-y-4">
          {result.questionFeedback?.map((q, idx) => {
            const qId = q.questionId || idx;
            const isExp = expandedAi[qId];
            const aiData = aiExplanations[qId];
            const isLoadingThis = loadingAi[qId];

            return (
              <div key={qId} className="p-4 rounded-xl border border-slate-200 bg-slate-50/50 space-y-3">
                <div className="flex items-center justify-between">
                  <span className="text-xs font-bold text-slate-500">Question {idx + 1}</span>
                  {q.isCorrect ? (
                    <span className="inline-flex items-center text-xs font-bold text-emerald-700 bg-emerald-100 px-2 py-0.5 rounded">
                      <CheckCircle2 className="w-3.5 h-3.5 mr-1" /> Correct (+1)
                    </span>
                  ) : (
                    <span className="inline-flex items-center text-xs font-bold text-rose-700 bg-rose-100 px-2 py-0.5 rounded">
                      <XCircle className="w-3.5 h-3.5 mr-1" /> Incorrect (0)
                    </span>
                  )}
                </div>

                <p className="font-bold text-slate-900 text-sm">{lang === 'te' ? q.questionTextTe : q.questionTextEn}</p>

                {(q.explanationEn || q.explanationTe) && (
                  <div className="p-3 bg-slate-100 text-slate-800 border border-slate-200 rounded-lg text-xs">
                    <span className="font-bold">{t('explanation')}: </span>
                    {lang === 'te' ? q.explanationTe : q.explanationEn}
                  </div>
                )}

                {/* AI Explanation Button for Incorrect Answers */}
                {!q.isCorrect && (
                  <div>
                    <button
                      onClick={() => handleFetchAiExplanation(
                        qId,
                        lang === 'te' ? q.questionTextTe : q.questionTextEn,
                        "Student Answer Option",
                        "Correct Option"
                      )}
                      className="px-3 py-1.5 bg-indigo-50 hover:bg-indigo-100 text-indigo-900 font-bold text-xs rounded-lg border border-indigo-200 transition flex items-center"
                    >
                      <Sparkles className="w-3.5 h-3.5 mr-1.5 text-indigo-600" />
                      <span>{t('aiExplain')}</span>
                      {isExp ? <ChevronUp className="w-3.5 h-3.5 ml-1.5" /> : <ChevronDown className="w-3.5 h-3.5 ml-1.5" />}
                    </button>

                    {/* Expanded AI Explanation Card */}
                    {isExp && (
                      <div className="mt-3 p-4 bg-indigo-950 text-white rounded-xl border border-indigo-800 space-y-3 text-xs">
                        <div className="flex items-center justify-between border-b border-indigo-800 pb-2">
                          <div className="flex items-center space-x-1.5 text-amber-300 font-bold">
                            <Bot className="w-4 h-4" />
                            <span>AI Diagnostic Breakdown</span>
                          </div>

                          {aiData?.modeLabel && (
                            <span className={`px-2 py-0.5 rounded text-[10px] font-bold ${
                              aiData.isFallbackMode ? 'bg-amber-300 text-slate-950' : 'bg-emerald-400 text-slate-950'
                            }`}>
                              {aiData.modeLabel}
                            </span>
                          )}
                        </div>

                        {isLoadingThis ? (
                          <div className="p-3 text-slate-300 italic flex items-center">
                            <div className="animate-spin w-3.5 h-3.5 border-2 border-amber-400 border-t-transparent rounded-full mr-2"></div>
                            Analyzing error patterns and generating solution...
                          </div>
                        ) : aiData ? (
                          <div className="space-y-2">
                            <div>
                              <span className="font-bold text-rose-300">Why Incorrect: </span>
                              <span className="text-slate-200">{aiData.whyWrong}</span>
                            </div>
                            <div>
                              <span className="font-bold text-emerald-300">Core Concept: </span>
                              <span className="text-slate-200">{aiData.correctConcept}</span>
                            </div>
                            <div>
                              <span className="font-bold text-amber-300">Step-by-Step Solution: </span>
                              <p className="text-slate-200 whitespace-pre-wrap mt-0.5 font-sans">{aiData.stepByStepSolution}</p>
                            </div>
                            <div>
                              <span className="font-bold text-cyan-300">Practice Example: </span>
                              <span className="text-slate-200">{aiData.similarExample}</span>
                            </div>
                          </div>
                        ) : null}
                      </div>
                    )}
                  </div>
                )}
              </div>
            );
          })}
        </div>
      </div>

      <div className="text-center pt-2">
        <Link
          to="/student/dashboard"
          className="inline-flex items-center px-6 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-bold text-xs rounded-xl shadow transition"
        >
          <span>Return to Dashboard</span>
          <ArrowRight className="w-4 h-4 ml-1" />
        </Link>
      </div>

    </div>
  );
};

export default QuizResultPage;
