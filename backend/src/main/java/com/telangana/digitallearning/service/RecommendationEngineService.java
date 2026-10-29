package com.telangana.digitallearning.service;

import com.telangana.digitallearning.dto.RecommendationDto;
import com.telangana.digitallearning.entity.LearningMaterial;
import com.telangana.digitallearning.entity.Recommendation;
import com.telangana.digitallearning.entity.Topic;
import com.telangana.digitallearning.repository.LearningMaterialRepository;
import com.telangana.digitallearning.repository.RecommendationRepository;
import com.telangana.digitallearning.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationEngineService {

    private final RecommendationRepository recommendationRepository;
    private final LearningMaterialRepository materialRepository;
    private final TopicRepository topicRepository;

    /**
     * Primary Research Recommendation Rule Algorithm:
     * - Score < 40%: Weak -> Basic learning material + step-by-step video + practice questions
     * - Score 40-60%: Needs Improvement -> Revision material + summary notes
     * - Score > 60%: Good -> Next-level enrichment / advanced topics
     */
    public Recommendation generateRecommendation(Long studentId, Long topicId, Double scorePercentage) {
        Topic topic = topicRepository.findById(topicId).orElse(null);
        String topicName = topic != null ? topic.getNameEn() : "Topic";

        List<LearningMaterial> materials = materialRepository.findByTopicId(topicId);

        String recommendationType;
        String msgEn;
        String msgTe;
        Long recommendedMaterialId = null;

        if (scorePercentage < 40.0) {
            recommendationType = "WEAK_REMEDIAL";
            msgEn = "Score is below 40% in " + topicName + ". Recommendation: Review basic notes, watch low-bandwidth video lesson, and complete practice set before retesting.";
            msgTe = topicName + " లో మీ స్కోర్ 40% కంటే తక్కువగా ఉంది. సూచన: ప్రాథమిక నోట్స్ చదవండి మరియు రీటెస్ట్ ఇచ్చే ముందు వీడియో వీక్షించండి.";
            
            // Pick notes or video
            recommendedMaterialId = materials.stream()
                    .filter(m -> "NOTES".equalsIgnoreCase(m.getMaterialType()) || "VIDEO".equalsIgnoreCase(m.getMaterialType()))
                    .map(LearningMaterial::getId)
                    .findFirst()
                    .orElse(!materials.isEmpty() ? materials.get(0).getId() : null);

        } else if (scorePercentage <= 60.0) {
            recommendationType = "REVISION";
            msgEn = "Score is between 40-60% in " + topicName + ". Recommendation: Revise key concepts and solve additional practice problems to strengthen mastery.";
            msgTe = topicName + " లో మీ స్కోర్ 40-60% ఉంది. సూచన: ముఖ్యమైన భావనలను పునశ్చరణ చేయండి మరియు అభ్యాస ప్రశ్నలను సాధించండి.";
            
            recommendedMaterialId = materials.stream()
                    .filter(m -> "PRACTICE".equalsIgnoreCase(m.getMaterialType()) || "NOTES".equalsIgnoreCase(m.getMaterialType()))
                    .map(LearningMaterial::getId)
                    .findFirst()
                    .orElse(!materials.isEmpty() ? materials.get(0).getId() : null);

        } else {
            recommendationType = "ADVANCED";
            msgEn = "Excellent score (>60%) in " + topicName + "! You have mastered this concept. Proceed to next level topic materials.";
            msgTe = topicName + " లో అద్భుతమైన స్కోర్ (>60%)! మీరు ఈ అంశాన్ని బాగా అర్థం చేసుకున్నారు. తదుపరి స్థాయికి వెళ్లండి.";

            recommendedMaterialId = !materials.isEmpty() ? materials.get(0).getId() : null;
        }

        Recommendation rec = Recommendation.builder()
                .studentId(studentId)
                .topicId(topicId)
                .previousScore(scorePercentage)
                .recommendationType(recommendationType)
                .recommendationMessageEn(msgEn)
                .recommendationMessageTe(msgTe)
                .recommendedMaterialId(recommendedMaterialId)
                .status("PENDING")
                .build();

        return recommendationRepository.save(rec);
    }

    public List<RecommendationDto> getRecommendationsForStudent(Long studentId) {
        List<Recommendation> list = recommendationRepository.findByStudentId(studentId);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public RecommendationDto convertToDto(Recommendation rec) {
        Topic topic = topicRepository.findById(rec.getTopicId()).orElse(null);
        LearningMaterial mat = rec.getRecommendedMaterialId() != null ?
                materialRepository.findById(rec.getRecommendedMaterialId()).orElse(null) : null;

        return RecommendationDto.builder()
                .id(rec.getId())
                .topicId(rec.getTopicId())
                .topicNameEn(topic != null ? topic.getNameEn() : "Topic #" + rec.getTopicId())
                .topicNameTe(topic != null ? topic.getNameTe() : "అంశం #" + rec.getTopicId())
                .previousScore(rec.getPreviousScore())
                .recommendationType(rec.getRecommendationType())
                .messageEn(rec.getRecommendationMessageEn())
                .messageTe(rec.getRecommendationMessageTe())
                .recommendedMaterialId(rec.getRecommendedMaterialId())
                .recommendedMaterialTitleEn(mat != null ? mat.getTitleEn() : "Remedial Learning Resource")
                .recommendedMaterialTitleTe(mat != null ? mat.getTitleTe() : "పునశ్చరణ పదార్థం")
                .materialType(mat != null ? mat.getMaterialType() : "NOTES")
                .status(rec.getStatus())
                .createdAt(rec.getCreatedAt())
                .retestScore(rec.getRetestScore())
                .build();
    }
}
