package application.usercases;

import com.applydigital.application.model.NewsGetResponseDTO;
import com.applydigital.domain.model.DeletedNewsEntity;
import com.applydigital.domain.model.NewsEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NewsTestData {

    public static NewsEntity createFakeNewsDTO() {
        return new NewsEntity(
                "obj-12345",
                "Andrew Doe",
                "This is a sample comment about new tech trends.",
                "Tech Stack  in 2025",
                "https://example.com/tech-stack-2025",
                List.of("Technology", "Innovation"),
                LocalDateTime.of(2025, 10, 29, 10, 30),
                LocalDateTime.of(2025, 10, 29, 11, 15),
                1001L,
                5005L
        );
    }

    public static NewsGetResponseDTO createFakeNewsGetResponseDTO() {
        NewsEntity field = createFakeNewsDTO();
        return new NewsGetResponseDTO(
                field.getAuthor(),
                field.getCommentText(),
                field.getStoryTitle(),
                field.getStoryUrl(),
                field.getTags(),
                field.getCreatedAt(),
                field.getObjectId());
    }


    public static DeletedNewsEntity createFakeDeletedNewsEntity() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        return new DeletedNewsEntity("obj-24680", now);
    }


    public static List<NewsEntity> createFakeNewsDTOList() {
        return List.of(
                new NewsEntity(
                        "obj-12345",
                        "Andrew Doe",
                        "This is a sample comment about new tech trends.",
                        "Tech Stack in 2025",
                        "https://example.com/tech-stack-2025",
                        List.of("Technology", "Innovation"),
                        LocalDateTime.of(2025, 10, 29, 10, 30),
                        LocalDateTime.of(2025, 10, 29, 11, 15),
                        1001L,
                        5005L
                ),
                new NewsEntity(
                        "obj-67890",
                        "Maria Johnson",
                        "AI-driven tools are reshaping the productivity landscape.",
                        "AI Productivity Breakthroughs",
                        "https://example.com/ai-productivity",
                        List.of("AI", "Productivity"),
                        LocalDateTime.of(2025, 11, 2, 14, 10),
                        LocalDateTime.of(2025, 11, 2, 14, 45),
                        2002L,
                        6006L
                ),
                new NewsEntity(
                        "obj-24680",
                        "Carlos Martínez",
                        "The gaming industry sees a rise in cloud-native engines.",
                        "Cloud Gaming Revolution 2025",
                        "https://example.com/cloud-gaming",
                        List.of("Gaming", "Cloud"),
                        LocalDateTime.of(2025, 10, 20, 9, 0),
                        LocalDateTime.of(2025, 10, 20, 9, 30),
                        3003L,
                        7007L
                ),
                new NewsEntity(
                        "obj-13579",
                        "Sarah Kim",
                        "Electric vehicle sales continue to grow across Europe.",
                        "EV Market Growth Report",
                        "https://example.com/ev-growth",
                        List.of("Automotive", "Sustainability"),
                        LocalDateTime.of(2025, 9, 15, 8, 20),
                        LocalDateTime.of(2025, 9, 15, 9, 5),
                        4004L,
                        8008L
                ),
                new NewsEntity(
                        "obj-98765",
                        "Tom Becker",
                        "Startups are doubling down on cybersecurity investments.",
                        "Cybersecurity Trends 2025",
                        "https://example.com/cybersecurity-trends",
                        List.of("Cybersecurity", "Startups"),
                        LocalDateTime.of(2025, 11, 10, 16, 45),
                        LocalDateTime.of(2025, 11, 10, 17, 20),
                        5005L,
                        9009L
                ),
                new NewsEntity(
                        "obj-11223",
                        "Laura Smith",
                        "Advances in quantum computing open new possibilities.",
                        "Quantum Computing Milestones",
                        "https://example.com/quantum-milestones",
                        List.of("Quantum", "Research"),
                        LocalDateTime.of(2025, 12, 1, 11, 10),
                        LocalDateTime.of(2025, 12, 1, 11, 50),
                        6006L,
                        10010L
                )
        );

    }

}
