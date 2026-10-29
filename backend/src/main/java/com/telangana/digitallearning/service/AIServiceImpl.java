package com.telangana.digitallearning.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telangana.digitallearning.dto.*;
import com.telangana.digitallearning.entity.AiInteraction;
import com.telangana.digitallearning.entity.User;
import com.telangana.digitallearning.repository.AiInteractionRepository;
import com.telangana.digitallearning.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AIServiceImpl implements AIService {

    private static final Logger log = LoggerFactory.getLogger(AIServiceImpl.class);

    @Value("${app.ai.api-key:}")
    private String apiKey;

    @Value("${app.ai.model:gemini-1.5-flash}")
    private String modelName;

    private final UserRepository userRepository;
    private final AiInteractionRepository aiInteractionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AIServiceImpl(UserRepository userRepository, AiInteractionRepository aiInteractionRepository) {
        this.userRepository = userRepository;
        this.aiInteractionRepository = aiInteractionRepository;
    }

    private boolean isApiKeyConfigured() {
        return StringUtils.hasText(apiKey);
    }

    @Override
    public AiAskResponse answerStudentQuestion(AiAskRequest request) {
        String lang = "te".equalsIgnoreCase(request.getPreferredLanguage()) ? "te" : "en";

        if (isApiKeyConfigured()) {
            try {
                String prompt = buildTutorPrompt(request);
                String externalResponse = callLlmApi(prompt);
                if (StringUtils.hasText(externalResponse)) {
                    saveInteraction(null, "ASK", request.getQuestion(), externalResponse, lang, false);
                    return AiAskResponse.builder()
                            .answer(externalResponse)
                            .language(lang)
                            .suggestedTopic(request.getTopicName())
                            .isFallbackMode(false)
                            .modeLabel("Live AI Response")
                            .build();
                }
            } catch (Exception ex) {
                log.warn("External AI call failed, switching to rule-guided fallback: {}", ex.getMessage());
            }
        }

        // Rule-Guided Fallback Engine (labeled as "AI Demo/Fallback Mode")
        String fallbackAnswer = generateTutorFallback(request, lang);
        saveInteraction(null, "ASK", request.getQuestion(), fallbackAnswer, lang, true);

        return AiAskResponse.builder()
                .answer(fallbackAnswer)
                .language(lang)
                .suggestedTopic(request.getTopicName() != null ? request.getTopicName() : "General Syllabus")
                .isFallbackMode(true)
                .modeLabel("AI Demo/Fallback Mode")
                .build();
    }

    @Override
    public AiExplainAnswerResponse explainWrongAnswer(AiExplainAnswerRequest request) {
        String lang = "te".equalsIgnoreCase(request.getPreferredLanguage()) ? "te" : "en";

        if (isApiKeyConfigured()) {
            try {
                String prompt = buildExplainPrompt(request);
                String rawResponse = callLlmApi(prompt);
                if (StringUtils.hasText(rawResponse)) {
                    try {
                        AiExplainAnswerResponse parsed = objectMapper.readValue(rawResponse, AiExplainAnswerResponse.class);
                        parsed.setIsFallbackMode(false);
                        parsed.setModeLabel("Live AI Response");
                        return parsed;
                    } catch (Exception parseEx) {
                        // Fall through if raw text
                    }
                }
            } catch (Exception ex) {
                log.warn("AI explain failed, using rule-guided fallback: {}", ex.getMessage());
            }
        }

        return buildExplainFallback(request, lang);
    }

    @Override
    public AiRecommendResponse generatePersonalizedRecommendation(AiRecommendRequest request) {
        String lang = "te".equalsIgnoreCase(request.getPreferredLanguage()) ? "te" : "en";

        if (isApiKeyConfigured()) {
            try {
                String prompt = buildRecommendPrompt(request);
                String externalResponse = callLlmApi(prompt);
                if (StringUtils.hasText(externalResponse)) {
                    return AiRecommendResponse.builder()
                            .personalizedRecommendationMessage(externalResponse)
                            .focusAreas(List.of("Concept Foundation", "Step-by-Step Practice", "Retest Readiness"))
                            .isFallbackMode(false)
                            .modeLabel("Live AI Response")
                            .build();
                }
            } catch (Exception ex) {
                log.warn("AI recommendation failed, using fallback: {}", ex.getMessage());
            }
        }

        return buildRecommendFallback(request, lang);
    }

    @Override
    public AiGenerateQuizResponse generateQuizQuestions(AiGenerateQuizRequest request) {
        String lang = "te".equalsIgnoreCase(request.getPreferredLanguage()) ? "te" : "en";

        if (isApiKeyConfigured()) {
            try {
                String prompt = buildQuizGenPrompt(request);
                String jsonResponse = callLlmApi(prompt);
                if (StringUtils.hasText(jsonResponse)) {
                    AiGenerateQuizResponse parsed = objectMapper.readValue(jsonResponse, AiGenerateQuizResponse.class);
                    parsed.setIsFallbackMode(false);
                    parsed.setModeLabel("Live AI Generated Drafts");
                    return parsed;
                }
            } catch (Exception ex) {
                log.warn("AI quiz generation failed, using fallback quiz draft generator: {}", ex.getMessage());
            }
        }

        return buildQuizGenFallback(request, lang);
    }

    @Override
    public AiStudyPlanResponse generateStudyPlan(AiStudyPlanRequest request) {
        String lang = "te".equalsIgnoreCase(request.getPreferredLanguage()) ? "te" : "en";
        User student = request.getStudentId() != null ? userRepository.findById(request.getStudentId()).orElse(null) : null;
        String studentName = student != null ? student.getName() : "Learner";

        if (isApiKeyConfigured()) {
            try {
                String prompt = buildStudyPlanPrompt(request, studentName);
                String rawJson = callLlmApi(prompt);
                if (StringUtils.hasText(rawJson)) {
                    AiStudyPlanResponse parsed = objectMapper.readValue(rawJson, AiStudyPlanResponse.class);
                    parsed.setIsFallbackMode(false);
                    parsed.setModeLabel("Live AI Study Plan");
                    return parsed;
                }
            } catch (Exception ex) {
                log.warn("AI study plan generation failed: {}", ex.getMessage());
            }
        }

        return buildStudyPlanFallback(request, studentName, lang);
    }

    // --- PROMPT BUILDERS ---

    private String buildTutorPrompt(AiAskRequest req) {
        return String.format(
                "You are an educational AI Learning Assistant for rural school students in Telangana State, India.\n" +
                "Context: Class %d, Subject: %s, Topic: %s, Preferred Language: %s.\n" +
                "Question: %s\n" +
                "Instructions: Explain simply at Class %d level. Support Telugu and English. Give clear step-by-step examples. Keep response focused and encouraging.",
                req.getClassLevel() != null ? req.getClassLevel() : 10,
                req.getSubjectName() != null ? req.getSubjectName() : "Mathematics",
                req.getTopicName() != null ? req.getTopicName() : "General",
                req.getPreferredLanguage(),
                req.getQuestion(),
                req.getClassLevel() != null ? req.getClassLevel() : 10
        );
    }

    private String buildExplainPrompt(AiExplainAnswerRequest req) {
        return String.format(
                "You are an AI Educational Assistant. A Class %d student in Telangana answered a question incorrectly.\n" +
                "Topic: %s, Question: %s\n" +
                "Student Answer: %s, Correct Answer: %s\n" +
                "Return structured explanation in %s containing: whyWrong, correctConcept, stepByStepSolution, similarExample.",
                req.getClassLevel(), req.getTopicName(), req.getQuestionText(), req.getStudentAnswer(), req.getCorrectAnswer(), req.getPreferredLanguage()
        );
    }

    private String buildRecommendPrompt(AiRecommendRequest req) {
        return String.format(
                "Student scored %.1f%% in Topic: %s (Class %d). Language: %s. " +
                "Provide a short 2-sentence personalized study advice telling them which specific material to read and practice.",
                req.getScore(), req.getTopicName(), req.getClassLevel(), req.getPreferredLanguage()
        );
    }

    private String buildQuizGenPrompt(AiGenerateQuizRequest req) {
        return String.format(
                "Generate %d multiple choice questions for Class %d %s, Topic: %s, Difficulty: %s.\n" +
                "Return JSON matching AiGenerateQuizResponse schema with optionsEn, optionsTe, correctOptionIndex (0-3), explanationEn, explanationTe.",
                req.getNumberOfQuestions() != null ? req.getNumberOfQuestions() : 3,
                req.getClassLevel(), req.getSubjectName(), req.getTopicName(), req.getDifficulty()
        );
    }

    private String buildStudyPlanPrompt(AiStudyPlanRequest req, String studentName) {
        return String.format(
                "Generate a 5-day study plan for %s (Class %d) in language %s. Return structured JSON with days array.",
                studentName, req.getClassLevel(), req.getPreferredLanguage()
        );
    }

    // --- LLM API CALLER ---

    private String callLlmApi(String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (apiKey.startsWith("AIza")) { // Gemini API key format
                String url = "https://generativelanguage.googleapis.com/v1beta/models/" + modelName + ":generateContent?key=" + apiKey;
                Map<String, Object> body = Map.of(
                        "contents", List.of(
                                Map.of("parts", List.of(Map.of("text", prompt)))
                        )
                );
                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
                ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    List candidates = (List) response.getBody().get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        Map candidate = (Map) candidates.get(0);
                        Map content = (Map) candidate.get("content");
                        List parts = (List) content.get("parts");
                        Map part = (Map) parts.get(0);
                        return (String) part.get("text");
                    }
                }
            } else { // Standard OpenAI format
                headers.setBearerAuth(apiKey);
                String url = "https://api.openai.com/v1/chat/completions";
                Map<String, Object> body = Map.of(
                        "model", modelName != null ? modelName : "gpt-4o-mini",
                        "messages", List.of(Map.of("role", "user", "content", prompt))
                );
                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
                ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    List choices = (List) response.getBody().get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map choice = (Map) choices.get(0);
                        Map message = (Map) choice.get("message");
                        return (String) message.get("content");
                    }
                }
            }
        } catch (Exception ex) {
            log.error("LLM Provider HTTP Request failed: {}", ex.getMessage());
        }
        return null;
    }

    // --- DETERMINISTIC RULE-GUIDED FALLBACK ENGINE ---

    private String generateTutorFallback(AiAskRequest req, String lang) {
        String q = req.getQuestion() != null ? req.getQuestion().toLowerCase() : "";
        String topic = req.getTopicName() != null ? req.getTopicName() : "Fractions";

        if ("te".equals(lang)) {
            if (q.contains("fraction") || q.contains("భిన్నం") || q.contains("భిన్నాలు")) {
                return "భిన్నాలు (Fractions) అనేవి ఒక పూర్తి భాగాన్ని లేదా సమూహాన్ని సమానంగా విభజించినప్పుడు వచ్చే భాగాలను సూచిస్తాయి.\n\n" +
                       "1. **లభం (Numerator)**: పైన ఉండే సంఖ్య (మనం తీసుకున్న భాగం).\n" +
                       "2. **హారం (Denominator)**: కింద ఉండే సంఖ్య (మొత్తం సమాన భాగాలు).\n\n" +
                       "*ఉదాహరణ*: 1/4 అంటే 4 సమాన భాగాలలో 1 భాగం.\n" +
                       "*కూడిక సూత్రం*: హారాలు సమానంగా ఉన్నప్పుడు లభాలను నేరుగా కూడాలి (1/4 + 2/4 = 3/4).";
            }
            return "తెలంగాణ స్టేట్ బోర్డ్ పాఠ్యాంశాల ప్రకారం " + topic + " కు సంబంధించిన వివరణ:\n" +
                   "ఈ అంశాన్ని సులభంగా అర్థం చేసుకోవడానికి ఉదాహరణలను పరిశీలించండి. ముందుగా ప్రాథమిక సూత్రాలు అధ్యయనం చేసి, సాధన ప్రశ్నలు పరిష్కరించడం ద్వారా మంచి మార్కులు సాధించవచ్చు.";
        } else {
            if (q.contains("fraction")) {
                return "Fractions represent equal parts of a whole shape or number.\n\n" +
                       "1. **Numerator** (Top number): Indicates how many parts we have.\n" +
                       "2. **Denominator** (Bottom number): Indicates total equal parts.\n\n" +
                       "*Example*: 1/4 means 1 part out of 4 equal divisions.\n" +
                       "*Rule*: To add fractions with equal denominators, simply add the numerators: 1/4 + 2/4 = 3/4.";
            }
            return "Based on Telangana Class " + (req.getClassLevel() != null ? req.getClassLevel() : 10) + " syllabus for " + topic + ":\n" +
                   "Break down complex problems into step-by-step rules. Review basic definitions first, watch low-bandwidth video lessons, and complete practice problem sets before taking your retest.";
        }
    }

    private AiExplainAnswerResponse buildExplainFallback(AiExplainAnswerRequest req, String lang) {
        boolean isTe = "te".equals(lang);

        String whyWrong = isTe ?
                "మీరు ఎంచుకున్న సమాధానం (" + req.getStudentAnswer() + ") తప్పు, ఎందుకంటే లభాలు మరియు హారాలను నేరుగా కూడకూడదు." :
                "Your selected answer (" + req.getStudentAnswer() + ") is incorrect because numerators and denominators were added directly without converting to common denominators.";

        String correctConcept = isTe ?
                "హారాలు వేర్వేరుగా ఉన్నప్పుడు ముందుగా హారాల క.సా.గు (LCM) కనుగొని సమాన హారాల భిన్నాలుగా మార్చాలి." :
                "When denominators are different, first find the Least Common Multiple (LCM) of the denominators to create like fractions.";

        String stepByStep = isTe ?
                "అడుగు 1: హారాల క.సా.గు కనుగొనండి.\nఅడుగు 2: సమాన భిన్నాలుగా మార్చండి.\nఅడుగు 3: లభాలను కూడండి: జవాబు = " + req.getCorrectAnswer() :
                "Step 1: Find the LCM of denominators.\nStep 2: Convert to like fractions.\nStep 3: Add the numerators to get: " + req.getCorrectAnswer();

        String similarExample = isTe ?
                "సాధన ఉదాహరణ: 1/3 + 1/6 = 2/6 + 1/6 = 3/6 = 1/2." :
                "Practice Example: 1/3 + 1/6 = 2/6 + 1/6 = 3/6 = 1/2.";

        return AiExplainAnswerResponse.builder()
                .whyWrong(whyWrong)
                .correctConcept(correctConcept)
                .stepByStepSolution(stepByStep)
                .similarExample(similarExample)
                .isFallbackMode(true)
                .modeLabel("AI Demo/Fallback Mode")
                .build();
    }

    private AiRecommendResponse buildRecommendFallback(AiRecommendRequest req, String lang) {
        boolean isTe = "te".equals(lang);

        String msg;
        if (isTe) {
            msg = String.format("%s %dవ తరగతి %s లో మీ స్కోర్ %.1f%%. ముందుగా ప్రాథమిక భిన్నాల నోట్స్ చదవండి, చిన్న పునశ్చరణ వీడియో చూడండి, 15 అభ్యాస ప్రశ్నలను సాధించి ఆపై రీటెస్ట్ రాయండి.",
                    "విద్యార్థి", req.getClassLevel() != null ? req.getClassLevel() : 10, req.getTopicName(), req.getScore());
        } else {
            msg = String.format("Learner scored %.1f%% in %s (Class %d). Start with the basic notes lesson, watch the low-bandwidth revision video, complete the practice question set, and then take the retest.",
                    req.getScore(), req.getTopicName(), req.getClassLevel() != null ? req.getClassLevel() : 10);
        }

        return AiRecommendResponse.builder()
                .personalizedRecommendationMessage(msg)
                .focusAreas(List.of("Basic Concept Review", "Step-by-Step LCM Practice", "Post-Test Retest"))
                .isFallbackMode(true)
                .modeLabel("AI Demo/Fallback Mode")
                .build();
    }

    private AiGenerateQuizResponse buildQuizGenFallback(AiGenerateQuizRequest req, String lang) {
        boolean isTe = "te".equals(lang);

        AiQuestionDto q1 = AiQuestionDto.builder()
                .id(UUID.randomUUID().toString())
                .questionTextEn("What is the result of 1/3 + 1/3?")
                .questionTextTe("1/3 + 1/3 యొక్క విలువ ఎంత?")
                .optionsEn(List.of("2/3", "2/6", "1/6", "3/3"))
                .optionsTe(List.of("2/3", "2/6", "1/6", "3/3"))
                .correctOptionIndex(0)
                .explanationEn("Since denominators are equal, add numerators: 1+1=2, so 2/3.")
                .explanationTe("హారాలు సమానం కాబట్టి లభాలను కూడండి: 1+1=2, కాబట్టి 2/3.")
                .marks(1.0)
                .difficulty(req.getDifficulty() != null ? req.getDifficulty() : "MEDIUM")
                .approved(false)
                .build();

        AiQuestionDto q2 = AiQuestionDto.builder()
                .id(UUID.randomUUID().toString())
                .questionTextEn("Simplify the fraction 8/12 to its lowest term.")
                .questionTextTe("8/12 భిన్నాన్ని అతి చిన్న రూపంలోకి సూక్ష్మీకరించండి.")
                .optionsEn(List.of("2/3", "4/6", "3/4", "1/2"))
                .optionsTe(List.of("2/3", "4/6", "3/4", "1/2"))
                .correctOptionIndex(0)
                .explanationEn("Divide numerator and denominator by 4: 8/12 = 2/3.")
                .explanationTe("లభం మరియు హారాన్ని 4 తో భాగించండి: 8/12 = 2/3.")
                .marks(1.0)
                .difficulty(req.getDifficulty() != null ? req.getDifficulty() : "MEDIUM")
                .approved(false)
                .build();

        return AiGenerateQuizResponse.builder()
                .questions(List.of(q1, q2))
                .isFallbackMode(true)
                .modeLabel("AI Demo/Fallback Mode")
                .build();
    }

    private AiStudyPlanResponse buildStudyPlanFallback(AiStudyPlanRequest req, String studentName, String lang) {
        boolean isTe = "te".equals(lang);

        List<StudyPlanDayDto> days = List.of(
                StudyPlanDayDto.builder()
                        .dayName("Monday")
                        .taskTitleEn("Fractions Concept Review & Basic Notes")
                        .taskTitleTe("భిన్నాల ప్రాథమిక భావనలు మరియు నోట్స్ చదవడం")
                        .durationMinutes(20)
                        .materialType("NOTES")
                        .completed(true)
                        .build(),
                StudyPlanDayDto.builder()
                        .dayName("Tuesday")
                        .taskTitleEn("Low-Bandwidth Video Lesson on LCM Addition")
                        .taskTitleTe("క.సా.గు కూడికలపై తక్కువ డేటా వీడియో పాఠం")
                        .durationMinutes(15)
                        .materialType("VIDEO")
                        .completed(false)
                        .build(),
                StudyPlanDayDto.builder()
                        .dayName("Wednesday")
                        .taskTitleEn("Remedial Practice Set (10 Questions)")
                        .taskTitleTe("పునశ్చరణ అభ్యాస ప్రశ్నాపత్రం (10 ప్రశ్నలు)")
                        .durationMinutes(25)
                        .materialType("PRACTICE")
                        .completed(false)
                        .build(),
                StudyPlanDayDto.builder()
                        .dayName("Thursday")
                        .taskTitleEn("AI Learning Assistant Doubts Clarification")
                        .taskTitleTe("AI ట్యూటర్ ద్వార సందేహాల నివృత్తి")
                        .durationMinutes(15)
                        .materialType("NOTES")
                        .completed(false)
                        .build(),
                StudyPlanDayDto.builder()
                        .dayName("Friday")
                        .taskTitleEn("Post-Test Mastery Retest Assessment")
                        .taskTitleTe("Post-Test పునఃపరీక్ష రాత పరీక్ష")
                        .durationMinutes(20)
                        .materialType("RETEST")
                        .completed(false)
                        .build()
        );

        return AiStudyPlanResponse.builder()
                .studentName(studentName)
                .planTitleEn("5-Day Personalized Mastery Study Plan")
                .planTitleTe("5 రోజుల వ్యక్తిగతీకరించిన అధ్యయన ప్రణాళిక")
                .days(days)
                .isFallbackMode(true)
                .modeLabel("AI Demo/Fallback Mode")
                .build();
    }

    private void saveInteraction(Long studentId, String type, String question, String response, String lang, boolean isFallback) {
        try {
            aiInteractionRepository.save(AiInteraction.builder()
                    .studentId(studentId)
                    .interactionType(type)
                    .question(question)
                    .response(response)
                    .language(lang)
                    .isFallbackMode(isFallback)
                    .build());
        } catch (Exception ex) {
            log.warn("Failed to persist AI interaction audit log: {}", ex.getMessage());
        }
    }
}
