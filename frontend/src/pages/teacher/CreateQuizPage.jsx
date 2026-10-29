import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { teacherApi, aiApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import { PlusCircle, CheckCircle2, Sparkles, Edit2, Trash2, Check, Bot } from 'lucide-react';

const CreateQuizPage = () => {
  const { lang, t } = useLanguage();
  const navigate = useNavigate();

  // Manual quiz form state
  const [titleEn, setTitleEn] = useState('');
  const [titleTe, setTitleTe] = useState('');
  const [classLevel, setClassLevel] = useState(10);
  const [topicId, setTopicId] = useState(1);
  const [durationMinutes, setDurationMinutes] = useState(15);
  const [testCategory, setTestCategory] = useState('PRE_TEST');
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  // AI Generator state
  const [aiSubject, setAiSubject] = useState('Mathematics');
  const [aiChapter, setAiChapter] = useState('Fractions');
  const [aiTopic, setAiTopic] = useState('Addition of Unlike Fractions');
  const [aiDifficulty, setAiDifficulty] = useState('MEDIUM');
  const [aiCount, setAiCount] = useState(2);
  const [loadingAi, setLoadingAi] = useState(false);
  const [draftQuestions, setDraftQuestions] = useState([]);
  const [aiModeLabel, setAiModeLabel] = useState('');

  const handleGenerateAiQuiz = async () => {
    setLoadingAi(true);
    try {
      const res = await aiApi.generateQuiz({
        classLevel: parseInt(classLevel),
        subjectName: aiSubject,
        chapterName: aiChapter,
        topicName: aiTopic,
        difficulty: aiDifficulty,
        numberOfQuestions: parseInt(aiCount),
        preferredLanguage: lang,
      });

      setDraftQuestions(res.data.questions || []);
      setAiModeLabel(res.data.modeLabel || 'AI Generated Drafts');
    } catch (err) {
      alert('Failed to generate AI quiz drafts.');
    } finally {
      setLoadingAi(false);
    }
  };

  const toggleApproveQuestion = (id) => {
    setDraftQuestions((prev) =>
      prev.map((q) => (q.id === id ? { ...q, approved: !q.approved } : q))
    );
  };

  const deleteQuestion = (id) => {
    setDraftQuestions((prev) => prev.filter((q) => q.id !== id));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await teacherApi.createQuiz({
        titleEn,
        titleTe,
        classLevel: parseInt(classLevel),
        topicId: parseInt(topicId),
        durationMinutes: parseInt(durationMinutes),
        totalMarks: 5.0,
        passingMarks: 3.0,
        isEvaluationTest: true,
        testCategory,
      });
      setSuccess(true);
      setTimeout(() => navigate('/teacher/dashboard'), 1500);
    } catch (err) {
      alert('Failed to create quiz.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-4xl mx-auto space-y-6">
      
      {/* Page Header */}
      <div className="border-b border-slate-200 pb-4 flex justify-between items-center">
        <div>
          <h1 className="text-2xl font-extrabold text-slate-900">{t('createQuiz')}</h1>
          <p className="text-xs text-slate-500">Create new Telangana State Board assessment or use AI Quiz Draft Generator.</p>
        </div>
      </div>

      {success && (
        <div className="p-4 bg-emerald-50 border border-emerald-300 text-emerald-800 rounded-xl text-xs font-bold flex items-center">
          <CheckCircle2 className="w-5 h-5 mr-2 text-emerald-600" />
          Quiz created successfully! Redirecting to teacher dashboard...
        </div>
      )}

      {/* AI Quiz Generator Section */}
      <div className="bg-gradient-to-r from-slate-900 via-indigo-950 to-slate-950 p-6 rounded-2xl text-white shadow-md space-y-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-2">
            <div className="p-2 bg-amber-400 text-slate-950 rounded-xl">
              <Sparkles className="w-5 h-5" />
            </div>
            <div>
              <h2 className="text-lg font-bold">{t('aiQuizGenerator')}</h2>
              <p className="text-xs text-slate-300">Generate syllabus-aligned draft multiple-choice questions automatically</p>
            </div>
          </div>

          {aiModeLabel && (
            <span className="px-2.5 py-1 bg-amber-400 text-slate-950 text-xs font-extrabold rounded-full">
              {aiModeLabel}
            </span>
          )}
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-3 gap-3 text-slate-900">
          <div>
            <label className="block text-[10px] font-bold uppercase text-slate-300 mb-1">Subject</label>
            <input
              type="text"
              value={aiSubject}
              onChange={(e) => setAiSubject(e.target.value)}
              className="w-full px-3 py-1.5 bg-white rounded-lg text-xs font-semibold outline-none"
            />
          </div>

          <div>
            <label className="block text-[10px] font-bold uppercase text-slate-300 mb-1">Chapter</label>
            <input
              type="text"
              value={aiChapter}
              onChange={(e) => setAiChapter(e.target.value)}
              className="w-full px-3 py-1.5 bg-white rounded-lg text-xs font-semibold outline-none"
            />
          </div>

          <div>
            <label className="block text-[10px] font-bold uppercase text-slate-300 mb-1">Topic</label>
            <input
              type="text"
              value={aiTopic}
              onChange={(e) => setAiTopic(e.target.value)}
              className="w-full px-3 py-1.5 bg-white rounded-lg text-xs font-semibold outline-none"
            />
          </div>
        </div>

        <div className="flex flex-col sm:flex-row items-center justify-between gap-3 pt-2">
          <div className="flex items-center space-x-3 text-xs">
            <label className="text-slate-300 font-semibold">Difficulty:</label>
            <select
              value={aiDifficulty}
              onChange={(e) => setAiDifficulty(e.target.value)}
              className="px-2 py-1 bg-white text-slate-900 rounded font-semibold"
            >
              <option value="EASY">Easy</option>
              <option value="MEDIUM">Medium</option>
              <option value="HARD">Hard</option>
            </select>

            <label className="text-slate-300 font-semibold">Count:</label>
            <input
              type="number"
              min={1}
              max={5}
              value={aiCount}
              onChange={(e) => setAiCount(e.target.value)}
              className="w-14 px-2 py-1 bg-white text-slate-900 rounded font-semibold text-center"
            />
          </div>

          <button
            type="button"
            onClick={handleGenerateAiQuiz}
            disabled={loadingAi}
            className="px-5 py-2 bg-amber-400 hover:bg-amber-300 text-slate-950 font-extrabold text-xs rounded-xl shadow transition flex items-center"
          >
            <Sparkles className="w-4 h-4 mr-1.5" />
            <span>{loadingAi ? 'Generating Drafts...' : 'Generate Questions with AI ✨'}</span>
          </button>
        </div>

        {/* AI Draft Questions List */}
        {draftQuestions.length > 0 && (
          <div className="space-y-3 pt-3 border-t border-slate-800">
            <p className="text-xs font-bold text-amber-300 uppercase">Review & Approve AI Draft Questions:</p>
            {draftQuestions.map((q, idx) => (
              <div key={q.id || idx} className="p-4 bg-slate-800/90 rounded-xl border border-slate-700 space-y-2">
                <div className="flex items-center justify-between">
                  <span className="text-xs font-bold text-slate-300">Draft Question {idx + 1}</span>
                  <div className="flex items-center space-x-2">
                    <button
                      type="button"
                      onClick={() => toggleApproveQuestion(q.id)}
                      className={`px-3 py-1 text-xs font-bold rounded-lg transition flex items-center ${
                        q.approved
                          ? 'bg-emerald-600 text-white'
                          : 'bg-slate-700 hover:bg-slate-600 text-slate-300'
                      }`}
                    >
                      <Check className="w-3.5 h-3.5 mr-1" />
                      {q.approved ? 'Approved' : 'Approve'}
                    </button>

                    <button
                      type="button"
                      onClick={() => deleteQuestion(q.id)}
                      className="p-1 bg-rose-900/50 hover:bg-rose-900 text-rose-300 rounded-lg"
                    >
                      <Trash2 className="w-3.5 h-3.5" />
                    </button>
                  </div>
                </div>

                <p className="text-sm font-bold text-white">{q.questionTextEn}</p>
                <p className="text-xs text-amber-200">{q.questionTextTe}</p>
              </div>
            ))}
          </div>
        )}
      </div>

      {/* Manual Quiz Details Form */}
      <form onSubmit={handleSubmit} className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-4">
        <h2 className="text-lg font-bold text-slate-900 border-b pb-2">Publish Quiz to Class</h2>

        <div>
          <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Quiz Title (English)</label>
          <input
            type="text"
            required
            value={titleEn}
            onChange={(e) => setTitleEn(e.target.value)}
            placeholder="e.g. Class 10 Fractions Remedial Assessment"
            className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600"
          />
        </div>

        <div>
          <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Quiz Title (Telugu / తెలుగు)</label>
          <input
            type="text"
            required
            value={titleTe}
            onChange={(e) => setTitleTe(e.target.value)}
            placeholder="ఉదా: 10వ తరగతి భిన్నాల పునశ్చరణ పరీక్ష"
            className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600"
          />
        </div>

        <div className="grid grid-cols-2 gap-4">
          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Class Level</label>
            <select
              value={classLevel}
              onChange={(e) => setClassLevel(e.target.value)}
              className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm bg-white"
            >
              <option value={6}>Class 6</option>
              <option value={7}>Class 7</option>
              <option value={8}>Class 8</option>
              <option value={9}>Class 9</option>
              <option value={10}>Class 10</option>
            </select>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Assessment Category</label>
            <select
              value={testCategory}
              onChange={(e) => setTestCategory(e.target.value)}
              className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm bg-white"
            >
              <option value="PRE_TEST">Baseline Pre-Test</option>
              <option value="POST_TEST">Retest Post-Test</option>
              <option value="CHAPTER_QUIZ">Regular Chapter Quiz</option>
            </select>
          </div>
        </div>

        <div>
          <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Duration (Minutes)</label>
          <input
            type="number"
            value={durationMinutes}
            onChange={(e) => setDurationMinutes(e.target.value)}
            className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600"
          />
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full py-3 bg-emerald-800 hover:bg-emerald-700 text-white font-bold text-xs rounded-xl shadow transition"
        >
          {loading ? 'Publishing Quiz...' : 'Publish Approved Quiz to Student Bank'}
        </button>
      </form>

    </div>
  );
};

export default CreateQuizPage;
