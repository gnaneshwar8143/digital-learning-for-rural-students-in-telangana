import React, { createContext, useContext, useState } from 'react';
import { en } from '../translations/en';
import { te } from '../translations/te';

const LanguageContext = createContext();

export const LanguageProvider = ({ children }) => {
  const [lang, setLang] = useState(() => localStorage.getItem('telangana_lang') || 'en');

  const switchLanguage = (newLang) => {
    setLang(newLang);
    localStorage.setItem('telangana_lang', newLang);
  };

  const t = (key) => {
    const dict = lang === 'te' ? te : en;
    return dict[key] || en[key] || key;
  };

  return (
    <LanguageContext.Provider value={{ lang, switchLanguage, t }}>
      {children}
    </LanguageContext.Provider>
  );
};

export const useLanguage = () => useContext(LanguageContext);
