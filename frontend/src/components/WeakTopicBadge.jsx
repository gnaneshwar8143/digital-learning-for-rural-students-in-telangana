import React from 'react';
import { AlertCircle, AlertTriangle, CheckCircle } from 'lucide-react';
import { useLanguage } from '../context/LanguageContext';

const WeakTopicBadge = ({ status }) => {
  const { t } = useLanguage();

  if (status === 'WEAK') {
    return (
      <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-semibold bg-rose-100 text-rose-800 border border-rose-300">
        <AlertCircle className="w-3.5 h-3.5 mr-1" />
        {t('weakStatus')}
      </span>
    );
  } else if (status === 'NEEDS_IMPROVEMENT') {
    return (
      <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-semibold bg-amber-100 text-amber-800 border border-amber-300">
        <AlertTriangle className="w-3.5 h-3.5 mr-1" />
        {t('needsImpStatus')}
      </span>
    );
  } else {
    return (
      <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-semibold bg-emerald-100 text-emerald-800 border border-emerald-300">
        <CheckCircle className="w-3.5 h-3.5 mr-1" />
        {t('goodStatus')}
      </span>
    );
  }
};

export default WeakTopicBadge;
