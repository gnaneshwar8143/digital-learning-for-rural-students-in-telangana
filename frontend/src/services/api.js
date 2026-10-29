import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('telangana_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export const authApi = {
  login: (credentials) => api.post('/auth/login', credentials),
  register: (data) => api.post('/auth/register', data),
};

export const curriculumApi = {
  getClasses: () => api.get('/curriculum/classes'),
  getSubjects: (classId) => api.get(`/curriculum/classes/${classId}/subjects`),
  getChapters: (subjectId) => api.get(`/curriculum/subjects/${subjectId}/chapters`),
  getTopics: (chapterId) => api.get(`/curriculum/chapters/${chapterId}/topics`),
  getTopicById: (topicId) => api.get(`/curriculum/topics/${topicId}`),
  getMaterials: (topicId) => api.get(`/curriculum/topics/${topicId}/materials`),
};

export const studentApi = {
  getDashboard: () => api.get('/student/dashboard'),
  getWeakTopics: () => api.get('/student/weak-topics'),
  getRecommendations: () => api.get('/student/recommendations'),
  getEvaluations: () => api.get('/student/evaluation'),
  getQuizzesByTopic: (topicId) => api.get(`/student/topics/${topicId}/quizzes`),
  getQuizById: (quizId) => api.get(`/student/quizzes/${quizId}`),
  getQuestions: (quizId) => api.get(`/student/quizzes/${quizId}/questions`),
  submitQuiz: (submission) => api.post('/student/quizzes/submit', submission),
};

export const aiApi = {
  ask: (data) => api.post('/ai/ask', data),
  explainAnswer: (data) => api.post('/ai/explain-answer', data),
  recommend: (data) => api.post('/ai/recommend', data),
  generateQuiz: (data) => api.post('/ai/generate-quiz', data),
  getStudyPlan: (data) => api.post('/ai/study-plan', data || {}),
};

export const teacherApi = {
  getDashboard: () => api.get('/teacher/dashboard'),
  createQuiz: (quizData) => api.post('/teacher/quizzes', quizData),
  uploadMaterial: (materialData) => api.post('/teacher/materials', materialData),
};

export const adminApi = {
  getDashboard: () => api.get('/admin/dashboard'),
  getUsers: () => api.get('/admin/users'),
};

export default api;
