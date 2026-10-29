import React, { useState } from 'react';
import { aiApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import { useAuth } from '../../context/AuthContext';
import { Sparkles, Send, Bot, User, BookOpen, AlertCircle, CheckCircle2 } from 'lucide-react';

const AITutorPage = () => {
  const { user } = useAuth();
  const { lang, t } = useLanguage();

  const [question, setQuestion] = useState('');
  const [subjectName, setSubjectName] = useState('Mathematics');
  const [topicName, setTopicName] = useState('Fractions & Operations');
  const [loading, setLoading] = useState(false);
  const [messages, setMessages] = useState([
    {
      sender: 'ai',
      text: lang === 'te'
        ? "నమస్తే! నేను మీ AI అసిస్టెంట్‌ని. తెలంగాణ 10వ తరగతి పాఠ్యాంశాలపై మీకు ఏవైనా సందేహాలు ఉంటే అడగండి."
        : "Hello! I am your AI Learning Assistant. Ask me any educational questions on your Telangana Class 10 syllabus.",
      isFallback: true,
      modeLabel: 'AI Demo/Fallback Mode',
    },
  ]);

  const handleAsk = async (e) => {
    e.preventDefault();
    if (!question.trim() || loading) return;

    const userQ = question;
    setQuestion('');
    setMessages((prev) => [...prev, { sender: 'user', text: userQ }]);
    setLoading(true);

    try {
      const res = await aiApi.ask({
        question: userQ,
        classLevel: user?.classLevel || 10,
        subjectName,
        topicName,
        preferredLanguage: lang,
        previousScore: 35.0,
      });

      setMessages((prev) => [
        ...prev,
        {
          sender: 'ai',
          text: res.data.answer,
          isFallback: res.data.isFallbackMode,
          modeLabel: res.data.modeLabel || 'AI Demo/Fallback Mode',
        },
      ]);
    } catch (err) {
      setMessages((prev) => [
        ...prev,
        {
          sender: 'ai',
          text: "AI Service temporarily unavailable. Please check your network connection and try again.",
          isError: true,
        },
      ]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-4xl mx-auto space-y-6">
      
      {/* Header */}
      <div className="bg-gradient-to-r from-emerald-950 via-slate-900 to-emerald-900 rounded-2xl p-6 text-white shadow-md flex items-center justify-between">
        <div>
          <div className="inline-flex items-center space-x-2 px-3 py-1 bg-emerald-800/80 text-amber-300 text-xs font-semibold rounded-full mb-2">
            <Sparkles className="w-4 h-4 text-amber-400" />
            <span>Telangana EdTech AI Layer</span>
          </div>
          <h1 className="text-2xl sm:text-3xl font-extrabold">{t('aiTutor')}</h1>
          <p className="text-xs text-emerald-200 mt-1">
            Class {user?.classLevel || 10} Syllabus Intelligent Academic Assistant • English & Telugu Support
          </p>
        </div>
      </div>

      {/* Subject / Topic Context Selector */}
      <div className="bg-white p-4 rounded-xl border border-slate-200 shadow-sm grid grid-cols-1 sm:grid-cols-2 gap-4">
        <div>
          <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Subject Context</label>
          <select
            value={subjectName}
            onChange={(e) => setSubjectName(e.target.value)}
            className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs bg-white focus:ring-2 focus:ring-emerald-600 outline-none"
          >
            <option value="Mathematics">Mathematics (గణితం)</option>
            <option value="Science">Science (విజ్ఞాన శాస్త్రం)</option>
            <option value="Social Studies">Social Studies (సామాజిక శాస్త్రం)</option>
            <option value="English">English</option>
            <option value="Telugu">Telugu (తెలుగు)</option>
          </select>
        </div>

        <div>
          <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Current Topic</label>
          <input
            type="text"
            value={topicName}
            onChange={(e) => setTopicName(e.target.value)}
            placeholder="e.g. Fractions & Operations"
            className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs outline-none focus:ring-2 focus:ring-emerald-600"
          />
        </div>
      </div>

      {/* Chat Messages Log */}
      <div className="bg-white rounded-2xl border border-slate-200 shadow-sm p-6 space-y-4 min-h-[350px] max-h-[500px] overflow-y-auto">
        {messages.map((msg, idx) => (
          <div
            key={idx}
            className={`flex items-start space-x-3 ${msg.sender === 'user' ? 'flex-row-reverse space-x-reverse' : ''}`}
          >
            <div className={`p-2.5 rounded-xl text-white shadow ${
              msg.sender === 'user' ? 'bg-emerald-800' : 'bg-slate-900'
            }`}>
              {msg.sender === 'user' ? <User className="w-5 h-5" /> : <Bot className="w-5 h-5 text-amber-400" />}
            </div>

            <div className={`max-w-[80%] p-4 rounded-2xl border ${
              msg.sender === 'user'
                ? 'bg-emerald-50 border-emerald-200 text-slate-900'
                : msg.isError
                ? 'bg-rose-50 border-rose-200 text-rose-800'
                : 'bg-slate-50 border-slate-200 text-slate-900'
            }`}>
              {msg.sender === 'ai' && (
                <div className="flex items-center justify-between mb-2">
                  <span className="text-[10px] font-bold uppercase tracking-wider text-emerald-800 flex items-center">
                    <Sparkles className="w-3 h-3 mr-1 text-amber-500" /> AI Tutor Explanation
                  </span>

                  {/* Mandated AI Status Label */}
                  {msg.modeLabel && (
                    <span className={`px-2 py-0.5 rounded text-[10px] font-semibold ${
                      msg.isFallback ? 'bg-amber-100 text-amber-900 border border-amber-300' : 'bg-emerald-100 text-emerald-900'
                    }`}>
                      {msg.modeLabel}
                    </span>
                  )}
                </div>
              )}

              <div className="text-xs leading-relaxed whitespace-pre-wrap font-sans">
                {msg.text}
              </div>
            </div>
          </div>
        ))}

        {loading && (
          <div className="flex items-center space-x-2 text-slate-500 text-xs font-semibold p-4 bg-slate-50 rounded-xl">
            <div className="animate-spin w-4 h-4 border-2 border-emerald-600 border-t-transparent rounded-full"></div>
            <span>AI Tutor is thinking and generating step-by-step explanation...</span>
          </div>
        )}
      </div>

      {/* Ask Input Form */}
      <form onSubmit={handleAsk} className="flex items-center space-x-2">
        <input
          type="text"
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
          placeholder={lang === 'te' ? 'భిన్నాలపై మీ సందేహాన్ని అడగండి (ఉదా: భిన్నాల కూడికను వివరించండి)...' : 'Ask any educational question (e.g., Explain fractions in simple Telugu)...'}
          className="flex-1 px-4 py-3 bg-white border border-slate-300 rounded-xl text-sm outline-none focus:ring-2 focus:ring-emerald-600 shadow-sm"
        />
        <button
          type="submit"
          disabled={loading || !question.trim()}
          className="px-5 py-3 bg-emerald-800 hover:bg-emerald-700 disabled:opacity-50 text-white font-bold text-xs rounded-xl shadow transition flex items-center"
        >
          <span>Ask AI ✨</span>
          <Send className="w-4 h-4 ml-2" />
        </button>
      </form>

    </div>
  );
};

export default AITutorPage;
