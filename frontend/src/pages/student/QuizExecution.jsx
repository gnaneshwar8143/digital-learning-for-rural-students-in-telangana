import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { studentApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import { Clock, CheckCircle2, AlertCircle } from 'lucide-react';

const QuizExecution = () => {
  const { quizId } = useParams();
  const navigate = useNavigate();
  const { lang, t } = useLanguage();

  const [quiz, setQuiz] = useState(null);
  const [questions, setQuestions] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [selectedAnswers, setSelectedAnswers] = useState({});
  const [timeLeft, setTimeLeft] = useState(900); // 15 mins default
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    studentApi.getQuizById(quizId).then((res) => {
      setQuiz(res.data);
      if (res.data.durationMinutes) {
        setTimeLeft(res.data.durationMinutes * 60);
      }
    });

    studentApi.getQuestions(quizId).then((res) => setQuestions(res.data));
  }, [quizId]);

  useEffect(() => {
    if (timeLeft <= 0) {
      handleSubmit();
      return;
    }
    const timer = setInterval(() => setTimeLeft((prev) => prev - 1), 1000);
    return () => clearInterval(timer);
  }, [timeLeft]);

  const handleSelectOption = (questionId, optionId) => {
    setSelectedAnswers((prev) => ({
      ...prev,
      [questionId]: optionId,
    }));
  };

  const handleSubmit = async () => {
    if (submitting) return;
    setSubmitting(true);

    const answersPayload = Object.keys(selectedAnswers).map((qId) => ({
      questionId: parseInt(qId),
      selectedOptionId: selectedAnswers[qId],
    }));

    try {
      const res = await studentApi.submitQuiz({
        quizId: parseInt(quizId),
        attemptType: quiz?.testCategory || 'INITIAL',
        answers: answersPayload,
      });

      // Pass result in router state or navigate directly to result page
      navigate('/student/quiz-result', { state: { result: res.data } });
    } catch (err) {
      console.error(err);
      alert('Error submitting quiz attempt');
      setSubmitting(false);
    }
  };

  if (!quiz || questions.length === 0) {
    return <div className="p-8 text-center text-slate-500 font-semibold">Loading Quiz Questions...</div>;
  }

  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${mins}:${secs < 10 ? '0' : ''}${secs}`;
  };

  const currentQuestion = questions[currentIndex];
  const qText = lang === 'te' ? currentQuestion.questionTextTe : currentQuestion.questionTextEn;

  return (
    <div className="max-w-3xl mx-auto space-y-6">
      
      {/* Quiz Header with Timer */}
      <div className="bg-slate-900 text-white p-5 rounded-2xl shadow-md flex items-center justify-between">
        <div>
          <span className="px-2 py-0.5 text-[10px] font-bold bg-amber-400 text-slate-950 rounded uppercase">
            {quiz.testCategory || 'QUIZ'}
          </span>
          <h2 className="text-xl font-bold mt-1">{lang === 'te' ? quiz.titleTe : quiz.titleEn}</h2>
        </div>

        <div className="flex items-center space-x-2 bg-slate-800 px-3 py-1.5 rounded-xl border border-slate-700 font-mono text-amber-300 font-bold text-sm">
          <Clock className="w-4 h-4 text-amber-400" />
          <span>{formatTime(timeLeft)}</span>
        </div>
      </div>

      {/* Question Card */}
      <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-6">
        
        <div className="flex items-center justify-between text-xs font-semibold text-slate-500">
          <span>{t('question')} {currentIndex + 1} {t('of')} {questions.length}</span>
          <span className="text-emerald-700">{currentQuestion.marks} Marks</span>
        </div>

        <h3 className="text-lg font-bold text-slate-900 leading-snug">{qText}</h3>

        {/* Options */}
        <div className="space-y-3">
          {currentQuestion.options?.map((opt) => {
            const optText = lang === 'te' ? opt.optionTextTe : opt.optionTextEn;
            const isSelected = selectedAnswers[currentQuestion.id] === opt.id;
            return (
              <button
                key={opt.id}
                onClick={() => handleSelectOption(currentQuestion.id, opt.id)}
                className={`w-full text-left p-4 rounded-xl border font-medium text-sm transition flex items-center justify-between ${
                  isSelected
                    ? 'bg-emerald-50 border-emerald-600 text-emerald-950 shadow-sm'
                    : 'bg-white border-slate-200 text-slate-700 hover:bg-slate-50'
                }`}
              >
                <span>{optText}</span>
                {isSelected && <CheckCircle2 className="w-5 h-5 text-emerald-600" />}
              </button>
            );
          })}
        </div>

        {/* Pagination & Submit Bar */}
        <div className="pt-4 border-t border-slate-100 flex items-center justify-between">
          <button
            disabled={currentIndex === 0}
            onClick={() => setCurrentIndex((prev) => prev - 1)}
            className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 disabled:opacity-50 text-xs font-semibold rounded-lg transition"
          >
            Previous
          </button>

          {currentIndex < questions.length - 1 ? (
            <button
              onClick={() => setCurrentIndex((prev) => prev + 1)}
              className="px-4 py-2 bg-emerald-800 hover:bg-emerald-700 text-white text-xs font-semibold rounded-lg transition"
            >
              Next Question
            </button>
          ) : (
            <button
              onClick={handleSubmit}
              disabled={submitting}
              className="px-5 py-2.5 bg-amber-400 hover:bg-amber-300 text-emerald-950 font-extrabold text-xs rounded-xl shadow transition"
            >
              {submitting ? 'Submitting...' : t('submitQuiz')}
            </button>
          )}
        </div>

      </div>
    </div>
  );
};

export default QuizExecution;
