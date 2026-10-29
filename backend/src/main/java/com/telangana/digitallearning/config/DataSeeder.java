package com.telangana.digitallearning.config;

import com.telangana.digitallearning.entity.*;
import com.telangana.digitallearning.repository.*;
import com.telangana.digitallearning.service.EvaluationService;
import com.telangana.digitallearning.service.RecommendationEngineService;
import com.telangana.digitallearning.service.WeakTopicDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final ChapterRepository chapterRepository;
    private final TopicRepository topicRepository;
    private final LearningMaterialRepository materialRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository optionRepository;
    private final QuizAttemptRepository attemptRepository;
    private final PasswordEncoder passwordEncoder;
    private final WeakTopicDetectionService weakTopicDetectionService;
    private final RecommendationEngineService recommendationEngineService;
    private final EvaluationService evaluationService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return; // Data already seeded
        }

        // 1. Create Demo Users
        User admin = User.builder()
                .name("Telangana Admin")
                .email("admin@telangana.edu")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ROLE_ADMIN)
                .classLevel(10)
                .preferredLanguage("en")
                .build();

        User teacher = User.builder()
                .name("Srinivas Rao (Teacher)")
                .email("teacher@telangana.edu")
                .password(passwordEncoder.encode("teacher123"))
                .role(Role.ROLE_TEACHER)
                .classLevel(10)
                .preferredLanguage("en")
                .build();

        User student1 = User.builder()
                .name("Rahul Kumar")
                .email("student@telangana.edu")
                .password(passwordEncoder.encode("student123"))
                .role(Role.ROLE_STUDENT)
                .classLevel(10)
                .preferredLanguage("te")
                .build();

        User student2 = User.builder()
                .name("Priya Sharma")
                .email("student2@telangana.edu")
                .password(passwordEncoder.encode("student123"))
                .role(Role.ROLE_STUDENT)
                .classLevel(10)
                .preferredLanguage("en")
                .build();

        userRepository.saveAll(List.of(admin, teacher, student1, student2));

        // 2. Create Classes (6 to 10)
        ClassEntity c6 = classRepository.save(ClassEntity.builder().classNumber(6).name("Class 6 - Telangana Board").build());
        ClassEntity c7 = classRepository.save(ClassEntity.builder().classNumber(7).name("Class 7 - Telangana Board").build());
        ClassEntity c8 = classRepository.save(ClassEntity.builder().classNumber(8).name("Class 8 - Telangana Board").build());
        ClassEntity c9 = classRepository.save(ClassEntity.builder().classNumber(9).name("Class 9 - Telangana Board").build());
        ClassEntity c10 = classRepository.save(ClassEntity.builder().classNumber(10).name("Class 10 - Telangana Board").build());

        // 3. Create Subjects for Class 10
        Subject math = subjectRepository.save(Subject.builder().classId(c10.getId()).nameEn("Mathematics").nameTe("గణితం").code("MATH10").icon("Calculator").build());
        Subject science = subjectRepository.save(Subject.builder().classId(c10.getId()).nameEn("Science").nameTe("విజ్ఞాన శాస్త్రం").code("SCI10").icon("Atom").build());
        Subject social = subjectRepository.save(Subject.builder().classId(c10.getId()).nameEn("Social Studies").nameTe("సామాజిక శాస్త్రం").code("SOC10").icon("Globe").build());
        Subject english = subjectRepository.save(Subject.builder().classId(c10.getId()).nameEn("English").nameTe("ఇంగ్లీష్").code("ENG10").icon("BookOpen").build());
        Subject telugu = subjectRepository.save(Subject.builder().classId(c10.getId()).nameEn("Telugu").nameTe("తెలుగు").code("TEL10").icon("Languages").build());

        // 4. Create Chapters for Mathematics
        Chapter ch1 = chapterRepository.save(Chapter.builder().subjectId(math.getId()).chapterNumber(1).titleEn("Fractions & Rational Numbers").titleTe("భిన్నాలు మరియు హేతుబద్ధ సంఖ్యలు").build());
        Chapter ch2 = chapterRepository.save(Chapter.builder().subjectId(math.getId()).chapterNumber(2).titleEn("Real Numbers & Logarithms").titleTe("వాస్తవ సంఖ్యలు మరియు సంవర్గమానాలు").build());

        // Create Chapters for Science
        Chapter ch3 = chapterRepository.save(Chapter.builder().subjectId(science.getId()).chapterNumber(1).titleEn("Life Processes & Nutrition").titleTe("జీవ క్రియలు మరియు పోషణ").build());

        // 5. Create Topics
        Topic tFractions = topicRepository.save(Topic.builder()
                .chapterId(ch1.getId())
                .nameEn("Fractions & Operations")
                .nameTe("భిన్నాలు మరియు కార్యకలాపాలు")
                .descriptionEn("Understanding proper, improper fractions and addition/subtraction rules.")
                .descriptionTe("క్రమ, అపక్రమ భిన్నాలు మరియు కూడికలు అర్థం చేసుకోవడం.")
                .build());

        Topic tRealNums = topicRepository.save(Topic.builder()
                .chapterId(ch2.getId())
                .nameEn("Real Numbers & Irrational Proofs")
                .nameTe("వాస్తవ సంఖ్యలు మరియు అకరణీయ రుజువులు")
                .descriptionEn("Proving root 2 is irrational and fundamental theorem of arithmetic.")
                .descriptionTe("రూట్ 2 అకరణీయమని నిరూపించడం.")
                .build());

        Topic tNutrition = topicRepository.save(Topic.builder()
                .chapterId(ch3.getId())
                .nameEn("Photosynthesis & Plant Respiration")
                .nameTe("కిరణజన్య సంయోగక్రియ మరియు శ్వాసక్రియ")
                .descriptionEn("Autotrophic nutrition, chloroplast structure, and light/dark reactions.")
                .descriptionTe("స్వయం పోషణ, హరితరేణువు నిర్మాణం మరియు కాంతి చర్యలు.")
                .build());

        // 6. Create Learning Materials (Notes, Low-bandwidth Videos, PDF)
        LearningMaterial matFractions1 = materialRepository.save(LearningMaterial.builder()
                .topicId(tFractions.getId())
                .classLevel(10)
                .titleEn("Fractions Revision Notes & Formulas")
                .titleTe("భిన్నాల పునశ్చరణ నోట్స్ మరియు సూత్రాలు")
                .descriptionEn("Step-by-step simple guide to solving fraction addition, subtraction, and mixed numbers.")
                .descriptionTe("భిన్నాల కూడిక, తీసివేత సులభమైన పద్ధతిలో నోట్స్.")
                .materialType("NOTES")
                .contentTextEn("# Fractions Concept Summary\n\n1. **Proper Fraction**: Numerator < Denominator (e.g., 3/5)\n2. **Improper Fraction**: Numerator >= Denominator (e.g., 7/4)\n3. **Addition Rule**: Find LCM of denominators, convert to like fractions, then add numerators.\n\n*Example*: 1/3 + 2/5 = 5/15 + 6/15 = 11/15.")
                .contentTextTe("# భిన్నాల సారాంశం\n\n1. **క్రమ భిన్నం**: లభం < హారం (ఉదా: 3/5)\n2. **అపక్రమ భిన్నం**: లభం >= హారం (ఉదా: 7/4)\n3. **కూడిక నియమం**: హారాల క.సా.గు కనుగొని కూడాలి.")
                .fileSizeKb(120L)
                .downloadable(true)
                .lowBandwidthOptimized(true)
                .build());

        LearningMaterial matFractions2 = materialRepository.save(LearningMaterial.builder()
                .topicId(tFractions.getId())
                .classLevel(10)
                .titleEn("Fractions Low-Data Video Lesson (3.2 MB)")
                .titleTe("భిన్నాలు లో-డేటా వీడియో పాఠం")
                .descriptionEn("Low-bandwidth compressed video explaining fraction problem solving.")
                .descriptionTe("భిన్నాల లెక్కలు సాధించే విధానం వీడియో.")
                .materialType("VIDEO")
                .fileUrl("https://www.youtube.com/embed/sample_fractions_telangana")
                .fileSizeKb(3200L)
                .downloadable(true)
                .lowBandwidthOptimized(true)
                .build());

        LearningMaterial matFractionsPractice = materialRepository.save(LearningMaterial.builder()
                .topicId(tFractions.getId())
                .classLevel(10)
                .titleEn("Fractions Remedial Practice Question Set")
                .titleTe("భిన్నాల సాధన ప్రశ్నాపత్రం")
                .descriptionEn("10 remedial practice questions with worked answers for weak learners.")
                .descriptionTe("బలహీన విద్యార్థుల కోసం 10 సాధన ప్రశ్నలు.")
                .materialType("PRACTICE")
                .contentTextEn("Q1: Add 2/7 + 3/7 = 5/7\nQ2: Simplify 4/12 = 1/3\nQ3: Subtract 5/6 - 1/3 = 5/6 - 2/6 = 3/6 = 1/2")
                .contentTextTe("ప్రశ్న 1: 2/7 + 3/7 ఎంత? జవాబు: 5/7\nప్రశ్న 2: 4/12 ని సూక్ష్మీకరించండి. జవాబు: 1/3")
                .fileSizeKb(85L)
                .downloadable(true)
                .lowBandwidthOptimized(true)
                .build());

        // 7. Create Quizzes
        Quiz qFractionsPre = quizRepository.save(Quiz.builder()
                .topicId(tFractions.getId())
                .titleEn("Fractions Baseline Quiz (Pre-Test)")
                .titleTe("భిన్నాలు ప్రాథమిక పరీక్ష (Pre-Test)")
                .classLevel(10)
                .durationMinutes(15)
                .totalMarks(5.0)
                .passingMarks(3.0)
                .isEvaluationTest(true)
                .testCategory("PRE_TEST")
                .build());

        Quiz qFractionsRetest = quizRepository.save(Quiz.builder()
                .topicId(tFractions.getId())
                .titleEn("Fractions Mastery Retest (Post-Test)")
                .titleTe("భిన్నాలు పునఃపరీక్ష (Post-Test)")
                .classLevel(10)
                .durationMinutes(15)
                .totalMarks(5.0)
                .passingMarks(3.0)
                .isEvaluationTest(true)
                .testCategory("POST_TEST")
                .build());

        // Questions for Pre-Test
        createQuestion(qFractionsPre.getId(), tFractions.getId(),
                "What is the result of 1/4 + 2/4?",
                "1/4 + 2/4 యొక్క ఫలితం ఎంత?",
                "Since denominators are equal, add numerators: (1+2)/4 = 3/4.", "హారాలు సమానం కాబట్టి లభాలను కూడండి: (1+2)/4 = 3/4.",
                List.of("3/4", "3/8", "2/4", "1/2"), 0);

        createQuestion(qFractionsPre.getId(), tFractions.getId(),
                "Simplify 6/18 to its lowest terms.",
                "6/18 ని అతి తక్కువ పదాలకు సూక్ష్మీకరించండి.",
                "Divide numerator and denominator by 6: 6/18 = 1/3.", "లభం మరియు హారాన్ని 6 తో భాగించండి: 6/18 = 1/3.",
                List.of("1/3", "2/6", "3/9", "1/2"), 0);

        createQuestion(qFractionsPre.getId(), tFractions.getId(),
                "Which of the following is an Improper Fraction?",
                "క్రింది వాటిలో అపక్రమ భిన్నం ఏది?",
                "An improper fraction has Numerator >= Denominator. 7/5 has numerator 7 > denominator 5.", "అపక్రమ భిన్నంలో లభం >= హారం. 7/5 లో లభం 7 > హారం 5.",
                List.of("3/5", "7/5", "1/2", "4/9"), 1);

        createQuestion(qFractionsPre.getId(), tFractions.getId(),
                "Calculate 5/6 - 1/6:",
                "5/6 - 1/6 ను లెక్కించండి:",
                "5/6 - 1/6 = 4/6 = 2/3.", "5/6 - 1/6 = 4/6 = 2/3.",
                List.of("4/6 (or 2/3)", "4/0", "6/6", "1/6"), 0);

        createQuestion(qFractionsPre.getId(), tFractions.getId(),
                "Find the LCM of 3 and 5 used to add 1/3 + 1/5:",
                "1/3 + 1/5 ని కూడటానికి 3 మరియు 5 ల క.సా.గు ఎంత?",
                "LCM of prime numbers 3 and 5 is 3 * 5 = 15.", "ప్రధాన సంఖ్యలు 3 మరియు 5 ల క.సా.గు 3 * 5 = 15.",
                List.of("15", "8", "35", "10"), 0);

        // Questions for Post-Test
        createQuestion(qFractionsRetest.getId(), tFractions.getId(),
                "What is 3/8 + 2/8?",
                "3/8 + 2/8 యొక్క విలువ ఎంత?",
                "Add numerators: 3+2=5, so 5/8.", "లభాలను కూడండి: 3+2=5, కాబట్టి 5/8.",
                List.of("5/8", "5/16", "1/8", "6/8"), 0);

        createQuestion(qFractionsRetest.getId(), tFractions.getId(),
                "Solve 2/3 + 1/4:",
                "2/3 + 1/4 ను సాధించండి:",
                "LCM = 12. 8/12 + 3/12 = 11/12.", "క.సా.గు = 12. 8/12 + 3/12 = 11/12.",
                List.of("11/12", "3/7", "5/12", "7/12"), 0);

        createQuestion(qFractionsRetest.getId(), tFractions.getId(),
                "Convert 7/3 to a mixed fraction:",
                "7/3 ని మిశ్రమ భిన్నంగా మార్చండి:",
                "7 divided by 3 gives 2 quotient and 1 remainder = 2 1/3.", "7 ని 3 తో భాగిస్తే భాగఫలం 2, శేషం 1 = 2 1/3.",
                List.of("2 1/3", "3 1/2", "1 4/3", "2 2/3"), 0);

        createQuestion(qFractionsRetest.getId(), tFractions.getId(),
                "Multiply 2/5 * 3/4:",
                "2/5 * 3/4 ను గుణించండి:",
                "(2*3)/(5*4) = 6/20 = 3/10.", "(2*3)/(5*4) = 6/20 = 3/10.",
                List.of("3/10", "5/20", "6/9", "1/2"), 0);

        createQuestion(qFractionsRetest.getId(), tFractions.getId(),
                "Which fraction is equivalent to 1/2?",
                "1/2 కి సమానమైన భిన్నం ఏది?",
                "4/8 = 1/2.", "4/8 = 1/2.",
                List.of("4/8", "3/8", "2/5", "5/8"), 0);

        // 8. Seed Initial Student Attempt & Weak Topic Detection for Rahul
        // Rahul took Pre-Test and scored 20% (1/5) -> WEAK TOPIC DETECTED!
        weakTopicDetectionService.recordOrUpdatePerformance(student1.getId(), tFractions.getId(), 35.0);
        recommendationEngineService.generateRecommendation(student1.getId(), tFractions.getId(), 35.0);
        evaluationService.recordOrUpdateEvaluation(student1.getId(), tFractions.getId(), 35.0, 75.0);

        // Seed attempt for Priya (Priya scored 80% GOOD)
        weakTopicDetectionService.recordOrUpdatePerformance(student2.getId(), tFractions.getId(), 80.0);
        recommendationEngineService.generateRecommendation(student2.getId(), tFractions.getId(), 80.0);
        evaluationService.recordOrUpdateEvaluation(student2.getId(), tFractions.getId(), 60.0, 90.0);
    }

    private void createQuestion(Long quizId, Long topicId, String textEn, String textTe,
                                String expEn, String expTe, List<String> options, int correctIdx) {
        Question q = questionRepository.save(Question.builder()
                .quizId(quizId)
                .topicId(topicId)
                .questionTextEn(textEn)
                .questionTextTe(textTe)
                .explanationEn(expEn)
                .explanationTe(expTe)
                .marks(1.0)
                .build());

        for (int i = 0; i < options.size(); i++) {
            optionRepository.save(QuestionOption.builder()
                    .questionId(q.getId())
                    .optionTextEn(options.get(i))
                    .optionTextTe(options.get(i))
                    .isCorrect(i == correctIdx)
                    .build());
        }
    }
}
