import React, { useState } from 'react';
import { teacherApi } from '../services/api';
import { Upload, X, CheckCircle2 } from 'lucide-react';
import { useLanguage } from '../context/LanguageContext';

const MaterialUploadModal = ({ isOpen, onClose, onSuccess }) => {
  const { t } = useLanguage();
  const [topicId, setTopicId] = useState(1);
  const [classLevel, setClassLevel] = useState(10);
  const [titleEn, setTitleEn] = useState('');
  const [titleTe, setTitleTe] = useState('');
  const [descriptionEn, setDescriptionEn] = useState('');
  const [descriptionTe, setDescriptionTe] = useState('');
  const [materialType, setMaterialType] = useState('NOTES');
  const [contentTextEn, setContentTextEn] = useState('');
  const [contentTextTe, setContentTextTe] = useState('');
  const [fileUrl, setFileUrl] = useState('');
  const [loading, setLoading] = useState(false);
  const [successMsg, setSuccessMsg] = useState(false);

  if (!isOpen) return null;

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await teacherApi.uploadMaterial({
        topicId: parseInt(topicId),
        classLevel: parseInt(classLevel),
        titleEn,
        titleTe,
        descriptionEn,
        descriptionTe,
        materialType,
        contentTextEn,
        contentTextTe,
        fileUrl,
        fileSizeKb: 150,
        downloadable: true,
        lowBandwidthOptimized: true,
      });
      setSuccessMsg(true);
      setTimeout(() => {
        setSuccessMsg(false);
        onSuccess && onSuccess();
        onClose();
      }, 1200);
    } catch (err) {
      alert('Failed to upload material');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-slate-900/60 backdrop-blur-sm z-50 flex items-center justify-center p-4 overflow-y-auto">
      <div className="bg-white rounded-2xl border border-slate-200 shadow-2xl max-w-lg w-full p-6 relative my-8">
        
        <button
          onClick={onClose}
          className="absolute top-4 right-4 p-1 text-slate-400 hover:text-slate-700 rounded-lg"
        >
          <X className="w-5 h-5" />
        </button>

        <div className="flex items-center space-x-2 text-emerald-800 font-bold text-lg mb-4">
          <Upload className="w-5 h-5 text-emerald-600" />
          <span>Upload Telangana Learning Material</span>
        </div>

        {successMsg && (
          <div className="mb-4 p-3 bg-emerald-50 border border-emerald-300 text-emerald-800 rounded-xl text-xs font-bold flex items-center">
            <CheckCircle2 className="w-4 h-4 mr-2 text-emerald-600" />
            Material uploaded successfully!
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-3 text-left">
          <div className="grid grid-cols-2 gap-3">
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
              <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Material Type</label>
              <select
                value={materialType}
                onChange={(e) => setMaterialType(e.target.value)}
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm bg-white"
              >
                <option value="NOTES">Study Notes</option>
                <option value="VIDEO">Low-Bandwidth Video</option>
                <option value="PRACTICE">Practice Set</option>
              </select>
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Title (English)</label>
            <input
              type="text"
              required
              value={titleEn}
              onChange={(e) => setTitleEn(e.target.value)}
              placeholder="Fractions Formula Sheet"
              className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600"
            />
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Title (Telugu / తెలుగు)</label>
            <input
              type="text"
              required
              value={titleTe}
              onChange={(e) => setTitleTe(e.target.value)}
              placeholder="భిన్నాల సూత్రాల నోట్స్"
              className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600"
            />
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Content / Summary (English)</label>
            <textarea
              rows={2}
              value={contentTextEn}
              onChange={(e) => setContentTextEn(e.target.value)}
              placeholder="Enter study note content or formula steps..."
              className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600"
            />
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Content / Summary (Telugu)</label>
            <textarea
              rows={2}
              value={contentTextTe}
              onChange={(e) => setContentTextTe(e.target.value)}
              placeholder="తెలుగు వివరణ లేదా గమనికలు పంపండి..."
              className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600"
            />
          </div>

          {materialType === 'VIDEO' && (
            <div>
              <label className="block text-xs font-semibold text-slate-700 uppercase mb-1">Video Stream URL</label>
              <input
                type="text"
                value={fileUrl}
                onChange={(e) => setFileUrl(e.target.value)}
                placeholder="https://www.youtube.com/embed/..."
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-sm outline-none focus:ring-2 focus:ring-emerald-600 font-mono"
              />
            </div>
          )}

          <div className="pt-2">
            <button
              type="submit"
              disabled={loading}
              className="w-full py-2.5 bg-emerald-800 hover:bg-emerald-700 text-white font-bold text-xs rounded-xl shadow transition"
            >
              {loading ? 'Uploading...' : 'Publish Study Material'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MaterialUploadModal;
