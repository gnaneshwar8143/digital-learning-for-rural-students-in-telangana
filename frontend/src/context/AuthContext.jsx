import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('telangana_user');
    return saved ? JSON.parse(saved) : null;
  });

  const [token, setToken] = useState(() => localStorage.getItem('telangana_token') || null);

  const loginUser = (authData) => {
    setUser({
      id: authData.id,
      name: authData.name,
      email: authData.email,
      role: authData.role,
      classLevel: authData.classLevel || 10,
      preferredLanguage: authData.preferredLanguage || 'en',
    });
    setToken(authData.token);
    localStorage.setItem('telangana_user', JSON.stringify(authData));
    localStorage.setItem('telangana_token', authData.token);
  };

  const logoutUser = () => {
    setUser(null);
    setToken(null);
    localStorage.removeItem('telangana_user');
    localStorage.removeItem('telangana_token');
  };

  return (
    <AuthContext.Provider value={{ user, token, loginUser, logoutUser }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
