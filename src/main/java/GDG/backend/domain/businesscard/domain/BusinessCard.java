package GDG.backend.domain.businesscard.domain;

import GDG.backend.domain.businesscard.domain.vo.BusinessCardInfoVO;
import GDG.backend.domain.businesscard.exception.UserNotHostException;
import GDG.backend.domain.link.domain.Link;
import GDG.backend.domain.reason.domain.Reason;
import GDG.backend.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.lang.Boolean.FALSE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BusinessCard {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "business_card_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String profileImage;
    private String name;
    private String email;
    private String introduction;
    private String styleTemplate;
    private String designTemplate;

    @OneToMany(mappedBy = "businessCard", cascade = ALL)
    private List<Reason> reasons = new ArrayList<>();

    @OneToMany(mappedBy = "businessCard", cascade = ALL)
    private List<Link> links = new ArrayList<>();

    private String companyName;
    private String position;

    @Builder
    public BusinessCard(User user, String profileImage, String name, String email, String introduction, String styleTemplate,
                        String designTemplate, String companyName, String position) {
        this.user = user;
        this.profileImage = profileImage;
        this.name = name;
        this.email = email;
        this.introduction = introduction;
        this.styleTemplate = styleTemplate;
        this.designTemplate = designTemplate;
        this.companyName = companyName;
        this.position = position;
    }

    public static BusinessCard createBusinessCard(User user, String profileImage, String name, String email, String introduction, String styleTemplate,
                                                  String designTemplate, String companyName, String position) {
        return builder()
                .user(user)
                .profileImage(profileImage)
                .name(name)
                .email(email)
                .introduction(introduction)
                .styleTemplate(styleTemplate)
                .designTemplate(designTemplate)
                .companyName(companyName)
                .position(position)
                .build();
    }

    public BusinessCardInfoVO getBusinessCardInfo() {
        return new BusinessCardInfoVO(
                id,
                name,
                profileImage,
                email,
                introduction,
                styleTemplate,
                designTemplate,
                companyName,
                position
        );
    }

    public void validUserIsHost(Long id) {
        if (!user.getId().equals(id)) {
            throw UserNotHostException.EXCEPTION;
        }
    }

    public void changeProfile(String profileImage, String name, String email, String introduction, String styleTemplate,
                              String designTemplate, String companyName, String position) {
        this.profileImage = profileImage;
        this.name = name;
        this.email = email;
        this.introduction = introduction;
        this.styleTemplate = styleTemplate;
        this.designTemplate = designTemplate;
        this.companyName = companyName;
        this.position = position;
    }

//    public void setReasons(Reason reasons) {
//        this.reasons.add(reasons);
//    }
}
