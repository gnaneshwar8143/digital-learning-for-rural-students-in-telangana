import React, { useEffect, useState } from 'react';
import { adminApi } from '../../services/api';
import { useLanguage } from '../../context/LanguageContext';
import StatCard from '../../components/StatCard';
import { ShieldCheck, Users, BookOpen, FileText, CheckCircle2, TrendingUp } from 'lucide-react';

const AdminDashboard = () => {
  const [data, setData] = useState(null);
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const { t } = useLanguage();

  useEffect(() => {
    Promise.all([adminApi.getDashboard(), adminApi.getUsers()])
      .then(([dashRes, usersRes]) => {
        setData(dashRes.data);
        setUsers(usersRes.data);
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="p-8 text-center text-slate-500">Loading System Admin Panel...</div>;

  return (
    <div className="space-y-6">
      
      <div className="bg-gradient-to-r from-purple-950 via-slate-900 to-purple-900 rounded-2xl p-6 text-white shadow-md">
        <span className="px-2.5 py-1 bg-purple-800 text-amber-300 text-xs font-bold rounded uppercase">
          System Administration
        </span>
        <h1 className="text-2xl sm:text-3xl font-extrabold mt-1">{t('adminTitle')}</h1>
        <p className="text-xs text-purple-200 mt-1">
          System-wide user control, curriculum management & research evaluation metrics.
        </p>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <StatCard title="Total Registered Students" value={data?.totalStudents || 0} icon={Users} color="emerald" />
        <StatCard title="Registered Teachers" value={data?.totalTeachers || 0} icon={ShieldCheck} color="indigo" />
        <StatCard title="Total Learning Resources" value={data?.totalMaterials || 0} icon={FileText} color="amber" />
        <StatCard title="System Average Gain" value={`+${data?.systemAverageImprovement || 0}%`} icon={TrendingUp} color="blue" />
      </div>

      <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-4">
        <h2 className="text-lg font-bold text-slate-900">User Accounts Control List</h2>

        <div className="overflow-x-auto">
          <table className="w-full text-left text-xs">
            <thead className="bg-slate-50 text-slate-500 uppercase tracking-wider font-semibold border-b border-slate-200">
              <tr>
                <th className="p-3">ID</th>
                <th className="p-3">Full Name</th>
                <th className="p-3">Email Address</th>
                <th className="p-3">Role</th>
                <th className="p-3">Class Level</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100 font-medium">
              {users.map((u) => (
                <tr key={u.id} className="hover:bg-slate-50">
                  <td className="p-3 font-mono text-slate-400">#{u.id}</td>
                  <td className="p-3 font-bold text-slate-900">{u.name}</td>
                  <td className="p-3 font-mono text-slate-600">{u.email}</td>
                  <td className="p-3">
                    <span className={`px-2 py-0.5 rounded text-[10px] font-bold ${
                      u.role === 'ROLE_ADMIN' ? 'bg-purple-100 text-purple-800' :
                      u.role === 'ROLE_TEACHER' ? 'bg-emerald-100 text-emerald-800' : 'bg-amber-100 text-amber-800'
                    }`}>
                      {u.role.replace('ROLE_', '')}
                    </span>
                  </td>
                  <td className="p-3">Class {u.classLevel || 10}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

    </div>
  );
};

export default AdminDashboard;
