import React from 'react';

const StatCard = ({ title, value, icon: Icon, color = 'emerald', subtext }) => {
  const colorMap = {
    emerald: 'bg-emerald-50 text-emerald-700 border-emerald-200',
    amber: 'bg-amber-50 text-amber-700 border-amber-200',
    rose: 'bg-rose-50 text-rose-700 border-rose-200',
    indigo: 'bg-indigo-50 text-indigo-700 border-indigo-200',
    blue: 'bg-blue-50 text-blue-700 border-blue-200',
  };

  const iconBgMap = {
    emerald: 'bg-emerald-600 text-white',
    amber: 'bg-amber-500 text-white',
    rose: 'bg-rose-600 text-white',
    indigo: 'bg-indigo-600 text-white',
    blue: 'bg-blue-600 text-white',
  };

  return (
    <div className={`p-5 rounded-xl border shadow-sm ${colorMap[color] || colorMap.emerald} transition hover:shadow-md`}>
      <div className="flex items-center justify-between">
        <div>
          <p className="text-xs font-semibold uppercase tracking-wider opacity-80">{title}</p>
          <h3 className="text-2xl font-bold mt-1 text-slate-900">{value}</h3>
          {subtext && <p className="text-xs mt-1 opacity-75 font-medium">{subtext}</p>}
        </div>
        {Icon && (
          <div className={`p-3 rounded-lg ${iconBgMap[color] || iconBgMap.emerald} shadow`}>
            <Icon className="w-6 h-6" />
          </div>
        )}
      </div>
    </div>
  );
};

export default StatCard;
