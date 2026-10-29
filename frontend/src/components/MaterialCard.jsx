import React from 'react';
import { FileText, Video, Download, Wifi, BookOpen } from 'lucide-react';
import { useLanguage } from '../context/LanguageContext';

const MaterialCard = ({ material, onSelect }) => {
  const { lang, t } = useLanguage();

  const title = lang === 'te' ? material.titleTe : material.titleEn;
  const description = lang === 'te' ? material.descriptionTe : material.descriptionEn;

  const handleDownload = (e) => {
    e.stopPropagation();
    const content = lang === 'te' ? material.contentTextTe : material.contentTextEn;
    const blob = new Blob([content || title], { type: 'text/plain;charset=utf-8' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `${title.replace(/\s+/g, '_')}_TelanganaBoard.txt`;
    link.click();
  };

  return (
    <div className="bg-white rounded-xl border border-slate-200 shadow-sm p-4 hover:shadow-md transition flex flex-col justify-between">
      <div>
        <div className="flex items-center justify-between mb-2">
          <span className="inline-flex items-center px-2.5 py-1 rounded-md text-xs font-semibold bg-emerald-50 text-emerald-700 border border-emerald-200">
            {material.materialType === 'VIDEO' ? (
              <>
                <Video className="w-3.5 h-3.5 mr-1" /> Video Lesson
              </>
            ) : material.materialType === 'PRACTICE' ? (
              <>
                <BookOpen className="w-3.5 h-3.5 mr-1" /> Practice Set
              </>
            ) : (
              <>
                <FileText className="w-3.5 h-3.5 mr-1" /> Study Notes
              </>
            )}
          </span>

          {material.lowBandwidthOptimized && (
            <span className="inline-flex items-center px-2 py-0.5 text-xs text-slate-600 bg-slate-100 rounded border border-slate-200" title="Optimized for 2G/3G rural networks">
              <Wifi className="w-3 h-3 mr-1 text-emerald-600" />
              {material.fileSizeKb ? `${material.fileSizeKb} KB` : 'Low Data'}
            </span>
          )}
        </div>

        <h4 className="font-bold text-slate-900 text-base mb-1">{title}</h4>
        <p className="text-xs text-slate-600 line-clamp-2 mb-4">{description}</p>
      </div>

      <div className="flex items-center space-x-2 pt-2 border-t border-slate-100">
        <button
          onClick={() => onSelect(material)}
          className="flex-1 py-2 px-3 bg-emerald-700 hover:bg-emerald-800 text-white rounded-lg text-xs font-semibold transition text-center shadow-sm"
        >
          {material.materialType === 'VIDEO' ? t('viewVideo') : t('studyMaterial')}
        </button>

        {material.downloadable && (
          <button
            onClick={handleDownload}
            className="py-2 px-3 bg-slate-100 hover:bg-slate-200 text-slate-700 rounded-lg text-xs font-semibold transition border border-slate-300 flex items-center"
            title={t('downloadNotes')}
          >
            <Download className="w-3.5 h-3.5 mr-1" />
            Download
          </button>
        )}
      </div>
    </div>
  );
};

export default MaterialCard;
